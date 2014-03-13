Narrative:
In order for students to be able to complete the course
As a lecturer
I want to check that there are no timetable slot clashes between courses

Scenario: administrator checks clashes
Given a user is a administrator
And there are 2 clashes
When they check for clashes
Then they see 2 clashes

Scenario: administrator checks clashes
Given a user is a administrator
And there are 1 clashes
When they check for clashes
Then they see 1 clashes

Scenario: administrator checks clashes
Given a user is a administrator
And there are 0 clashes
When they check for clashes
Then they see 0 clashes

Scenario: non-adminisrator checks classes
Given a user is a user
And there are 0 clashes
When they check for clashes
Then an exception is thrown
