<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Añadir Producto</div>
				<form action="ProductoNuevo" method="post">
					<label for="nombre">Nombre: </label><input type="text"
						name="nombre" /><br></br> <label for="fecha">Fecha: </label><input
						type="text" name="fecha" /><br></br> <label for="precio">Precio:
					</label><input type="text" name="precio" /><br></br> <label for="cantidad">Cantidad:
					</label><input type="text" name="cantidad" /> <br></br> <label for="url">URL:
					</label><input type="text" name="url" /> <br></br> <label for="categoria">Categoria:
					</label><select name="categoria" id="categoria">
						<c:forEach items="${requestScope.categorias}" var="categoria">
							<option value="${categoria.titulo}">${categoria.titulo}</option>
						</c:forEach>

					</select> <br></br> <input type="submit" value="enviar" />
				</form>

				<div class="center_title_bar">Añadir Categoría</div>
				<form action="CategoriaNueva" method="post">
					<label for="titulo">Titulo: </label><input type="text"
						name="titulo" /><br></br> <input type="submit" value="enviar" />
				</form>

				<div class="center_title_bar">Añadir Fabricante</div>
				<form action="FabricanteNuevo" method="post">
					<label for="nombreFabricante">Nombre: </label><input type="text"
						name="nombreFabricante" /><br></br> <input type="submit" value="enviar" />
				</form>

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