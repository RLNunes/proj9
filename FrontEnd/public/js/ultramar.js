function getAno(v_valor) {
  document.getElementById("anoUlt").value = v_valor.substr(6);
}

async function filtrar(vsearch) {
    let proc;
    if (vsearch == null || vsearch == '') {
        proc = 'all';
    } else {
        proc = vsearch;
    }
    let pessoa = document.getElementById("filPes").value;
    if (pessoa == null || pessoa == '') {
        pessoa = "all";
    }
    const tema = document.getElementById("selFilTema").value;
    const palChave = document.getElementById("selFilPalCha").value;
    if (proc == 'all' && pessoa == 'all' && tema == '0' && palChave == '0') {
        search('');
    }
    else {
        const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/search/" + proc + "/" + pessoa + "/" + tema + "/" + palChave;
        console.log(urlBase);
        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista das provocações");
            } else {
                Ultramares = await response.json();
                let tabela = '<table class="table">' +
                '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nº</th><th scope="col">Registro</th><th scope="col">Resumo</th>' +
                    '<th scope="col">Referência Documental</th><th scope="col">Pessoas<br>Citadas</th><th scope="col">Requerente</th>' +
                    '<th scope="col">Palavra<br>Chave</th><th scope="col">Tema</th><th>Ano</th>' +
                '</tr>';
                conta = 0;
                for (const Ultramar of Ultramares) {
                    tabela += `<tr>
                                  <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editUltramar(`+ Ultramar.pk_ultramar + `)"></i></td>
                                  <td scope="row">` + Ultramar.pk_ultramar + `</td>
                                  <td scope="row">` + Ultramar.registro + `</td>
                                  <td scope="row">` + Ultramar.resumo + `</td>
                                  <td scope="row">` + Ultramar.referencia_documental + `</td>
                                  <td scope="row">` + Ultramar.pescita + `</td>
                                  <td scope="row">` + Ultramar.pesreq + `</td>
                                  <td scope="row">` + Ultramar.palcha + `</td>
                                  <td scope="row">` + Ultramar.tema + `</td>
                                  <td scope="row">`+ Ultramar.ano + `</td>
                              </tr>`;
                    if (conta >= 10) {
                        break;
                    }
                    conta++;
                }
                if (conta >= 10) {
                    tabela += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
                } else {
                    if (conta == 0) {
                        tabela += '<tr><td colspan="9"><h4>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</h4></td></tr>'
                    }
                }
                tabela += `</table>`;
                document.getElementById("TableUltramar").innerHTML = tabela;
            }
        });
    }
}

function limpaFiltro(vsearch) {
    document.getElementById("filPes").value = "";
    document.getElementById("selFilTema").value = 0;
    document.getElementById("selFilPalCha").value = 0;
    search(vsearch);
}

