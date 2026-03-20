/************************* Utilizadores  *************************** */
/************************ Lista todos os Utilizadores ****************************** */
async function getUtilizadores(pageNum, rowsPage) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/utilizadores/all/" + pageNum + "/" + rowsPage;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver a lista dos utilizadores");
        } else {
            let objUtilizadores = await response.json();
            let Utilizadores = objUtilizadores.allUtilizadores;
            let tabela = '<table class="table">' +
                '<tr>' +
                '<th scope="col">Editar</th><th scope="col">Nome</th><th scope="col">Email</th><th scope="col">Utilizador</th><th scope="col">Aministrador?</th>' +
                '</tr>';
            for (const Utilizador of Utilizadores) {
                tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getUtilizador(`+ Utilizador.user_id + `)"></i></td>
                                    <td scope="row">` + Utilizador.nome + `</td>
                                    <td scope="row">` + Utilizador.email + `</td>
                                    <td scope="row">` + Utilizador.username + `</td>
                                    <td scope="row">` + (Utilizador.admin == 'Y' ? "Sim" : "Não") + `</td>
                                </tr>`;
            }
            tabela += `</table>`;

            // Paginação
            tabela += ` <ul class="pagination">`;
            let numPage = Math.ceil(objUtilizadores.rowcount / rowsPage);
            for (let i = 1; i <= numPage; i++) {
                if (i == pageNum) {
                    tabela += `<li class="page-item active"><span class="page-link" >` + i + `</span></li>`;
                } else {
                    tabela += `<li class="page-item"><a class="page-link" href="javascript:getUtilizadores(` + i + `,` + rowsPage + `)">` + i + `</a></li>`;
                }
            }
            tabela += `</ul> `;
            document.getElementById("TableUtilizador").innerHTML = tabela;
        }
    })
}


/************************ Procurar utilizadores ****************************** */
async function search(v_search) {
    var urlBase;
    if (v_search == null || v_search == '') {
        getUtilizadores(1, 10);
    } else {
        urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/utilizadores/search/" + v_search;

        let texto = "";
        var myHeaders = new Headers();
        myHeaders.append("token", await getToken());

        var myInit = { method: "GET", headers: myHeaders }
        var myRequest = new Request(`${urlBase}`, myInit);

        await fetch(myRequest).then(async function (response) {
            if (!response.ok) {
                console.log("Erro ao devolver a lista dos utilizadores");
            } else {
                let Utilizadores = await response.json();
                let tabela = '<table class="table">' +
                    '<tr>' +
                    '<th scope="col">Editar</th><th scope="col">Nome</th><th scope="col">Email</th><th scope="col">Utilizador</th><th scope="col">Aministrador?</th>' +
                    '</tr>';
                let conta = 0;
                for (const Utilizador of Utilizadores) {
                    tabela += `<tr>
                                    <td scope="row"><i class="fa fa-pen-fancy" style="cursor:pointer;" onclick="getUtilizador(`+ Utilizador.user_id + `)"></i></td>
                                    <td scope="row">` + Utilizador.nome + `</td>
                                    <td scope="row">` + Utilizador.email + `</td>
                                    <td scope="row">` + Utilizador.username + `</td>
                                    <td scope="row">` + (Utilizador.admin == 'Y' ? "Sim" : "Não") + `</td>
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
                document.getElementById("TableUtilizador").innerHTML = tabela;
            }
        });
    }
}


/************************ devolve o utilizador ****************************** */
async function getUtilizador(v_userId) {
    const urlBase = await getUrlPayaraDireto() + "/CircPeticionario/webresources/utilizadores/record/" + v_userId;
    var myHeaders = new Headers();
    myHeaders.append("token", await getToken());

    var myInit = { method: "GET", headers: myHeaders }
    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response) {
        if (!response.ok) {
            console.log("Erro ao devolver o utilizador.");
        } else {
            $('#criarUtilizador').modal('show');

            document.getElementById("btnCriar").style.display = 'none';
            document.getElementById("btnSave").style.display = 'block';
            document.getElementById("btnDel").style.display = 'block';

            document.getElementById("messageModify").style.display = 'block'
            let Utilizador = await response.json();
            document.getElementById("nomeAdd").value = Utilizador.nome;
            document.getElementById("utilizadorAdd").value = Utilizador.username;
            document.getElementById("emailAdd").value = Utilizador.email;
            document.getElementById("senhaAdd").value = '';
            document.getElementById("repSenhaAdd").value = '';
            if (Utilizador.isAdmin == "Y") {
                document.getElementById("adminAdd").checked = true;
            } else {
                document.getElementById("adminAdd").checked = false;
            }
            document.getElementById("utilizadorId").value = v_userId;

        }
    })
}



