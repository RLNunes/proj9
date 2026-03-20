

/************************* Palavras-Chaves *************************** */
/************************ Lista todos os Temas Consulta ****************************** */
async function getAllPalChavesCon(pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/palavra_chave/consultaPalChave/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Temas consulta.");
        } else {
            objPes = await response.json();
            let palChaves = objPes.allPalChaves;
            let tabela = '<table class="table" id="consPessoa" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col" style="background:#b91d47; color:#fff;">Palavra-Chave</th><th scope="col" style="background:#F7464A;">Provocação</th><th scope="col" style="background:#E2EAE9;">Mandado</th>` +
                `<th scope="col"  style="background:#D4CCC5;">Consulta</th><th scope="col" style="background:#949FB1;">Ultramar</th><th scope="col" style="background:#4D5360; color:#fff;">Resposta</th>` +
                `</tr>`;
            for (const palChave of palChaves) {
                tabela += `<tr>
                                <td scope="row" style="background:#b91d47; color:#fff;">` + palChave.palavra_chave + `</td>`
                if (palChave.provnum > 0) {
                    tabela += `<td scope="row" style="background:#F7464A;"><a href="javascript:openDetalhe(` + palChave.pk_palavra_chave + `,'` + palChave.palavra_chave + `','Prova')">` + palChave.provnum + `</a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#F7464A;">` + palChave.provnum + `</td>`;
                }

                if (palChave.mannum > 0) {
                    tabela += `<td scope="row" style="background:#E2EAE9;"><a href="javascript:openDetalhe(` + palChave.pk_palavra_chave + `,'` + palChave.palavra_chave + `','Mando')">` + palChave.mannum + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#E2EAE9;">` + palChave.mannum + `</td>`;
                }

                if (palChave.connum > 0) {
                    tabela += `<td scope="row" style="background:#D4CCC5;"><a href="javascript:openDetalhe(` + palChave.pk_palavra_chave + `,'` + palChave.palavra_chave + `','Con')">` + palChave.connum + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#D4CCC5;">` + palChave.connum + `</td>`;
                }

                if (palChave.ultranum > 0) {
                    tabela += `<td scope="row" style="background:#949FB1;"><a href="javascript:openDetalhe(` + palChave.pk_palavra_chave + `,'` + palChave.palavra_chave + `','Ultra')">` + palChave.ultranum + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#949FB1;">` + palChave.ultranum + `</td>`;
                }

                if (palChave.resnum > 0) {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;"><a href="javascript:openDetalhe(` + palChave.pk_palavra_chave + `,'` + palChave.palavra_chave + `','Res')">` + palChave.resnum + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;">` + palChave.resnum + `</td>`;
                }
                tabela += `</tr>`
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objPes.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPalChavesCon(1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPalChavesCon(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPalChavesCon(` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("TablePessoa").innerHTML = tabela;
        }
    });
}


