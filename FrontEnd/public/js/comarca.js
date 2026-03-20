/************************* Comarcas *************************** */
/************************ Lista todas as Comarcas ****************************** */
async function getAllComarcas(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das comarcas");
        } else {
            let objComa = await response.json();
            let Comarcas = objComa.allComarcas;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome Comarca</th><th>Comarca 2</th>' +
                '</tr>';
            for (const Comarca of Comarcas) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getComarca(`+ Comarca.id + `)"></i></td>
                                    <td scope="row">` + Comarca.nome_comarca + `</td>
                                    <td scope="row">` + Comarca.comarca_nome2 + `</td>
                                </tr>`

            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objComa.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllComarcas(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableComarca").innerHTML = tabela;
        }
    })
}


/************************ Procurar as Comarca ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllComarcas(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das comarcas");
            } else {
                Comarcas = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome Comarca</th><th>Comarca 2</th>' +
                    '</tr>';
                let conta = 0;
                for (const Comarca of Comarcas) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getComarca(`+ Comarca.id + `)"></i></td>
                                    <td scope="row">` + Comarca.nome_comarca + `</td>
                                    <td scope="row">` + Comarca.comarca_nome2 + `</td>
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
                document.getElementById("TableComarca").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve a comarca pedida ****************************** */
async function getComarca(v_comarcaId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/record/" + v_comarcaId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a Comarca.");
        } else {
            $('#criarComarca').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let comarca = await response.json();
            document.getElementById("comarcaAdd").value = comarca.nome_comarca;
            document.getElementById("comarcaId").value = v_comarcaId;
            document.getElementById("2Comarca").value = comarca.comarca_nome2Desc;
            document.getElementById("comarca2Value").value = comarca.comarca_nome2;
            selectComarca2();
        }
    })
}



/********************* Lista do 2ª Comarca  ***************************** */
async function selectComarca2(v_nomePesq) {
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
            let Comarcas2 = await response.json();
            let tableComarca = '<table id="table2Comarca" class="ListPopUpModal">';
            let conta = 1;
            for (const Comarca2 of Comarcas2) {

                tableComarca += '<tr><td><a href="javascript:add2Comarca(' + Comarca2.id + ',`' + Comarca2.nome + '`)">' + Comarca2.nome + '</a></td></tr>'

                if (conta == 10) {
                    break;
                }
                conta++;
            }
            if (conta >= 10) {
                tableComarca += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
            }
            tableComarca += `</table>`;
            console.log(urlBase + ' ----- ' + tableComarca);
            document.getElementById("tabComarca2").innerHTML = tableComarca;
        }
    })
}

/**************** Adiciona á text box do Comarca 2 ************************ */
function add2Comarca(v_id, v_desc) {
    document.getElementById("2Comarca").value = v_desc;
    document.getElementById("comarca2Value").value = v_id;
    $('#modalComarca2').modal('hide');
}

// função de criação de nova comarca
async function addComarca() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("comarcaAdd").value == "") {
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
        let vComarca2;
        if (document.getElementById("comarca2Value").value == 0) {
            vComarca2 = '';
        } else {
            vComarca2 = document.getElementById("comarca2Value").value;
        }
        var objCons = {
            nome_comarca: document.getElementById("comarcaAdd").value,
            comarca_nome2: vComarca2
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Comarca criada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarComarca').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Comarca.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarComarca').modal('hide');
                });
            }
        });
    }
}



// função de alterar comarca
async function saveComarca() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("comarcaAdd").value == "") {
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
        let vComarca2;
        if (document.getElementById("comarca2Value").value == 0) {
            vComarca2 = '';
        } else {
            vComarca2 = document.getElementById("comarca2Value").value;
        }
        var objCons = {
            nome_comarca: document.getElementById("comarcaAdd").value,
            comarca_nome2: vComarca2
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/update/" + document.getElementById("comarcaId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Comarca alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarComarca').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar Comarca.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarComarca').modal('hide');
                });
            }
        });
    }
}

function delComarca() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta comarca?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/delete/" + document.getElementById("comarcaId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    sessionStorage.setItem("v_idCons", result);
                    Swal.fire({
                        icon: "success",
                        title: "Comarca eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarComarca').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar a comarca.",
                        text: "Esta comarca encontra-se associada a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarComarca').modal('hide');
                    });
                }
            });
        }
    });
}