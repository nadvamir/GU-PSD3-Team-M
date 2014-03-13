Scenario: admin assigns an available room to a timetable slot
Given a user is an admin
And a room is available
And a timetable slot exists
When assigns a room to a timetable slot
Then a room is assigned

Scenario: admin assigns an unavailable room to a timetable slot
Given a user is an admin
And a room is not available
And a timetable slot exists
When assigns a room to a timetable slot
Then a room is not assigned

Scenario: admin assigns an available room to a not existent timetable slot
Given a user is an admin
And a room is available
And a timetable slot does not exist
When assigns a room to a timetable slot
Then a room is not assigned

Scenario: non-admin assigns a room to timetable slot
Given a user is not an admin
And a room is available
And a timetable slot exists
When assigns a room to a timetable slot
Then a room is not assigned