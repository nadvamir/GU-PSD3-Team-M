require_relative 'db'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test2'

sess = db.table( 'sessions' )
attfile = File.new('attendance.csv','w')
puts sess.all
for s in sess.all
	shs = s.to_hash['attended'].to_s
	date = s.to_hash['date'].to_s
	for u in shs.split(',')
		attfile.syswrite(u+' '+date+"\n")
	end
end