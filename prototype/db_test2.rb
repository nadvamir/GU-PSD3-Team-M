require_relative 'db'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test2'
require 'date'

utable = db.table( 'users' )
ctable = db.table( 'courses' )
ltable = db.table( 'labs' )
stable = db.table( 'lab_sessions' )

for i in 0..4
currentUser = "user#{i}"
	unless utable.find( 'name' => currentUser ).one
		utable.insert 'name' => currentUser, 'id' => "#{i}#{i}#{i}#{i}#{i}#{i}#{i}"
	end
end

course = 'course1'
course_id = 1384469955679254
unless ctable.find( 'name' => course ).one
	ctable.insert 'name' => course, 'id' => course_id
end

for i in 0..4
  ltable.insert 'course_id' => course_id
end

session1s = DateTime.parse('11:00:00 31/10/2013')
session1e = DateTime.parse('12:00:00 31/10/2013')
session2s = DateTime.parse('12:00:00 31/10/2013')
session2e = DateTime.parse('13:00:00 31/10/2013')
session3s = DateTime.parse('13:00:00 31/10/2013')
session3e = DateTime.parse('14:00:00 31/10/2013')
session4s = DateTime.parse('14:00:00 31/10/2013')
session4e = DateTime.parse('15:00:00 31/10/2013')
unless stable.find( 'start_date' => session1s.to_s ).one
	stable.insert 'start_date' => session1s.to_s, 'end_date' => session1e.to_s, 'attended' => '0000000'
end
unless stable.find( 'start_date' => session2s.to_s ).one
	stable.insert 'start_date' => session2s.to_s, 'end_date' => session2e.to_s, 'attended' => '2222222'
end
unless stable.find( 'start_date' => session3s.to_s ).one
	stable.insert 'start_date' => session3s.to_s, 'end_date' => session3e.to_s, 'attended' => '4444444'
end
unless stable.find( 'start_date' => session4s.to_s ).one
	stable.insert 'start_date' => session4s.to_s, 'end_date' => session4e.to_s, 'attended' => '4444444'
end

db.save