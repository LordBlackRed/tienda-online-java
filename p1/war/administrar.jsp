<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
<title>Administrar</title>
<link rel="stylesheet" type="text/css" href="style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="javascript/scripts.js"></script>
<script>
	function validar(formulario) {
		var precio = isNaN(formulario.precio.value);
		var cantidad = isNaN(formulario.cantidad.value);
		var categoria = formulario.categoria.value;
		var fabricanteProducto = formulario.fabricanteProducto.value;
		var nombre = trim(formulario.nombre.value);
		
		if (fabricanteProducto == "defecto" || categoria == "defecto" || nombre =="") {
			alert("Todos los campos obligatorios no han sido rellenados");
			return false;
		} else if (precio || cantidad) {
			alert("Formato introducido incorrecto");
			return false;
		} else {
			return true;
		}
	}
	function validarCampoVacio(campo){
		var cadena = trim(campo.value);
		if(cadena==""){
			alert("Todos los campos obligatorios no han sido rellenados");
			return false;
		}else{
			return true;
		}
	}
	
	
	
	function validarEnvio(formulario){
		var gastosEnvio = isNaN(formulario.precioEnvio.value);
		var empresa = trim(formulario.empresa.value);
		
		if(gastosEnvio){
			alert("Formato introducido incorrecto");
			return false;
		}else if(empresa == "" || formulario.precioEnvio.value==""){
			alert("Todos los campos obligatorios no han sido rellenados");
			return false;
		}else{
			return true;
		}
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
				<div class="center_title_bar">Añadir Producto</div>

				<div id="stylized">
					<form action="ProductoNuevo" method="post" name="formProductoNuevo">

						<label for="nombre">Nombre: </label> <input type="text"
							name="nombre" /> <label for="precio">Precio: </label> <input
							type="text" name="precio" /> <label for="cantidad">Cantidad:
						</label> <input type="text" name="cantidad" /> <label for="url">URL:
						</label> <input type="text" name="url" /> <label for="descripcion">Descripción:
						</label>
						<textarea name="descripcion" cols="20" rows="4"></textarea>
						<label for="categoria">Categoria: <span class="small">Elige
								su categoría</span>
						</label><select name="categoria" id="categoria">
							<option value="defecto">Seleccione una opción</option>
							<c:forEach items="${requestScope.categorias}" var="categoria">
								<option value="${categoria.titulo}">${categoria.titulo}</option>
							</c:forEach>
						</select> <br></br> <label for="fabricanteProducto">Fabricante: <span
							class="small">Elige su Fabricante</span>
						</label> <select name="fabricanteProducto" id="fabricanteProducto">
							<option value="defecto">Seleccione una opción</option>
							<c:forEach items="${requestScope.fabricantes}" var="fabricante">
								<option value="${fabricante.id}">${fabricante.nombre}</option>
							</c:forEach>

						</select>

						<button type="submit" value="enviar"
							onclick="return validar(formProductoNuevo);">Enviar</button>
					</form>
				</div>
				<div class="center_title_bar">Añadir Categoría o Fabricante</div>
				<div id="estilos_categoria">
					<div class="form_categoria">

						<form action="CategoriaNueva" method="post" name="formCategoriaNueva">
							<label for="titulo">Categoría: </label><input type="text"
								name="titulo" />
							<button type="submit" value="enviar" onclick="return validarCampoVacio(formCategoriaNueva.titulo);">Enviar</button>
						</form>
					</div>

					<div class="form_fabricante">

						<form action="FabricanteNuevo" method="post" name="formFabricanteNuevo">
							<label for="nombreFabricante">Fabricante: </label><input
								type="text" name="nombreFabricante" />
							<button type="submit" value="enviar" onclick="return validarCampoVacio(formFabricanteNuevo.nombreFabricante);">Enviar</button>
						</form>
					</div>
				</div>
				<div class="center_title_bar">Eliminar Categoría o Fabricante</div>
				<div id="estilos_categoria">
					<div class="form_categoria">

						<form action="EliminarCategoria" method="post">
							<label for="titulo">Categoría: </label> <select
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
				<div class="center_title_bar">Añadir Envío</div>
				<div id="estilos_categoria">
					<div id="form_envio">

						<form action="NuevoEnvio" method="post" name="formNuevoEnvio">
							<label for="empresa">Nombre Empresa: </label><input type="text"
								name="empresa" id="empresa" /> <label for="precioEnvio">Gastos
								de Envío: </label><input tyoe="text" name="precioEnvio" id="precioEnvio" />
							<button type="submit" value="enviarNuevoEnvio" onclick="return validarEnvio(formNuevoEnvio);">Enviar</button>
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