/************************ Provocação ****************************** */
/************************ Lista todos os temas Consulta Provocação ****************************** */
async function getAllProvPalChave(v_idPalChave, palChave, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/getConPalChaveProvoca/" + v_idPalChave + "/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Provocação tema.");
        } else {
            objProv = await response.json();
            provocacaos = objProv.allProvocacao;
            console.log(objProv)
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const provocacao of provocacaos) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editProvocacao(`+ provocacao.id + `)"></i></td>
                                <td scope="row">` + provocacao.id + `</td>
                                <td scope="row">` + provocacao.resumo + `</td>
                                <td scope="row">` + provocacao.registro + `</td>
                                <td scope="row">` + provocacao.referencia_documental + `</a></td>
                                <td scope="row">` + provocacao.folio_pagina + `</td>
                                <td scope="row"` + provocacao.data + `</td>
                                <td scope="row">` + provocacao.ano + `</td>
                            </tr>`
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvPalChave(` + v_idPalChave + `, '` + palChave + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllProvPalChave(` + v_idPalChave + `, '` + palChave + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvPalChave(` + v_idPalChave + `, '` + palChave + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Prvocações</b>";
            document.getElementById("nomePes").innerHTML = "" + palChave;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editProvocacao(v_id) {
    sessionStorage.setItem("v_idProv", v_id);
    window.location.href = "./EditProvocacao.html";
}

/************************ Mandado ****************************** */
/************************ Lista todos os temas Mandado  Consulta****************************** */
async function getAllMandoPalChave (v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/mandado/getPalChaveMandado/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Mandado Tema.");
        } else {
            objProv = await response.json();
            Mandados = objProv.allMandados;
                        let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const mandado of Mandados) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editProvocacao(`+ mandado.pk_mandado + `)"></i></td>
                                <td scope="row">` + mandado.pk_mandado + `</td>
                                <td scope="row">` + mandado.resumo + `</td>
                                <td scope="row">` + mandado.registro + `</td>
                                <td scope="row">` + mandado.referencia_documental + `</a></td>
                                <td scope="row">` + mandado.folio_pagina + `</td>
                                <td scope="row"` + mandado.data + `</td>
                                <td scope="row">` + mandado.ano + `</td>
                            </tr>`
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllpalChaveTema(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllpalChaveTema(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllpalChaveTema(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Mandado</b>";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editMandado(v_id) {
    sessionStorage.setItem("v_idMan", v_id);
    window.location.href = "./EditMandado.html";
}


/************************ Consulta ****************************** */
/************************ Lista todos os Tema Consulta Consulta ****************************** */
async function getAllPalChaveCon(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/consulta/getConPalChaveConsulta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Consulta requerente.");
        } else {
            objProv = await response.json();
            consultaReq = objProv.allConsulta;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Sumula</th><th scope="col" >Parecer<br>Régio</th>` +
                `<th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const consulta of consultaReq) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editConsulta(`+ consulta.id + `)"></i></td>
                                <td scope="row">` + consulta.id + `</td>
                                <td scope="row">` + consulta.resumo + `</td>
                                <td scope="row">` + consulta.registro + `</td>
                                <td scope="row">` + consulta.referencia_documental + `</a></td>
                                <td scope="row">` + consulta.folio_pagina + `</td>
                                <td scope="row">` + consulta.sumula + `</a></td>
                                <td scope="row">` + consulta.parecer_regio + `</td>
                                <td scope="row"` + consulta.data_consulta + `</td>
                                <td scope="row">` + consulta.ano_consulta + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllTemaPalChave(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllTemaPalChave(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllTemaPalChave(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Consulta</b>";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editConsulta(v_id) {
    sessionStorage.setItem("v_idCons", v_id);
    window.location.href = "./EditConsulta.html";
}


/************************ Ultramar ****************************** */
/************************ Lista todos Temas Ultramar Consulta ****************************** */
async function getAllUltraPalChave(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/getConPalChaveUltramar/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Consulta requerente.");
        } else {
            objProv = await response.json();
            ultramarReq = objProv.allUltramar;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Autoridade</th>` +
                `<th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const ultramar of ultramarReq) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editUltramar(`+ ultramar.pk_ultramar + `)"></i></td>
                                <td scope="row">` + ultramar.pk_ultramar + `</td>
                                <td scope="row">` + ultramar.resumo + `</td>
                                <td scope="row">` + ultramar.registro + `</td>
                                <td scope="row">` + ultramar.referencia_documental + `</a></td>
                                <td scope="row">` + ultramar.folio_pagina + `</td>
                                <td scope="row">` + ultramar.autoridade + `</a></td>
                                <td scope="row"` + ultramar.data + `</td>
                                <td scope="row">` + ultramar.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllUltraPalChave(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllUltraPalChave(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllUltraPalChave(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Ultramar</b>";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editUltramar(v_id) {
    sessionStorage.setItem("v_idUlt", v_id);
    window.location.href = "./EditUltramar.html";
}



/************************ Resposta ****************************** */
/************************ Lista todas as Tema Rewsposta ****************************** */
async function getAllResPalChave(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/getConPalChaveResposta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Consulta Destinatario.");
        } else {
            objProv = await response.json();
            respostaReq = objProv.allPalResposta;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Nova Ordem/<br>Não Cumprimento</th>` +
                `<th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const resposta of respostaReq) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editResposta(`+ resposta.pk_resposta + `)"></i></td>
                                <td scope="row">` + resposta.pk_resposta + `</td>
                                <td scope="row">` + resposta.resumo + `</td>
                                <td scope="row">` + resposta.registro + `</td>
                                <td scope="row">` + resposta.referencia_documental + `</a></td>
                                <td scope="row">` + resposta.folio_pagina + `</td>
                                <td scope="row">` + resposta.nova_ordem_nao_cumprimento + `</a></td>
                                <td scope="row"` + resposta.data_resposta + `</td>
                                <td scope="row">` + resposta.ano_resposta + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllResPalChave(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllResPalChave(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllResPalChave(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Resposta</b>";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editResposta(v_id) {
    sessionStorage.setItem("v_idResp", v_id);
    window.location.href = "./EditResposta.html";
}

async function openDetalhe(v_pesId, v_tema, v_tab, v_pess) {
    if (v_tab == 'Prova') {
        getAllProvPalChave(v_pesId, v_tema, 1, 10);
    } else {
        if (v_tab == 'Mando') {
            getAllMandoPalChave(v_pesId, v_tema, 1, 10);

        } else {
            if (v_tab == 'Con') {
                getAllPalChaveCon(v_pesId, v_tema, 1, 10);

            } else {
                if (v_tab == 'Ultra') {
                    getAllUltraPalChave(v_pesId, v_tema, 1, 10);

                } else {
                    if (v_tab == 'Res') {
                        getAllResPalChave(v_pesId, v_tema, 1, 10);

                    }
                }
            }
        }
    }
    $('#modalDetalhes').modal('show');

}

function cancelDetalhes() {
    $('#modalDetalhes').modal('hide');
}