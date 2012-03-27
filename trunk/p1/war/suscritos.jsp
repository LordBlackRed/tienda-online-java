<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Suscritos</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
</head>
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">

				<div class="center_title_bar">Suscritos</div>
				<c:if test="${requestScope.numeroSuscritos !=0}">
					<table class="lista">
						<c:set var="noOfRows" value="${0}" />
						<c:forEach items="${requestScope.suscritos}" var="suscrito">
							<c:if test="${noOfRows % 2 == 0}">
								<tr>
							</c:if>
							<td>${suscrito.mail}</td>
							<c:if test="${noOfRows % 2 != 0}">
								</tr>
							</c:if>
							<c:set var="noOfRows" value="${noOfRows + 1}" />
						</c:forEach>
					</table>
				</c:if>
			</div>

			<!-- end of center content -->

			<jsp:include page="right-content.jsp" />

		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->
</body>
</html>