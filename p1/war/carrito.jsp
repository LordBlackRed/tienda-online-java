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
<script type="text/javascript" src="js/boxOver.js"></script>
<script>
	function validar(formulario) {
		if (formulario.empresaEnvio.value == "Elige una Opcion") {
			alert("Debe seleccionar el tipo de envio");
			return false;
		} else {
			return true;
		}
	}
</script>

<script type="text/javascript">
	function ajaxFunction() {
		var xmlHttp;

		try {

			xmlHttp = new XMLHttpRequest();
			return xmlHttp;
		} catch (e) {

			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				return xmlHttp;
			} catch (e) {

				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					return xmlHttp;
				} catch (e) {
					alert("Tu navegador no soporta AJAX!");
					return false;
				}
			}
		}
	}

	function actualizarTotal(formulario) {
		var total = formulario.totalSinEnvio.value;
		if (formulario.empresaEnvio.value == "Elige una Opcion") {
			var ajax;
			ajax = ajaxFunction();
			ajax.open("POST", true);
			ajax.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 1) {
					document.getElementById("totalFactura").innerHTML = " Aguarde por favor...";
				}
				if (ajax.readyState == 4) {
					document.getElementById("totalFactura").innerHTML = "<label>Total: </label>"
							+ total + "&#8364;";
					document.getElementById("empresaEnvio").innerHTML = "<label>Envio: </label>"
							+ 0 + "&#8364;";
				}
			}

			ajax.send(null);

		}
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
							<table border="1">
								<th>Ref</th>
								<th>Nombre</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Total</th>
								<c:set var="totalFactura" value="0" />

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
							onclick=" return validar(formCarrito);"><img
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