Scenario: the system supports a timetable slot for a session
Given a user is a admin
When they create 1 different slots for a session VALID
Then there are 1 different slots for a session VALID

Scenario: the system supports 20 different timetable slots per session
Given a user is a admin
When they create 20 different slots for a session VALID
Then there are 20 different slots for a session VALID

