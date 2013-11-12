require_relative 'db'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test'

utable = db.table( 'users' )

vasya = utable.find( 'name' => 'Vasya' ).one
if vasya
    vasya.set 'surname' => 'Petrov'
else
    utable.insert 'name' => 'Vasya'
end

vasya = utable.find( 'name' => 'Petya' ).one
if vasya
    vasya.set 'surname' => 'Sumkin'
else
    utable.insert 'name' => 'Petya'
end

unless utable.find( 'name' => 'Vasya', 'surname'=> 'Popov' ).one
    utable.insert 'name' => 'Vasya', 'surname' => 'Popov'
end

unless db.table( 'items' ).find( 'name' => 'ring' ).one
    db.table( 'items' ).insert 'name' => 'ring', 'belongs' => 'Popov'
end

puts db.tables( 'users', 'items' ).find( 'items.belongs' => 'users.surname' ).all.map { |r| r.to_hash }

utable.find( 'name' => 'Vasya', 'surname' => 'Popov' ).one.delete

users = db.table( 'users' ).all
users.map { |u| puts u.to_hash }

db.save
