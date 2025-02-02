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
import java.util.List;

@WebServlet("/getTasks")
public class GetTasksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            TaskDao taskDao = new TaskDao();
            List<Task> tasks = taskDao.getTasksByUserId(user.getId());

            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.html");
        }
    }
}
