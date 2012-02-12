<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="head.jsp" />

<body>

	<div id="main_container">

		<jsp:include page="cabecera.jsp" />

		<div id="main_content">

			<jsp:include page="menu-up.jsp" />

			<jsp:include page="left-content.jsp" />

			<div class="center_content">
				<div class="center_title_bar">Datos de Usuario</div>

				<div id="stylized" class="for_producto_nuevo">
					<form action="ActualizarUsuario" method="post">

						<label for="nombreUsuario">Nombre: </label> <input type="text"
							name="nombreUsuario" value="${sessionScope.usuario.nombre}" /> <label
							for="apellidos">Apellidos: </label> <input type="text"
							name="apellidos" value="${sessionScope.usuario.apellidos}" /> <label
							for="dni">DNI: </label> <input type="text" name="dni"
							value="${sessionScope.usuario.dni}" /> <label for="direccion">Dirección:
						</label> <input type="text" name="direccion"
							value="${sessionScope.usuario.direccion}" /> <label
							for="localidad">Localidad: </label> <input type="text"
							name="localidad" value="${sessionScope.usuario.localidad}" /> <label
							for="provincia">Provincia: </label><input type="text"
							name="provincia" value="${sessionScope.usuario.provincia}" /><label
							for="cp">Código Postal</label> <input type="text" name="cp"
							value="${sessionScope.usuario.cp}" /><label for="fechaN">Fecha
							Nacimiento</label><input type="text" name="fechaN"
							value="${sessionScope.usuario.fechaNacimiento}" /> <label
							for="telFijo">Teléfono Fijo </label> <input type="text"
							name="telFijo" value="${sessionScope.usuario.telefonoFijo}" /> <label
							for="telMovil">Teléfono Móvil </label><input type="text"
							name="telMovil" value="${sessionScope.usuario.telefonoMovil}" />
						<div class="spacer"></div>
						<button type="submit" value="enviar">Enviar</button>

					</form>
				</div>
			</div>
			<jsp:include page="right-content.jsp" />

		</div>
		<!-- end of main content -->

		<jsp:include page="footer-content.jsp" />

	</div>
	<!-- end of main_container -->
</body>
</html>