
const express = require('express');
const cors = require('cors');

const app = express();

// Configurar o middleware CORS para permitir solicitações de todas as origens
app.use(cors());

app.use(express.json()); // Faz o parse (validação e interpretação) de solicitações do tipo application/json
app.use(express.urlencoded({ extended: true })); // Faz o parse do conteúdo tipo application/x-www-form-urlencoded

require("./rotas/rotas")(app);

require("dotenv").config();
const PORT =  process.env.PORT;

app.listen(PORT, () => console.log(`server running on PORT ${PORT}`))
// const user = require("./public/js/utilizadores");
// const resp = require("./public/js/respostas");

app.use(express.static('public'))