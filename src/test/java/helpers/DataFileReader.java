package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataFileReader {
    public static Properties properties;
    private static InputStream inputStream = null;

    public static Properties getPropertyInstance()
    {
        properties = new Properties();
        File resourcesFolder = new File(
                System.getProperty("user.dir")
                        + "/src/test/resources");
        for (File file : resourcesFolder.listFiles())
        {
            if (file.isFile())
            {
                try
                {
                    inputStream = new FileInputStream(file);
                    properties.load(inputStream);
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
        return properties;
    }

    public static String readProperty(String key)
    {
        String property = properties.getProperty(key);
        if (property == null)
        {
            System.out.println("Property not recognised!");
            System.out.println("key = " + key);
            return "PROPERTY NOT RECOGNISED";
        }
        return properties.getProperty(key);
    }

}
