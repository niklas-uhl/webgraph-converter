package edu.kit.informatik.ae.writers;

import java.io.IOException;

public interface GraphWriter {
    void startWriting(long numNodes, long numEdges) throws IOException;
    void writeNewNode(long nodeId) throws IOException;

    void writeEdge(long source, long target) throws IOException;

    void finishWriting() throws IOException;
}

