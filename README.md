# cityhop

**cityhop** determines whether two given cities are connected by known roads. It provides a single RESTful endpoint that answers the query, "Can I get from city A to city B?"

It loads the roads from a local text file--which means, a "known road" is a road known to *it*. 

## Install and Launch (macOS and other UN*X-based systems)

Prerequisites:
 * Java 11

To install and launch the app, open a terminal and do the following:
 1. cd to dir in which you want to install the project (i.e., the project's parent dir).
 2. clone the github project via 
 1. cd to project home dir
 2. run <code>./gradlew bootRun</code>
 3. ALTERNATE: to read roadmap data from non-default location, do <code>./gradlew bootRun --args=myRoadMapData.txt</code>. Be sure to replace "myRoadMapData.txt" with the path to the file you want to load.
 
Note that logs get written to standard output.

### Usage

Send HTTP GET requests to <code>localhost</code> port <code>8080</code>, naming origin and destination cities, thus:

> http://localhost:8080/connected?origin=city1&destination=city2

If <code>city1</code> is connected to <code>city2</code> by any path along known roads, the response will be:

> yes

If not, or if the request is not formatted properly, the response will be:

> no

### Automated Tests 

Run JUnit tests from the command line via <code>./gradlew test</code>

### Design Choices

The data about the roads is held in class Roadmap, which is almost but not quite a digraph model: it models the cities as graph vertices (class Roadmap.City), but does not have an explicit model of the roads. Each city merely has a list of the names of other cities directly connected to it. This seemed to me to be the right tradeoff between simplicity and evolvability. If in future we want to model the roads explicitly--so we can give them properties such as distance, for example--it will be easy to do the upgrade.

Also note, I didn't bother with one-way roads, but made sure it will be straightforward to add support for them later.

It would be possible in theory to speed up runtime response by building a complete reachability matrix at startup time, in effect by doing all possible searches in advance and saving the results. That would have sped up runtime response at the expense of additional startup time and memory footprint. I chose not to do it that way because, in default of contextual information about where and how this service will be used, there's no real case for it.

I assumed the roadmap does not change while the app is running. To support reloading the roadmap without shutting down the app, a fair amount of additional code would be needed.

I separated the search functionality from the endpoint. Besides making the division of labor clearer, this makes it easy to replace the search implementation. Also note, the class that implements the search functionality ("ReachabilityCheck") is a hair's-breadth away from being a <code>Callable</code>, meaning it could be easily modified for use by an Executor service.

I chose to make this a Gradle project simply because in past I've used Gradle more than Maven.
 
### Future Improvements

 * As per the instructions, the endpoint responds to any unexpected input with "no". This does not distinguish the case where the query URL is syntactically incorrect from the cas where one or both of the cities is unknown. It the case of incorrectly formatted URL it would be better to respond with a useful error message.
 * It would be more informative to provide the path linking the origin and destination cities in addition to just "yes" or "no".


### Implementation Details 

This was written and tested on a Mac using Eclipse v.2020-03 with openjdk version "11.0.8" 2020-07-14. Skeleton project was created via [Spring Initializr|https://start.spring.io/]


