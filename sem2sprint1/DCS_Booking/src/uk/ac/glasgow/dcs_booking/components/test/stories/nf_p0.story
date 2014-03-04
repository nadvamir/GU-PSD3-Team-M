Scenario: 100 courses are added
Given a user is a lecturer
When he asks to import 100 random courses from MyCampus
Then 100 random courses exist in the database

Scenario: 1000 courses are added
Given a user is a lecturer
When he asks to import 1000 random courses from MyCampus
Then 1000 random courses exist in the database

