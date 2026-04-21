package com.student.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:h2:mem:student_db;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static boolean initialized = false;

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        
        if (!initialized) {
            initializeDatabase(conn);
            initialized = true;
        }
        
        return conn;
    }
    
    private static void initializeDatabase(Connection conn) throws Exception {
        Statement stmt = conn.createStatement();

        stmt.execute("DROP TABLE IF EXISTS students");
        
        stmt.execute("CREATE TABLE students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) NOT NULL UNIQUE, " +
                    "course VARCHAR(100) NOT NULL, " +
                    "phone VARCHAR(20), " +
                    "grade VARCHAR(5))");
        
        stmt.execute("INSERT INTO students (name, email, course, phone, grade) VALUES " +
                    "('John Doe', 'john@example.com', 'Computer Science', '9876543210', 'A'), " +
                    "('Jane Smith', 'jane@example.com', 'Mathematics', '9876543211', 'A+')");
        stmt.close();
    }
}
