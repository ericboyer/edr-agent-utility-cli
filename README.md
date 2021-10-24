# edr-agent-utility-cli Project

[![Quarkus Codestart CI](https://github.com/ericboyer/edr-agent-utility-cli/actions/workflows/ci.yml/badge.svg)](https://github.com/ericboyer/edr-agent-utility-cli/actions/workflows/ci.yml)

This project uses Quarkus, the Supersonic Subatomic Java Framework.

This application can be used to test an Endpoint Detection and Response (EDR) agent by triggering specific
activities. Telemetry for each activity is collected and logged for further analysis to ensure the EDR agents
are functioning properly.

## Overview
To satisfy the homework requirements, I created a CLI application based on a Java-based technology called 
[Quarkus](https://quarkus.io) using [picocli](http://picocli.info). I was only able to test the application on MacOS 
and Linux (Fedora 34) and additional testing is required for Windows.
 
The application logs all activities performed to `/tmp/edr-agent-utility-cli.log` by default, but logfile location 
can be modified by adding `-Dlogfile=<logfile>` to the java command as described below. The logs are CSV formatted.

## Command Line Interface
### NAME

Launches the EDR Agent utility CLI:

```
java [-Dlogfile=<logfile>] -jar edr-agent-utility-cli-1.0.0-SNAPSHOT-runner.jar [-h, --help] [-V, --version] <command> [<args>]
```

### SYNOPSIS

| command | args | description | 
| ---- | ---- | ----|
| create | -f,--file=\<file> | Creates a `file` of a specified type at a specified location |
| modify | -f,--file=\<file> | Modifies a `file` |
| delete | -f,--file=\<file> | Deletes a `file` |
| run | -e,--executable=\<executable> [\<args>...]| Runs a process given a path to the `executable` file and the desired (optional) command-line arguments |
| transmit | -h,--host=\<ip>  -p,--port=\<port>| Creates a network connection to `ip:port` and transmits data |


## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

> Note: App was tested with OpenJDK 11

It produces the `*-runner.jar` file in the `build/` directory. Be aware that it is an
_über-jar_ and all dependencies are packaged in this single executable jar. If you choose not to build yourself, it
can be downloaded from the latest [workflow](https://github.com/ericboyer/edr-agent-utility-cli/suites/4145263911/artifacts/106285846)

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`. 
