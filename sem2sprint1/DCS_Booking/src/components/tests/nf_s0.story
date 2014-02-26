Scenario: user authenticates through MyCampus
Given a user is a guest
When they choose to login
And they enter "admin" as a username
And they enter "giberish" as password
Then a request to MyCampus is sent
