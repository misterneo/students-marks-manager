<%@tag description="Template" pageEncoding="UTF-8"%>
<%@attribute name="content" fragment="true"%>

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title}</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/pages/assets/style.css" />
</head>

<body>
<div class="bg-pattern">
</div>
	<%@include file="layouts/header.jsp"%>
	<div id="wrapper">

		<%@include file="layouts/sidebar.jsp"%>

		<div id="page-content-wrapper">
			<div class="container-fluid xyz">
				<div class="row">
					<div class="col-lg-12">
						<jsp:invoke fragment="content" />
					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/pages/assets/script.js"></script>
</body>
</html>