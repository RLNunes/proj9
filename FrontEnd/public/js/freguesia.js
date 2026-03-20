/************************* Freguesias *************************** */
/************************ Lista todas as Freguesias ****************************** */
async function getAllFreguesias(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das freguesias");
        } else {
            objFreg = await response.json();
            Freguesias = objFreg.allFreguesias;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome Freguesia</th><th scope="col">Comarca</th><th scope="col">Capitania</th>' +
                '</tr>';
            for (const Freguesia of Freguesias) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getFreguesia(`+ Freguesia.id + `)"></i></td>
                                    <td scope="row">` + Freguesia.nome_freguesia + `</td><td scope="row">` + Freguesia.comarcadesc + `</td>
                                    <td scope="row">` + Freguesia.capitaniadesc + `</td>
                                </tr>`

            }
            tabela += `</table>`;
            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objFreg.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllFreguesias(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableFreg").innerHTML = tabela;
        }
    })
}


/************************ Procurar as Freguesias ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllFreguesias(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das freguesias");
            } else {
                Freguesias = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome Freguesia</th><th scope="col">Comarca</th><th scope="col">Capitania</th>' +
                    '</tr>';
                let conta = 0;
                for (const Freguesia of Freguesias) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getFreguesia(`+ Freguesia.id + `)"></i></td>
                                    <td scope="row">` + Freguesia.nome_freguesia + `</td><td scope="row">` + Freguesia.comarcadesc + `</td>
                                    <td scope="row">` + Freguesia.capitaniadesc + `</td>
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
                document.getElementById("TableFreg").innerHTML = tabela;
            }
        })
    }
}


/************************ devolve a freguesia pedida ****************************** */
async function getFreguesia(v_fregId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/record/" + v_fregId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das Freguesias.");
        } else {
            $('#criarFreg').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let freguesia = await response.json();
            document.getElementById("fregAdd").value = freguesia.nome_freguesia;
            document.getElementById("fregId").value = v_fregId;

            getAllMCapitania(freguesia.capitania);
            getAllComarca(freguesia.comarca);

        }
    })
}


/****************** Capitania ******************* */
async function getAllMCapitania(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/capitania/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das capitanias");
        } else {
            let Capitanias = await response.json();
            let selectbox = '<label for="capFregSel" ><b>Capitania</b></label>' +
                '<select class="form-control" id="capFregSel" name="capFregSel" placeholder="Termo">' +
                '<option value="">&nbsp;</option>';
            let selected = '';
            for (const capitania of Capitanias) {

                if (v_id == capitania.id) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + capitania.id + '" ' + selected + '>' + capitania.nome_capitania + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("capitaniaSel").innerHTML = selectbox;
        }
    })
}



/****************** Comarca ******************* */
async function getAllComarca(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das comarcas");
        } else {
            let Comarcas = await response.json();
            let selectbox = '<label for="comFregSel" ><b>Comarca</b></label>' +
                '<select class="form-control" id="comFregSel" name="comFregSel" placeholder="Termo">' +
                '<option value="">&nbsp;</option>';
            let selected = '';
            for (const Comarca of Comarcas) {

                if (v_id == Comarca.id) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + Comarca.id + '" ' + selected + '>' + Comarca.nome_comarca + '</option>'

            }
            selectbox += `</select>`;
            document.getElementById("comarcaSel").innerHTML = selectbox;
        }
    })
}


// função de criação de nova Freguesia
async function addFreguesia() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("fregAdd").value == "") {
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
            nome_freguesia: document.getElementById("fregAdd").value,
            capitania: document.getElementById("capFregSel").value,
            comarca: document.getElementById("comFregSel").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Freguesia criada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarFreg').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Freguesia",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarFreg').modal('hide');
                });
            }
        });
    }
}



// função de alterar freguesia
async function saveFreguesia() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("fregAdd").value == "") {
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
            nome_freguesia: document.getElementById("fregAdd").value,
            capitania: document.getElementById("capFregSel").value,
            comarca: document.getElementById("comFregSel").value
        };
        console.log(document.getElementsByName("registroRadio").value);
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/update/" + document.getElementById("fregId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Freguesia alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarFreg').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar Freguesia.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarFreg').modal('hide');
                });
            }
        });
    }
}

function delFreguesia() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta fregusia?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/delete/" + document.getElementById("fregId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    sessionStorage.setItem("v_idCons", result);
                    Swal.fire({
                        icon: "success",
                        title: "Freguesia eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarFreg').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar Freguesia.",
                        text: "Esta freguesia encontra-se associada a m registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarFreg').modal('hide');
                    });
                }
            });
        }
    });
}