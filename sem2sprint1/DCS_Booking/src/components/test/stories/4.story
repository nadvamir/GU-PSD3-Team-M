Narrative:
In order don't have to create a large number of sessions
I want to specify that session is a one off, or recurs weekly 
or fortnightly

Scenario: Helen specify one off on session of course PSD3
Given a Helen who is lecturer
When course PSD3 is selected
And session from the course is selected
And the oneoff is selected
Then the session is oneoff

Scenario: Kritin specify weekly on session of course IS3
Given a Kritin who is lecturer
When course IS3 is selected
And session from the course is selected
And the weekly is selected
Then the session is weekly

Scenario: Coco specify fortnight on session of course OS3
Given a Coco who is lecturer
When course PSD3 is selected
And session from the course is selected
And the fortnight is selected
Then the session is fortnight
 