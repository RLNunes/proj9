/************************* Registros *************************** */
/************************ Lista todos os Registros ****************************** */
async function getAllRegistros(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos registros");
        } else {
            objReg = await response.json();
            Registros = objReg.allRegistros;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome registro</th>' +
                '</tr>';
            for (const Registro of Registros) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getRegistro(`+ Registro.pk_registro + `)"></i></td>
                                    <td scope="row">` + Registro.descricao + `</td>
                                </tr>`
            }
            
            tabela += `</table>`;
            
            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objReg.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllRegistros(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableRegistro").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Registros ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllRegistros(1,10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/search/" + v_search;
    }
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos registros");
        } else {
            let Registros;
            if (v_search == null || v_search == '') {
                let objReg = await response.json();
                Registros = objReg.allRegistros
            }else{
                Registros = await response.json();
            }
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome Registro</th>' +
                '</tr>';
            let conta = 0;
            console.log(urlBase);
            for (const Registro of Registros) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getRegistro(`+ Registro.id + `)"></i></td>
                                    <td scope="row">` + Registro.descricao + `</td>
                                </tr>`
                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tabela += '<tr><td colspan="4">Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tabela += `</table>`;
            document.getElementById("TableRegistro").innerHTML = tabela;
        }
    })
}


/************************ devolve o Registro pedido ****************************** */
async function getRegistro(v_registroId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/record/" + v_registroId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o registro.");
        } else {
            $('#criarRegistro').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let Registro = await response.json();
            document.getElementById("registroAdd").value = Registro.descricao;
            document.getElementById("registroId").value = v_registroId;

        }
    })
}


// função de criação de novo registro
async function addRegistro() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("registroAdd").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        var objCons = {
            descricao: document.getElementById("registroAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Registro criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRegistro').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar registro.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRegistro').modal('hide');
                });
            }
        });
    }
}



// função de alterar Registro
async function saveRegistro() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("registroAdd").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        var objCons = {
            descricao: document.getElementById("registroAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/update/" + document.getElementById("registroId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Registro alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRegistro').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar registro.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRegistro').modal('hide');
                });
            }
        });
    }
}

function delRegistro() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta registro?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/delete/" + document.getElementById("registroId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Registro eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarRegistro').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o registro.",
                        text: "Este registro encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarRegistro').modal('hide');
                    });
                }
            });
        }
    });
}