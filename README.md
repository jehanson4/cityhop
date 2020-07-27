# cityhop
Demo RESTful service using Spring Boot

**cityhop** determines whether two given cities are connected by known roads. It provides a single endpoint that answers the query, "Can I get from city A to city B?"

It loads its graph of cities and roads from a local file--i.e., a "known" road is a road known to *it*. 

### Install and Launch

### Usage

### Design Choices

 * not-quite-digraph: no need at present to model the roads, but it will be easier to upgrade if in future we want to add properties to them (such as distance).
 * separate the work from the endpoint.
 * did not separate the loading from the roadmap
 * chose gradle b/c more familiar to me than maven
 
### Implementation Notes 

 
### Suggested Improvements

 * As per the instructions, the endpoint responds to any unexpected input with "no". This does not distinguish the case where the query URL is syntactically incorrect from the cas where one or both of the cities is unknown. It the case of incorrectly formatted URL it would be better to respond with a useful error message.



