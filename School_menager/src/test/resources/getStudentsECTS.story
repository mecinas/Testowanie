Scenario: When the user sends a request to retrieve student's ECTS points, api would respond with json data containing student's ECTS value

Given ects endpoint
And ects repository of students
And ects repository of grades
And ects repository of courses
And ects student's id

When student is assigned to some courses

Then api would respond with json containing student's ECTS

