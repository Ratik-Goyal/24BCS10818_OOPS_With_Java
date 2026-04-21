package com.student.dao;

import com.student.model.Student;
import com.student.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class StudentDAO {
    
    public boolean addStudent(Student student) throws Exception {
        String sql = "INSERT INTO students (name, email, course, phone, grade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGrade());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getString("phone"),
                    rs.getString("grade")
                ));
            }
        }
        return students;
    }

    public Student getStudentById(int id) throws Exception {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("phone"),
                        rs.getString("grade")
                    );
                }
            }
        }
        return null;
    }

    public boolean updateStudent(Student student) throws Exception {
        String sql = "UPDATE students SET name=?, email=?, course=?, phone=?, grade=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGrade());
            ps.setInt(6, student.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws Exception {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Student> searchStudents(String keyword) throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR email LIKE ? OR course LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getString("phone"),
                        rs.getString("grade")
                    ));
                }
            }
        }
        return students;
    }

    public Map<String, Integer> getStatistics() throws Exception {
        Map<String, Integer> stats = new HashMap<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM students");
            if (rs.next()) {
                stats.put("totalStudents", rs.getInt("total"));
            }
            
            rs = stmt.executeQuery("SELECT COUNT(DISTINCT course) as courses FROM students");
            if (rs.next()) {
                stats.put("totalCourses", rs.getInt("courses"));
            }
            
            rs = stmt.executeQuery("SELECT COUNT(*) as aGrade FROM students WHERE grade LIKE 'A%'");
            if (rs.next()) {
                stats.put("aGradeStudents", rs.getInt("aGrade"));
            }
        }
        return stats;
    }
}