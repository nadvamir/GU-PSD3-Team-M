require 'io/console'

class UI
    class << self
        # choose one of the options
        # options have symbol characters as keys
        # the chosen symbol is returned
        def choose( msg, options )
            puts msg
            options.each { |k, v| printf "%3s: %s\n", k, v }
            print "Enter your choice: "

            # no enter pressing required
            begin
                cmd = STDIN.getch.downcase.to_sym
            end until options.has_key? cmd

            puts; line
            cmd
        end

        # returns true if the user enters Y, false otherwise
        # requires enter, just in case
        def confirm( msg )
            print msg + " Y/n: "
            confirmed = gets.downcase.strip == "y"
            confirmed
        end

        # returns a user typed string
        def enter( msg )
            print msg + ": "
            gets.strip
        end

        # a visual separator of sorts. I like lines.
        def line
            puts "-------------------------------------------------"
        end
    end
end
