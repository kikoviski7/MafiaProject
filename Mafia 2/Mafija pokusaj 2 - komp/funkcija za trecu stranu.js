window.addEventListener('load', () =>{

    let mafiaRoles = ['godfather', 'mafioso', 'janitor', 'framer', 'consort'];
    let neutralRoles = ['jester', 'executioner', 'serial killer', 'werewolf'];

    // Deklarisem role listu 
    let usedRoles = localStorage.getItem('ROLES');

    // Pretvaram rolove u listu
    let roleList = usedRoles.split(',');

    // Deklarisem imena
    let usedNames = localStorage.getItem('PLAYERS');

    // Pretvaram imena u listu
    let nameList = usedNames.split(',');

    // Deklarisem body
    let body = document.querySelector('body');

    if(usedRoles.includes(roleList[0])){
    
        // Pravim naslov
        let h1 = document.createElement('h1');
    
        // Index rola
        let indexOfRole = roleList.findIndex(element => element === roleList[0]);
    
        // Deklarisem naslov teksta i pretvaram sve u velika slova
        let h1Text = roleList[0];
        h1Text = h1Text.toUpperCase();
    
        // Stavljam naslov texta 
        h1.innerText = h1Text;

        if(mafiaRoles.includes(roleList[0])){
            body.setAttribute('class', 'mafia-treca-strana')
            h1.setAttribute('class', 'h1-mafia');
        }
        else if(neutralRoles.includes(roleList[0])){
            h1.setAttribute('class', 'h1-neutral');
        }
    
        // Deklarisem div
        let divTown = document.createElement('div');

        if(mafiaRoles.includes(roleList[0])){
            // Dodaljujem divu atribute
            divTown.setAttribute('class', 'mafia-section section');
        }
        else if(neutralRoles.includes(roleList[0])){
             // Dodaljujem divu atribute
             divTown.setAttribute('class', 'neutral-section section');
        }
    
        
    
        // Petlja ce napraviti onoliko dugmadi koliko ima uloga
        for(let i = 0; i < roleList.length; i++){
                
            if(i != indexOfRole){
                    
                // Pravim labelu
                let label = document.createElement('label');
    
                // Dodajem labeli atribute
                label.setAttribute('for', roleList[i]);
                label.setAttribute('class', 'label');
    
                // Pravim input
                let input = document.createElement('input');
    
                // Dodajem inputu atribute
                input.setAttribute('type', 'checkbox');
                input.setAttribute('class', 'checkbox role');
                input.setAttribute('id', roleList[i]);
    
                // Pravim div
                let div = document.createElement('div');

                // Dodajem atribute na div
                div.setAttribute('class', 'checkbox-box');

                // Stavljam text na div
                let divText = nameList[i] + ' ' + roleList[i];
                div.innerText = divText;

                // Stavljam h1 na body 
                body.appendChild(h1);
    
                // Stavljam div na body
                body.appendChild(divTown);
                    
                // Dodajem divu atribute
                divTown.appendChild(label);
                label.appendChild(input);
                label.appendChild(div);

            }   
        }
        // Ako je godfather, mafija vise ne napada 
        if(roleList[0] == 'godfather'){
            roleList.shift();
            roleList.shift();
        }
        else{
            roleList.shift();
        }

        // Njihova imena se isto izbacuju 
        if(nameList[0] == 'Sandra'){
            nameList.shift();
            nameList.shift();
        }
        else{
            nameList.shift();
        }

        // Stavljam novi roleList i nameList na local storage 
        localStorage.setItem('ROLES-LEFT', roleList);
        localStorage.setItem('PLAYERS-LEFT', nameList);


        // Pravim dugme za sledecu stranicu 
        let button = document.createElement('span');
        button.setAttribute('class', 'btn');
        button.setAttribute('onclick', 'nextPage()')
        button.innerText = 'Apply';
        body.appendChild(button);
        
    }

    
})

