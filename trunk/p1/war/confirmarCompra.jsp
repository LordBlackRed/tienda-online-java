<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Confirmar Compra</title>
<link rel="stylesheet" type="text/css" media="all"
	href="jsDatePick_ltr.min.css" />
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="javascript/scripts.js"></script>
<script type="text/javascript" src="jsDatePick.min.1.3.js"></script>
<script>
	window.onload = function() {
		new JsDatePick({
			useMode : 2,
			target : "fechaN",
			dateFormat : "%d/%m/%Y"
		});
	};
</script>
</head>

<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Datos Facturación</div>
				<div id="stylizedRealizarCompra">
					<form action="RealizarCompra" method="post"
						name="formRealizarCompra">

						<label for="nombreUsuario">Nombre*: </label> <input type="text"
							name="nombreUsuario" value="${sessionScope.usuario.nombre}" /> <label
							for="apellidos">Apellidos*: </label> <input type="text"
							name="apellidos" value="${sessionScope.usuario.apellidos}" /> <label
							for="dni">DNI*: </label> <input type="text" name="dni"
							value="${sessionScope.usuario.dni}" /> <label for="direccion">Direcci&#243;n*:
						</label> <input type="text" name="direccion"
							value="${sessionScope.usuario.direccion}" /> <label
							for="localidad">Localidad*: </label> <input type="text"
							name="localidad" value="${sessionScope.usuario.localidad}" /> <label
							for="provincia">Provincia*: </label><input type="text"
							name="provincia" value="${sessionScope.usuario.provincia}" /><label
							for="cp">C&#243;digo Postal*:</label> <input type="text"
							name="cp" value="${sessionScope.usuario.cp}" /><label
							for="fechaN">Fecha Nacimiento:</label><input type="text"
							name="fechaN" id="fechaN"
							value="${sessionScope.usuario.fechaNacimiento}" /> <label
							for="telFijo">Tel&#233;fono Fijo: </label> <input type="text"
							name="telFijo" value="${sessionScope.usuario.telefonoFijo}" /> <label
							for="telMovil">Tel&#233;fono M&#243;vil*: </label><input
							type="text" name="telMovil"
							value="${sessionScope.usuario.telefonoMovil}" /> <input
							type="hidden" name="precio" value="${requestScope.totalFactura}" />
						<p>*Campos obligatorios</p>
						<button type="submit"
							onclick="return validarDatosCompra(formRealizarCompra);"></button>
					</form>
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