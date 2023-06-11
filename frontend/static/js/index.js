async function verificarLogin(){
  const token = localStorage.getItem("token")
  if(token != null){
    let fetchLogado = await fetch(
      "http://localhost:8080/user/token",{
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": token
        }
      }
    ).then((response) => response.json())
    const navLogado = document.getElementById("nav-logado");
    navLogado.hidden = false;
    const nomeUsuario = document.getElementById("nome-usuario");
    nomeUsuario.innerText = fetchLogado.name;
    if(fetchLogado.perfil == "ADMINISTRADOR"){
      const administrador = document.getElementById("li-administrador");
      administrador.hidden = false;
    }
    const pathname = window.location.pathname
    if(pathname == '/static/pages/infoconta.html'){
      const name = document.getElementById("username");
      const email = document.getElementById("email");
      const cpf = document.getElementById("cpf");
  
      name.innerText = fetchLogado.name;
      email.innerText = fetchLogado.email;
      cpf.innerText = fetchLogado.cpf;
    }
  }
  else{
    const navNaoLogado = document.getElementById("nav-nao-logado");
    navNaoLogado.hidden = false;
  }
}

function logout(){
  localStorage.removeItem("token");
  window.location.reload();
}