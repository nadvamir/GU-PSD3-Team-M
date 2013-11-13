require_relative 'db'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test2'

utable = db.table( 'users' )
ctable = db.table( 'courses' )
stable = db.table( 'sessions' )

for i in 0..10
currentUser = "user#{i}"
	unless utable.find( 'name' => currentUser ).one
		utable.insert 'name' => currentUser
	end
end

course = 'course1'
unless ctable.find( 'name' => course ).one
	ctable.insert 'name' => course
end

session = '13/11/2013'
unless stable.find( 'date' => session ).one
	stable.insert 'date' => session
	stable.insert 'course' => course
	stable.insert 'attended' => 'user1,user5,user7'
end

db.save