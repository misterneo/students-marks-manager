$("#exampleModal").on("show.bs.modal", function (event) {
  var button = $(event.relatedTarget);
  var recipient = button.data("whatever");

  var modal = $(this);
  if (recipient) {
    recipient = recipient.split(",");

    for (var i = 0; i < recipient.length - 4; i++) {
      $(".markInput")[i].value = recipient[i + 3];
    }

    modal.find("#edit-fn").html(recipient[1]);
    modal.find("#edit-ln").html(recipient[2]);
    modal.find("#studentId").val(recipient[0]);
  }
});

$("#addMarksSubmit").click(function (e) {
  $("#addMarksForm").submit();
});

$("#addStudentSubmit").click(function (e) {
  $("#addStudentForm").submit();
});

$("#addSubjectSubmit").click(function (e) {
  $("#addSubjectForm").submit();
});

$("#menu-toggle").click(function (e) {
  e.preventDefault();
  $("#wrapper").toggleClass("toggled");
});
$("#menu-toggle-2").click(function (e) {
  e.preventDefault();
  $("#wrapper").toggleClass("toggled-2");
  $("#menu ul").hide();
});

function initMenu() {
  $("#menu ul").hide();
  $("#menu ul").children(".current").parent().show();
  $("#menu li a").click(function () {
    var checkElement = $(this).next();
    if (checkElement.is("ul") && checkElement.is(":visible")) {
      return false;
    }
    if (checkElement.is("ul") && !checkElement.is(":visible")) {
      $("#menu ul:visible").slideUp("normal");
      checkElement.slideDown("normal");
      return false;
    }
  });
}
$(document).ready(function () {
  initMenu();

  try {
    var error = $(".hasError")[0];
    if (error.value == "badInputs") {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Invalid inputs!",
        confirmButtonColor: "#337ab7",
      });
    }
  } catch (e) {}

  try {
    var th = $(".marksTH");

    for (var i = 0; i < th.length; i++) {
      th[i].innerHTML = th[i].innerHTML.replace("_", " ");
      if (th[i].innerHTML.includes("| ")) {
        th[i].innerHTML = th[i].innerHTML.split("| ")[1];
        th[i].innerHTML = th[i].innerHTML.split(" (")[0];
      }
    }
  } catch (e) {
    console.log(e);
  }
});
