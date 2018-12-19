
function validarSenha(){
	
	    let senha = document.getElementById('senha-cadastro').value;
	    let senhaConfirnada = document.getElementById('senha-confirma-cadastro').value;
	    
	    console.log('validar');
	    
	if (senha.value == senhaConfirnada.value) {
	    console.log('validar If');
	    document.getElementById('senha-confirma-cadastro').style.borderColor = "green";
	    
	} if (senha.value != senhaConfirnada.value){
	    document.getElementById('senha-confirma-cadastro').style.borderColor = "red";
	}

}

function cadastro(){
	let http = new XMLHttpRequest();
    http.open('POST','/usuario');
    http.setRequestHeader('Content-Type','application/json');
  

    let nome = document.getElementById('nome-cadastro').value;
    let email = document.getElementById('email-cadastro').value;
    let senha = document.getElementById('senha-cadastro').value;
    let telefone = document.getElementById('telefone-cadastro').value;
    
    http.onload = function(){
		 
			 if(this.status == 200){
				 localStorage.setItem("nome", nome);
				 localStorage.setItem("email", email);
				 
				 document.location.href = "/postagem.html";
				  		 	 
			 }
		 }
    
    
   let usuario = {
       'nomeDeUsuario':`${nome}`,
       'email':`${email}`,
       'senha':`${senha}`,
       'telefone':`${telefone}`
   };

   http.onerro = () => alert('ERRO');
   http.send(JSON.stringify(usuario));

  
}

function logar()
{
	
	let http = new XMLHttpRequest();
    http.open('POST','/usuario/logar');
    http.setRequestHeader('Content-Type','application/json');
    
    let email = document.getElementById('emaillogar').value;
    let senha = document.getElementById('senhalogar').value;
    http.onload = function(){
		 
		 if(this.status == 200){
			
			 window.location.href = "http://localhost:8082/postagem.html";
			 	 	 
		 }
	 }

	let usuario = {
	  'email':`${email}`,
	  'senha':`${senha}`,
	 
	};
	
	http.onerro = () => alert('ERRO');
	http.send(JSON.stringify(usuario));
}