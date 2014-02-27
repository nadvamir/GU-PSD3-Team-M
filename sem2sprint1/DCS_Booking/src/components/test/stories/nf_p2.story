Scenario: 1000 different users can exist
Given a database contains 1000 dummy users
When they choose to login
And they enter "student" as a username
And they enter "giberish" as password
Then a user is recognised as a student

Scenario: 2000 different users can exist
Given a database contains 2000 dummy users
When they choose to login
And they enter "student" as a username
And they enter "giberish" as password
Then a user is recognised as a student

