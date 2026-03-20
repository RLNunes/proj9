let vIsAdmin = 'N'

// TOKEN_SERVICE é a chave de API estática usada para autenticar pedidos ao Payara.
// É o mesmo valor configurado no JNDI TOKEN_SERVICE do Payara.
const TOKEN_SERVICE = 'eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY3MDI2ODYzOCwiaWF0IjoxNjcwMjY4NjM4fQ.uygunXd7eT8C0ZXc8Cfqnn-l9zGi3Rg9QWRkIxsThvg';

// Retorna o token de serviço se o utilizador tiver sessão iniciada, ou '' caso contrário.
// A sessão é guardada em localStorage pela função login().
async function getToken() {
  let token = localStorage.token;
  if (token) {
    vIsAdmin = localStorage.isAdmin || 'N';
    return token;
  }
  return '';
}

// Igual a getToken() mas limpa o body se o utilizador não for administrador.
async function getAdminToken() {
  let token = localStorage.token;
  if (token) {
    vIsAdmin = localStorage.isAdmin || 'N';
    if (vIsAdmin !== 'Y') {
      document.getElementById('body').innerHTML = '';
      return '';
    }
    return token;
  }
  document.getElementById('body').innerHTML = '';
  return '';
}

// Devolve o prefixo "/api" para construir URLs relativas para o Payara.
// O Nginx faz o reverse proxy de /api/ para http://circ.peticionario.payara:8080/,
// pelo que /api/CircPeticionario/webresources/... chega corretamente ao Payara.
function getUrlPayaraDireto() {
  return Promise.resolve('/api');
}

// getUrlNode() já não é necessária (Node foi substituído pelo Nginx),
// mas mantém-se por compatibilidade com login.js e utilizador.js.
function getUrlNode() {
  return Promise.resolve('');
}

// Devolve o estado de administrador a partir do localStorage.
async function getIsAdmin() {
  return localStorage.isAdmin || 'N';
}


function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("isAdmin");
  window.location.replace("index.html");
}

async function getMenuAdmin(v_sel, v_list) {
  v_menu = `<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  <a class="navbar-brand" href="#"><img src="./img/Marca_Circuitos_Oceanicos_logo_selo_azul.png" width="50"> </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">`;

  if (await getToken() != '' && vIsAdmin == 'Y') {
    if (v_sel == 'home') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../admin.html">Inicio Admin</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../admin.html">Inicio Admin</a> </li>';
    }
    v_menu += `<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                   Toponimia
                </a>
                <div class="dropdown-menu">
                <a class="dropdown-item" href="../termo.html">Termo</a>
                <a class="dropdown-item" href="../freguesia.html">Freguesia</a>
                <a class="dropdown-item" href="../comarca.html">Comarca</a>
                <a class="dropdown-item" href="../capitania.html">Capitania</a>
            <\li>`;
    if (v_sel == 'registro') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../registro.html">Registro</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../registro.html">Registro</a> </li>';
    }
    v_menu += ` `;
    if (v_sel == 'referencia') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../referencia.html">Referência Documental</a> </li> ';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../referencia.html">Referência Documental</a> </li>';
    }
    /* if (v_sel == 'tipoDiplo') {
       v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../tipoDiplo.html">Tipologia Diplomática</a> </li>';
     } else {
       v_menu += '<li class="nav-item"> <a class="nav-link" href="../tipoDiplo.html">Tipologia Diplomática</a> </li>';
     }*/
    v_menu += `<li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                    Marcadores
                  </a>
                  <div class="dropdown-menu">
                  <a class="dropdown-item" href="../ecoOcup.html">Económico (Ocupação)</a>
                  <a class="dropdown-item" href="../socJur.html">Sócio Jurídico</a>
              <\li>`;

    v_menu += `<li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                Ofícios/Títulos
              </a>
              <div class="dropdown-menu">
              <a class="dropdown-item" href="../ofiTitulo.html">Ofícios/Títulos</a>
              <a class="dropdown-item" href="../agregador.html">Agregadores</a>
          <\li>`;

    v_menu += `<li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                        Secretario/Conselheiro
                      </a>
                      <div class="dropdown-menu">
                      <a class="dropdown-item" href="../secretario.html">Secretário/Conselheiro</a>
                      <a class="dropdown-item" href="../agregadorSecretario.html">Agregadores</a>
                  <\li>`;
    if (v_sel == 'pessoa') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../pessoa.html">Pessoa</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../pessoa.html">Pessoa</a> </li>';
    }

    v_menu += `<li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                Temas
              </a>
              <div class="dropdown-menu">
              <a class="dropdown-item" href="../tema.html">Tema</a>
              <a class="dropdown-item" href="../agregadorTema.html">Agregadores</a>
          <\li>`;
    if (v_sel == 'palChave') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../palChave.html">Palavra-Chave</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../palChave.html">Palavra-Chave</a> </li>';
    }
    if (v_sel == 'util') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../utilizador.html">Utilizador</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../utilizador.html">Utilizador</a> </li>';
    }

    v_menu += ` </ul>`;
    if (v_list == 1) {
      v_menu += `<form class="d-flex">
        <input id="VSearch" class="form-control me-2" type="search" placeholder="Procurar" onkeypress="onEvent(event)" aria-label="Procurar">
        <button type="button" class="btn btn-outline-success" onclick="search(document.getElementById('VSearch').value);">Procurar</button>
      </form> `;
    }
  } else {
    document.getElementById('body').innerHTML = '';
  }
  v_menu += ` </div> `;
  v_menu += `  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="flex-grow: 0;">
                  <ul class="navbar-nav mr-auto">
                      <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                    <img
                      src="../img/userLogin.png"
                      class="rounded-circle"
                      height="85"
                      alt="Black and White Portrait of a Man"
                      loading="lazy"
                    />
                    </a>
                    <div class="dropdown-menu">
                    <a class="dropdown-item" href="../">Página Inicial</a> 
                    <a class="dropdown-item" href="../consultaNumero.html">Consulta</a>                   
                    <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#" onclick="logout();">logout</a>
                    </div>
                    </li>
                </ul></div>`
  v_menu += `</div>
</nav>`;


  return v_menu;

}


