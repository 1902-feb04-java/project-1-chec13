let submit;
let username;
let password;
window.addEventListener('load', ()=> {
submit = document.getElementById('login-form');
submit.addEventListener('submit', formEvent);
console.log(submit.getElementsByTagName("input"));

});

function formEvent(e) {
    
    e.preventDefault();
}