/************************* Ultramar *************************** */
/************************ Lista todas os Ultramares ****************************** */
async function getAllUltramares(pageNum, rowsPage) {
  rootUrl = await getUrlPayaraDireto();
  let urlBase = rootUrl + "/CircPeticionario/webresources/tema/all/1/1000";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  document.getElementById("filPes").value = '';
  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);
  sessionStorage.setItem("v_pageNum", pageNum);

  await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
          let listTemas = await response.json();
          let selTema = '<select name="selFilTema" id="selFilTema" class="form-control" >' +
              '<option value="0" selected>Tema</option>'
          for (const listTema of listTemas.allTemas) {

              selTema += ' <option value="' + listTema.pk_tema + '">' + listTema.tema + '</option>'
          }
          selTema += `</select>`;
          document.getElementById("filTema").innerHTML = selTema;
      }
  })

  urlBase = rootUrl + "/CircPeticionario/webresources/palavra_chave/all/1/1000";
  myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
          let listPalChaves = await response.json();
          let selPalChave = '<select name="selFilPalCha" id="selFilPalCha" class="form-control">' +
              '<option value="0" selected>Palavra Chave</option>'
          for (const listPalChave of listPalChaves.allPalChaves) {

              selPalChave += ' <option value="' + listPalChave.pk_palavra_chave + '">' + listPalChave.palavra_chave + '</option>'
          }
          selPalChave += `</select>`;
          document.getElementById("filPalCha").innerHTML = selPalChave;
      }
  })

  urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/all/" + pageNum + "/" + rowsPage;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista de ultramares");
    } else {
      let objUltra = await response.json();
      let Ultramares = objUltra.allUltramar;
      let tabela = '<table class="table">' +
        '<tr>' +
            '<th scope="col">Editar</th><th scope="col">Nº</th><th scope="col">Registro</th><th scope="col">Resumo</th>' +
            '<th scope="col">Referência Documental</th><th scope="col">Pessoas<br>Citadas</th><th scope="col">Requerente</th>' +
            '<th scope="col">Palavra<br>Chave</th><th scope="col">Tema</th><th>Ano</th>' +
        '</tr>';
      for (const Ultramar of Ultramares) {
        tabela += `<tr>
                                  <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editUltramar(`+ Ultramar.pk_ultramar + `)"></i></td>
                                  <td scope="row">` + Ultramar.pk_ultramar + `</td>
                                  <td scope="row">` + Ultramar.registro + `</td>
                                  <td scope="row">` + Ultramar.resumo + `</td>
                                  <td scope="row">` + Ultramar.referencia_documental + `</td>
                                  <td scope="row">` + Ultramar.pescita + `</td>
                                  <td scope="row">` + Ultramar.pesreq + `</td>
                                  <td scope="row">` + Ultramar.palcha + `</td>
                                  <td scope="row">` + Ultramar.tema + `</td>
                                  <td scope="row">`+ Ultramar.ano + `</td>
                              </tr>`;
      }
      tabela += `</table>`;

      // Paginação
      tabela += ` <ul class="pagination">`;
      let numPage = Math.ceil(objUltra.rowcount / rowsPage);
      if (pageNum != 1) {
          tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllUltramares(1,` + rowsPage + `)" style="color: white;">Inicio</a></span></li>`;
          if (pageNum > 5) {
              tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
          }
      }
      for (let i = 1; i <= numPage; i++) {
          if (i == pageNum) {
              tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
          } else {
              if (i > pageNum - 5 && i < pageNum + 5) {
                  tabela += ` <li class="page-item"><a class="page-link" href="javascript:getAllUltramares(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
              }
          }
      }
      if (pageNum != numPage) {
          if (pageNum < numPage - 4) {
              tabela += `<li class="page-item"><span class="page-link" >. . .</span></li>`;
          }
          tabela += `<li class="page-item active"><span class="page-link" ><a href="javascript:getAllUltramares(` + numPage + `,` + rowsPage + `)" style="color: white;">Fim</a></span></li>`;
      }
      tabela += `</ul> `;
      document.getElementById("TableUltramar").innerHTML = tabela;
    }
  })
}
/************************ Lista todas os Ultramares ****************************** */
async function search(v_search) {
  if (v_search == null || v_search == '') {
    const v_pageNum =  parseInt(sessionStorage.getItem("v_pageNum"));
    await
    getAllUltramares(v_pageNum, 10);
  } else {
    let pessoa = document.getElementById("filPes").value;
    if (pessoa == null || pessoa == '') {
        pessoa = "all";
    }
    const tema = document.getElementById("selFilTema").value;
    const palChave = document.getElementById("selFilPalCha").value;
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/search/" + v_search + "/" + pessoa + "/" + tema + "/" + palChave;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
        console.log("Erro ao devolver a lista de ultramares");
      } else {
        let Ultramares = await response.json();
        let tabela = '<table class="table">' +
          '<tr>' +
              '<th scope="col">Editar</th><th scope="col">Nº</th><th scope="col">Registro</th><th scope="col">Resumo</th>' +
              '<th scope="col">Referência Documental</th><th scope="col">Pessoas<br>Citadas</th><th scope="col">Requerente</th>' +
              '<th scope="col">Palavra<br>Chave</th><th scope="col">Tema</th><th>Ano</th>' +
          '</tr>';
        let conta = 0;
        for (const Ultramar of Ultramares) {
          tabela += `<tr>
                        <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="editUltramar(`+ Ultramar.pk_ultramar + `)"></i></td>
                        <td scope="row">` + Ultramar.pk_ultramar + `</td>
                        <td scope="row">` + Ultramar.registro + `</td>
                        <td scope="row">` + Ultramar.resumo + `</td>
                        <td scope="row">` + Ultramar.referencia_documental + `</td>
                        <td scope="row">` + Ultramar.pescita + `</td>
                        <td scope="row">` + Ultramar.pesreq + `</td>
                        <td scope="row">` + Ultramar.palcha + `</td>
                        <td scope="row">` + Ultramar.tema + `</td>
                        <td scope="row">`+ Ultramar.ano + `</td>
                    </tr>`;
          if (conta >= 10) {
            break;
          }

          conta++;
        }
        if (conta >= 10) {
          tabela += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        } else {
          if (conta == 0) {
            tabela += '<tr><td colspan="7"><h4>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</h4></td></tr>'
          }
        }
        tabela += `</table>`;
        document.getElementById("TableUltramar").innerHTML = tabela;
      }
    });
  }
}

/************************ devolve o ultramar pedido ****************************** */
async function getUltramar(ultId) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/record/" + ultId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  const getAdmin = await getIsAdmin();
  if (getAdmin == 'Y') {
    document.getElementById('btnDel').style.visibility = 'visible';
  } else {
    document.getElementById('btnDel').style.visibility = 'hidden';
  }
  
  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver o ultramar");
    } else {
      ultramar = await response.json();
      document.getElementById("ultObs").value = ultramar.resumo;
      document.getElementById("anoUlt").value = ultramar.ano;
      document.getElementById("autUlt").value = ultramar.autoridade;
      document.getElementById("datUlt").value = ultramar.data;
      document.getElementById("folioPagina").value = ultramar.folio_pagina;
      document.getElementById("IdUltra").innerHTML = 'Ultramar Nº ' + ultId;
      if (ultramar.anoPost == "Y") {
        document.getElementById("anoPos").checked = true;
      } else {
        document.getElementById("anoPos").checked = false;
      }
      if (ultramar.anoPost == "N") {
        document.getElementById("anoAnt").checked = true;
      } else {
        document.getElementById("anoAnt").checked = false;
      }
      getRefDoc(ultId, ultramar.referencia_documental);
      listSecEstado(ultId);
      listRefDoc('');
      listRegistroAdd(ultramar.registro, '');
      listPesCitada(ultId);
      listPalavraChave(ultId);
      listRequerente(ultId);
      listTema(ultId);
      listProvocacao(ultId);
      listMandado(ultId);
      listConsulta(ultId);
      listUltramar(ultId);
      listResposta(ultId);
      listTermo(ultId);
      listFreguesia(ultId);
      listComarca(ultId);
      listCapitania(ultId);

    }
  })
}
/*************************** Editar Ultramar ************************************** */
async function editUltramar(v_id) {
  sessionStorage.setItem("v_idUlt", v_id);
  window.location.href = "./EditUltramar.html";
}



/********************* Lista Registros com Procura ***************************** */
async function listRegistroAdd(v_id, v_search) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/registro/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos registros");
    } else {
      let Registros = await response.json();
      let tableRegistros = '<table id="tableRegistro" class="ListPopUpModal">';
      let conta = 0
      for (const registro of Registros) {
        if (registro.id == v_id) {
          adicRegistro(v_id, registro.descricao);
        }
        tableRegistros += '<tr><td><a href="javascript:adicRegistro(' + registro.id + ',`' + registro.descricao + '`);">' + registro.descricao + '</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableRegistros += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableRefDoc += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableRegistros += `</table>`;
      document.getElementById("tabRegistro").innerHTML = tableRegistros;
    }
  })
}

function openRegistro() {
  $('#criarMan').modal('hide');
  $('#modalRegistro').modal('show');

}

function cancelRegistro() {
  $('#modalRegistro').modal('hide');
  $('#criarMan').modal('show');

}

/**************** Adiciona á text box do registro ************************ */
function adicRegistro(v_id, v_desc) {
  document.getElementById("registroAdd").value = v_desc;
  document.getElementById("registroAddValue").value = v_id;
  $('#modalRegistro').modal('hide');
  $('#criarMan').modal('show');
}

/****************** Referência Documental ******************* */
/************************ devolve a referência documental pedida ****************************** */
async function getRefDoc(v_respId, v_refDocId) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/record/" + v_refDocId;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a referencia documental");
    } else {
      refDoc = await response.json();
      adicRefDoc(v_refDocId, refDoc.referencia + ' (' + refDoc.complemento + ')');
    }
  })
}

/****************** Referência Documental ******************* */
/********************* Lista Referências Documentais ***************************** */
async function listRefDoc(v_search) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das Referências Documentais");
    } else {
      let refDoc = await response.json();
      console.log(refDoc);
      let tableRefDoc = '<table id="tableRefDoc" class="ListPopUpModal">';
      let conta = 0;
      for (const refDocs of refDoc) {

        tableRefDoc += '<tr><td><a href="javascript:adicRefDoc(' + refDocs.pk_ref_documento + ',`' + refDocs.referencia + ' (' + refDocs.complemento + ')`);">' + refDocs.referencia + ' (' + refDocs.complemento + ')</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableRefDoc += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableRefDoc += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableRefDoc += `</table>`;
      document.getElementById("tabRefDoc").innerHTML = tableRefDoc;
      console.log(tableRefDoc)
    }
  })
}

function openRefDoc() {
  $('#criarMan').modal('hide');
  $('#modalRefDoc').modal('show');

}

function cancelRefDoc() {
  $('#modalRefDoc').modal('hide');
  $('#criarMan').modal('show');

}


/**************** Adiciona á text box da referência documental ************************ */
function adicRefDoc(v_id, v_desc) {
  document.getElementById("ref_doc").value = v_desc;
  document.getElementById("ref_docValue").value = v_id;
  $('#modalRefDoc').modal('hide');
  $('#criarMan').modal('show');
}

function goToModalRef() {
  $('#modalRefDoc').modal('hide');
  $('#criarRefDoc').modal('show');

} function cancelModalRef() {
  $('#criarRefDoc').modal('hide');
  $('#modalRefDoc').modal('show');

}

/************************ devolve a referência documental pedida ****************************** */
async function getRefDocs(v_respId, v_refDocId) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/record/" + v_refDocId;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a referencia documental");
    } else {
      refDoc = await response.json();
      adicRefDoc(v_refDocId, refDoc.referencia + ' (Nº:' + refDoc.numero + ')');
    }
  })
}


/****************** Tipo Referência ******************* */
async function getAllTipRef(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/tipo_referencia_documental/all";
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

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




/****************** Secretário/Concelheiro ******************* */
async function listSecEstado(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_secretario_conselho_ultramar/allUltramar/" + v_id;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log(response);
    } else {
      let listSecEstados = await response.json();
      console.log(listSecEstados);
      let tableSecEstado = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableListSecEstado" >Secretário/Conselheiro:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selSecEstado(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delSecEstado(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableListSecEstado" class="listPopUp">';

      for (const listSecEstado of listSecEstados) {
        tableSecEstado += '<tr><td class="checkDel"><i class="checkDelSecEst far fa-circle" style="color:red;" onclick="checkDelSecEst(this);" value="' + listSecEstado.id + '"></i></td><td>' + listSecEstado.nome + '</td></tr>';
      }
      tableSecEstado += `</table>`;
      document.getElementById("secEstado").innerHTML = tableSecEstado;
      console.log(tableSecEstado)
    }
  })
}

async function selectSecEstado(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_secretario_conselho_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log(response);
    } else {
      let listSecEstados = await response.json();
      console.log(listSecEstados);
      let tableSecEstado = '<div class="scroll"><table id="tableSelSecEstado" class="ListPopUpModal">';

      for (const listSecEstado of listSecEstados) {

        tableSecEstado += '<tr><td><a href="javascript:addSecEstado(' + v_id + ',' + listSecEstado.id + ')">' + listSecEstado.nome + '</a></td></tr>'


      }
      tableSecEstado += `</table></div>`;
      document.getElementById("tabSecEstado").innerHTML = tableSecEstado;
      console.log(tableSecEstado)
    }
  })
}
/****************** Pessoas Citadas ******************* */
/******************** Devolve lista das pessoas citadas já introduzidas *************************** */
async function listPesCitada(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pcitadas_ultramar/allUltramar/" + v_id + "/all";
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das pessoas citadas");
    } else {
      let PesCitadas = await response.json();
      console.log(PesCitadas);
      let tableDPesCitada = '<div class="row" styyle="margin:10px;"><div class="column listTabNome" align="left"><label for="tableListPesCitada" >Pessoas Citadas:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selPesCitada(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delPesCitada(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableListPesCitada" class="listPopUp">';

      for (const PesCitada of PesCitadas) {

        tableDPesCitada += '<tr><td class="checkDel"><i class="checkDelPesCitada far fa-circle" style="color:red;" onclick="checkDelPesCitada(this);" value="' + PesCitada.id + '"></i></td><td>' + PesCitada.nome + '</td></tr>'


      }
      tableDPesCitada += `</table>`;
      document.getElementById("pesCitadas").innerHTML = tableDPesCitada;
    }
  })
}

/*********************** Devolve lista das Pessoas Citadas que ainda não foram escolhidss ************************************** */
async function selectPesCitada(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pcitadas_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listPesCitadas = await response.json();
      let tablePesCitada = '<table id="tableSelPesCitada" class="ListPopUpModal">';
      let conta = 1;
      for (const listPesCitada of listPesCitadas) {

        tablePesCitada += '<tr><td><a href="javascript:addPesCitada(' + v_id + ',' + listPesCitada.id + ')">' + listPesCitada.nome + '</a></td>' +
          '<td> <span type="button" onclick="getPessoaInfo(' + listPesCitada.id + ')">' +
          '<img src="../img/info.png" width="20px"></span></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tablePesCitada += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tablePesCitada += `</table>`;
      document.getElementById("tabmodalPesCitada").innerHTML = tablePesCitada;
    }
  })
}



/****************** Palavra Chave ******************* */
/******************** Devolve lista das palavras chaves já introduzidas *************************** */
async function listPalavraChave(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pchave_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das Palavras Chaves");
    } else {
      let pChaves = await response.json();
      console.log(pChaves);
      let tablepChaves = '<div class="row" ><div class="column listTabNome" align="left"><label for="tablepChaves" >Palavra Chave:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selPalChave(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delPalChave(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tablepChaves" class="listPopUp">';

      for (const pChave of pChaves) {

        tablepChaves += '<tr><td class="checkDel"><i class="checkDelPalChave far fa-circle" style="color:red;" onclick="checkDelPalChave(this);" value="' + pChave.id + '"></i></td><td>' + pChave.palavra_chave + '</td></tr>'


      }
      tablepChaves += `</table>`;
      document.getElementById("palavraChave").innerHTML = tablepChaves;
      console.log(tablepChaves)
    }
  })
}
/*********************** Devolve lista das palavras chaves que ainda não foram escolhidas ************************************** */
async function selectPalChave(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pchave_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listPalChaves = await response.json();
      let tablePalChaves = '<div class="scroll"><table id="tableSelPalChave" class="ListPopUpModal">';
      let conta = 1;
      for (const listPalChave of listPalChaves) {

        tablePalChaves += '<tr><td><a href="javascript:addPalChave(' + v_id + ',' + listPalChave.id + ')">' + listPalChave.palavra_chave + '</a></td></tr>'

        /* if (conta == 10) {
            break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
         tablePalChaves += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tablePalChaves += `</table></div>`;
      document.getElementById("tabmodalPalChave").innerHTML = tablePalChaves;
    }
  })
}


/****************** Requerente ******************* */
/******************** Devolve lista dos Requerentes já introduzidos *************************** */
async function listRequerente(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_requerente_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos Requerentes");
    } else {
      let requerentes = await response.json();
      console.log(requerentes);
      let tableRequerente = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableRequerente" >Requerente:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selRequerente(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delRequerente(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableRequerente" class="listPopUp">';

      for (const requerente of requerentes) {

        tableRequerente += '<tr><td class="checkDel"><i class="checkDelRequerente far fa-circle" style="color:red;" onclick="checkDelRequerente(this);" value="' + requerente.id + '"></i></td><td>' + requerente.nome + '</td></tr>'


      }
      tableRequerente += `</table>`;
      document.getElementById("requerente").innerHTML = tableRequerente;
    }
  })
}

/*********************** Devolve lista dos requerentes que ainda não foram escolhidos ************************************** */
async function selectRequerente(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_requerente_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listRequerentes = await response.json();
      let tableRequerente = '<table id="tableSelRequerente" class="ListPopUpModal">';
      let conta = 1;
      for (const listRequerente of listRequerentes) {

        tableRequerente += '<tr><td><a href="javascript:addRequerente(' + v_id + ',' + listRequerente.id + ')">' + listRequerente.nome + '</a></td>' +
          '<td> <span type="button" onclick="getPessoaInfo(' + listRequerente.id + ')">' +
          '<img src="../img/info.png" width="20px"></span></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableRequerente += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tableRequerente += `</table>`;
      document.getElementById("tabmodalRequerente").innerHTML = tableRequerente;
    }
  })
}


/****************** Tema ******************* */
/******************** Devolve lista dos Temas já introduzidos *************************** */
async function listTema(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_tema_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos Temas");
    } else {
      let temas = await response.json();
      console.log(temas);
      let tableTemas = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableTemas" >Tema:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selTema(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delTema(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableTemas" class="listPopUp">';

      for (const tema of temas) {

        tableTemas += '<tr><td class="checkDel"><i class="checkDelTema far fa-circle" style="color:red;" onclick="checkDelTema(this);" value="' + tema.id + '"></i></td>' +
          '<td>' + tema.tema + '</td></tr>'


      }
      tableTemas += `</table>`;
      document.getElementById("tema").innerHTML = tableTemas;
      console.log(tableTemas)
    }
  })
}

/*********************** Devolve lista dos temas que ainda não foram escolhidos ************************************** */
async function selectTema(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_tema_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listTemas = await response.json();
      let tableTema = '<div class="scroll"><table id="tableSelTema" class="ListPopUpModal">';
      let conta = 1;
      for (const listTema of listTemas) {

        tableTema += '<tr><td><a href="javascript:addTema(' + v_id + ',' + listTema.id + ')">' + listTema.tema + '</a></td></tr>'

        /* if (conta == 10) {
            break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
          tableTema += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tableTema += `</table></div>`;
      document.getElementById("tabmodalTema").innerHTML = tableTema;
    }
  })
}



// **************************************************************************
/*********************************Local ******************************************* */
// **************************************************************************

/****************** Termo ******************* */
/******************** Devolve lista dos Termos já introduzidos *************************** */
async function listTermo(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos Termos");
    } else {
      let termos = await response.json();
      let tableTermos = '<div class="row" ><div class="column listTabNomePeq" align="left"><label for="tableTermos" >Termo:</label>' +
        '</div><div class="column listTabBotaoPeq" align="right"><a href="javascript:selTermo(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delTermo(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableTermos" class="listPopUp">';

      for (const termo of termos) {

        tableTermos += '<tr><td class="checkDel"><i class="checkDelTermo far fa-circle" style="color:red;" onclick="checkDelTermo(this);" value="' + termo.id + '"></i></td>' +
          '<td>' + termo.nome_termo + '(' + termo.capitania + ')</td></tr>'


      }
      tableTermos += `</table>`;
      document.getElementById("termo").innerHTML = tableTermos;
      console.log(tableTermos)
    }
  })
}

/*********************** Devolve lista dos termos que ainda não foram escolhidos ************************************** */
async function selectTermo(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listTermos = await response.json();
      let tableTermo = '<div class="scroll"><table id="tableSelTermo" class="ListPopUpModal">';
      let conta = 1;
      for (const listTermo of listTermos) {

        tableTermo += '<tr><td><a href="javascript:addTermo(' + v_id + ',' + listTermo.id + ')">' + listTermo.nome_termo + '(' + listTermo.capitania + ')</a></td></tr>'

        /* if (conta == 10) {
          break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
        tableTermo += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tableTermo += `</table></div>`;
      document.getElementById("tabmodalTermo").innerHTML = tableTermo;
    }
  })
}

/****************** Freguesia ******************* */
/******************** Devolve lista das Freguesia já introduzidas *************************** */
async function listFreguesia(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_freguesia_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das freguesias");
    } else {
      let freguesias = await response.json();
      let tableFreguesias = '<div class="row" ><div class="column listTabNomePeq" align="left"><label for="tableFreguesia" >Freguesia:</label>' +
        '</div><div class="column listTabBotaoPeq" align="right"><a href="javascript:selFreguesia(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delFreguesia(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableFreguesia" class="listPopUp">';

      for (const freguesia of freguesias) {

        tableFreguesias += '<tr><td class="checkDel"><i class="checkDelFreguesia far fa-circle" style="color:red;" onclick="checkDelFreguesia(this);" value="' + freguesia.id + '"></i></td>' +
          '<td>' + freguesia.nome_freguesia + '</td></tr>';


      }
      tableFreguesias += `</table>`;
      document.getElementById("freguesia").innerHTML = tableFreguesias;
    }
  })
}

/*********************** Devolve lista das freguesias que ainda não foram escolhidas ************************************** */
async function selectFreguesia(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_freguesia_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listFreguesias = await response.json();
      let tableFreguesia = '<div class="scroll"><table id="tableSelFreguesia" class="ListPopUpModal">';
      let conta = 1;
      for (const freguesia of listFreguesias) {

        tableFreguesia += '<tr><td><a href="javascript:addFreguesia(' + v_id + ',' + freguesia.id + ')">' + freguesia.nome_freguesia + '</a></td></tr>';

        /* if (conta == 10) {
          break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
        tableFreguesia += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tableFreguesia += `</table></div>`;
      document.getElementById("tabmodalFreguesia").innerHTML = tableFreguesia;
    }
  })
}

/****************** Comarca ******************* */
/******************** Devolve lista das Comarca já introduzidas *************************** */
async function listComarca(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_comarca_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das comarcas");
    } else {
      let comarcas = await response.json();
      let tableComarcas = '<div class="row" ><div class="column listTabNomePeq" align="left"><label for="tableComarca" >Comarca:</label>' +
        '</div><div class="column listTabBotaoPeq" align="right"><a href="javascript:selComarca(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delComarca(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableComarca" class="listPopUp">';

      for (const comarca of comarcas) {

        tableComarcas += '<tr><td class="checkDel"><i class="checkDelComarca far fa-circle" style="color:red;" onclick="checkDelComarca(this);" value="' + comarca.id + '"></i></td>' +
          '<td>' + comarca.nome_comarca + '</td></tr>';


      }
      tableComarcas += `</table>`;
      document.getElementById("comarca").innerHTML = tableComarcas;
    }
  })
}

/*********************** Devolve lista das comarcas que ainda não foram escolhidas ************************************** */
async function selectComarca(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_comarca_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listComarcas = await response.json();
      let tableComarca = '<div class="scroll"><table id="tableSelComarca" class="ListPopUpModal">';
      let conta = 1;
      for (const comarca of listComarcas) {

        tableComarca += '<tr><td><a href="javascript:addComarca(' + v_id + ',' + comarca.id + ')">' + comarca.nome_comarca + '</a></td></tr>';

        /* if (conta == 10) {
          break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
        tableComarca += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tableComarca += `</table></div>`;
      document.getElementById("tabmodalComarca").innerHTML = tableComarca;
    }
  })
}

/****************** Capitania ******************* */
/******************** Devolve lista das Capitania já introduzidas *************************** */
async function listCapitania(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_capitania_ultramar/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das capitanias");
    } else {
      let capitanias = await response.json();
      let tableCapitanias = '<div class="row" ><div class="column listTabNomePeq" align="left"><label for="tableCapitania" >Capitania:</label>' +
        '</div><div class="column listTabBotaoPeq" align="right"><a href="javascript:selCapitania(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delCapitania(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableCapitania" class="listPopUp">';

      for (const capitania of capitanias) {

        tableCapitanias += '<tr><td class="checkDel"><i class="checkDelCapitania far fa-circle" style="color:red;" onclick="checkDelCapitania(this);" value="' + capitania.id + '"></i></td>' +
          '<td>' + capitania.nome_capitania + '</td></tr>';


      }
      tableCapitanias += `</table>`;
      document.getElementById("capitania").innerHTML = tableCapitanias;
    }
  })
}

/*********************** Devolve lista das capitanias que ainda não foram escolhidas ************************************** */
async function selectCapitania(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_capitania_ultramar/notInUltramar/" + v_id + "/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listCapitanias = await response.json();
      let tableCapitania = '<div class="scroll"><table id="tableSelCapitania" class="ListPopUpModal">';
      let conta = 1;
      for (const capitania of listCapitanias) {

        tableCapitania += '<tr><td><a href="javascript:addCapitania(' + v_id + ',' + capitania.id + ')">' + capitania.nome_capitania + '</a></td></tr>';

        /* if (conta == 10) {
          break;
        } */
        conta++;
      }
      /* if (conta >= 10) {
        tableCapitania += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } */
      tableCapitania += `</table></div>`;
      document.getElementById("tabmodalCapitania").innerHTML = tableCapitania;
    }
  })
}

// **************************************************************************
/*********************************Documentos Relacionados ******************************************* */
// **************************************************************************


/****************** Provocação ******************* */
/******************** Devolve lista dos Porocações já introduzidas *************************** */
async function listProvocacao(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_provocacao/allUltramar/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das Provocações");
    } else {
      let provocacoes = await response.json();
      console.log(provocacoes);
      let tableProvocacao = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableProvocacao" >Provoção:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selProvocacao(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delProvocacao(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableProvocacao" class="listPopUp">';

      for (const provocacao of provocacoes) {

        tableProvocacao += '<tr><td class="checkDel"><i class="checkDelProvocacao far fa-circle" style="color:red;" onclick="checkDelProvocacao(this);" value="' + provocacao.id + '"></i></td>' +
          '<td><div class="checkId"><b>Nº ' + provocacao.id + '</b></div></td><td>' + provocacao.resumo + ' (' + provocacao.ano + ')</td></tr>'


      }
      tableProvocacao += `</table>`;
      document.getElementById("provocacao").innerHTML = tableProvocacao;
      console.log(tableProvocacao)
    }
  })
}

/*********************** Devolve lista das provocações que ainda não foram escolhidas ************************************** */
async function selectProvocacao(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == 'all' &&
    document.getElementById("capitaniaFiltroValue").value == '' &&
    document.getElementById("termoFiltroValue").value == '' &&
    document.getElementById("freguesiaFiltroValue").value == '' &&
    document.getElementById("comarcaFiltroValue").value == '') {

    let tableProvocacao = '<table id="tableSelProvocacao" class="ListPopUpModal">';
    tableProvocacao += `</table>`;
    document.getElementById("tabmodalProvocacao").innerHTML = tableProvocacao;
  } else {
    var objResp = {
      capitaniaSearch: document.getElementById("capitaniaFiltroValue").value,
      termoSearch: document.getElementById("termoFiltroValue").value,
      freguesiaSearch: document.getElementById("freguesiaFiltroValue").value,
      comarcaSearch: document.getElementById("comarcaFiltroValue").value,
      dataDe: document.getElementById("filtroDe").value,
      dataAte: document.getElementById("filtroA").value
    };
    var json = JSON.stringify(objResp);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_provocacao/notInUltramar/" + v_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "POST", headers: myHeaders, body: json }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
        let listProvocacaos = await response.json();
        let tableProvocacao = '<table id="tableSelProvocacao" class="ListPopUpModal">';
        let conta = 1;
        for (const listProvocacao of listProvocacaos) {

          tableProvocacao += '<tr><td><a href="javascript:addProvocacao(' + v_id + ',' + listProvocacao.id + ')"><b>Nº:' + listProvocacao.id + '</b>-' + listProvocacao.resumo + ' (' + listProvocacao.ano + ')</a></td>' +
            '<td> <span type="button" onclick="getProvocacaoInfo(' + listProvocacao.id + ')">' +
            '<img src="../img/info.png" width="20px"></span></td></tr>'

          if (conta == 10) {
            break;
          }
          conta++;
        }
        if (conta >= 10) {
          tableProvocacao += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        }
        tableProvocacao += `</table>`;
        document.getElementById("tabmodalProvocacao").innerHTML = tableProvocacao;
      }
    })
  }
}

/************************ devolve o info da Provocação ****************************** */
async function getProvocacaoInfo(provId) {

  $('#modalProvocacao').modal('hide');
  $('#infoProv').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/provocacao/recordInfo/" + provId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a provocação");
    } else {
      provocacao = await response.json();
      document.getElementById("provObsInfo").innerHTML = provocacao.resumo;
      document.getElementById("anoProv").innerHTML = provocacao.ano;
      document.getElementById("destAut").innerHTML = provocacao.destinatario_autoridade_tratamento;
      document.getElementById("datProv").innerHTML = provocacao.data;
      document.getElementById("provRefDoc").innerHTML = provocacao.referencia_documentalDesc;
      document.getElementById("provRegInfo").innerHTML = provocacao.registroDesc;
      document.getElementById("folioPaginaInfoProv").innerHTML = provocacao.folio_pagina;
      document.getElementById("termoPesProv").innerHTML = provocacao.termo;
      document.getElementById("fregPesProv").innerHTML = provocacao.freguesia;
      document.getElementById("comaPesProv").innerHTML = provocacao.comarca;
      document.getElementById("capiPesProv").innerHTML = provocacao.capitania;
      sessionStorage.setItem("v_idProv", provId);

    }
  })
}

function cancelInfoProvoca() {
  $('#infoProv').modal('hide');
  $('#modalProvocacao').modal('show');

}

function goToProv(v_id) {
  window.location = "EditProvocacao.html";

}

/****************** Mandado ******************* */
/******************** Devolve lista dos Mandados já introduzidos *************************** */
async function listMandado(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_mandado_ultramar/allMandado/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos Mandados");
    } else {
      let mandados = await response.json();
      console.log(mandados);
      let tableMandado = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableMandado" >Mandado:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selMandado(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delMandado(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableMandado" class="listPopUp">';
      console.log('Port: ' + urlBase);
      for (const mandado of mandados) {

        tableMandado += '<tr><td class="checkDel"><i class="checkDelMandado far fa-circle" style="color:red;" onclick="checkDelMandado(this);" value="' + mandado.id + '"></i></td>' +
          '<td><div class="checkId"><b>Nº ' + mandado.id + '</b></div></td>' +
          '<td>' + mandado.mandado + ' por: ' + mandado.nome_quem_envia +
          ' (' + mandado.ano + ')</td></tr>'


      }
      tableMandado += `</table>`;
      document.getElementById("mandado").innerHTML = tableMandado;
      console.log(tableMandado)
    }
  })
}

/*********************** Devolve lista dos mandados que ainda não foram escolhidos ************************************** */
async function selectMandado(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == 'all' &&
    document.getElementById("capitaniaFiltroValueMan").value == '' &&
    document.getElementById("termoFiltroValueMan").value == '' &&
    document.getElementById("freguesiaFiltroValueMan").value == '' &&
    document.getElementById("comarcaFiltroValueMan").value == '') {

    let tableProvocacao = '<table id="tableSelMandado" class="ListPopUpModal">';
    tableProvocacao += `</table>`;
    document.getElementById("tabmodalMandado").innerHTML = tableProvocacao;
  } else {
    var objResp = {
      capitaniaSearch: document.getElementById("capitaniaFiltroValueMan").value,
      termoSearch: document.getElementById("termoFiltroValueMan").value,
      freguesiaSearch: document.getElementById("freguesiaFiltroValueMan").value,
      comarcaSearch: document.getElementById("comarcaFiltroValueMan").value,
      dataDe: document.getElementById("filtroDeMan").value,
      dataAte: document.getElementById("filtroAMan").value
    };
    var json = JSON.stringify(objResp);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_mandado_ultramar/notInMandado/" + v_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "POST", headers: myHeaders, body: json }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
        let listMandados = await response.json();
        let tableMandado = '<table id="tableSelMandado" class="ListPopUpModal">';
        let conta = 1;
        for (const listMandado of listMandados) {

          tableMandado += '<tr><td><a href="javascript:addMandado(' + v_id + ',' + listMandado.id + ')"><b>Nº:' + listMandado.id + '</b>-' + listMandado.resumo + ' (' + listMandado.ano + ')</a></td>' +
            '<td> <span type="button" onclick="getMandadoInfo(' + listMandado.id + ')">' +
            '<img src="../img/info.png" width="20px"></span></td></tr>'

          if (conta == 10) {
            break;
          }
          conta++;
        }
        if (conta >= 10) {
          tableMandado += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        }
        tableMandado += `</table>`;
        document.getElementById("tabmodalMandado").innerHTML = tableMandado;
      }
    })
  }
}

/************************ devolve o info do Mandado pedido ****************************** */
async function getMandadoInfo(manId) {

  $('#modalMandado').modal('hide');
  $('#infoMan').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/mandado/recordInfo/" + manId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver o mandado");
    } else {
      mandado = await response.json();
      document.getElementById("manObs").innerHTML = mandado.resumo;
      document.getElementById("anoMando").innerHTML = mandado.ano;
      document.getElementById("destQuemEnv").innerHTML = mandado.nome_quem_envia;
      document.getElementById("destMan").innerHTML = mandado.mandado;
      document.getElementById("dataMando").innerHTML = mandado.data;
      document.getElementById("manRefDoc").innerHTML = mandado.referencia_documentalDesc;
      document.getElementById("manRegInfo").innerHTML = mandado.registroDesc;
      document.getElementById("folioPaginaInfoMan").innerHTML = mandado.folio_pagina;
      document.getElementById("termoPesMan").innerHTML = mandado.termo;
      document.getElementById("fregPesMan").innerHTML = mandado.freguesia;
      document.getElementById("comaPesMan").innerHTML = mandado.comarca;
      document.getElementById("capiPesMan").innerHTML = mandado.capitania;
      sessionStorage.setItem("v_idUlt", manId);
      //    getAllCategoria(consulta.tipologia_diplomatica);

    }
  })
}

function cancelInfoMandado() {
  $('#infoMan').modal('hide');
  $('#modalMandado').modal('show');

}

function goToMan(v_id) {
  window.location = "EditMandado.html";

}


/****************** Consulta ******************* */
/******************** Devolve lista dos Consultas já introduzidas *************************** */
async function listConsulta(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_consulta/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das Consultas");
    } else {
      let consultas = await response.json();
      let tableConsulta = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableConsulta" >Consulta:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selConsulta(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delConsulta(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableConsulta" class="listPopUp">';

      for (const consulta of consultas) {

        tableConsulta += '<tr><td class="checkDel"><i class="checkDelConsulta far fa-circle" style="color:red;" onclick="checkDelConsulta(this);" value="' + consulta.id + '"></i></td>' +
          '<td><div class="checkId"><b>Nº ' + consulta.id + '</b></div></td>' +
          '<td>' + consulta.resumo + ' (' + consulta.ano_consulta + ')</td></tr>'


      }
      tableConsulta += `</table>`;
      document.getElementById("consulta").innerHTML = tableConsulta;
    }
  })
}

/*********************** Devolve lista das consultas que ainda não foram escolhidas ************************************** */
async function selectConsulta(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == 'all' &&
    document.getElementById("capitaniaFiltroValueCon").value == '' &&
    document.getElementById("termoFiltroValueCon").value == '' &&
    document.getElementById("freguesiaFiltroValueCon").value == '' &&
    document.getElementById("comarcaFiltroValueCon").value == '') {

    let tableProvocacao = '<table id="tableSelConsulta" class="ListPopUpModal">';
    tableProvocacao += `</table>`;
    document.getElementById("tabmodalConsulta").innerHTML = tableProvocacao;
  } else {
    var objResp = {
      capitaniaSearch: document.getElementById("capitaniaFiltroValueCon").value,
      termoSearch: document.getElementById("termoFiltroValueCon").value,
      freguesiaSearch: document.getElementById("freguesiaFiltroValueCon").value,
      comarcaSearch: document.getElementById("comarcaFiltroValueCon").value,
      dataDe: document.getElementById("filtroDeCon").value,
      dataAte: document.getElementById("filtroACon").value
    };
    var json = JSON.stringify(objResp);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_consulta/notInUltramar/" + v_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "POST", headers: myHeaders, body: json }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
        let listConsultas = await response.json();
        let tableConsulta = '<table id="tableSelConsulta" class="ListPopUpModal">';
        let conta = 1;
        for (const listConsulta of listConsultas) {

          tableConsulta += '<tr><td><a href="javascript:addConsulta(' + v_id + ',' + listConsulta.id + ')"><b>Nº:' + listConsulta.id + '</b>-' + listConsulta.resumo + ' (' + listConsulta.ano_consulta + ')</a></td>' +
            '<td> <span type="button" onclick="getConsultaInfo(' + listConsulta.id + ')">' +
            '<img src="../img/info.png" width="20px"></span></td></tr>'

          if (conta == 10) {
            break;
          }
          conta++;
        }
        if (conta >= 10) {
          tableConsulta += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        }
        tableConsulta += `</table>`;
        document.getElementById("tabmodalConsulta").innerHTML = tableConsulta;
      }
    })
  }
}

/************************ devolve o info da Consulta ****************************** */
async function getConsultaInfo(consId) {

  $('#modalConsulta').modal('hide');
  $('#infoCons').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/consulta/recordInfo/" + consId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a consulta");
    } else {
      consulta = await response.json();
      document.getElementById("consObsInfo").innerHTML = consulta.resumo;
      document.getElementById("anoCons").innerHTML = consulta.ano_consulta;
      document.getElementById("parReal").innerHTML = consulta.parecer_regio;
      document.getElementById("sumula").innerHTML = consulta.sumula;
      document.getElementById("datCons").innerHTML = consulta.data_consulta;
      document.getElementById("datParReal").innerHTML = consulta.data_parecer_regio;
      document.getElementById("consRefDoc").innerHTML = consulta.referencia_documentalDesc;
      document.getElementById("consRegInfo").innerHTML = consulta.registroDesc;
      document.getElementById("folioPaginaInfoCon").innerHTML = consulta.folio_pagina;
      document.getElementById("termoPesCon").innerHTML = consulta.termo;
      document.getElementById("fregPesCon").innerHTML = consulta.freguesia;
      document.getElementById("comaPesCon").innerHTML = consulta.comarca;
      document.getElementById("capiPesCon").innerHTML = consulta.capitania;
      sessionStorage.setItem("v_idCons", consId);
      //    getAllCategoria(consulta.tipologia_diplomatica);

    }
  })
}

function cancelInfoConculta() {
  $('#infoCons').modal('hide');
  $('#modalConsulta').modal('show');

}

function goToCons(v_id) {
  window.location = "EditConsulta.html";

}



/****************** Ultramar ******************* */
/******************** Devolve lista dos Ultramres já introduzidos *************************** */
async function listUltramar(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_ultramar/allUltramarRecente/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista do Ultramar");
    } else {
      let ultramares = await response.json();
      console.log(ultramares);
      let tableUltramar = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableUltramar" >Ultramar:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selUltramar(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delUltramar(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableUltramar" class="listPopUp">';

      for (const ultramar of ultramares) {

        tableUltramar += '<tr><td class="checkDel"><i class="checkDelUltramar far fa-circle" style="color:red;" onclick="checkDelUltramar(this);" value="' + ultramar.id + '"></i></td>' +
          '<td><div class="checkId"><b>Nº ' + ultramar.id + '</b></div></td>' +
          '<td>' + ultramar.resumo + ' (' + ultramar.ano + ')</td></tr>'


      }
      tableUltramar += `</table>`;
      document.getElementById("ultramar").innerHTML = tableUltramar;
      console.log(tableUltramar)
    }
  })
}

/*********************** Devolve lista dos Ultramares que ainda não foram escolhidos ************************************** */
async function selectUltramar(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == 'all' &&
    document.getElementById("capitaniaFiltroValueUltra").value == '' &&
    document.getElementById("termoFiltroValueUltra").value == '' &&
    document.getElementById("freguesiaFiltroValueUltra").value == '' &&
    document.getElementById("comarcaFiltroValueUltra").value == '') {

    let tableProvocacao = '<table id="tableSelUltramar" class="ListPopUpModal">';
    tableProvocacao += `</table>`;
    document.getElementById("tabmodalUltramar").innerHTML = tableProvocacao;
  } else {
    var objResp = {
      capitaniaSearch: document.getElementById("capitaniaFiltroValueUltra").value,
      termoSearch: document.getElementById("termoFiltroValueUltra").value,
      freguesiaSearch: document.getElementById("freguesiaFiltroValueUltra").value,
      comarcaSearch: document.getElementById("comarcaFiltroValueUltra").value,
      dataDe: document.getElementById("filtroDeUltra").value,
      dataAte: document.getElementById("filtroAUltra").value
    };
    var json = JSON.stringify(objResp);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_ultramar/notInUltramarNovo/" + v_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "POST", headers: myHeaders, body: json }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
        let listUltramares = await response.json();
        let tableUltramar = '<table id="tableSelUltramar" class="ListPopUpModal">';
        let conta = 1;
        for (const listUltramar of listUltramares) {

          tableUltramar += '<tr><td><a href="javascript:addUltramarRel(' + v_id + ',' + listUltramar.id + ')"><b>Nº:' + listUltramar.id + '</b>-' + listUltramar.resumo + ' (' + listUltramar.ano + ')</a></td>' +
            '<td> <span type="button" onclick="getUltramarInfo(' + listUltramar.id + ')">' +
            '<img src="../img/info.png" width="20px"></span></td></tr>'

          if (conta == 10) {
            break;
          }
          conta++;
        }
        if (conta >= 10) {
          tableUltramar += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        }
        tableUltramar += `</table>`;
        document.getElementById("tabmodalUltramar").innerHTML = tableUltramar;
      }
    })
  }
}

/************************ devolve o info do Ultramar ****************************** */
async function getUltramarInfo(UltId) {

  $('#modalUltramar').modal('hide');
  $('#infoUltra').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/recordInfo/" + UltId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver o ultramar");
    } else {
      ultramar = await response.json();
      document.getElementById("ultObsInfo").innerHTML = ultramar.resumo;
      document.getElementById("anoUltInfo").innerHTML = ultramar.ano;
      document.getElementById("autUltInfo").innerHTML = ultramar.autoridade;
      document.getElementById("datUltInfo").innerHTML = ultramar.data;
      document.getElementById("ultRefDoc").innerHTML = ultramar.referencia_documentalDesc;
      document.getElementById("ultRegInfo").innerHTML = ultramar.registroDesc;
      document.getElementById("folioPaginaInfoUltra").innerHTML = ultramar.folio_pagina;
      document.getElementById("termoPesUltra").innerHTML = ultramar.termo;
      document.getElementById("fregPesUltra").innerHTML = ultramar.freguesia;
      document.getElementById("comaPesUltra").innerHTML = ultramar.comarca;
      document.getElementById("capiPesUltra").innerHTML = ultramar.capitania;
      sessionStorage.setItem("v_idUlt", UltId);

    }
  })
}

function cancelInfoUltramar() {
  $('#infoUltra').modal('hide');
  $('#modalUltramar').modal('show');

}

function goToUlt(v_id) {
  window.location = "EditUltramar.html";

}


/****************** Respostas ******************* */
async function listResposta(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_resposta/allUltramar/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das Respostas");
    } else {
      let Respostas = await response.json();
      let tableResposta = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableResposta" >Resposta:</label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selResposta(' + v_id + ')"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delResposta(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableResposta" class="listPopUp">';

      for (const resposta of Respostas) {

        tableResposta += '<tr><td class="checkDel"><i class="checkDelResposta far fa-circle" style="color:red;" onclick="checkDelResposta(this);" value="' + resposta.id + '"></i></td>' +
          '<td><div class="checkId"><b>Nº ' + resposta.id + '</b></div></td>' +
          '<td>' + resposta.resumo + ' (' + resposta.ano_resposta + ')</td></tr>'


      }
      tableResposta += `</table>`;
      document.getElementById("resposta").innerHTML = tableResposta;
    }
  })
}

/*********************** Devolve lista das Respostas que ainda não foram escolhidas ************************************** */
async function selectResposta(v_id, v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == 'all' &&
    document.getElementById("capitaniaFiltroValueRes").value == '' &&
    document.getElementById("termoFiltroValueRes").value == '' &&
    document.getElementById("freguesiaFiltroValueRes").value == '' &&
    document.getElementById("comarcaFiltroValueRes").value == '') {

    let tableProvocacao = '<table id="tableSelResposta" class="ListPopUpModal">';
    tableProvocacao += `</table>`;
    document.getElementById("tabmodalResposta").innerHTML = tableProvocacao;
  } else {
    var objResp = {
      capitaniaSearch: document.getElementById("capitaniaFiltroValueRes").value,
      termoSearch: document.getElementById("termoFiltroValueRes").value,
      freguesiaSearch: document.getElementById("freguesiaFiltroValueRes").value,
      comarcaSearch: document.getElementById("comarcaFiltroValueRes").value,
      dataDe: document.getElementById("filtroDeRes").value,
      dataAte: document.getElementById("filtroARes").value
    };
    var json = JSON.stringify(objResp);
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_resposta/notInUltramar/" + v_id + "/" + v_nomePesq;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "POST", headers: myHeaders, body: json }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
      if (!response.ok) {
      } else {
        let listRespostas = await response.json();
        let tableResposta = '<table id="tableSelResposta" class="ListPopUpModal">';
        let conta = 1;
        for (const listResposta of listRespostas) {

          tableResposta += '<tr><td><a href="javascript:addResposta(' + v_id + ',' + listResposta.id + ')"><b>Nº:' + listResposta.id + '</b>-' + listResposta.resumo + ' (' + listResposta.ano_resposta + ')</a></td>' +
            '<td> <span type="button" onclick="getRespostaInfo(' + listResposta.id + ')">' +
            '<img src="../img/info.png" width="20px"></span></td></tr>'

          if (conta == 10) {
            break;
          }
          conta++;
        }
        if (conta >= 10) {
          tableResposta += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
        }
        tableResposta += `</table>`;
        document.getElementById("tabmodalResposta").innerHTML = tableResposta;
      }
    })
  }
}

/************************ devolve o info da Resposta ****************************** */
async function getRespostaInfo(ResId) {

  $('#modalResposta').modal('hide');
  $('#infoRes').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/resposta/recordInfo/" + ResId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a resposta");
    } else {
      resposta = await response.json();
      console.log(resposta);
      document.getElementById("resObsInfo").innerHTML = resposta.resumo;
      document.getElementById("anoResInfo").innerHTML = resposta.ano_resposta;
      document.getElementById("novaOrdInfo").innerHTML = resposta.nova_ordem_nao_cumprimento;
      document.getElementById("tipoDiploInfo").innerHTML = resposta.tipologia_diplomaticaDesc;
      document.getElementById("datResInfo").innerHTML = resposta.data_resposta;
      document.getElementById("impressosInfo").innerHTML = resposta.impressos;
      document.getElementById("resRefDocInfo").innerHTML = resposta.referencia_documentalDesc;
      document.getElementById("resRegInfo").innerHTML = resposta.registroDesc;
      document.getElementById("folioPaginaInfoRes").innerHTML = resposta.folio_pagina;
      document.getElementById("termoPesRes").innerHTML = resposta.termo;
      document.getElementById("fregPesRes").innerHTML = resposta.freguesia;
      document.getElementById("comaPesRes").innerHTML = resposta.comarca;
      document.getElementById("capiPesRes").innerHTML = resposta.capitania;
      sessionStorage.setItem("v_idResInfo", ResId);
    }
  })
}

function cancelInfoResposta() {
  $('#infoRes').modal('hide');
  $('#modalResposta').modal('show');

}

function goToRes(v_id) {
  sessionStorage.setItem("v_idRes", sessionStorage.getItem("v_idResInfo"));
  window.location = "EditResposta.html";

}

/*************************** Fim Docomentos Relacionados****************************************** */

/************************ devolve o info da Pessoa ****************************** */
async function getPessoaInfo(pesId) {
  let v_tipoAdd = sessionStorage.getItem("v_AddPes");
  if (v_tipoAdd == 'requerente') {
    $('#modalRequerente').modal('hide');
  } else {
    if (v_tipoAdd == 'pesCitada') {
      $('#modalPesCita').modal('hide');
    }
  }
  $('#infoPes').modal('show');

  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/pessoa/record/" + pesId;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver o Pessoa");
    } else {
      pessoa = await response.json();
      document.getElementById("nomePes").innerHTML = pessoa.nome;
      document.getElementById("idadePes").innerHTML = pessoa.idade;
      document.getElementById("sexoPes").innerHTML = pessoa.sexo;
      document.getElementById("filPes").innerHTML = pessoa.filiacao;
      document.getElementById("termoPes").innerHTML = pessoa.termoDesc;
      document.getElementById("fregPes").innerHTML = pessoa.freguesiaDesc;
      document.getElementById("comaPes").innerHTML = pessoa.comarcaDesc;
      document.getElementById("capiPes").innerHTML = pessoa.capitaniaDesc;
      listInfoOfiTitulo(pesId);
      listInfoSocJuridico(pesId);
      listInfoMarEco(pesId);
    }
  })
}



function limpaTermo() {
  document.getElementById("termoPesCriar").value = "";
  document.getElementById("termoPesAddValue").value = "";
}

function limpaFreg() {
  document.getElementById("fregPesCriar").value = "";
  document.getElementById("fregPesAddValue").value = "";
}

function limpaComa() {
  document.getElementById("comaPesCriar").value = "";
  document.getElementById("comaPesAddValue").value = "";
}

function limpaCapi() {
  document.getElementById("capiPesCriar").value = "";
  document.getElementById("capiPesAddValue").value = "";
}

// *******************************************************************************


/********************* Local Pessoa ***************************** */
/********************* Lista do Termo  ***************************** */
async function selectTermoPes(v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/search/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let Termos = await response.json();
      let tableTermos = '<table id="table2Termo" class="ListPopUpModal">';
      let conta = 1;
      for (const termo of Termos) {

        tableTermos += '<tr><td><a href="javascript:addTermoPes(' + termo.id + ',`' + termo.nome_termo + '`)">' + termo.nome_termo + '</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableTermos += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tableTermos += `</table>`;

      document.getElementById("tabmodalTermoPes").innerHTML = tableTermos;
    }
  })
}

function addTermoPes(v_id, v_desc) {
  document.getElementById("termoPesAddValue").value = v_id;
  document.getElementById("termoPesCriar").value = v_desc;
  $('#modalTermoPes').modal('hide');
  $('#criarPes').modal('show');
}

function cancelarTermoPes() {
  $('#modalTermoPes').modal('hide');
  $('#criarPes').modal('show');
}

function openTermoPes() {
  $('#criarPes').modal('hide');
  $('#modalTermoPes').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectFreguesiaPes(v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/search/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let Freguesias = await response.json();
      let tableFreguesias = '<table id="tableFreg" class="ListPopUpModal">';
      let conta = 1;
      for (const freguesia of Freguesias) {

        tableFreguesias += '<tr><td><a href="javascript:addFregPes(' + freguesia.id + ',`' + freguesia.nome_freguesia + '`)">' + freguesia.nome_freguesia + '</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableFreguesias += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tableFreguesias += `</table>`;

      document.getElementById("tabmodalFreguesiaPes").innerHTML = tableFreguesias;
    }
  })
}

function addFregPes(v_id, v_desc) {
  document.getElementById("fregPesAddValue").value = v_id;
  document.getElementById("fregPesCriar").value = v_desc;
  $('#modalFregPes').modal('hide');
  $('#criarPes').modal('show');
}

function cancelarFregPes() {
  $('#modalFregPes').modal('hide');
  $('#criarPes').modal('show');
}

function openFregPes() {
  $('#criarPes').modal('hide');
  $('#modalFregPes').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectComarcaPes(v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let Comarcas = await response.json();
      let tableComarcas = '<table id="tableComa" class="ListPopUpModal">';
      let conta = 1;
      for (const comarca of Comarcas) {

        tableComarcas += '<tr><td><a href="javascript:addComaPes(' + comarca.id + ',`' + comarca.nome_comarca + '`)">' + comarca.nome_comarca + '</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableComarcas += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tableComarcas += `</table>`;

      document.getElementById("tabmodalComarcaPes").innerHTML = tableComarcas;
    }
  })
}

function addComaPes(v_id, v_desc) {
  document.getElementById("comaPesAddValue").value = v_id;
  document.getElementById("comaPesCriar").value = v_desc;
  $('#modalComaPes').modal('hide');
  $('#criarPes').modal('show');
}

function cancelarComaPes() {
  $('#modalComaPes').modal('hide');
  $('#criarPes').modal('show');
}

function openComaPes() {
  $('#criarPes').modal('hide');
  $('#modalComaPes').modal('show');
}

/********************* Lista da Freguesia  ***************************** */
async function selectCapitaniaPes(v_nomePesq) {
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/capitania/search/" + v_nomePesq;
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let Capitanias = await response.json();
      let tableCapitanias = '<table id="tableCapi" class="ListPopUpModal">';
      let conta = 1;
      for (const capitania of Capitanias) {

        tableCapitanias += '<tr><td><a href="javascript:addCapiPes(' + capitania.id + ',`' + capitania.nome_capitania + '`)">' + capitania.nome_capitania + '</a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableCapitanias += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      }
      tableCapitanias += `</table>`;

      document.getElementById("tabmodalCapitaniaPes").innerHTML = tableCapitanias;
    }
  })
}

function addCapiPes(v_id, v_desc) {
  document.getElementById("capiPesAddValue").value = v_id;
  document.getElementById("capiPesCriar").value = v_desc;
  $('#modalCapiPes').modal('hide');
  $('#criarPes').modal('show');
}

function cancelarCapiPes() {
  $('#modalCapiPes').modal('hide');
  $('#criarPes').modal('show');
}

function openCapiPes() {
  $('#criarPes').modal('hide');
  $('#modalCapiPes').modal('show');
}

function criarPessoa(v_id) {
  let v_tipoAdd = sessionStorage.getItem("v_AddPes");
  if (v_tipoAdd == 'requerente') {
    $('#modalRequerente').modal('hide');
  } else {
    if (v_tipoAdd == 'pesCitada') {
      $('#modalPesCita').modal('hide');
    }
  }
  listSocJuridico(0);
  listMarEco(0);
  listOfiTitulo(0);

  selectTermoPes();
  document.getElementById("termoPesCriar").value = "";
  document.getElementById("termoPesAddValue").value = "";
  selectFreguesiaPes();
  document.getElementById("fregPesCriar").value = "";
  document.getElementById("fregPesAddValue").value = "";
  selectComarcaPes();
  document.getElementById("comaPesCriar").value = "";
  document.getElementById("comaPesAddValue").value = "";
  selectCapitaniaPes();
  document.getElementById("capiPesCriar").value = "";
  document.getElementById("capiPesAddValue").value = "";

  $('#criarPes').modal('show');

}

function cancelarCriaPessoa() {
  let v_tipoAdd = sessionStorage.getItem("v_AddPes");
  if (v_tipoAdd == 'requerente') {
    $('#modalRequerente').modal('show');
  } else {
    if (v_tipoAdd == 'pesCitada') {
      $('#modalPesCita').modal('show');
    }
  }

  $('#criarPes').modal('hide');

}

function cancelarInfoPessoa() {
  let v_tipoAdd = sessionStorage.getItem("v_AddPes");
  if (v_tipoAdd == 'requerente') {
    $('#modalRequerente').modal('show');
  } else {
    if (v_tipoAdd == 'pesCitada') {
      $('#modalPesCita').modal('show');
    }
  }

  $('#infoPes').modal('hide');

}


/****************** lista todos Socio Juridico  ******************* */
async function listSocJuridico(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/allPessoa/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das SocJuridico");
    } else {
      let SocJuridicos = await response.json();
      let tableSocJuridico = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableSocJuridico" ><b>Sócio Juridico:</b></label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selectSocJuridico(`all`)"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delSocJuridico(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableSocJuridicoSel" class="listPopUp">';

      for (const SocJuridico of SocJuridicos) {

        tableSocJuridico += '<tr><td class="checkDel"><i class="checkDelSocJuridico far fa-circle" style="color:red;" onclick="checkDelSocJuridico(this);" value="' + SocJuridico.id + '"></i></td>' + '<td><div class="checkId"><b>Nº ' + SocJuridico.id + '</b></div></td>' +
          '<td>' + SocJuridico.marcador_status_juridico + '</td></tr>'


      }
      tableSocJuridico += `</table>`;
      document.getElementById("addSocJuridico").innerHTML = tableSocJuridico;
    }
  })
}


/****************** lista todos Socio Juridico info Pessoa  ******************* */
async function listInfoSocJuridico(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/allPessoa/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das SocJuridico");
    } else {
      let SocJuridicos = await response.json();
      let tableSocJuridico = '<table id="tableSocJuridicoInfo" class="listPopUp">';

      for (const SocJuridico of SocJuridicos) {

        tableSocJuridico += '<tr><td>' + SocJuridico.marcador_status_juridico + '</td></tr>'


      }
      tableSocJuridico += `</table>`;
      document.getElementById("infoSocJuridico").innerHTML = tableSocJuridico;
    }
  })
}

function addSocJuridico(v_id, v_desc) {
  var table = document.getElementById("tableSocJuridicoSel");
  var row = table.insertRow();
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  cell1.innerHTML = '<i class="checkDelSocJuridico far fa-circle" style="color:red;" onclick="checkDelSocJuridico(this);" value="' + v_id + '"></i>';
  cell2.innerHTML = v_desc;
  $('#modalSocJuridico').modal('hide');
  $('#criarPes').modal('show');
}


function cancelarSocJuridico() {
  $('#modalSocJuridico').modal('hide');
  $('#criarPes').modal('show');
}

/******************** Selecionar e descelecionar ************************** */
function checkDelSocJuridico(v_obj) {
  if (v_obj.className == 'checkDelSocJuridico far fa-circle') {
    v_obj.className = 'checkDelSocJuridico fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelSocJuridico far fa-circle';
  }
}

function delSocJuridico(v_id) {
  let elements = document.getElementsByClassName('checkDelSocJuridico');
  let v_linha = 0;
  for (let element of elements) {
    if (element.attributes.class.value == 'checkDelSocJuridico fa fa-check-circle') {
      document.getElementById("tableSocJuridicoSel").deleteRow(v_linha);
    }
    v_linha += 1;
  }
}

/*********************** Devolve lista Socio Juridico ************************************** */
async function selectSocJuridico(v_nomePesq) {

  $('#criarPes').modal('hide');
  $('#modalSocJuridico').modal('show');
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_status/search/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listSocJuridicos = await response.json();
      let tableSocJuridico = '<div class="scroll"><table id="tableSelSocJuridico" class="ListPopUpModal">';

      for (const listSocJuridico of listSocJuridicos) {

        tableSocJuridico += '<tr><td><a href="javascript:addSocJuridico(' + listSocJuridico.id + ',`' + listSocJuridico.marcador_status_juridico + '`)">' + listSocJuridico.marcador_status_juridico + ' </a></td>' +
          '</tr>';
      }
      tableSocJuridico += `</table></div>`;
      document.getElementById("tabmodalSocJuridico").innerHTML = tableSocJuridico;
    }
  })
}

/****************** Marcador Económico ******************* */

/****************** lista todos Marcador Económico  ******************* */
async function listMarEco(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/allPessoa/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos marcadores económicos");
    } else {
      let MarEcos = await response.json();
      let tableMarEco = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableMarEco" ><b>Marcador economico (Ocupação):</b></label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selectMarEco(`all`)"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delMarEco(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableMarEcoSel" class="listPopUp">';

      for (const MarEco of MarEcos) {

        tableMarEco += '<tr><td class="checkDel"><i class="checkDelMarEco far fa-circle" style="color:red;" onclick="checkDelMarEco(this);" value="' + MarEco.id + '"></i></td>' + '<td><div class="checkId"><b>Nº ' + MarEco.id + '</b></div></td>' +
          '<td>' + MarEco.designacao + '</td></tr>'


      }
      tableMarEco += `</table>`;
      document.getElementById("addMarEco").innerHTML = tableMarEco;
    }
  })
}


/****************** lista todos Marcador Económico info Pessoa  ******************* */
async function listInfoMarEco(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/allPessoa/" + v_id;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos marcadores económicos");
    } else {
      let MarEcos = await response.json();
      let tableMarEco = '<table id="tableMarEcoInfo" class="listPopUp">';

      for (const MarEco of MarEcos) {
        tableMarEco += '<tr><td>' + MarEco.designacao + '</td></tr>'


      }
      tableMarEco += `</table>`;
      document.getElementById("infoMarEco").innerHTML = tableMarEco;
    }
  })
}

function addMarEco(v_id, v_desc) {
  var table = document.getElementById("tableMarEcoSel");
  var row = table.insertRow();
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  cell1.innerHTML = '<i class="checkDelMarEco far fa-circle" style="color:red;" onclick="checkDelMarEco(this);" value="' + v_id + '"></i>';
  cell2.innerHTML = v_desc;
  $('#modalMarEco').modal('hide');
  $('#criarPes').modal('show');
}

function cancelarMarEco() {
  $('#modalMarEco').modal('hide');
  $('#criarPes').modal('show');
}

/******************** Selecionar e descelecionar ************************** */
function checkDelMarEco(v_obj) {
  if (v_obj.className == 'checkDelMarEco far fa-circle') {
    v_obj.className = 'checkDelMarEco fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelMarEco far fa-circle';
  }
}

function delMarEco(v_id) {
  let elements = document.getElementsByClassName('checkDelMarEco');
  let v_linha = 0;
  for (let element of elements) {
    if (element.attributes.class.value == 'checkDelMarEco fa fa-check-circle') {
      document.getElementById("tableMarEcoSel").deleteRow(v_linha);
    }
    v_linha += 1;
  }
}

/*********************** Devolve lista Marcador Económico ************************************** */
async function selectMarEco(v_nomePesq) {

  $('#criarPes').modal('hide');
  $('#modalMarEco').modal('show');
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/marcador_economico/search/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let listMarEcos = await response.json();
      let tableMarEco = '<div class="scroll"><table id="tableSelMarEco" class="ListPopUpModal">';

      for (const listMarEco of listMarEcos) {

        tableMarEco += '<tr><td><a href="javascript:addMarEco(' + listMarEco.id + ',`' + listMarEco.designacao + '`)">' + listMarEco.designacao + ' </a></td>' +
          '</tr>';
      }

      tableMarEco += `</table></div>`;
      document.getElementById("tabmodalMarEco").innerHTML = tableMarEco;
    }
  })
}


/****************** Ofício Titulo ******************* */

/****************** lista todos Ofício Titulo  ******************* */
async function listOfiTitulo(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/allPessoa/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos marcadores económicos");
    } else {
      let OfiTitulos = await response.json();
      let tableOfiTitulo = '<div class="row" ><div class="column listTabNome" align="left"><label for="tableOfiTitulo" ><b>Ofício/Título:</b></label>' +
        '</div><div class="column listTabBotao" align="right"><a href="javascript:selectOfiTitulo(`all`)"><img src="../img/addLine.png" width="35"></a> ' +
        '<a href="javascript:delOfiTitulo(' + v_id + ')"><img src="../img/delList.png" width="35"></a></div></div>' +
        '<table id="tableOfiTituloSel" class="listPopUp">';

      for (const OfiTitulo of OfiTitulos) {

        tableOfiTitulo += '<tr><td class="checkDel"><i class="checkDelOfiTitulo far fa-circle" style="color:red;" onclick="checkDelOfiTitulo(this);" value="' + OfiTitulo.id + '"></i></td>' + '<td><div class="checkId"><b>Nº ' + OfiTitulo.id + '</b></div></td>' +
          '<td>' + OfiTitulo.designacao + '</td></tr>'


      }
      tableOfiTitulo += `</table>`;
      document.getElementById("addOfiTitulo").innerHTML = tableOfiTitulo;
    }
  })
}


/****************** lista todos oficios titulos info Pessoa  ******************* */
async function listInfoOfiTitulo(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_oficio_requerente/allPessoa/" + v_id + "/all";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos marcadores económicos");
    } else {
      let OfiTitulos = await response.json();
      let tableOfiTitulo = '<table id="tableOfiTituloInfo" class="listPopUp">';

      for (const OfiTitulo of OfiTitulos) {
        tableOfiTitulo += '<tr><td>' + OfiTitulo.designacao + '</td></tr>'


      }
      tableOfiTitulo += `</table>`;
      document.getElementById("infoOfiTitulo").innerHTML = tableOfiTitulo;
    }
  })
}

function addOfiTitulo(v_id, v_desc) {
  var table = document.getElementById("tableOfiTituloSel");
  var row = table.insertRow();
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  cell1.innerHTML = '<i class="checkDelOfiTitulo far fa-circle" style="color:red;" onclick="checkDelOfiTitulo(this);" value="' + v_id + '"></i>';
  cell2.innerHTML = v_desc;
  $('#modalOfiTitulo').modal('hide');
  $('#criarPes').modal('show');
}


function cancelarOfiTitulo() {
  $('#modalOfiTitulo').modal('hide');
  $('#criarPes').modal('show');
}

/******************** Selecionar e descelecionar ************************** */
function checkDelOfiTitulo(v_obj) {
  if (v_obj.className == 'checkDelOfiTitulo far fa-circle') {
    v_obj.className = 'checkDelOfiTitulo fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelOfiTitulo far fa-circle';
  }
}

function delOfiTitulo(v_id) {
  let elements = document.getElementsByClassName('checkDelOfiTitulo');
  let v_linha = 0;
  for (let element of elements) {
    if (element.attributes.class.value == 'checkDelOfiTitulo fa fa-check-circle') {
      document.getElementById("tableOfiTituloSel").deleteRow(v_linha);
    }
    v_linha += 1;
  }
}

/*********************** Devolve lista Oficio Titulo ************************************** */
async function selectOfiTitulo(v_nomePesq) {

  $('#criarPes').modal('hide');
  $('#modalOfiTitulo').modal('show');
  if (v_nomePesq == null) {
    v_nomePesq = 'all';
  }
  if (v_nomePesq == '') {
    v_nomePesq = 'all';
  }
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/oficio_titulo/search/" + v_nomePesq;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
    } else {
      let OfiTitulos = await response.json();
      let tableOfiTitulo = '<div class="scroll"><table id="tableSelOfiTitulo" class="ListPopUpModal">';

      for (const OfiTitulo of OfiTitulos) {

        tableOfiTitulo += '<tr><td><a href="javascript:addOfiTitulo(' + OfiTitulo.pk_oficio_titulo + ',`' + OfiTitulo.designacao + '`)">' + OfiTitulo.designacao + ' </a></td>' +
          '</tr>';
      }

      tableOfiTitulo += `</table></div>`;
      document.getElementById("tabmodalOfiTitulo").innerHTML = tableOfiTitulo;
    }
  })
}

/**************************************************************************** */
/**************************************************************************** */
/**************************************************************************** */


/****************** Termo ******************* */
async function getAllTermo(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/all";
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos termos");
    } else {
      let termos = await response.json();
      let selectbox = '<label for="termoSel" ><b>Termo:</b></label>' +
        '<select class="form-control" id="termoSel" name="termoSel" placeholder="Termo">' +
        '<option value="-1">&nbsp;</option>';
      let selected = '';
      for (const termo of termos) {
        if (v_id == termo.id) {
          selected = 'selected="selected"';
        } else {
          selected = '';
        }
        selectbox += '<option value="' + termo.id + '" ' + selected + '>' + termo.nome_termo + '(' + termo.capitania + ')' + '</option>'


      }
      selectbox += `</select>`;
      document.getElementById("addTermoPes").innerHTML = selectbox;
    }
  })
}

/****************** Freguesia ******************* */
async function getAllFreguesia(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/all";
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das freguesias");
    } else {
      let freguesias = await response.json();
      let selectbox = '<label for="fregSel" ><b>Freguesia:</b></label>' +
        '<select class="form-control" id="fregSel" name="fregSel" placeholder="Freguesia">' +
        '<option value="-1">&nbsp;</option>';
      let selected = '';
      for (const freguesia of freguesias) {
        if (v_id == freguesia.id) {
          selected = 'selected="selected"';
        } else {
          selected = '';
        }
        selectbox += '<option value="' + freguesia.id + '" ' + selected + '>' + freguesia.nome_freguesia + '</option>'


      }
      selectbox += `</select>`;
      document.getElementById("addFregPes").innerHTML = selectbox;
    }
  })
}


/****************** Comarca ******************* */
async function getAllComarca(v_id) {
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/all";
  let texto = "";
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das comarcas");
    } else {
      let comarcas = await response.json();
      let selectbox = '<label for="comaSel" ><b>Comarca:</b></label>' +
        '<select class="form-control" id="comaSel" name="comaSel" placeholder="Comarca">' +
        '<option value="-1">&nbsp;</option>';
      let selected = '';
      for (const comarca of comarcas) {
        if (v_id == comarca.id) {
          selected = 'selected="selected"';
        } else {
          selected = '';
        }
        selectbox += '<option value="' + comarca.id + '" ' + selected + '>' + comarca.nome + '</option>'


      }
      selectbox += `</select>`;
      document.getElementById("addComaPes").innerHTML = selectbox;
    }
  })
}


// *******************************************
// *************  Filtro ************
// *******************************************

function limparFiltro() {

  document.getElementById("termoFiltro").value = "";
  document.getElementById("termoFiltroValue").value = "";
  document.getElementById("capitaniaFiltro").value = "";
  document.getElementById("capitaniaFiltroValue").value = "";
  document.getElementById("freguesiaFiltro").value = "";
  document.getElementById("freguesiaFiltroValue").value = "";
  document.getElementById("comarcaFiltro").value = "";
  document.getElementById("comarcaFiltroValue").value = "";
  document.getElementById("filtroA").value = "";
  document.getElementById("filtroDe").value = "";
  selectProvocacao(sessionStorage.getItem("v_idUlt"), document.getElementById('procProvocacao').value);
}

function limparFiltroMan() {
  document.getElementById("termoFiltroMan").value = "";
  document.getElementById("termoFiltroValueMan").value = "";
  document.getElementById("capitaniaFiltroMan").value = "";
  document.getElementById("capitaniaFiltroValueMan").value = "";
  document.getElementById("freguesiaFiltroMan").value = "";
  document.getElementById("freguesiaFiltroValueMan").value = "";
  document.getElementById("comarcaFiltroMan").value = "";
  document.getElementById("comarcaFiltroValueMan").value = "";
  document.getElementById("filtroAMan").value = "";
  document.getElementById("filtroDeMan").value = "";
  selectMandado(sessionStorage.getItem("v_idUlt"), document.getElementById('procMandado').value);
}

function limparFiltroCon() {
  document.getElementById("termoFiltroCon").value = "";
  document.getElementById("termoFiltroValueCon").value = "";
  document.getElementById("capitaniaFiltroCon").value = "";
  document.getElementById("capitaniaFiltroValueCon").value = "";
  document.getElementById("freguesiaFiltroCon").value = "";
  document.getElementById("freguesiaFiltroValueCon").value = "";
  document.getElementById("comarcaFiltroCon").value = "";
  document.getElementById("comarcaFiltroValueCon").value = "";
  document.getElementById("filtroACon").value = "";
  document.getElementById("filtroDeCon").value = "";
  selectConsulta(sessionStorage.getItem("v_idUlt"), document.getElementById('procConsulta').value);
}

function limparFiltroUltra() {
  document.getElementById("termoFiltroUltra").value = "";
  document.getElementById("termoFiltroValueUltra").value = "";
  document.getElementById("capitaniaFiltroUltra").value = "";
  document.getElementById("capitaniaFiltroValueUltra").value = "";
  document.getElementById("freguesiaFiltroUltra").value = "";
  document.getElementById("freguesiaFiltroValueUltra").value = "";
  document.getElementById("comarcaFiltroUltra").value = "";
  document.getElementById("comarcaFiltroValueUltra").value = "";
  document.getElementById("filtroAUltra").value = "";
  document.getElementById("filtroDeUltra").value = "";
  selectUltramar(sessionStorage.getItem("v_idUlt"), document.getElementById('procUltramar').value);
}

function limparFiltroRes() {
  document.getElementById("termoFiltroRes").value = "";
  document.getElementById("termoFiltroValueRes").value = "";
  document.getElementById("capitaniaFiltroRes").value = "";
  document.getElementById("capitaniaFiltroValueRes").value = "";
  document.getElementById("freguesiaFiltroRes").value = "";
  document.getElementById("freguesiaFiltroValueRes").value = "";
  document.getElementById("comarcaFiltroRes").value = "";
  document.getElementById("comarcaFiltroValueRes").value = "";
  document.getElementById("filtroARes").value = "";
  document.getElementById("filtroDeRes").value = "";
  selectResposta(sessionStorage.getItem("v_idUlt"), document.getElementById('procResposta').value);
}


/********************* Lista Termos ***************************** */
async function listFiltroTermo(v_search, v_pedido) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('hide');
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('hide');
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('hide');
      } else {
        if (v_pedido == 'ultramar') {
          $('#modalUltramar').modal('hide');
        } else {
          $('#modalResposta').modal('hide');
        }
      }
    }
  }
  $('#modalTemoFiltro').modal('show');
  document.getElementById("origemTermo").value = v_pedido;
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/termo/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista dos termos");
    } else {
      let termos = await response.json();
      let tableTermos = '<table id="tableFiltroTermo" class="ListPopUpModal">';
      let conta = 0;
      for (const termo of termos) {

        tableTermos += '<tr><td><a href="javascript:adicFiltroTermo(' + termo.id + ',`' +
          termo.nome_termo + ' (' + termo.capitania + ')`,`' + v_pedido + '`);">' +
          termo.nome_termo + ' (' + termo.capitania + ')</a></td></tr>';

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableTermos += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableTermos += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableTermos += `</table>`;
      document.getElementById("tabTermoFiltro").innerHTML = tableTermos;
    }
  })
}

