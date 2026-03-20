

function getMenuAdmin(v_sel, v_list) {

  v_menu = `<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
  <a class="navbar-brand" href="#"><img src="./img/Marca_Circuitos_Oceanicos_logo_selo_azul.png" width="50"> </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">`;
  if (v_sel == 'home') {
    v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../index.html">Home</a> </li>';
  } else {
    v_menu += '<li class="nav-item"> <a class="nav-link" href="../index.html">Home</a> </li>';
  }
  if (v_sel == 'topo') {
    v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="#.html">Toponimia</a> </li>';
  } else {
    v_menu += '<li class="nav-item"> <a class="nav-link" href="#">Toponimia</a> </li>';
  }
  if (v_sel == 'registro') {
    v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../resgistro.html">Registro</a> </li>';
  } else {
    v_menu += '<li class="nav-item"> <a class="nav-link" href="../registro.html">Registro</a> </li>';
  }
  v_menu += ` `;
  if (v_sel == 'referencia') {
    v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../referencia.html">Referência Documental</a> </li> ';
  } else {
    v_menu += '<li class="nav-item"> <a class="nav-link" href="../referencia.html">Referência Documental</a> </li>';
  }
  if (v_sel == 'tipoDiplo') {
    v_menu += '<li class="nav-item active"> <a class="nav-link active" aria-current="page" href="../tipoDiplo.html">Tipologia Diplomática</a> </li>';
  } else {
    v_menu += '<li class="nav-item"> <a class="nav-link" href="../tipoDiplo.html">Tipologia Diplomática</a> </li>';
  }
  /*    v_menu += `
      <li class="nav-item">
        <a class="nav-link" href="#">Provocação</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
          Link
        </a>
        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
          <li><a class="dropdown-item" href="#">Action</a></li>
          <li><a class="dropdown-item" href="#">Another action</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled">Link</a>
      </li>`; */

  v_menu += ` </ul>`;
  if (v_list == 1) {
    v_menu += `<form class="d-flex">
        <input id="VSearch" class="form-control me-2" type="search" placeholder="Procurar" aria-label="Procurar">
        <button type="button" class="btn btn-outline-success" onclick="search(document.getElementById('VSearch').value);">Procurar</button>
      </form> `;
  }
  v_menu += ` </div> `;
  /* if (v_sel == 'home'){
    v_menu += ` <button
                  type="button"
                  class="btn btn-primary mx-auto d-block mt-5"
                  id="btnModalLogin" onclick="$('#modalLogin').modal('show');">
                  Fazer login
              </button>`
  } */
  v_menu += `<ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
              <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
              <img
                src="../img/userLogin.png"
                class="rounded-circle"
                height="85"
                alt="Black and White Portrait of a Man"
                loading="lazy"
              />
            </a>
                <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                  <li><a class="dropdown-item" href="#" onclick="$('#modalLogin').modal('show');">Login</a></li>
                  <li><a class="dropdown-item" href="../admin.html">Administração</a></li>
                </ul>
              </li>
              </ul>`
  v_menu += `</div>
</nav>`;
  return v_menu;
}
