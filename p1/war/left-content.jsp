<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="string"
	value="${requestScope['javax.servlet.forward.servlet_path']}" />
<c:set var="start" value="1" />
<c:set var="end" value="${fn:length(string)}" />

<div class="crumb_navigation">
	Actual: <span class="current"> ${fn:substring(string,start,end)}</span>
</div>

<div class="left_content">
	<div class="title_box">Categor&#237;as</div>

	<ul class="left_menu">

		<c:forEach items="${requestScope.categorias}" var="categoria">
			<c:set var="noOfRows" value="${noOfRows + 1 }" />

			<c:choose>
				<c:when test="${noOfRows % 2 == 0}">
					<li class="odd"><a href="Index?categoria=${categoria.titulo}">${categoria.titulo}</a></li>
				</c:when>
				<c:otherwise>
					<li class="even"><a href="Index?categoria=${categoria.titulo}">${categoria.titulo}</a></li>
				</c:otherwise>
			</c:choose>

		</c:forEach>
	</ul>

	<div class="title_box">Producto Especial</div>
	<div class="border_box">
		<c:choose>
			<c:when test="${sessionScope.usuario.admin == true}">
				<div class="product_title">Elige un Producto</div>

				<div class="product_img">
					<form action="ElegirProductoEspecial" method="post"
						name="formElegirProductoEspecial">
						<select name="productoEspecial">
							<option value="defecto">Elige una Opción</option>
							<c:forEach items="${requestScope.productos}" var="producto">
								<option value="${producto.id.id}">${producto.nombre}</option>
							</c:forEach>
						</select> <input type="submit" value="enviar"
							onclick="return validarSelect(formElegirProductoEspecial.productoEspecial);" />
					</form>
				</div>

			</c:when>
			<c:otherwise>
				<div class="product_title">
					<a href="Detalles?id=${requestScope.productoEspecial.id.id}">${requestScope.productoEspecial.nombre}</a>
				</div>
				<a href="Detalles?id=${requestScope.productoEspecial.id.id}"><img
					src="${requestScope.productoEspecial.urlImagen}"
					alt="Imagen producto Especial" title="" border="0" width="140"
					height="120" /></a>
				<div class="prod_price">
					<span class="price">${requestScope.productoEspecial.precio}</span>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="title_box">Suscríbete</div>
	<div class="border_box">
		<form action="Suscribir" method="post">
			<input type="text" name="mail" class="newsletter_input"
				value="Tu email" />
			<button type="submit" class="join">Suscribir</button>
		</form>
	</div>

	<div class="banner_adds">

		<c:choose>
			<c:when test="${sessionScope.usuario.admin == true}">
				<form action="AddSponsor" method="post">
					<label for="urlLeft">Inserta su Dirección web</label><input
						type="text" name="urlLeft" id="urlLeft" /> <input type="submit"
						value="enviar" />
				</form>
			</c:when>
			<c:otherwise>
				<img src="${requestScope.sponsor[0]}" alt="Sponsor" height="200"
					width="150" title="" border="0" />
			</c:otherwise>
		</c:choose>
	</div>

</div>
<!-- end of left content -->