<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />

<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Carrito de la Compra</div>
				<form action="ActualizarCarrito" method="post">
					<table border="1">
						<th>Ref</th>
						<th>Nombre</th>
						<th>Precio</th>
						<th>Cantidad</th>
						<th>Total</th>

						<c:forEach items="${sessionScope.carrito}" var="producto">
							<c:set var="contador" value="${contador+1}" />
							<c:set var="totalFactura"
								value="${totalFactura + (producto.precio * producto.cantidad)}" />
							<tr>
								<td>${producto.id.id}</td>
								<td>${producto.nombre}</td>
								<td>${producto.precio}</td>

								<td><input type="text" name="cantidad${contador}"
									value="${producto.cantidad}" /></td>
								<td>${producto.precio * producto.cantidad}</td>
								<td><a href="BorrarProductoCarrito?id=${producto.id.id}">Borrar</a></td>
							</tr>

						</c:forEach>

					</table>
					Envío: <select name="empresaEnvio">
						<option>Elige una Opción</option>
						<c:forEach items="${requestScope.empresasEnvio}" var="envio">
							<option value="${envio.id}"  <c:if test="${sessionScope.empresaEnvio.id == envio.id}">selected=selected</c:if>
							 >${envio.empresa}</option>

						</c:forEach>

					</select> <input type="submit" value="Actualizar" />
				</form>
				<c:set var="totalFacturaConEnvio"
					value="${totalFactura + sessionScope.empresaEnvio.precio}" />
				Total: ${totalFacturaConEnvio} <br></br>
				<a href="RealizarCompra?precio=${totalFacturaConEnvio}">Realizar Compra</a>
				
				
			
				
				
				
				
			</div>

			<jsp:include page="right-content.jsp" />

		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->
</body>
</html>