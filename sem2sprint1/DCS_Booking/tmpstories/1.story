Scenario: lecturer importing an exising my campus course
Given a user is a lecturer
When he asks to import PSD3 course from MyCampus
Then PSD3 course exists in the database

Scenario: non-lecturing individual importing a course
Given a user is a student
When he asks to import PSD3 course from MyCampus
Then an exception is thrown

Scenario: lecturer importing an inexisten my campus course
Given a user is a lecturer
When he asks to import RTFM5 course from MyCampus
Then an exception is thrown

