let http = new XMLHttpRequest;
let idUser;
let recebe;
function savePost(){


	http.open('POST','/usuario/'+ idUser +'/savePost');
	http.setRequestHeader('Content-Type', 'application/json');

	let descricao = document.getElementById('descricao-post').value;

	let postagem = {
			'descricao':`${descricao}`

	};

	http.onerro = () => alert('ERRO');	
	http.send(JSON.stringify(postagem));

	listarPostagens();
}

function buscarPorEmail(){
	let email = localStorage.getItem("email");
	http.open('GET','/usuario/buscarEmail/' + email);
	http.onload = function(){

		if(this.status == 200){
			recebe = JSON.parse(this.responseText);
			idUser =recebe.content[0].id;
			savePost();
		}
	}
	http.onerro = () => alert('ERRO');
	http.send();
}

function listarPostagens(){
	document.getElementById('card').innerHTML =""; 
	http.open('GET','/postagem');
	http.onload = function(){

		if(this.status == 200){
		let	posters = JSON.parse(this.responseText);
			console.log(posters);
        let r = posters.content.length;
			for (var i = 0; i < posters.content.length; i++) {
				document.getElementById('card').innerHTML += "<div class=\"card\" style=\"width: 18rem;\">"
				+"<div class=\"card-body\">"
				+"<h5 class=\"card-title\">Postagem</h5>"
				+"<p class=\"card-text\">"+ posters.content[i].descricao +"</p>"
				+"</div>"
				+"</div>"				
			}

		}
	}
	http.onerro = () => alert('ERRO');
	http.send();
}
function logar(){
	
	document.location="usuario.html";
}