async function closelistFiltroTermo() {
  $('#modalTemoFiltro').modal('hide');
  if (document.getElementById("origemTermo").value == 'provocacao') {
    $('#modalProvocacao').modal('show');
  } else {
    if (document.getElementById("origemTermo").value == 'mandado') {
      $('#modalMandado').modal('show');
    } else {
      if (document.getElementById("origemTermo").value == 'consulta') {
        $('#modalConsulta').modal('show');
      } else {
        if (document.getElementById("origemTermo").value == 'Ultramar') {
          $('#modalUltramar').modal('show');
        } else {
          $('#modalResposta').modal('show');
        }
      }
    }
  }

}

async function adicFiltroTermo(v_id, v_nome, v_pedido) {
  $('#modalTemoFiltro').modal('hide');
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('show');
    document.getElementById("termoFiltro").value = v_nome;
    document.getElementById("termoFiltroValue").value = v_id;
    selectProvocacao(sessionStorage.getItem("v_idUlt"), document.getElementById('procProvocacao').value);
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('show');
      document.getElementById("termoFiltroMan").value = v_nome;
      document.getElementById("termoFiltroValueMan").value = v_id;
      selectMandado(sessionStorage.getItem("v_idUlt"), document.getElementById('procMandado').value);
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('show');
        document.getElementById("termoFiltroCon").value = v_nome;
        document.getElementById("termoFiltroValueCon").value = v_id;
        selectConsulta(sessionStorage.getItem("v_idUlt"), document.getElementById('procConsulta').value);
      } else {
        if (v_pedido == 'ultramar') {
          $('#modalUltramar').modal('show');
          document.getElementById("termoFiltroUltra").value = v_nome;
          document.getElementById("termoFiltroValueUltra").value = v_id;
          selectUltramar(sessionStorage.getItem("v_idUlt"), document.getElementById('procUltramar').value);
        } else {
          $('#modalResposta').modal('show');
          document.getElementById("termoFiltroRes").value = v_nome;
          document.getElementById("termoFiltroValueRes").value = v_id;
          selectResposta(sessionStorage.getItem("v_idUlt"), document.getElementById('procResposta').value);
        }
      }
    }
  }
}


