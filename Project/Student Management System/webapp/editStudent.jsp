<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Edit Student</h1>
        <div class="form-section">
            <form action="students" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${student.id}">
                <input type="text" name="name" value="${student.name}" placeholder="Name" required>
                <input type="email" name="email" value="${student.email}" placeholder="Email" required>
                <input type="text" name="course" value="${student.course}" placeholder="Course" required>
                <input type="text" name="phone" value="${student.phone}" placeholder="Phone" pattern="[0-9]{10}" required>
                <select name="grade" required>
                    <option value="A+" ${student.grade == 'A+' ? 'selected' : ''}>A+</option>
                    <option value="A" ${student.grade == 'A' ? 'selected' : ''}>A</option>
                    <option value="B+" ${student.grade == 'B+' ? 'selected' : ''}>B+</option>
                    <option value="B" ${student.grade == 'B' ? 'selected' : ''}>B</option>
                    <option value="C+" ${student.grade == 'C+' ? 'selected' : ''}>C+</option>
                    <option value="C" ${student.grade == 'C' ? 'selected' : ''}>C</option>
                    <option value="D" ${student.grade == 'D' ? 'selected' : ''}>D</option>
                    <option value="F" ${student.grade == 'F' ? 'selected' : ''}>F</option>
                </select>
                <button type="submit">Update</button>
                <a href="students" class="btn-cancel">Cancel</a>
            </form>
        </div>
    </div>
</body>
</html>
