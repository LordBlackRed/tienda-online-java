<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="head.jsp" />


<body>
<div class="cuerpo">
<jsp:include page="menu-principal.jsp" />
	<form action="Registro" method="post">
		<label for="usuario">Usuario</label><input type="text" name="usuario"><br>
		<label for="pass">Contraseña</label><input type="password" name="pass"><br>
		<input type="submit" value="enviar">
	</form>
	</div>
</body>
</html>