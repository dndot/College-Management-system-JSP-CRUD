<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dhiraj.Student" %>
<%@ page import="com.dhiraj.StudentDAO" %>
<%
    // Retrieve the student object from the request
    Student student = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
</head>
<body>

    <h1>Student Management</h1>
    <h3>
        <a href="AddStudent.jsp"> Add New Student </a>
        &nbsp;&nbsp;&nbsp;
        <a href="StudentList.jsp"> List All Students </a>
    </h3>



    <h1>Edit Student</h1>
    <form action="editStudent" method="post">
        <input type="hidden" name="id" value="<%= student.getId() %>">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="<%= student.getName() %>" required></td>
            </tr>
            <tr>
                <td>Roll Number:</td>
                <td><input type="text" name="rollNumber" value="<%= student.getRollNumber() %>" required></td>
            </tr>
            <tr>
                <td>Course:</td>
                <td><input type="text" name="course" value="<%= student.getCourse() %>" required></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" value="<%= student.getAge() %>" required></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Update Student">
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
