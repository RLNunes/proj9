/************************* Agregador  *************************** */
/************************ Lista todos os Agregadores ****************************** */
async function getAllAgregador(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos agregadores");
        } else {
            let objAgregadores = await response.json();
            let Agregadores = objAgregadores.allAgregador;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Agregador</th>' +
                '</tr>';
            for (const Agregador of Agregadores) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getAgregador(`+ Agregador.pk_tema_agregador + `)"></i></td>
                                    <td scope="row">` + Agregador.designacao + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objAgregadores.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllAgregador(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableAgregador").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Agregadores ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllAgregador(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos agregadores");
            } else {
                Agregadores = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Agregador</th>' +
                    '</tr>';
                let conta = 0;
                for (const Agregador of Agregadores) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getAgregador(`+ Agregador.pk_tema_agregador + `)"></i></td>
                                    <td scope="row">` + Agregador.designacao + `</td>
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
                document.getElementById("TableAgregador").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve o Agregador ****************************** */
async function getAgregador(v_aregadorId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/record/" + v_aregadorId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o agregador.");
        } else {
            $('#criarAgregador').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let Agregador = await response.json();
            document.getElementById("agregadorAdd").value = Agregador.designacao;
            document.getElementById("agregadorId").value = v_aregadorId;

        }
    })
}


// função de criação de novo Agregador
async function addAgregador() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("agregadorAdd").value == "") {
        errForm += "Agregador é obrigatório.<br>";
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
            designacao: document.getElementById("agregadorAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(obj);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Agregador criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarAgregador').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Agregador.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarAgregador').modal('hide');
                });
            }
        });
    }
}



// função de alterar Agregador
async function saveAgregador() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("agregadorAdd").value == "") {
        errForm += "Agregador é obrigatório.<br>";
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
            designacao: document.getElementById("agregadorAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/update/" + document.getElementById("agregadorId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Agregador alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarAgregador').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar agregador.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarAgregador').modal('hide');
                });
            }
        });
    }
}

function delAgregador() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este agregador?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/tema_agregador/delete/" + document.getElementById("agregadorId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Agregador eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarAgregador').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o agregador.",
                        text: "Este agregador encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarAgregador').modal('hide');
                    });
                }
            });
        }
    });
}