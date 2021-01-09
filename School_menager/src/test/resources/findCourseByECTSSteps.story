Scenario: When the user sends a request to search for students, api would respond json data containing student's details

Given find by ECTS endpoint
And find by ECTS repository of courses
And find by ECTS number of ECTS

When in repository there are courses with given number of ECTS
Then api would respond with json of courses with given ECTS

