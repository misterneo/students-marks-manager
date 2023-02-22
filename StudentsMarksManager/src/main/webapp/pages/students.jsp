<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
  <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

      <t:main-layout>

        <jsp:attribute name="content">
          <div class="page-title">
            <h3>
              Students List:
            </h3>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"><i
                class="fa-solid fa-user-plus" style="margin-right: 5px;"></i>Add Student</button>
          </div>

          <c:if test="${students.size() == 0}">
            <div class="no-data">
              <p>No Data Available
              <p>
            </div>
          </c:if>

          <c:if test="${students.size() > 0}">
            <table style="margin-top: 10px" class="container table table-hover">
              <thead class='table-dark'>
                <tr>
                  <th># ID</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th style="width:5%; text-align:center">Delete</th>
                </tr>
              </thead>
              <tbody>

                <c:forEach var="student" items="${students}">
                  <tr>
                    <td>${student.getId()}</td>
                    <td class="text-capitalize">${student.getFirst_name()}</td>
                    <td class="text-capitalize">${student.getLast_name()}</td>
                    <td>${student.getEmail()}</td>
                    <td style="text-align:center">
                      <form autocomplete="off" class="col" method="post" action="StudentController">
                        <input type="hidden" name="id" value="${student.getId()}" />

                        <button id="delete-student-btn" data-toggle="tooltip" data-placement="bottom" title="Delete"
                          type="submit" class="btn btn-sm">
                          <i class="bi bi-trash" style="color: red"></i>
                        </button>
                      </form>

                    </td>
                  </tr>
                </c:forEach>

              </tbody>
            </table>
          </c:if>


          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header" style="display: flex; align-items: center; justify-content: space-between;">
                  <h5 class="modal-title" id="exampleModalLabel"> Add new student</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form id="addStudentForm" method="post" action="students">
                    <div class="form-group">
                      <label for="first_name" class="col-form-label">First Name:</label>
                      <input name="fn" type="text" class="form-control" id="first_name" required>
                    </div>
                    <div class="form-group">
                      <label for="last_name" class="col-form-label">Last Name:</label>
                      <input name="ln" type="text" class="form-control" id="last_name" required>
                    </div>
                    <div class="form-group">
                      <label for="email" class="col-form-label">Email:</label>
                      <input name="email" type="email" class="form-control" id="email" required>
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button id="addStudentSubmit" type="button" class="btn btn-primary">Add</button>
                </div>
              </div>
            </div>
          </div>
        </jsp:attribute>
      </t:main-layout>