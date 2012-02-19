package com.kyle.route66.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class AccountActivationServlet
 */
public class AccountActivationServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(AccountActivationServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountActivationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestId = request.getParameter("id");
		
		log.debug("id=" + requestId);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("requestId", requestId);
		
		response.sendRedirect(request.getContextPath() + "/account/startActivation.jsf");
	}

}
