package com.ua.rd.HW39.service;

import com.ua.rd.HW39.domain.Student;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.*;


@Service
public class TestStudentService {

    public List<Student> findAll() {
        String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
        String DB_USER = "root";
        String DB_PASSWORD = "ujdyj100";
        String SELECT_FROM_STUDENTS = "SELECT * FROM students_list";
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENTS)) {
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("groupId"))
                        .build();
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Student> findByGroupId(int groupId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
        String DB_USER = "root";
        String DB_PASSWORD = "ujdyj100";
        String SELECT_FROM_STUDENTS_GROUP_ID = "SELECT * FROM students_list WHERE groupId = ?";
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            ps = conn.prepareStatement(SELECT_FROM_STUDENTS_GROUP_ID);
            ps.setInt(1, groupId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("groupId"))
                        .build();
                result.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                assert ps != null;
                ps.close();
            } catch (SQLException e) {

            }
        }
        return result;
    }

}
