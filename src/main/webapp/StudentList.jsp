<%@ page import="java.util.List" %>
<%@ page import="com.dhiraj.Student" %>
<%@ page import="com.dhiraj.StudentDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Student Management</title>
</head>
<body>

    <h1>Student Management</h1>
    <h3>
        <a href="AddStudent.jsp"> Add New Student </a>
        &nbsp;&nbsp;&nbsp;
        <!-- <a href="StudentList.jsp"> List All Students </a> -->
    </h3>


<div align="center">
    <table border="1" >
    <h2>List of Students</h2>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Roll Number</th>
            <th>Course</th>
            <th>Age</th>
            <th>Actions</th>
        </tr>
        <%
            List<Student> studentList = (List<Student>) request.getAttribute("studentList");
            if (studentList != null && !studentList.isEmpty()) {
                for (Student student : studentList) {
        %>
        <tr>
            <td><%= student.getId() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getRollNumber() %></td>
            <td><%= student.getCourse() %></td>
            <td><%= student.getAge() %></td>
            <td>
                <a href="editStudent?id=<%= student.getId() %>">
                    <button>Edit</button>
                </a>
                <a href="deleteStudent?id=<%= student.getId() %>">
                    <button>Delete</button>
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6"> No students found.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
