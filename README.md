# MiniUrl
A Simple URL Shortening service

## Assumptions:
No user login needed for the application.
The final length of the shortened url should be 6 chars  



## DB Schema
url_entry
id : String
longUrl : String



## Choice of Data Storage:
No SQL database (Embedded Mongo DB used here for demo to skip additional setup)

## Reasons for the choice of a NoSQL database:
- Data is not relational in nature
- NoSQL DB would ease storing billions of records as number of urls grow ensuring high read performance (as the app is read intensive) with growing scale
- Out of box horizontal scalability
- Individual record size is very less.



## Design Analysis:
- The number of unique urls possible is 62^6 ~ 56 Billion
-  This number can further be increased if no of characters in encoded short url is increased (set to 6 presently)
- The final length of encoded short url is independent of input url length


## Running instructions:
- Download and install STS editor from https://spring.io/tools
- Import this project into the workspace
- To run automated test cases , right click the project and run as maven tests.
- Right click and run the project as a Spring Boot application and try the following end-points:

http://localhost:8080/generate-url : generates a short url for a given
long url.
Sample request body: {"longUrl":"https://www.khanacademy.org/math/calculus-all-old/integration-calc/indefinite-integrals-of-common-functions-calc/v/basic-trig-and-exponential-antiderivatives?modal=1"}
Sample output : {"shortUrl":"http://localhost:8080/avw1v5"}

- Now once the above returned short url is entered in the browser , the request gets routed to the original long url.


## Future scope:
- Authentication for create url end point
- API Rate limiting on basis of developer api key in create url end point
to limit url creation based on a quota set per developer. This will also prevent repeated bot requests
- Having an expiry value associated with each url .This will help to remove unused urls and free up data storage .
- Caching of most frequently used urls to further speed up read requests
