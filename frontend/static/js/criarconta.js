async function criarConta(){
  const nameInput = document.getElementById("name")
  const cpfInput = document.getElementById("cpf")
  const emailInput = document.getElementById("email")
  const passwordInput = document.getElementById("password")

  
  const bodyRequest = {
    name: nameInput.value,
    email: emailInput.value,
    cpf: cpfInput.value,
    password: passwordInput.value
  }

  debugger
  let fetchCreate = await fetch(
    "http://localhost:8080/cliente",{
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bodyRequest)
    }
  ).then((response) => response.json())

  if(fetchCreate.message == "Usuário já cadastrado!"){
    alert(fetchCreate.message)
    nameInput.value = ""
    emailInput.value = ""
    cpfInput.value = ""
    passwordInput.value = ""
  }
  else if(fetchCreate.message == "Usuário criado com sucesso!"){
    const bodyRequestLogin = {
      email: emailInput.value,
      password: passwordInput.value
    }
    let fetchLogin = await fetch(
      "http://localhost:8080/cliente/login",{
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(bodyRequestLogin)
      }
    ).then((response) => response.json())
    if(fetchLogin.token == "Bad credentials"){
      alert("Credenciais incorretas")
      emailInput.value = "";
      passwordInput.value = "";
    }
    else{
      localStorage.setItem("token", fetchLogin.token)
      window.open("http://localhost:5500/", "_self")
    }
  }

 
}