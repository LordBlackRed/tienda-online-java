<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">

				<div class="center_title_bar">Latest Products</div>

				<c:forEach items="${requestScope.productosCategoria}" var="producto">
				
				<div class="prod_box">
						<div class="top_prod_box"></div>
						<div class="center_prod_box">
							<div class="product_title">
								<a href="details.html">${producto.nombre}</a>
							</div>
							<div class="product_img">
								<a href="details.html"><img src="${producto.urlImagen}"
									width="120" height="150" alt="" title="" border="0" /></a>
							</div>
							<div class="prod_price">
								<span class="price">${producto.precio}</span>
							</div>
						</div>
						<div class="bottom_prod_box"></div>
						<div class="prod_details_tab">
							<c:choose>
								<c:when test="${sessionScope.usuario.nombre != 'admin'}">

									<a href="AddCarrito?id=${producto.id.id}"
										title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
										src="images/cart.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="AddPuntuacion?id=${producto.id.id}&megusta=t" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="AddPuntuacion?id=${producto.id.id}&megusta=f" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/no_me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
										src="images/favorites.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="Detalles?id=${producto.id.id}" class="prod_details">details</a>

								</c:when>

								<c:otherwise>
									<a href="EliminarProducto?id=${producto.id.id}"
										title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
										src="images/icono_eliminar.gif" alt="Eliminar Producto"
										title="" border="0" class="left_bt" /></a>
									<a href="#" title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/favs.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="#" title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
										src="images/favorites.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="Detalles?id=${producto.id.id}" class="prod_details">details</a>

								</c:otherwise>
							</c:choose>
						</div>
					</div>
				
				</c:forEach>
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