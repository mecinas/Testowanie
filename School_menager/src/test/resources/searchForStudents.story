Scenario: When the user sends a request to search for students, api would respond json data containing student's details

Given endpoint
And repository of students
And search phrase

When in repository there are students whose names start with search phrase
Then api would respond json of these students

