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

function validarRegistro(formulario) {
	var nombre = trim(formulario.nombre.value);
	var pass = trim(formulario.pass.value);

	if (nombre == "" || pass == "") {
		alert("Todos los campos obligatorios no han sido rellenados");
		return false;
	} else {
		return true;
	}
}

function enviarPaypal(formulario) {
	formulario.submit();
}
function validarCarrito(formulario, envio) {
	if (formulario.empresaEnvio.value == "Elige una Opcion") {
		alert("Debe seleccionar el tipo de envio");
		return false;
	} else if (envio == "") {
		alert("Debe actualizar el carrito para introducir los gastos de envio");
		return false;
	} else {
		return true;
	}
}

function ajaxFunction() {
	var xmlHttp;

	try {

		xmlHttp = new XMLHttpRequest();
		return xmlHttp;
	} catch (e) {

		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			return xmlHttp;
		} catch (e) {

			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				return xmlHttp;
			} catch (e) {
				alert("Tu navegador no soporta AJAX!");
				return false;
			}
		}
	}
}
function actualizarTotal(formulario) {
	var total = formulario.totalSinEnvio.value;
	if (formulario.empresaEnvio.value == "Elige una Opcion") {
		var ajax;
		ajax = ajaxFunction();
		ajax.open("POST", true);
		ajax.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 1) {
				document.getElementById("totalFactura").innerHTML = " Aguarde por favor...";
			}
			if (ajax.readyState == 4) {
				document.getElementById("totalFactura").innerHTML = "<label>Total: </label>"
						+ total + "&#8364;";
				document.getElementById("empresaEnvio").innerHTML = "<label>Envio: </label>"
						+ 0 + "&#8364;";
			}
		}

		ajax.send(null);

	}
}
function validarProductoNuevo(formulario) {
	var precio = isNaN(formulario.precio.value);
	var cantidad = isNaN(formulario.cantidad.value);
	var categoria = formulario.categoria.value;
	var fabricanteProducto = formulario.fabricanteProducto.value;
	var nombre = trim(formulario.nombre.value);

	if (fabricanteProducto == "defecto" || categoria == "defecto"
			|| nombre == "") {
		alert("Todos los campos obligatorios no han sido rellenados");
		return false;
	} else if (precio || cantidad) {
		alert("Formato introducido incorrecto");
		return false;
	} else {
		return true;
	}
}

function validarCampoVacio(campo) {
	var cadena = trim(campo.value);
	if (cadena == "") {
		alert("Todos los campos obligatorios no han sido rellenados");
		return false;
	} else {
		return true;
	}
}

function validarEnvio(formulario) {
	var gastosEnvio = isNaN(formulario.precioEnvio.value);
	var empresa = trim(formulario.empresa.value);

	if (gastosEnvio) {
		alert("Formato introducido incorrecto");
		return false;
	} else if (empresa == "" || formulario.precioEnvio.value == "") {
		alert("Todos los campos obligatorios no han sido rellenados");
		return false;
	} else {
		return true;
	}
}

function backward() {
	if (which > 0) {
		window.status = ''
		which--;
		document.images.photoslider.src = photos[which];
	}
}

function foto(num) {
	document.images.photoslider.src = photos[num];
	Enviar(texto[num], 'oferta_text');
	Enviar(nombreProductos[num], 'oferta_title');
}

function Enviar(texto, capa) {
	var ajax;
	ajax = ajaxFunction();
	ajax.open("POST", true);
	ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 1) {
			document.getElementById(capa).innerHTML = " Aguarde por favor...";
		}
		if (ajax.readyState == 4) {
			document.getElementById(capa).innerHTML = texto;
		}
	}

	ajax.send(null);
}

function validarCabecera(formulario) {

	if (formulario.producto1.value == "predeterminado"
			|| formulario.producto2.value == "predeterminado"
			|| formulario.producto3.value == "predeterminado"
			|| formulario.producto4.value == "predeterminado"
			|| formulario.producto5.value == "predeterminado") {
		alert("Debe elegir 5 productos");
		return false;
	} else {
		return true;
	}
}