require_relative 'ui'
require_relative 'db'
require 'date'
require 'set'

def login( username, password )
  puts "I guess we'll trust you. For now."
end

def export_course_data( db )
    puts "Exporting the course data for all students for a chosen course"
	puts "Available courses: "
	courses = db.table( 'courses' )
	labs = db.table( 'labs' )
	users = db.table( 'users' )
	sess = db.table( 'lab_sessions' )
	
	for c in courses.all
		puts c.to_hash['name'].to_s
	end
	course = UI.enter( "Enter course to export data for" )
	for c in courses.all
		if (c.to_hash['name'].to_s == course)
			courseID = c.to_hash['id'].to_s
		end
	end
	filename = UI.enter( "Enter the name of the file to be exported" )
	exfile = File.new(filename,'w')
	#get students enrolled on course by seeing which students are enrolled on the courses' labs
	ens = Set.new

	for l in labs.all
		if (l.to_hash['course_id'].to_s == courseID) 
			for en in l.to_hash['enrolled'].to_s.split(',')
				ens.add(en)
			end
		end
	end
	#start exporting them and their attendance
	for stu in ens
		for u in users.all
			if (u.to_hash['id'].to_s == stu) 
				sname = u.to_hash['name'].to_s
			end
		end
		enin = Array.new
		for l in labs.all
			if (l.to_hash['enrolled'].to_s.split(',').include? stu) 
				enin.push(l.to_hash['id'])
			end
		end
		exfile.syswrite(sname+','+stu)
		for s in sess.all
			if (enin.include? s.to_hash['lab_id'].to_s)
				if (s.to_hash['attended'].to_s.split(',').include? stu)
					exfile.syswrite(',present')
				else
					exfile.syswrite(',absent')
				end
			end
		end
		exfile.syswrite("\n")
	end
end

def export_student_data( db )
	puts "Exporting the student data for all courses"

	puts "Available students: "
	courses = db.table( 'courses' )
	labs = db.table( 'labs' )
	users = db.table( 'users' )
	sess = db.table( 'lab_sessions' )
	
	for s in users.all
		puts s.to_hash['name'].to_s
	end
	student = UI.enter( "Enter student to export data for" )
	for s in users.all
		if (s.to_hash['name'].to_s == student)
			studentID = s.to_hash['id'].to_s
		end
	end
	filename = UI.enter( "Enter the name of the file to be exported (preferably including the student name or ID)" )
	exfile = File.new(filename,'w')
	
	#get courses and labs student is enrolled on
	cenin = Set.new
	lenin = Set.new

	for l in labs.all
		if (l.to_hash['enrolled'].to_s.split(',').include? studentID)
			cenin.add(l.to_hash['course_id'].to_s)
			lenin.add(l.to_hash['id'].to_s)
		end
	end
	
	#export
	for c in cenin
		for co in courses.all
			if (co.to_hash['id'].to_s == c) 
				cname = co.to_hash['name'].to_s
				for labid in lenin
					exfile.syswrite(cname+','+c)
					for lab in labs.all
						if (lab.to_hash['course_id'].to_s == c && lab.to_hash['id'].to_s == labid)
							for s in sess.all
								if (s.to_hash['lab_id'].to_s==labid)
									exfile.syswrite(','+labid)
									if (s.to_hash['attended'].to_s.split(',').include? studentID)
										exfile.syswrite(',present')
									else
										exfile.syswrite(',absent')
									end
								end
							end
						end
					end
					exfile.syswrite("\n")
				end
			end
		end
		
	end
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

def export_stuff( db )
  # course or student?
  msg = "Export all information for a course? (No will export all information for a student)"
  
  # Handle choice
  UI.line
  if UI.confirm(msg)
      export_course_data( db )
  else
      export_student_data( db )
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
  # Get list of students enrolled in the session
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
    sessions = db.table('lab_sessions')
    UI.line
    option = UI.choose msg, options
    
    case option
    # Adding student to database
    when :a
      addstudent = UI.enter("Enter the ID of the student to add")
      for session in sessions.all
        if (session.to_hash['id'].to_s == sessionchoice)
          attended = session.to_hash['attended'].to_s+","+addstudent
          puts attended
          session.set 'attended' => attended
        end
      end
      puts "Adding student to attendance"
      
    # Removing student from database
    when :r
      remstudent = UI.enter("Enter the ID of the student to remove")
      for session in sessions.all
        if (session.to_hash['id'].to_s == sessionchoice)
          attended = ""
          # Create array of students, remove the element representing the student that must be removed, recreate string from remaining elements
          attar = session.to_hash['attended'].to_s.split(',')
		  attar.delete(remstudent)
		  for s in attar
			attended = attended+s+','
		  end
		  attended = attended.chomp(',')
          puts attended
          session.set 'attended' => attended
        end
      end
      puts "Removing student from attendance"
    end
  end until  option == :q
  
end
