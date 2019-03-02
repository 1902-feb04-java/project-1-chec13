let profileBtn;
let reimbursementBtn;
let logout;
let profileDiv;
let reimbursementsDiv;
let reimbursementsTable;
let requests = [];
let user;
let positions = ["Employee", "Manager"];
let statusArray = ["Approved", "Pending", "Denied"];
let managerStatus = false;
window.onload = function () {

    profileDiv = document.getElementById("profile-div");
    reimbursementsDiv = document.getElementById("reimbursement-div");
    profileBtn = document.getElementById("profileBtn");
    reimbursementBtn = document.getElementById("reimbursementsBtn");
    logout = document.getElementById("logout-form");
    reimbursementsTable = document.getElementById("reimbursement-search-table-div");

    profileBtn.addEventListener("click", () => {
        headerButtonPressed(0);
    });
    reimbursementBtn.addEventListener("click", () => {
        headerButtonPressed(1);
    });
    logout.addEventListener('submit', () => {

    });
    setProfile("John", "Vasgird", "Employee", "None", "jvasgird", "jvasgird@gmail.com", "1778 Bandoni ave",
        "", "CA", "94580", "3/1/2019");

    let xhr = new XMLHttpRequest();

    xhr.addEventListener('readystatechange', () => {
        if (xhr.readyState === 4) {
            user = JSON.parse(xhr.response);
            setProfile(user.first_name, user.last_name, positions[user.position - 1], user.manager, user.username, user.email,
                 "", "", "", "", user.start_date);
            if (user.position == 2) {
                managerStatus = true;
            }
            
        }
    });

    xhr.open('get', "http://localhost:8080/Project-1/getUser");

    xhr.send();
    //JSON.parse()

}

function headerButtonPressed(btn) {
    document.getElementById("welcome").style.display = "none";
    if (btn === 0) {
        profileDiv.style.display = "block";

        reimbursementsDiv.style.display = "none";
    }
    else if (btn === 1) {
        profileDiv.style.display = "none";
        reimbursementsDiv.style.display = "block";
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if (xhr.readyState == 4)
            {
                clearReimbursementsTable();
                let rArray = JSON.parse(xhr.response);
                console.log(rArray);
                for (let parse of Object.keys(rArray))
                {
                    //console.log(rArray[parse])
                    //let r = JSON.parse(parse);
                    addElementToReimbursements(new Reimbursement(rArray[parse].amount, rArray[parse].requestee,
                        rArray[parse].resolver, statusArray[rArray[parse].status-1], rArray[parse].info, rArray[parse].request_time,
                        rArray[parse].resolved,"", rArray[parse].reimburesment_id));
                }
                console.log(rArray);
            }
        });
        xhr.open('get', "http://localhost:8080/Project-1/ReimbursementsData");

        xhr.send();
    }
}

function setProfile(firstName = "", lastName = "", position, manager,
    username = "", email = "", address = "", address2 = "", state = "", zipCode = "", startDate = "", image) {
    document.getElementById("profile-name").innerText = "Name: " + firstName + " " + lastName;
    document.getElementById("profile-username").innerText = "Username: " + username;
    document.getElementById("profile-email").value = email;
    document.getElementById("profile-address").value = address;
    document.getElementById("profile-address2").value = address2;
    document.getElementById("profile-state").value = state;
    document.getElementById("profile-zipcode").value = zipCode;
    document.getElementById("profile-start-date").innerText = "Start Date: " + startDate;
    document.getElementById("profile-position").innerText = "Position: " + position;
    document.getElementById("profile-manager").innerText = "Manager: " + manager;

}
class Reimbursement {
    constructor(amount = "", requestee = "", resolver = "none", status = "", description = "",
     requestDate = "", resolveDate = "none", image, id) {
        this.amount = amount;
        this.requestee = requestee;
        this.resolver = resolver;
        this.status = status;
        this.description = description;
        this.image = image;
        this.requestDate = requestDate;
        this.resolveDate = resolveDate;
        this.id = id;
        requests.push(this);
    }

}
function getElementString(reimbursement) {
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
function getStatusInputSelection(currentStatus) {
    if (!managerStatus)
        return "";
    string = `<select name="status">`;

    for (s of statusArray) {
        if (currentStatus === s) {
            string += `<option selected="selected" value="${s}">${s}</option>`;

        }
        else {
            string += `<option value="${s}">${s}</option>`;
        }
    }
    string += `</select>`;
    return string;

}
function addElementToReimbursements(reimbursement) {

    reimbursementsTable.innerHTML += getElementString(reimbursement);
}
function clearReimbursementsTable()
{
    reimbursementsTable.innerHTML= "";
}