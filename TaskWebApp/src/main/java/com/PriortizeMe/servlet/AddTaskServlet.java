package com.PriortizeMe.servlet;

import com.PriortizeMe.dao.TaskDao;
import com.PriortizeMe.model.Task;
import com.PriortizeMe.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskName = request.getParameter("taskName");
        String priority = request.getParameter("priority");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            Task task = new Task();
            task.setUserId(user.getId());
            task.setTaskName(taskName);
            task.setPriority(priority);

            TaskDao taskDao = new TaskDao();
            if (taskDao.addTask(task)) {
                response.sendRedirect("profile.jsp"); // Redirecting to the profile page after success
            } else {
                response.sendRedirect("error.jsp?msg=Error adding task!"); // Redirecting to error page
            }
        } else {
            response.sendRedirect("login.html");
        }
    }
}
