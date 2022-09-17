# Charter Homework

### Prerequisites
- gradle 7.5.1 (to build the project)
- OpenJDK 15.0.2 (JDK version)

### Start the webserver
From the project root directory
- run `gradle bootJar` (to compile code)
- run `java -jar build/libs/reward-0.0.1-SNAPSHOT.jar` (to start webserver)
- run `curl -X POST -H "Content-Type: application/json" -d @./testData.json http://localhost:8080/reward` (to test with sample data file)