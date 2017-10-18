package sg.edu.nus.iss.vmcs.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class TextLoaderStrategy implements LoadPropertyStrategy {

	@Override
	public Properties loadProperties(String filename) throws IOException {
		Properties prop = new Properties(System.getProperties());
		FileInputStream stream = new FileInputStream(filename);
		prop.load(stream);
		stream.close();
		return prop;
	}

	@Override
	public void saveProperties(String name, Properties prop) throws IOException {
		FileOutputStream stream = new FileOutputStream(name);
		prop.store(stream, "");
		stream.close();
	}
}
