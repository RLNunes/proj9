
/************************* Termo *************************** */
/************************ Lista todas os Termos ****************************** */
async function getAllTermos(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos termos");
        } else {
            let objTerm = await response.json();
            let Termos = objTerm.allTermos
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome Termo</th><th scope="col">capitania</th><th scope="col">Freguesias</th><th scope="col">Comarcas</th><th scope="col">2º Termo</th>' +
                '</tr>';
            let AuxTermo2;
            for (const Termo of Termos) {
                if (Termo.termo2 == null) {
                    AuxTermo2 = ' ';
                } else {
                    AuxTermo2 = Termo.termo2;
                }
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTermo(`+ Termo.id + `)"></i></td>
                                    <td scope="row">` + Termo.nome_termo + `</td>
                                    <td scope="row">` + Termo.capitania + `</td>
                                    <td scope="row">` + Termo.freguesias + `</td>
                                    <td scope="row">` + Termo.comarca + `</td>
                                    <td scope="row">` + AuxTermo2 + `</td>
                                </tr>`;

            }

            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objTerm.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllTermos(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableTermo").innerHTML = tabela;
        }
    })
}


/************************ Procurar as termos ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllTermos(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/search/" + v_search;
        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos termos");
            } else {
                Termos = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome Termo</th><th scope="col">capitania</th><th scope="col">Freguesias</th><th scope="col">Comarcas</th><th scope="col">2º Termo</th>' +
                    '</tr>';
                let AuxTermo2;
                let conta = 0;
                for (const Termo of Termos) {
                    if (Termo.termo2 == null) {
                        AuxTermo2 = ' ';
                    } else {
                        AuxTermo2 = Termo.termo2;
                    }
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTermo(`+ Termo.id + `)"></i></td>
                                    <td scope="row">` + Termo.nome_termo + `</td>
                                    <td scope="row">` + Termo.capitania + `</td>
                                    <td scope="row">` + Termo.freguesias + `</td>
                                    <td scope="row">` + Termo.comarca + `</td>
                                    <td scope="row">` + AuxTermo2 + `</td>
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
                document.getElementById("TableTermo").innerHTML = tabela;
            }
        });
    }
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
            let selectbox = '<label for="capTermoSel" ><b>Capitania</b></label>' +
                '<select class="form-control" id="capTermoSel" name="capTermoSel" placeholder="Termo">' +
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



/****************** lista todas as Freguesias  ******************* */
async function listFreguesias(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_freguesia/allTermos/" + v_id;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    document.getElementById('procFreg').onclick = function () { selectFreguesia(document.getElementById('procFreguesia').value); }
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das Freguesias");
        } else {
            let Freguesias = await response.json();
            let tableFreguesia = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableFreguesia" ><b>Freguesia:</b></label>' +
                '</div><div class="column listTabBotao" align="right"><a href="javascript:selectFreguesia(`all`)"><img src="../img/addLine.png" width="35"></a> ' +
                '<a href="javascript:delFreguesia(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
                '<table id="tableFreguesiaSel" class="listPopUp">';

            for (const Freguesia of Freguesias) {

                tableFreguesia += '<tr><td class="checkDel"><i class="checkDelFreguesia far fa-circle" style="color:red;" onclick="checkDelFreguesia(this);" value="' + Freguesia.id + '"></i></td>' +
                    '<td>' + Freguesia.freguesia + '</td></tr>'


            }
            tableFreguesia += `</table>`;
            document.getElementById("freguesiaSel").innerHTML = tableFreguesia;
        }
    })
}




/********************* Lista do Freguesias not in Termo  ***************************** */
async function selectFreguesia(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_freguesia/notInTermo/" + document.getElementById("termoId").value + "/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Freguesias = await response.json();
            let tableFreguesia = '<table id="tableFreguesia" class="ListPopUpModal">';
            let conta = 1;
            for (const Freguesia of Freguesias) {

                tableFreguesia += '<tr><td><a href="javascript:addFreguesia(' + Freguesia.id + ',`' + document.getElementById("termoId").value + '`)">' + Freguesia.freguesia + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableFreguesia += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por favor afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableFreguesia += `</table>`;

            document.getElementById("tabFreguesia").innerHTML = tableFreguesia;

            $('#modalFreguesia').modal('show');
        }
    })
}



/****************** lista todas as Comarcas  ******************* */
async function listComarcas(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_comarca/allTermos/" + v_id + "/all";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    document.getElementById('procComarca').onclick = function () { selectComarca(document.getElementById('procComarca').value); }
    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das Comarcas");
        } else {
            let Comarcas = await response.json();
            let tableComarca = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableComarca" ><b>Comarca:</b></label>' +
                '</div><div class="column listTabBotao" align="right"><a href="javascript:selectComarca()"><img src="../img/addLine.png" width="35"></a> ' +
                '<a href="javascript:delComarca(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
                '<table id="tableComarcaSel" class="listPopUp">';

            for (const Comarca of Comarcas) {

                tableComarca += '<tr><td class="checkDel"><i class="checkDelComarca far fa-circle" style="color:red;" onclick="checkDelComarca(this);" value="' + Comarca.id + '"></i></td>' +
                    '<td>' + Comarca.comarca + '</td></tr>'


            }
            tableComarca += `</table>`;
            document.getElementById("comarcaSel").innerHTML = tableComarca;
        }
    })
}

/********************* Lista das Comarcas not in Termo  ***************************** */
async function selectComarca(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_comarca/notInTermo/" + document.getElementById("termoId").value + "/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Comarcas = await response.json();
            let tableComarca = '<table id="tableComarca" class="ListPopUpModal">';
            let conta = 1;
            for (const Comarca of Comarcas) {

                tableComarca += '<tr><td><a href="javascript:addComarca(' + Comarca.id + ',`' + document.getElementById("termoId").value + '`)">' + Comarca.comarca + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableComarca += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableComarca += `</table>`;

            document.getElementById("tabComarca").innerHTML = tableComarca;

            $('#modalComarca').modal('show');
        }
    })
}

/********************* Lista do 2º Termo  ***************************** */
async function selectTermo2(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/search/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Termos = await response.json();
            let tableTermos = '<table id="table2Termo" class="ListPopUpModal">';
            let conta = 1;
            for (const termo of Termos) {

                tableTermos += '<tr><td><a href="javascript:add2Termo(' + termo.id + ',`' + termo.nome_termo + '`)">' + termo.nome_termo + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableTermos += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableTermos += `</table>`;

            document.getElementById("tabTermo").innerHTML = tableTermos;
        }
    })
}

/************************ devolve a termo pedido ****************************** */
async function getTermo(v_termoId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/record/" + v_termoId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos termos");
        } else {
            $('#criarTermo').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            termo = await response.json();
            document.getElementById("termoId").value = v_termoId;
            document.getElementById("termoAdd").value = termo.nome_termo;
            getAllMCapitania(termo.capitania);
            listFreguesias(v_termoId);
            listComarcas(v_termoId);
            document.getElementById("2termo").value = termo.termo_nome2Desc;
            document.getElementById("termoValue").value = termo.termo_nome2;

        }
    })
}

/**************** Adiciona á text box do Termo 2 ************************ */
function add2Termo(v_id, v_desc) {
    document.getElementById("2termo").value = v_desc;
    document.getElementById("termoValue").value = v_id;
    $('#modalTermo').modal('hide');
}

// função de criação de novo Termo
async function addTermo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("termoAdd").value == "") {
        errForm += "Termo é obrigatório.<br>";
    }
    if (document.getElementById("capTermoSel").value == "") {
        errForm += "Capitania é obrigatória.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        let vTermo2;
        if (document.getElementById("termoValue").value == 0) {
            vTermo2 = null;
        } else {
            vTermo2 = document.getElementById("termoValue").value;
        }
        var objCons = {
            nome_termo: document.getElementById("termoAdd").value,
            capitania: document.getElementById("capTermoSel").value,
            termo2: vTermo2
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: async function (result) {
                let termoId = result;
                /* Adicionar freguesia escolhidas  ao termo que foi criado*/
                let elements = document.getElementsByClassName('checkDelFreguesia');
                for (let element of elements) {
                    var objTermFreg = {
                        termo: termoId,
                        freguesia: element.attributes.value.value
                    };
                    var exit = "no";
                    var json = JSON.stringify(objTermFreg);

                    var myHeaders = new Headers();
                    myHeaders.append("token", await getAdminToken());
                    $.ajax({
                        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_freguesia/insert",
                        type: "POST",
                        headers: { "token": await getAdminToken() },
                        data: json,
                        dataType: "json",

                    });
                }
                Swal.fire({
                    icon: "success",
                    title: "Termo Criado.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTermo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Termo",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTermo').modal('hide');
                });
            }
        });
    }
}



// função de criação de novo Termo
async function saveTermo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("termoAdd").value == "") {
        errForm += "Termo é obrigatório.<br>";
    }
    if (document.getElementById("capTermoSel").value == "") {
        errForm += "Capitania é obrigatória.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        let vTermo2;
        if (document.getElementById("termoValue").value == 0) {
            vTermo2 = null;
        } else {
            vTermo2 = document.getElementById("termoValue").value;
        }
        var objCons = {
            nome_termo: document.getElementById("termoAdd").value,
            capitania: document.getElementById("capTermoSel").value,
            termo2: vTermo2
        };
        console.log(document.getElementsByName("registroRadio").value);
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/update/" + document.getElementById("termoId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Termo alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTermo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar Termo",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTermo').modal('hide');
                });
            }
        });
    }
}


/************************** Freguesia ***************************** */
/********************* Adicionar Freguesia ************************* */
async function addFreguesia(v_freg_id, v_termo_id) {

    var objFreg = {
        termo: v_termo_id,
        freguesia: v_freg_id
    };
    var json = JSON.stringify(objFreg);

    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_freguesia/insert",
        type: "POST",
        headers: { "token": await getAdminToken() },
        data: json,
        dataType: "json",
        success: function (result) {
            Swal.fire({
                icon: "success",
                title: "Freguesia adicionada.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalFreguesia').modal('hide');
                listFreguesias(v_termo_id);
            });

        },
        error: function (err) {
            console.log(err);
            exit = err;
            Swal.fire({
                icon: "success",
                title: "Freguesia adicionada.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalFreguesia').modal('hide');
                listFreguesias(v_termo_id);
            });
        }
    });
}



/********************* Retirar Freguesia ************************* */
async function delFreguesia(v_termo_id) {
    // vai percorrer todos os item da página que utilizem a class checked
    // Se checked e clicar eliminar remove da lista os selecionados
    let elements = document.getElementsByClassName('checkDelFreguesia fa fa-check-circle');
    let v_freg_id;
    for (let element of elements) {
        v_freg_id = element.attributes.value.value;

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_freguesia/delete/" + v_termo_id + "/" + v_freg_id,
            type: "DELETE",
            headers: { "token": await getAdminToken() },
            data: "",
            dataType: "json"
        });
    }
    Swal.fire({
        icon: "success",
        title: "Freguesia(s) removida(s) com sucesso.",
        showConfirmButton: false,
        timer: 1500,
    }).then(function () {
        listFreguesias(v_termo_id);
    });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelFreguesia(v_obj) {
    if (v_obj.className == 'checkDelFreguesia far fa-circle') {
        v_obj.className = 'checkDelFreguesia fa fa-check-circle';
    } else {
        v_obj.className = 'checkDelFreguesia far fa-circle';
    }
}



function addFreguesiaAdd(v_id, v_desc) {
    var table = document.getElementById("tableFreguesiaSelAdd");
    var row = table.insertRow();
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<i class="checkDelFreguesia far fa-circle" style="color:red;" onclick="checkDelFreguesia(this);" value="' + v_id + '"></i>';
    cell2.innerHTML = v_desc;
    $('#modalFreguesia').modal('hide');
}

function delFreguesiaAdd(v_id) {
    const elements = document.getElementsByClassName('checkDelFreguesia');
    let v_linha = 0;
    console.log(elements)
    for (let element of elements) {
        if (element.attributes.class.value == 'checkDelFreguesia fa fa-check-circle') {
            document.getElementById("tableFreguesiaSelAdd").deleteRow(v_linha);
        }
        v_linha += 1;
    }
}

/*********************** Devolve lista das Freguesias ************************************** */
async function selectFreguesiaAdd(v_nomePesq) {

    /* $('#criarPes').modal('hide'); */
    $('#modalFreguesia').modal('show');
    document.getElementById('procFreg').onclick = function () { selectFreguesiaAdd(document.getElementById('procFreguesia').value); }
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/search/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listFreguesias = await response.json();
            let tableFreguesia = '<table id="tableSelFreguesia" class="ListPopUpModal">';
            let conta = 1;
            for (const listFreguesia of listFreguesias) {

                tableFreguesia += '<tr><td><a href="javascript:addFreguesiaAdd(' + listFreguesia.id + ',`' + listFreguesia.nome_freguesia + '`)">' + listFreguesia.nome_freguesia + ' </a></td>' +
                    '</tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableFreguesia += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableFreguesia += `</table>`;
            document.getElementById("tabFreguesia").innerHTML = tableFreguesia;
        }
    })
}


/************************** Comarcas ***************************** */
/********************* Adicionar Comarca ************************* */
async function addComarca(v_coma_id, v_termo_id) {

    var obj = {
        termo: v_termo_id,
        comarca: v_coma_id
    };
    var json = JSON.stringify(obj);

    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_comarca/insert",
        type: "POST",
        headers: { "token": await getAdminToken() },
        data: json,
        dataType: "json",
        success: function (result) {
            Swal.fire({
                icon: "success",
                title: "Comarca adicionada.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalComarca').modal('hide');
                listComarcas(v_termo_id);
            });

        },
        error: function (err) {
            console.log(err);
            exit = err;
            Swal.fire({
                icon: "success",
                title: "Comarca adicionada.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalComarca').modal('hide');
                listComarcas(v_termo_id);
            });
        }
    });
}



/********************* Retirar Comarca ************************* */
async function delComarca(v_termo_id) {
    // vai percorrer todos os item da página que utilizem a class checked
    // Se checked e clicar eliminar remove da lista os selecionados
    let elements = document.getElementsByClassName('checkDelComarca fa fa-check-circle');
    let v_coma_id;
    for (let element of elements) {
        v_coma_id = element.attributes.value.value;

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_comarca/delete/" + v_termo_id + "/" + v_coma_id,
            type: "DELETE",
            headers: { "token": await getAdminToken() },
            data: "",
            dataType: "json"
        });
    }
    Swal.fire({
        icon: "success",
        title: "Comarca(s) removida(s) com sucesso.",
        showConfirmButton: false,
        timer: 1500,
    }).then(function () {
        listComarcas(v_termo_id);
    });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelComarca(v_obj) {
    if (v_obj.className == 'checkDelComarca far fa-circle') {
        v_obj.className = 'checkDelComarca fa fa-check-circle';
    } else {
        v_obj.className = 'checkDelComarca far fa-circle';
    }
}



function addComarcaAdd(v_id, v_desc) {
    var table = document.getElementById("tableComarcaSelAdd");
    var row = table.insertRow();
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<i class="checkDelComarca far fa-circle" style="color:red;" onclick="checkDelComarca(this);" value="' + v_id + '"></i>';
    cell2.innerHTML = v_desc;
    $('#modalComarca').modal('hide');
}

function delComarcaAdd(v_id) {
    const elements = document.getElementsByClassName('checkDelComarca');
    let v_linha = 0;
    console.log(elements)
    for (let element of elements) {
        if (element.attributes.class.value == 'checkDelComarca fa fa-check-circle') {
            document.getElementById("tableComarcaSelAdd").deleteRow(v_linha);
        }
        v_linha += 1;
    }
}

/*********************** Devolve lista das Comarcas ************************************** */
async function selectComarcaAdd(v_nomePesq) {

    /* $('#criarPes').modal('hide'); */
    $('#modalComarca').modal('show');
    document.getElementById('procComa').onclick = function () { selectComarcaAdd(document.getElementById('procComarca').value); }
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listComarcas = await response.json();
            let tableComarca = '<table id="tableSelComarca" class="ListPopUpModal">';
            let conta = 1;
            for (const listComarca of listComarcas) {

                tableComarca += '<tr><td><a href="javascript:addComarcaAdd(' + listComarca.id + ',`' + listComarca.nome_comarca + '`)">' + listComarca.nome_comarca + ' </a></td>' +
                    '</tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableComarca += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableComarca += `</table>`;
            document.getElementById("tabComarca").innerHTML = tableComarca;
        }
    })
}

function delTermo() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta termo?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/delete/" + document.getElementById("termoId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Termo eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTermo').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o termo.",
                        text: "Este termo encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTermo').modal('hide');
                    });
                }
            });
        }
    });
}