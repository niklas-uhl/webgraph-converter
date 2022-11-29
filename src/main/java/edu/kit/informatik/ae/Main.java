package edu.kit.informatik.ae;

import edu.kit.informatik.ae.writers.GraphWriter;
import edu.kit.informatik.ae.writers.EdgeListWriter;
import it.unimi.dsi.big.webgraph.BVGraph;
import it.unimi.dsi.big.webgraph.ImmutableGraph;
import it.unimi.dsi.io.OutputBitStream;
import it.unimi.dsi.logging.ProgressLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static it.unimi.dsi.big.webgraph.BVGraph.OFFSETS_EXTENSION;

@CommandLine.Command(name = "webgraph-converter", mixinStandardHelpOptions = true, description = "Convert graphs in the webgraph format to plain text edgelists.")
public class Main implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BVGraph.class);

    @CommandLine.Parameters(paramLabel = "<input basename>", description = "Basename of the input graph.")
    private String basename;

    @CommandLine.Option(names = {"-o", "--outfile"}, paramLabel = "The output filename.")
    private File outFile;

    @Override
    public void run() {
        final ProgressLogger pl = new ProgressLogger();
        var offset_file = new File(basename + OFFSETS_EXTENSION);
        if (!offset_file.exists()) {
            try {
                ImmutableGraph graph = ImmutableGraph.loadOffline(basename, pl);
                final OutputBitStream offsets = new OutputBitStream(graph.basename() + OFFSETS_EXTENSION, 64 * 1024);
                pl.expectedUpdates = graph.numNodes();
                pl.start("Writing offsets...");
                ((BVGraph)graph).writeOffsets(offsets, pl);
                offsets.close();
                pl.count = graph.numNodes();
                pl.done();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
        try {
            if (outFile == null) {
                outFile = new File(basename + ".edgelist");
            }
            pl.logger.info("Writing output to " + outFile);
            var outFileWriter = new FileWriter(outFile);
            GraphWriter writer = new EdgeListWriter(outFileWriter);


            BVGraph graph = BVGraph.loadMapped(basename, pl);

            pl.expectedUpdates = graph.numNodes();
            pl.start("Writing graph...");
            writer.startWriting(graph.numNodes(), graph.numArcs());
            var nodes = graph.nodeIterator();
            while (nodes.hasNext()) {
                var node = nodes.nextLong();
                writer.writeNewNode(node);
                var neighbors = graph.successors(node);
                var deg = graph.outdegree(node);
                for (long i = 0; i < deg; i++) {
                    var neighbor = neighbors.nextLong();
                        writer.writeEdge(node, neighbor);
                }
                pl.update();
            }
            writer.finishWriting();
            outFileWriter.close();
            pl.done();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
