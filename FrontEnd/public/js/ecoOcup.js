/************************* Marcador Económico  *************************** */
/************************ Lista todos os Marcadores Económicos ****************************** */
async function getAllEcoOcup(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos marcadores");
        } else {
            let objEcoOcups = await response.json();
            let EcoOcups = objEcoOcups.allMarcEco;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Marcador</th>' +
                '</tr>';
            for (const EcoOcup of EcoOcups) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getEcoOcup(`+ EcoOcup.id + `)"></i></td>
                                    <td scope="row">` + EcoOcup.designacao + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objEcoOcups.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllEcoOcup(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableEcoOcup").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Marcadores Económicos ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllEcoOcup(1,10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos marcadores");
            } else {
                EcoOcups = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Marcador</th>' +
                    '</tr>';
                let conta = 0;
                for (const EcoOcup of EcoOcups) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getEcoOcup(`+ EcoOcup.id + `)"></i></td>
                                    <td scope="row">` + EcoOcup.designacao + `</td>
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
                document.getElementById("TableEcoOcup").innerHTML = tabela;
            }
        })
    }
}


/************************ devolve o Marcador Económico pedido ****************************** */
async function getEcoOcup(v_ecoOcupId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/record/" + v_ecoOcupId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o marcador económico.");
        } else {
            $('#criarEcoOcup').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let ecoOcup = await response.json();
            document.getElementById("ecoOcupAdd").value = ecoOcup.designacao;
            document.getElementById("ecoOcupId").value = ecoOcup.pk_economico_ocupacao;

        }
    })
}


// função de criação de novo Marcador Económico
async function addEcoOcup() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("ecoOcupAdd").value == "") {
        errForm += "Marcador é obrigatório.<br>";
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
            designacao: document.getElementById("ecoOcupAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Marcador criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarEcoOcup').modal('hide');
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
                    $('#criarEcoOcup').modal('hide');
                });
            }
        });
    }
}



// função de alterar Marcador Económico
async function saveEcoOcup() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("ecoOcupAdd").value == "") {
        errForm += "Marcador é obrigatório.<br>";
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
            designacao: document.getElementById("ecoOcupAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/update/" + document.getElementById("ecoOcupId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Marcador alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarEcoOcup').modal('hide');
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
                    search(document.getElementById("VSearch").value);
                    $('#criarEcoOcup').modal('hide');
                });
            }
        });
    }
}

function delEcoOcup() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este marcador?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/delete/" + document.getElementById("ecoOcupId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Marcador eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarEcoOcup').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o marcador.",
                        text: "Este marcador encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarEcoOcup').modal('hide');
                    });
                }
            });
        }
    });
}