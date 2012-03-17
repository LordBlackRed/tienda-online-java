<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="head.jsp" />
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