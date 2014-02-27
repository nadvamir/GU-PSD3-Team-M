Narrative:
In order to populate a course
As a lecturer
I want to add a session to a course

Scenario: lecturer adding a session to a course
Given a user is a lecturer
When they create a new session
And the session is valid and unique
And the lecturer submits the session to be in course VALID
Then the session exists in course VALID in the database

Scenario: lecturer adding a session to a course
Given a user is a lecturer
When they create a new session
And the session is valid and unique
And the lecturer submits the session to be in course NONSENSE
Then an exception is thrown

Scenario: lecturer adding nonsense session to a course
Given a user is a lecturer
When they create a new session
And the session is nonsense
And the lecturer submits the session to be in course VALID
Then an exception is thrown

Scenario: lecturer adding existing session to a course
Given a user is a lecturer
When they create a new session
And the session already exists in course VALID
And the lecturer submits the session to be in course VALID
Then an exception is thrown

Scenario: non-lecturer user adding a session to a course
Given a user is a student
When they create a new session
And the session is valid and unique
And the student submits the session to be in course VALID
Then an exception is thrown