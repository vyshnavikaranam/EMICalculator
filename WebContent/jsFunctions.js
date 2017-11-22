function principalComponent() {
	document.getElementById("installmentField").style.display="block";
	document.getElementById("installmentNo").required=true;
	document.getElementById("submitButton").style.display="inline";
	document.getElementById("cemi").style.display="none";
	document.getElementById("rs").style.display="none";
	document.getElementById("pc").style.display="none";
}
function floating() {
	document.getElementById("floating1").style.display="block";
	document.getElementById("floating2").style.display="block";
	document.getElementById("floating3").style.display="block";
	document.getElementById("newInterest").required=true;
	document.getElementById("floatingRadio1").required=true;
	document.getElementById("floatingRadio2").required=true;
	sameTenure();
}
function fixed() {
	document.getElementById("floating1").style.display="none";
	document.getElementById("floating2").style.display="none";
	document.getElementById("floating3").style.display="none";
	document.getElementById("newInterest").required=false;
	document.getElementById("floatingRadio1").required=false;
	document.getElementById("floatingRadio2").required=false;
	sameTenure();
}
function resetAll() {
	fixed();
	document.getElementById("installmentField").style.display="none";
	document.getElementById("installmentNo").required=false;
	document.getElementById("cemi").style.display="inline";
	document.getElementById("rs").style.display="inline";
	document.getElementById("pc").style.display="inline";
	document.getElementById("submitButton").style.display="none";
}
function sameEMI() {
	document.getElementById("cemi").value="Calculate Tenure";
}
function sameTenure() {
	document.getElementById("cemi").value="Calculate EMI";
}