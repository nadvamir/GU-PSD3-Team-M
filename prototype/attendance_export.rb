require_relative 'db'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test2'

cTables = 
sess = db.tables( 'sessions' )
for s in sess.all
puts s.to_hash
end