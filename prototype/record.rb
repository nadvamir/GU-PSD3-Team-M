# a record class, which we need so that we can easily update
# values and delete itself from the database
class Record
    def initialize( record, table )
        @record = record
        @table = table
    end

    # delete the record from the database
    def delete
        @table.delete @record
    end

    # update its fields
    def set( parameters )
        parameters.each { |field, value| @record[ field ] = value }
    end

    # get the hash representation for accessing the fieds
    def to_hash
        @record
    end
end
