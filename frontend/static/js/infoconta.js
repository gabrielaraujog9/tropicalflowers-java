async function preencherInfosUser(){
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

    const name = document.getElementById("username");
    const email = document.getElementById("email");
    const cpf = document.getElementById("cpf");

    name.innerText = fetchLogado.name;
    email.innerText = fetchLogado.email;
    cpf.innerText = fetchLogado.cpf;
  }
}