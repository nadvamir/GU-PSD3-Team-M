Scenario: student booking a timetable slot
Given a user is a student
When they book a slot
And the slot is valid and unique
And the student has no conflicts with other slots
And the slot is not full
Then student exists in database