async function secaoRender(){
 loading(true);
  const opcaoAtiva = document.querySelector(".active")
  const opcaoAtual = opcaoAtiva.innerText
  const linhaHeaderTable = document.getElementById("table-linha-secao");
  const tableBody = document.getElementById("table-body");
  linhaHeaderTable.innerHTML = "";
  tableBody.innerHTML = "";

  const titleSecao = document.getElementById("title-secao")
  const buttonSecao = document.getElementById("button-secao")
  switch(opcaoAtual){
    case "Usuários":
      titleSecao.innerText = "Usuários"
      buttonSecao.innerText = "Criar usuário"
      buttonSecao.setAttribute("onclick", "formCadastro('cadastro-usuario')")
      await renderTable("http://localhost:8080/user")
      break;
    case "Produtos":
      titleSecao.innerText = "Produtos"
      buttonSecao.innerText = "Criar produto"
      buttonSecao.setAttribute("onclick", "formCadastro('cadastro-produto')")
      await renderTable("http://localhost:8080/product")
      break;
  }
  loading(false);
}
function formCadastro(idElemento){
  const form = document.getElementById(idElemento)
  debugger
  if(form.classList.contains("visually-hidden")){
    form.classList.add("visible")
    form.classList.remove("visually-hidden")
  }else{
    form.classList.remove("visible")
    form.classList.add("visually-hidden")
  }

}
async function renderTable(urlRequest){
  const token = localStorage.getItem("token");
      if(token == null){
        alert("Você precisa efetuar o login!")
        window.open("http://localhost:5500/static/pages/login.html","_self")
      }
      const dados = await fetch(urlRequest,{
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Authorization": token
          }
        }
      ).then((response) => response.json())
      if(dados.length > 0){

        const keys = Object.keys(dados[0])
        keys.unshift("#")
        keys.push("Excluir")
        keys.push("Atualizar")
        const linhaHeaderTable = document.getElementById("table-linha-secao");
        const tableBody = document.getElementById("table-body");

        for(let key of keys){
          if(key == "image"){
            continue
          }
          linhaHeaderTable.appendChild(createTH(key.toUpperCase()))
        }
        debugger
        for(let i = 0; i <  dados.length; i++){
          let tr = createTR(i, dados[i])
          
          tableBody.appendChild(tr)
        }
      }
}
function createTR(index, value){
  const tr = document.createElement("tr");
  const firstTH = document.createElement("th");
  firstTH.innerText = index + 1;
  firstTH.setAttribute("scope", "row")
  tr.appendChild(firstTH)
  for(let key in value){
    let td;
    debugger
    if(key == "id"){
      td = createTD(value[key])
    }
    else if(key == "createdAt" || key == "updatedAt"){
      td = createTD(value[key].split("T")[0])
    }
    else if(key == "image"){
      continue
    }
    else if(key == "price"){
      td = createTD("R$ " + value[key])
    }
    else{
      td = createTD(value[key])
    }
    tr.appendChild(td)
  }
  const iconDelete = document.createElement("i")
  iconDelete.classList.add("bi")
  iconDelete.classList.add('bi-trash')
  iconDelete.style.cursor = "pointer"
  iconDelete.setAttribute("onclick", "excluir(this)")
  tr.appendChild(createTD(iconDelete))

  const iconEdit = document.createElement("i")
  iconEdit.classList.add("bi")
  iconEdit.classList.add('bi-pencil-square')
  iconEdit.style.cursor = "pointer"
  iconEdit.setAttribute("onclick", "atualizar(this)")
  tr.appendChild(createTD(iconEdit))

  return tr
}
function createTD(value){
  const td = document.createElement("td");
  td.append(value)
  td.style.whiteSpace = "nowrap"
  return td
}

function createTH(key){
  const th = document.createElement("th");

  th.innerText = key;
  th.setAttribute("scope", "col")

  return th
}

function chanceActice(element){
  const btnSidebar = document.getElementsByClassName("btn-sidebar")

  for(let a of btnSidebar){
    a.classList.remove("active")
  }
  element.classList.add("active")

}

async function onLoadUser(){
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
    const username = document.getElementById("username")
    username.innerText = fetchLogado.name
  }
  
}

function loading(hidden){
  debugger
  const loading = document.getElementById("loading")
  if(hidden){
    loading.classList.add("visible")
    loading.classList.remove("visually-hidden")
  }else{
    loading.classList.remove("visible")
    loading.classList.add("visually-hidden")
  }
}

async function cadastrarUsuario(){
  loading(true)
  const token = localStorage.getItem("token")
  const nameInput = document.getElementById("name")
  const cpfInput = document.getElementById("cpf")
  const emailInput = document.getElementById("email")
  const passwordInput = document.getElementById("password")
  const roleOption = document.getElementById("role")
  
  const bodyRequest = {
    name: nameInput.value,
    email: emailInput.value,
    cpf: cpfInput.value,
    password: passwordInput.value
  }

  let fetchCreate
  if(roleOption.value == "CLIENTE"){
    fetchCreate = await fetch(
      "http://localhost:8080/cliente",{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(bodyRequest)
      }
    ).then((response) => response.json())
  }
  else if(roleOption.value == "ADMINISTRADOR"){
    fetchCreate = await fetch(
      "http://localhost:8080/administrador",{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": token
        },
        body: JSON.stringify(bodyRequest)
      }
    ).then((response) => response.json())
  }
  alert(fetchCreate.message)
  nameInput.value = ""
  emailInput.value = ""
  cpfInput.value = ""
  passwordInput.value = ""
  await secaoRender()
  loading(false)
}

async function cadastrarProduto(){
  loading(true)
  const token = localStorage.getItem("token")
  const nameInput = document.getElementById("name-produto")
  const descriptionInput = document.getElementById("description")
  const priceInput = document.getElementById("price")
  const stockInput = document.getElementById("stock")
  const imgInput = document.getElementById("formFile").files[0]
  var reader = new FileReader();
  reader.onloadend = async function () {
    debugger
    var base64 = reader.result
    const bodyRequest = {
      name: nameInput.value,
      description: descriptionInput.value,
      image: base64,
      price: priceInput.value,
      stock: stockInput.value
    }
    let fetchCreate = await fetch(
      "http://localhost:8080/product",{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": token
        },
        body: JSON.stringify(bodyRequest)
      }
    ).then((response) => response.json())

    alert(fetchCreate.message)
    nameInput.value = ""
    descriptionInput.value = ""
    priceInput.value = ""
    stockInput.value = ""
    imgInput = ""
    
    
  }
  reader.readAsDataURL(imgInput);
  await secaoRender()
  loading(false)
}
async function excluir(element){
  loading(true)
  const id = element.parentElement.parentElement.getElementsByTagName("td")[0].innerText
  const opcaoAtiva = document.querySelector(".active")
  const opcaoAtual = opcaoAtiva.innerText
  switch(opcaoAtual){
    case "Usuários":
      await fetchExcluir(`http://localhost:8080/user/${id}`)
      break;
    case "Produtos":
      await fetchExcluir(`http://localhost:8080/product/${id}`)
      break;
  }
  loading(false)
}
async function fetchExcluir(url){
  const token = localStorage.getItem("token")
  let fetchExcluir = await fetch(
    url,{
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": token
      }
    }
  ).then((response) => response.json())
  alert(fetchExcluir.message)
  await secaoRender()
}