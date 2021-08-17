
# BigQuery Unit Testing for Isolated Environment

<!-- ABOUT THE PROJECT -->
## About The Project

Making BigQuery unit tests work when your local/isolated environment cannot connect to BigQuery APIs is challenging. This project demonstrates how you can stub/mock your BigQuery responses for unit test cases.

The scenario for which this solution will work:
* You need to unit test a function which calls on BigQuery (SQL,DDL,DML)
* You don’t actually want to run the Query/DDL/DML command, but just work off the results
* You want to run several such commands, and want the output to match BigQuery output format

## The idea in a nutshell:
* Store BigQuery results as Serialized Strings in a local file, where the query (md5 hashed) is the key. (see RunSampleQuery.java and query.properties)
* In your unit test cases, mock BigQuery results to retrieve the previously serialized version of the Query output (see BigqueryTesting.java)

Reach out to me for questions or suggestions. 
 

### Built With
* [Mockito](https://site.mockito.org)


<!-- GETTING STARTED -->
## Getting Started

1. Modify and run RunSampleQuery.java
2. Modify and Run Junit test cases
3. Copy this approach to your project 

### Prerequisites

To run RunSampleQuery.java you need to have been authenticated via GoogleCloud CLI. RunSampleQuery.java cannot be run in an isolated environment but the Unit Tests can run without network connectivity.

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Ajay Prabhakar - [@hicoder](https://twitter.com/hicoder) - ajay@hicoder.net

