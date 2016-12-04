function validate(){
	var passwordInput = document.getElementById("pass-input");
	var confirmPasswordInput = document.getElementById("pass-confirm-input");

	var pass = passwordInput.value;
	var confirmPass = confirmPasswordInput.value;
	return checkPassword(pass, confirmPass);
}

const BIG_LETTER_PATTERN = /A-Z/.compile();
const NUMBER_PATTERN = /0-9/.compile();
const SMALL_LETTER_PATTERN = /a-z/.compile();

function checkPassword(pass, confirmPass){
	var errP = document.getElementById("pass-err-p");
	var errConfirmP = document.getElementById("pass-confirm-err-p");
	var result = true;
	if(!pass.localeCompare(confirmPass)){
		errConfirmP.className = "";
		result = false;
	}
	if(pass.length < 6){
		errP.className = "";
		result = false;
	}
	if(!((BIG_LETTER_PATTERN.exec(pass)) 
		&& (NUMBER_PATTERN.exec(pass)) 
		&& (SMALL_LETTER_PATTERN.exec(pass)))){
		errP.className = "";
		result = false;
	}
	return result;
}