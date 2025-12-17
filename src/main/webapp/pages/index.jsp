<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


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

		<h3 class="pb-3 pt-3">Report Application</h3>

		<form:form action="search" modelAttribute="searchRequest"
			method="post">

			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${plans}" />
						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
							<form:option value="Other">Other</form:option>

						</form:select></td>
				</tr>
				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>End Date:</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>

				<tr>
					<td rowspan="3"><input type="submit" value="Search"
						class="btn btn-primary" /></td>
				</tr>

			</table>

		</form:form>

		<hr>

		<table class="table table-strip table-hover">
			<thead>
				<tr>
					<td>Id</td>
					<td>Holder Name</td>
					<td>Gender</td>
					<td>Plan Name</td>
					<td>Plan Status</td>
					<td>Start Date</td>
					<td>End Date</td>
					<td>Benefit Amt</td>
				</tr>
			</thead>

			<tbody>


				<c:choose>

					<c:when test="${not empty results}">
						<c:forEach items="${results}" var="p">
							<tr>
								<td>${p.citizenId}</td>
								<td>${p.citizenName}</td>
								<td>${p.gender}</td>
								<td>${p.planName}</td>
								<td>${p.planStatus}</td>
								<td>${p.planStartDate}</td>
								<td>${p.planEndDate}</td>
								<td>${p.benefitAmount}</td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan="8" class="text-center">No Data</td>
						</tr>
					</c:otherwise>

				</c:choose>


			</tbody>


		</table>

		<hr>

		Export:<a href="#">Pdf</a> <a href="#">Excel</a>


	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>
</html>