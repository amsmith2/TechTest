# TechTest

## Part 1
### Pre-Requisites
You will need Maven installed to package and run this project.
### Running Part 1
Part 1 is run from the master branch.
In command line, within the TechTest folder, enter the following:
```
mvn clean package
java -jar target/TechTest-1.0-SNAPSHOT.jar
```

## Part 2
### Pre-Requisites
You will need Maven installed to package and run this project.
### Running Part 2
Part 2 is run from the part2 branch.
In command line, within the TechTest folder, enter the following:
```
git checkout part2
mvn clean package
java -jar target/gs-serving-web-content-0.1.0.jar
```
Then just head to http://localhost:8080/ !
