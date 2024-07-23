<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.dhiraj.StudentDAO" %>
<%@ page import="com.dhiraj.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Student</title>
</head>
<body>


    <h1>Student Management</h1>
    <h2>
        <a href="AddStudent.jsp"> Add New Student </a>
        &nbsp;&nbsp;&nbsp;
        <a href="list"> List All Students </a>
    </h2>



    <h1>Add New Student</h1>
    <form action="addStudent" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required/></td>
            </tr>
            <tr>
                <td>Roll Number:</td>
                <td><input type="text" name="rollNumber" required/></td>
            </tr>
            <tr>
                <td>Course:</td>
                <td><input type="text" name="course" required/></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><input type="number" name="age" required/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add Student"/></td>
            </tr>
        </table>
    </form>

</body>
</html>
