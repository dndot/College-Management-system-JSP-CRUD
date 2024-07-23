package com.dhiraj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student student) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "INSERT INTO student (name, roll_number, course, age) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getRollNumber());
        preparedStatement.setString(3, student.getCourse());
        preparedStatement.setInt(4, student.getAge());
        preparedStatement.executeUpdate();
        conn.close();
    }

    public List<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<>();

        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM student";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setRollNumber(resultSet.getString("roll_number"));
            student.setCourse(resultSet.getString("course"));
            student.setAge(resultSet.getInt("age"));
            studentList.add(student);
        }
        conn.close();
        return studentList;
    }

    public Student getStudentById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "SELECT * FROM student WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Student student = null;
        if (resultSet.next()) {
            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setRollNumber(resultSet.getString("roll_number"));
            student.setCourse(resultSet.getString("course"));
            student.setAge(resultSet.getInt("age"));
        }
        conn.close();
        return student;
    }

    public void updateStudent(Student student) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "UPDATE student SET name=?, roll_number=?, course=?, age=? WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getRollNumber());
        preparedStatement.setString(3, student.getCourse());
        preparedStatement.setInt(4, student.getAge());
        preparedStatement.setInt(5, student.getId());
        preparedStatement.executeUpdate();
        conn.close();
    }

    public void deleteStudent(int studentId) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseUtil.getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, studentId);
        preparedStatement.executeUpdate();
        conn.close();
    }
}
