<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
	<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

			<t:main-layout>
				<jsp:attribute name="content">
					<div class="page-title">
						<h3>
							Marks List:
						</h3>
					</div>

					<c:if test="${data.size() == 0}">
						<div class="no-data">
							<p>No Data Available
							<p>

						</div>
					</c:if>

					<c:if test="${data.size() > 0}">

						<table style="margin-top: 10px" class="container table table-hover">
							<thead class='table-dark'>
								<tr>
									<c:forEach var="col" items="${columns}">

										<th class="marksTH text-capitalize">${col}</th>

									</c:forEach>
									<th style="width: 7%;text-align:center">Edit</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${data}">
									<tr>
										<c:forEach var="value" items="${student}">
											<td class="text-capitalize <c:out value=" ${value.equals('-') ? 'mark_empty' : '' }" />
											">${value}</td>
										</c:forEach>
										<td style="text-align:center"><i style="cursor: pointer;"
												class="fa-solid fa-file-pen text-success" data-toggle="modal"
												data-whatever="${ String.join(',',student) }"
												data-target="#exampleModal"></i></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</c:if>

					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header"
									style="display: flex; align-items: center; justify-content: space-between;">
									<h5 class="modal-title" id="exampleModalLabel"> Editing marks for <span
											id="edit-fn"></span> <span id="edit-ln"></span>
									</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form id="addMarksForm" method="post" action="marks">
										<c:forEach var="col" items="${  columns.subList(3, columns.size() -1)}">

											<div class="form-group">
												<label for="${col}" class="col-form-label">${col}:</label>
												<input name="${col}" type="number" class="form-control markInput"
													id="${col}">
											</div>

										</c:forEach>
										<input type="hidden" name="student_id" id="studentId" />
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
									<button id="addMarksSubmit" type="button" class="btn btn-primary">Edit</button>

								</div>
							</div>
						</div>
					</div>
				</jsp:attribute>
			</t:main-layout>