package com.iteachcoding.web.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.iteachcoding.web.model.Person;
import com.iteachcoding.web.util.WorkbookUtility;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String target = null;
    
    try {
      
      final String fileName = getServletContext().getRealPath(WorkbookUtility.INPUT_FILE);
      final File inputFile = new File(fileName);
      final List<Person> people = WorkbookUtility.retrievePeopleFromWorkbook(inputFile);
      
      final String firstName = request.getParameter("firstName");
      
      final List<Person> filtered = people
                                      .stream()
                                      .filter((person) -> person.getFirstName().equalsIgnoreCase(firstName))
                                      .collect(Collectors.toList());
      
      request.setAttribute("people", filtered);
      
      target = "view-all.jsp";
      
    } catch (InvalidFormatException e) {
      e.printStackTrace();
      throw new IOException("The workbook file has an invalid format.");
    }
    
    request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
