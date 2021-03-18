package utilities;

public class EnvironmentManager {
    //we will use this class to set up all the environment variables

    //our environments will have different urls, user credentials, api endpoints
    public static String baseUrl;
    public static String username;
    public static String password;

    public static void setUpEnvironment() throws Exception{
        switch (ConfigReader.getProperty("environment").toLowerCase()){
            case "dev":
                DEV();
                break;
            case "qa":
                QA();
                break;
            case "prod":
                PROD();
                break;
                default:
                    //here you can set qa env to be default
                    throw new Exception("Invalid environment in configuration.properties.\n" +
                            "Please select from: Dev, Qa, Prod");

        }
    }



    private static void DEV(){
        //to set up dev variables
        baseUrl = "https://www.dev-meetup.com/";
        username = "TestUserDev";
        password = "test123";
    }

    private static void QA(){
        //to set up qa variables
        baseUrl = "https://www.qa-meetup.com/";
        username = "TestUserQa";
        password = "test123";
    }

    private static void PROD(){
        //set up prod variables
        baseUrl = "https://www.meetup.com/";
        username = "ProdUser";
        password = "test123";
    }




}
