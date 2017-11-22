package com.nucleus.backend;

import java.io.PrintWriter;

class EMIFunction {
	public double installmentCalculator(double loanAmount, double interestRate, double tenure, double noPayments) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		return installmentAmount;
	}
	
	public void repaymentSchedule(double loanAmount, double interestRate, double tenure, double noPayments, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div align=\"center\" class=\"container\"><br><br><table class=\"table table-striped\"><thead><tr><th>Installment No.</th><th>Outstanding</th><th>Interest</th><th>Principal</th><th>E.M.I</th></tr></thead><tbody>");
		while(k<=tenure){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			out.println("<tr><td>"+k+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
			os = os - p;
			k++;
		}
		out.println("</tbody></table>");
		out.println("<br><h3><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
	}

	public void principalComponent(double loanAmount, double interestRate, double tenure, double noPayments, double installmentNo, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		i=0;
		p=0;
		os = loanAmount;
		while(k<=installmentNo){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			os = os - p;
			k++;
		}
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 class=\"container\">The principal component for installment no. "+Math.round(installmentNo)+" = "+p+"<br><br>");
		out.println("The interest component for installment no. "+Math.round(installmentNo)+" = "+i);
		out.println("<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3>");
	}
	
	public void newEMISameTenure(double loanAmount, double interestRate, double tenure, double noPayments, double installmentNo, double newInterest, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		i=0;
		p=0;
		os = loanAmount;
		while(k<=installmentNo){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			os = os - p;
			k++;
		}
		double newEMI = installmentCalculator(os, newInterest, tenure-installmentNo, noPayments);
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 class=\"container\">The EMI value (with new Interest Rate) when the tenure is same = <font color=\"red\">"+newEMI+"</font><br><br>");
		out.println("<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3>");
	}
	
	public void newTenureSameEMI(double loanAmount, double interestRate, double tenure, double noPayments, double installmentNo, double newInterest, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		i=0;
		p=0;
		os = loanAmount;
		while(k<=installmentNo){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			os = os - p;
			k++;
		}
		double temp = installmentAmount/(installmentAmount-((os*newInterest)/1200));
		double newTenure = Math.log(temp)/Math.log((1+(newInterest/1200)));
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">The new tenure in which you can repay the loan with same EMI = "+((int)newTenure+1)+"<br><br>");
		out.println("<br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3>");
	}
	
	public void newRepaymentScheduleSameTenure(double loanAmount, double interestRate, double tenure, double noPayments, double installmentNo, double newInterest, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div align=\"center\" class=\"container\"><br><br><table class=\"table table-striped\"><thead><tr><th>Installment No.</th><th>Outstanding</th><th>Interest</th><th>Principal</th><th>E.M.I</th></tr></thead><tbody>");
		while(k<=installmentNo){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			out.println("<tr><td>"+(k)+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
			os = os - p;
			k++;
		}
		double newInstallmentAmount = (os*(newInterest/1200)*Math.pow((1+(newInterest/1200)), tenure-installmentNo))/(Math.pow((1+(newInterest/1200)), (tenure-installmentNo))-1);
		double i2,p2;
		while(k<=tenure){
			i2=(os*newInterest)/1200;
			p2 = newInstallmentAmount-i2;
			out.println("<tr><td>"+(k)+"</td><td>"+(os)+"</td><td>"+(i2)+"</td><td>"+(p2)+"</td><td>"+(newInstallmentAmount)+"</td></tr>");
			os = os - p2;
			k++;
		}
		out.println("</tbody></table>");
		out.println("<br><h3><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
	}
	
	public void newRepaymentScheduleSameEMI(double loanAmount, double interestRate, double tenure, double noPayments, double installmentChangeNo, double newInterest, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div align=\"center\" class=\"container\"><br><br><table class=\"table table-striped\"><thead><tr><th>Installment No.</th><th>Outstanding</th><th>Interest</th><th>Principal</th><th>E.M.I</th></tr></thead><tbody>");
		while(os>0){
			if(k<=installmentChangeNo) {
				i=(os*interestRate)/1200;
				p = installmentAmount-i;
				out.println("<tr><td>"+(k)+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
				os = os - p;
				k++;
			} else {
				i=(os*newInterest)/1200;
				p = installmentAmount-i;
				out.println("<tr><td>"+(k)+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
				os = os - p;
				k++;
			}
		}
		out.println("</tbody></table>");
		out.println("<br><h3><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
	}
	
	public void newPrincipalComponentSameTenure(double loanAmount, double interestRate, double tenure, double noPayments, double installmentChangeNo, double newInterest, double installmentNo, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		while(k<=installmentChangeNo) {
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			os = os - p;
			k++;
		}
		double newInstallmentAmount = (os*(newInterest/1200)*Math.pow((1+(newInterest/1200)), tenure-installmentNo))/(Math.pow((1+(newInterest/1200)), (tenure-installmentNo))-1);
		i=0;
		p=0;
		os = loanAmount;
		while(k<=installmentNo){
			if(k<=installmentChangeNo) {
				i=(os*interestRate)/1200;
				p = installmentAmount-i;
				os = os - p;
				k++;
			} else {
				i=(os*newInterest)/1200;
				p = newInstallmentAmount-i;
				os = os - p;
				k++;
			}
		}
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">The principal component for installment no. "+Math.round(installmentNo)+" = "+p+"<br><br>");
		out.println("The interest component for installment no. "+Math.round(installmentNo)+" = "+i);
		out.println("<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3>");
	}
	
	public void newPrincipalComponentSameEMI(double loanAmount, double interestRate, double tenure, double noPayments, double installmentChangeNo, double newInterest, double installmentNo, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		i=0;
		p=0;
		while(os>loanAmount && k<=installmentNo){
			if(k<=installmentChangeNo) {
				i=(os*interestRate)/1200;
				p = installmentAmount-i;
				os = os - p;
				k++;
			} else {
				i=(os*newInterest)/1200;
				p = installmentAmount-i;
				os = os - p;
				k++;
			}
		}
		if(k<installmentNo) {
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">Please give valid installment number!<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Try again?</button></a></h3>");
		} else {
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><h3 align=\"center\" class=\"container\">The principal component for installment no. "+Math.round(installmentNo)+" = "+p+"<br><br>");
			out.println("The interest component for installment no. "+Math.round(installmentNo)+" = "+i);
			out.println("<br><br><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3>");
		}
	}
	
	public void prepaymentSchedule(double loanAmount, double interestRate, double tenure, double noPayments, double prepaymentAmount, double prepaymentMonths, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div align=\"center\" class=\"container\"><br><br><table class=\"table table-striped\"><thead><tr><th>Installment No.</th><th>Outstanding</th><th>Interest</th><th>Principal</th><th>E.M.I</th></tr></thead><tbody>");
		out.println("<p><b>Prepayment of <font color=\"red\">Rs. "+prepaymentAmount+"</font> has been paid after <font color=\"red\">"+(int)prepaymentMonths+"</font> months. <font color=\"red\">5% </font>penalty will be issued</p></b><br>");
		while(k<=prepaymentMonths){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			out.println("<tr><td>"+k+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
			os = os - p;
			k++;
		}
		os = (os - prepaymentAmount)+(0.05*prepaymentAmount);
		installmentAmount = (os*(interestRate/1200)*Math.pow((1+(interestRate/1200)), (tenure-prepaymentMonths)))/(Math.pow((1+(interestRate/1200)), (tenure-prepaymentMonths))-1);
		while(k<=tenure){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			out.println("<tr><td>"+k+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
			os = os - p;
			k++;
		}
		out.println("</tbody></table>");
		out.println("<br><h3><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
	}
	
	public void foreclosureSchedule(double loanAmount, double interestRate, double tenure, double noPayments, double foreclosureMonths, PrintWriter out) {
		double installmentAmount;
		installmentAmount = (loanAmount*(interestRate/1200)*Math.pow((1+(interestRate/1200)), tenure))/(Math.pow((1+(interestRate/1200)), tenure)-1);
		int k=1;
		double i,p,os;
		os = loanAmount;
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"><div align=\"center\" class=\"container\"><br><br><table class=\"table table-striped\"><thead><tr><th>Installment No.</th><th>Outstanding</th><th>Interest</th><th>Principal</th><th>E.M.I</th></tr></thead><tbody>");
		while(k<=foreclosureMonths){
			i=(os*interestRate)/1200;
			p = installmentAmount-i;
			out.println("<tr><td>"+k+"</td><td>"+(os)+"</td><td>"+(i)+"</td><td>"+(p)+"</td><td>"+(installmentAmount)+"</td></tr>");
			os = os - p;
			k++;
		}
		out.println("</tbody></table>");
		out.println("<br><p><b>Final payment of<font color=\"red\"> Rs. "+Math.round(os*1.05)+" </font>has to be done after <font color=\"red\">"+(int)foreclosureMonths+" months</font> for closure (<font color=\"red\">5%</font> penalty included).</p></b>");
		out.println("<br><h3><a href=\"index.html\"><button type=\"submit\" class=\"btn btn-success\">Calculate again?</button></a></h3></div>");
	}
}