package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class DBUtils {

    private static final Logger logger = LogManager.getLogger(DBUtils.class);

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection(){
        try{
            logger.info("Creating database connection");

            connection = DriverManager.getConnection(
                    ConfigReader.getProperty("db.url"),
                    ConfigReader.getProperty("db.user"),
                    ConfigReader.getProperty("db.password")
            );
            logger.info("Database connection established successfully.");

        }catch (SQLException e){
            logger.error("Failed to connect to the database.", e);
                       throw new RuntimeException("Failed to connect to the Database", e);
        }

    }

    /**
     This method runs a query and returns the data (ResultSet)
     @param query - The SQL command (e.g. SELECT * FROM users)
     @return A ResultSet object containing the data returned by the database.
     */

    public static ResultSet executeQuery(String query){
        try{
            if (connection == null) {
                logger.warn("Database connection is null. Creating new connection");
                createConnection();
            }

            logger.info("Executing SQL query: {}", query);

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            logger.info("Query executed successfully");
            return resultSet;
        } catch (SQLException e){
            logger.error("Query execution failed: {}", query,e);
            throw new RuntimeException("Query execution failed: " + query,e);
        }
    }

    /**
     * ALWAYS close the connection when finished to avoid memory leaks!
     */

    public static void destroy(){
        try{
            logger.info("Closing database resources...");

            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();

            logger.info("Database resources closed successfully");
        }catch (SQLException e){
            logger.error("Error while closing database resources.",e);
        }
    }





}
