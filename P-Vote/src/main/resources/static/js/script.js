document.addEventListener("DOMContentLoaded", function() {
    console.log("JavaScript is working!");
    // You can add more JS functionalities here if needed
});

document.getElementById("username").addEventListener("input", function() {
    document.getElementById("error-message").style.display = "none";
});


function validateForm() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (!username || !password) {
        alert("Please fill out both fields.");
        return false;
    }
    return true;
}

function checkUsername() {
    var username = document.getElementById("username").value;
	var domain = "@postbank.co.ke";

	if (!username.endsWith(domain)) {
	    alert("Invalid format for username!");
	    return false;
	}
	
	    /* const url = `/checkUser?userName=${encodeURIComponent(username)}`;

	    fetch(url)
	        .then(response => {
	            if (response.ok) {
	                console.log("Request was successful, but no response body.");
	            } else {
	                console.error("Request failed with status:", response.status);
	            }
	        })
	        .catch(error => {
	            console.error('Error:', error);
	        }); */

    if (username) {
        // If the username is filled in, show the password field and change button text to "Login"
		document.getElementById("password").style.display = "block";
		document.getElementById("submit-button").textContent = "Login";
        //passwordField.style.display = "block";
        //submitButton.textContent = "Login";
    } else {
        // If the username is not filled in, hide the password field and change button text to "Verify"
       document.getElementById("password").style.display = "none";
	   document.getElementById("submit-button").textContent = "Verify";
    }
}