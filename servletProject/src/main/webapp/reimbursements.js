let profileBtn;
let reimbursementBtn;
let logout;
let profileDiv;
let reimbursementsDiv;
let reimbursementsTable;
let requests = [];
let statusArray = ["Approved", "Pending", "Denied"];
window.onload =  function() {

    profileDiv = document.getElementById("profile-div");
    reimbursementsDiv = document.getElementById("reimbursement-div");
    profileBtn = document.getElementById("profileBtn");
    reimbursementBtn = document.getElementById("reimbursementsBtn");
    logout = document.getElementById("logoutBtn");
    reimbursementsTable = document.getElementById("reimbursement-search-table-div");

    profileBtn.addEventListener("click", ()=>
    {
        headerButtonPressed(0);
    });
    reimbursementBtn.addEventListener("click", ()=>{
        headerButtonPressed(1);
    });
    logout.addEventListener('click',()=>{

    });
    setProfile("John", "Vasgird", "jvasgird", "jvasgird@gmail.com", "1778 Bandoni ave",
    "","CA", "94580", "3/1/2019");
    let r = new Reimbursement("130.00", "John Vasgird", "none", "Pending", "please approve", "3/1/2019", "3/1/2019");
    for (let x = 0; x < 99; x++)
    {
        addElementToReimbursements(r);
    }

}

function headerButtonPressed(btn)
{
    if (btn === 0)
    {
        profileDiv.style.display = "block";
        reimbursementsDiv.style.display = "none";
    }
    else if(btn === 1)
    {
        profileDiv.style.display = "none";
        reimbursementsDiv.style.display = "block";
    }
}

function setProfile( firstName = "", lastName = "", 
username="", email="", address="", address2="",state="", zipCode="", startDate="", image)
{
    document.getElementById("profile-name").innerText = "Name: " + firstName + " " + lastName;
    document.getElementById("profile-username").innerText = "Username: " + username;
    document.getElementById("profile-email").value = email;
    document.getElementById("profile-address").value = address;
    document.getElementById("profile-address2").value = address2;
    document.getElementById("profile-state").value = state;
    document.getElementById("profile-zipcode").value = zipCode;
    document.getElementById("profile-start-date").innerText = "Start Date: " + startDate;
    
}
class Reimbursement {
    constructor(amount="", requestee="", resolver="none", status="",description="", requestDate="", resolveDate="none", image){
        this.amount = amount;
        this.requestee = requestee;
        this.resolver = resolver;
        this.status = status;
        this.description = description;
        this.image = image;
        this.requestDate = requestDate;
        this.resolveDate = resolveDate;
        requests.push(this);
    }
    
}
function getElementString(reimbursement)
{
    string = `<div class="reimburesment-item">
                    <p>Requestee: ${reimbursement.requestee} 
                    Resolver: ${reimbursement.resolver} 
                    Status: ${reimbursement.status}
                    Amount: ${reimbursement.amount}
                    Request Date: ${reimbursement.requestDate}
                    Resolve Date: ${reimbursement.resolveDate}
                    </p>
                    <br>
                    ${getStatusInputSelection(reimbursement.status)}
                    Description: ${reimbursement.description}

                
                </div>`;
                return string;
}
function getStatusInputSelection(currentStatus)
{
    string = `<select name="status">`;
    
    for (s of statusArray){
        if(currentStatus === s)
        {
            string += `<option selected="selected" value="${s}">${s}</option>`;
            
        }
        else{
        string += `<option value="${s}">${s}</option>`;
        }
    }
    string += `</select>`;
    return string;

}
function addElementToReimbursements(reimbursement)
{
    
    reimbursementsTable.innerHTML += getElementString(reimbursement);
}