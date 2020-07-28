# cityhop

Demo RESTful service using Spring Boot

**cityhop** determines whether two given cities are connected by known roads. It provides a single RESTful endpoint that answers the query, "Can I get from city A to city B?"

It loads the roads from a local text file--which means: a "known road" is a road found in the file. 

## Install and Run (macOS, Linux)

Prerequisite: Java 11

To install and launch the app, open a terminal and do the following:

1. cd to the dir in which you want to install the project (i.e., the project's parent dir).
2. clone the github project via  
   `git clone https://github.com/jehanson4/cityhop.git`
3. cd to the project home dir
4. Launch the app via  
   `./gradlew bootRun`  
   This loads the default file `city.txt`
5. ALTERNATE: to read roadmap data from non-default location, do  
	`./gradlew bootRun --args=myRoadMapData.txt`  
	Be sure to replace "myRoadMapData.txt" with the path to the file you want to load.
 
Logs get written to standard output. Overrides to default log levels are set in `src/main/resources/application.properties`

JUnit tests may be run from the command line via `./gradlew test`

### Usage

Send HTTP GET requests to `localhost` port `8080`, naming origin and destination cities, thus:

> http://localhost:8080/connected?origin=city1&destination=city2

If `city1` is connected to `city2` by any path along known roads, the response will be:

> yes

If not, or if the request is not formatted properly, the response will be:

> no

### Design Choices

The data about the roads is held in class `Roadmap`, which is almost but not quite a directed-graph model: it models the cities as graph vertices (class `Roadmap.City`), but does not have an explicit model of the roads. Each city merely has a list of other cities directly connected to it. This seemed to me to be the right tradeoff between simplicity and evolvability. If in future we want to model the roads explicitly--so we can give them properties such as distance, or distinguish travel in the two directions, for example--it will be easy to do the upgrade.

It would be possible in theory to speed up runtime response by building a complete reachability matrix at startup time, in effect by doing all possible searches in advance and saving the results. That would incurred  additional startup time and memory footprint. I chose not to do it because, without information about where and how this service will be used, there's no real case for it.
 
### Future Improvements

At present the endpoint responds to any unexpected input with "no". This does not distinguish the case where the query URL is syntactically incorrect from the case where one or both of the cities is unknown. It the case of a syntactically incorrect URL it would be more user-friendly to respond with a useful error message.
 
It would be more informative to provide the path linking the origin and destination cities in addition to just "yes". This is a straightforward enhancement.


### Implementation Details 

cityhop was written and tested on a Mac with openjdk 11.0.8. The project skeleton was created via [Spring Initializr](https://start.spring.io/) on 27 Jul 2020.


