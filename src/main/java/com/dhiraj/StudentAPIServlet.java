package com.dhiraj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class StudentAPIServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Student> students;
        try {
            students = studentDAO.getAllStudents();
            PrintWriter out = resp.getWriter();
            for (Student student : students) {
                out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() + ", Course: " + student.getCourse() + ", Age: " + student.getAge());
            }
            out.flush();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Internal server error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String rollNumber = req.getParameter("rollNumber");
        String course = req.getParameter("course");
        String ageStr = req.getParameter("age");

        if (name.isEmpty() || rollNumber.isEmpty() || course.isEmpty() || ageStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("All fields are required: name, rollNumber, course, and age.");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);

            Student student = new Student();
            student.setName(name);
            student.setRollNumber(rollNumber);
            student.setCourse(course);
            student.setAge(age);

            studentDAO.addStudent(student);
            PrintWriter out = resp.getWriter();
            out.print("Student added successfully");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Invalid age format. Age must be a number.");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error adding student");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String rollNumber = req.getParameter("rollNumber");
        String course = req.getParameter("course");
        String ageStr = req.getParameter("age");
        int id = Integer.parseInt(req.getParameter("id"));

        if (name.isEmpty() || rollNumber.isEmpty() || course.isEmpty() || ageStr.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("All fields are required: name, rollNumber, course, and age.");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setRollNumber(rollNumber);
            student.setCourse(course);
            student.setAge(age);

            studentDAO.updateStudent(student);
            PrintWriter out = resp.getWriter();
            out.print("Student updated successfully");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Invalid age format. Age must be a number.");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error updating student");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int studentId = 0;
        studentId = Integer.parseInt(req.getParameter("id"));

        if (studentId == 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Student ID is required.");
            return;
        }

        try {
            studentDAO.deleteStudent(studentId);
            PrintWriter out = resp.getWriter();
            out.print("Student deleted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print("Error deleting student");
        }
    }
}

