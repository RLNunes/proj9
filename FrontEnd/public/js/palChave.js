/************************* Palavra-Chave  *************************** */
/************************ Lista todos as palavras-Chaves ****************************** */
async function getAllPalChave(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das palavras-chaves");
        } else {
            let objPalChaves = await response.json();
            let PalChaves = objPalChaves.allPalChaves;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Palavra-Chave</th>' +
                '</tr>';
            for (const PalChave of PalChaves) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getPalChave(`+ PalChave.pk_palavra_chave + `)"></i></td>
                                    <td scope="row">` + PalChave.palavra_chave + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objPalChaves.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllPalChave(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TablePalChave").innerHTML = tabela;
        }
    })
}


/************************ Procurar as palavras-chaves ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllPalChave(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das palavras-chaves");
            } else {
                PalChaves = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Palavra-Chave</th>' +
                    '</tr>';
                let conta = 0;
                for (const PalChave of PalChaves) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getPalChave(`+ PalChave.pk_palavra_chave + `)"></i></td>
                                    <td scope="row">` + PalChave.palavra_chave + `</td>
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
                document.getElementById("TablePalChave").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve a Palavra-Chave ****************************** */
async function getPalChave(v_palChaveId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/record/" + v_palChaveId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a palavra-chave.");
        } else {
            $('#criarPalChave').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';
            let PalChave = await response.json();
            document.getElementById("palChaveAdd").value = PalChave.palavra_chave;
            document.getElementById("palChaveId").value = v_palChaveId;

        }
    })
}


// função de criação de nova palavra-chave
async function addPalChave() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("palChaveAdd").value == "") {
        errForm += "Palavra-Chave é obrigatória.<br>";
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
            palavra_chave: document.getElementById("palChaveAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(obj);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Palavra-Chave criada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarPalChave').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Palavra-Chave.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarPalChave').modal('hide');
                });
            }
        });
    }
}



// função de alterar PalavraChave
async function savePalChave() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("palChaveAdd").value == "") {
        errForm += "Palavra-Chave é obrigatória.<br>";
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
            palavra_chave: document.getElementById("palChaveAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/update/" + document.getElementById("palChaveId").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Palavra-Chave alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarPalChave').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar palavra-chave.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarPalChave').modal('hide');
                });
            }
        });
    }
}

function delPalChave() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta palavra-chave?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/delete/" + document.getElementById("palChaveId").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    sessionStorage.setItem("v_idCons", result);
                    Swal.fire({
                        icon: "success",
                        title: "Palavra-chave eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarPalChave').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar a palavra-chave.",
                        text: "Esta palavra-chave encontra-se associada a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarPalChave').modal('hide');
                    });
                }
            });
        }
    });
}