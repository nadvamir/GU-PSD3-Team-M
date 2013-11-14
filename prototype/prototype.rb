require_relative 'db'
require_relative 'ui'
require_relative 'controllers'

# main file, use as an application entry point

# we will need a database
db = Db.new( 'localhost', 'root', '' )
db.select_db 'prototype'

# intro text
UI.line
puts "The Prototype. (c) Team M, 2013"

# main loop
msg = "Choose what you want to do:"
options = {
    :e => "Export course data to CSV",
    :a => "record Attendance",
    :q => "Quit the application"
}
# yay for the oldschool
begin
    UI.line
    option = UI.choose msg, options
    case option
    when :e
        export_course_data
    when :a
        record_attendance
    end
end until option == :q

if UI.confirm "Exit?"
    UI.line
    puts "Thank you for using this raw thingy!"
    UI.line
else
    puts "Too late!"
end

# saving the work after an execution has finished
db.save
