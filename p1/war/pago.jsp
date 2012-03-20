<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Pago</title>
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


				<div class="center_title_bar">Formas de pago</div>
				<div class="privacidad">
					<p>
						De momento y por cuestiones de seguridad Electronix acepta
						sólamente el método de pago <strong>Paypal</strong>
					</p>
					<p>El sistema Paypal.es es un sistema de pago muy utilizado en
						todo el mundo para pagar tus compras de forma segura.</p>
					<p>Las compras se realizan sin facilitar los datos de tu
						tarjeta, pudiendo comprar en más de 100.000 sitios webs de todo el
						mundo y todo el proceso se realiza en unos pocos segundos. Es un
						servicio de pago seguro y rápido.</p>
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