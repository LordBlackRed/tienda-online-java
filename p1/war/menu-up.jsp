<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="menu_tab">
	<div class="left_menu_corner"></div>
	<ul class="menu">
		<li><a href="Index" class="nav1"> Inicio </a></li>
		<li class="divider"></li>
		<c:if test="${!empty sessionScope.usuario && sessionScope.usuario.admin == false}">
		<li><a href="Index?fav=t" class="nav3">Favoritos</a></li>
		<li class="divider"></li>
		<li><a href="MiCuenta" class="nav4">Mi Cuenta</a></li>
		<li class="divider"></li>
		</c:if>
		<li><a href="Registrarse" class="nav4">Registrarse</a></li>
		<li class="divider"></li>
		<li><a href="Envio" class="nav5">Envío </a></li>
		<li class="divider"></li>
		<li><a href="Contacto" class="nav6">Contacta</a></li>
		<li class="divider"></li>

		<c:if test="${sessionScope.usuario.admin == true}">

			<li><a href="Administrar" class="nav2">Administrar</a></li>
			<li class="divider"></li>
			<li><a href="Facturacion" class="nav2">Facturación </a></li>
		</c:if>


	</ul>

	<div class="right_menu_corner"></div>
</div>
<!-- end of menu tab -->