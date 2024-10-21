window.addEventListener('load', () =>{

    // Iz Storage uzimam igrace i uloge
    let playerList = localStorage.getItem('PLAYERS');
    let roleList = localStorage.getItem('ROLES');

    // Uloge i igrace stavljam u liste
    let playersList = playerList.split(',');
    let rolesList = roleList.split(',');

    // Deklarisem random brojeve
    let p,r;

    // Deklarisem izabrana imena i uloge
    let chosenName;
    let chosenRole;

    // Deklarisem iskoriscena imena i uloge
    let usedNames = [];
    let usedRoles = [];

    // Nek se generator ponovi onoliko puta koliko ima imena u listi
    for(let i = 0; i < playersList.length; i++){
        // Generisem random brojeve za players i roles
        p = Math.floor(Math.random() * playersList.length);
        r = Math.floor(Math.random() * rolesList.length);

        // Nasumicno ime i ulogu dodajem u izabrano ime i uloga
        chosenName = playersList[p];
        chosenRole = rolesList[r];

        // Ova petlja se nece odraditi u prvom ciklusu
        if(i > 0){
            // Dok iskroscena imena sadrze izabrano ime
            while(usedNames.includes(chosenName)){
                // Opet generisi broj
                p = Math.floor(Math.random() * playersList.length);

                // Nasumicno ime dodajem u izabrano ime
                chosenName = playersList[p];

            }

            //Dok iskoriscene uloge sadrze izabranu ulogu
            while(usedRoles.includes(chosenRole)){
                // Opet generisi broj
                r = Math.floor(Math.random() * rolesList.length);

                // Nasumicnu ulogu dodajem u izabranu ulogu
                chosenRole = rolesList[r];

            }

            
        }
        // Stavljam izabrano ime i ulogu u iskoriscena imena i uloge
        usedNames.push(chosenName);
        usedRoles.push(chosenRole);
    }

    // Prikazuje sva imena i njihove uloge u konzoli
    for(i = 0; i < playersList.length; i++){
            console.log(usedNames[i]);
            console.log(usedRoles[i]);
            console.log('----------------');
        }
    
    // Transporter mora biti prvi u prikazivanju
    if(usedRoles.includes('transporter')){
        // Nadji koji je transporter po redu
        let transporterIndex = usedRoles.findIndex(index => index === 'transporter');
        console.log(transporterIndex);

        // Deklarisem body
        let body = document.querySelector('body');

        // Pravim naslov
        let h1 = document.createElement('h1');

        // Deklarisem naslov teksta i pretvaram ga u velika slova
        let h1Text = usedNames[transporterIndex] + ' ' + usedRoles[transporterIndex];
        h1Text = h1Text.toUpperCase();

        // Dodajem text u h1
        h1.innerText = h1Text;

        // Stavljam h1 na body
        body.appendChild(h1);

        // Deklarisem town div
        let townDiv = document.createElement('div');

        // Dodeljujem town divu atribute
        townDiv.setAttribute('class', 'town-section section');

        // Stavljam townDiv na body
        body.appendChild(townDiv);

        // Petlja ce napraviti onoliko dugmadi koliko ima uloga
        for(i = 0; i < rolesList.length; i++){

        
            if(i != transporterIndex){
                
                // Pravim labelu koja ide oko svega 
                let label = document.createElement('label');

                // Dodajem labeli atribute
                label.setAttribute('for', usedRoles[i]);
                label.setAttribute('class', 'label');
                
                // Pravim input
                let input = document.createElement('input');

                // Dodajem inputu atribute
                input.setAttribute('type', 'checkbox');
                input.setAttribute('class', 'checkbox role');
                input.setAttribute('id', usedRoles[i]);

                // Pravim div
                let div = document.createElement('div');

                // Dodajem divu atribute 
                div.setAttribute('class', 'checkbox-box');

                // Dodajem divu text
                div.innerText = usedNames[i] + ' ' + usedRoles[i];

                // Labelu ubacujem u body a input i div u labelu
                townDiv.appendChild(label);
                label.appendChild(input);
                label.appendChild(div);


                
            }
        }
    }
    
    
})