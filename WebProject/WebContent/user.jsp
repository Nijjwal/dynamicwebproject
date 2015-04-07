<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table border="1px">
 <%
//Get total number of rows and cols
 Object rows = request.getAttribute("rows");
 int r = (Integer) rows; //cast an object to integer
 

 Object cols = request.getAttribute("cols");
 int c = (Integer) cols;




String[][] printArray = (String[][])session.getAttribute("userDetail");


for(int i=0; i<r; i++)
{
	out.println("<tr>"); 
	for(int j=0; j<c; j++)
	{
	   out.println("<td>");
	   	out.println(printArray[i][j]);
	   out.println("</td>");
	}
	out.println("</tr>"); 
	//out.println("<br/>");
}
 %>
 
 </table>
	

</body>
</html>