/************************* Tipologia Diplomática *************************** */
/************************ Lista todas as Tipologia Diplomática ****************************** */
async function getAllTipoDiplos(pageNum, rowsPage) {
    const urlBase = v_url + "/CircPeticionario/webresources/tipologia_diplomatica/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das Tipologia Diplomáticas");
        } else {
            objTipoDiplos = await response.json();
            TipoDiplos = objTipoDiplos.allTipoDiplo;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome tipologia diplomática</th>' +
                '</tr>';
            for (const TipoDiplo of TipoDiplos) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTipoDiplo(`+ TipoDiplo.pk_tipologia + `)"></i></td>
                                    <td scope="row">` + TipoDiplo.nome + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objTipoDiplos.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllTipoDiplos(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableTipoDiplo").innerHTML = tabela;
        }
    })
}


/************************ Procurar as Tipologia Diplomáticas ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllTipoDiplos(1, 10);
    } else {
        urlBase = v_url + "/CircPeticionario/webresources/tipologia_diplomatica/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das Tipologia Diplomáticas");
            } else {
                TipoDiplos = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome tipologia diplomática</th>' +
                    '</tr>';
                let conta = 0;
                for (const TipoDiplo of TipoDiplos) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getTipoDiplo(`+ TipoDiplo.pk_tipologia + `)"></i></td>
                                    <td scope="row">` + TipoDiplo.nome + `</td>
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
                document.getElementById("TableTipoDiplo").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve a Tipologia Diplomática pedida ****************************** */
async function getTipoDiplo(v_tipoDiploId) {
    const urlBase = v_url + "/CircPeticionario/webresources/tipologia_diplomatica/record/" + v_tipoDiploId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a Tipologia Diplomática.");
        } else {
            $('#criarTipoDiplo').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let TipoDiplo = await response.json();
            document.getElementById("tipoDiploAdd").value = TipoDiplo.nome;
            document.getElementById("tipoDiploId").value = v_tipoDiploId;

        }
    })
}


// função de criação de nova Tipologia Diplomática
function addTipoDiplo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("tipoDiploAdd").value == "") {
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
            nome: document.getElementById("tipoDiploAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());
        $.ajax({
            url: v_url + "/CircPeticionario/webresources/tipologia_diplomatica/insert",
            type: "POST",
            headers: { "token": await getToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Tipologia Diplomática criada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTipoDiplo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Tipologia Diplomática.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTipoDiplo').modal('hide');
                });
            }
        });
    }
}



// função de alterar Tipologia Diplomática
function saveTipoDiplo() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("tipoDiploAdd").value == "") {
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
            nome: document.getElementById("tipoDiploAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());
        $.ajax({
            url: v_url + "/CircPeticionario/webresources/tipologia_diplomatica/update/" + document.getElementById("tipoDiploId").value,
            type: "PUT",
            headers: { "token": await getToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                sessionStorage.setItem("v_idCons", result);
                Swal.fire({
                    icon: "success",
                    title: "Tipologia Diplomática alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTipoDiplo').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar Tipologia Diplomática.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarTipoDiplo').modal('hide');
                });
            }
        });
    }
}

function delTipoDiplo() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta tipologia diplomática?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then((result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getToken());
            $.ajax({
                url: v_url + "/CircPeticionario/webresources/tipologia_diplomatica/delete/" + document.getElementById("tipoDiploId").value,
                type: "DELETE",
                headers: { "token": await getToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Tipologia diplomática eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTipoDiplo').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar a tipologia diplomática.",
                        text: "Esta tipologia diplomática encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarTipoDiplo').modal('hide');
                    });
                }
            });
        }
    });
}