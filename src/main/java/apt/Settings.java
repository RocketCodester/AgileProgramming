package apt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class Settings {
	private static Settings instance;
	private Properties properties;
	
	private Settings() {
		properties = new Properties();
		String directory = System.getProperty("user.dir").replace('\\', '/');
		directory += "/src/main/config";
		// @@@ TODO Why is this needed?  Why not just not add the directory=directory+"/apt";?
		while (!"".equals(directory)) {
			String configFileName = directory + "/app.config";
			if (new File(configFileName).exists()) {
				try {
					properties.load(new FileInputStream(configFileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			} else {
				int separator = directory.lastIndexOf('/');
				if (separator == -1) {
					directory = "";
				} else {
					directory = directory.substring(0, separator);
				}
			}
		}
		System.out.println("Unable to locate app.config");
	}
	
	public synchronized static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
	public String getDbConnectionDriver() {
		return properties.getProperty("DbConnection.driver");
	}
	
	public String getDbConnectionUrl() {
		return properties.getProperty("DbConnection.url");
	}
}
