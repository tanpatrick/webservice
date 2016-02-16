# webservice

Assumption: Maven has already been installed/configured.

How to use via cURL: 
1) After checking out/cloning the project, navigate inside the webservice folder.
2) Open command line then execute maven command: mvn spring-boot:run
3) Once you've already see the 'Started Application in 18.853 seconds (JVM running for 29.852)' from the command line,
it means that the application has been successfully started.
4) cURL http://localhost:8080, the response should be similar to the one shown below

{
  "_links": {
    "owner": {
      "href": "http://localhost:8080/owner"
    },
    "company": {
      "href": "http://localhost:8080/company"
    },
    "profile": {
      "href": "http://localhost:8080/profile"
    }
  }
}

5) Now, to add a company on the command line type
  curl -H "Content-Type: application/json" -X POST -d '{"name":"xyz","address":"xyz","city":"xyz","country":"xyz"}' http://localhost:8080/company
  
  Response should be similar to the one shown below
  
  {
    "id" : 1,
    "name" : "xzy",
    "address" : "xyz",
    "city" : "xyz",
    "country" : "xyz",
    "email" : null,
    "phone" : null,
    "_links" : {
        "self" : {
          "href" : "http://localhost:8080/company/1"
        },
        "company" : {
          "href" : "http://localhost:8080/company/1"
        },
        "owners" : {
          "href" : "http://localhost:8080/company/1/owners"
        }
    }
  }
  
6) To add an owner to a company, 
  curl -H "Content-Type: application/json" -X POST -d '{"name":"abc","company":"http://localhost:8080/company/1"}' http://localhost:8080/owner
  
  where, company value is the url of the company
  
NOTE: This application uses HATEOAS which simplifies the navigation since all necessary links are provided on the web service response/s.

------------------------
How to use via UI: 

1) Assuming that the app is already running, open the browser then type http://localhost:8080/ui
2) To add new company, click the 'ADD NEW COMPANY' button
3) Provide the company details then click 'Create' button
  In case no name, for example, has been entered the application will show an error message 'may not be empty' (color red).
4) To add an owner, go back to the previous screen or type http://localhost:8080/ui to the browser address bar
5) Enter the owner name then click 'Add' button
6) To check if the owner has been successfully added/linked to the company, go back to the previous screen 
or type http://localhost:8080/ui to the browser address bar
7) To edit an existing company, from the main page, click view link.

NOTE: This uses an in-memory database for testing purposes
