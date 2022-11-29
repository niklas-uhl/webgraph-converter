# webgraph-converter

Helper tool for converting graphs from the [LAW Webgraph collection](https://law.di.unimi.it/datasets.php)
to plain text based edge list files.

## Building + Running
This tool is built using Gradle. You can build it without installing Gradle. From the project root run:
```bash
# Build and install the executable scripts to the build directory
> ./gradlew installShadowDist
# execute
> build/install/webgraph-converter-shadow/bin/webgraph-converter
Usage: webgraph-converter [-hV] [-o=The output filename.] <input basename>
Convert graphs in the webgraph format to plain text edgelists.
      <input basename>   Basename of the input graph.
  -h, --help             Show this help message and exit.
  -o, --outfile=The output filename.

  -V, --version          Print version information and exit.
```
