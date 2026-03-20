/************************* Oficio Titulo *************************** */
/************************ Lista todas os Oficios Titulos ****************************** */
async function getAllOfiTitulo(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos ofícios/títulos");
        } else {
            let objOfiTitulo = await response.json();
            let ofiTitulos = objOfiTitulo.allOficioTitulo;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Ofício/Título</th><th>Agregador</th>' +
                '</tr>';
            for (const ofiTitulo of ofiTitulos) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getOfiTitulo(`+ ofiTitulo.pk_oficio_titulo + `)"></i></td>
                                    <td scope="row">` + ofiTitulo.designacao + `</td>
                                    <td scope="row">` + ofiTitulo.oficio_agregador + `</td>
                                </tr>`

            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objOfiTitulo.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllOfiTitulo(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableOfiTitulo").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Oficio Titulos ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllOfiTitulo(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos ofícios/titulos");
            } else {
                ofiTitulos = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Ofício/Título</th><th>Agregador</th>' +
                    '</tr>';
                let conta = 0;
                for (const ofiTitulo of ofiTitulos) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getOfiTitulo(`+ ofiTitulo.pk_oficio_titulo + `)"></i></td>
                                    <td scope="row">` + ofiTitulo.designacao + `</td>
                                    <td scope="row">` + ofiTitulo.oficio_agregador + `</td>
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
                document.getElementById("TableOfiTitulo").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve a comarca pedida ****************************** */
async function getOfiTitulo(v_ofiTituloId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/record/" + v_ofiTituloId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o ofício/título.");
        } else {
            $('#criarOfiTitulo').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let ofiTitulo = await response.json();
            document.getElementById("ofiTituloAdd").value = ofiTitulo.designacao;
            document.getElementById("ofiTituloId").value = v_ofiTituloId;
            getAllAgregador(ofiTitulo.oficio_agregador);
        }
    })
}

/****************** Select Agregador ******************* */
async function getAllAgregador(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_agregador/search/all";
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
                if (v_id == agrega.pk_oficio_agregador) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + agrega.pk_oficio_agregador + '" ' + selected + '>' + agrega.designacao + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("agregaSel").innerHTML = selectbox;
        }
    })
}


/************************************************************************************* */
/************************************************************************************** */

// função de criação de novo oficio Titulo
async function addOfiTitulo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("ofiTituloAdd").value == "") {
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
        let vAgregador;
        vAgregador = document.getElementById("agrega").value;
        var objCons = {
            designacao: document.getElementById("ofiTituloAdd").value,
            oficio_agregador: vAgregador
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Ofício/Título criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarOfiTitulo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Ofício/Título.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarOfiTitulo').modal('hide');
                });
            }
        });
    }
}



// função de alterar oficio titulo
async function saveOfiTitulo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("ofiTituloAdd").value == "") {
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
        let vAgregador;
        vAgregador = document.getElementById("agrega").value;
        var objCons = {
            designacao: document.getElementById("ofiTituloAdd").value,
            oficio_agregador: vAgregador
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/update/" + document.getElementById("ofiTituloId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Ofício/Título alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarOfiTitulo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar ofício/título.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarOfiTitulo').modal('hide');
                });
            }
        });
    }
}

function delOfiTitulo() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este Ofício/Título?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/delete/" + document.getElementById("ofiTituloId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    sessionStorage.setItem("v_idCons", result);
                    Swal.fire({
                        icon: "success",
                        title: "Ofício/Título eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarOfiTitulo').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o ofício/título.",
                        text: "Esta ofício/título encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarOfiTitulo').modal('hide');
                    });
                }
            });
        }
    });
}