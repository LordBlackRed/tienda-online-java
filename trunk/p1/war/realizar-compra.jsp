<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Electronix Store</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="js/boxOver.js"></script>
<script type='text/javascript'>
function enviarPaypal(){
	document.formTpv.submit();
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


				<div class="center_title_bar">Realizando Compra</div>

				<div class="redireccion">
					<span class="imagen"><img src="images/loading.gif"
						alt="Redireccionando a Paypal" width="32" height="32" /></span> <span class="redireccionar">
						Redireccionando al sistema de pago Paypal, por favor espere. </span>
						
				</div>
		

			</div>
					<form name='formTpv' method='post'
					action='https://www.sandbox.paypal.com/cgi-bin/webscr'>

					<input type='hidden' name='cmd' value='_xclick'> <input
						type='hidden' name='business'
						value='rafabl_1329012870_biz@hotmail.com'> <input
						type='hidden' name='item_name' value='Nueva compra en mi web'>
					<input type='hidden' name='item_number' value='VENTA-X2561'>
					<input type='hidden' name='amount'
						value='${requestScope.totalFactura}'> <input type='hidden'
						name='page_style' value='primary'> <input type='hidden'
						name='no_shipping' value='1'> <input type='hidden'
						name='return' value='http://localhost:8888/Index'> <input
						type='hidden' name='rm' value='2'> <input type='hidden'
						name='cancel_return' value='http://mi_pagina/cancelada.html'>
					<input type='hidden' name='no_note' value='1'> <input
						type='hidden' name='currency_code' value='EUR'> <input
						type='hidden' name='cn' value='PP-BuyNowBF'> <input
						type='hidden' name='custom' value=''> <input type='hidden'
						name='first_name' value='${sessionScope.usuario.nombre}'>
					<input type='hidden' name='last_name'
						value='${sessionScope.usuario.apellidos}'> <input
						type='hidden' name='address1'
						value='${sessionScope.usuario.direccion}'> <input
						type='hidden' name='city'
						value='${sessionScope.usuario.localidad}'> <input
						type='hidden' name='zip' value='${sessionScope.usuario.cp}'>
					<input type='hidden' name='night_phone_a'
						value='${sessionScope.usuario.telefonoFijo}'> <input
						type='hidden' name='night_phone_b'
						value='${sessionScope.usuario.telefonoMovil}'> <input
						type='hidden' name='lc' value='es'> <input type='hidden'
						name='country' value='ES'>

				</form>
				<script>enviarPaypal()</script>

			<!-- end of center content -->

			<jsp:include page="right-content.jsp" />

		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->
	
</body>
</html>