package com.nucleus.backend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Prepayment
 */
@WebServlet("/Prepayment")
public class Prepayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Prepayment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			float loanAmount = Float.parseFloat(request.getParameter("loanAmount"));
			float interest = Float.parseFloat(request.getParameter("interest"));
			float tenure = Float.parseFloat(request.getParameter("tenure"));
			float paymentsPerYear = Float.parseFloat(request.getParameter("paymentsPerYear"));
			float prepaymentAmount = Float.parseFloat(request.getParameter("prepaymentAmount"));
			float prepaymentMonths = Float.parseFloat(request.getParameter("prepaymentMonths"));
			EMIFunction obj = new EMIFunction();
			obj.prepaymentSchedule(loanAmount, interest, tenure, paymentsPerYear, prepaymentAmount, prepaymentMonths, out);
		} catch (NumberFormatException e) {
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">Please give valid input!<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Try again?</button></a></h3>");				
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}