package sg.edu.nus.iss.vmcs.system;

<<<<<<< HEAD
import java.io.FileOutputStream;
import java.io.IOException;
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> 7b61a2b5782daa5002691eb233826206e26979ad
import java.util.Properties;

public class MySqlLoaderStrategy implements LoadPropertyStrategy {
	Connection connection;
	@Override
	public Properties loadProperties(String dbName) {
		openConnection();
		Properties prop = new Properties();
		String sql = "SELECT * FROM " + dbName;
		try {
			ResultSet result = connection.prepareStatement(sql).executeQuery();
			while (result.next()) {
				prop.setProperty(result.getString(0), result.getString(1));
			}
			result.close();
		}catch(Exception e) {
			System.out.println("Load exception");
		}
		return prop;
	}
	
<<<<<<< HEAD
	@Override
	public void saveProperties(String name, Properties prop) throws IOException {

	}
=======
    private void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.connection = DriverManager.getConnection(Environment.getDBString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
>>>>>>> 7b61a2b5782daa5002691eb233826206e26979ad

    }
}
