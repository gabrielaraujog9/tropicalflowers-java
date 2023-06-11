async function efetuarLogin(){
  verificarLogin()
  const emailInput = document.getElementById("username")
  const passwordInput = document.getElementById("password")

  const bodyRequest = {
    email: emailInput.value,
    password: passwordInput.value
  }
  debugger
  let fetchLogin = await fetch(
    "http://localhost:8080/user/login",{
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bodyRequest)
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

async function entrarNaPaginaCriacao(){
  window.open("http://localhost:5500/static/pages/criarconta.html", "_self")
}

async function verificarLogin(){
  const token = localStorage.getItem("token")
  if(token != null){
    window.open("http://localhost:5500/", "_self")
  }
  return
}
