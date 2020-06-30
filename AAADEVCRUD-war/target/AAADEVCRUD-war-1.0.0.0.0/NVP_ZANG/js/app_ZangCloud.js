/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Creado 20 de Diciembre 2019");
/*
 * VARIABLES
 */
var absolutepath = getAbsolutePath();
console.log(absolutepath);


/*
 * EVENTOS
 */
document.getElementById('launch').addEventListener('click', function (e) {
    e.preventDefault();

    console.log(btoa(document.getElementById('accountSid').textContent + ":" + document.getElementById('accountToken').textContent));
    
    var data = new FormData();
    data.append("action", "postZang");
    data.append("workFlowName", document.getElementById('WFN').textContent);
    data.append("version", document.getElementById('Version').textContent);
    data.append("accountSID", document.getElementById('accountSid').textContent);
    data.append("token", btoa(document.getElementById('accountSid').textContent + ":" + document.getElementById('accountToken').textContent));
    data.append("email", document.getElementById('email').textContent);
    data.append("sms", document.getElementById('SMS').textContent);
    data.append("call", document.getElementById('Call').textContent);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function() {
      if(this.readyState === 4) {
        console.log(this.responseText);
        alert("Petición lanzada");
      }
    });

    xhr.open("POST", absolutepath);
    xhr.send(data);
 
});

document.getElementById('settings').addEventListener('click', function (e) {
    Swal.fire({
        title: '<strong>Settings</strong>',
        icon: 'info',
        html:
                '<form>' +
                '<div class="form-group">' +
                '   <label for="AccountSid">Account SID</label>' +
                '  <input type="text" class="form-control" id="accountSidInput"  placeholder="Account SID">' +
                '                <label for="AccountToken">Account Token</label>' +
                '               <input type="text" class="form-control" id="accountTokenInput"  placeholder="Account Token">          ' +
                '              <label for="WFNInput">WorkFlow Name</label>' +
                '             <input type="text" class="form-control" id="workFlowNameInput" placeholder="WorkFlow Name">' +
                '            <label for="version">Version</label>' +
                '           <input type="email" class="form-control" id="versionInput" placeholder="Version">' +
                '      </div>' +
                '     <div class="form-group form-check">' +
                '        <input type="checkbox" class="form-check-input" id="emailCheck">' +
                '       <label class="form-check-label" for="email">Email</label>' +
                '      <input type="checkbox" class="form-check-input" id="SMSCheck">' +
                '     <label class="form-check-label" for="sms">SMS</label>' +
                '    <input type="checkbox" class="form-check-input" id="CallCheck">' +
                '   <label class="form-check-label" for="Call">Call</label>' +
                ' </div>' +
                ' </form>',
        showCloseButton: true,
        showCancelButton: true,
        focusConfirm: false,
        confirmButtonText:
                'Great!',
        confirmButtonAriaLabel: 'Thumbs up, great!',
        cancelButtonText:
                'Cancel',
        cancelButtonAriaLabel: 'Thumbs down'
    }).then((result) => {
        if (result.value) {
            confirmSettings();
        }
    });
    function confirmSettings() {
        console.log("Confirm Settings");
        document.getElementById('accountSid').textContent = document.getElementById('accountSidInput').value;
        document.getElementById('accountToken').textContent = document.getElementById('accountTokenInput').value;
        document.getElementById('WFN').textContent = document.getElementById('workFlowNameInput').value;
        document.getElementById('Version').textContent = document.getElementById('versionInput').value;

        if (document.getElementById('emailCheck').checked) {
            document.getElementById('email').textContent = 'YES';
        } else {
            document.getElementById('email').textContent = 'NO';
        }

        if (document.getElementById('SMSCheck').checked) {
            document.getElementById('SMS').textContent = 'YES';
        } else {
            document.getElementById('SMS').textContent = 'NO';
        }

        if (document.getElementById('CallCheck').checked) {
            document.getElementById('Call').textContent = 'YES';
        } else {
            document.getElementById('Call').textContent = 'NO';
        }

    }
});


/*
 * FUNCIONES
 */

function changeStatus(_this) {
    console.log(_this.parentNode.children[0].textContent);

    var numeroDeCuenta = _this.parentNode.parentNode.id;
    console.log("numero de cuenta " + numeroDeCuenta);

    //_this.parentNode.children[0].textContent = "1";

    var data = new FormData();
    data.append("action", "changeStatus");
    data.append("numeroDeCuenta", numeroDeCuenta);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = false;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            console.log(this.responseText);
            try {
                var jsonObject = JSON.parse(this.responseText);
                if (jsonObject.status === "ok") {
                    _this.parentNode.children[0].textContent = jsonObject.statusFactura;

                }

                if (jsonObject.status === "error") {
                    alert("Erro : " + jsonObject.message);
                }

            } catch (error) {
                alert("Erro : " + error.toString());
            }
        }
    });

    xhr.open("POST", absolutepath);

    xhr.send(data);
}

function authenticateUser(user, password)
{
    var token = user + ":" + password;

    // Should i be encoding this value????? does it matter???
    // Base64 Encoding -> btoa
    var hash = btoa(token);

    return "Basic " + hash;
}
function getAbsolutePath() {
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 15);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
}

