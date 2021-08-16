





<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Many organizations would like to unit-test bigquery on a local environment that may not have access to BigQuery apis. Since BigQuery does not have a local simulator, this becomes challenging

This project outlines an approach that can help you overcome this by mocking BigQuery calls. This is useful if 

* You are unit-testing a function where BigQuery results are getting used. 
* You don't intend to actually test your queries. 
* You have a limited set of queries and outputs that you are looking to test. 



### Built With
* [Mockito](https://site.mockito.org)


<!-- GETTING STARTED -->
## Getting Started

1. Import Maven Product
2. Modify and run RunSampleQuery.java
3. Modify and Run Junit test cases
4. Copy this approach to your project 

### Prerequisites

To run RunSampleQuery.java you need to have been authenticated via GoogleCloud CLI



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Ajay Prabhakar - [@hicoder](https://twitter.com/hicoder) - ajay@hicoder.net