/********************* Lista Capitanias ***************************** */
async function listFiltroCapitania(v_search, v_pedido) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('hide');
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('hide');
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('hide');
      }
      if (v_pedido == 'ultramar') {
        $('#modalUltramar').modal('hide');
      } else {
        $('#modalResposta').modal('hide');
      }
    }
  }
  $('#modalCapiFiltro').modal('show');
  document.getElementById("origemCapitania").value = v_pedido;
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/capitania/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das capitanias");
    } else {
      let capitanias = await response.json();
      let tableCapitania = '<table id="tableFiltroCapi" class="ListPopUpModal">';
      let conta = 0;
      for (const capitania of capitanias) {

        tableCapitania += '<tr><td><a href="javascript:adicFiltroCapi(' + capitania.id + ',`' + capitania.nome_capitania + '`,`' + v_pedido + '`);">' + capitania.nome_capitania + ' </a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableCapitania += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableCapitania += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableCapitania += `</table>`;
      document.getElementById("tabCapiFiltro").innerHTML = tableCapitania;
    }
  })
}

function closelistFiltroCapi() {
  $('#modalCapiFiltro').modal('hide');
  if (document.getElementById("origemCapitania").value == 'provocacao') {
    $('#modalProvocacao').modal('show');
  } else {
    if (document.getElementById("origemCapitania").value == 'mandado') {
      $('#modalMandado').modal('show');
    } else {
      if (document.getElementById("origemCapitania").value == 'consulta') {
        $('#modalConsulta').modal('show');
      } else {
        if (document.getElementById("origemCapitania").value == 'ultramar') {
          $('#modalUltramar').modal('show');
        } else {
          $('#modalResposta').modal('show');
        }
      }
    }
  }
}

