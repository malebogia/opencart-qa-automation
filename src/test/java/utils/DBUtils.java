package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class DBUtils {

private static final Logger logger = LogManager.getLogger(DBUtils.class);
private static Connection connection;

public static void createConnection(){
    try{
        logger.info("Creating database connection...");

        connection = DriverManager.getConnection(
                ConfigReader.getProperty("db.url"),
                ConfigReader.getProperty("db.user"),
                ConfigReader.getProperty("db.password")
        );

        logger.info("Database connection established successfully.");
    } catch (SQLException e){
        logger.error("Failed to connect to the database", e);

        throw new RuntimeException("Failed to connect to the database",e);
    }
}

    /**
     * Executes a SQL SELECT query and returns a ResultSet.
     *
     * IMPORTANT:
     * - Statement is created locally (thread-safe)
     * - Errors are logged AND rethrown
     *
     * @param query SQL query to execute
     * @return ResultSet with the query result
     */

    public static ResultSet executeQuery(String query){

        try {
            if (connection == null){
                logger.warn("Database connection is null. Creating a new connection.");
                createConnection();
            }

            logger.info("Executing SQL query: {}", query);

            Statement statement = connection.createStatement();

            return  statement.executeQuery(query);
        }catch (SQLException e){
            logger.error("Query execution failed: {}",query,e);
            throw new RuntimeException("Query execution failed: " + query,e);
        }
    }



    public static void destroy(){
        try{
            logger.info("Closing database connection...");
            if (connection != null){
                connection.close();
                connection = null;
            }

            logger.info("Database connection closed successfully");
        }catch (SQLException e){
            logger.error("Error while closing database connection", e);
        }
    }



}
