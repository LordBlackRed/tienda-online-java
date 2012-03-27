<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Carrito de la Compra</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script src="javascript/scripts.js" type="text/javascript"></script>
<script>
var error = ${requestScope.error};
if (error == true) {
	alert("¡No tenemos Stock suficiente! Por favor, revisa la disponibilidad de los productos");
}
</script>
</head>

<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Carrito de la Compra</div>
				<div id="carrito">
					<c:if test="${fn:length(sessionScope.carrito)!=0}">
						<form action="ActualizarCarrito" method="post" name="formCarrito">
							<table class="lista">
							<tr>
								<th>Ref</th>
								<th>Nombre</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Total</th>
								</tr>
								<c:set var="totalFactura" value="0" />

								<c:forEach items="${sessionScope.carrito}" var="producto">
									<c:set var="contador" value="${contador+1}" />
									<c:set var="totalFactura"
										value="${totalFactura + (producto.precio * producto.cantidad)}" />
									<tr id="pro-3">
										<td><a href="Detalles?id=${producto.id.id}">${producto.id.id}</a></td>
										<td width="165">${producto.nombre}</td>
										<td>${producto.precio}&#8364;</td>

										<td><input type="text" name="cantidad${contador}"
											value="${producto.cantidad}" /></td>
										<td>${producto.precio * producto.cantidad}&#8364;</td>
										<td width="20"><div class="borrarProductoCarrito"><a href="BorrarProductoCarrito?id=${producto.id.id}"></a></div></td>
									</tr>

								</c:forEach>

							</table>
							<br></br> <strong>Envío:</strong> <select name="empresaEnvio"
								onclick="actualizarTotal(formCarrito)">
								<option value="Elige una Opcion">Elige una Opción</option>
								<c:forEach items="${requestScope.empresasEnvio}" var="envio">
									<option value="${envio.id}" hidden="${envio.precio}"
										<c:if test="${sessionScope.empresaEnvio.id == envio.id}">selected=selected</c:if>>${envio.empresa}</option>

								</c:forEach>

							</select> <br></br>
							<div class="actualizar">
								<input type="submit" value="Actualizar" />
							</div>
							<br></br> <input type="hidden" name="totalSinEnvio"
								value="${totalFactura}" />
						</form>

						<div id="empresaEnvio">
							<label>Envio: </label>
							<c:choose>
								<c:when test="${empty sessionScope.empresaEnvio.precio}">0 </c:when>
								<c:otherwise>${sessionScope.empresaEnvio.precio}</c:otherwise>
							</c:choose>
							&#8364;
						</div>
						<c:set var="totalFacturaConEnvio"
							value="${totalFactura + sessionScope.empresaEnvio.precio}" />

						<div id="totalFactura">
							<label>Total: </label> ${totalFacturaConEnvio} &#8364;
						</div>
						<br></br>

						<a href="ConfirmarCompra?precio=${totalFactura}"
							onclick=" return validarCarrito(formCarrito, '${sessionScope.empresaEnvio.precio}');"><img
							src="images/next.png" /> </a>
					</c:if>
				</div>
			</div>

			<jsp:include page="right-content.jsp" />

		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->
</body>
</html>