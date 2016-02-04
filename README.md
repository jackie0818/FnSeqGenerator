# FnSeqGenerator

Profile:
  This project is web service providing a GET method to get the Fibonacci sequence of designated item number.
Prerequisite to build/run:
 1. JDK 1.8
 2. maven 3.2

Steps to build:
 1. cd FnSeqGenerator
 2. mvn clean package
 A war file named FnSeqGenerator-1.0-SNAPSHOT.war comes out in target directoty.

Steps to run unit test:
  1.  cd FnSeqGenerator
  2.  mvn test
  Expected output:
  Results :
  Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

Steps to run functional test:
  1. cd FnSeqGenerator
  2. mvn verify        -- here also run unit test
  Expected output for funtional test:
  Results :
  Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

Steps to run in jetty:
  1. cd FnSeqGenerator
  2. mvn jetty:run-war
  After jetty starts up, you may access http://localhost:8080/fn/out/{n} to get required fibonacci sequence via browser, here {n} should be an integer more than 0, meaning the numbers of items in returned fiboncci sequence;
