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