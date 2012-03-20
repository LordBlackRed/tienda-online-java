<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Electronix Store</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="javascript/scripts.js"></script>
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
						alt="Redireccionando a Paypal" width="32" height="32" /></span> <span
						class="redireccionar"> Redireccionando al sistema de pago
						Paypal, por favor espere. </span>
				</div>

			</div>
			<jsp:include page="right-content.jsp" />
		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->

	<jsp:useBean id="today" class="java.util.Date" />
	<c:set var="string" value="${today}" />
	<c:set var="start" value="24" />
	<c:set var="end" value="28" />

	<c:set var="annio" value="${fn:substring(string,start,end)}" />

	<form name='formTpv' method='post'
		action='https://www.sandbox.paypal.com/cgi-bin/webscr'>

		<input type='hidden' name='cmd' value='_cart' /> <input type='hidden'
			name='business' value='rafabl_1329012870_biz@hotmail.com' />
		<c:set var="contador" value="1" />
		<c:forEach items="${sessionScope.carrito}" var="producto">

			<input type='hidden' name='item_name_${contador}'
				value='${producto.nombre}' />
			<input type='hidden' name='item_number_${contador}'
				value='${producto.id.id}' />
			<input type='hidden' name='amount_${contador}'
				value='${producto.precio}' />
			<input type='hidden' name='quantity_${contador}'
				value='${producto.cantidad}' />
			<c:set var="contador" value="${contador+1}" />
		</c:forEach>
		<input type='hidden' name='item_name_${contador}'
			value='Gastos Envio: ${sessionScope.empresaEnvio.empresa}' /> <input
			type='hidden' name='item_number_${contador}'
			value='${sessionScope.empresaEnvio.id}' /> <input type='hidden'
			name='amount_${contador}' value='${sessionScope.empresaEnvio.precio}' />
		<input type='hidden' name='page_style' value='primary' /> <input
			type='hidden' name='return'
			value='http://tiendaonlinejava.appspot.com/' /> <input type='hidden'
			name='rm' value='2' /> <input type='hidden' name='cancel_return'
			value='http://tiendaonlinejava.appspot.com/' /> <input type='hidden'
			name='no_note' value='1' /> <input type='hidden'
			name='currency_code' value='EUR' /> <input type='hidden' name='cn'
			value='PP-BuyNowBF' /> <input type='hidden' name='custom' value='' />
		<input type='hidden' name='first_name'
			value='${sessionScope.usuario.nombre}' /> <input type='hidden'
			name='last_name' value='${sessionScope.usuario.apellidos}' /> <input
			type='hidden' name='address1'
			value='${sessionScope.usuario.direccion}' /> <input type='hidden'
			name='city' value='${sessionScope.usuario.localidad}' /> <input
			type='hidden' name='zip' value='${sessionScope.usuario.cp}' /> <input
			type='hidden' name='night_phone_a'
			value='${sessionScope.usuario.telefonoFijo}' /> <input type='hidden'
			name='night_phone_b' value='${sessionScope.usuario.telefonoMovil}' />
		<input type='hidden' name='lc' value='es' /> <input type='hidden'
			name='country' value='ES' /> <input type="hidden" name="upload"
			value="1" />
	</form>
	<script>
		enviarPaypal(formTpv);
	</script>

	<!-- end of center content -->
</body>
</html>