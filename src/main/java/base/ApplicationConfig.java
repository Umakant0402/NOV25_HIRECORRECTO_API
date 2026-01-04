package base;

/**
 * FrameworkConfig
 * FrameworkController
 * ApplicationController
 */
public class ApplicationConfig {

    //ENVIRONMENT VARIABLES
    public static String getEnvironment() {
        String env = System.getProperty("environment");
        if (env == null || env.isEmpty()) {
            env = "stage";
        }
        System.out.println("Running tests on environment: " + env.toUpperCase());
        return env;
    }


}
