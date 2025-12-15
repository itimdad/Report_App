<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Generation Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
</head>
<body>
       
       <div class="container">
      
             <h1>Generate Report</h1>
             
             <form:form action="search" modelAttribute="searchRequest" method="get">
                   
                   Plan Name: <form:select path="planName">
                              <form:option value="-Select-"></form:option>
                              <form:options items="${plans}"/>
                   </form:select>
                   Plan Status : <form:select path="planStatus">
                                 <form:option value="-Select-"></form:option>
                                 <form:options items="${status}"/>
                   
                   </form:select>
                   Gender: <form:select path="gender">
                         <form:option value="-Select-"></form:option>
                         <form:option value="Male"></form:option>
                         <form:option value="Fe-Male"></form:option>
                         <form:option value="Other"></form:option>
                         
                   </form:select>
                   <br> <br>
                   Start Date: <form:input path="startDate" type="date"/>
                   End Date: <form:input path="endDate" type="date"/>
                   <br>
                   <br>
                   <form:button class="btn btn-primary">Search</form:button>
             
             </form:form>
             
       
       </div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>
</html>