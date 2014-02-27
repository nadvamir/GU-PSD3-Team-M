Scenario: admin adding a timetable slot to a session
Given a user is an admin
When they submit a new slot
And all fields are valid
And slot does not already exist
Then that slot exists in the database

Scenario: admin adding nonsense timetable slot to a course
Given a user is an admin
When they submit a new slot
And some slot field(s) is/are nonsense
Then an exception is thrown

Scenario: admin adding existing timetable slot to a course
Given a user is an admin
When they submit a new slot
And that slot already exists
Then an exception is thrown

Scenario: non-admin user adding a session to a course
Given a user is a student
When they submit a new slot
Then an exception is thrown