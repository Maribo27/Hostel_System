/*
function validate() {
        var form = document.forms.Register;
        var elem = form.username.value;
        if (!elem || elem.match(/^[w]{5,20}$/ig) != null){
            alert ("Wrong username (5 or more letters)");
            return;
        }

        elem = form.name.value;
        if (!elem || elem.match(/^[A-ZА-Я][a-zа-я]{1,50}$/g) === null){
            alert ("Wrong name (from 2 to 50 letters)");
            return;
        }

        elem = form.surname.value;
        if (!elem || elem.match(/^[A-ZА-Я][a-zа-я]{1,50}$/g) === null){
            alert ("Wrong surname (from 2 to 50 letters)");
            return;
        }

        elem = form.age.value;
        if (!elem || elem.match(/^([789]|[1-9]\d|1([01]\d|20))$/) === null){
            alert ("Wrong age (from 7 to 120)");
            return;
        }

        elem = form.email.value;
        if (!elem || elem.match(/^[w]+@[A-Za-z]+.[A-Za-z]{2,3}$/ig) != null){
            alert ("Wrong email");
            return;
        }

        elem = form.password.value;
        if (!elem || elem.match(/^[w]{6,12}$/ig) != null){
            alert ("Wrong password (from 6 to 12 symbols)");
            return;
        }
        
        form.submit();
    }

    */

function CustomValidation(input) {
    this.invalidities = [];
    this.validityChecks = [];
    this.inputNode = input;
    this.registerListener();
}

CustomValidation.prototype = {
    addInvalidity: function(message) {
        this.invalidities.push(message);
    },

    getInvalidities: function() {
        return this.invalidities.join('. \n');
    },

    checkValidity: function(input) {
        for ( var i = 0; i < this.validityChecks.length; i++ ) {
            var isInvalid = this.validityChecks[i].isInvalid(input);
            if (isInvalid) {
                this.addInvalidity(this.validityChecks[i].invalidityMessage);
            }

            var requirementElement = this.validityChecks[i].element;
            if (requirementElement) {
                if (isInvalid) {
                    requirementElement.classList.add('invalid');
                    requirementElement.classList.remove('valid');
                } else {
                    requirementElement.classList.remove('invalid');
                    requirementElement.classList.add('valid');
                }
            }
        }
    },

    checkInput: function() {

        this.inputNode.CustomValidation.invalidities = [];
        this.checkValidity(this.inputNode);

        if ( this.inputNode.CustomValidation.invalidities.length === 0 && this.inputNode.value !== '' ) {
            this.inputNode.setCustomValidity('');
        } else {
            var message = this.inputNode.CustomValidation.getInvalidities();
            this.inputNode.setCustomValidity(message);
        }
    },

    registerListener: function() {
        var CustomValidation = this;
        this.inputNode.addEventListener('keyup', function() {
            CustomValidation.checkInput();
        });


    }

};

var usernameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 3;
        },
        invalidityMessage: 'This input needs to be at least 3 characters',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(1)')
    },

    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/[^a-zA-Z0-9]/g);
            return !!illegalCharacters;
        },
        invalidityMessage: 'Only letters and numbers are allowed',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(2)')
    }
];

var passwordValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 8 | input.value.length > 100;
        },
        invalidityMessage: 'This input needs to be between 8 and 100 characters',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(1)')
    },

    {
        isInvalid: function(input) {
            return !input.value.match(/[0-9]/g);
        },
        invalidityMessage: 'At least 1 number is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(2)')
    },

    {
        isInvalid: function(input) {
            return !input.value.match(/[a-z]/g);
        },
        invalidityMessage: 'At least 1 lowercase letter is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(3)')
    },

    {
        isInvalid: function(input) {
            return !input.value.match(/[A-Z]/g);
        },
        invalidityMessage: 'At least 1 uppercase letter is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(4)')
    },

    {
        isInvalid: function(input) {
            return !input.value.match(/[!@#$%^&*]/g);
        },
        invalidityMessage: 'You need one of the required special characters',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(5)')
    }
];

var passwordRepeatValidityChecks = [
    {
        isInvalid: function() {
            return passwordRepeatInput.value != passwordInput.value;
        },
        invalidityMessage: 'This password needs to match the first one'
    }
];



var usernameInput = document.getElementById('username');
usernameInput.CustomValidation = new CustomValidation(usernameInput);
usernameInput.CustomValidation.validityChecks = usernameValidityChecks;
/*

var nameInput = document.getElementById('name');
usernameInput.CustomValidation = new CustomValidation(nameInput);
usernameInput.CustomValidation.validityChecks = nameValidityChecks;

var lastnameInput = document.getElementById('lastname');
usernameInput.CustomValidation = new CustomValidation(lastnameInput);
usernameInput.CustomValidation.validityChecks = lastnameValidityChecks;

var surnameInput = document.getElementById('surname');
usernameInput.CustomValidation = new CustomValidation(surnameInput);
usernameInput.CustomValidation.validityChecks = surnameValidityChecks;

var emailInput = document.getElementById('email');
usernameInput.CustomValidation = new CustomValidation(emailInput);
usernameInput.CustomValidation.validityChecks = emailValidityChecks;
*/

var passwordInput = document.getElementById('password');
passwordInput.CustomValidation = new CustomValidation(passwordInput);
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

var passwordRepeatInput = document.getElementById('password_repeat');
passwordRepeatInput.CustomValidation = new CustomValidation(passwordRepeatInput);
passwordRepeatInput.CustomValidation.validityChecks = passwordRepeatValidityChecks;


var inputs = document.querySelectorAll('input:not([type="submit"])');
var submit = document.querySelector('input[type="submit"');
var form = document.getElementById('registration');

function validate() {
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].CustomValidation.checkInput();
    }
}

submit.addEventListener('click', validate);
form.addEventListener('submit', validate);