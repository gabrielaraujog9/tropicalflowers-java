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

async function listagemDeProdutosPorPreco(){
  const produtos = await fetch("http://localhost:8080/product",{
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }
  ).then((response) => response.json())

  const produtosMaisBaratos = produtos.sort((x, y) => {
    return x.price - y.price
  })

  const cardOfertas = document.getElementsByClassName("card-melhores-ofertas")
  const titleOfertas = document.getElementsByClassName("nome-melhores-ofertas")
  const priceOfertas = document.getElementsByClassName("price-melhores-ofertas")

  for(let i = 0; i < 3; i++){
    cardOfertas[i].setAttribute("id", produtosMaisBaratos[i].id)
    cardOfertas[i].setAttribute("onclick", "abrirProduto(this.getAttribute('id'))")
    cardOfertas[i].style.backgroundImage = `url(${produtosMaisBaratos[i].image})`;
    cardOfertas[i].style.cursor = "pointer"
    titleOfertas[i].innerText = produtosMaisBaratos[i].name
    priceOfertas[i].innerText = `R$ ${produtosMaisBaratos[i].price}`
  }
}

async function abrirProduto(id){
  localStorage.removeItem("produtoId")
  localStorage.setItem("produtoId", id)
  window.open("http://localhost:5500/static/pages/produto.html", "_self")
}