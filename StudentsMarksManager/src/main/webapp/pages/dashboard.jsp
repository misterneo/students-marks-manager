<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib prefix="t"
tagdir="/WEB-INF/tags" %>

<t:main-layout>
  <jsp:attribute name="content">
    <h1>Welcome to Students Marks Manager</h1>

    <p style="margin-bottom: 30px">
      This is a web-based platform designed to help teachers manage student
      marks. With our easy-to-use system, you can easily input, track and
      analyze student grades, allowing you to better understand each student's
      academic progress. The Student Marks Manager provides a simple and intuitive user
      interface that makes it easy to manage student information, and grading
      criteria.
    </p>

    <div class="row">
      <div class="col-md-4" style="margin-bottom: 20px">
        <div class="card-counter info">
          <i class="fa fa-users"></i>
          <span class="count-numbers">${totalStudents}</span>
          <span class="count-name">Students</span>
        </div>
      </div>

      <div class="col-md-4" style="margin-bottom: 20px">
        <div class="card-counter danger">
          <i class="fa fa-book"></i>
          <span class="count-numbers">${totalSubjects}</span>
          <span class="count-name">Subjects</span>
        </div>
      </div>

      <div class="col-md-4" style="margin-bottom: 20px">
        <div class="card-counter success">
          <i class="fa fa-pen"></i>
          <span class="count-numbers">${overallAvgScore} / 20</span>
          <span class="count-name">Average Score</span>
        </div>
      </div>
    </div>
  </jsp:attribute>
</t:main-layout>
