# VehicleApp
A simple Restful webservice that takes Post requests in the following format:
{
"vehicles": [
{
"VIN": "string",
"manufacturer": "string",
"bay_number": "integer"
},
{
"VIN": "string",
"manufacturer": "string",
"bay_number": "integer"
},
// etc...
]
}

performs some simple validation and writes the resulting Vehicle objects to a database.

Running the application and accessing the results:-
    - The only verb available to call the app is 'POST' and running locally application can be accessed 
        (via Postmand for example) at localhost:8080/vehicles

    - The database can be accessed while the applicatio is running at:-
        http://localhost:8080/h2-console
    
-   User name is sa (the h2 default), and no password has been set.


Validation criteria, as provided, are as follows:-
1) Possible manufacturers are Ford, GM, and VW.
2) VIN (Vehicle Identification Number) is the primary identifier of a vehicle.
3) VIN and manufacturer are required fields, bay_number is not.

Behavioural requirements are as follows:-
1) Only vehicles from Ford and GM are saved to the database. Vehicles from VW are ignored.
2) Vehicles with a bay_number of greater than 100 are ignored.
3) Vehicles with no bay_number present are saved. 

Assumptions and other information
1) I've used an h2 in memory database for convenience and portability.
    I was going to go with Apache Derby until the pom revealed a vulnerability I hadn't previous been aware of.
   While you wouldn't use either of these in production I generally find it's good practice not to ignore such warnings.
    It should be relatively trivial
2) Obviously, as bay_number is stored as an integer in the database it's automatically set to zero if it's not present.
3) I've allowed negative bay_numbers, though it would be simple to exclude them. Considering bay_number as location
    identifier rather than a count, it's not completely impossible that the value could be negative. 
4) I've performed only very simple validation on the incoming json. It will fail with an internal server error if the
    json isn't valid. A production grade app would obviously handle errors and exceptions more gracefully, and actually
    provide some logging, which I've not done here. 
5) A production grade app would also make more use of soft-code properties (not hard coding VW or bay numbers over 100
    as the only ignore-criteria, etc)