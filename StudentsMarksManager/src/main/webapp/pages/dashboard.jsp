<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib prefix="t"
tagdir="/WEB-INF/tags" %>

<t:main-layout>
  <jsp:attribute name="content">
    <h1 style="margin-bottom: 50px; margin-top: 30px; font-weight: bold">Welcome back professor!</h1>

    
    <h2 style="margin-bottom: 20px; font-weight: 500">Dashboard</h2>
    
    <p style="margin-bottom: 20px; max-width: 800px">
    	This is an overview of the total number of students you added, subjects you entered in the platform along with the overall average of all the student's marks
    	 
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
