module.exports = app => {
    const controlador = require("../controladores/controller.js");

    var router = require("express").Router();

    // retira token para os serviços
    router.get("/getToken/", controlador.getToken);
	
    // retira url para os serviços
    router.get("/getUrlPayaraDireto/", controlador.getUrlPayaraDireto);
	
	// retira url2 para os serviços
    router.get("/getUrlNode/", controlador.getUrlNode);

    //  Verificar se utilizador e Administrador
    router.get("/isAdmin/", controlador.isAdmin);
    
    // Login 
    router.post("/login/", controlador.login);

    // Cria um novo utilizador
    router.post("/registar", controlador.registar);

    // Cria um novo utilizador
    router.put("/alterar", controlador.alterar);

    // Elimina um  utilizador
    router.delete("/delete/:id", controlador.delete);
    
    app.use('/api', router);
  };