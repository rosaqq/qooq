package net.sknv.qooq;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Utils {
    public static String getToken() {

        final URL config = Utils.class.getClassLoader().getResource("general.properties");
        System.out.println(config);

        try {
            Properties prop = new Properties();
            FileInputStream ip = new FileInputStream(String.valueOf(config).substring("file:/".length()));
            prop.load(ip);
            return prop.getProperty("token");
        }
        catch (IOException io) {
            System.out.println(io);
            return "ERROR";
        }
    }
}
