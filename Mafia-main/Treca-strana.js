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
    for(i = 0; i < playersList.length; i++){
        console.log(usedNames[i]);
        console.log(usedRoles[i]);
        console.log('----------------');
    }
    
})