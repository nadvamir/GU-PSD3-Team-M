Scenario: careful student checks if he signed up for all compulsory courses
Given a user is a student
And PSD3 is compulsory
And DIM3 is compulsory
And a user signed up for PSD3
And a user signed up for DIM3
When he asks if he has signed up for all compulsory courses
Then a positive answer is given

Scenario: careless student checks if he signed up for all compulsory courses
Given a user is a student
And PSD3 is compulsory
And DIM3 is compulsory
And a user signed up for PSD3
When he asks if he has signed up for all compulsory courses
Then a negative answer is given

Scenario: a non-user tries to check his compulsory courses
Given a user is a tutor
And PSD3 is compulsory
And DIM3 is compulsory
And a user signed up for PSD3
When he asks if he has signed up for all compulsory courses
Then an exception is thrown

