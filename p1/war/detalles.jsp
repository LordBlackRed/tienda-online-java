<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Electronix Store</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="lightbox.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<!-- *****JavaScript***** -->
<!-- Galería de imagenes -->
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
				<div class="center_title_bar">Motorola 156 MX-VL</div>

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
								Disponibilitate: <span class="blue">${requestScope.producto.cantidad}</span><br />

								Garantie: <span class="blue">24 luni</span><br /> Tip
								transport: <span class="blue">Mic</span><br /> Pretul include <span
									class="blue">TVA</span><br />
							</div>
							<div class="prod_price_big">
								<span class="reduce">350$</span> <span class="price">${requestScope.producto.precio}$</span>
							</div>

							<c:choose>
								<c:when test="${sessionScope.usuario.nick != 'admin'}">
									<a href="AddCarrito?id=${requestScope.producto.id.id}" class="addtocart">add to cart</a>
									<a href="#" class="compare">compare</a>
								</c:when>

								<c:otherwise>

									<a href="EliminarProducto?id=${requestScope.producto.id.id}"
										class="eliminarproducto">Eliminar Producto</a>
									<a href="#" class="compare">compare</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="bottom_prod_box_big"></div>
				</div>

				<div class="center_title_bar">Similar products</div>

				<div class="prod_box">
					<div class="top_prod_box"></div>
					<div class="center_prod_box">
						<div class="product_title">
							<a href="details.html">Motorola 156 MX-VL</a>
						</div>
						<div class="product_img">
							<a href="details.html"><img src="images/laptop.gif" alt=""
								title="" border="0" /></a>
						</div>
						<div class="prod_price">
							<span class="reduce">350$</span> <span class="price">270$</span>
						</div>
					</div>
					<div class="bottom_prod_box"></div>
					<div class="prod_details_tab">
						<a href="#" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
							src="images/cart.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
							src="images/favs.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
							src="images/favorites.gif" alt="" title="" border="0"
							class="left_bt" /></a> <a href="details.html" class="prod_details">details</a>
					</div>
				</div>

				<div class="prod_box">
					<div class="top_prod_box"></div>
					<div class="center_prod_box">
						<div class="product_title">
							<a href="details.html">Iphone Apple</a>
						</div>
						<div class="product_img">
							<a href="details.html"><img src="images/p4.gif" alt=""
								title="" border="0" /></a>
						</div>
						<div class="prod_price">
							<span class="price">270$</span>
						</div>
					</div>
					<div class="bottom_prod_box"></div>
					<div class="prod_details_tab">
						<a href="#" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
							src="images/cart.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
							src="images/favs.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
							src="images/favorites.gif" alt="" title="" border="0"
							class="left_bt" /></a> <a href="details.html" class="prod_details">details</a>
					</div>
				</div>

				<div class="prod_box">
					<div class="top_prod_box"></div>
					<div class="center_prod_box">
						<div class="product_title">
							<a href="details.html">Samsung Webcam</a>
						</div>
						<div class="product_img">
							<a href="details.html"><img src="images/p5.gif" alt=""
								title="" border="0" /></a>
						</div>
						<div class="prod_price">
							<span class="reduce">350$</span> <span class="price">270$</span>
						</div>
					</div>
					<div class="bottom_prod_box"></div>
					<div class="prod_details_tab">
						<a href="#" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
							src="images/cart.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
							src="images/favs.gif" alt="" title="" border="0" class="left_bt" /></a>
						<a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
							src="images/favorites.gif" alt="" title="" border="0"
							class="left_bt" /></a> <a href="details.html" class="prod_details">details</a>
					</div>
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