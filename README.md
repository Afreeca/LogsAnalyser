# LogsAnalyser
A console application that reads from the logs file and flag any logs based in a pre define condition

## Requirements:
* CommandLine or Eclipse/STS

## Running the application from the command line using Maven
1. In the base directory (containing pom.xml)
* mvn clean install
* mvn exec:java

#### Running just the application test

1. In the base directory (containing pom.xml)
* mvn test

#### Running application without tests (in case tests are failing)

1. In the base directory (containing pom.xml)
* mvn -Dmaven.test.skip=true install

#### Extra instructions
At the begin the program will required the path of the log file, if you don't want to specify the path
the application will use the default file in the (log-events/logs.text) whitch contains some logs entries
