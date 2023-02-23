<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
  <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

      <t:main-layout>
        <jsp:attribute name="content">
          <div class="page-title">
            <h3>
              Subjects List:
            </h3>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal"><i
                class="fa-solid fa-user-plus" style="margin-right: 5px;"></i>Add Subject</button>
          </div>

          <c:if test="${subjects.size() == 0}">
            <div class="no-data">
              <p>No Data Available
              <p>
            </div>
          </c:if>

          <c:if test="${subjects.size() > 0}">
            <table style="margin-top: 10px" class="container table table-hover">
              <thead class='table-dark'>
                <tr>
                  <th style="width:10%;"># ID</th>
                  <th>Name</th>
                  <th>Coefficient</th>
                  <th style="width:5%; text-align:center">Delete</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="subject" items="${subjects}">
                  <tr>
                    <td>${subject.getId()}</td>
                    <td class="text-capitalize">${subject.getName()}</td>
                    <td>${subject.getCoefficient()}</td>
                    <td style="text-align:center">
                      <form autocomplete="off" class="col" method="post" action="StudentController">
                        <input type="hidden" name="id" value="${subject.getId()}" />

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
                  <h5 class="modal-title" id="exampleModalLabel"> Add new subject</h5>
                  <input class="hasError" type="hidden" value="${error}">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <form id="addSubjectForm" method="post" action="subjects">
                    <div class="form-group">
                      <label for="name" class="col-form-label">Subject Name:</label>
                      <input name="name" type="text" class="form-control" id="name">
                    </div>

                    <div class="form-group">
                      <label for="coef" class="col-form-label">Coefficient:</label>
                      <input name="coef" type="number" class="form-control" id="coef">
                    </div>

                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button id="addSubjectSubmit" type="button" class="btn btn-primary">Add</button>
                </div>
              </div>
            </div>
          </div>
        </jsp:attribute>
      </t:main-layout>