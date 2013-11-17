PSD3 Prototype
=======================

Coded using Ruby 1.9.x

Run prototype.rb to use. Log in using any username and password combination. Follow on-screen instructions. Manual alteration of the provided prototype.json database file is not recommended.

Current database structure is based on 3 tables with certain fields each:

users: id, name, barcode

courses: id, name

labs: id, course_id (ID of the course this lab group is for), enrolled (students enrolled in this lab group)

sessions: id, lab_id (lab group this session is for), start_date, end_date, attended (user IDs of users that have attended the session)