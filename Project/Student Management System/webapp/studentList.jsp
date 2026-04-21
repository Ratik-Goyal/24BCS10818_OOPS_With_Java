<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Student Management System</h1>
        
        <!-- Statistics -->
        <div class="stats-section">
            <div class="stat-card">
                <h3>${stats.totalStudents}</h3>
                <p>Total Students</p>
            </div>
            <div class="stat-card">
                <h3>${stats.totalCourses}</h3>
                <p>Courses</p>
            </div>
        </div>

        <!-- Search -->
        <div class="search-section">
            <form action="students" method="post">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="Search students..." value="${searchKeyword}">
                <button type="submit">Search</button>
                <c:if test="${not empty searchKeyword}">
                    <a href="students" class="btn-clear">Clear</a>
                </c:if>
            </form>
        </div>
        
        <!-- Add Student -->
        <div class="form-section">
            <h2>Add New Student</h2>
            <form action="students" method="post">
                <input type="hidden" name="action" value="add">
                <input type="text" name="name" placeholder="Name" required>
                <input type="email" name="email" placeholder="Email" required>
                <input type="text" name="course" placeholder="Course" required>
                <input type="text" name="phone" placeholder="Phone" pattern="[0-9]{10}" required>
                <select name="grade" required>
                    <option value="">Select Grade</option>
                    <option value="A+">A+</option>
                    <option value="A">A</option>
                    <option value="B+">B+</option>
                    <option value="B">B</option>
                    <option value="C+">C+</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                    <option value="F">F</option>
                </select>
                <button type="submit">Add Student</button>
            </form>
        </div>

        <!-- Student List -->
        <div class="table-section">
            <h2>Student List</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Course</th>
                        <th>Phone</th>
                        <th>Grade</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.id}</td>
                            <td>${student.name}</td>
                            <td>${student.email}</td>
                            <td>${student.course}</td>
                            <td>${student.phone}</td>
                            <td><span class="grade-${student.grade}">${student.grade}</span></td>
                            <td>
                                <a href="students?action=edit&id=${student.id}">Edit</a>
                                <a href="students?action=delete&id=${student.id}" 
                                   onclick="return confirm('Delete this student?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
