/************************* Marcador Sócio Jurídico  *************************** */
/************************ Lista todos os Sócios Jurídicos ****************************** */
async function getAllSocJur(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos marcadores");
        } else {
            let objSocJurs = await response.json();
            let SocJurs = objSocJurs.allMarcStatus;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Marcador</th>' +
                '</tr>';
            for (const SocJur of SocJurs) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getSocJur(`+ SocJur.id + `)"></i></td>
                                    <td scope="row">` + SocJur.marcador_status_juridico + `</td>
                                </tr>`;

            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objSocJurs.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllSocJur(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableSocJur").innerHTML = tabela;
        }
    })
}


/************************ Procurar os Marcadores Sócio Juridico ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllSocJur(1,10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos marcadores");
            } else {
                SocJurs = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Marcador</th>' +
                    '</tr>';
                let conta = 0;
                for (const SocJur of SocJurs) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getSocJur(`+ SocJur.id + `)"></i></td>
                                    <td scope="row">` + SocJur.marcador_status_juridico + `</td>
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
                document.getElementById("TableSocJur").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve o Marcador Sócio Juridico pedido ****************************** */
async function getSocJur(v_socJurId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/record/" + v_socJurId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o marcador sócio jurídico.");
        } else {
            $('#criarSocJur').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let socJur = await response.json();
            document.getElementById("socJurAdd").value = socJur.marcador_status_juridico;
            document.getElementById("socJurId").value = socJur.marcador_socio_juridico_id;

        }
    })
}


// função de criação de novo Marcador Sócio Juridico
async function addSocJur() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("socJurAdd").value == "") {
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
            marcador_status_juridico: document.getElementById("socJurAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/insert",
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
                    $('#criarSocJur').modal('hide');
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
                    $('#criarSocJur').modal('hide');
                });
            }
        });
    }
}



// função de alterar Marcador Sócio Juridico
async function saveSocJur() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("socJurAdd").value == "") {
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
            marcador_status_juridico: document.getElementById("socJurAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/update/" + document.getElementById("socJurId").value,
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
                    $('#criarSocJur').modal('hide');
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
                    $('#criarSocJur').modal('hide');
                });
            }
        });
    }
}

function delSocJur() {
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
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/delete/" + document.getElementById("socJurId").value,
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
                        $('#criarSocJur').modal('hide');
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
                        $('#criarSocJur').modal('hide');
                    });
                }
            });
        }
    });
}