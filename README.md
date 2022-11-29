# webgraph-converter

Helper tool for converting graphs from the [LAW Webgraph collection](https://law.di.unimi.it/datasets.php)
to plain text based edge list files.

## Building + Running
This tool is built using Maven. From the project root run:
```bash
# Build the .jar which includes all dependencies.
> mvn package
# execute
> java -jar target/webgraph-converter-0.1-jar-with-dependencies.jar
Usage: webgraph-converter [-hV] [-o=The output filename.] <input basename>
Convert graphs in the webgraph format to plain text edgelists.
      <input basename>   Basename of the input graph.
  -h, --help             Show this help message and exit.
  -o, --outfile=The output filename.

  -V, --version          Print version information and exit.
```