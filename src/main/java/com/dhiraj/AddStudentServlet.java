package com.dhiraj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String rollNumber = req.getParameter("rollNumber");
        String course = req.getParameter("course");
        int age = Integer.parseInt(req.getParameter("age"));

        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setRollNumber(rollNumber);
        newStudent.setCourse(course);
        newStudent.setAge(age);

        try {
            studentDAO.addStudent(newStudent);
            resp.sendRedirect("list"); // Redirect to student list page after adding
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
