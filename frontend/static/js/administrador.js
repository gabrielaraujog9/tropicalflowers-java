async function secaoRender(){
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
      renderTable("http://localhost:8080/user")
      break;
    case "Produtos":
      titleSecao.innerText = "Produtos"
      buttonSecao.innerText = "Criar produto"
      renderTable("http://localhost:8080/product")
      break;
  }
}

function createTR(index, value){
  const tr = document.createElement("tr");
  const firstTH = document.createElement("th");
  firstTH.innerText = index + 1;
  firstTH.setAttribute("scope", "row")
  tr.appendChild(firstTH)
  for(let key in value){
    
    const td = createTD(value[key])
    tr.appendChild(td)
  }

  return tr
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
        const linhaHeaderTable = document.getElementById("table-linha-secao");
        const tableBody = document.getElementById("table-body");

        for(let key of keys){
          linhaHeaderTable.appendChild(createTH(key.toUpperCase()))
        }
        debugger
        for(let i = 0; i <  dados.length; i++){
          let tr = createTR(i, dados[i])
          
          tableBody.appendChild(tr)
        }
      }
}
function createTD(value){
  const td = document.createElement("td");
  td.innerText = value
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

