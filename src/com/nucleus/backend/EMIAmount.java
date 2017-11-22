package com.nucleus.backend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EMIAmount
 */
@WebServlet("/EMIAmount")
public class EMIAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EMIAmount() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			float loanAmount = Float.parseFloat(request.getParameter("loanAmount"));
			float interest = Float.parseFloat(request.getParameter("interest"));
			float tenure = Float.parseFloat(request.getParameter("tenure"));
			float paymentsPerYear = Float.parseFloat(request.getParameter("paymentsPerYear"));
			EMIFunction obj = new EMIFunction();
			if(request.getParameter("optradio1").equals("Fixed Interest")) {
				if(request.getParameter("operation").equals("Calculate EMI")) {
					double emiValue = obj.installmentCalculator(loanAmount, interest, tenure, paymentsPerYear);
					out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div class=\"form-group\"><h3 align=\"center\" class=\"container\">The Equated Monthly Installment value is: <font color=\"red\">"+emiValue+"</font><br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
				} else if(request.getParameter("operation").equals("Repayment Schedule")) {
					obj.repaymentSchedule(loanAmount, interest, tenure, paymentsPerYear, out);
				} else {
					float installmentNo = Float.parseFloat(request.getParameter("installmentNo"));
					obj.principalComponent(loanAmount, interest, tenure, paymentsPerYear, installmentNo, out);
				}	
			} else if(request.getParameter("optradio1").equals("Floating Interest")){
				float installmentChangeNo = Float.parseFloat(request.getParameter("timeBeforeInterestChange"));
				float newInterest = Float.parseFloat(request.getParameter("newInterest"));
				if(request.getParameter("optradio2").equals("Change EMI, Same Tenure")) {
					if(request.getParameter("operation").equals("Calculate EMI")) {
						obj.newEMISameTenure(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, out);
					} else if(request.getParameter("operation").equals("Repayment Schedule")) {
						obj.newRepaymentScheduleSameTenure(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, out);
					} else {
						float installmentNo = Float.parseFloat(request.getParameter("installmentNo"));
						obj.newPrincipalComponentSameTenure(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, installmentNo, out);
					}
				} else if(request.getParameter("optradio2").equals("Change Tenure, Same EMI")) {
					if(request.getParameter("operation").equals("Calculate Tenure")) {
						obj.newTenureSameEMI(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, out);
					} else if(request.getParameter("operation").equals("Repayment Schedule")) {
						obj.newRepaymentScheduleSameEMI(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, out);
					} else {
						float installmentNo = Float.parseFloat(request.getParameter("installmentNo"));
						obj.newPrincipalComponentSameEMI(loanAmount, interest, tenure, paymentsPerYear, installmentChangeNo, newInterest, installmentNo, out);
					}
				}
			}
		} catch (NumberFormatException e) {
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">Please give valid input!<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Try again?</button></a></h3>");				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
