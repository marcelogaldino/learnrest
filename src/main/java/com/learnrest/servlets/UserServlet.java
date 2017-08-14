package com.learnrest.servlets;

import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando
 */
@WebServlet(value = "/users/*")
public class UserServlet extends HttpServlet {

    @Inject
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<User> users = userDAO.findAll();
        
        PrintWriter pw = resp.getWriter();
        
        for(User u : users) {
            pw.print(u);
        }
        
    }
    
    
}
