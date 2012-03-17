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
						Sesion: <strong>${sessionScope.usuario.nick}</strong> <a
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
			${items} Items <br /> <span class="border_cart"></span> Total:
			${suma} </span>
		</div>

		<div class="cart_icon">
			<c:choose>
				<c:when test="${sessionScope.usuario.admin==false}">
					<a href="Carrito" title="Vaya al carrito de la compra!"><img
						src="images/shoppingcart.png" alt="" title="" width="48"
						height="48" border="0" /></a>
				</c:when>
				<c:otherwise>
					<img src="images/shoppingcart.png" alt="" title="" width="48"
						height="48" border="0" />
				</c:otherwise>
			</c:choose>
		</div>

	</div>

	<div class="title_box">¡Nuevo!</div>
	<div class="border_box">
		<div class="product_title">
			<a href="Detalles?id=${requestScope.productoNuevo.id.id}">${requestScope.productoNuevo.nombre}</a>
		</div>
		<div class="product_img">
			<a href="Detalles?id=${requestScope.productoNuevo.id.id}"><img
				src="${requestScope.productoNuevo.urlImagen}" alt="Imagen" title=""
				border="0" width="140" height="120" /></a>
		</div>
		<div class="prod_price">
			<span class="price">${requestScope.productoNuevo.precio}&#8364;</span>
		</div>
	</div>

	<div class="title_box">Fabricantes</div>

	<ul class="left_menu">
		<c:forEach items="${requestScope.fabricantes}" var="fabricante">
			<c:set var="noOfRows" value="${noOfRows + 1 }" />

			<c:choose>
				<c:when test="${noOfRows % 2 == 0}">
					<li class="odd"><a href="Index?fabricante=${fabricante.id}">${fabricante.nombre}</a></li>
				</c:when>
				<c:otherwise>
					<li class="even"><a href="Index?fabricante=${fabricante.id}">${fabricante.nombre}</a></li>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</ul>

	<div class="banner_adds">
		<c:choose>
			<c:when test="${sessionScope.usuario.admin == true}">
				<form action="AddSponsor" method="post">
					<label for="urlRight">Inserta su Dirección web</label><input
						type="text" name="urlRight" id="urlRight" /> <input type="submit"
						value="enviar" />
				</form>
			</c:when>
			<c:otherwise>
				<img src="${requestScope.sponsor[1]}" alt="Sponsor" height="150"
					width="150" title="" border="0" />
			</c:otherwise>
		</c:choose>

	</div>

</div>
<!-- end of right content -->