async function getMenu(v_sel, v_list) {
  console.log("f: "+sessionStorage.getItem("v_page")+"  -->> "+v_sel)
  if (sessionStorage.getItem("v_page")!=v_sel){
    sessionStorage.setItem("v_page",v_sel);
    sessionStorage.setItem("v_pageNum",1);
  }
  v_menu = `<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  <a class="navbar-brand" href="./index.html"><img src="./img/Marca_Circuitos_Oceanicos_logo_selo_vermelho.png" width="50"> </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"  aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">`;
  if (await getToken() != '') {
    if (v_sel == 'home') {
      v_menu += '<li class="nav-item active" id="home"> <a class="nav-link active" aria-current="page" href="../index.html">Inicio</a> </li>';
    } else {
      v_menu += '<li class="nav-item" id="home"> <a class="nav-link" href="../index.html">Inicio</a> </li>';
    }
    if (v_sel == 'prov') {
      v_menu += '<li class="nav-item active" id="prov"> <a class="nav-link active" aria-current="page" href="../provocacao.html">Provocação</a> </li>';
    } else {
      v_menu += '<li class="nav-item" id="prov"> <a class="nav-link" href="../provocacao.html">Provocação</a> </li>';
    }
    if (v_sel == 'manda') {
      v_menu += '<li class="nav-item active" id="manda"> <a class="nav-link active" aria-current="page" href="../mandado.html">Mandado</a> </li>';
    } else {
      v_menu += '<li class="nav-item" id="manda"> <a class="nav-link" href="../mandado.html">Mandado</a> </li>';
    }
    v_menu += ` `;
    if (v_sel == 'cons') {
      v_menu += '<li class="nav-item active" id="cons"> <a class="nav-link active" aria-current="page" href="../consulta.html">Consulta</a> </li> ';
    } else {
      v_menu += '<li class="nav-item" id="cons"> <a class="nav-link" href="../consulta.html">Consulta</a> </li>';
    }
    if (v_sel == 'ultra') {
      v_menu += '<li class="nav-item active" id="ultra"> <a class="nav-link active" aria-current="page" href="../ultramar.html">Ultramar</a> </li>';
    } else {
      v_menu += '<li class="nav-item" id="ultra"> <a class="nav-link" href="../ultramar.html">Ultramar</a> </li>';
    }
    v_menu += ` `;
    if (v_sel == 'res') {
      v_menu += '<li class="nav-item active" id="res"> <a class="nav-link active" aria-current="page" href="../resposta.html">Respostas</a> </li> ';
    } else {
      v_menu += '<li class="nav-item" id="res"> <a class="nav-link" href="../resposta.html">Respostas</a> </li>';
    }

    v_menu += ` </ul>`;
    if (v_list == 1) {
      v_menu += `<form class="d-flex">
        <input id="VSearch" class="form-control me-2" type="search" placeholder="Procurar" onkeypress="onEvent(event)" aria-label="Procurar">
        <button type="button" class="btn btn-outline-success" onclick="search(document.getElementById('VSearch').value);" id="btnProcurar">Procurar</button>
      </form> `;

    }
  } else {
    document.getElementById('body').innerHTML = '';
  }
  v_menu += ` </div> `;
  v_menu += `  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="flex-grow: 0;">
                <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">`;
  v_menu += ` <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
              <img
                src="../img/userLogin.png"
                class="rounded-circle"
                height="85"
                alt="Black and White Portrait of a Man"
                loading="lazy"
              />
            </a>
            <div class="dropdown-menu">`;
  if (v_sel == 'home' && await getToken() == '') {
    v_menu += `   <a class="dropdown-item" href="#" onclick="$('#modalLogin').modal('show');">Entrar</a>`;
  } else {
    if (await getToken() != '') {
      if (vIsAdmin == 'Y') {
        v_menu += `  <a class="dropdown-item" href="../admin.html">Administração</a>`;
        v_menu += `  <a class="dropdown-item" href="../consultaNumero.html">Consulta</a>`;
      }
      v_menu += `  <a class="dropdown-item" href="#" onclick="logout();">Sair</a>`;
    }
  }

  v_menu += ` </div>
              </li>
              </ul></div>`
  v_menu += `</div>
</nav>`;

  return v_menu;
}


