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
