Narrative:
In order to populate a course
As a lecturer
I want to add a session to a course

Scenario: lecturer adding a session to a course
Given a lecturer
When they create a new session
And the session is valid and unique
And the lecturer submits the session
Then that session exists in the database

Scenario: lecturer adding nonsense session to a course
Given a lecturer
When they create a new session
And the session is nonsense
And the lecturer submits the session
Then an exception is thrown

Scenario: lecturer adding existing session to a course
Given a lecturer
When they create a new session
And that session already exists
And the lecturer submits the session
Then an exception is thrown

Scenario: non-lecturer user adding a session to a course
Given a student
When they create a new session
And the session is valid and unique
And the student submits the session
Then an exception is thrown