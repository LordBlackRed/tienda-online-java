<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="menu_tab">
	<div class="left_menu_corner"></div>
	<ul class="menu">
		<li><a href="Index" class="nav1"> Inicio </a></li>
		<li class="divider"></li>
		<li><a href="#" class="nav3">Specials</a></li>
		<li class="divider"></li>
		<li><a href="#" class="nav4">My account</a></li>
		<li class="divider"></li>
		<li><a href="Registrarse" class="nav4">Registrarse</a></li>
		<li class="divider"></li>
		<li><a href="#" class="nav5">Shipping </a></li>
		<li class="divider"></li>
		<li><a href="contact.html" class="nav6">Contact Us</a></li>
		<li class="divider"></li>

		<c:choose>
			<c:when test="${sessionScope.usuario.admin == true}">

				<li><a href="Administrar" class="nav2">Administrar</a></li>
				<li class="divider"></li>
				<li class="currencies">Currencies <select>
						<option>US Dollar</option>
						<option>Euro</option>
				</select>
				</li>

			</c:when>
			<c:otherwise>
				<li class="currencies">Currencies <select>
						<option>US Dollar</option>
						<option>Euro</option>
				</select>
				</li>
			</c:otherwise>
		</c:choose>



	</ul>

	<div class="right_menu_corner"></div>
</div>
<!-- end of menu tab -->