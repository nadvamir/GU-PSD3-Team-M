Scenario: lecturer adding a session to a course
Given a lecturer
When they submit a new session
And all fields are valid
And the session does not already exist
Then that session exists in the database

Scenario: lecturer adding nonsense session to a course
Given a lecturer
When they submit a new session
And some session field(s) is/are nonsense
Then an exception is thrown

Scenario: lecturer adding existing session to a course
Given a lecturer
When they submit a new session
And that session already exists
Then an exception is thrown

Scenario: non-lecturer user adding a session to a course
Given a student
When they submit a new session
Then an exception is thrown