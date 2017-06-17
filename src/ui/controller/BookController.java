package ui.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.LibraryService;
import domain.db.LibraryServiceNoDB;
import domain.model.Book;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibraryService library = new LibraryServiceNoDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String destination;
		
		switch(action) {
			case "READ":
				destination = showBooks(request, response);
				break;
			default:
				destination = "index.jsp";
				break;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	protected String showBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "boekenkast.jsp";
		Collection<Book> books = library.read();
		request.setAttribute("books", books);
		return destination;
	}

}
