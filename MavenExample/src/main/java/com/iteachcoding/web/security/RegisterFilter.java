package com.iteachcoding.web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;
import com.iteachcoding.web.dao.impl.UserIO;
import com.iteachcoding.web.model.User;
import com.iteachcoding.web.util.CookieUtility;

/**
 * Servlet Filter implementation class RegisterFilter
 */
@WebFilter("/RegisterFilter")
public class RegisterFilter implements Filter {

  private FilterConfig filterConfig = null;

  /**
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig) throws ServletException {
    this.filterConfig = fConfig;
  }

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    if (isIgnoredPath(request, filterConfig) || isRegistered(request, response)) {
      // pass the request along the filter chain
      chain.doFilter(request, response);
    } else {
      ((HttpServletRequest) request).getRequestDispatcher("register.jsp").forward(request, response);
    }

  }

  /**
   * Check the requestURI to see if it contains an ignored path using class constant and web.xml 
   * init parameter.  
   * 
   * @param requestURI
   * @param filterConfig
   * @return true if path should be ignored
   */
  private static boolean isIgnoredPath(final ServletRequest request, final FilterConfig filterConfig) {

    final String requestURI = ((HttpServletRequest) request).getRequestURI();
    final String pathsToIgnore = filterConfig.getInitParameter("pathsToIgnore");

    for (final String path : pathsToIgnore.split(",")) {
      if (requestURI.contains(path)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check to see if the user is active in session. 
   * If not, check for a cookie. Otherwise, return false 
   * so we can send user to registration page.
   * 
   * @param request
   * @param response
   * @return true if user is registered
   */
  private static boolean isRegistered(final ServletRequest request, final ServletResponse response) {

    final HttpSession session = ((HttpServletRequest) request).getSession();
    User user = (User) session.getAttribute("user");

    // if User object doesn't exist, check email cookie
    if (user == null) {
      final Cookie[] cookies = ((HttpServletRequest) request).getCookies();
      final String emailAddress = CookieUtility.getCookieValue(cookies, "emailCookie");

      // if cookie doesn't exist, go to registration page
      if (Strings.isNullOrEmpty(emailAddress)) {
        return false;
      } else { // if cookie exists, try to create User object, add to Session, and set registered to true
        final String path = session.getServletContext().getRealPath("/WEB-INF/EmailList.txt");
        user = UserIO.getUser(emailAddress, path);
        if (user == null) { // if user isn't found in cookie, go to registration page
          return false;
        }
        session.setAttribute("user", user);
        session.setAttribute("registered", true);
      }
    }
    return true;
  }

  /**
   * @see Filter#destroy()
   */
  @Override
  public void destroy() {
    filterConfig = null;
  }

}
