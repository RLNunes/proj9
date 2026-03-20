

/************************* Pessoas *************************** */
/************************ Lista todas as Pessoas Consulta ****************************** */
async function getAllPessoasCon(pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/consultaPessoa/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Pessoas consulta.");
        } else {
            objPes = await response.json();
            pessoas = objPes.allPessoas;
            let tabela = '<table class="table" id="consPessoa" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col" colspan="3" style="background:#b91d47; color:#fff;">Pessoa</th><th scope="col" colspan="3" style="background:#F7464A;">Provocação</th><th scope="col" colspan="2" style="background:#E2EAE9;">Mandado</th>` +
                `<th scope="col" colspan="2" style="background:#D4CCC5;">Consulta</th><th scope="col" colspan="2" style="background:#949FB1;">Ultramar</th><th scope="col" colspan="3" style="background:#4D5360; color:#fff;">Resposta</th>` +
                `</tr>` +
                `<tr>` +
                `<th scope="col" style="background:#b91d47; color:#fff;">Nome</th><th scope="col" style="background:#b91d47; color:#fff;">idade</th>` +
                `<th scope="col" style="background:#b91d47; color:#fff;">Sexo</th><th scope="col" style="background:#F7464A;">Requerente</th><th scope="col" style="background:#F7464A;">Pessoa<br>Citada</th><th scope="col" style="background:#F7464A;">Remetente</th>` +
                `<th scope="col" style="background:#E2EAE9;">Requerente</th><th scope="col" style="background:#E2EAE9;">Pessoa<br>Citada</th><th scope="col" style="background:#D4CCC5;">Requerente</th><th scope="col" style="background:#D4CCC5;">Pessoa<br>Citada</th>` +
                `<th scope="col" style="background:#949FB1;">Requerente</th><th scope="col" style="background:#949FB1;">Pessoa<br>Citada</th><th scope="col" style="background:#4D5360; color:#fff;">Requerente</th><th scope="col" style="background:#4D5360; color:#fff;">Pessoa<br>Citada</th><th scope="col" style="background:#4D5360; color:#fff;">Destinatário</th>` +
                `</tr>`;
            for (const pessoa of pessoas) {
                tabela += `<tr>
                                <td scope="row" style="background:#b91d47; color:#fff;">` + pessoa.nome + `</td>
                                <td scope="row" style="background:#b91d47; color:#fff;">` + pessoa.idade + `</td>
                                <td scope="row" style="background:#b91d47; color:#fff;">` + pessoa.sexo + `</td>`
                if (pessoa.provreq > 0) {
                    tabela += `<td scope="row" style="background:#F7464A;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Prova','Req')">` + pessoa.provreq + `</a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#F7464A;">` + pessoa.provreq + `</td>`;
                }

                if (pessoa.provpescita > 0) {
                    tabela += `<td scope="row" style="background:#F7464A;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Prova','PesCita')">` + pessoa.provpescita + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#F7464A;">` + pessoa.provpescita + `</td>`;
                }

                if (pessoa.provrem > 0) {
                    tabela += `<td scope="row" style="background:#F7464A;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Prova','Rem')">` + pessoa.provrem + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#F7464A;">` + pessoa.provrem + `</td>`;
                }

                if (pessoa.manreq > 0) {
                    tabela += `<td scope="row" style="background:#E2EAE9;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Mando','Req')">` + pessoa.manreq + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#E2EAE9;">` + pessoa.manreq + `</td>`;
                }

                if (pessoa.manpescita > 0) {
                    tabela += `<td scope="row" style="background:#E2EAE9;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Mando','PesCita')">` + pessoa.manpescita + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#E2EAE9;">` + pessoa.manpescita + `</td>`;
                }

                if (pessoa.conreq > 0) {
                    tabela += `<td scope="row" style="background:#D4CCC5;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Con','Req')">` + pessoa.conreq + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#D4CCC5;">` + pessoa.conreq + `</td>`;
                }

                if (pessoa.conpescita > 0) {
                    tabela += `<td scope="row" style="background:#D4CCC5;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Con','PesCita')">` + pessoa.conpescita + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#D4CCC5;">` + pessoa.conpescita + `</td>`;
                }

                if (pessoa.ultrareq > 0) {
                    tabela += `<td scope="row" style="background:#949FB1;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Ultra','Req')">` + pessoa.ultrareq + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#949FB1;">` + pessoa.ultrareq + `</td>`;
                }

                if (pessoa.ultrapescita > 0) {
                    tabela += `<td scope="row" style="background:#949FB1;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Ultra','PesCita')">` + pessoa.ultrapescita + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#949FB1;">` + pessoa.ultrapescita + `</td>`;
                }

                if (pessoa.resreq > 0) {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Res','Req')">` + pessoa.resreq + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;">` + pessoa.resreq + `</td>`;
                }

                if (pessoa.respescita > 0) {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Res','PesCita')">` + pessoa.respescita + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;">` + pessoa.respescita + `</td>`;
                }

                if (pessoa.resdest > 0) {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;"><a href="javascript:openDetalhe(` + pessoa.id + `,'` + pessoa.nome + `','Res','Dest')">` + pessoa.resdest + `<a></td>`;
                } else {
                    tabela += `<td scope="row" style="background:#4D5360; color:#fff;">` + pessoa.resdest + `</td>`;
                }
                tabela += `</tr>`
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objPes.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPessoasCon(1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPessoasCon(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPessoasCon(` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("TablePessoa").innerHTML = tabela;
        }
    });
}


/************************ Provocação ****************************** */
/************************ Lista todas as Pessoas Consulta ****************************** */
async function getAllProvReq(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/getConReqProvoca/" + v_idPes + "/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Provocação requerente.");
        } else {
            objProv = await response.json();
            provocacaos = objProv.allProvocacao;
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
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvReq(` + v_idPes + `, '` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllProvReq(` + v_idPes + `, '` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvReq(` + v_idPes + `, '` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Provocação</b><br>Requerente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

/************************ Lista todas as Pessoas Citadas Consulta ****************************** */
async function getAllProvPesCita(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/getConPesCitaProvoca/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Provocação Pessoa Citada.");
        } else {
            objProv = await response.json();
            provocacaosPesCita = objProv.allProvocacao;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const provocacao of provocacaosPesCita) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editProvocacao(`+ provocacao.id + `)"></i></td>
                                <td scope="row">` + provocacao.id + `</td>
                                <td scope="row">` + provocacao.resumo + `</td>
                                <td scope="row">` + provocacao.registro + `</td>
                                <td scope="row">` + provocacao.referencia_documental + `</a></td>
                                <td scope="row">` + provocacao.folio_pagina + `</td>
                                <td scope="row"` + provocacao.data + `</td>
                                <td scope="row">` + provocacao.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvPesCita(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllProvPesCita(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllProvPesCita(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Provocação</b><br>Pessoas Citadas";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

/************************ Lista todas as Pessoas Citadas Consulta ****************************** */
async function getAllRemCita(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/getConRemProvoca/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Provocação Pessoa Citada.");
        } else {
            objProv = await response.json();
            provocacaosPesCita = objProv.allProvocacao;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const provocacao of provocacaosPesCita) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editProvocacao(`+ provocacao.id + `)"></i></td>
                                <td scope="row">` + provocacao.id + `</td>
                                <td scope="row">` + provocacao.resumo + `</td>
                                <td scope="row">` + provocacao.registro + `</td>
                                <td scope="row">` + provocacao.referencia_documental + `</a></td>
                                <td scope="row">` + provocacao.folio_pagina + `</td>
                                <td scope="row"` + provocacao.data + `</td>
                                <td scope="row">` + provocacao.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllRemCita(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllRemCita(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllRemCita(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Provocação</b><br>Remetente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editProvocacao(v_id) {
    sessionStorage.setItem("v_idProv", v_id);
    document.getElementById("nomePes").innerHTML = "" + pesNome;
    window.location.href = "./EditProvocacao.html";
}


/************************ Provocação ****************************** */
/************************ Lista todas as Requerentes Mandado ****************************** */
async function getAllReqMando(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/mandado/conReqMandado/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Mandado requerente.");
        } else {
            objProv = await response.json();
            provocacaosReq = objProv.allMandados;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Mandado</th><th scope="col" >Nome Quem<br>Envia</th>` +
                `<th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const mandado of provocacaosReq) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editMandado(`+ mandado.pk_mandado + `)"></i></td>
                                <td scope="row">` + mandado.pk_mandado + `</td>
                                <td scope="row">` + mandado.resumo + `</td>
                                <td scope="row">` + mandado.registro + `</td>
                                <td scope="row">` + mandado.referencia_documental + `</a></td>
                                <td scope="row">` + mandado.folio_pagina + `</td>
                                <td scope="row">` + mandado.mandado + `</a></td>
                                <td scope="row">` + mandado.nome_quem_envia + `</td>
                                <td scope="row"` + mandado.data + `</td>
                                <td scope="row">` + mandado.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqMando(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllReqMando(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqMando(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Mandado</b><br>Requerente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}


/************************ Lista todas as Requerentes Mandado ****************************** */
async function getAllPesCitaMando(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/mandado/getConPesCitaMandado/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Mandado Pessoa Citada.");
        } else {
            objProv = await response.json();
            provocacaosReq = objProv.allMandados;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Mandado</th><th scope="col" >Nome Quem<br>Envia</th>` +
                `<th scope="col">Data</th><th scope="col">Ano</th>` +
                `</tr>`;
            for (const mandado of provocacaosReq) {
                tabela += `<tr>
                                <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editMandado(`+ mandado.pk_mandado + `)"></i></td>
                                <td scope="row">` + mandado.pk_mandado + `</td>
                                <td scope="row">` + mandado.resumo + `</td>
                                <td scope="row">` + mandado.registro + `</td>
                                <td scope="row">` + mandado.referencia_documental + `</a></td>
                                <td scope="row">` + mandado.folio_pagina + `</td>
                                <td scope="row">` + mandado.mandado + `</a></td>
                                <td scope="row">` + mandado.nome_quem_envia + `</td>
                                <td scope="row"` + mandado.data + `</td>
                                <td scope="row">` + mandado.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaMando(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPesCitaMando(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaMando(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Mandado</b><br>Pessoa Citada";
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
/************************ Lista todas as Requerentes Consulta ****************************** */
async function getAllReqCon(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/consulta/getConReqConsulta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

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
                                <td scope="row"` + consulta.data + `</td>
                                <td scope="row">` + consulta.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Consulta</b><br>Requerente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}



/************************ Lista todas as Requerentes Consulta ****************************** */
async function getAllPesCitaCon(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/consulta/getConPesCitaConsulta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

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
                                <td scope="row"` + consulta.data + `</td>
                                <td scope="row">` + consulta.ano + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaCon(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPesCitaCon(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaCon(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Consulta</b><br>Pessoa Citada";
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
/************************ Lista todas as Requerentes Ultramar ****************************** */
async function getAllReqUltra(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/getConReqUltramar/" + v_idPes + "/" + pageNum + "/" + rowsPage;

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
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqCon(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Ultramar</b><br>Requerente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

/************************ Lista todas as Requerentes Ultramar ****************************** */
async function getAllPesCitaUltra(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/getConPesCitaUltramar/" + v_idPes + "/" + pageNum + "/" + rowsPage;

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
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaUltra(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPesCitaUltra(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaUltra(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Ultramar</b><br>Pessoa Citada";
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
/************************ Lista todas as Requerentes Resposta ****************************** */
async function getAllReqRes(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/getConReqResposta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Consulta requerente.");
        } else {
            objProv = await response.json();
            respostaReq = objProv.allPalResposta;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Nova Ordem/<br>Não Cumprimento</th>` +
                `<th scope="col" >Tipologia Diplomática</th><th scope="col">Data</th><th scope="col">Ano</th>` +
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
                                <td scope="row">` + resposta.tipologia_diplomatica + `</a></td>
                                <td scope="row"` + resposta.data_resposta + `</td>
                                <td scope="row">` + resposta.ano_resposta + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqRes(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllReqRes(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllReqRes(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Resposta</b><br>Requerente";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}


/************************ Lista todas as Pessoa Citada Resposta ****************************** */
async function getAllPesCitaRes(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/getConPesCitaResposta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista de Consulta requerente.");
        } else {
            objProv = await response.json();
            respostaReq = objProv.allPalResposta;
            let tabela = '<table class="table" id="consReqProva" style="text-align: center;">' +
                `<tr>` +
                `<th scope="col">Editar</th><th scope="col" >Nº</th><th scope="col">Resumo</th><th scope="col">Registro</th>` +
                `<th scope="col">Referência<br>Documental</th><th scope="col" >Fólio/Página</th><th scope="col">Nova Ordem/<br>Não Cumprimento</th>` +
                `<th scope="col" >Tipologia Diplomática</th><th scope="col">Data</th><th scope="col">Ano</th>` +
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
                                <td scope="row">` + resposta.tipologia_diplomatica + `</a></td>
                                <td scope="row"` + resposta.data_resposta + `</td>
                                <td scope="row">` + resposta.ano_resposta + `</td>
                            </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objProv.rowcount / rowsPage);
            if (pageNum != 1) {
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaRes(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllPesCitaRes(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllPesCitaRes(` + v_idPes + `,'` + pesNome + `',` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Resposta</b><br>Pessoa Citada";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

/************************ Lista todas as Destinatário Resposta ****************************** */
async function getAllDestRes(v_idPes, pesNome, pageNum, rowsPage) {

    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/getConDestResposta/" + v_idPes + "/" + pageNum + "/" + rowsPage;

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
                `th scope="col">Data</th><th scope="col">Ano</th>` +
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
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllDestRes(` + v_idPes + `,'` + pesNome + `',1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
                if (pageNum > 5) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
            }
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    if (i > pageNum - 5 && i < pageNum + 5) {
                        tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllDestRes(` + v_idPes + `,'` + pesNome + `',` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                    }
                }
            }
            if (pageNum != numPage) {
                if (pageNum < numPage - 4) {
                    tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
                }
                tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllDestRes(` + v_idPes + `,'` + pesNome + `',` + + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
            }
            tabela += `</ul> `;
            document.getElementById("tituloDetalhe").innerHTML = "<b>Resposta</b><br>Destinatário";
            document.getElementById("nomePes").innerHTML = "" + pesNome;
            document.getElementById("tabDetalhes").innerHTML = tabela;
        }
    });
}

async function editResposta(v_id) {
    sessionStorage.setItem("v_idResp", v_id);
    window.location.href = "./EditResposta.html";
  }


async function openDetalhe(v_pesId, v_pesNome, v_tab, v_pess) {
    if (v_tab == 'Prova') {
        if (v_pess == 'Req') {
            await getAllProvReq(v_pesId, v_pesNome, 1, 10);
        } else {
            if (v_pess == 'PesCita') {
                await getAllProvPesCita(v_pesId, v_pesNome, 1, 10);
            } else {
                if (v_pess == 'Rem') {
                    await getAllRemCita(v_pesId, v_pesNome, 1, 10);
                }
            }
        }
    } else {
        if (v_tab == 'Mando') {
            if (v_pess == 'Req') {
                await getAllReqMando(v_pesId, v_pesNome, 1, 10);
            } else {
                if (v_pess == 'PesCita') {
                    await getAllPesCitaMando(v_pesId, v_pesNome, 1, 10);
                }
            }
        } else {
            if (v_tab == 'Con') {
                if (v_pess == 'Req') {
                    await getAllReqCon(v_pesId, v_pesNome, 1, 10);
                } else {
                    if (v_pess == 'PesCita') {
                        await getAllPesCitaCon(v_pesId, v_pesNome, 1, 10);
                    }
                }
            } else {
                if (v_tab == 'Ultra') {
                    if (v_pess == 'Req') {
                        await getAllReqUltra(v_pesId, v_pesNome, 1, 10);
                    } else {
                        if (v_pess == 'PesCita') {
                            await getAllPesCitaUltra(v_pesId, v_pesNome, 1, 10);
                        }
                    }
                } else {
                    if (v_tab == 'Res') {
                        if (v_pess == 'Req') {
                            await getAllReqRes(v_pesId, v_pesNome, 1, 10);
                        } else {
                            if (v_pess == 'PesCita') {
                                await getAllPesCitaRes(v_pesId, v_pesNome, 1, 10);
                            } else {
                                if (v_pess == 'Dest') {
                                    await getAllDestRes(v_pesId, v_pesNome, 1, 10);
                                }
                            }
                        }
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