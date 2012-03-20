<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />

<title>Detalles Producto</title>
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
<script>
	var productoEliminado = ${requestScope.productoEliminado};
	if (productoEliminado) {
		alert("El producto ha sido eliminado de la Base de Datos");
		 location.href='Index'; 
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
				<c:choose>
					<c:when test="${sessionScope.usuario.nick != 'admin'}">
						<div class="center_title_bar">${requestScope.producto.nombre}</div>

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
									<div class="specifications">
										Disponibilidad: <span class="blue">${requestScope.producto.cantidad}</span><br />
										Descripci&oacute;n: <span class="blue">${requestScope.producto.descripcion}</span><br />
										Fecha de Alta: <span class="blue"> <fmt:formatDate
												pattern="dd-MM-yyyy" value="${requestScope.producto.fecha}" />
										</span>
									</div>
									<div class="prod_price_big">
										<span class="price">${requestScope.producto.precio}
											&#8364;</span>
									</div>


									<a href="AddCarrito?id=${requestScope.producto.id.id}"
										class="addtocart">A&#241;adir al Carro</a>
									<c:if test="${requestScope.error==false}">
										<c:choose>
											<c:when test="${requestScope.fav == false}">
												<a href="NuevoFavorito?id=${requestScope.producto.id.id}"
													class="compare">Favorito</a>
											</c:when>
											<c:otherwise>
												<a href="EliminarFavorito?id=${requestScope.producto.id.id}"
													class="eliminarproducto">Favorito</a>

											</c:otherwise>
										</c:choose>
									</c:if>
								</div>
							</div>
							<div class="bottom_prod_box_big"></div>
						</div>

						<div class="center_title_bar">Productos Similares</div>

						<c:forEach items="${requestScope.productosSimilares}"
							var="productoSimilar">

							<div class="prod_box">
								<div class="top_prod_box"></div>
								<div class="center_prod_box">
									<div class="product_title">
										<a href="details.html">${productoSimilar.nombre}</a>
									</div>
									<div class="product_img">
										<a href="details.html"><img
											src="${productoSimilar.urlImagen}" alt="imagen" title=""
											border="0" width="120" height="150" /></a>
									</div>
									<div class="prod_price">
										<span class="price">${productoSimilar.precio}&#8364;</span>
									</div>
								</div>
								<div class="bottom_prod_box"></div>
								<div class="prod_details_tab">

									<a href="AddCarrito?id=${productoSimilar.id.id}"
										title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
										src="images/cart.gif" alt="" title="" border="0"
										class="left_bt" /></a> <a
										href="AddPuntuacion?id=${productoSimilar.id.id}&megusta=t"
										title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a> <a
										href="AddPuntuacion?id=${productoSimilar.id.id}&megusta=f"
										title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/no_me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a> <a
										href="VerPuntuaciones?id=${productoSimilar.id.id}"
										title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
										src="images/favorites.gif" alt="" title="" border="0"
										class="left_bt" /></a> <a
										href="Detalles?id=${productoSimilar.id.id}"
										class="prod_details">M&#225;s</a>


								</div>
							</div>

						</c:forEach>
					</c:when>
					<c:otherwise>
						<!-- SI ERES ADMINISTRADOR -->
						<form action="ModificarProducto" method="post">

							<div class="center_title_bar">
								<input type="text" name="nombreProducto"
									value="${requestScope.producto.nombre}" />
							</div>

							<div class="prod_box_big">
								<div class="top_prod_box_big"></div>
								<div class="center_prod_box_big">

									<div class="product_img_big">

										<div class="port_img">
											<div class="section">

												<strong>URL: </strong><input type="text" name="urlImagen"
													value="${requestScope.producto.urlImagen}" />

											</div>
											<!-- end .section -->
										</div>
									</div>
									<div class="details_big_box">
										<div class="specifications">
											Disponibilidad: <input type="text" name="cantidad"
												value="${requestScope.producto.cantidad}" /><br />
											Descripci&oacute;n: <input type="text" name="descripcion"
												value="${requestScope.producto.descripcion}" /><br />
											Fecha de Alta: <span class="blue"> <fmt:formatDate
													pattern="dd-MM-yyyy" value="${requestScope.producto.fecha}" />
											</span><br /> Precio: <input type="text" name="precio"
												value="${requestScope.producto.precio}" /><br />
											Categor&#237;a: <select name="categoriaProducto">
												<c:forEach items="${requestScope.categorias}"
													var="categoria">
													<c:choose>
														<c:when
															test="${categoria.titulo==requestScope.producto.categoriaString}">
															<option value="${categoria.titulo}" selected="selected">${categoria.titulo}</option>
														</c:when>
														<c:otherwise>
															<option value="${categoria.titulo}">${categoria.titulo}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>

										</div>
										<input type="hidden" name="idProducto"
											value="${requestScope.producto.id.id}" /> <a
											href="EliminarProducto?id=${requestScope.producto.id.id}"
											class="eliminarproducto">Eliminar</a>
										<button type="submit" value="Modificar"
											class="modificarProducto"></button>


									</div>
								</div>
								<div class="bottom_prod_box_big"></div>
							</div>

							<div class="center_title_bar">Productos Similares</div>

							<c:forEach items="${requestScope.productosSimilares}"
								var="productoSimilar">

								<div class="prod_box">
									<div class="top_prod_box"></div>
									<div class="center_prod_box">
										<div class="product_title">
											<a href="details.html">${productoSimilar.nombre}</a>
										</div>
										<div class="product_img">
											<a href="details.html"><img
												src="${productoSimilar.urlImagen}" alt="imagen" title=""
												border="0" width="120" height="150" /></a>
										</div>
										<div class="prod_price">
											<span class="price">${productoSimilar.precio}&#8364;</span>
										</div>
									</div>
									<div class="bottom_prod_box"></div>
									<div class="prod_details_tab">

										<a href="EliminarProducto?id=${productoSimilar.id.id}"
											title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
											src="images/icono_eliminar.gif" alt="Eliminar Producto"
											title="" border="0" class="left_bt" /></a> <a href="#"
											title="header=[Specials] body=[&nbsp;] fade=[on]"><img
											src="images/favs.gif" alt="" title="" border="0"
											class="left_bt" /></a> <a href="#"
											title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
											src="images/favorites.gif" alt="" title="" border="0"
											class="left_bt" /></a> <a
											href="Detalles?id=${productoSimilar.id.id}"
											class="prod_details">M&#225;s</a>

									</div>
								</div>

							</c:forEach>
						</form>
					</c:otherwise>
				</c:choose>
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