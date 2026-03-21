
// Autenticação do utilizador via Payara (sem Node.js).
// O Nginx faz o proxy de /api/ → Payara, pelo que não há pedidos cross-origin.

// Alias para bcryptjs (a lib expõe-se como dcodeIO.bcrypt)
const bcrypt = dcodeIO.bcrypt;

async function login() {
    var errForm = "";
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
        const username = document.getElementById("usernameLogin").value;
        const password = document.getElementById("senhaLogin").value;
        // Verificar se o utilizador existe no Payara (devolve o hash da password e isAdmin)
        const existUrl = '/api/CircPeticionario/webresources/utilizadores/exist/' + encodeURIComponent(username);
        try {
            const response = await fetch(existUrl, {
                method: 'GET',
                headers: { 'token': TOKEN_SERVICE }
            });
            if (!response.ok) {
                throw new Error('Utilizador não encontrado.');
            }
            const dados = await response.json();
            if (!dados.user_id || dados.user_id === 0) {
                throw new Error('Utilizador ou senha incorretos.');
            }
            // Comparar a password introduzida com o hash bcrypt guardado no Payara
            const passwordOk = await bcrypt.compare(password, dados.password);
            if (!passwordOk) {
                throw new Error('Utilizador ou senha incorretos.');
            }
            // Guardar sessão no localStorage
            localStorage.setItem('token', TOKEN_SERVICE);
            localStorage.setItem('isAdmin', dados.isAdmin || 'N');
            $('#modalLogin').modal('hide');
            Swal.fire({
                icon: 'success',
                title: 'Autenticação com sucesso!',
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                window.location.replace('index.html');
            });
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: error.message || 'Erro na autenticação.',
                showConfirmButton: false,
                timer: 1500,
            }).then(function () {
                $('#modalLogin').modal('hide');
            });
        }
    }
}

