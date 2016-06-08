<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page isELIgnored="false" %>
</head>
<body>
<div align="center">
        <form:form action="registartion" method="post" commandName="registrationForm">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Police Station - Registration</h2></td>
                </tr>
  
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td>Station Area:</td>
                    <td><form:input path="stationArea" /></td>
                </tr>
                <tr>
                    <td>Circle:</td>
                    <td><form:input path="circle" /></td>
                </tr>
                 <tr>
                    <td>State:</td>
                    <td><form:input path="state" /></td>
                </tr>
                  <tr>
                    <td>District:</td>
                    <td><form:input path="district" /></td>
                </tr>
                 <tr>
                    <td>Pin:</td>
                    <td><form:input path="pin" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>