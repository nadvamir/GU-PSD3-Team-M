require_relative 'db'
require 'date'

db = Db.new( 'localhost', 'root', '' )
db.select_db 'test2'

sess = db.table( 'sessions' )
attfile = File.new('BarcodeData.txt','r')

while line = attfile.gets
	stuff = line.split(',')
	#The barcode scanner uses an American date format, mm/dd/yy. This is stupid. We shall turn it into a REAL format.
	dateComps = stuff[3].split('/')
	dateComps[2] = dateComps[2].to_i+2000
	date = dateComps[1]+'/'+dateComps[0]+'/'+dateComps[2].to_s
	#It also uses a 12 hour format for time, which needs to go away
	time = DateTime.strptime(stuff[2], '%I:%M:%S %p')
	time = time.strftime('%H:%M:%S')
	theD = DateTime.parse(time+' '+date)
	#time to insert attendance into sessions!
	for s in sess.all
		if (theD>DateTime.parse(s.to_hash['start_date'].to_s) && theD<DateTime.parse(s.to_hash['end_date'].to_s))
			if (!s.to_hash['attended'].to_s.split(',').include? stuff[0])
				puts 'importing attendance'
				s.set 'attended' => s.to_hash['attended'].to_s+','+stuff[0]
			end
		end
	end
	
end
db.save
