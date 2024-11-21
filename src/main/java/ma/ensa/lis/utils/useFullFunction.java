package ma.ensa.lis.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class useFullFunction {

    public static void executeSqlScript(Connection conn, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");

                // Execute the SQL statement when a semicolon is encountered
                if (line.trim().endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0); // Clear the buffer for the next statement
                }
            }
        } catch (Exception e) {
            throw new IOException("Error executing SQL script: " + e.getMessage(), e);
        }
    }
}
