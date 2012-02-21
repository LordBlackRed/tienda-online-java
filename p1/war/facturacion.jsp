<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="head.jsp" />
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">

				<div class="center_title_bar">Facturación</div>
				<table class="lista">
					<tr>
						<th width="1%">Nº</th>
						<th>Fecha</th>
						<th>DNI Cliente</th>
						<th>Total</th>
					</tr>
					<c:forEach items="${requestScope.facturas}" var="factura">

						<tr id="pro-3">
							<td><a href="DetalleFactura?id=${factura.numero}">${factura.numero}</a></td>
							<td><fmt:formatDate pattern="dd/MM/yyyy - HH:ss" value="${factura.fecha}" /></td>
							<td><a href="Facturacion?dni=${factura.usuario.dni}">${factura.usuario.dni}</a></td>
							<td>${factura.precio}€</td>
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