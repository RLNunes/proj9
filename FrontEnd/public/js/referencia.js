
/************************* Referência Documental *************************** */
/************************ Lista todas as referências ****************************** */
async function getAllrefDocs(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista das referências ");
        } else {
            let objRefDocs = await response.json();
            let refDocs = objRefDocs.allRefDocs;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Referência</th><th scope="col">Complemento</th>' +
                '<th scope="col">URL</th>' +
                '</tr>';
            for (const refDoc of refDocs) {
                let v_url = ' ';
                if (refDoc.url != null) {
                    v_url = refDoc.url;
                }
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editRefDoc(`+ refDoc.id + `)"></i></td>
                                    <td scope="row">` + refDoc.referencia + `</td>
                                    <td scope="row">` + refDoc.complemento + `</td>
                                    <td scope="row">` + v_url + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objRefDocs.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllrefDocs(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableReferencia").innerHTML = tabela;
        }
    })
}

/************************ procurar as referências ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllrefDocs(1, 10)
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/search/" + v_search;

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das referências ");
            } else {
                refDocs = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Referência</th><th scope="col">Numero</th><th scope="col">Complemento</th>' +
                    '<th scope="col">URL</th>' +
                    '</tr>';
                let conta = 0;
                for (const refDoc of refDocs) {
                    let v_url = ' ';
                    if (refDoc.url != null) {
                        v_url = refDoc.url;
                    }
                    console.log(refDoc)
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editRefDoc(`+ refDoc.pk_ref_documento + `)"></i></td>
                                    <td scope="row">` + refDoc.referencia + `</td>
                                    <td scope="row">` + refDoc.numero + `</td>
                                    <td scope="row">` + refDoc.complemento + `</td>
                                    <td scope="row">` + v_url + `</td>
                                </tr>`
                    if (conta == 10) {
                        break;
                    }
                    conta++;
                }
                if (conta >= 10) {
                    tabela += '<tr><td colspan="4">Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
                } else {
                    if (conta == 0) {
                        tabela += '<tr><td colspan="4">Não foram encontrados nenhum dado.</td></tr>'
                    }
                }
                tabela += `</table>`;
                document.getElementById("TableReferencia").innerHTML = tabela;
            }
        });
    }
}



/************************ devolve a resposta pedida ****************************** */
async function editRefDoc(v_refDocId) {

    document.getElementById("btnAdd").style.display = "none";
    document.getElementById("btnSave").style.display = "block";
    document.getElementById("btnDel").style.display = "block";
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/record/" + v_refDocId;
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de referências documentais");
        } else {
            refDoc = await response.json();
            document.getElementById("refDocAdd").value = refDoc.referencia;
            document.getElementById("numAdd").value = refDoc.numero;
            document.getElementById("compAdd").value = refDoc.complemento;
            document.getElementById("urlAdd").value = refDoc.url;

        }
    });
    document.getElementById("ref_docValue").value = v_refDocId;
    $('#criarRefDoc').modal('show');
}


/****************** Tipo Referência ******************* */
async function getAllTipRef(v_id) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tipo_referencia_documental/all";
    let texto = "";
    var myHeaders = new Headers();
    myHeaders.append("token", await getAdminToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos tipos de referências");
        } else {
            let tipRefs = await response.json();
            let selectbox = '<label for="capTipRef" ><b>Tipo Referência</b></label>' +
                '<select class="form-control" id="tipRefSel" name="tipRefSel" placeholder="Termo">' +
                '<option value="">&nbsp;</option>';
            let selected = '';
            for (const tipRef of tipRefs) {
                if (v_id == tipRef.id) {
                    selected = 'selected="selected"';
                } else {
                    selected = '';
                }
                selectbox += '<option value="' + tipRef.id + '" ' + selected + '>' + tipRef.tipo_referencia + '</option>'


            }
            selectbox += `</select>`;
            document.getElementById("tipRef").innerHTML = selectbox;
        }
    })
}



// função de criação de nova Referência Documental
async function addRefDoc() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("refDocAdd").value == "") {
        errForm += "Referência é obrigatória.<br>";
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
            referencia: document.getElementById("refDocAdd").value,
            numero: document.getElementById("numAdd").value,
            complemento: document.getElementById("compAdd").value,
            url: document.getElementById("urlAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/insert",
            type: "POST",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Referência Documental Criada.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRefDoc').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar Referência Documental",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRefDoc').modal('hide');
                });
            }
        });
    }
}


// função de criação de nova Referência Documental
async function saveRefDoc() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("refDocAdd").value == "") {
        errForm += "Referência é obrigatória.<br>";
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
            referencia: document.getElementById("refDocAdd").value,
            numero: document.getElementById("numAdd").value,
            complemento: document.getElementById("compAdd").value,
            url: document.getElementById("urlAdd").value
        };
        var exit = "no";
        var json = JSON.stringify(objCons);

        var myHeaders = new Headers();
        myHeaders.append("token", await getAdminToken());
        $.ajax({
            url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/update/" + document.getElementById("ref_docValue").value,
            type: "PUT",
            headers: { "token": await getAdminToken() },
            data: json,
            dataType: "json",
            success: function (result) {
                Swal.fire({
                    icon: "success",
                    title: "Referência Documental alterada com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRefDoc').modal('hide');
                });

            },
            error: function (err) {
                console.log(err);
                exit = err;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar Referência Documental",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarRefDoc').modal('hide');
                });
            }
        });
    }
}

function delReferencia() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar esta referência documental?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            var myHeaders = new Headers();
            myHeaders.append("token", await getAdminToken());
            $.ajax({
                url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/delete/" + document.getElementById("ref_docValue").value,
                type: "DELETE",
                headers: { "token": await getAdminToken() },
                dataType: "json",
                success: function (result) {
                    Swal.fire({
                        icon: "success",
                        title: "Referência documental eliminada com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarRefDoc').modal('hide');
                    });

                },
                error: function (err) {
                    console.log(err);
                    exit = err;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao elimiar o referência documental.",
                        text: "Esta referência documental encontra-se associado a um registo",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarRefDoc').modal('hide');
                    });
                }
            });
        }
    });
}