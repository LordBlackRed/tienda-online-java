<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Envio</title>
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


				<div class="center_title_bar">Entrega</div>

				<div class="envio">
					<p>En el momento de realizar la compra, podr� escoger el m�todo
						de env�o entre los disponibles. Una vez elegido el tipo de env�o,
						actualizamos el carrito y autom�ticamente se modificar� el precio
						total del pedido. La entrega de los pedidos se realizar� en la
						direcci�n indicada en la cuenta personal del usuario registrado o
						la de paypal (en su caso).</p>
					<p>Por norma general, todos los pedidos realizados de lunes a
						viernes con pago mediante tarjeta de cr�dito o Paypal se
						realizar�n siempre al d�a siguiente de haber recibido el pago,
						pero en casos extraordinarios puede retrasarse de dos a tres d�as,
						seg�n el stock de los productos.</p>
					<p>En los pagos mediante transferencia bancaria, el env�o se
						producir� una vez el ingreso se haya hecho efectivo. Si desea
						comprar m�s de un art�culo, le aconsejamos que los pida bajo un
						mismo pedido para no pagar gastos de env�o individualmente para
						cada art�culo.</p>
				</div>
				<div class="center_title_bar">Plazos de Entrega</div>
				<div class="envio">
					<p>-Entrega del 80% en 2-3 d�as laborables.</p>
					<p>-Garantizada la entrega del 100% de los env�os en plazos
						inferiores a 6 d�as.</p>
					<p>IMPORTANTE: En los env�os que tengan como origen y destino
						Canarias, Ceuta y Melilla, las eventuales retenciones aduaneras no
						contar�n a efectos del compromiso de plazo de entrega. No se
						considerar�n h�biles los s�bados, domingos y festivos locales o
						nacionales.</p>
				</div>
				<div class="center_title_bar">Incidencias</div>

				<div class="envio">
					<p>No es posible cambiar la direcci�n de entrega una vez
						realizado el pedido. Los env�os se har�n �nicamente a la direcci�n
						proporcionada como "Direcci�n de entrega" al realizar el pedido (a
						excepci�n de los pedidos pagados por Paypal). Aseg�rate que los
						datos sean correctos y completos, no se realizar�n env�os a
						direcciones proporcionadas por email.</p>
					<p>Aquellos env�os que sean devueltos por negligencia del
						comprador (direcci�n de env�o incompleta o err�nea, paquete
						devuelto tras 15 d�as esperando su recogida, etc.) no ser�n
						reexpedidos hasta que el comprador haya pagado de nuevo los gastos
						de reenv�o.</p>
				</div>
				<div class="center_title_bar">Devoluciones</div>
				<div class="envio">
					<p>Si existe un problema con el art�culo recibido, tiene un
						periodo de 7 d�as para su devoluci�n y pedir un reintegro de la
						cantidad pagada (a excepci�n de los gastos de env�o). Antes de
						proceder con la devoluci�n debe contactar con nosotros para que le
						indiquemos las condiciones y el modo a proceder. Los art�culos
						devueltos deber�n ser enviados por mensajer�a, Correos certificado
						o cualquier otro modo de env�o que disponga de seguimiento.</p>
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