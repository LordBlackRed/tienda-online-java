<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Detalles Factura</title>
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

				<div class="center_title_bar">Factura Nº
					${requestScope.idFactura}</div>
				<table class="lista">
					<tr>
						<th width="1%">Producto</th>
						<th>Cantidad</th>
						<th>Precio</th>
					</tr>
					<c:forEach items="${requestScope.lineasFactura}" var="lineaFactura">
						<tr id="pro-3">
							<td><a href="Detalles?id=${lineaFactura.idProducto}">${lineaFactura.idProducto}</a></td>
							<td>${lineaFactura.cantidad}</td>
							<td>${lineaFactura.precio}&#8364;</td>
						</tr>
					</c:forEach>
				</table>

				<table class="lista">
					<tr>
						<th>Env&#237;o</th>
						<th>Precio</th>
					</tr>
					<tr>
						<td>${requestScope.nombreEmpresaEnvio}</td>
						<td>${requestScope.precioEnvio}&#8364;</td>
					</tr>
				</table>
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