require_relative 'ui'
require_relative 'db'
require 'date'

def export_course_data
    puts "Exporting the course data"
end

def record_attendance( db )
  # csv or manual
  msg = "Import attendance from a barcode reader file?"
  
  # Handle choice
  UI.line
  if UI.confirm(msg)
      record_attendance_barcode( db )
  else
      record_attendance_manual( db )
    end
end

# Handle csv import for attendance
def record_attendance_barcode( db )
  puts "Recording attendance from barcode file"
  
  filename = UI.enter( "Enter the name of your file" )
  attfile = File.new(filename,'r')
  
  sess = db.table( 'lab_sessions' )

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
  db.save   # Needed?
  
end

# Handle manual attendance alterations
def record_attendance_manual( db )
  labs = db.table("labs")
  for lab in labs.all
    puts lab.to_hash['id'].to_s
  end
  
  labchoice = UI.enter( "Enter the lab you wish to update" )
  labsessions = db.tables("lab_sessions", "labs")
  for session in labsessions.all
    # THIS DOESN'T WORK
    if (session.to_hash['lab_sessions.lab_id'].to_s == session.to_hash['labs.id'].to_s) && (session.to_hash['labs.id'].to_s == labchoice)
      puts session.to_hash['start_date'].to_s+"   ID: "+session.to_hash['id']
    end
  end
  
  sessionschoice = UI.enter( "Enter the ID of the session you wish to update" )
  sessions = db.tables("lab_sessions")
  
  puts "Recording attendance manually"
end
