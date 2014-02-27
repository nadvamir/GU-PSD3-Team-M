Scenario: the system supports a timetable slot for a session
Given a user is a admin
When they create 1 different slots for a "PSD3" course session
Then there are 1 differen slos for a "PSD3" course session

Scenario: the system supports 20 different timetable slots per session
Given a user is a admin
When they create 20 different slots for a "PSD3" course session
Then there are 20 differen slos for a "PSD3" course session

