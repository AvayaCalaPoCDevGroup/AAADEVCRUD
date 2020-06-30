/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Created 9/23/2019");
/*
 * Variebles
 */
var absolutepath = getAbsolutePath();
/*
 * Eventos
 */

document.getElementById('lanzarCampana').addEventListener('click', function (e) {
    e.preventDefault();
    console.log("Launch WF");
    var data = new FormData();
    data.append("family", "AAADEVNOTIFICACIONVP");
    data.append("type", "AAADEVNOTIFICACIONVPFamily");
    data.append("version", "1.0");
    data.append("eventBody", "{\"NVP\":\"YES\"}");

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            Swal.fire(
                    'Info',
                    'Lanzado.',
                    'info'
                    );
        }
    });

    xhr.open("POST", "https://breeze2-196.collaboratory.avaya.com/services/EventingConnector/events");
    xhr.send(data);
});


document.getElementById('crearNotificacion').addEventListener('click', function (e) {
    e.preventDefault();
    console.log("Crear Notificación");
    Swal.fire({
        title: '<strong>Crear Notificación</strong>',
        html: '        <div class="container">' +
                '            <div class="row"> ' +
                '<div class="col-sm-12">' +
                '   <form id="formCreateNotif" class="needs-validation">' +
                '      <h2>Ingresar todos los campos</h2>' +
                '     <div class="input-group col-sm-12" style="display: none">' +
                '        <p>Número de Cuenta: &nbsp; </p>' +
                '       <input type="text" class="form-control" placeholder="Numero De cuenta"  id="numeroDeCuenta" required>' +
                '  </div>' +
                ' <div class="input-group col-sm-12">' +
                '    <p>Nombre: &nbsp; </p>' +
                '    <input type="text" class="form-control" placeholder="Nombre del Notificado"  id="nombre" required>' +
                '        </div>' +
                '       <div class="input-group col-sm-12">' +
                '          <p>eMail &nbsp; </p>' +
                '         <input type="email" class="form-control" placeholder="E Mail" id="eMail" required>' +
                '    </div>' +
                '   <div class="input-group col-sm-12">' +
                '      <p>Apellido &nbsp; </p>' +
                '     <input type="text" class="form-control" placeholder="apellido" id="apellido" required>' +
                '</div>' +
                '       <div class="input-group col-sm-12">' +
                '          <p>Monto &nbsp; </p>' +
                '         <input type="text" class="form-control" placeholder="Monto " id="monto" required>' +
                '    </div>' +
                '   <div class="input-group col-sm-12">' +
                '      <p>Fecha De Vencimiento &nbsp; </p>' +
                '     <input type="date" class="form-control" id="fechaDeVencimiento" required>' +
                '</div>' +
                '        <div class="input-group col-sm-12">' +
                '           <p>Teléfono de Casa: &nbsp; </p>' +
                '          <input type="text" class="form-control" placeholder="Teléfono de Casa"  id="telefonoDeCasa" required>' +
                '     </div>' +
                '   <div class="input-group col-sm-12">' +
                '       <p>Teléfono Movil: &nbsp; </p>' +
                '      <input type="text" class="form-control" placeholder="Teléfono Móvil" id="telefonoMovil" required>' +
                ' </div>' +
                '<div class="input-group col-sm-12">' +
                '            <p>Número de factura: &nbsp; </p>' +
                '           <input type="text" class="form-control" placeholder="Número de Factura" id="numeroDeFactura" required>' +
                '      </div>' +
                '     <div class="input-group col-sm-12">' +
                '        <p>Status: &nbsp; </p>' +
                '      <select class="form-control" id="status">' +
                '         <option>0</option>' +
                '        <option>1</option>' +
                '   </select>' +
                '        </div>' +
                '   </form>' +
                '<br/>'+
                '<button id="btnFormCreateNotif" class="btn btn-success bn-block">Aceptar</button>'+
                '</div>' +
                '</div>' +
                '</div>',
        showCloseButton: false,
        showCancelButton: false,
        showConfirmButton: false
//        focusConfirm: false,
//        confirmButtonText:
//                'READY!',
//        confirmButtonAriaLabel: 'Thumbs up, great!',
//        cancelButtonText:
//                'EXIT',
//        cancelButtonAriaLabel: 'Thumbs down'
//    }).then((result) => {
//        if (result.value) {
//            crearNotificacion();
//        }
//    });
    });
    
    document.getElementById('btnFormCreateNotif').addEventListener('click', function (e) {
    	var form = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
    	var form = document.getElementById('formCreateNotif');

        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        } else {        	
        	crearNotificacion();
        	Swal.Close
        }
        form.classList.add('was-validated');
    });

    function crearNotificacion() {
        var nombre = document.getElementById('nombre').value;
        var email = document.getElementById('eMail').value;
        var apellido = document.getElementById('apellido').value;
        var monto = document.getElementById('monto').value;
        var fechadevencimiento = document.getElementById('fechaDeVencimiento').value;
        var telefonodecasa = document.getElementById('telefonoDeCasa').value;
        var telefonomovil = document.getElementById('telefonoMovil').value;
        var numerodefactura = document.getElementById('numeroDeFactura').value;
        var status = document.getElementById('status').value;

        if (validateInputs()) {
            var data = new FormData();
            data.append("action", "crearNotificacion");
            data.append("nombre", nombre);
            data.append("apellido", apellido);
            data.append("telefonocasa", telefonodecasa);
            data.append("telefonomovil", telefonomovil);
            data.append("email", email);
            data.append("monto", monto);
            data.append("numerodefactura", numerodefactura);
            data.append("fechadevencimiento", fechadevencimiento);
            data.append("status", status);

            var xhr = new XMLHttpRequest();
            xhr.withCredentials = false;

            xhr.addEventListener("readystatechange", function () {
                if (this.readyState === 4) {
                    console.log(this.responseText);
                    var jsonObject = JSON.parse(this.responseText);
                    if (jsonObject.status === "ok") {
                        var tBody = document.getElementById('tBody');
                        //CREACION DE TR
                        var TR = document.createElement('TR');
                        TR.setAttribute('id', jsonObject.numerodecuenta + "," + jsonObject.nombre);
                        //CREACION DE TD's
                        var numeroDeCuentaTD = document.createElement('TD');
                        var numeroDeCuentaTDTextNode = document.createTextNode(jsonObject.numerodecuenta);
                        numeroDeCuentaTD.appendChild(numeroDeCuentaTDTextNode);
                        var nombreTD = document.createElement('TD');
                        var nombreTDTextNode = document.createTextNode(jsonObject.nombre);
                        nombreTD.appendChild(nombreTDTextNode);
                        var eMailTD = document.createElement('TD');
                        var eMailTDTextNode = document.createTextNode(jsonObject.email);
                        eMailTD.appendChild(eMailTDTextNode);
                        var apellidoTD = document.createElement('TD');
                        var apellidoTDTextNode = document.createTextNode(jsonObject.apellido);
                        apellidoTD.appendChild(apellidoTDTextNode);
                        var montoTD = document.createElement('TD');
                        var montoTDTextNode = document.createTextNode(jsonObject.monto);
                        montoTD.appendChild(montoTDTextNode);
                        var fechaDeVencimientoTD = document.createElement('TD');
                        var fechaDeVencimientoTDTextNode = document.createTextNode(jsonObject.fechadevencimiento);
                        fechaDeVencimientoTD.appendChild(fechaDeVencimientoTDTextNode);
                        var telefonoDeCasaTD = document.createElement('TD');
                        var telefonoDeCasaTDTextNode = document.createTextNode(jsonObject.telefonocasa);
                        telefonoDeCasaTD.appendChild(telefonoDeCasaTDTextNode);
                        var telefonoMovilTD = document.createElement('TD');
                        var teledonoMovilTDTextNode = document.createTextNode(jsonObject.telefonomovil);
                        telefonoMovilTD.appendChild(teledonoMovilTDTextNode);
                        var numeroDeFacturaTD = document.createElement('TD');
                        var numeroDeFacturaTDTextNode = document.createTextNode(jsonObject.numerodefactura);
                        numeroDeFacturaTD.appendChild(numeroDeFacturaTDTextNode);
                        var statusTD = document.createElement('TD');
                        var statusTDTextNode = document.createTextNode(jsonObject.statusBD);
                        statusTD.appendChild(statusTDTextNode);


                        var buttonsTD = document.createElement('TD');
                        var divRow = document.createElement('DIV');
                        divRow.setAttribute('class', 'row');
                        //Boton Editar
                        var divColEditar = document.createElement('DIV');
                        divColEditar.setAttribute('class', 'col');
                        var buttonEditar = document.createElement('DIV');
                        buttonEditar.setAttribute('type', 'button');
                        buttonEditar.setAttribute('class', 'btn btn-warning btn-block');
                        buttonEditar.setAttribute('style', 'color: black; font-weight: bold');
                        buttonEditar.setAttribute('onclick', 'editar(this)');
                        var buttonEditarTextNode = document.createTextNode('EDITAR');
                        buttonEditar.appendChild(buttonEditarTextNode);
                        divColEditar.appendChild(buttonEditar);
                        //Boton Borrar
                        var divColBorrar = document.createElement('DIV');
                        divColBorrar.setAttribute('class', 'col');
                        var buttonBorrar = document.createElement('DIV');
                        buttonBorrar.setAttribute('type', 'button');
                        buttonBorrar.setAttribute('class', 'btn btn-danger btn-block');
                        buttonBorrar.setAttribute('style', 'color: black; font-weight: bold');
                        buttonBorrar.setAttribute('onclick', 'borrar(this)');
                        var buttonBorrarTextNode = document.createTextNode('BORRAR');
                        buttonBorrar.appendChild(buttonBorrarTextNode);
                        divColBorrar.appendChild(buttonBorrar);
                        divRow.appendChild(divColEditar);
                        divRow.appendChild(divColBorrar);
                        buttonsTD.appendChild(divRow);


                        TR.appendChild(numeroDeCuentaTD);
                        TR.appendChild(nombreTD);
                        TR.appendChild(eMailTD);
                        TR.appendChild(apellidoTD);
                        TR.appendChild(montoTD);
                        TR.appendChild(fechaDeVencimientoTD);
                        TR.appendChild(telefonoDeCasaTD);
                        TR.appendChild(telefonoMovilTD);
                        TR.appendChild(numeroDeFacturaTD);
                        TR.appendChild(statusTD);
                        TR.appendChild(buttonsTD);

                        tBody.appendChild(TR);
                        Swal.fire(
                                'Éxito!',
                                'Se ha creado un notificación nueva con el nombre de: ' + jsonObject.nombre + " " + jsonObject.apellido,
                                'success'
                                );
                    }
                    if (jsonObject.status === "error") {
//                        Swal.fire(
//                                'Error!',
//                                'Error al crear notificación, favor de intentarlo más tarde.',
//                                'error'
//                                );
                    }
                }
            });

            xhr.open("POST", absolutepath + "NotificacionVencimientoDePago");
            xhr.send(data);


        } else {
            Swal.fire(
                    'Error!',
                    'Favor de llenar todos los campos',
                    'error'
                    );
        }
    }

    function validateInputs() {
        if (document.getElementById('nombre').value === "" || document.getElementById('nombre').value === null) {
            return false;
        }
        if (document.getElementById('eMail').value === "" || document.getElementById('eMail').value === null) {
            return false;
        }
        if (document.getElementById('apellido').value === "" || document.getElementById('apellido').value === null) {
            return false;
        }
        if (document.getElementById('monto').value === "" || document.getElementById('monto').value === null) {
            return false;
        }
        if (document.getElementById('fechaDeVencimiento').value === "" || document.getElementById('fechaDeVencimiento').value === null) {
            return false;
        }
        if (document.getElementById('telefonoDeCasa').value === "" || document.getElementById('telefonoDeCasa').value === null) {
            return false;
        }
        if (document.getElementById('telefonoMovil').value === "" || document.getElementById('telefonoMovil').value === null) {
            return false;
        }
        if (document.getElementById('numeroDeFactura').value === "" || document.getElementById('numeroDeFactura').value === null) {
            return false;
        }
        return true;
    }

});