async function adicFiltroCapi(v_id, v_nome, v_pedido) {

  $('#modalCapiFiltro').modal('hide');
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('show');
    document.getElementById("capitaniaFiltro").value = v_nome;
    document.getElementById("capitaniaFiltroValue").value = v_id;
    selectProvocacao(sessionStorage.getItem("v_idUlt"), document.getElementById('procProvocacao').value);
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('show');
      document.getElementById("capitaniaFiltroMan").value = v_nome;
      document.getElementById("capitaniaFiltroValueMan").value = v_id;
      selectMandado(sessionStorage.getItem("v_idUlt"), document.getElementById('procMandado').value);
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('show');
        document.getElementById("capitaniaFiltroCon").value = v_nome;
        document.getElementById("capitaniaFiltroValueCon").value = v_id;
        selectConsulta(sessionStorage.getItem("v_idUlt"), document.getElementById('procConsulta').value);
      } else {
        if (v_pedido == 'ultramar') {
          $('#modalUltramar').modal('show');
          document.getElementById("capitaniaFiltroUltra").value = v_nome;
          document.getElementById("capitaniaFiltroValueUltra").value = v_id;
          selectUltramar(sessionStorage.getItem("v_idUlt"), document.getElementById('procUltramar').value);
        } else {
          $('#modalResposta').modal('show');
          document.getElementById("capitaniaFiltroRes").value = v_nome;
          document.getElementById("capitaniaFiltroValueRes").value = v_id;
          selectResposta(sessionStorage.getItem("v_idUlt"), document.getElementById('procResposta').value);
        }
      }
    }
  }
}

