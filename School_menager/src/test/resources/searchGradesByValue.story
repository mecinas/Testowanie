Scenario: When the user sends a request to search for grades by value, api would respond json data containing grades

Given grades endpoint
And grades repository
And students repository
And courses repository
And grade value

When in repository there are grades with given value
Then api would respond json of these grades
