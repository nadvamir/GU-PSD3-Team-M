Scenario: 100 different concurent users can work
Given a database contains 1000 dummy users
When 100 random users login to the system
Then a user nr 1 is recognised as a student
And a user nr 100 is recognised as a student

Scenario: 120 different concurent users can work
Given a database contains 1000 dummy users
When 120 random users login to the system
Then a user nr 9 is recognised as a student
And a user nr 111 is recognised as a student
