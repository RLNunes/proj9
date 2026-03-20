/************************* Secretario  *************************** */
/************************ Lista todos os Secretarios ****************************** */
async function getAllSecretario(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos Secretários");
        } else {
            let objSecretario = await response.json();
            let Secretarios = objSecretario.allSecretarioConselheiro;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Secretário/Conselheiro</th><th scope="col">Agregador</th>' +
                '</tr>';
            for (const Secretario of Secretarios) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getSecretario(`+ Secretario.pk_secretario_conselheiro + `)"></i></td>
                                    <td scope="row">` + Secretario.descricao + `</td><td scope="row">` + Secretario.secretario_agregador + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objSecretario.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllSecretario(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableSecretario").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Secretario ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllSecretario(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos secretarios");
            } else {
                Secretarios = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Secretário/conselheiro</th><th scope="col">Agregador</th>' +
                    '</tr>';
                let conta = 0;
                for (const Secretario of Secretarios) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getSecretario(`+ Secretario.pk_secretario_conselheiro + `)"></i></td>
                                    <td scope="row">` + Secretario.descricao + `</td><td scope="row">` + Secretario.secretario_agregador + `</td>
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
                document.getElementById("TableSecretario").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve o Secretario ****************************** */
async function getSecretario(v_secretarioId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/record/" + v_secretarioId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o secretario.");
        } else {
            $('#criarSecretario').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let Secretario = await response.json();
            document.getElementById("secretarioIdAdd").value = Secretario.designacao;
            document.getElementById("secretarioId").value = v_secretarioId;
            getAllAgregador(Secretario.secretario_agregador);

        }
    })
}


/****************** Select Agregador ******************* */
async function getAllAgregador(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_agregador/search/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos agregadores");
        } else {
            let agregas = await response.json();
            let selectbox = '<label for="agrega" ><b>Agregador</b></label>' +
                '<select class="form-control" id="agrega" name="agrega" placeholder="Agregador">' +
                '<option value="">&nbsp;</option>';
            let selected = '';
            for (const agrega of agregas) {
                if (v_id == agrega.pk_secretario_agregador) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + agrega.pk_secretario_agregador + '" ' + selected + '>' + agrega.designacao + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("agregaSel").innerHTML = selectbox;
        }
    })
}


// função de criação de novo Secretario
async function addSecretario() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("secretarioIdAdd").value == "") {
        errForm += "Secretario é obrigatório.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        var obj = {
            designacao: document.getElementById("secretarioIdAdd").value,
            secretario_agregador: document.getElementById("agrega").value
        };
        var exit = "no";
        var json = JSON.stringify(obj);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Secretário criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarSecretario').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar secretário.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarSecretario').modal('hide');
                });
            }
        });
    }
}



// função de alterar Secretario
async function saveSecretario() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("secretarioIdAdd").value == "") {
        errForm += "Secretario é obrigatório.<br>";
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
            designacao: document.getElementById("secretarioIdAdd").value,
            secretario_agregador: document.getElementById("agrega").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/update/" + document.getElementById("secretarioId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Secretário alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarSecretario').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar secretário.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarSecretario').modal('hide');
                });
            }
        });
    }
}

function delSecretario() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este secretário?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/secretario_conselheiro/delete/" + document.getElementById("secretarioId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Secretário eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarSecretario').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o secretário.",
                        text: "Este secretário encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarSecretario').modal('hide');
                    });
                }
            });
        }
    });
}