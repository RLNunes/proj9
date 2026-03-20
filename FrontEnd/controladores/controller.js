require("dotenv").config();
//console.log("ACCESS_TOKEN_SECRET:", process.env.ACCESS_TOKEN_SECRET);
//console.log("REFRESH_TOKEN_SECRET:", process.env.REFRESH_TOKEN_SECRET);
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

function getCookie(cookie, name) {
    cookie = ";" + cookie;
    cookie = cookie.split("; ").join(";");
    cookie = cookie.split(" =").join("=");
    cookie = cookie.split(";" + name + "=");
    if (cookie.length < 2) {
        return null;
    }
    else {
        return decodeURIComponent(cookie[1].split(";")[0]);
    }
}

exports.getToken = (req, res) => {
    console.log("A autorizar SERVIÇO...");
    authenticateToken(req, res);
    const vCookies = getCookie(req.headers.cookie, 'isAdmin');
    if (req.status == 'Y') {
        console.log('tenho login feito . . . ');
        return res.send({
            token: process.env.TOKEN_SERVICE,
            isAdmin: vCookies
        });
    }

}

exports.getUrlPayaraDireto = async (req, res) => {
    console.log("a Retirar URL Payara Direto . . . ");
    return res.send({ url: process.env.URL_PAYARA_DIRETO });
}

exports.getUrlNode = async (req, res) => {
    console.log("a Retirar URL Node . . . ");
    return res.send({ url: process.env.URL_NODE });
}

async function authenticateToken(req, res) {
    console.log("A verificar token...");

    const authHeader = req.headers["authorization"];
    const token = authHeader && authHeader.split(" ")[1];
    if (token == null) {
        console.log("Token nula");
        return res.sendStatus(401);
    }
    jwt.verify(token, process.env.ACCESS_TOKEN_SECRET, (err, user) => {
        if (err) {
            return res.sendStatus(403);
        }
        req.status = 'Y';
    });
}

exports.isAdmin = async (req, res) => {
     authenticateToken(req, res);
    return res.status(201).send({
        isAdmin: getCookie(req.headers.cookie, 'isAdmin')
    });
}

// LOGIN - autentica um utilizador
exports.login = async (req, res) => {
    console.log("Autenticação de um utilizador");
	console.log("REQ.BODY:", req.body);
    console.log("REQ.PARAMS:", req.params);
    console.log("REQ.QUERY:", req.query);
    console.log("REQ.HEADERS:", req.headers);
    console.log("REQ.COOKIE HEADER:", req.headers.cookie);
	const urldeteste = `${process.env.URL_PAYARA_LOGIN}`;
	console.log("URL do endpoint:", urldeteste);
    if (!req.body) {
        return res.status(400).send({
            message: "O campo não pode ser vazio!",
        });
    }
    const username = req.body.username;
    console.log('username: ' + username);
    fetch(`${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/exist/` + username, {
        headers: { "token": process.env.TOKEN_SERVICE },
        method: "GET"
    })
        .then(async (response) => {
            const dados = await response.json();
            console.log('user_id: ' + dados.user_id)

            if (dados.user_id != 0) {
                if (await bcrypt.compare(req.body.password, dados.password)) {

                    const user = { username: username };
                    const accessToken = jwt.sign(user, process.env.ACCESS_TOKEN_SECRET);

                    /*  res.setHeader('Access-Control-Allow-Credentials', true);
                      res.setHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
                      res.setHeader('Access-Control-Allow-Origin', 'http://localhost:80'); */
                      // SameSite=None;
                    res.setHeader('Set-Cookie', 'isAdmin=' + dados.isAdmin + '; ');
                    return res.status(201).send({
                        message:
                            "Autenticação com sucesso!",
                        token: accessToken
                    });
                }
                else {
                    return res.status(400).send({ message: "Senha ou o utilizador estão errados!" });
                }
            } else {
                return res.status(400).send({ message: "Senha ou o utilizador estão errados." });
            }
        })
        .catch((error) => {
            console.log(error);
            return res.status(400).send({ message: "Erro ao autenticar.<br>" + error });
        });
};

// REGISTAR - cria um novo utilizador
exports.registar = async (req, res) => {
    console.log("Registar novo utilizador");
    if (!req.body) {
        return res.status(400).send({
            message: "O campo não pode ser vazio!",
        });
    }
	console.log("teste1");
    const salt = await bcrypt.genSalt();
    const auxPassword = req.body.password
    const hashPassword = await bcrypt.hash(auxPassword, salt);
    const email = req.body.email;
    const password = hashPassword;
    const username = req.body.username;
    const isAdmin = req.body.isAdmin;
    const nome = req.body.nome;
    let dd = JSON.stringify(req.body);
    var objUser = {
        nome: nome,
        username: username,
        password: password,
        email: email,
        isAdmin: isAdmin
    };
    var json = JSON.stringify(objUser);
    console.log(auxPassword + ':');
	console.log(`${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/insert`);
    fetch(`${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/insert`, {
        headers: { "token": process.env.TOKEN_SERVICE },
        method: "POST",
        body: json
    })
        .then(async (response) => {
			console.log("teste2");
            return res.status(201).send({
                message:
                    "Utilizador criado com sucesso!",
            });
        })
        .catch((error) => {
            console.log(error);
            return res.status(400).send({ message: "Erro ao criar utilizador" });
        });
};


// ALTERAR - Altera os dados de um utilizador
exports.alterar = async (req, res) => {
    console.log("Alterar utilizador");
    if (!req.body) {
        return res.status(400).send({
            message: "O campo não pode ser vazio!",
        });
    }
    let hashPassword;
    if (req.body.password != '') {
        const salt = await bcrypt.genSalt();
        const auxPassword = req.body.password
        hashPassword = await bcrypt.hash(auxPassword, salt);

    } else {
        hashPassword = '';
    }
    const password = hashPassword;
    const email = req.body.email;
    const username = req.body.username;
    const isAdmin = req.body.isAdmin;
    const nome = req.body.nome;
    var objUser = {
        nome: nome,
        username: username,
        password: password,
        email: email,
        isAdmin: isAdmin
    };
    var json = JSON.stringify(objUser);
    console.log(password + ':');
	
	console.log("URL Payara:", `${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/update/${req.body.userId}`);
	console.log("JSON a enviar ao Payara:", json);
	
    fetch(`${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/update/${req.body.userId}`, {
        headers: { "token": process.env.TOKEN_SERVICE },
        method: "PUT",
        body: json
    })
        .then(async (response) => {
            return res.status(201).send({
                message:
                    "Utilizador alterado com sucesso!",
            });
        })
        .catch((error) => {
            console.log(error);
            return res.status(400).send({ message: "Erro ao alterar utilizador" });
        });
};

// DELETE one - elimina o utilizador com o id recebido
exports.delete = (req, res) => {
    console.log("Eliminar utilizador." + req.params.id);
    if (!req.params) {
        return res.status(400).send({
            message: "O id do utilizador não pode ser vazio!",
        });
    }

    fetch(`${process.env.URL_PAYARA_PROXY}/CircPeticionario/webresources/utilizadores/delete/${req.params.id}`, {
        headers: { "token": process.env.TOKEN_SERVICE },
        method: "DELETE"
    })
        .then(async (response) => {
            return res.status(201).send({
                message:
                    "Utilizador eliminado com sucesso!",
            });
        })
        .catch((error) => {
            console.log(error);
            return res.status(400).send({ message: "Erro ao eliminar o utilizador" });
        });
};