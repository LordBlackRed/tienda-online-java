<%@page import="tiendaonline.ServletIndex"%>
<%@page import="tiendaonline.listeners.ContextoListener"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<script src="javascript/scripts.js" type="text/javascript"></script>

<script type="text/javascript">
	var photos = new Array();
	var which = 0;
	var texto = new Array();
	var nombreProductos = new Array();

	<%if (ContextoListener.productosCabecera.get(0).getDescripcion() != null) {%>
	texto[1] = "<%=ContextoListener.productosCabecera.get(0)
						.getDescripcion()%>";
	texto[2] = "<%=ContextoListener.productosCabecera.get(1)
						.getDescripcion()%>";
	texto[3] = "<%=ContextoListener.productosCabecera.get(2)
						.getDescripcion()%>";
	texto[4] = "<%=ContextoListener.productosCabecera.get(3)
						.getDescripcion()%>";
	texto[5] = "<%=ContextoListener.productosCabecera.get(4)
						.getDescripcion()%>";
	nombreProductos[1] = "<%=ContextoListener.productosCabecera.get(0).getNombre()%>";
	nombreProductos[2] = "<%=ContextoListener.productosCabecera.get(1).getNombre()%>";
	nombreProductos[3] = "<%=ContextoListener.productosCabecera.get(2).getNombre()%>";
	nombreProductos[4] = "<%=ContextoListener.productosCabecera.get(3).getNombre()%>";
	nombreProductos[5] = "<%=ContextoListener.productosCabecera.get(4).getNombre()%>";
	photos[1] = "<%=ContextoListener.productosCabecera.get(0)
						.getUrlImagen()%>";
	photos[2] = "<%=ContextoListener.productosCabecera.get(1)
						.getUrlImagen()%>";
	photos[3] = "<%=ContextoListener.productosCabecera.get(2)
						.getUrlImagen()%>";
	photos[4] = "<%=ContextoListener.productosCabecera.get(3)
						.getUrlImagen()%>";
	photos[5] = "<%=ContextoListener.productosCabecera.get(4)
						.getUrlImagen()%>";
	
<%}%>

</script>

<div class="top_bar">
	<div class="top_search">
		<div class="search_text">Buscar Producto</div>
		<form action="Index" method="post">
			<input type="text" class="search_input" name="search" /> <input
				type="image" src="images/search.gif" class="search_bt" />
		</form>
	</div>

</div>
<div id="header">

	<div id="logo">
		<a href="Index"><img src="images/logo.png" alt=""
			title="Electronix" border="0" width="237" height="140" /></a>
	</div>

	<div class="oferte_content">
		<div class="top_divider">
			<img src="images/header_divider.png" alt="" title="" width="1"
				height="164" />
		</div>
		<div class="oferta">

			<div class="oferta_content">
				<img src="${requestScope.producto.urlImagen}" width="94" height="92"
					border="0" class="oferta_img" name="photoslider" />

				<div class="oferta_details">
					<c:choose>
						<c:when test="${sessionScope.usuario.admin == true}">
							<form action="AddProductoCabecera" method="post" name="formCabecera">
								<p>
									<label for="producto">Producto #1: </label> <select
										name="producto1">
										<option value="predeterminado">Seleccione una opción</option>
										<c:forEach items="${requestScope.productos}" var="producto">
											<option value="${producto.id.id}">${producto.nombre}</option>
										</c:forEach>

									</select>
								</p>
								<p>
									<label for="producto">Producto #2: </label> <select
										name="producto2">
										<option value="predeterminado">Seleccione una opción</option>
										<c:forEach items="${requestScope.productos}" var="producto">
											<option value="${producto.id.id}">${producto.nombre}</option>
										</c:forEach>

									</select>
								</p>
								<p>
									<label for="producto">Producto #3: </label> <select
										name="producto3">
										<option value="predeterminado">Seleccione una opción</option>
										<c:forEach items="${requestScope.productos}" var="producto">
											<option value="${producto.id.id}">${producto.nombre}</option>
										</c:forEach>

									</select>
								</p>
								<p>
									<label for="producto">Producto #4: </label> <select
										name="producto4">
										<option value="predeterminado">Seleccione una opción</option>
										<c:forEach items="${requestScope.productos}" var="producto">
											<option value="${producto.id.id}">${producto.nombre}</option>
										</c:forEach>

									</select>
								</p>
								<p>
									<label for="producto">Producto #5: </label> <select
										name="producto5">
										<option value="predeterminado">Seleccione una opción</option>
										<c:forEach items="${requestScope.productos}" var="producto">
											<option value="${producto.id.id}">${producto.nombre}</option>
										</c:forEach>

									</select>
								</p>
								<button type="submit" name="enviar" onclick=" return validarCabecera(formCabecera)">Enviar</button>
							</form>
						</c:when>
						<c:otherwise>
							<div id="oferta_title">${requestScope.producto.nombre}</div>
							<div id="oferta_text">${requestScope.producto.descripcion}</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="oferta_pagination">

				<c:if test="${sessionScope.usuario.admin != true}">

					<a href="javascript:foto(1)">${1}</a>
					<a href="javascript:foto(2)">${2}</a>
					<a href="javascript:foto(3)">${3}</a>
					<a href="javascript:foto(4)">${4}</a>
					<a href="javascript:foto(5)">${5}</a>

				</c:if>

			</div>

		</div>
		<div class="top_divider">
			<img src="images/header_divider.png" alt="" title="" width="1"
				height="164" />
		</div>

	</div>
	<!-- end of oferte_content-->


</div>