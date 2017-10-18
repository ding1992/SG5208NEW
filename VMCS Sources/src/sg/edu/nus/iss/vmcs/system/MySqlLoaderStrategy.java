package sg.edu.nus.iss.vmcs.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MySqlLoaderStrategy implements LoadPropertyStrategy {
	Connection connection;
	@Override
	public Properties loadProperties(String dbName) {
		openConnection();
		Properties prop = new Properties();
		String sql = "SELECT * FROM " + dbName;
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				prop.put(result.getString("key"), result.getString("value"));
			}
			result.close();
		}catch(Exception e) {
			System.out.println("Load exception: "+e.getMessage());
		}
		return prop;
	}
	
	@Override
	public void saveProperties(String dbName, Properties prop) throws IOException {
		openConnection();
		String sql = "UPDATE "+dbName+" SET value = ? where `key` = ? ";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			Iterator it = prop.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry pair = (Map.Entry)it.next();
				stmt.setString(1, pair.getValue().toString());
				stmt.setString(2, pair.getKey().toString());
				//stmt.executeUpdate();
				stmt.addBatch();
			}
			stmt.executeBatch();
		}catch(Exception e) {
			System.out.println("Load exception: "+e.getMessage());
		}
	}
    private void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(Environment.getDBString(),"root","password");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
