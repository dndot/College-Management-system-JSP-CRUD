package com.dhiraj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Student student = studentDAO.getStudentById(id);
            req.setAttribute("student", student);
            req.getRequestDispatcher("EditStudent.jsp").forward(req, resp); // Forward to edit student JSP page
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String rollNumber = req.getParameter("rollNumber");
        String course = req.getParameter("course");
        int age = Integer.parseInt(req.getParameter("age"));

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setRollNumber(rollNumber);
        student.setCourse(course);
        student.setAge(age);

        try {
            studentDAO.updateStudent(student);
            resp.sendRedirect("list"); // Redirect to student list page after update
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
