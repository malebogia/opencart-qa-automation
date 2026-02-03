package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for database operations.
 * Handles connection creation, query execution, and connection cleanup.
 */
public class DBUtils {

    private static final Logger logger = LogManager.getLogger(DBUtils.class);
    private static Connection connection;

    /**
     * Creates a database connection using configuration from ConfigReader.
     */
    private static void createConnection() {
        try {
            logger.info("Creating database connection...");

            connection = DriverManager.getConnection(
                    ConfigReader.getProperty("db.url"),
                    ConfigReader.getProperty("db.user"),
                    ConfigReader.getProperty("db.password")
            );

            logger.info("Database connection established successfully");

        } catch (SQLException e) {
            logger.error("Failed to connect to the database", e);
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    /**
     * Executes a SQL SELECT query and returns the result as a list of maps.
     *
     * @param query SQL query to execute.
     * @return List of rows represented as Map<ColumnName, Value>.
     */
    public static List<Map<String, Object>> executeQuery(String query) {
        try {
            if (connection == null || connection.isClosed()) {
                logger.warn("Database connection is null or closed. Recreating...");
                createConnection();
            }

            logger.info("Executing SQL query: {}", query);

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Map<String, Object>> result = new ArrayList<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnName(i), resultSet.getObject(i));
                    }
                    result.add(row);
                }
                return result;
            }

        } catch (SQLException e) {
            logger.error("Query execution failed: {}", query, e);
            throw new RuntimeException("Query execution failed: " + query, e);
        }
    }

    /**
     * Closes the database connection.
     * Recommended to call after all tests are finished.
     */
    public static void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                logger.info("Closing database connection...");
                connection.close();
                connection = null;
                logger.info("Database connection closed successfully");
            }
        } catch (SQLException e) {
            logger.error("Error while closing database connection", e);
        }
    }
}
