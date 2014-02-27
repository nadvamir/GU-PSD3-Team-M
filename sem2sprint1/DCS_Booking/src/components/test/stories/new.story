Narrative:
In order to book rooms and allow students to sign up
As a admin
I want to add a timetable slot to a session

Scenario: admin adding a timetable slot to a session
Given a user is a admin
When they create a new slot
And the slot is valid and unique
And the admin submits the new slot to be in session VALID
Then that slot exists in session VALID the database

Scenario: admin adding a timetable slot to a session
Given a user is a admin
When they create a new slot
And the slot is valid and unique
And the admin submits the new slot to be in session NONSENSE
Then an exception is thrown

Scenario: admin adding nonsense timetable slot to a course
Given a user is a admin
When they create a new slot
And the slot is nonsense
And the admin submits the new slot to be in session VALID
Then an exception is thrown

Scenario: admin adding existing timetable slot to a course
Given a user is a admin
When they create a new slot
And that slot already exists in session VALID
And the admin submits the new slot to be in session VALID
Then an exception is thrown

Scenario: non-admin user adding a session to a course
Given a user is a student
When they create a new slot
And the slot is valid and unique
And the student submits the new slot to be in session VALID
Then an exception is thrown