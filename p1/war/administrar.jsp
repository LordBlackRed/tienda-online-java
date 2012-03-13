<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />
<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">A�adir Producto</div>

				<div id="stylized">
					<form action="ProductoNuevo" method="post">

						<label for="nombre">Nombre: </label> <input type="text"
							name="nombre" /> <label for="precio">Precio: </label> <input
							type="text" name="precio" /> <label for="cantidad">Cantidad:
						</label> <input type="text" name="cantidad" /> <label for="url">URL:
						</label> <input type="text" name="url" /> <label for="descripcion">Descripci�n:
						</label>
						<textarea name="descripcion" cols="20" rows="4"></textarea>
						<label for="categoria">Categoria: <span class="small">Elige
								su categor�a</span>
						</label><select name="categoria" id="categoria">
							<option value="defecto">Seleccione una opci�n</option>
							<c:forEach items="${requestScope.categorias}" var="categoria">
								<option value="${categoria.titulo}">${categoria.titulo}</option>
							</c:forEach>
						</select> <br></br> <label for="fabricanteProducto">Fabricante: <span
							class="small">Elige su Fabricante</span>
						</label> <select name="fabricanteProducto" id="fabricanteProducto">
							<option value="defecto">Seleccione una opci�n</option>
							<c:forEach items="${requestScope.fabricantes}" var="fabricante">
								<option value="${fabricante.id}">${fabricante.nombre}</option>
							</c:forEach>

						</select>
						
						<button type="submit" value="enviar">Enviar</button>
					</form>
				</div>
				<div class="center_title_bar">A�adir Categor�a o Fabricante</div>
				<div id="estilos_categoria">
					<div class="form_categoria">

						<form action="CategoriaNueva" method="post">
							<label for="titulo">Categor�a: </label><input type="text"
								name="titulo" />
							<button type="submit" value="enviar">Enviar</button>
						</form>
					</div>

					<div class="form_fabricante">

						<form action="FabricanteNuevo" method="post">
							<label for="nombreFabricante">Fabricante: </label><input
								type="text" name="nombreFabricante" />
							<button type="submit" value="enviar">Enviar</button>
						</form>
					</div>
				</div>
				<div class="center_title_bar">Eliminar Categor�a o Fabricante</div>
				<div id="estilos_categoria">
					<div class="form_categoria">

						<form action="EliminarCategoria" method="post">
							<label for="titulo">Categor�a: </label> <select
								name="eliminarCategoria" id="eliminarCategoria">
								<c:forEach items="${requestScope.categorias}" var="categoria">
									<option value="${categoria.titulo}">${categoria.titulo}</option>
								</c:forEach>
							</select>
							<button type="submit" value="enviarEliminarCategoria">Eliminar</button>
						</form>
					</div>

					<div class="form_fabricante">

						<form action="EliminarFabricante" method="post">
							<label for="eliminarFabricante">Fabricante: </label> <select
								name="eliminarFabricante" id="eliminarFabricante">
								<c:forEach items="${requestScope.fabricantes}" var="fabricante">
									<option value="${fabricante.nombre}">${fabricante.nombre}</option>
								</c:forEach>
							</select>
							<button type="submit" value="enviarEliminarFabricante">Eliminar</button>
						</form>
					</div>
				</div>
				<div class="center_title_bar">A�adir Env�o</div>
				<div id="estilos_categoria">
					<div class="form_categoria">

						<form action="NuevoEnvio" method="post">
							<label for="empresa">Nombre Empresa: </label><input type="text"
								name="empresa" id="empresa" /> <label for="precioEnvio">Gastos
								de Env�o: </label><input tyoe="text" name="precioEnvio" id="precioEnvio" />
							<button type="submit" value="enviarNuevoEnvio">Enviar</button>
						</form>
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