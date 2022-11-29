package edu.kit.informatik.ae.writers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class EdgeListWriter implements GraphWriter {
    private final BufferedWriter writer;

    public EdgeListWriter(Writer outfile) throws IOException {
        writer = new BufferedWriter(outfile);
    }


    @Override
    public void startWriting(long numNodes, long numEdges) {
    }

    @Override
    public void writeNewNode(long nodeId) {
    }

    @Override
    public void writeEdge(long source, long target) throws IOException {
        String edgeString = String.format("%d\t%d\n", source, target);
        writer.write(edgeString);
    }

    @Override
    public void finishWriting() {
    }
}
