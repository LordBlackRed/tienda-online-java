<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="tiendaonline.metodos.MisMetodos"%>
<%@page import="tiendaonline.enumerados.MisAtributos"%>
<%@page import="tiendaonline.ServletIndex"%>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Inicio</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script>
	var error = ${requestScope.error};
	if (error == true) {
		alert("Usuario o password incorrecto");
	}
</script>
<script type="text/javascript">
	var registrado = ${requestScope.registrado};
	if (registrado == true) {
		alert("Usuario registrado. Ya puede loguearse");
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


				<div class="center_title_bar">
					Últimos Productos
					<c:if test="${requestScope.fav==true}">Favoritos</c:if>
				</div>

				<c:forEach items="${requestScope.productos}" var="producto">

					<div class="prod_box">
						<div class="top_prod_box"></div>
						<div class="center_prod_box">
							<div class="product_title">${producto.nombre}</div>
							<div class="product_img">
								<img src="${producto.urlImagen}" width="125" height="150" alt=""
									title="" border="0" />
							</div>
							<div class="prod_price">
								<span class="price">${producto.precio}</span>
							</div>
						</div>
						<div class="bottom_prod_box"></div>
						<div class="prod_details_tab">
							<c:choose>
								<c:when test="${sessionScope.usuario.nick != 'admin'}">

									<a
										href="AddCarrito?id=${producto.id.id}&pag=${requestScope.paginaActual}"
										title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
										src="images/cart.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a
										href="AddPuntuacion?id=${producto.id.id}&megusta=t&pag=${requestScope.paginaActual}"
										title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a>
									<a
										href="AddPuntuacion?id=${producto.id.id}&megusta=f&pag=${requestScope.paginaActual}"
										title="header=[Specials] body=[&nbsp;] fade=[on]"><img
										src="images/no_me_gusta.png" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="VerPuntuaciones?id=${producto.id.id}"
										title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
										src="images/favorites.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="Detalles?id=${producto.id.id}" class="prod_details">Más</a>

								</c:when>

								<c:otherwise>
									<a
										href="EliminarProducto?id=${producto.id.id}&pag=${requestScope.paginaActual}"
										title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img
										src="images/icono_eliminar.gif" alt="Eliminar Producto"
										title="" border="0" class="left_bt" /></a>
									<a href="VerPuntuaciones?id=${producto.id.id}"
										title="header=[Gifts] body=[&nbsp;] fade=[on]"><img
										src="images/favorites.gif" alt="" title="" border="0"
										class="left_bt" /></a>
									<a href="Detalles?id=${producto.id.id}" class="prod_details">Más</a>

								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>

				<c:if test="${requestScope.numPaginas !=1 && requestScope.numPaginas !=0}">
					<div id="center_pagination">
						<c:choose>
							<c:when test="${requestScope.fav==true}">
								<c:if test="${requestScope.paginaActual != 1}">
									<a href="Index?pag=${requestScope.paginaAnterior}&fav=t"
										title="Anterior">&#171;</a>
								</c:if>
								<%
									for (int contador = 1; contador <= Integer
														.parseInt(request.getAttribute(
																MisAtributos.numPaginas.toString())
																.toString()); contador++) {
													out.print("<a href='Index?pag=" + contador
															+ "&fav=t'>" + contador + "</a>" + "\t");
												}
								%>
								<c:if
									test="${requestScope.paginaActual != requestScope.numPaginas}">
									<a href="Index?pag=${requestScope.paginaSiguiente}&fav=t"
										title="Siguiente">&#187;</a>
								</c:if>

							</c:when>
							<c:when test="${requestScope.fav==null}">
								<c:if test="${requestScope.paginaActual != 1}">
									<a href="Index?pag=${requestScope.paginaAnterior}"
										title="Anterior">&#171;</a>
								</c:if>
								<%
									for (int contador = 1; contador <= Integer
														.parseInt(request.getAttribute(
																MisAtributos.numPaginas.toString())
																.toString()); contador++) {
													out.print("<a href='Index?pag=" + contador + "'>"
															+ contador + "</a>" + "\t");
												}
								%>
								<c:if
									test="${requestScope.paginaActual != requestScope.numPaginas}">
									<a href="Index?pag=${requestScope.paginaSiguiente}"
										title="Siguiente">&#187;</a>
								</c:if>

							</c:when>
						</c:choose>
						<%
							if (ServletIndex.categoria != null) {
						%>

						<c:if test="${requestScope.paginaActual != 1}">
							<a
								href="Index?pag=${requestScope.paginaAnterior}&categoria=<%=ServletIndex.categoria%>"
								title="Anterior">&#171;</a>
						</c:if>
						<%
							for (int contador = 1; contador <= Integer.parseInt(request
											.getAttribute(MisAtributos.numPaginas.toString())
											.toString()); contador++) {
										out.print("<a href='Index?pag=" + contador
												+ "&categoria=" + ServletIndex.categoria + "'>"
												+ contador + "</a>" + "\t");
									}
						%>
						<c:if
							test="${requestScope.paginaActual != requestScope.numPaginas}">
							<a
								href="Index?pag=${requestScope.paginaSiguiente}&categoria=<%=ServletIndex.categoria%>"
								title="Siguiente">&#187;</a>
						</c:if>
						<%
							} else if (ServletIndex.fabricante != null) {
						%>
						<c:if test="${requestScope.paginaActual != 1}">
							<a
								href="Index?pag=${requestScope.paginaAnterior}&fabricante=<%=ServletIndex.fabricante%>"
								title="Anterior">&#171;</a>
						</c:if>
						<%
							for (int contador = 1; contador <= Integer.parseInt(request
											.getAttribute(MisAtributos.numPaginas.toString())
											.toString()); contador++) {
										out.print("<a href='Index?pag=" + contador
												+ "&fabricante=" + ServletIndex.fabricante
												+ "'>" + contador + "</a>" + "\t");
									}
						%>
						<c:if
							test="${requestScope.paginaActual != requestScope.numPaginas}">
							<a
								href="Index?pag=${requestScope.paginaSiguiente}&fabricante=<%=ServletIndex.fabricante%>"
								title="Siguiente">&#187;</a>
						</c:if>

						<%
							} else if (ServletIndex.busquedaPer != null) {
						%>
						<c:if test="${requestScope.paginaActual != 1}">
							<a
								href="Index?pag=${requestScope.paginaAnterior}&search=<%=ServletIndex.busquedaPer%>"
								title="Anterior">&#171;</a>
						</c:if>
						<%
							for (int contador = 1; contador <= Integer.parseInt(request
											.getAttribute(MisAtributos.numPaginas.toString())
											.toString()); contador++) {
										out.print("<a href='Index?pag=" + contador + "&search="
												+ ServletIndex.busquedaPer + "'>" + contador
												+ "</a>" + "\t");
									}
						%>
						<c:if
							test="${requestScope.paginaActual != requestScope.numPaginas}">
							<a
								href="Index?pag=${requestScope.paginaSiguiente}&search=<%=ServletIndex.busquedaPer%>"
								title="Siguiente">&#187;</a>
						</c:if>
						<%
							}
						%>
					</div>
				</c:if>
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