<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Contacto</title>
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


				<div class="center_title_bar">Contacto</div>
				<div id="contacto">
					<form action="EnviarMail" method="post">
						<label for="mail">E-Mail: </label><input type="text" name="mail"
							id="mail" /> <br></br> <label for="nombre">Nombre:</label><input
							type="text" name="nombre" id="nombre" /> <br></br> <label
							for="asunto">Asunto:</label><input type="text" name="asunto"
							id="asunto" /> <br></br> <label for="mensaje">Mensaje:</label><br></br>
						<textarea rows="8" cols="30" name="mensaje" id="mensaje"></textarea>
						<br></br>
						<button type="submit" value="enviar">Enviar</button>
					</form>
				</div>
				<br></br> <strong>*Por cuestiones del servidor, sólo se
					admiten E-Mails remitentes de GMail</strong>
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