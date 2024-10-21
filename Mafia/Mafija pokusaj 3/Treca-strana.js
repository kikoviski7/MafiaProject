window.addEventListener('load', () =>{

    // Iz Storage uzimam igrace i uloge
    let playerList = localStorage.getItem('PLAYERS');
    let roleList = localStorage.getItem('ROLES');

    // Uloge i igrace stavljam u liste
    let playersList = playerList.split(',');
    let rolesList = roleList.split(',');
    console.log(playersList);
    console.log(rolesList);

    // Deklarisem koji su rolovi mafija i neutral
    let mafiaRoles = ['godfather', 'framer', 'mafioso', 'janitor', 'consort'];
    let neutralRoles = ['serial-killer', 'executioner', 'jester', 'werewolf'];
    let townRoles = ["transporter", "major", "veteran", "doctor", "bodyguard", "lookout", "escort", "retributionist", "sherif", "vigilante"]

    // Deklarisem random brojeve
    let p,r;

    // Deklarisem izabrana imena i uloge
    let chosenName;
    let chosenRole;

    // Deklarisem iskoriscena imena i uloge
    let usedNames = [];
    let usedRoles = [];

    // Deklarisem body
    let body = document.querySelector('body');

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
        console.log(chosenName);
        console.log(chosenRole);
        console.log('----------');
            
        }
        // Stavljam izabrano ime i ulogu u iskoriscena imena i uloge
        usedNames.push(chosenName);
        usedRoles.push(chosenRole);
    }

    // // Prikazuje sva imena i njihove uloge u konzoli
    // for(i = 0; i < playersList.length; i++){
    //         console.log(usedNames[i]);
    //         console.log(usedRoles[i]);
    //         console.log('----------------');
    //     }

    // Naslov i div sekcija ce se napraviti po jednom za svaki role
    for(let i = 0; i < rolesList.length; i++){

        // Pravim naslov
        let h1 = document.createElement('h1');

        // Deklarisem naslov teksta i pretvaram ga u velika slova
        let h1Text = rolesList[i];
        h1Text = h1Text.toUpperCase();

        // Dodajem text u h1
        h1.innerText = h1Text;

        // Dodajem atribute u h1
        if(mafiaRoles.includes(rolesList[i])){
            
            // Ako je uloga mafija, nek bude crven
            h1.setAttribute('class', 'h1-mafia');
        }
        else if(neutralRoles.includes(rolesList[i])){

            // Ako je uloga neutral, nek bude siv
            h1.setAttribute('class', 'h1-neutral');
        }
        else{
            
            // Ako uloga nije ni mafia ni neutral, onda je town
            h1.setAttribute('class', 'h1-town');
        }

        // Stavljam h1 na body
        body.appendChild(h1);

        // Deklarisem town div
        let sectionDiv = document.createElement('div');

        // Dodeljujem town divu atribute
        if(mafiaRoles.includes(rolesList[i])){
            
            // Ako je uloga mafija, nek bude crven
            sectionDiv.setAttribute('class', 'mafia-section section');
        }
        else if(neutralRoles.includes(rolesList[i])){

            // Ako je uloga neutral, nek bude siv
            sectionDiv.setAttribute('class', 'neutral-section section');
        }
        else{
            
            // Ako uloga nije ni mafia ni neutral, onda je town
            sectionDiv.setAttribute('class', 'town-section section');
        }

        // Stavljam sectionDiv na body
        body.appendChild(sectionDiv);

        if(mafiaRoles.includes(h1Text.toLowerCase())){
                 
            if(!(mafiaRoles.includes(usedRoles[i]))){
                

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
             sectionDiv.appendChild(label);
             label.appendChild(input);
             label.appendChild(div);
            }
        
        
        }
    }
})