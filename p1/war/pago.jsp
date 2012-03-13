<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="head.jsp" />
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">


				<div class="center_title_bar">Formas de pago</div>
				<div class="privacidad">
					<p>De momento y por cuestiones de seguridad Electronix acepta
						sólamente el método de pago <strong>Paypal</strong></p>
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