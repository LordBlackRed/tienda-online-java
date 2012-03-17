function validarSelect(select) {
	if (select.value == "defecto") {
		alert("Valor incorrecto");
		return false;
	} else {
		return true;
	}
}

function validarDatosUsuario(formulario) {
	// Comprobamos si los campos de texto num&#233;ricos realmente lo son
	var cp = isNaN(formulario.cp.value);
	var telFijo = isNaN(formulario.telFijo.value);
	var telMovil = isNaN(formulario.telMovil.value);
	var isFecha = isDate(formulario.fechaN.value);
	if (cp || telFijo || telMovil || !isFecha) {
		alert("Formato introducido incorrecto");
		return false;
	} else {
		return true;
	}
}

function isDate(Cadena) {
	var Fecha = new String(Cadena);
	var RealFecha = new Date();
	var Ano = new String(Fecha.substring(Fecha.lastIndexOf("/") + 1,
			Fecha.length));
	var Mes = new String(Fecha.substring(Fecha.indexOf("/") + 1, Fecha
			.lastIndexOf("/")));
	var Dia = new String(Fecha.substring(0, Fecha.indexOf("/")));

	if (isNaN(Ano) || Ano.length < 4 || parseFloat(Ano) < 1900) {
		return false;
	}
	if (isNaN(Mes) || parseFloat(Mes) < 1 || parseFloat(Mes) > 12) {
		return false;
	}
	if (isNaN(Dia) || parseInt(Dia, 10) < 1 || parseInt(Dia, 10) > 31) {
		return false;
	}
	if (Mes == 4 || Mes == 6 || Mes == 9 || Mes == 11 || Mes == 2) {
		if (Mes == 2 && Dia > 28 || Dia > 30) {
			return false;
		}
	}

	return true;
}
function validarDatosCompra(formulario) {
	if (validarDatosUsuario(formulario)) {
		var nombreUsuario = trim(formulario.nombreUsuario.value);
		var apellidos = trim(formulario.apellidos.value);
		var dni = trim(formulario.dni.value);
		var direccion = trim(formulario.direccion.value);
		var localidad = trim(formulario.localidad.value);
		var provincia = trim(formulario.provincia.value);
		var cp = trim(formulario.cp.value);
		var telMovil = trim(formulario.telMovil.value);
		
		if (nombreUsuario == "" || apellidos == "" || dni == ""
				|| direccion == "" || localidad == "" || provincia == ""
				|| cp == "" || cp == 0 || telMovil == "" || telMovil == 0) {
		alert("Todos los campos obligatorios no han sido rellenados");
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function trim(myString) {
	return myString.replace(/^\s+/g, '').replace(/\s+$/g, '')
}