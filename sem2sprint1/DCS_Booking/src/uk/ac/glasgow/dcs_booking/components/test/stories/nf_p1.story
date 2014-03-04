Scenario: 1 session is added to a course
Given a user is a lecturer
When they create 1 sessions for "PSD3"
Then "PSD3" has 1 different sessions associated with it

Scenario: 12 different sessions added to a course
Given a user is a lecturer
When they create 10 sessions for "PSD3"
Then "PSD3" has 10 different sessions associated with it

