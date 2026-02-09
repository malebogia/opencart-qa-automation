package data.factories;

import data.models.User;
import utils.ConfigReader;

public class UserFactory {

    public static User validUserJonh() {
        return new User("kiril",
                "kiril",
                ConfigReader.getProperty("base.email"),
                ConfigReader.getProperty("default.password")
        );
    }

    public static User validUserJenny() {
        return new User("Jenny",
                "Doe",
                generateUniqueEmail(),
                ConfigReader.getProperty("default.password")
        );
    }

    public static User validUserJim() {
        return new User("Jim",
                "Doe",
                generateUniqueEmail(),
                ConfigReader.getProperty("default.password")
        );
    }

    public static User validUserToni() {
        return new User("Toni",
                "Doe",
                generateUniqueEmail(),
                ConfigReader.getProperty("default.password")
        );
    }

    private static String generateUniqueEmail(){
        return "user" + System.currentTimeMillis() + "@test.com";
    }
}
