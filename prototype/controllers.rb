require_relative 'ui'
require_relative 'db'
require 'date'

def login( username, password )
  puts "I guess we'll trust you. For now."
end

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
  
  # Display labs
  for lab in labs.all
    puts lab.to_hash['id'].to_s
  end
  # Enter lab to update
  UI.line
  labchoice = UI.enter( "Enter the lab you wish to update" )
  labsessions = db.tables("lab_sessions", "labs")
  
  ## TESTING FIND FUNCTION ##
  #filteredlabsessions = labsessions.find('lab_sessions.lab_id' => 'labs.id')
  #for session in labsessions.all
  #  puts "ahhhh"
  #  if session.to_hash['labs.id'].to_s == labchoice
  #    puts "filtered   "+session.to_hash['lab_sessions.start_date'].to_s+"   ID: "+session.to_hash['lab_sessions.id'].to_s
  #  end
  #end
  
  # Display lab sessions for that lab
  for session in labsessions.all
    if (session.to_hash['lab_sessions.lab_id'].to_s == session.to_hash['labs.id'].to_s) && (session.to_hash['labs.id'].to_s == labchoice)
      puts session.to_hash['lab_sessions.start_date'].to_s+"   ID: "+session.to_hash['lab_sessions.id'].to_s
    end
  end
  # Enter session to update
  UI.line
  sessionchoice = UI.enter( "Enter the ID of the session you wish to update" )
  
  # Get list of students who have currently attended session
  # Get list of students enroleed in the session
  for session in labsessions.all
    if (session.to_hash['lab_sessions.lab_id'].to_s == session.to_hash['labs.id'].to_s) && (session.to_hash['lab_sessions.id'].to_s == sessionchoice)
      attendees = session.to_hash['lab_sessions.attended'].to_s
      enrolled = session.to_hash['labs.enrolled'].to_s
    end
  end
  
  # Display students who have attended session
  puts "Current attendees: "
  for attendee in attendees.split(',')
    puts attendee
  end
  # Display absent students
  puts "Current absentees: "
  for student in enrolled.split(',')
    puts student unless attendees.include?(student)
  end
  
  # Main recprding loop
  msg = "Attendance Recording\nChoose what you want to do:"
  options = {
      :a => "Add student to attended",
      :r => "Remove student from attended",
      :q => "Quit attendance recording"
  }
  begin
    sessions = db.tables('lab_sessions')
    UI.line
    option = UI.choose msg, options
    
    case option
    # Adding student to database
    # NOT WORKING
    when :a
      addstudent = UI.enter("Enter the ID of the student to add")
      for session in sessions.all
        puts "got here"
        if (session.to_hash['id'].to_s == sessionchoice)
          # THIS CODE IS NEVER REACHED?
          attended = session.to_hash['attended'].to_s+","+addstudent
          puts attended
          session.set({:attended => attended})
        end
      end
      puts "Adding student to attendance"
      
    # Removing student from database
    # NOT WORKING
    when :r
      remstudent = UI.enter("Enter the ID of the student to remove")
      for session in sessions.all
        puts "got here"
        if (session.to_hash['id'].to_s == sessionchoice)
          # THIS CODE IS NEVER REACHED?
          attended = ""
          # Iterate through students and create a new string, ommiting student to be removed
          for student in session.to_hash['attended'].to_s.split(',')
            if student != remstudent
              if attended == ""
                attended = student
              end
            else
              attended = attended+","+student
            end
          end
          puts attended
          session.set({:attended => attended})
        end
      end
      puts "Removing student from attendance"
    end
  end until  option == :q
  
end
