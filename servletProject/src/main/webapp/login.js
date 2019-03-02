let submit;
let username;
let password;
let login_message;
window.addEventListener('load', ()=> {
submit = document.getElementById('login-form');

//submit.addEventListener('submit', formEvent);
login_message = document.getElementById("login_message");

});

function formEvent(e) {
	e.preventDefault();
    username = submit.username.value;
    password = submit.password.value;
    console.log(submit);
    let response;
    
    let req = new XMLHttpRequest();
    req.addEventListener('readystatechange', ()=>{
        if (req.readyState === 4)
        {
        	try {
        		let obj = JSON.parse(req.response);
        		login_message.innerHTML = obj.message;
        	} catch(err)
            	{
            	console.log(req.response);
            	document.write(req.response);
            	}
        }
    });
    req.open('post',"http://localhost:8080/Project-1/login");

    req.send(JSON.stringify(new data(password, username)));
    
    //req.send(submit.password.value);
    // req.send(`password=${pass}`);
    // req.send(`username=${username}`);
    // req.send('bullshit');
    //e.preventDefault();
    
    return false;
}
function data(pass, username)
{
    this.password = pass;
    this.username = username;
}