// função de criação de novo utilizador
async function addUtilizador() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("nomeAdd").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (document.getElementById("utilizadorAdd").value == "") {
        errForm += "Utilizador é obrigatório.<br>";
    }
    const padrao = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W]).{8,20})/;
    if (!padrao.test(document.getElementById("senhaAdd").value)) {
        errForm += 'A senha tem de ser no minimo 8 caracteres e tem de ter letras maiúsculas, minúsculas, números e um simbolos(Ex.:*[!#$%&?). ';
    } else {
        if (document.getElementById("senhaAdd").value == "") {
            errForm += "Senha é obrigatória.<br>";
        }
        if (document.getElementById("repSenhaAdd").value != document.getElementById("senhaAdd").value) {
            errForm += "As duas senhas não são iguais.<br>";
        }
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        //const url = "http://localhost/api";
		const url = await getUrlNode() + "/api";
		console.log("Criar user: " + url);
        let vIsAdmin;
        if (document.getElementById("adminAdd").checked == true) {
            vIsAdmin = 'Y';
        } else {
            vIsAdmin = 'N';
        }
        fetch(`${url}/registar`, {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            method: "POST",
            body: `nome=${document.getElementById("nomeAdd").value}&email=${document.getElementById("emailAdd").value}&username=${document.getElementById("utilizadorAdd").value}&password=${document.getElementById("senhaAdd").value}&isAdmin=${vIsAdmin}`,
        })
            .then((response) => {
                Swal.fire({
                    icon: "success",
                    title: "Utilizador criado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarUtilizador').modal('hide');
                });
            })
            .catch((error) => {
                console.log(error);
                exit = error;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao criar utilizador.<br>" + error,
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarUtilizador').modal('hide');
                });
            });
    }
}



// função de alterar Utilizador
async function saveUtilizador() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("nomeAdd").value == "") {
        errForm += "Nome é obrigatório.<br>";
    }
    if (document.getElementById("utilizadorAdd").value == "") {
        errForm += "Utilizador é obrigatório.<br>";
    }
    if (document.getElementById("senhaAdd").value != "") {

        const padrao = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W]).{8,20})/;
        if (!padrao.test(document.getElementById("senhaAdd").value)) {
            errForm += 'A senha tem de ser no minimo 8 caracteres e tem de ter letras maiúsculas, minúsculas, números e um simbolos(Ex.:*[!#$%&?). ';
        } else {
            if (document.getElementById("repSenhaAdd").value != document.getElementById("senhaAdd").value) {
                errForm += "As duas senhas não são iguais.<br>";
            }
        }
    }
    if (errForm != "") {
        Swal.fire({
            icon: "error",
            title: errForm,
            showConfirmButton: false,
            timer: 4500,
        });

    } else {
        //const url = "http://localhost/api";
		const url = await getUrlNode() + "/api";

        let vIsAdmin;
        if (document.getElementById("adminAdd").checked == true) {
            vIsAdmin = 'Y';
        } else {
            vIsAdmin = 'N';
        }
		
        // Obter valores
        const nome = document.getElementById("nomeAdd").value;
        const email = document.getElementById("emailAdd").value;
        const username = document.getElementById("utilizadorAdd").value;
        const password = document.getElementById("senhaAdd").value;
        const userId = document.getElementById("utilizadorId").value;

        // Log de debug
        console.log("Dados a enviar:");
        console.log("nome:", nome);
        console.log("email:", email);
        console.log("username:", username);
        console.log("password:", password);
        console.log("isAdmin:", vIsAdmin);
        console.log("userId:", userId);
		
		
        fetch(`${url}/alterar`, {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            method: "PUT",
            body: `nome=${document.getElementById("nomeAdd").value}&email=${document.getElementById("emailAdd").value}` +
                `&username=${document.getElementById("utilizadorAdd").value}&password=${document.getElementById("senhaAdd").value}` +
                `&isAdmin=${vIsAdmin}&userId=${document.getElementById("utilizadorId").value}`,
        })
            .then((response) => {
                Swal.fire({
                    icon: "success",
                    title: "Utilizador alterado com sucesso.",
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarUtilizador').modal('hide');
                });
            })
            .catch((error) => {
                console.log(error);
                exit = error;
                Swal.fire({
                    icon: "error",
                    title: "Erro ao alterar o utilizador.<br>" + error,
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    search(document.getElementById("VSearch").value);
                    $('#criarUtilizador').modal('hide');
                });
            });
    }
}

async function delUtilizador() {
    Swal.fire({
        icon: "error",
        title: "Tem a certeza que quer eliminar este utilizador?",
        showConfirmButton: true,
        showCancelButton: true,
    }).then(async (result) => {
        if (result.isConfirmed) {
            //const url = "http://localhost/api";
			const url = await getUrlNode() + "/api";
            fetch(`${url}/delete/${document.getElementById("utilizadorId").value}`, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                method: "DELETE"
            })
                .then((response) => {
                    Swal.fire({
                        icon: "success",
                        title: "Utilizador eliminado com sucesso.",
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarUtilizador').modal('hide');
                    });
                })
                .catch((error) => {
                    console.log(error);
                    exit = error;
                    Swal.fire({
                        icon: "error",
                        title: "Erro ao eliminar utilizador.<br>" + error,
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        search(document.getElementById("VSearch").value);
                        $('#criarUtilizador').modal('hide');
                    });
                });
        }
    });
}