/********************* Lista Freguesias ***************************** */
async function listFiltroFreguesia(v_search, v_pedido) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('hide');
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('hide');
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('hide');
      }
      if (v_pedido == 'ultramar') {
        $('#modalUltramar').modal('hide');
      } else {
        $('#modalResposta').modal('hide');
      }
    }
  }
  $('#modalFregFiltro').modal('show');
  document.getElementById("origemFreguesia").value = v_pedido;
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/freguesia/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das freguesias");
    } else {
      let freguesias = await response.json();
      let tableFreguesia = '<table id="tableFiltroFreg" class="ListPopUpModal">';
      let conta = 0;
      for (const freguesia of freguesias) {

        tableFreguesia += '<tr><td><a href="javascript:adicFiltroFreg(' + freguesia.id + ',`' + freguesia.nome_freguesia + '`,`' + v_pedido + '`);">' + freguesia.nome_freguesia + ' </a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableFreguesia += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableFreguesia += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableFreguesia += `</table>`;
      document.getElementById("tabFregFiltro").innerHTML = tableFreguesia;
    }
  })
}

async function closelistFiltroFreg() {
  $('#modalFregFiltro').modal('hide');
  if (document.getElementById("origemFreguesia").value == 'provocacao') {
    $('#modalProvocacao').modal('show');
  } else {
    if (document.getElementById("origemFreguesia").value == 'mandado') {
      $('#modalMandado').modal('show');
    } else {
      if (document.getElementById("origemFreguesia").value == 'consulta') {
        $('#modalConsulta').modal('show');
      } else {
        if (document.getElementById("origemFreguesia").value == 'ultramar') {
          $('#modalUltramar').modal('show');
        } else {
          $('#modalResposta').modal('show');
        }
      }
    }
  }
}

async function adicFiltroFreg(v_id, v_nome, v_pedido) {
  $('#modalFregFiltro').modal('hide');
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('show');
    document.getElementById("freguesiaFiltro").value = v_nome;
    document.getElementById("freguesiaFiltroValue").value = v_id;
    selectProvocacao(sessionStorage.getItem("v_idUlt"), document.getElementById('procProvocacao').value);
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('show');
      document.getElementById("freguesiaFiltroMan").value = v_nome;
      document.getElementById("freguesiaFiltroValueMan").value = v_id;
      selectMandado(sessionStorage.getItem("v_idUlt"), document.getElementById('procMandado').value);
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('show');
        document.getElementById("freguesiaFiltroCon").value = v_nome;
        document.getElementById("freguesiaFiltroValueCon").value = v_id;
        selectConsulta(sessionStorage.getItem("v_idUlt"), document.getElementById('procConsulta').value);
      } else {
        if (v_pedido == 'ultramar') {
          $('#modalUltramar').modal('show');
          document.getElementById("freguesiaFiltroUltra").value = v_nome;
          document.getElementById("freguesiaFiltroValueUltra").value = v_id;
          selectUltramar(sessionStorage.getItem("v_idUlt"), document.getElementById('procUltramar').value);
        } else {
          $('#modalResposta').modal('show');
          document.getElementById("freguesiaFiltroRes").value = v_nome;
          document.getElementById("freguesiaFiltroValueRes").value = v_id;
          selectResposta(sessionStorage.getItem("v_idUlt"), document.getElementById('procResposta').value);
        }
      }
    }
  }
}

/********************* Lista Comarcas ***************************** */
async function listFiltroComarca(v_search, v_pedido) {
  if (v_search == null) {
    v_search = 'all';
  }
  if (v_search == '') {
    v_search = 'all';
  }
  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('hide');
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('hide');
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('hide');
      }
      if (v_pedido == 'ultramar') {
        $('#modalUltramar').modal('hide');
      } else {
        $('#modalResposta').modal('hide');
      }
    }
  }
  $('#modalComaFiltro').modal('show');
  document.getElementById("origemComarca").value = v_pedido;
  const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/comarca/search/" + v_search;
  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());

  var myInit = { method: "GET", headers: myHeaders }
  var myRequest = new Request(`${urlBase}`, myInit);

  await fetch(myRequest).then(async function (response) {
    if (!response.ok) {
      console.log("Erro ao devolver a lista das comarcas");
    } else {
      let comarcas = await response.json();
      let tableComarca = '<table id="tableFiltroComa" class="ListPopUpModal">';
      let conta = 0;
      for (const comarca of comarcas) {

        tableComarca += '<tr><td><a href="javascript:adicFiltroComa(' + comarca.id + ',`' + comarca.nome_comarca + '`,`' + v_pedido + '`);">' + comarca.nome_comarca + ' </a></td></tr>'

        if (conta == 10) {
          break;
        }
        conta++;
      }
      if (conta >= 10) {
        tableComarca += '<tr><td>Esta pesquisa devolveu muitos valores.<br>Por afine a sua pesquisa para devolver menos valores.</td></tr>'
      } else {
        if (conta == 0) {
          tableComarca += '<tr><td>Esta pesquisa não devolveu valores.<br>Por favor faça uma nova.</td></tr>'
        }
      }
      tableComarca += `</table>`;
      document.getElementById("tabComaFiltro").innerHTML = tableComarca;
    }
  })
}

async function closelistFiltroComa() {
  $('#modalComaFiltro').modal('hide');
  if (document.getElementById("origemComarca").value == 'provocacao') {
    $('#modalProvocacao').modal('show');
  } else {
    if (document.getElementById("origemComarca").value == 'mandado') {
      $('#modalMandado').modal('show');
    } else {
      if (document.getElementById("origemComarca").value == 'consulta') {
        $('#modalConsulta').modal('show');
      } else {
        if (document.getElementById("origemComarca").value == 'ultramar') {
          $('#modalUltramar').modal('show');
        } else {
          $('#modalResposta').modal('show');
        }
      }
    }
  }
}

