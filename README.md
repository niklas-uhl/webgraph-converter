# webgraph-converter

Helper tool for converting graphs from the [LAW Webgraph collection](https://law.di.unimi.it/datasets.php)
to plain text based edge list files.

## Building + Running
This tool is built using Gradle. You can build it without installing Gradle. From the project root run:
```console
# Build and install the executable scripts to the build directory
$ ./gradlew installShadowDist
# execute
$ build/install/webgraph-converter-shadow/bin/webgraph-converter
Usage: webgraph-converter [-hV] [-o=<outFile>] <basename>
Convert graphs in the webgraph format to plain text edgelists.
      <basename>            Basename of the input graph without file extension.
  -h, --help                Show this help message and exit.
  -o, --outfile=<outFile>   The output filename. Defaults to <basename>.edgelist
  -V, --version             Print version information and exit.
```

## Example
The following example shows how to download and convert a graph using the [uk-2007-05@100000](https://law.di.unimi.it/webdata/uk-2007-05@100000/) graph.

First, load the graph using
```console
$ ./download_law_graph.sh http://data.law.di.unimi.it/webdata/uk-2007-05@100000/uk-2007-05@100000
$ ls -l
-rw-r--r--  1 user  staff  775257 17 Nov  2010 uk-2007-05@100000.graph
-rw-r--r--  1 user  staff     931 24 Dez  2011 uk-2007-05@100000.md5sums
-rw-r--r--  1 user  staff     959 21 Dez  2010 uk-2007-05@100000.properties
```

Running the converter then generates an additional offset file (if not present) and writes the graph as a plain text edgelist to `uk-2007-05@100000.edgelist`.

```console
$ webgraph-converter uk-2007-05@100000
Writing offsets...
Completed.
Writing graph...
Completed.
...
$ ls -l
-rw-r--r--  1 user  staff  35782656 29 Nov 23:06 uk-2007-05@100000.edgelist
-rw-r--r--  1 user  staff    775257 17 Nov  2010 uk-2007-05@100000.graph
-rw-r--r--  1 user  staff       931 24 Dez  2011 uk-2007-05@100000.md5sums
-rw-r--r--  1 user  staff    129593 29 Nov 23:06 uk-2007-05@100000.offsets
-rw-r--r--  1 user  staff       959 21 Dez  2010 uk-2007-05@100000.properties
```
