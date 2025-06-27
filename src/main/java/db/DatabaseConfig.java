package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private static final String PROPERTIES_FILE = "src/main/resources/.properties";

    public static Properties LoadProperties() {
        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;

    }

}
