
// função de criação de novo utilizador
async function login() {
    var errForm = "";
    // validação do preenchimento dos campos
    if (document.getElementById("usernameLogin").value == "") {
        errForm += "Utilizador é obrigatório.<br>";
    }
    if (document.getElementById("senhaLogin").value == "") {
        errForm += "Senha é obrigatório.<br>";
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
		console.log('teste: ' + url);
		const bodyContent = `username=${document.getElementById("usernameLogin").value}&password=${document.getElementById("senhaLogin").value}`;
        try {
			//console.log(`${url}/login`);
            fetch(`${url}/login`, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                credentials: "include",
                method: "POST",
                body: `username=${document.getElementById("usernameLogin").value}&password=${document.getElementById("senhaLogin").value}`,
            })
                .then(async (response) => {
                    result = await response.json();
                    if (!response.ok) {
                        Swal.fire({
                            icon: "error",
                            title: result.message,
                            showConfirmButton: false,
                            timer: 1500,
                        }).then(function () {
                            $('#modalLogin').modal('hide');
                        });
                        // statReg.innerHTML = response.statusText;
                    } else {
                        localStorage.setItem("token", result.token);
                        $('#modalLogin').modal('hide');
                        Swal.fire({
                            icon: "success",
                            title: result.message,
                            showConfirmButton: false,
                            timer: 1500,
                        }).then(function () {
                            window.location.replace("index.html");
                        });
                    }
                })
                .catch((error) => {
                    //console.log(error);
                    exit = error;
                    Swal.fire({
                        icon: "error",
                        title: "Erro na autenticação.<br>" + error,
                        showConfirmButton: false,
                        timer: 1500,
                    }).then(function () {
                        $('#modalLogin').modal('hide');
                    });
                });
        }
        catch {
            Swal.fire({
                icon: "error",
                title: "Erro na autenticação.<br>" + error,
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalLogin').modal('hide');
            });

        }
    }
}
