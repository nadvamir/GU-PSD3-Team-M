package components.database;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Provides a simple wrapper around a (Derby) DBMS for
 * storing sensor readings.
 * 
 * @author tws
 * 
 */
public class DBMS {
	
	private static final String connectionString =
		"jdbc:derby:data/dcs;create=true";

	
	public DBMS(){
		try {
			getDatabaseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a ResultSet for the specified table, filtered
	 * by the specified condition.
	 * 
	 * @param tableName
	 *            the table containing sensor data.
	 * @param condition
	 *            the filtering condition
	 * @return an SQL ResultSet
	 * @throws SQLException
	 */
	public ResultSet getRows(String tableName, String condition)
		throws SQLException{
		Connection connection = getDatabaseConnection();
		
		String sqlString = 
			"SELECT * FROM "+ tableName +" WHERE " + condition;
		
		PreparedStatement preparedStatement =
			connection.prepareStatement(sqlString);
				
		ResultSet result = preparedStatement.executeQuery();

		return result;	
	}

	/**
	 * Inserts a new row into the specified table using the
	 * map of column name, values as data.
	 * 
	 * @param tableName
	 *            the name of the table to append the row
	 *            to.
	 * @param values
	 *            a Map of column name, value pairs. Java
	 *            types are converted to SQL types as
	 *            appropriate.
	 * @throws SQLException
	 */
	public void insertRow(String tableName, Map<String,Object> values)
		throws SQLException {
						
		Connection connection = 
			getDatabaseConnection();
		
		List<String> fields =
			new ArrayList<String>(values.keySet());				
	
		String fieldsSQL = createFieldsSQLString(fields);
				
		String statementString = 
			"INSERT INTO " + tableName +
			 " ("+fieldsSQL+")" +
			   "VALUES (?,?,?)";
				
		PreparedStatement statement = 
			connection.prepareStatement(statementString);
		
		
		Integer columnIndex = 1;
		
		for (String field: fields){
			Object value = values.get(field);
			
			if (value.getClass().equals(Timestamp.class))
				statement.setTimestamp(columnIndex, Timestamp.class.cast(value));
			else if (value.getClass().equals(String.class))
				statement.setString(columnIndex, String.class.cast(value));
			else if (value.getClass().equals(Double.class))
				statement.setDouble(columnIndex, Double.class.cast(value));

			columnIndex ++;
		}
		
		statement.execute();
		connection.close();
	}


	private String createFieldsSQLString(List<String> fields) {
				
		Iterator<String> fieldsIterator = 
				fields.iterator();
		
		String result = 
			fieldsIterator.hasNext()?fieldsIterator.next():"";
		
		while(fieldsIterator.hasNext()){
			String field = ", "+fieldsIterator.next();
			result += field;
		}
		
		return result;
	}
	
	/**
	 * Checks if the specified table exists in the managed database.
	 * @param tableName the name of the table to check for
	 * @return true if the table exists in the database, else false.
	 * @throws SQLException
	 */
	public Boolean tableExists(String tableName) 
			throws SQLException{
			
		Connection connection =
			getDatabaseConnection();
			
		DatabaseMetaData metaData = 
			connection.getMetaData();
							
		ResultSet resultSet = 
			metaData.getTables(
				null,
				null,
				tableName.toUpperCase(),
				new String[]{"TABLE"});
			
		Boolean result = resultSet.next();

		resultSet.close();
		connection.close();
		
		return result;
	}
		
	/**
	 * Creates the specified table
	 * 
	 * @param tableName
	 *            the name of the table to create
	 * @param specification
	 *            an SQL string describing the fields to be
	 *            inserted.
	 * @throws SQLException
	 */
	public void createTable(String tableName, String specification)
		throws SQLException{
		
		String statementString =
			"CREATE TABLE "+tableName +
		    "  (" + specification +"  )"; 
		 
		Connection connection =
			getDatabaseConnection();
			
		Statement statement = connection.createStatement();
		statement.execute(statementString);

		statement.close();
		connection.close();
	}
		
	private static Connection getDatabaseConnection ()
		throws SQLException{
		return DriverManager.getConnection(connectionString);
	}

	public void stop() {
		try {
			
			DriverManager.getConnection("jdbc:derby:data/sensordb;shutdown=true");
		} catch (java.sql.SQLNonTransientConnectionException e) {
			//This will always throw an exception, so ignore - See Derby documentation
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}