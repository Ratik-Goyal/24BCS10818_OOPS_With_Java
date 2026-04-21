package com.student.servlet;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action == null ? "list" : action) {
                case "add": addStudent(request, response); break;
                case "update": updateStudent(request, response); break;
                case "delete": deleteStudent(request, response); break;
                case "edit": showEditForm(request, response); break;
                case "search": searchStudents(request, response); break;
                default: listStudents(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        request.setAttribute("students", studentDAO.getAllStudents());
        request.setAttribute("stats", studentDAO.getStatistics());
        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        studentDAO.addStudent(createStudent(request));
        response.sendRedirect("students");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        request.setAttribute("student", studentDAO.getStudentById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("editStudent.jsp").forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        Student student = createStudent(request);
        student.setId(Integer.parseInt(request.getParameter("id")));
        studentDAO.updateStudent(student);
        response.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }

    private void searchStudents(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String keyword = request.getParameter("keyword");
        request.setAttribute("students", studentDAO.searchStudents(keyword));
        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("stats", studentDAO.getStatistics());
        request.getRequestDispatcher("studentList.jsp").forward(request, response);
    }

    private Student createStudent(HttpServletRequest request) {
        Student student = new Student();
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setCourse(request.getParameter("course"));
        student.setPhone(request.getParameter("phone"));
        student.setGrade(request.getParameter("grade"));
        return student;
    }
}