function nextPage(){


    // Deklarisem mafia i neutral rolove
    let mafiaRoles = ['godfather', 'mafioso', 'janitor', 'framer', 'consort'];
    let neutralRoles = ['jester', 'executioner', 'serial killer', 'werewolf'];

    // Deklarisem role listu 
    let usedRoles = localStorage.getItem('ROLES');

    // Pretvaram rolove u listu
    let roleList = usedRoles.split(',');
    console.log(roleList);

    // Deklarisem imena
    let usedNames = localStorage.getItem('PLAYERS');

    // Pretvaram imena u listu
    let nameList = usedNames.split(',');
    console.log(nameList);

    // Deklarisem preostale uloge
    let roles = localStorage.getItem('ROLES-LEFT');

    // Pretvaram uloge u listu
    let rolesLeft = roles.split(',');
    console.log(rolesLeft);

    // Preostala imena
    let players = localStorage.getItem('PLAYERS-LEFT');

    // Oretvaram imena u listu
    let playersLeft = players.split(',');
    console.log(playersLeft);
    
    // Otvaram novi prozor
    var opened = window.open('');

    // Deklarisem head
    let head = opened.document.querySelector('head');

    // Deklarisem strukturu html
    let responsiveWidth = opened.document.createElement('meta');
    responsiveWidth.setAttribute('name', 'viewport');
    responsiveWidth.setAttribute('content', 'width=device-width, initial-scale=1.0');

    // Deklarisem JavaScript
    let script = opened.document.createElement('script');
    let code = "function nextPage(){\
        var opened = window.open('');\
        let mafiaRoles = ['godfather', 'mafioso', 'janitor', 'framer', 'consort'];\
        let neutralRoles = ['jester', 'executioner', 'serial killer', 'werewolf'];\
        let usedRoles = localStorage.getItem('ROLES');\
        let roleList = usedRoles.split(',');\
        let usedNames = localStorage.getItem('PLAYERS');\
        let nameList = usedNames.split(',');\
        let roles = localStorage.getItem('ROLES-LEFT');\
        let rolesLeft = roles.split(',');\
        let players = localStorage.getItem('PLAYERS-LEFT');\
        let playersLeft = players.split(',');\
        let head = opened.document.querySelector('head');\
        let responsiveWidth = opened.document.createElement('meta');\
        responsiveWidth.setAttribute('name', 'viewport');\
        responsiveWidth.setAttribute('content', 'width=device-width, initial-scale=1.0');\
        let script = opened.document.createElement('script');\
        let code = 'function nextPage(){\
            var opened = window.open();\
            let mafiaRoles = [`godfather`, `mafioso`, `janitor`, `framer`, `consort`];\
            let neutralRoles = [`jester`, `executioner`, `serial killer`, `werewolf`];\
            let usedRoles = localStorage.getItem(`ROLES`);\
            let roleList = usedRoles.split(`,`);\
            let usedNames = localStorage.getItem(`PLAYERS`);\
            let nameList = usedNames.split(`,`);\
            let roles = localStorage.getItem(`ROLES-LEFT`);\
            let rolesLeft = roles.split(`,`);\
            let players = localStorage.getItem(`PLAYERS-LEFT`);\
            let playersLeft = players.split(`,`);\
            let head = opened.document.querySelector(`head`);\
            let responsiveWidth = opened.document.createElement(`meta`);\
            responsiveWidth.setAttribute(`name`, `viewport`);\
            responsiveWidth.setAttribute(`content`, `width=device-width, initial-scale=1.0`);\
            head.appendChild(responsiveWidth);\
            let style = opened.document.createElement(`style`);\
            let css = `body{\
                background-color: #313131;\
            }\
            \
            .section{\
                display: grid;\
                grid-template-columns: repeat(2, 50%);\
                grid-auto-rows: 60px;\
            }\
            hr{\
                height: 3px;\
                background-color: black;\
                border: none;\
            }\
            .checkbox{\ display: none;}\
            .section div{\
                position: relative;\
                margin: 5px;\
                color: white;\
                text-align: center;\
                line-height: 50px;\
            }\
            .bodyguard{\
                grid-column-start: 1;\
                grid-column-end: 3;\
            }\
            .mafia-section .checkbox-box{\
                background-color: rgb(209, 13, 13);\
                border: solid 1px red;\
                transition: 0.5s;\
            }\
            .mafia-section .checkbox:checked + .checkbox-box{\
                background-color: #313131;\
                box-shadow: 0 0 10px red,\
                inset 5px 5px 10px red,\
                inset -5px -5px 10px red;\
                border-radius: 15px;\
            }\
            .town-section .checkbox-box{\
                background-color: rgb(126, 208, 3);\
                border: solid 1px yellowgreen;\
                transition: 0.5s;\
            }\
            .town-section .checkbox:checked + .checkbox-box{\
                background-color: #313131;\
                box-shadow: 0 0 10px yellowgreen,\
                inset 5px 5px 10px yellowgreen,\
                inset -5px -5px 10px yellowgreen;\
                border-radius: 15px;\
            }\
            .neutral-section .checkbox-box{\
                background-color: rgb(107, 104, 104);\
                border: solid 1px grey;\
                transition: 0.5s;\
            }\
            .neutral-section .checkbox:checked + .checkbox-box{\
                background-color: #313131;\
                box-shadow: 0 0 10px grey,\
                inset 5px 5px 10px grey,\
                inset -5px -5px 10px grey;\
                border-radius: 15px;\
            }\
            .btn{\
                height: 40px;\
                width: 30%;\
                position: absolute;\
                background-color: grey;\
                border: 3px solid rgb(177, 177, 177);\
                border-radius: 15px;\
                line-height: 40px;\
                font-size: 14px;\
                text-align: center;\
                margin-left: 130px;\
                margin-top: 50px;\
                color: white;\
            }\
            .h1-town{\
                color: yellowgreen;\
                text-align: center;\
                margin-top: 50px;\
                margin-bottom: 50px;\
            }\
            .h1-mafia{\
                color: red;\
                text-align: center;\
                margin-top: 50px;\
                margin-bottom: 50px;\
            }\
            .h1-neutral{\
                color: grey;\
                text-align: center;\
                margin-top: 50px;\
                margin-bottom: 50px;\
            }`;\
            // style.innerText = css;\
            // script.innerText = code;\
            // head.appendChild(style);\
            // head.appendChild(responsiveWidth);\
            // head.appendChild(script);\
            let body = opened.document.querySelector(`body`);\
            if(usedRoles.includes(roleList[0])){\
                        let h1 = opened.document.createElement(`h1`);\
                        let indexOfRole = roleList.findIndex(element => element === roleList[0]);\
                        let h1Text = rolesLeft[0];\
                        h1Text = h1Text.toUpperCase();\
                        h1.innerText = h1Text;\
                        if(mafiaRoles.includes(rolesLeft[0])){\
                            h1.setAttribute(`class`, `h1-mafia`)\
                        }\
                        let divSection = opened.document.createElement(`div`);\
                        if(mafiaRoles.includes(roleList[0])){\
                            divSection.setAttribute(`class`, `mafia-section section`);\
                        }\
                        else if(neutralRoles.includes(roleList[0])){\
                            divSection.setAttribute(`class`, `neutral-section section`);\
                        }\
                        else{\
                            divSection.setAttribute(`class`, `town-section section`);\
                        }\
                        for(let i = 0; i < roleList.length; i++){\
                            if(i != indexOfRole){\
                                let label = opened.document.createElement(`label`);\
                                label.setAttribute(`for`, roleList[i]);\
                                label.setAttribute(`class`, `label`);\
                                let input = opened.document.createElement(`input`);\
                                input.setAttribute(`type`, `checkbox`);\
                                input.setAttribute(`class`, `checkbox role`);\
                                input.setAttribute(`id`, roleList[i]);\
                                let div = opened.document.createElement(`div`);\
                                div.setAttribute(`class`, `checkbox-box`);\
                                let divText = nameList[i] + ` ` + roleList[i];\
                                div.innerText = divText;\
                                body.appendChild(h1);\
                                body.appendChild(divSection);\
                                divSection.appendChild(label);\
                                label.appendChild(input);\
                                label.appendChild(div);\
                            }\
                        }\
                        if(roleList[0] == `godfather`){\
                            roleList.shift();\
                            roleList.shift();\
                        }\
                        else{\
                            roleList.shift();\
                        }\
                        if(nameList[0] == `Sandra`){\
                            nameList.shift();\
                            nameList.shift();\
                        }\
                        else{\
                            nameList.shift();\
                        }\
                        localStorage.setItem(`ROLES-LEFT`, roleList);\
                        localStorage.setItem(`PLAYERS-LEFT`, nameList);\
                        let button = document.createElement(`span`);\
                        button.setAttribute(`class`, `btn`);\
                        button.setAttribute(`onclick`, `nextPage()`);\
                        button.innerText = `Apply`;\
                        body.appendChild(button);\
            }\
        }';\
        head.appendChild(responsiveWidth);\
        let style = opened.document.createElement('style');\
        let css = 'body{\
            background-color: #313131;\
        }\
        \
        .section{\
            display: grid;\
            grid-template-columns: repeat(2, 50%);\
            grid-auto-rows: 60px;\
        }\
        hr{\
            height: 3px;\
            background-color: black;\
            border: none;\
        }\
        .checkbox{\ display: none;}\
        .section div{\
            position: relative;\
            margin: 5px;\
            color: white;\
            text-align: center;\
            line-height: 50px;\
        }\
        .bodyguard{\
            grid-column-start: 1;\
            grid-column-end: 3;\
        }\
        .mafia-section .checkbox-box{\
            background-color: rgb(209, 13, 13);\
            border: solid 1px red;\
            transition: 0.5s;\
        }\
        .mafia-section .checkbox:checked + .checkbox-box{\
            background-color: #313131;\
            box-shadow: 0 0 10px red,\
            inset 5px 5px 10px red,\
            inset -5px -5px 10px red;\
            border-radius: 15px;\
        }\
        .town-section .checkbox-box{\
            background-color: rgb(126, 208, 3);\
            border: solid 1px yellowgreen;\
            transition: 0.5s;\
        }\
        .town-section .checkbox:checked + .checkbox-box{\
            background-color: #313131;\
            box-shadow: 0 0 10px yellowgreen,\
            inset 5px 5px 10px yellowgreen,\
            inset -5px -5px 10px yellowgreen;\
            border-radius: 15px;\
        }\
        .neutral-section .checkbox-box{\
            background-color: rgb(107, 104, 104);\
            border: solid 1px grey;\
            transition: 0.5s;\
        }\
        .neutral-section .checkbox:checked + .checkbox-box{\
            background-color: #313131;\
            box-shadow: 0 0 10px grey,\
            inset 5px 5px 10px grey,\
            inset -5px -5px 10px grey;\
            border-radius: 15px;\
        }\
        .btn{\
            height: 40px;\
            width: 30%;\
            position: absolute;\
            background-color: grey;\
            border: 3px solid rgb(177, 177, 177);\
            border-radius: 15px;\
            line-height: 40px;\
            font-size: 14px;\
            text-align: center;\
            margin-left: 130px;\
            margin-top: 50px;\
            color: white;\
        }\
        .h1-town{\
            color: yellowgreen;\
            text-align: center;\
            margin-top: 50px;\
            margin-bottom: 50px;\
        }\
        .h1-mafia{\
            color: red;\
            text-align: center;\
            margin-top: 50px;\
            margin-bottom: 50px;\
        }\
        .h1-neutral{\
            color: grey;\
            text-align: center;\
            margin-top: 50px;\
            margin-bottom: 50px;\
        }';\
        style.innerText = css;\
        head.appendChild(style);\
        let body = opened.document.querySelector('body');\
        if(usedRoles.includes(roleList[0])){\
                    let h1 = opened.document.createElement('h1');\
                    let indexOfRole = roleList.findIndex(element => element === roleList[0]);\
                    let h1Text = rolesLeft[0];\
                    h1Text = h1Text.toUpperCase();\
                    h1.innerText = h1Text;\
                    if(mafiaRoles.includes(rolesLeft[0])){\
                        h1.setAttribute('class', 'h1-mafia')\
                    }\
                    let divSection = opened.document.createElement('div');\
                    if(mafiaRoles.includes(roleList[0])){\
                        divSection.setAttribute('class', 'mafia-section section');\
                    }\
                    else if(neutralRoles.includes(roleList[0])){\
                        divSection.setAttribute('class', 'neutral-section section');\
                    }\
                    else{\
                        divSection.setAttribute('class', 'town-section section');\
                    }\
                    for(let i = 0; i < roleList.length; i++){\
                        if(i != indexOfRole){\
                            let label = opened.document.createElement('label');\
                            label.setAttribute('for', roleList[i]);\
                            label.setAttribute('class', 'label');\
                            let input = opened.document.createElement('input');\
                            input.setAttribute('type', 'checkbox');\
                            input.setAttribute('class', 'checkbox role');\
                            input.setAttribute('id', roleList[i]);\
                            let div = opened.document.createElement('div');\
                            div.setAttribute('class', 'checkbox-box');\
                            let divText = nameList[i] + ' ' + roleList[i];\
                            div.innerText = divText;\
                            body.appendChild(h1);\
                            body.appendChild(divSection);\
                            divSection.appendChild(label);\
                            label.appendChild(input);\
                            label.appendChild(div);\
                        }\
                    }\
                    if(roleList[0] == 'godfather'){\
                        roleList.shift();\
                        roleList.shift();\
                    }\
                    else{\
                        roleList.shift();\
                    }\
                    if(nameList[0] == 'Sandra'){\
                        nameList.shift();\
                        nameList.shift();\
                    }\
                    else{\
                        nameList.shift();\
                    }\
                    localStorage.setItem('ROLES-LEFT', roleList);\
                    localStorage.setItem('PLAYERS-LEFT', nameList);\
                    let button = document.createElement('span');\
                    button.setAttribute('class', 'btn');\
                    button.setAttribute('onclick', 'nextPage()');\
                    button.innerText = 'Apply';\
                    body.appendChild(button);\
        }\
    }";

    // Deklarisem style
    let style = opened.document.createElement('style');
    let css = "body{\
        background-color: #313131;\
    }\
    \
    .section{\
        display: grid;\
        grid-template-columns: repeat(2, 50%);\
        grid-auto-rows: 60px;\
    }\
    hr{\
        height: 3px;\
        background-color: black;\
        border: none;\
    }\
    .checkbox{\ display: none;}\
    .section div{\
        position: relative;\
        margin: 5px;\
        color: white;\
        text-align: center;\
        line-height: 50px;\
    }\
    .bodyguard{\
        grid-column-start: 1;\
        grid-column-end: 3;\
    }\
    .mafia-section .checkbox-box{\
        background-color: rgb(209, 13, 13);\
        border: solid 1px red;\
        transition: 0.5s;\
    }\
    .mafia-section .checkbox:checked + .checkbox-box{\
        background-color: #313131;\
        box-shadow: 0 0 10px red,\
        inset 5px 5px 10px red,\
        inset -5px -5px 10px red;\
        border-radius: 15px;\
    }\
    .town-section .checkbox-box{\
        background-color: rgb(126, 208, 3);\
        border: solid 1px yellowgreen;\
        transition: 0.5s;\
    }\
    .town-section .checkbox:checked + .checkbox-box{\
        background-color: #313131;\
        box-shadow: 0 0 10px yellowgreen,\
        inset 5px 5px 10px yellowgreen,\
        inset -5px -5px 10px yellowgreen;\
        border-radius: 15px;\
    }\
    .neutral-section .checkbox-box{\
        background-color: rgb(107, 104, 104);\
        border: solid 1px grey;\
        transition: 0.5s;\
    }\
    .neutral-section .checkbox:checked + .checkbox-box{\
        background-color: #313131;\
        box-shadow: 0 0 10px grey,\
        inset 5px 5px 10px grey,\
        inset -5px -5px 10px grey;\
        border-radius: 15px;\
    }\
    .btn{\
        height: 40px;\
        width: 30%;\
        position: absolute;\
        background-color: grey;\
        border: 3px solid rgb(177, 177, 177);\
        border-radius: 15px;\
        line-height: 40px;\
        font-size: 14px;\
        text-align: center;\
        margin-left: 130px;\
        margin-top: 50px;\
        color: white;\
    }\
    .h1-town{\
        color: yellowgreen;\
        text-align: center;\
        margin-top: 50px;\
        margin-bottom: 50px;\
    }\
    .h1-mafia{\
        color: red;\
        text-align: center;\
        margin-top: 50px;\
        margin-bottom: 50px;\
    }\
    .h1-neutral{\
        color: grey;\
        text-align: center;\
        margin-top: 50px;\
        margin-bottom: 50px;\
    }";

    // Stavljam te atribute u head
    style.innerText = css;
    script.innerText = code;
    head.appendChild(style);
    head.appendChild(responsiveWidth);
    head.appendChild(script);

    // Deklarisem body
    let body = opened.document.querySelector('body');


            if(usedRoles.includes(roleList[0])){
    
                // Pravim naslov
                let h1 = opened.document.createElement('h1');
            
                // Index rola
                let indexOfRole = roleList.findIndex(element => element === roleList[0]);
            
                // Deklarisem naslov teksta i pretvaram sve u velika slova
                let h1Text = rolesLeft[0];
                h1Text = h1Text.toUpperCase();
            
                // Stavljam naslov texta 
                h1.innerText = h1Text;

                if(mafiaRoles.includes(rolesLeft[0])){
                    h1.setAttribute('class', 'h1-mafia')
                }
            
                // Deklarisem div
                let divSection = opened.document.createElement('div');
            
                if(mafiaRoles.includes(roleList[0])){
                    // Dodaljujem divu atribute
                    divSection.setAttribute('class', 'mafia-section section');
                }
                else if(neutralRoles.includes(roleList[0])){
                    divSection.setAttribute('class', 'neutral-section section');
                }
                else{
                    divSection.setAttribute('class', 'town-section section');
                }
            
                // Petlja ce napraviti onoliko dugmadi koliko ima uloga
                for(let i = 0; i < roleList.length; i++){
                        
                    if(i != indexOfRole){
                            
                        // Pravim labelu
                        let label = opened.document.createElement('label');
            
                        // Dodajem labeli atribute
                        label.setAttribute('for', roleList[i]);
                        label.setAttribute('class', 'label');
            
                        // Pravim input
                        let input = opened.document.createElement('input');
            
                        // Dodajem inputu atribute
                        input.setAttribute('type', 'checkbox');
                        input.setAttribute('class', 'checkbox role');
                        input.setAttribute('id', roleList[i]);
            
                        // Pravim div
                        let div = opened.document.createElement('div');
        
                        // Dodajem atribute na div
                        div.setAttribute('class', 'checkbox-box');
        
                        // Stavljam text na div
                        let divText = nameList[i] + ' ' + roleList[i];
                        div.innerText = divText;
        
                        // Stavljam h1 na body 
                        body.appendChild(h1);
            
                        // Stavljam div na body
                        body.appendChild(divSection);
                            
                        // Dodajem divu atribute
                        divSection.appendChild(label);
                        label.appendChild(input);
                        label.appendChild(div);
        
                    }
                }   
                // Ako je godfather, mafija vise ne napada 
                if(rolesLeft[0] == 'godfather'){
                    rolesLeft.shift();
                    rolesLeft.shift();
                }
                else{
                    rolesLeft.shift();
                }
        
                // Njihova imena se isto izbacuju 
                if(nameList[0] == 'Sandra'){
                    nameList.shift();
                    nameList.shift();
                }
                else{
                    nameList.shift();
                }
                console.log()
        
                // Prvo brisem igrace i uloge
                localStorage.removeItem('ROLES-LEFT');
                localStorage.removeItem('PLAYERS-LEFT');

                // Stavljam novi roleList i nameList na local storage 
                localStorage.setItem('ROLES-LEFT', rolesLeft);
                localStorage.setItem('PLAYERS-LEFT', nameList);
        
        
                // Pravim dugme za sledecu stranicu 
                let button = document.createElement('span');


                button.setAttribute('class', 'btn');
                button.setAttribute('onclick', 'nextPage()')
                button.innerText = 'Apply';
                body.appendChild(button);
            }
}