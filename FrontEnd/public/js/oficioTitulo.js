
/************************* Ofício Título *************************** */
/************************ Lista todos os Ofícios Títulos ****************************** */
async function getAllOficioTitulo(pageNum, rowsPage) {
    const urlBase = v_url + "/CircPeticionario/webresources/oficio_titulo/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos ofícios títulos ");
        } else {
            let objOficiosTitulos = await response.json();
            let oficiosTitulos = objOficiosTitulos.allOficioTitulo;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Ofício Título</th><th scope="col">Agregador</th>' +
                '</tr>';
            for (const oficioTitulo of oficiosTitulos) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editOficioTitulo(`+ oficioTitulo.pk_oficio_titulo + `)"></i></td>
                                    <td scope="row">` + oficioTitulo.designacao + `</td>
                                    <td scope="row">` + oficioTitulo.oficio_agregador + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objOficiosTitulos.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getAllOficioTitulo(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableOficioTitulo").innerHTML = tabela;
        }
    })
}

/************************ procurar as referências ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getAllOficioTitulo(1, 10)
    } else {
        urlBase = v_url + "/CircPeticionario/webresources/oficio_titulo/search/" + v_search;

        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);
alert('ss')
        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das referências ");
            } else {
                let oficiosTitulos = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Ofício Título</th><th scope="col">Agregador</th>' +
                    '</tr>';
                let conta = 0;
                for (const oficioTitulo of oficiosTitulos) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editOficioTitulo(`+ oficioTitulo.pk_oficio_titulo + `)"></i></td>
                                    <td scope="row">` + oficioTitulo.designacao + `</td>
                                    <td scope="row">` + oficioTitulo.oficio_agregador + `</td>
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
                document.getElementById("TableOficioTitulo").innerHTML = tabela;
            }
        });
    }
}
