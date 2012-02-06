<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="right_content">

	<!-- Si el usuario no se ha logueado muestra el formulario de loguin -->
	<p>
		<c:choose>
			<c:when test="${empty sessionScope.usuario}">
				<div class="shopping_cart_login">
					<form action="Login" method="post">

						<p>
							<label for="nombre"><strong>Usuario:</strong></label> <input
								type="text" name="nombre" id="nombre" /> <label for="pass"><strong>Contraseña:</strong></label>
							<input type="password" name="pass" id="pass" size="17" />
						</p>
						<p>
							<input type="submit" value="enviar" />
						</p>
					</form>
				</div>
			</c:when>

			<c:otherwise>
				<div class="shopping_cart_sesion">
					<br>
					<p>
						Sesion: <strong>${sessionScope.usuario.nombre}</strong> <a
							href="CerrarSesion">Salir</a>
					</p>
					</br>
				</div>
			</c:otherwise>
		</c:choose>
	</p>



	<div class="shopping_cart">
		<div class="cart_title">Shopping cart</div>

		<div class="cart_details">

			<c:forEach items="${sessionScope.carrito}" var="producto">
				<c:set var="items" value="${items+producto.cantidad}" />

				<span class="price"> <c:set var="suma"
						value="${suma+(producto.cantidad*producto.precio)}" />
			</c:forEach>
			${items} items <br /> <span class="border_cart"></span> Total:
			${suma} </span>
		</div>

		<div class="cart_icon">
			<a href="Carrito" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img
				src="images/shoppingcart.png" alt="" title="" width="48" height="48"
				border="0" /></a>
		</div>

	</div>

	<div class="title_box">Whatï¿½s new</div>
	<div class="border_box">
		<div class="product_title">
			<a href="details.html">Motorola 156 MX-VL</a>
		</div>
		<div class="product_img">
			<a href="details.html"><img src="images/p2.gif" alt="" title=""
				border="0" /></a>
		</div>
		<div class="prod_price">
			<span class="reduce">350$</span> <span class="price">270$</span>
		</div>
	</div>

	<div class="title_box">Manufacturers</div>

	<ul class="left_menu">
		<c:forEach items="${requestScope.fabricantes}" var="fabricante">
			<c:set var="noOfRows" value="${noOfRows + 1 }" />

			<c:choose>
				<c:when test="${noOfRows % 2 == 0}">
					<li class="odd"><a href="services.html">${fabricante.nombre}</a></li>
				</c:when>
				<c:otherwise>
					<li class="even"><a href="services.html">${fabricante.nombre}</a></li>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</ul>

	<div class="banner_adds">

		<a href="#"><img src="images/bann1.jpg" alt="" title="" border="0" /></a>
	</div>

</div>
<!-- end of right content -->