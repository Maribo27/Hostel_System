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
            return input.value.length < 5;
        },
        invalidityMessage: 'This input needs to be at least 3 characters',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[a-z].*$/ig);
        },
        invalidityMessage: 'This input needs to have first symbol - letter',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/^.*[^\w\d._].*$/g);
            return !!illegalCharacters;
        },
        invalidityMessage: 'Only letters, numbers, . and _ are allowed',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(3)')
    }
];

var nameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 2;
        },
        invalidityMessage: 'This input needs to be at least 3 characters',
        element: document.querySelector('label[for="name"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[A-ZА-Я].*$/g);
        },
        invalidityMessage: 'This input needs to have first symbol - uppercase letter',
        element: document.querySelector('label[for="name"] .input-requirements li:nth-child(3)')
    },
    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/^.*[^a-zа-я].*$/ig);
            return !!illegalCharacters;
        },
        invalidityMessage: 'Only letters are allowed',
        element: document.querySelector('label[for="name"] .input-requirements li:nth-child(2)')
    }
];

var lastnameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 2 && input.value.length > 0;
        },
        invalidityMessage: 'This input needs to be at least 3 characters',
        element: document.querySelector('label[for="lastname"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[A-ZА-Я].*$/g) && input.value.length > 0;
        },
        invalidityMessage: 'This input needs to have first symbol - uppercase letter',
        element: document.querySelector('label[for="lastname"] .input-requirements li:nth-child(3)')
    },
    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/^.*[^a-zа-я].*$/ig);
            return illegalCharacters & input != null;
        },
        invalidityMessage: 'Only letters are allowed',
        element: document.querySelector('label[for="lastname"] .input-requirements li:nth-child(2)')
    }
];

var surnameValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 2;
        },
        invalidityMessage: 'This input needs to be at least 3 characters',
        element: document.querySelector('label[for="surname"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function(input) {
            return !input.value.match(/^[A-ZА-Я].*$/g);
        },
        invalidityMessage: 'This input needs to to have first symbol - uppercase letter',
        element: document.querySelector('label[for="surname"] .input-requirements li:nth-child(3)')
    },
    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/^.*[^a-zа-я].*$/ig);
            return !!illegalCharacters;
        },
        invalidityMessage: 'Only letters are allowed',
        element: document.querySelector('label[for="surname"] .input-requirements li:nth-child(2)')
    }
];

var emailValidityChecks = [
    {
        isInvalid: function(input) {
            return !input.value.match(/^.*@.*$/g);
        },
        invalidityMessage: 'This input needs to have @',
        element: document.querySelector('label[for="email"] .input-requirements li:nth-child(2)')
    },
    {
        isInvalid: function(input) {
            var illegalCharacters = input.value.match(/^[A-Za-z._\d\-]+@[A-Za-z]+\.[A-Za-z]{2,3}$/g);
            return !illegalCharacters;
        },
        invalidityMessage: 'Only latin letters, numbers, _, - and . are allowed',
        element: document.querySelector('label[for="email"] .input-requirements li:nth-child(1)')
    }
];

var passwordValidityChecks = [
    {
        isInvalid: function(input) {
            return input.value.length < 5 | input.value.length > 12;
        },
        invalidityMessage: 'This input needs to be between 5 and 12 characters',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(1)')
    },

    {
        isInvalid: function(input) {
            return !input.value.match(/^[\w\d._]{5,12}$/g);
        },
        invalidityMessage: 'Only latin letters, numbers, _ and . are allowed',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(2)')
    }
];

var passwordRepeatValidityChecks = [
    {
        isInvalid: function() {
            return passwordRepeatInput.value !== passwordInput.value;
        },
        invalidityMessage: 'This password needs to match the first one'
    }
];

var usernameInput = document.getElementById('username');
if (usernameInput != null) {
    usernameInput.CustomValidation = new CustomValidation(usernameInput);
    usernameInput.CustomValidation.validityChecks = usernameValidityChecks;
}

var nameInput = document.getElementById('name');
if (nameInput != null) {
    nameInput.CustomValidation = new CustomValidation(nameInput);
    nameInput.CustomValidation.validityChecks = nameValidityChecks;
}

var lastnameInput = document.getElementById('lastname');
if (lastnameInput != null) {
    lastnameInput.CustomValidation = new CustomValidation(lastnameInput);
    lastnameInput.CustomValidation.validityChecks = lastnameValidityChecks;
}

var surnameInput = document.getElementById('surname');
if (surnameInput != null) {
    surnameInput.CustomValidation = new CustomValidation(surnameInput);
    surnameInput.CustomValidation.validityChecks = surnameValidityChecks;
}

var emailInput = document.getElementById('email');
if (emailInput != null) {
    emailInput.CustomValidation = new CustomValidation(emailInput);
    emailInput.CustomValidation.validityChecks = emailValidityChecks;
}


var passwordInput = document.getElementById('password');
if (passwordInput != null) {
    passwordInput.CustomValidation = new CustomValidation(passwordInput);
    passwordInput.CustomValidation.validityChecks = passwordValidityChecks;
}

var passwordRepeatInput = document.getElementById('password_repeat');
if (passwordRepeatInput != null) {
    passwordRepeatInput.CustomValidation = new CustomValidation(passwordRepeatInput);
    passwordRepeatInput.CustomValidation.validityChecks = passwordRepeatValidityChecks;
}

var inputs = document.querySelectorAll('input:not([type="submit"])');
var submit = document.querySelector('input[type="submit"');
var form = document.getElementById('registration');
var login = document.getElementById('login');

function validate() {
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].CustomValidation.checkInput();
    }
}

submit.addEventListener('click', validate);
form.addEventListener('submit', validate);
login.addEventListener('submit', validate);