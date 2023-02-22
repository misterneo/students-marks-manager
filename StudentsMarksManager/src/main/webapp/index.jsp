<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/pages/assets/style.css" />
</head>

<body>
	<div class="bg-pattern"></div>
	<section class="heroSection">

		<div class="row hero-container">
			<div class="col-lg-12 hero-content">
				<img class="hero-image"
					src="${pageContext.request.contextPath}/pages/assets/hero.svg"
					alt="hero image" />

				<h1>Welcome to Students Marks Manager</h1>

				<p style="margin-top: 20px">This is a web-based platform
					designed to help teachers manage student marks. With our
					easy-to-use system, you can easily input, track and analyze student
					grades, allowing you to better understand each student's academic
					progress. The Student Marks Manager provides a simple and intuitive
					user interface that makes it easy to manage student information,
					and grading criteria.</p>

				<a href="dashboard" class="btn-get-started">Get Started</a>
			</div>
		</div>

	</section>

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/pages/assets/script.js"></script>
</body>
</html>