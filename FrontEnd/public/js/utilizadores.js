require("dotenv").config();

async function getUtilizadores(){
    const urlBase = v_url + "/CircPeticionario/webresources/utilizadores/all";
    let texto = "";
    var myHeaders = new Headers();
    //myHeaders.append("token","eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY3MDI2ODYzOCwiaWF0IjoxNjcwMjY4NjM4fQ.uygunXd7eT8C0ZXc8Cfqnn-l9zGi3Rg9QWRkIxsThvg");
	myHeaders.append("token",process.env.TOKEN_SERVICE);
    var myInit = { method: "GET", headers: myHeaders }

    var myRequest = new Request(`${urlBase}`, myInit);

    await fetch(myRequest).then(async function (response){
        if (!response.ok){
            console.log("Erro ao fdevolver a lista de utilizadortes");
        }else{
            utilizadores = await response.json();
            console.log(utilizadores);
        }
    }
    )
}

