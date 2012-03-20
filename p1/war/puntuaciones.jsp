<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Puntuaciones</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="lightbox.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script src="javascript/prototype.js" type="text/javascript"></script>
<script src="javascript/scriptaculous.js?load=effects,builder"
	type="text/javascript"></script>
<script src="javascript/lightbox.js" type="text/javascript"></script>
</head>
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">

				<div class="center_title_bar">Puntuacion</div>

				<div class="prod_box_big">
					<div class="top_prod_box_big"></div>
					<div class="center_prod_box_big">

						<div class="product_img_big">

							<div class="port_img">
								<div class="section">

									<div class="thumbnail">
										<a href="${requestScope.producto.urlImagen}"
											rel="lightbox[plants]" title="Laptop."><img
											src="${requestScope.producto.urlImagen}" width="115"
											height="80" /></a>
									</div>

								</div>
								<!-- end .section -->
							</div>
						</div>
						<div class="details_big_box">
							<div class="product_title_big">${requestScope.producto.nombre}</div>
							<div class="specifications">
								Puntos Positivos: <span class="blue">
									${requestScope.puntosPositivos}</span><br /> Puntos Negativos: <span
									class="red">${requestScope.puntosNegativos}</span><br />
								Puntos Totales: <span class="gris">${requestScope.puntosTotales}</span><br />
							</div>
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