/************************* Pessoas  *************************** */
/************************ Lista todas as Pessoas ****************************** */
async function getAllPessoas(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das pessoas");
        } else {
            let objPes = await response.json();
            let Pessoas = objPes.allPessoas;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome</th><th scope="col">Ano Nascimento</th><th scope="col">Marcador Económico</th>' +
                '<th scope="col">Sexo</th><th scope="col">Filiação</th><th scope="col">Termo</th>' +
                '<th scope="col">Freguesia</th><th scope="col">Comarca</th><th scope="col">Capitania</th>' +
                '</tr>';
            for (const Pessoa of Pessoas) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getPessoa(`+ Pessoa.id + `)"></i></td>
                                    <td scope="row">` + Pessoa.nome + `</td>
                                    <td scope="row">` + Pessoa.idade + `</td>
                                    <td scope="row">` + Pessoa.marcador_economico_ocupacaoinfo + `</td>
                                    <td scope="row">` + Pessoa.sexo + `</td>
                                    <td scope="row">` + Pessoa.filiacao + `</td>
                                    <td scope="row">` + Pessoa.termoinfo + `</td>
                                    <td scope="row">` + Pessoa.freguesiainfon + `</td>
                                    <td scope="row">` + Pessoa.comarcainfo + `</td>
                                    <td scope="row">` + Pessoa.capitaniainfo + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objPes.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllPessoas(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TablePessoa").innerHTML = tabela;
        }
    })
}

async function search(v_search) {
    if (v_search == null || v_search == '') {
        getAllPessoas(1, 10);
    } else {
        const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/search/" + v_search;
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das pessoas");
            } else {
                Pessoas = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome</th><th scope="col">Ano Nascimento</th><th scope="col">Marcador Económico</th>' +
                    '<th scope="col">Sexo</th><th scope="col">Filiação</th><th scope="col">Termo</th>' +
                    '<th scope="col">Freguesia</th><th scope="col">Comarca</th><th scope="col">Capitania</th>' +
                    '</tr>';
                let conta = 1;
                console.log(Pessoas)
                for (const Pessoa of Pessoas) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getPessoa(`+ Pessoa.id + `)"></i></td>
                                    <td scope="row">` + Pessoa.nome + `</td>
                                    <td scope="row">` + Pessoa.idade + `</td>
                                    <td scope="row">` + Pessoa.marecoocupinfo + `</td>
                                    <td scope="row">` + Pessoa.sexo + `</td>
                                    <td scope="row">` + Pessoa.filiacao + `</td>
                                    <td scope="row">` + Pessoa.termoinfo + `</td>
                                    <td scope="row">` + Pessoa.freguesiainfon + `</td>
                                    <td scope="row">` + Pessoa.comarcainfo + `</td>
                                    <td scope="row">` + Pessoa.capitaniainfo + `</td>
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
                document.getElementById("TablePessoa").innerHTML = tabela;
            }
        });
    }
}

/************************ devolve a Pessoa a pedido ****************************** */
async function getPessoa(v_pessoaId) {

    sessionStorage.setItem("v_idPessoa", v_pessoaId);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/record/" + v_pessoaId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a pessoa.");
        } else {
            $('#criarPessoa').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let pessoa = await response.json();
            document.getElementById("pessoaId").value = v_pessoaId;
            document.getElementById("addNomePes").value = pessoa.nome;
            document.getElementById("addIdadePes").value = pessoa.idade;
            document.getElementById("addSexoPes").value = pessoa.sexo;
            document.getElementById("addFilPes").value = pessoa.filiacao;
            // getAllMarEco(pessoa.marcador_economico_ocupacao);

            listMarEco(v_pessoaId);
            listSocJuridico(v_pessoaId);
            listOfiTitulo(v_pessoaId);
            selectTermo();
            document.getElementById("termoPes").value = pessoa.termoDesc;
            if (pessoa.termo == 0) {
                document.getElementById("termoPesValue").value = "";
            } else {
                document.getElementById("termoPesValue").value = pessoa.termo;
            }
            selectFreguesia();
            document.getElementById("fregPes").value = pessoa.freguesiaDesc;
            if (pessoa.freguesia == 0) {
                document.getElementById("fregPesValue").value = "";
            } else {
                document.getElementById("fregPesValue").value = pessoa.freguesia;
            }
            selectComarca();
            document.getElementById("comaPes").value = pessoa.comarcaDesc;
            if (pessoa.comarca == 0) {
                document.getElementById("comaPesValue").value = "";
            } else {
                document.getElementById("comaPesValue").value = pessoa.comarca;
            }
            selectCapitania();
            document.getElementById("capiPes").value = pessoa.capitaniaDesc;
            if (pessoa.capitania == 0) {
                document.getElementById("capiPesValue").value = "";
            } else {
                document.getElementById("capiPesValue").value = pessoa.capitania;
            }
        }
    })
}

/****************** Marcador Económico ******************* */
async function getAllMarEco(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos marcadores económicos");
        } else {
            let marEcos = await response.json();
            let selectbox = '<label for="marEcoSel" ><b>Marcador economico (Ocupação)</b></label>' +
                '<select class="form-control" id="marEcoSel" name="marEcoSel" placeholder="Termo">' +
                '<option value="">&nbsp;</option>';
            let selected = '';
            for (const marEco of marEcos) {
                if (v_id == marEco.id) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + marEco.id + '" ' + selected + '>' + marEco.designacao + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("addMarPes").innerHTML = selectbox;
        }
    })
}

/****************** lista todos Marcador Económico  ******************* */
async function listMarEco(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/allPessoa/" + v_id;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos marcadores económicos");
        } else {
            let MarEcos = await response.json();
            let tableMarEco = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableMarEco" ><b>Marcador Económico:</b></label>' +
                '</div><div class="column listTabBotao" align="right"><a href="javascript:selectMarEco(' + v_id + ',`all`)"><img src="../img/addLine.png" width="35"></a> ' +
                '<a href="javascript:delMarEco(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
                '<table id="tableMarEcoSel" class="listPopUp">';

            for (const MarEco of MarEcos) {

                tableMarEco += '<tr><td class="checkDel"><i class="checkDelMarEco far fa-circle" style="color:red;" onclick="checkDelMarEco(this);" value="' + MarEco.id + '"></i></td>' +
                    '<td>' + MarEco.designacao + '</td></tr>'


            }
            tableMarEco += `</table>`;
            document.getElementById("addMarEco").innerHTML = tableMarEco;
        }
    })
}


/*********************** Devolve lista Marcador Económico ************************************** */
async function selectMarEco(v_pessoa_id, v_nomePesq) {

    $('#criarPessoa').modal('hide'); 
    $('#modalMarEco').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/notInPessoa/" + v_pessoa_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listMarEcos = await response.json();
            let tableMarEco = '<div class="scroll"><table id="tableSelMarEco" class="ListPopUpModal">';
            
            for (const listMarEco of listMarEcos) {

                tableMarEco += '<tr><td><a href="javascript:addMarEco(' + v_pessoa_id + ',' + listMarEco.id + ')">' + listMarEco.designacao + ' </a></td>' +
                    '</tr>';
            }
            
            tableMarEco += `</table></div>`;
            document.getElementById("tabmodalMarEco").innerHTML = tableMarEco;
        }
    })
}


/*********************** Devolve lista Marcador Económico ************************************** */
async function selectMarEcoAdd(v_nomePesq) {

    $('#criarPessoa').modal('hide');
    $('#modalMarEco').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/search/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listMarEcos = await response.json();
            let tableMarEco = '<div class="scroll"><table id="tableSelMarEco" class="ListPopUpModal">';
            
            for (const listMarEco of listMarEcos) {

                tableMarEco += '<tr><td><a href="javascript:addMarEcoAdd(' + listMarEco.id + ',`' + listMarEco.designacao + '`)">' + listMarEco.designacao + ' </a></td>' +
                    '</tr>';
            }
            
            tableMarEco += `</table></div>`;
            document.getElementById("tabmodalMarEco").innerHTML = tableMarEco;
        }
    })
}

function addMarEcoAdd(v_id, v_desc) {
    var table = document.getElementById("tableMarEcoSel");
    var row = table.insertRow();
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<i class="checkDelMarEco far fa-circle" style="color:red;" onclick="checkDelMarEco(this);" value="' + v_id + '"></i>';
    cell2.innerHTML = v_desc;
    $('#modalMarEco').modal('hide');
    $('#criarPessoa').modal('show');
}

function delMarEcoAdd(v_id) {
    let elements = document.getElementsByClassName('checkDelMarEco');
    let v_linha = 0;
    for (let element of elements) {
        if (element.attributes.class.value == 'checkDelMarEco fa fa-check-circle') {
            document.getElementById("tableMarEcoSel").deleteRow(v_linha);
        }
        v_linha += 1;
    }
}
/******************** Selecionar e descelecionar ************************** */
function checkDelMarEco(v_obj) {
    if (v_obj.className == 'checkDelMarEco far fa-circle') {
        v_obj.className = 'checkDelMarEco fa fa-check-circle';
    } else {
        v_obj.className = 'checkDelMarEco far fa-circle';
    }
}

/******************************************************************* */
/******************************************************************* */

/****************** lista todos Socio Juridico  ******************* */
async function listSocJuridico(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/allPessoa/" + v_id;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das SocJuridico");
        } else {
            let SocJuridicos = await response.json();
            let tableSocJuridico = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableSocJuridico" ><b>Sócio Juridico:</b></label>' +
                '</div><div class="column listTabBotao" align="right"><a href="javascript:selectSocJuridico(' + v_id + ',`all`)"><img src="../img/addLine.png" width="35"></a> ' +
                '<a href="javascript:delSocJuridico(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
                '<table id="tableSocJuridicoSel" class="listPopUp">';

            for (const SocJuridico of SocJuridicos) {

                tableSocJuridico += '<tr><td class="checkDel"><i class="checkDelSocJuridico far fa-circle" style="color:red;" onclick="checkDelSocJuridico(this);" value="' + SocJuridico.id + '"></i></td>' +
                    '<td>' + SocJuridico.marcador_status_juridico + '</td></tr>'


            }
            tableSocJuridico += `</table>`;
            document.getElementById("addSocJuridico").innerHTML = tableSocJuridico;
        }
    })
}


/*********************** Devolve lista Socio Juridico ************************************** */
async function selectSocJuridico(v_pessoa_id, v_nomePesq) {

    $('#criarPessoa').modal('hide');
    $('#modalSocJuridico').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/notInPessoa/" + v_pessoa_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listSocJuridicos = await response.json();
            let tableSocJuridico = '<div class="scroll"><table id="tableSelSocJuridico" class="ListPopUpModal">';
            
            for (const listSocJuridico of listSocJuridicos) {

                tableSocJuridico += '<tr><td><a href="javascript:addSocJuridico(' + v_pessoa_id + ',' + listSocJuridico.id + ')">' + listSocJuridico.marcador_status_juridico + ' </a></td>' +
                    '</tr>';
            }
            
            tableSocJuridico += `</table></div>`;
            document.getElementById("tabmodalSocJuridico").innerHTML = tableSocJuridico;
        }
    })
}


/*********************** Devolve lista Socio Juridico ************************************** */
async function selectSocJuridicoAdd(v_nomePesq) {

    $('#criarPessoa').modal('hide');
    $('#modalSocJuridico').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/search/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listSocJuridicos = await response.json();
            let tableSocJuridico = '<div class="scroll"><table id="tableSelSocJuridico" class="ListPopUpModal">';
            
            for (const listSocJuridico of listSocJuridicos) {

                tableSocJuridico += '<tr><td><a href="javascript:addSocJuridicoAdd(' + listSocJuridico.id + ',`' + listSocJuridico.marcador_status_juridico + '`)">' + listSocJuridico.marcador_status_juridico + ' </a></td>' +
                    '</tr>';
            }
            
            tableSocJuridico += `</table></div>`;
            document.getElementById("tabmodalSocJuridico").innerHTML = tableSocJuridico;
        }
    })
}

function cancelarSocJuridico(){
  $('#modalSocJuridico').modal('hide');
  $('#criarPessoa').modal('show');
}


function addSocJuridicoAdd(v_id, v_desc) {
    var table = document.getElementById("tableSocJuridicoSel");
    var row = table.insertRow();
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<i class="checkDelSocJuridico far fa-circle" style="color:red;" onclick="checkDelSocJuridico(this);" value="' + v_id + '"></i>';
    cell2.innerHTML = v_desc;
    $('#modalSocJuridico').modal('hide');
    $('#criarPessoa').modal('show');
}

function delSocJuridicoAdd(v_id) {
    let elements = document.getElementsByClassName('checkDelSocJuridico');
    let v_linha = 0;
    for (let element of elements) {
        if (element.attributes.class.value == 'checkDelSocJuridico fa fa-check-circle') {
            document.getElementById("tableSocJuridicoSel").deleteRow(v_linha);
        }
        v_linha += 1;
    }
}
/******************** Selecionar e descelecionar ************************** */
function checkDelSocJuridico(v_obj) {
    if (v_obj.className == 'checkDelSocJuridico far fa-circle') {
        v_obj.className = 'checkDelSocJuridico fa fa-check-circle';
    } else {
        v_obj.className = 'checkDelSocJuridico far fa-circle';
    }
}


/****************** Termo ******************* */
async function getAllTermo(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos termos");
        } else {
            let termos = await response.json();
            let selectbox = '<label for="termoSel" ><b>Termo:</b></label>' +
                '<select class="form-control" id="termoSel" name="termoSel" placeholder="Termo">' +
                '<option value="-1">&nbsp;</option>';
            let selected = '';
            for (const termo of termos) {
                if (v_id == termo.id) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + termo.id + '" ' + selected + '>' + termo.nome_termo + '(' + termo.capitania + ')' + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("addTermoPes").innerHTML = selectbox;
        }
    })
}


/****************** lista todos Oficio Titulo  ******************* */
async function listOfiTitulo(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/allPessoa/" + v_id + "/all";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das oficios/titulos");
        } else {
            let OfiTitulos = await response.json();
            let tableOfiTitulo = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableOfiTitulo" ><b>Ofício/Título:</b></label>' +
                '</div><div class="column listTabBotao" align="right"><a href="javascript:selectOfiTitulo(' + v_id + ',`all`)"><img src="../img/addLine.png" width="35"></a> ' +
                '<a href="javascript:delOfiTitulo(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
                '<table id="tableOfiTituloSel" class="listPopUp">';

            for (const OfiTitulo of OfiTitulos) {

                tableOfiTitulo += '<tr><td class="checkDel"><i class="checkDelOfiTitulo far fa-circle" style="color:red;" onclick="checkDelOfiTitulo(this);" value="' + OfiTitulo.id + '"></i></td>' +
                    '<td>' + OfiTitulo.designacao + '</td></tr>'


            }
            tableOfiTitulo += `</table>`;
            document.getElementById("addOfiTitulo").innerHTML = tableOfiTitulo;
        }
    })
}


/*********************** Devolve lista Oficio Titulo ************************************** */
async function selectOfiTitulo(v_pessoa_id, v_nomePesq) {

    $('#criarPessoa').modal('hide'); 
    $('#modalOfiTitulo').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/notInPessoa/" + v_pessoa_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listOfiTitulos = await response.json();
            let tableOfiTitulo = '<div class="scroll"><table id="tableSelOfiTitulo" class="ListPopUpModal">';
            
            for (const listOfiTitulo of listOfiTitulos) {

                tableOfiTitulo += '<tr><td><a href="javascript:addOfiTitulo(' + v_pessoa_id + ',' + listOfiTitulo.id + ')">' + listOfiTitulo.designacao + ' </a></td>' +
                    '</tr>';
            }
            
            tableOfiTitulo += `</table></div>`;
            document.getElementById("tabmodalOfiTitulo").innerHTML = tableOfiTitulo;
        }
    })
}


/*********************** Devolve lista Oficio Titulo ************************************** */
async function selectOfiTituloAdd(v_nomePesq) {

    $('#criarPessoa').modal('hide');
    $('#modalOfiTitulo').modal('show');
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/search/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let listOfiTitulos = await response.json();
            let tableOfiTitulo = '<div class="scroll"><table id="tableSelOfiTitulo" class="ListPopUpModal">';
            
            for (const listOfiTitulo of listOfiTitulos) {

                tableOfiTitulo += '<tr><td><a href="javascript:addOfiTituloAdd(' + listOfiTitulo.pk_oficio_titulo + ',`' + listOfiTitulo.descricao + '`)">'
                    + listOfiTitulo.descricao + ' </a></td>' +
                    '</tr>';
            }
            tableOfiTitulo += `</table></div>`;
            document.getElementById("tabmodalOfiTitulo").innerHTML = tableOfiTitulo;
        }
    })
}

function addOfiTituloAdd(v_id, v_desc) {
    var table = document.getElementById("tableOfiTituloSel");
    var row = table.insertRow();
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<i class="checkDelOfiTitulo far fa-circle" style="color:red;" onclick="checkDelOfiTitulo(this);" value="' + v_id + '"></i>';
    cell2.innerHTML = v_desc;
    $('#modalOfiTitulo').modal('hide');
    $('#criarPessoa').modal('show');
}

function delOfiTituloAdd(v_id) {
    let elements = document.getElementsByClassName('checkDelOfiTitulo');
    let v_linha = 0;
    for (let element of elements) {
        if (element.attributes.class.value == 'checkDelOfiTitulo fa fa-check-circle') {
            document.getElementById("tableOfiTituloSel").deleteRow(v_linha);
        }
        v_linha += 1;
    }
}
/******************** Selecionar e descelecionar ************************** */
function checkDelOfiTitulo(v_obj) {
    if (v_obj.className == 'checkDelOfiTitulo far fa-circle') {
        v_obj.className = 'checkDelOfiTitulo fa fa-check-circle';
    } else {
        v_obj.className = 'checkDelOfiTitulo far fa-circle';
    }
}

// *******************************************************************************


/********************* Local Pessoa ***************************** */
/********************* Lista do Termo  ***************************** */
async function selectTermo(v_nomePesq) {
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

                tableTermos += '<tr><td><a href="javascript:addTermo(' + termo.id + ',`' + termo.nome_termo + '`)">' + termo.nome_termo + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableTermos += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableTermos += `</table>`;

            document.getElementById("tabmodalTermo").innerHTML = tableTermos;
        }
    })
}

function addTermo(v_id, v_desc) {
    document.getElementById("termoPesValue").value = v_id;
    document.getElementById("termoPes").value = v_desc;
    $('#modalTermo').modal('hide');
    $('#criarPessoa').modal('show');
}

function cancelarTermoPes(){
  $('#modalTermo').modal('hide');
  $('#criarPessoa').modal('show');
}

function openTermoPes(){
  $('#criarPessoa').modal('hide');
  $('#modalTermo').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectFreguesia(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/search/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Freguesias = await response.json();
            let tableFreguesias = '<table id="tableFreg" class="ListPopUpModal">';
            let conta = 1;
            for (const freguesia of Freguesias) {

                tableFreguesias += '<tr><td><a href="javascript:addFreg(' + freguesia.id + ',`' + freguesia.nome_freguesia + '`)">' + freguesia.nome_freguesia + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableFreguesias += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableFreguesias += `</table>`;

            document.getElementById("tabmodalFreguesia").innerHTML = tableFreguesias;
        }
    })
}

function addFreg(v_id, v_desc) {
    document.getElementById("fregPesValue").value = v_id;
    document.getElementById("fregPes").value = v_desc;
    $('#modalFreg').modal('hide');
    $('#criarPessoa').modal('show');
}

function cancelarFregPes(){
  $('#modalFreg').modal('hide');
  $('#criarPessoa').modal('show');
}

function openFregPes(){
  $('#criarPessoa').modal('hide');
  $('#modalFreg').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectComarca(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Comarcas = await response.json();
            let tableComarcas = '<table id="tableComa" class="ListPopUpModal">';
            let conta = 1;
            for (const comarca of Comarcas) {

                tableComarcas += '<tr><td><a href="javascript:addComa(' + comarca.id + ',`' + comarca.nome_comarca + '`)">' + comarca.nome_comarca + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableComarcas += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableComarcas += `</table>`;

            document.getElementById("tabmodalComarca").innerHTML = tableComarcas;
        }
    })
}

function addComa(v_id, v_desc) {
    document.getElementById("comaPesValue").value = v_id;
    document.getElementById("comaPes").value = v_desc;
    $('#modalComa').modal('hide');
    $('#criarPessoa').modal('show');
}

function cancelarComaPes(){
  $('#modalComa').modal('hide');
  $('#criarPessoa').modal('show');
}

function openComaPes(){
  $('#criarPessoa').modal('hide');
  $('#modalComa').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectCapitania(v_nomePesq) {
    if (v_nomePesq == null) {
        v_nomePesq = 'all';
    }
    if (v_nomePesq == '') {
        v_nomePesq = 'all';
    }
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/capitania/search/" + v_nomePesq;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
        } else {
            let Capitanias = await response.json();
            let tableCapitanias = '<table id="tableCapi" class="ListPopUpModal">';
            let conta = 1;
            for (const capitania of Capitanias) {

                tableCapitanias += '<tr><td><a href="javascript:addCapi(' + capitania.id + ',`' + capitania.nome_capitania + '`)">' + capitania.nome_capitania + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableCapitanias += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableCapitanias += `</table>`;

            document.getElementById("tabmodalCapitania").innerHTML = tableCapitanias;
        }
    })
}

function addCapi(v_id, v_desc) {
    document.getElementById("capiPesValue").value = v_id;
    document.getElementById("capiPes").value = v_desc;
    $('#modalCapi').modal('hide');
    $('#criarPessoa').modal('show');
}

function cancelarCapiPes(){
  $('#modalCapi').modal('hide');
  $('#criarPessoa').modal('show');
}

function openCapiPes(){
  $('#criarPessoa').modal('hide');
  $('#modalCapi').modal('show');
}

/********************************************************************** */
/************************* Criar Pessoa ******************************* */
/********************************************************************** */

async function addPessoa() {
    // função Criar Pessoa
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("addNomePes").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: "Erro criar pessoa",
            text: errForm,
            showConfirmButton: false,
            timer: 3500,
        });

    } else {
        //Se não houver erro no preenchimento então chama o serviço criar pessoa
        var objPes = {
            nome: document.getElementById("addNomePes").value,
            idade: document.getElementById("addIdadePes").value,
            sexo: document.getElementById("addSexoPes").value,
            filiacao: document.getElementById("addFilPes").value,
            //marcador_economico_ocupacao: document.getElementById("marEcoSel").value,
            termo: document.getElementById("termoPesValue").value,
            freguesia: document.getElementById("fregPesValue").value,
            comarca: document.getElementById("comaPesValue").value,
            capitania: document.getElementById("capiPesValue").value
        };
        var exit = "no";
        var json = JSON.stringify(objPes);
        console.log(json);
        const v_url = await getUrlPayaraDireto();
        const v_token = await getToken();
        $.ajax({
          url: v_url + "/CircPeticionario/webresources/pessoa/insert",
          type: "POST",
          headers: { "token": v_token },
          data: json,
          dataType: "json",
          
          success: function ( result) {
                let pessoaId = result;
                /* Adicionar Socio Juridico escolhidos  a pessoa que foi criada*/
                let elements = document.getElementsByClassName('checkDelSocJuridico');
                for (let element of elements) {
                    var objSocPes = {
                        marcador_socio_juridico: element.attributes.value.value,
                        pessoa: pessoaId
                    };
                    var exit = "no";
                    var json = JSON.stringify(objSocPes);

                    var myHeaders = new Headers();
                    myHeaders.append("token", v_token);
                    $.ajax({
                        url: v_url + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/insert",
                        type: "POST",
                        headers: { "token": v_token },
                        data: json,
                        dataType: "json",

                    });
                }
                /* Adicionar Oficio Titulo escolhidos  a pessoa que foi criada*/
                let elementsOfi = document.getElementsByClassName('checkDelOfiTitulo');
                for (let elementOfi of elementsOfi) {
                    var objOfiTit = {
                        oficio_titulo: elementOfi.attributes.value.value,
                        pessoa: pessoaId
                    };
                    var exit = "no";
                    var json = JSON.stringify(objOfiTit);

                    var myHeaders = new Headers();
                    myHeaders.append("token", v_token);
                    $.ajax({
                        url: v_url + "/CircPeticionario/webresources/relac_oficio_requerente/insert",
                        type: "POST",
                        headers: { "token": v_token },
                        data: json,
                        dataType: "json",

                    });
                }
                /* Adicionar Marcador Económico escolhidos  a pessoa que foi criada*/
                let elementsMarEco = document.getElementsByClassName('checkDelMarEco');
                for (let elementMarEco of elementsMarEco) {
                    var objMarEco = {
                        marcador_economico_ocupacao: elementMarEco.attributes.value.value,
                        pessoa: pessoaId
                    };
                    var exit = "no";
                    var json = JSON.stringify(objMarEco);

                    var myHeaders = new Headers();
                    myHeaders.append("token", v_token);
                    $.ajax({
                        url: v_url + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/insert",
                        type: "POST",
                        headers: { "token": v_token},
                        data: json,
                        dataType: "json",

                    });
                }
                Swal.fire({
                    icon: "success",
                    title: "Pessoa criada com sucesso",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    if (document.getElementById("VSearch").value == null) {
                        search('all');
                    } else {
                        if (document.getElementById("VSearch").value == '') {
                            search('all');
                        } else {
                            search(document.getElementById("VSearch").value);
                        }
                    }
                    $('#criarPessoa').modal('hide');
                });
            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar a pessoa.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    if (document.getElementById("VSearch").value == null) {
                        search('all');
                    } else {
                        if (document.getElementById("VSearch").value == '') {
                            search('all');
                        } else {
                            search(document.getElementById("VSearch").value);
                        }
                    }
                    $('#criarPessoa').modal('hide');
                });
            }
        });
    }
}

// função de alterar Pessoa
async function savePessoa() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("addNomePes").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: "Erro criar pessoa",
            text: errForm,
            showConfirmButton: false,
            timer: 3500,
        });

    } else {
        var objPes = {
            nome: document.getElementById("addNomePes").value,
            idade: document.getElementById("addIdadePes").value,
            sexo: document.getElementById("addSexoPes").value,
            filiacao: document.getElementById("addFilPes").value,
            termo: document.getElementById("termoPesValue").value,
            freguesia: document.getElementById("fregPesValue").value,
            comarca: document.getElementById("comaPesValue").value,
            capitania: document.getElementById("capiPesValue").value
        };
        var exit = "no";
        var json = JSON.stringify(objPes);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/update/" + document.getElementById("pessoaId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Pessoa alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    if (document.getElementById("VSearch").value == null) {
                        search('');
                    } else {
                        if (document.getElementById("VSearch").value == '') {
                            search('');
                        } else {
                            search(document.getElementById("VSearch").value);
                        }
                    }
                    $('#criarPessoa').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar marcador.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    if (document.getElementById("VSearch").value == null) {
                        search('all');
                    } else {
                        if (document.getElementById("VSearch").value == '') {
                            search('all');
                        } else {
                            search(document.getElementById("VSearch").value);
                        }
                    }
                    $('#criarPessoa').modal('hide');
                });
            }
        });
    }
}

/************************** Marcador Económico ***************************** */
/********************* Adicionar Marcador Económico ************************* */
async function addMarEco(v_pes_id, v_ecoMar_id) {

    var obj = {
        pessoa: v_pes_id,
        marcador_economico_ocupacao: v_ecoMar_id
    };
    var json = JSON.stringify(obj);

    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/insert",
        type: "POST",
        headers: { "token": await getAdminToken() },
        data: json,
        dataType: "json",
        success: function (result) {
            Swal.fire({
                icon: "success",
                title: "Marcador Económico adicionado.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalMarEco').modal('hide');
                $('#criarPessoa').modal('show');
                listMarEco(v_pes_id);
            });

        },
        error: function (err) {
            console.log(err);
            exit = err;
            Swal.fire({
                icon: "error",
                title: "Erro ao adicionar marcador económico.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalMarEco').modal('hide');
                $('#criarPessoa').modal('show');
                listMarEco(v_pes_id);
            });
        }
    });
}

function cancelarMarEco(){
  $('#modalMarEco').modal('hide');
  $('#criarPessoa').modal('show');
}


/********************* Retirar Marcador Económico ************************* */
async function delMarEco(v_pessoa_id) {
    // vai percorrer todos os item da página que utilizem a class checked
    // Se checked e clicar eliminar remove da lista os selecionados
    let elements = document.getElementsByClassName('checkDelMarEco fa fa-check-circle');
    let v_ecoMar_id;
    for (let element of elements) {
        v_ecoMar_id = element.attributes.value.value;
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/delete/" + v_ecoMar_id + "/" + v_pessoa_id,
            type: "DELETE",
            headers: { "token": await getAdminToken() },
            data: "",
            dataType: "json"
        });
    }
    Swal.fire({
        icon: "success",
        title: "Marcador(es) Económico(s) removido(s) com sucesso.",
        showConfirmButton: false,
        timer: 1500,
    }).then(function () {
        listMarEco(v_pessoa_id);
    });
}


/************************** Socio Juridico ***************************** */
/********************* Adicionar Socio Juridico ************************* */
async function addSocJuridico(v_pes_id, v_ecoJur_id) {

    var obj = {
        pessoa: v_pes_id,
        marcador_socio_juridico: v_ecoJur_id
    };
    var json = JSON.stringify(obj);

    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/insert",
        type: "POST",
        headers: { "token": await getAdminToken() },
        data: json,
        dataType: "json",
        success: function (result) {
            Swal.fire({
                icon: "success",
                title: "Marcador Sócio Jurídico adicionado.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalSocJuridico').modal('hide');
                $('#criarPessoa').modal('show');
                listSocJuridico(v_pes_id);
            });

        },
        error: function (err) {
            console.log(err);
            exit = err;
            Swal.fire({
                icon: "success",
                title: "Marcador Sócio Jurídico adicionado.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalSocJuridico').modal('hide');
                $('#criarPessoa').modal('show');
                listSocJuridico(v_pes_id);
            });
        }
    });
}

function cancelarMarEco(){
  $('#modalMarEco').modal('hide');
  $('#criarPessoa').modal('show');
}


/********************* Retirar Socio Juridico ************************* */
async function delSocJuridico(v_pessoa_id) {
    // vai percorrer todos os item da página que utilizem a class checked
    // Se checked e clicar eliminar remove da lista os selecionados
    let elements = document.getElementsByClassName('checkDelSocJuridico fa fa-check-circle');
    let v_ecoJur_id;
    for (let element of elements) {
        v_ecoJur_id = element.attributes.value.value;
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/delete/" + v_ecoJur_id + "/" + v_pessoa_id,
            type: "DELETE",
            headers: { "token": await getAdminToken() },
            data: "",
            dataType: "json"
        });
    }
    Swal.fire({
        icon: "success",
        title: "Socio Juridico(s) removido(s) com sucesso.",
        showConfirmButton: false,
        timer: 1500,
    }).then(function () {
        listSocJuridico(v_pessoa_id);
    });
}

/************************** Oficio Titulo ***************************** */
/********************* Adicionar Oficio Titulo ************************* */
async function addOfiTitulo(v_pes_id, v_ecoJur_id) {

    var obj = {
        pessoa: v_pes_id,
        oficio_titulo: v_ecoJur_id
    };
    var json = JSON.stringify(obj);

    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());
    $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/insert",
        type: "POST",
        headers: { "token": await getAdminToken() },
        data: json,
        dataType: "json",
        success: function (result) {
            Swal.fire({
                icon: "success",
                title: "Oficio/Título adicionado.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalOfiTitulo').modal('hide');
                $('#criarPessoa').modal('show');
                listOfiTitulo(v_pes_id);
            });

        },
        error: function (err) {
            console.log(err);
            exit = err;
            Swal.fire({
                icon: "success",
                title: "Oficio/Título adicionado.",
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalOfiTitulo').modal('hide');
                $('#criarPessoa').modal('show');
                listOfiTitulo(v_pes_id);
            });
        }
    });
}

function cancelarOfiTitulo(){
  $('#modalOfiTitulo').modal('hide');
  $('#criarPessoa').modal('show');
}


/********************* Retirar Oficio Titulo ************************* */
async function delOfiTitulo(v_pessoa_id) {
    // vai percorrer todos os item da página que utilizem a class checked
    // Se checked e clicar eliminar remove da lista os selecionados
    let elements = document.getElementsByClassName('checkDelOfiTitulo fa fa-check-circle');
    let v_ecoJur_id;
    for (let element of elements) {
        v_ecoJur_id = element.attributes.value.value;
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/delete/" + v_ecoJur_id + "/" + v_pessoa_id,
            type: "DELETE",
            headers: { "token": await getAdminToken() },
            data: "",
            dataType: "json"
        });
    }
    Swal.fire({
        icon: "success",
        title: "Oficio(s)/Título(s) removido(s) com sucesso.",
        showConfirmButton: false,
        timer: 1500,
    }).then(function () {
        listOfiTitulo(v_pessoa_id);
    });
}

function delPessoa() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta pessoa?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/delete/" + document.getElementById("pessoaId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    sessionStorage.setItem("v_idCons", result);
                    Swal.fire({
                        icon: "success",
                        title: "Pessoa eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarPessoa').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar a pessoa.",
                        text: "Esta pessoa encontra-se associada a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarPessoa').modal('hide');
                    });
                }
            });
        }
    });
}