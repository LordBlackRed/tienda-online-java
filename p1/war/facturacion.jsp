<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="tiendaonline.enumerados.MisAtributos"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Facturación</title>
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

				<div class="center_title_bar">Facturaci&#243;n</div>
				<c:if test="${requestScope.numFacturas != 0}">
					<table class="lista">
						<tr>
							<th width="1%">N&#186;</th>
							<th>Fecha</th>
							<th>DNI Cliente</th>
							<th>Total</th>
						</tr>
						<c:forEach items="${requestScope.facturas}" var="factura">

							<tr id="pro-3">
								<td><a href="DetalleFactura?id=${factura.numero}">${factura.numero}</a></td>
								<td><fmt:formatDate pattern="dd/MM/yyyy - HH:ss"
										value="${factura.fecha}" /></td>
								<td><c:choose>
										<c:when test="${requestScope.isFacturaUsuarioDni == false}">
											<a href="Facturacion?dni=${factura.usuario.dni}">${factura.usuario.dni}</a>
										</c:when>
										<c:otherwise>
											<a href="Facturacion?dni=${requestScope.facturaUsuarioDni}">${requestScope.facturaUsuarioDni}</a>
										</c:otherwise>
									</c:choose></td>
								<td>${factura.precio}&#8364;</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<c:if
					test="${requestScope.numPaginas !=1 && requestScope.numPaginas !=0}">
					<div id="center_pagination">
						<c:if test="${requestScope.paginaActual != 1}">
							<a href="Facturacion?pag=${requestScope.paginaAnterior}"
								title="Anterior">&#171;</a>
						</c:if>
						<%
							for (int contador = 1; contador <= Integer.parseInt(request
										.getAttribute(MisAtributos.numPaginas.toString())
										.toString()); contador++) {
									out.print("<a href='Facturacion?pag=" + contador + "'>"
											+ contador + "</a>" + "\t");
								}
						%>
						<c:if
							test="${requestScope.paginaActual != requestScope.numPaginas}">
							<a href="Facturacion?pag=${requestScope.paginaSiguiente}"
								title="Siguiente">&#187;</a>
						</c:if>

					</div>
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