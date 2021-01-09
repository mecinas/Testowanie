Scenario: When the user sends a request to get student's average grade, api would respond with json of average grade of student with given id

Given average grade endpoint
And average grade repository of students
And average grade repository of courses
And average grade repository of grades
And average grade student id

When in repository student has grades
Then api would respond with json of average grade of student with given id

