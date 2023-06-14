async function onLoadProduto(){
  const id = localStorage.getItem("produtoId")
  const produto = await fetch(`http://localhost:8080/product/${id}`,{
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }
  ).then((response) => response.json())
  const imagemProduto = document.getElementById("imagem-produto")
  imagemProduto.setAttribute("src", produto.image)

  const nomeProduto = document.getElementById("nome-produto")
  nomeProduto.innerText = produto.name

  const descricaoProduto = document.getElementById("descricao-produto")
  descricaoProduto.innerText = produto.description
}