async function getMenuConsulta(v_sel, v_list) {
  v_menu = `<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  <a class="navbar-brand" href="#"><img src="./img/Marca_Circuitos_Oceanicos_logo_selo_verde.png" width="50"> </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">`;

  if (await getToken() != '' && vIsAdmin == 'Y') {

    if (v_sel == 'Numeros') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../consultaNumero.html">Home</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../consultaNumero.html">Home</a> </li>';
    }
    if (v_sel == 'Pessoa') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../consultaPessoa.html">Pessoa</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../consultaPessoa.html">Pessoa</a> </li>';
    }
    if (v_sel == 'Tema') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../consultaTema.html">Tema</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../consultaTema.html">Tema</a> </li>';
    }
    if (v_sel == 'PalChave') {
      v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../consultaPalChave.html">Palavra-Chave</a> </li>';
    } else {
      v_menu += '<li class="nav-item"> <a class="nav-link" href="../consultaPalChave.html">Palavra-Chave</a> </li>';
    }

    v_menu += ` </ul>`;
    if (v_list == 1) {
      v_menu += `<form class="d-flex">
        <input id="VSearch" class="form-control me-2" type="search" placeholder="Procurar" onkeypress="onEvent(event)" aria-label="Procurar">
        <button type="button" class="btn btn-outline-success" onclick="search(document.getElementById('VSearch').value);">Procurar</button>
      </form> `;
    }
  } else {
    document.getElementById('body').innerHTML = '';
  }
  v_menu += ` </div> `;
  v_menu += `  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="flex-grow: 0;">
                  <ul class="navbar-nav mr-auto">
                      <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                    <img
                      src="../img/userLogin.png"
                      class="rounded-circle"
                      height="85"
                      alt="Black and White Portrait of a Man"
                      loading="lazy"
                    />
                    </a>
                    <div class="dropdown-menu">
                    <a class="dropdown-item" href="../">Página Inicial</a>  
                    <a class="dropdown-item" href="../admin.html">Administração</a>                  
                    <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#" onclick="logout();">logout</a>
                    </div>
                    </li>
                </ul></div>`
  v_menu += `</div>
</nav>`;


  return v_menu;

}



function onEvent(event) {
  if (event.key === "Enter") {
    event.preventDefault();
    search(document.getElementById("VSearch").value);
  }
};