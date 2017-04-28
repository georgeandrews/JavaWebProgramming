package com.iteachcoding.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iteachcoding.web.dao.impl.UserIO;
import com.iteachcoding.web.model.User;

/**
 * Servlet implementation class RegisterUserController
 */
@WebServlet("/RegisterUser")
public class RegisterUserController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/register.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    final String action = request.getParameter("action");
    final String target = "/index.jsp";

    if (action.equals("registerUser")) {
      registerUser(request, response);
    }

    request.getRequestDispatcher(target).forward(request, response);
  }



  private void registerUser(final HttpServletRequest request, final HttpServletResponse response) {

    // get the user data
    final String email = request.getParameter("email");
    final String firstName = request.getParameter("firstName");
    final String lastName = request.getParameter("lastName");

    final User user = new User(firstName, lastName, email);

    // write the User object to a file
    final String path = getServletContext().getRealPath("/WEB-INF/EmailList.txt");
    UserIO.add(user, path);

    // store the User object as a session attribute
    final HttpSession session = request.getSession();
    session.setAttribute("user", user);
    session.setAttribute("registered", true);

    // add a cookie that stores the user's email to browser
    final Cookie c = new Cookie("emailCookie", email);
    c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years
    c.setPath("/");                      // allow entire app to access it
    response.addCookie(c);
  }

}