/*
 * Funciones
 */
function isselected(value, status){
	if(value == status){
		return "selected";
	} else {
		return "";
	}
}
function editar(_this) {
    console.log("Editar");
    console.log(_this.parentNode.parentNode.parentNode.parentNode.id);
    var idNotificacionMpdificada = _this.parentNode.parentNode.parentNode.parentNode.id;
    var propiedades = _this.parentNode.parentNode.parentNode.parentNode.children;
    console.log(propiedades);

    var numeroDeCuenta = propiedades[0].textContent;
    var nombre = propiedades[1].textContent;
    var eMail = propiedades[2].textContent;
    var apellido = propiedades[3].textContent;
    var monto = propiedades[4].textContent;
    var fechaDeVencimiento = propiedades[5].textContent;
    var telefonoDeCasa = propiedades[6].textContent;
    var telefonoMovil = propiedades[7].textContent;
    var numeroDeFactura = propiedades[8].textContent;
    var status = propiedades[9].textContent;

    Swal.fire({
        title: '<strong>Editar</strong>',
        type: 'info',
        html: '        <div class="container">' +
                '            <div class="row"> ' +
                '<div class="col-sm-12">' +
                '   <form>' +
                '      <h2>Ingresar solo los campos que desea actualizar</h2>' +
                '     <div class="input-group col-sm-12" style="display: none">' +
                '        <p>Número de Cuenta: &nbsp; </p>' +
                '       <input type="text" class="form-control" placeholder="' + numeroDeCuenta + '" value="' + numeroDeCuenta + '" id="numeroDeCuenta">' +
                '  </div>' +
                ' <div class="input-group col-sm-12">' +
                '    <p>Nombre: &nbsp; </p>' +
                '    <input type="text" class="form-control" placeholder="' + nombre + '" value="' + nombre + '" id="nombre">' +
                '        </div>' +
                '       <div class="input-group col-sm-12">' +
                '          <p>eMail &nbsp; </p>' +
                '         <input type="text" class="form-control" placeholder="' + eMail + '" value="' + eMail + '" id="eMail">' +
                '    </div>' +
                '   <div class="input-group col-sm-12">' +
                '      <p>Apellido &nbsp; </p>' +
                '     <input type="text" class="form-control" placeholder="' + apellido + '" value="' + apellido + '" id="apellido">' +
                '</div>' +
                '       <div class="input-group col-sm-12">' +
                '          <p>Monto &nbsp; </p>' +
                '         <input type="text" class="form-control" placeholder="' + monto + '" value="' + monto + '" id="monto">' +
                '    </div>' +
                '   <div class="input-group col-sm-12">' +
                '      <p>Fecha De Vencimiento &nbsp; </p>' +
                '     <input type="date" class="form-control" id="fechaDeVencimiento" value="' + fechaDeVencimiento + '" >' +
                '</div>' +
                '        <div class="input-group col-sm-12">' +
                '           <p>Teléfono de Casa: &nbsp; </p>' +
                '          <input type="text" class="form-control" placeholder="' + telefonoDeCasa + '" value="' + telefonoDeCasa + '" id="telefonoDeCasa">' +
                '     </div>' +
                '   <div class="input-group col-sm-12">' +
                '       <p>Teléfono Movil: &nbsp; </p>' +
                '      <input type="text" class="form-control" placeholder="' + telefonoMovil + '" value="' + telefonoMovil + '" id="telefonoMovil">' +
                ' </div>' +
                '<div class="input-group col-sm-12">' +
                '            <p>Número de factura: &nbsp; </p>' +
                '           <input type="text" class="form-control" placeholder="' + numeroDeFactura + '" value="' + numeroDeFactura + '" id="numeroDeFactura">' +
                '      </div>' +
                '     <div class="input-group col-sm-12">' +
                '        <p>Status: &nbsp; </p>' +
                '      <select class="form-control" id="status">' +
                '        <option '+isselected(0, status)+'>0</option>' +
                '        <option '+isselected(1, status)+'>1</option>' +
                '   </select>' +
                '        </div>' +
                '   </form>' +                
                '</div>' +
                '</div>' +
                '</div>',
        showCloseButton: true,
        showCancelButton: true,
        focusConfirm: false,
        confirmButtonText:
                'READY!',
        confirmButtonAriaLabel: 'Thumbs up, great!',
        cancelButtonText:
                'EXIT',
        cancelButtonAriaLabel: 'Thumbs down'
    }).then((result) => {
        if (result.value) {
            modificarBD();
        }
    });
    

    function modificarBD() {
        var numerodecuenta = document.getElementById('numeroDeCuenta').value;
        var nombre = document.getElementById('nombre').value;
        var email = document.getElementById('eMail').value;
        var apellido = document.getElementById('apellido').value;
        var monto = document.getElementById('monto').value;
        var fechadevencimiento = document.getElementById('fechaDeVencimiento').value;
        var telefonodecasa = document.getElementById('telefonoDeCasa').value;
        var telefonomovil = document.getElementById('telefonoMovil').value;
        var numerodefactura = document.getElementById('numeroDeFactura').value;
        var status = document.getElementById('status').value;

        console.log(numerodecuenta);
        console.log(nombre);
        console.log(email);
        console.log(apellido);
        console.log(monto);
        console.log(fechadevencimiento);
        console.log(telefonodecasa);
        console.log(telefonomovil);
        console.log(numerodefactura);
        console.log(status);

        var data = new FormData();
        data.append("action", "editNotificacion");
        data.append("numerodecuenta", numerodecuenta);
        data.append("nombre", nombre);
        data.append("apellido", apellido);
        data.append("telefonocasa", telefonodecasa);
        data.append("telefonomovil", telefonomovil);
        data.append("eMail", email);
        data.append("monto", monto);
        data.append("numerodefactura", numerodefactura);
        data.append("fechadevencimiento", fechadevencimiento);
        data.append("status", status);

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    var elementoAModificar = document.getElementById(idNotificacionMpdificada);
                    elementoAModificar.children[0].textContent = jsonObject.numerodecuenta;
                    elementoAModificar.children[1].textContent = jsonObject.nombre;
                    elementoAModificar.children[2].textContent = jsonObject.email;
                    elementoAModificar.children[3].textContent = jsonObject.apellido;
                    elementoAModificar.children[4].textContent = jsonObject.monto;
                    elementoAModificar.children[5].textContent = jsonObject.fechadevencimiento;
                    elementoAModificar.children[6].textContent = jsonObject.telefonocasa;
                    elementoAModificar.children[7].textContent = jsonObject.telefonomovil;
                    elementoAModificar.children[8].textContent = jsonObject.numerodefactura;
                    elementoAModificar.children[9].textContent = jsonObject.statusBD;
                    elementoAModificar.setAttribute('id', jsonObject.numerodecuenta + "," + jsonObject.nombre);
                    Swal.fire(
                            'Éxito!',
                            'La notificación ha sido modificada',
                            'success'
                            );
                }
                if (jsonObject.status === "error") {
                    Swal.fire(
                            'Error!',
                            'NO se ha podido modificar la notificación',
                            'error'
                            )
                }
            }
        });

        xhr.open("POST", absolutepath + "NotificacionVencimientoDePago");
        xhr.send(data);


    }

}

