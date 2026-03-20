/************************* Tema  *************************** */
/************************ Lista todos os Temas ****************************** */
async function getAllTema(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos temas");
        } else {
            let objTemas = await response.json();
            let Temas = objTemas.allTemas;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Tema</th><th scope="col">Agregador</th>' +
                '</tr>';
            for (const Tema of Temas) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTema(`+ Tema.pk_tema + `)"></i></td>
                                    <td scope="row">` + Tema.tema + `</td><td scope="row">` + Tema.agregadordesc + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objTemas.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllTema(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableTema").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Tema ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllTema(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos temas");
            } else {
                Temas = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Tema</th><th scope="col">Agregador</th>' +
                    '</tr>';
                let conta = 0;
                for (const Tema of Temas) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTema(`+ Tema.pk_tema + `)"></i></td>
                                    <td scope="row">` + Tema.tema + `</td><td scope="row">` + Tema.agregadordesc + `</td>
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
                document.getElementById("TableTema").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve o Tema ****************************** */
async function getTema(v_temaId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/record/" + v_temaId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o tema.");
        } else {
            $('#criarTema').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let Tema = await response.json();
            document.getElementById("temaAdd").value = Tema.tema;
            document.getElementById("temaId").value = v_temaId;
            getAllAgregador(Tema.agregador);

        }
    })
}


/****************** Select Agregador ******************* */
async function getAllAgregador(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/search/all";
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
                if (v_id == agrega.pk_tema_agregador) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + agrega.pk_tema_agregador + '" ' + selected + '>' + agrega.designacao + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("agregaSel").innerHTML = selectbox;
        }
    })
}


// função de criação de novo Tema
async function addTema() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("temaAdd").value == "") {
        errForm += "Tema é obrigatório.<br>";
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
            tema: document.getElementById("temaAdd").value,
            agregador: document.getElementById("agrega").value
        };
        var exit = "no";
        var json = JSON.stringify(obj);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Tema criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTema').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Marcador.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTema').modal('hide');
                });
            }
        });
    }
}



// função de alterar Tema
async function saveTema() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("temaAdd").value == "") {
        errForm += "Tema é obrigatório.<br>";
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
            tema: document.getElementById("temaAdd").value,
            agregador: document.getElementById("agrega").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/update/" + document.getElementById("temaId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Tema alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTema').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar tema.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTema').modal('hide');
                });
            }
        });
    }
}

function delTema() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este tema?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema/delete/" + document.getElementById("temaId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Tema eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTema').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o tema.",
                        text: "Este tema encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTema').modal('hide');
                    });
                }
            });
        }
    });
}