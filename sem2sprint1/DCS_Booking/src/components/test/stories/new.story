Narrative:
In order to book rooms and allow students to sign up
As a admin
I want to add a timetable slot to a session

Scenario: admin adding a timetable slot to a session
Given an admin
When they create a new slot
And the slot is valid and unique
And the admin submits the new slot
Then that slot exists in the database

Scenario: admin adding nonsense timetable slot to a course
Given an admin
When they create a new slot
And the slot is nonsense
And the admin submits the new slot
Then an exception is thrown

Scenario: admin adding existing timetable slot to a course
Given an admin
When they create a new slot
And that slot already exists
And the admin submits the new slot
Then an exception is thrown

Scenario: non-admin user adding a session to a course
Given a student
When they create a new slot
And the slot is valid and unique
And the student submits the new slot
Then an exception is thrown