async function adicFiltroComa(v_id, v_nome, v_pedido) {
  $('#modalComaFiltro').modal('hide');

  if (v_pedido == 'provocacao') {
    $('#modalProvocacao').modal('show');
    document.getElementById("comarcaFiltro").value = v_nome;
    document.getElementById("comarcaFiltroValue").value = v_id;
    selectProvocacao(sessionStorage.getItem("v_idUlt"), document.getElementById('procProvocacao').value);
  } else {
    if (v_pedido == 'mandado') {
      $('#modalMandado').modal('show');
      document.getElementById("comarcaFiltroMan").value = v_nome;
      document.getElementById("comarcaFiltroValueMan").value = v_id;
      selectMandado(sessionStorage.getItem("v_idUlt"), document.getElementById('procMandado').value);
    } else {
      if (v_pedido == 'consulta') {
        $('#modalConsulta').modal('show');
        document.getElementById("comarcaFiltroCon").value = v_nome;
        document.getElementById("comarcaFiltroValueCon").value = v_id;
        selectConsulta(sessionStorage.getItem("v_idUlt"), document.getElementById('procConsulta').value);
      } else {
        if (v_pedido == 'ultramar') {
          $('#modalUltramar').modal('show');
          document.getElementById("comarcaFiltroUltra").value = v_nome;
          document.getElementById("comarcaFiltroValueUltra").value = v_id;
          selectUltramar(sessionStorage.getItem("v_idUlt"), document.getElementById('procUltramar').value);
        } else {
          $('#modalResposta').modal('show');
          document.getElementById("comarcaFiltroRes").value = v_nome;
          document.getElementById("comarcaFiltroValueRes").value = v_id;
          selectResposta(sessionStorage.getItem("v_idUlt"), document.getElementById('procResposta').value);
        }
      }
    }
  }
}




// ***********************************************
// ***********************************************
// Funções Criar, Modificar e Eliminar
// ***********************************************
// ***********************************************



// *******************************************
// *************  Salvar Ultramar ************
// *******************************************


async function salvar() {
  // função de update da reposta
  var errForm = "";
  // validação do preenchimento dos campos
  if (document.getElementById("ultObs").value == "") {
    errForm += "Resumo é obrigatório.<br>";
  }
  if (document.getElementById("registroAddValue").value == "") {
    errForm += "Tem de escolher pelo menos registro.<br>";
  }
  if (document.getElementById("anoUlt").value == "") {
    errForm += "Ano é obrigatório.<br>";
  }
  if (errForm != "") {
    Swal.fire({
      icon: "error",
      title: errForm,
      showConfirmButton: false,
      timer: 4500,
    });

  } else {
    let v_idUlt = sessionStorage.getItem("v_idUlt")
    let v_anoPos = '';
    if (document.getElementById("anoAnt").checked == true) {
      v_anoPos = 'N'
    }
    if (document.getElementById("anoPos").checked == true) {
      v_anoPos = 'Y'
    }
    //Se não houver erro no preenchimento então chama o serviço de registo

    var objResp = {
      data: document.getElementById("datUlt").value,
      resumo: document.getElementById("ultObs").value,
      registro: document.getElementById("registroAddValue").value,
      ano: document.getElementById("anoUlt").value,
      referencia_documental: document.getElementById("ref_docValue").value,
      autoridade: document.getElementById("autUlt").value,
      anoPost: v_anoPos,
      folio_pagina: document.getElementById("folioPagina").value
    };
    var exit = "no";
    var json = JSON.stringify(objResp);

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      //url: "http://213.30.2.186:22228/KOFKOF/webresources/users/create",
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/update/" + v_idUlt,
      type: "PUT",
      headers: { "token": await getToken() },
      data: json,
      dataType: "json",
      success: function (result) {
        sessionStorage.setItem("v_idUlt", result);
        Swal.fire({
          icon: "success",
          title: "Ultramar guardado com sucesso",
          showConfirmButton: false,
          timer: 1500,
        }).then(function () {
          window.location = "ultramar.html";

        });

      },
      error: function (err) {
        console.log(err);
        exit = err;
        Swal.fire({
          icon: "error",
          title: "Erro ao alterar ultramar",
          showConfirmButton: false,
          timer: 1500,
        });
      }
    });
  }
}

function cancelar() {
  window.location = "ultramar.html";
}

// função de criação de nova Ultramar
async function addNovoUltramar(v_id) {
  var errForm = "";

  // validação do preenchimento dos campos
  if (document.getElementById("ultObs").value == "") {
    errForm += "Resumo é obrigatório.<br>";
  }
  if (document.getElementById("registroAddValue").value == "") {
    errForm += "Registo é obrigatório.<br>";
  }
  if (document.getElementById("anoUlt").value == "") {
    errForm += "Ano é obrigatório.<br>";
  }
  if (errForm != "") {
    Swal.fire({
      icon: "error",
      title: errForm,
      showConfirmButton: false,
      timer: 4500,
    });

  } else {
    //Se não houver erro no preenchimento então chama o serviço de registo
    let v_anoPos = '';
    if (document.getElementById("anoAnt").checked == true) {
      v_anoPos = 'N'
    }
    if (document.getElementById("anoPos").checked == true) {
      v_anoPos = 'Y'
    }
    var objProv = {
      data: document.getElementById("datUlt").value,
      resumo: document.getElementById("ultObs").value,
      autoridade: document.getElementById("autUlt").value,
      referencia_documental: document.getElementById("ref_docValue").value,
      registro: document.getElementById("registroAddValue").value,
      ano: document.getElementById("anoUlt").value,
      anoPost: v_anoPos,
      folio_pagina: document.getElementById("folioPagina").value
    };
    var exit = "no";
    var json = JSON.stringify(objProv);

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/insert",
      type: "POST",
      headers: { "token": await getToken() },
      data: json,
      dataType: "json",
      success: function (result) {
        sessionStorage.setItem("v_idUlt", result);
        Swal.fire({
          icon: "success",
          title: "Ultramar Criado.",
          showConfirmButton: false,
          timer: 1500,
        }).then(function () {
          window.location = "EditUltramar.html";
        });

      },
      error: function (err) {
        console.log(err);
        exit = err;
        Swal.fire({
          icon: "error",
          title: "Erro ao criar Ultramar",
          showConfirmButton: false,
          timer: 1500,
        }).then(function () {
          window.location = "ultramar.html";
        });
      }
    });
  }
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
      complemento: document.getElementById("compAdd").value,
      url: document.getElementById("urlAdd").value
    };
    var exit = "no";
    var json = JSON.stringify(objCons);

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/referencia_documental/insert",
      type: "POST",
      headers: { "token": await getToken() },
      data: json,
      dataType: "json",
      success: function (result) {
        Swal.fire({
          icon: "success",
          title: "Referência Documental Criada.",
          showConfirmButton: false,
          timer: 1500,
        }).then(function () {
          $('#criarRefDoc').modal('hide');
          adicRefDoc(result, document.getElementById("refDocAdd").value + '(' + document.getElementById("compAdd").value + ')');
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
          $('#criarRefDoc').modal('hide');
          adicRefDoc(result, document.getElementById("refDocAdd").value + '(' + document.getElementById("compAdd").value + ')');
        });
      }
    });
  }
}


/************************** Secretário/Concelheiro ***************************** */

/******************** Selecionar e descelecionar ************************** */
function checkDelSecEst(v_obj) {
  if (v_obj.className == 'checkDelSecEst far fa-circle') {
    v_obj.className = 'checkDelSecEst fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelSecEst far fa-circle';
  }
}

function selSecEstado(v_id) {
  $('#modalSecEstado').modal('show');
  selectSecEstado(v_id);
}

/********************* Adicionar Secretário/Concelheiro ************************* */
async function addSecEstado(v_prov_id, v_secEstado_id) {
  //Se não houver erro no preenchimento então chama o serviço de registo
  var objResp = {
    secretario_conselho: v_secEstado_id,
    ultramar: v_prov_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_secretario_conselho_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Secretário/Concelheiro criado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalSecEstado').modal('hide');
        listSecEstado(v_prov_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Secretário/Concelheiro adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalSecEstado').modal('hide');
        listSecEstado(v_prov_id);
      });
    }
  });
}

/********************* Retirar Secretário/Concelheiro ************************* */
async function delSecEstado(v_prov_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelSecEst fa fa-check-circle');
  let v_secEstado_id;
  for (let element of elements) {
    v_secEstado_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_secretario_conselho_ultramar/delete/" + v_secEstado_id + "/" + v_prov_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Secretário(s)/Concelheiro(s) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    $('#modalSecEstado').modal('hide');
    listSecEstado(v_prov_id);
  });
}

/************************** Pessoas Citadas ***************************** */
/********************* Adicionar Pessoas Citada ************************* */
async function addPesCitada(v_ult_id, v_pesCitada_id) {
  //Se não houver erro no preenchimento então chama o serviço de registo
  var objResp = {
    p_citadas: v_pesCitada_id,
    ultramar: v_ult_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pcitadas_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Pessoa citada adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalPesCita').modal('hide');
        $('#criarPes').modal('hide');
        listPesCitada(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Pessoa citada adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalPesCita').modal('hide');
        listPesCitada(v_ult_id);
      });
    }
  });
}

/********************* Retirar Pessoa Ciatada ************************* */
async function delPesCitada(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelPesCitada fa fa-check-circle');
  let v_pesCitada_id;
  for (let element of elements) {
    v_pesCitada_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pcitadas_ultramar/delete/" + v_pesCitada_id + "/" + v_ult_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Pessoa(s) Citada(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listPesCitada(v_ult_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelPesCitada(v_obj) {
  if (v_obj.className == 'checkDelPesCitada far fa-circle') {
    v_obj.className = 'checkDelPesCitada fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelPesCitada far fa-circle';
  }
}


function selPesCitada(v_id) {
  $('#modalPesCita').modal('show');
  selectPesCitada(v_id);
  sessionStorage.setItem("v_AddPes", 'pesCitada');
}


/************************** Palavra Chave ***************************** */
/********************* Adicionar Palavra Chaves ************************* */
async function addPalChave(v_ult_id, v_dpalChave_id) {
  //Se não houver erro no preenchimento então chama o serviço de registo
  var objResp = {
    palavra_chave: v_dpalChave_id,
    ultramar: v_ult_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pchave_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Palavra Chave adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalPalChave').modal('hide');
        listPalavraChave(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Palavra Chave adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalPalChave').modal('hide');
        listPalavraChave(v_ult_id);
      });
    }
  });
}

/********************* Retirar Palavra Chave ************************* */
async function delPalChave(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelPalChave fa fa-check-circle');
  let v_pchave_id;
  for (let element of elements) {
    v_pchave_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_pchave_ultramar/delete/" + v_ult_id + "/" + v_pchave_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Palavra(s) Chave(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listPalavraChave(v_ult_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelPalChave(v_obj) {
  if (v_obj.className == 'checkDelPalChave far fa-circle') {
    v_obj.className = 'checkDelPalChave fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelPalChave far fa-circle';
  }
}

function selPalChave(v_id) {
  $('#modalPalChave').modal('show');
  selectPalChave(v_id);
}


/************************** Requerente ***************************** */
/********************* Adicionar Requerente ************************* */
async function addRequerente(v_ult_id, v_requerente_id) {

  var objResp = {
    requerente: v_requerente_id,
    ultramar: v_ult_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_requerente_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Requerente adicionado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalRequerente').modal('hide');
        listRequerente(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Requerente adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalRequerente').modal('hide');
        $('#criarPes').modal('hide');
        listRequerente(v_ult_id);
      });
    }
  });
}

/********************* Retirar Requerente ************************* */
async function delRequerente(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelRequerente fa fa-check-circle');
  let v_requerente_id;
  for (let element of elements) {
    v_requerente_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_requerente_ultramar/delete/" + v_requerente_id + "/" + v_ult_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Requerente(s) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listRequerente(v_ult_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelRequerente(v_obj) {
  if (v_obj.className == 'checkDelRequerente far fa-circle') {
    v_obj.className = 'checkDelRequerente fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelRequerente far fa-circle';
  }
}

function selRequerente(v_id) {
  $('#modalRequerente').modal('show');
  selectRequerente(v_id);
  sessionStorage.setItem("v_AddPes", 'requerente');
}

/************************** Tema ***************************** */
/********************* Adicionar Tema ************************* */
async function addTema(v_ult_id, v_tema_id) {

  var objResp = {
    tema: v_tema_id,
    ultramar: v_ult_id
  };
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_tema_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Tema adicionado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalTema').modal('hide');
        listTema(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Tema adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalTema').modal('hide');
        listTema(v_ult_id);
      });
    }
  });
}

