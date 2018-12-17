let http = new XMLHttpRequest;
let idUser;
let recebe;
function savePost(){


	console.log(idUser);
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
	console.log("blz");
	let email = localStorage.getItem("email");
	http.open('GET','/usuario/buscarEmail/' + email);
	http.onload = function(){

		if(this.status == 200){
			recebe = JSON.parse(this.responseText);
			console.log(recebe.content[0].id);
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
				console.log(posters);
				document.getElementById('card').innerHTML += "<div class=\"card\" style=\"width: 18rem;\">"
				+"<div class=\"card-body\">"
				+"<h5 class=\"card-title\">Card title</h5>"
				+"<h6 class=\"card-subtitle mb-2 text-muted\">Card subtitle</h6>"
				+"<p class=\"card-text\">"+ posters.content[i].descricao +"</p>"
				+"</div>"
				+"</div>"				
			}

		}
	}
	http.onerro = () => alert('ERRO');
	http.send();
}
