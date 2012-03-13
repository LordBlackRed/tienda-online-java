
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Registrar Usuario</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="js/boxOver.js"></script>
<script>
	var error = ${requestScope.error};
	if (error == true) {
		alert("El usuario introducido ya existe");
	}
	function validar(formulario) {
		var nombre = trim(formulario.nombre.value);
		var pass = trim(formulario.pass.value);

		if (nombre == "" || pass == "") {
			alert("Todos los campos obligatorios no han sido rellenados");
			return false;
		} else {
			return true;
		}
	}

	function trim(myString) {
		return myString.replace(/^\s+/g, '').replace(/\s+$/g, '')
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
				<div class="center_title_bar">Registrar Usuario</div>

				<div class="prod_box_big">
					<div class="top_prod_box_big"></div>
					<div class="center_prod_box_big">

						<div id="contact_form">
							<form action="Registro" method="post" name="formRegistro">

								<label class="contact"><strong>Usuario:</strong></label> <input
									type="text" class="contact_input" name="nombre" id="nombre" /><br></br>
								<label class="contact"><strong>Contrase&#241;a:</strong></label>
								<input type="password" class="contact_input" name="pass"
									id="pass" /><br></br>

								<button type="submit" onclick=" return validar(formRegistro);">Enviar</button>
							</form>

						</div>

					</div>
					<div class="bottom_prod_box_big"></div>
				</div>

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