/********************* Retirar Tema ************************* */
async function delTema(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelTema fa fa-check-circle');
  let v_tema_id;
  for (let element of elements) {
    v_tema_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_tema_ultramar/delete/" + v_tema_id + "/" + v_ult_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Tema(s) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listTema(v_ult_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelTema(v_obj) {
  if (v_obj.className == 'checkDelTema far fa-circle') {
    v_obj.className = 'checkDelTema fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelTema far fa-circle';
  }
}

function selTema(v_id) {
  $('#modalTema').modal('show');
  selectTema(v_id);
}





/************************** Local ***************************** */
/************************** Termo ***************************** */
/********************* Adicionar Termo ************************* */
async function addTermo(v_ultra_id, v_termo_id) {

  var objResp = {
    termo: v_termo_id,
    ultramar: v_ultra_id
  };
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Termo adicionado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalTermo').modal('hide');
        listTermo(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Termo adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalTermo').modal('hide');
        listTermo(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Termo ************************* */
async function delTermo(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelTermo fa fa-check-circle');
  for (let element of elements) {
    v_termo_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_termo_ultramar/delete/" + v_termo_id + "/" + v_ultra_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Termo(s) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listTermo(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelTermo(v_obj) {
  if (v_obj.className == 'checkDelTermo far fa-circle') {
    v_obj.className = 'checkDelTermo fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelTermo far fa-circle';
  }
}

function selTermo(v_id) {
  $('#modalTermo').modal('show');
  selectTermo(v_id, document.getElementById('procTermo').value);
}

/************************** Freguesia ***************************** */
/********************* Adicionar Freguesia ************************* */
async function addFreguesia(v_ultra_id, v_freg_id) {

  var objFreg = {
    freguesia: v_freg_id,
    ultramar: v_ultra_id
  };
  var json = JSON.stringify(objFreg);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_freguesia_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Freguesia adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalFreguesia').modal('hide');
        listFreguesia(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Freguesia adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalFreguesia').modal('hide');
        listFreguesia(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Freguesia ************************* */
async function delFreguesia(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelFreguesia fa fa-check-circle');
  for (let element of elements) {
    v_freg_id = element.attributes.value.value;
    var myHeaders = new Headers();

    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_freguesia_ultramar/delete/" + v_freg_id + "/" + v_ultra_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Freguesia(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listFreguesia(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelFreguesia(v_obj) {
  if (v_obj.className == 'checkDelFreguesia far fa-circle') {
    v_obj.className = 'checkDelFreguesia fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelFreguesia far fa-circle';
  }
}

function selFreguesia(v_id) {
  $('#modalFreguesia').modal('show');
  selectFreguesia(v_id, document.getElementById('procFreguesia').value);
}

/************************** Comarca ***************************** */
/********************* Adicionar Comarca ************************* */
async function addComarca(v_ultra_id, v_coma_id) {

  var objFreg = {
    comarca: v_coma_id,
    ultramar: v_ultra_id
  };
  var json = JSON.stringify(objFreg);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_comarca_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Comarca adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalComarca').modal('hide');
        listComarca(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Comarca adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalComarca').modal('hide');
        listComarca(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Comarca ************************* */
async function delComarca(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelComarca fa fa-check-circle');
  for (let element of elements) {
    v_coma_id = element.attributes.value.value;
    var myHeaders = new Headers();

    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_comarca_ultramar/delete/" + v_coma_id + "/" + v_ultra_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Comarca(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listComarca(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelComarca(v_obj) {
  if (v_obj.className == 'checkDelComarca far fa-circle') {
    v_obj.className = 'checkDelComarca fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelComarca far fa-circle';
  }
}

function selComarca(v_id) {
  $('#modalComarca').modal('show');
  selectComarca(v_id, document.getElementById('procComarca').value);
}

/************************** Capitania ***************************** */
/********************* Adicionar Capitania ************************* */
async function addCapitania(v_ultra_id, v_cap_id) {

  var objCap = {
    capitania: v_cap_id,
    ultramar: v_ultra_id
  };
  var json = JSON.stringify(objCap);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_capitania_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Capitania adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalCapitania').modal('hide');
        listCapitania(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Capitania adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalCapitania').modal('hide');
        listCapitania(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Capitania ************************* */
async function delCapitania(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelCapitania fa fa-check-circle');
  for (let element of elements) {
    v_coma_id = element.attributes.value.value;
    var myHeaders = new Headers();

    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_capitania_ultramar/delete/" + v_coma_id + "/" + v_ultra_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Capitania(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listCapitania(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelCapitania(v_obj) {
  if (v_obj.className == 'checkDelCapitania far fa-circle') {
    v_obj.className = 'checkDelCapitania fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelCapitania far fa-circle';
  }
}

function selCapitania(v_id) {
  $('#modalCapitania').modal('show');
  selectCapitania(v_id, document.getElementById('procCapitania').value);
}



/************************** Provocação ***************************** */
/********************* Adicionar Provocação ************************* */
async function addProvocacao(v_ultra_id, v_provocacao_id) {

  var objResp = {
    provocacao: v_provocacao_id,
    ultramar: v_ultra_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_provocacao/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Provocação adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalProvocacao').modal('hide');
        listProvocacao(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "error",
        title: "Erro ao adicionar provocação.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalProvocacao').modal('hide');
        listProvocacao(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Provocação ************************* */
async function delProvocacao(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelProvocacao fa fa-check-circle');
  let v_procacao_id;
  for (let element of elements) {
    v_procacao_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_provocacao/delete/" + v_ultra_id + "/" + v_procacao_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Procação(ões) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listProvocacao(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelProvocacao(v_obj) {
  if (v_obj.className == 'checkDelProvocacao far fa-circle') {
    v_obj.className = 'checkDelProvocacao fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelProvocacao far fa-circle';
  }
}

function selProvocacao(v_id) {
  $('#modalProvocacao').modal('show');
  selectProvocacao(v_id);
}


/************************** Mandado ***************************** */
/********************* Adicionar Mandado ************************* */
async function addMandado(v_ultra_id, v_mandado_id) {

  var objResp = {
    mandado: v_mandado_id,
    ultramar: v_ultra_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_mandado_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Mandado adicionado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalMandado').modal('hide');
        listMandado(v_ultra_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Mandado adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalMandado').modal('hide');
        listMandado(v_ultra_id);
      });
    }
  });
}

/********************* Retirar Mandado ************************* */
async function delMandado(v_ultra_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelMandado fa fa-check-circle');
  let v_mandado_id;
  for (let element of elements) {
    v_mandado_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_mandado_ultramar/delete/" + v_ultra_id + "/" + v_mandado_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Mandado(s) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listMandado(v_ultra_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelMandado(v_obj) {
  if (v_obj.className == 'checkDelMandado far fa-circle') {
    v_obj.className = 'checkDelMandado fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelMandado far fa-circle';
  }
}

function selMandado(v_id) {
  $('#modalMandado').modal('show');
  selectMandado(v_id);
}




/************************** Consulta ***************************** */
/********************* Adicionar Consulta ************************* */
async function addConsulta(v_ult_id, v_consulta_id) {

  var objResp = {
    consulta: v_consulta_id,
    ultramar: v_ult_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_consulta/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Consulta adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalConsulta').modal('hide');
        listConsulta(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Consulta adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalConsulta').modal('hide');
        listConsulta(v_ult_id);
      });
    }
  });
}

/********************* Retirar Consulta ************************* */
async function delConsulta(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelConsulta fa fa-check-circle');
  let v_consulta_id;
  for (let element of elements) {
    v_consulta_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_consulta/delete/" + v_ult_id + "/" + v_consulta_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Consulta(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listConsulta(v_ult_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelConsulta(v_obj) {
  if (v_obj.className == 'checkDelConsulta far fa-circle') {
    v_obj.className = 'checkDelConsulta fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelConsulta far fa-circle';
  }
}

function selConsulta(v_id) {
  $('#modalConsulta').modal('show');
  selectConsulta(v_id);
}



/************************** Ultramar ***************************** */
/********************* Adicionar Ultramar ************************* */
async function addUltramarRel(v_ultramar_new_id, v_ultramar_old_id) {

  var objResp = {
    ultramar_mais_antigo: v_ultramar_old_id,
    ultramar_mais_recente: v_ultramar_new_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_ultramar/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Ultramar adicionado com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalUltramar').modal('hide');
        listUltramar(v_ultramar_new_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Ultramar adicionado com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalUltramar').modal('hide');
        listUltramar(v_ultramar_new_id);
      });
    }
  });
}

/********************* Retirar Ultramar ************************* */
async function delUltramar(v_ultramar_new_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelUltramar fa fa-check-circle');
  let v_ultramar_old_id;
  for (let element of elements) {
    v_ultramar_old_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_ultramar/delete/" + v_ultramar_old_id + "/" + v_ultramar_new_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Ultramar(es) removido(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listUltramar(v_ultramar_new_id);
  });
}

/******************** Selecionar e descelecionar ************************** */
function checkDelUltramar(v_obj) {
  if (v_obj.className == 'checkDelUltramar far fa-circle') {
    v_obj.className = 'checkDelUltramar fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelUltramar far fa-circle';
  }
}

function selUltramar(v_id) {
  $('#modalUltramar').modal('show');
  selectUltramar(v_id);
}


/************************** Resposta ***************************** */
/********************* Adicionar Resposta ************************* */
async function addResposta(v_ult_id, v_resp_id) {

  var objResp = {
    ultramar: v_ult_id,
    resposta: v_resp_id
  };
  var exit = "no";
  var json = JSON.stringify(objResp);

  var myHeaders = new Headers();
  myHeaders.append("token", await getToken());
  $.ajax({
    url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_resposta/insert",
    type: "POST",
    headers: { "token": await getToken() },
    data: json,
    dataType: "json",
    success: function (result) {
      Swal.fire({
        icon: "success",
        title: "Resposta adicionada com sucesso.",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalResposta').modal('hide');
        listResposta(v_ult_id);
      });

    },
    error: function (err) {
      console.log(err);
      exit = err;
      Swal.fire({
        icon: "success",
        title: "Resposta adicionada com sucesso!!",
        showConfirmButton: false,
        timer: 1500,
      }).then(function () {
        $('#modalResposta').modal('hide');
        listResposta(v_ult_id);
      });
    }
  });
}

/********************* Retirar Resposta ************************* */
async function delResposta(v_ult_id) {
  // vai percorrer todos os item da página que utilizem a class checked
  // Se checked e clicar eliminar remove da lista os selecionados
  let elements = document.getElementsByClassName('checkDelResposta fa fa-check-circle');
  let v_resp_id;
  for (let element of elements) {
    v_resp_id = element.attributes.value.value;

    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());
    $.ajax({
      url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/relac_ultramar_resposta/delete/" + v_ult_id + "/" + v_resp_id,
      type: "DELETE",
      headers: { "token": await getToken() },
      data: "",
      dataType: "json"
    });
  }
  Swal.fire({
    icon: "success",
    title: "Respostas(s) removida(s) com sucesso.",
    showConfirmButton: false,
    timer: 1500,
  }).then(function () {
    listResposta(v_ult_id);
  });
}


/******************** Selecionar e descelecionar ************************** */
function checkDelResposta(v_obj) {
  if (v_obj.className == 'checkDelResposta far fa-circle') {
    v_obj.className = 'checkDelResposta fa fa-check-circle';
  } else {
    v_obj.className = 'checkDelResposta far fa-circle';
  }
}

function selResposta(v_id) {
  $('#modalResposta').modal('show');
  selectResposta(v_id);
}



/********************************************************************** */
/************************* Criar Pessoa ******************************* */
/********************************************************************** */




async function addPessoa() {
  // função Criar Pessoa
  var errForm = "";
  // validação do preenchimento dos campos
  if (document.getElementById("addNomePes").value == "") {
    errForm += "Nome é obrigatório.<br>";
  }
  if (document.getElementById("addSexoPes").value == "") {
    errForm += "Sexo é obrigatório.<br>";
  }
  if (errForm != "") {
    Swal.fire({
      icon: "error",
      title: "Erro criar pessoa",
      text: errForm,
      showConfirmButton: false,
      timer: 3500,
    });

  } else {
    //Se não houver erro no preenchimento então chama o serviço criar pessoa
    var objPes = {
      nome: document.getElementById("addNomePes").value,
      idade: document.getElementById("addIdadePes").value,
      sexo: document.getElementById("addSexoPes").value,
      filiacao: document.getElementById("addFilPes").value,
      termo: document.getElementById("termoPesAddValue").value,
      freguesia: document.getElementById("fregPesAddValue").value,
      comarca: document.getElementById("comaPesAddValue").value,
      capitania: document.getElementById("capiPesAddValue").value
    };
    const v_token = await getToken();
    const v_url = await getUrlPayaraDireto();
    var exit = "no";
    var json = JSON.stringify(objPes);
    console.log(json);
    var myHeaders = new Headers();
    myHeaders.append("token", v_token);
    $.ajax({
      url: v_url + "/CircPeticionario/webresources/pessoa/insert",
      type: "POST",
      headers: { "token": v_token },
      data: json,
      dataType: "json",
      success: function (result) {
        let pessoaId = result;
        /* Adicionar Socio Juridico escolhidos  a pessoa que foi criada*/
        let elements = document.getElementsByClassName('checkDelSocJuridico');
        for (let element of elements) {
          var objSocPes = {
            marcador_socio_juridico: element.attributes.value.value,
            pessoa: pessoaId
          };
          var exit = "no";
          var json = JSON.stringify(objSocPes);

          var myHeaders = new Headers();
          myHeaders.append("token", v_token);
          $.ajax({
            url: v_url + "/CircPeticionario/webresources/relac_marcador_socio_juridico_pessoa/insert",
            type: "POST",
            headers: { "token": v_token },
            data: json,
            dataType: "json",

          });
        }
        /* Adicionar Marcador Económico escolhidos  a pessoa que foi criada*/
        let elementsMarEco = document.getElementsByClassName('checkDelMarEco');
        for (let element of elementsMarEco) {
          var objMarEco = {
            marcador_economico_ocupacao: element.attributes.value.value,
            pessoa: pessoaId
          };
          var exit = "no";
          var json = JSON.stringify(objMarEco);

          var myHeaders = new Headers();
          myHeaders.append("token", v_token);
          $.ajax({
            url: v_url + "/CircPeticionario/webresources/relac_marcador_economico_ocupacao_pessoa/insert",
            type: "POST",
            headers: { "token": v_token },
            data: json,
            dataType: "json",

          });
        }
        /* Adicionar Marcador Económico escolhidos  a pessoa que foi criada*/
        let elementsOfiTitulo = document.getElementsByClassName('checkDelOfiTitulo');
        for (let element of elementsOfiTitulo) {
          var objOfiTitulo = {
            oficio_titulo: element.attributes.value.value,
            pessoa: pessoaId
          };
          var exit = "no";
          var json = JSON.stringify(objOfiTitulo);

          var myHeaders = new Headers();
          myHeaders.append("token", v_token);
          $.ajax({
            url: v_url + "/CircPeticionario/webresources/relac_oficio_requerente/insert",
            type: "POST",
            headers: { "token": v_token },
            data: json,
            dataType: "json",

          });
        }
        Swal.fire({
          icon: "success",
          title: "Pessoa criada com sucesso",
          showConfirmButton: false,
          timer: 1500,
        }).then(function () {
          $('#criarPes').modal('hide');
          let v_tipoAdd = sessionStorage.getItem("v_AddPes");
          if (v_tipoAdd == 'requerente') {
            addRequerente(sessionStorage.getItem("v_idUlt"), pessoaId);
          } else {
            if (v_tipoAdd == 'pesCitada') {
              addPesCitada(sessionStorage.getItem("v_idUlt"), pessoaId)
            }
          }
        });
      },
      error: function (err) {
        console.log(err);
        exit = err;
        Swal.fire({
          icon: "error",
          title: "Erro ao criar a pessoa.",
          showConfirmButton: false,
          timer: 1500,
        });
      }
    });
  }
}

async function delUltra() {
  Swal.fire({
    icon: "error",
    title: "Tem a certeza que quer eliminar este ultramar?",
    showConfirmButton: true,
    showCancelButton: true,
  }).then(async (result) => {
    if (result.isConfirmed) {
      var myHeaders = new Headers();
      myHeaders.append("token", await getToken());
      $.ajax({
        url: await getUrlPayaraDireto() + "/CircPeticionario/webresources/ultramar/delete/" + sessionStorage.getItem("v_idUlt"),
        type: "DELETE",
        headers: { "token": await getToken() },
        dataType: "json",
        success: function (result) {
          Swal.fire({
            icon: "success",
            title: "Ultramar eliminado com sucesso.",
            showConfirmButton: false,
            timer: 1500,
          }).then(function () {
            window.location = "ultramar.html";
          });

        },
        error: function (err) {
          console.log(err);
          exit = err;
          Swal.fire({
            icon: "error",
            title: "Erro ao elimiar o ultramar.",
            text: "Este ultramar encontra-se associada a um registo",
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });
    }
  });
}