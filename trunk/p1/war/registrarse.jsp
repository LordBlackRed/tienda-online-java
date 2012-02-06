	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />

<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Contact Us</div>

				<div class="prod_box_big">
					<div class="top_prod_box_big"></div>
					<div class="center_prod_box_big">

						<div class="contact_form">
							<form action="Registro" method="post">

								<div class="form_row">
									<label class="contact"><strong>Usuario:</strong></label> <input
										type="text" class="contact_input" name="nombre" />
								</div>

								<div class="form_row">
									<label class="contact"><strong>Contraseña:</strong></label> <input
										type="text" class="contact_input" name="pass" />
								</div>

								<div class="form_row">
									<input type="submit" value="enviar" class="contact" />
								</div>
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