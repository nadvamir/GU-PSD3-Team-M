Scenario: admin assigns an available room to a timetable slot
Given a user is a admin
When he assigns a room to a timetable slot
When a room is available
Then a room is assigned

Scenario: admin assigns an unavailable room to a timetable slot
Given a user is a admin
When he assigns a room to a timetable slot
When a room is not available
Then a room is not assigned

Scenario: non-admin assigns a room to timetable slot
When he assigns a room to a timetable slot
Then a room is not assigned