function borrar(_this) {
    console.log("Borrar");
    console.log(_this.parentNode.parentNode.parentNode.parentNode.id);
    var idTD = _this.parentNode.parentNode.parentNode.parentNode.id;
    var split = idTD.split(",");
    var numeroDeCuenta = split[0];
    var nombre = split[1];
    Swal.fire({
        title: 'Seguro que deseas borrar la siguiente notificación?',
        text: "Numero de Cuenta: " + numeroDeCuenta + " a Nombre de: " + nombre + "",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, estoy seguro!'
    }).then((result) => {
        if (result.value) {
            borrarNotificacion();
        }
    });

    function borrarNotificacion() {

        var data = new FormData();
        data.append("action", "borrarNotificacion");
        data.append("numerodecuenta", numeroDeCuenta);
        data.append("nombre", nombre);

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = false;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    var node = document.getElementById(idTD);
                    node.parentNode.removeChild(node);
                    Swal.fire(
                            'Éxito!',
                            'La notificación ha sido borrada correctamente',
                            'success'
                            );
                }
                if (jsonObject.status === "error") {
                    Swal.fire(
                            'Error!',
                            'NO se ha podido borrar la notificación',
                            'error'
                            )
                }
            }
        });

        xhr.open("POST", absolutepath + "NotificacionVencimientoDePago");
        xhr.send(data);

        Swal.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success'
                );
    }
}

function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}

$('#btnLogOut').click( function() {

    var data = new FormData();
    data.append("action", "LogOut");
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            try {
                var result = JSON.parse(this.responseText);
                if (result.status === "ok") {
                    window.location.href = "https://breeze2-196.collaboratory.avaya.com/services/AAADEVPoCDemoPage/Demos";
                } else {                    
                    alert("Error: " + result.message);
                }
            } catch (error) {
            	alert("Error: " + error);
            }

        }
    });

    xhr.open("POST", "LogIn");
    xhr.send(data);

});
