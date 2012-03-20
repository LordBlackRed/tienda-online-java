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
					<p>En el momento de realizar la compra, podrá escoger el método
						de envío entre los disponibles. Una vez elegido el tipo de envío,
						actualizamos el carrito y automáticamente se modificará el precio
						total del pedido. La entrega de los pedidos se realizará en la
						dirección indicada en la cuenta personal del usuario registrado o
						la de paypal (en su caso).</p>
					<p>Por norma general, todos los pedidos realizados de lunes a
						viernes con pago mediante tarjeta de crédito o Paypal se
						realizarán siempre al día siguiente de haber recibido el pago,
						pero en casos extraordinarios puede retrasarse de dos a tres días,
						según el stock de los productos.</p>
					<p>En los pagos mediante transferencia bancaria, el envío se
						producirá una vez el ingreso se haya hecho efectivo. Si desea
						comprar más de un artículo, le aconsejamos que los pida bajo un
						mismo pedido para no pagar gastos de envío individualmente para
						cada artículo.</p>
				</div>
				<div class="center_title_bar">Plazos de Entrega</div>
				<div class="envio">
					<p>-Entrega del 80% en 2-3 días laborables.</p>
					<p>-Garantizada la entrega del 100% de los envíos en plazos
						inferiores a 6 días.</p>
					<p>IMPORTANTE: En los envíos que tengan como origen y destino
						Canarias, Ceuta y Melilla, las eventuales retenciones aduaneras no
						contarán a efectos del compromiso de plazo de entrega. No se
						considerarán hábiles los sábados, domingos y festivos locales o
						nacionales.</p>
				</div>
				<div class="center_title_bar">Incidencias</div>

				<div class="envio">
					<p>No es posible cambiar la dirección de entrega una vez
						realizado el pedido. Los envíos se harán únicamente a la dirección
						proporcionada como "Dirección de entrega" al realizar el pedido (a
						excepción de los pedidos pagados por Paypal). Asegúrate que los
						datos sean correctos y completos, no se realizarán envíos a
						direcciones proporcionadas por email.</p>
					<p>Aquellos envíos que sean devueltos por negligencia del
						comprador (dirección de envío incompleta o errónea, paquete
						devuelto tras 15 días esperando su recogida, etc.) no serán
						reexpedidos hasta que el comprador haya pagado de nuevo los gastos
						de reenvío.</p>
				</div>
				<div class="center_title_bar">Devoluciones</div>
				<div class="envio">
					<p>Si existe un problema con el artículo recibido, tiene un
						periodo de 7 días para su devolución y pedir un reintegro de la
						cantidad pagada (a excepción de los gastos de envío). Antes de
						proceder con la devolución debe contactar con nosotros para que le
						indiquemos las condiciones y el modo a proceder. Los artículos
						devueltos deberán ser enviados por mensajería, Correos certificado
						o cualquier otro modo de envío que disponga de seguimiento.</p>
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