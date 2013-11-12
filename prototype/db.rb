require_relative 'table'
require 'pathname'
require 'json'

# a database abstraction, providing interface to the actual database.
# for the prototyping purpose, the database is a file, storing json
# representation of a big big hash.
# technically, one could later replace methods of this and the related
# classes to work with real world databases, without modyfing the 
# actual application logic.
class Db
    # initialization stub, we don't care about those
    def initialize( server, username, password )
        @db = {}
    end

    # a method to select the database
    # in our case it is a json file in the application directory.
    # If it is not found, the database is an empty hash, and you can
    # create a new one via save()
    def select_db( dbname )
        @dbname = dbname + ".json"
        if Pathname.new( @dbname ).exist?
            @db = JSON.parse( IO.read( @dbname ) )
        else
            @db = {}
        end
    end

    # a method to save the database back into the file
    # now, it shouldn't be necessary for the real life databases,
    # but in our case we should always call it at the end of the app.
    # it creates a new database, if it did not exist before.
    def save
        File.open( @dbname, "w" ) do |f|
            f.write( @db.to_json )
        end
    end

    # creates a table in the database, destroying all previous data
    # maybe we should not allow to do that in case of an existing table?
    def create_table( tname )
        @db[ tname ] = []
    end

    # drops the table out of the database
    def drop_table( tname )
        @db.delete( tname )
    end

    # returns the table to work with, creates a new one if it is not found
    def table( tname )
        create_table( tname ) unless @db.has_key? tname
        Table.new @db[ tname ], @db[ tname ]
    end

    # cartesian product of tables, only for Read and Update purposes.
    # in these we can search with table1.field = table2.field queries,
    # in addition to usual searching. All the fields are prefixed with 
    # the table name that they come from.
    def tables( *tnames )
        table = []
        tnames.each do |tname|
            create_table( tname ) unless @db.has_key? tname
            ptable = @db[ tname ].map do |record|
                Hash[ record.map{ |k, v| [ "#{tname}.#{k}", v] } ]
            end

            unless table.empty?
                table = table.product ptable
                table = table.map { |hashes| hashes[0].merge hashes[1] }
            else
                table = ptable
            end
        end
        Table.new table, table
    end
end
