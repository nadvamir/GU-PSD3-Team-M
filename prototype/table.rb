require_relative 'record'

# it is a physical table, but it might also be its projection
# it is safe to use for CRUD in all cases except when it is a result of
# db.tables() method. I am too lazy to implement locking, so in that case
# it is up to you to remember only do searches and updates in it
class Table
    def initialize( table, actual_table )
        @table = table
        # actual table is required in the case of chained find() calls
        @actual_table = actual_table
    end

    # an old version of find, that you would always chain
    # now you can just pass a hash, but the chaining behaviour is 
    # still retained
    #def find( field, value )
    #    filtered = @table.select do |r|
    #        r.has_key? field and r[ field ] == value
    #    end
    #    Table.new filtered, @actual_table
    #end

    # a search function, which can be given a hash of parameters
    # to filter the table on. If the table is a cartesian product
    # of a few other tables, you can use hash keys as variables
    # and do things like find( 'products.owner' => 'users.id' )
    def find( parameters )
        filtered = @table.select do |r|
            accept = true
            parameters.each do |field, value|
                # simple query
                unless value.include? '.'
                    accept = false unless r.has_key? field and r[ field ] == value
                # value is another field
                else
                    accept = false unless r.has_key? field and r.has_key? value and r[ field ] == r[ value ]
                end
            end
            accept
        end
        Table.new filtered, @actual_table
    end

    # returns the first record in the table
    def one
        Record.new( @table.first, @actual_table) unless @table.empty?
    end

    # returns an array of records, contained in the table
    def all
        @table.map { |r| Record.new r, @actual_table }
    end

    # inserts a record in the table
    # in a very much NoSQL fashion, it does not care for consistency
    # so it is again up to user.
    def insert( record )
        @table.push record
        @actual_table.push record unless @table == @actual_table
    end
end
