Scenario: student authenticates
Given a user is a guest
When they choose to login
And they enter "student" as a username
And they enter "giberish" as a password
Then a user is recognised as a student

Scenario: tutor authenticates
Given a user is a guest
When they choose to login
And they enter "tutor" as a username
And they enter "giberish" as a password
Then a user is recognised as a tutor

Scenario: lecturer authenticates
Given a user is a guest
When they choose to login
And they enter "lecturer" as a username
And they enter "giberish" as a password
Then a user is recognised as a lecturer

Scenario: admin authenticates
Given a user is a guest
When they choose to login
And they enter "admin" as a username
And they enter "giberish" as a password
Then a user is recognised as a admin
