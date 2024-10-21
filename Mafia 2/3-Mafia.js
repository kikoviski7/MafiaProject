// Sve u jednom

function Roll(){
    // Prvo selektujem input
    let input = document.querySelector('.players');

    // Iz inputa uzimam imena igraca
    let players = input.value;
 
    // Stavljam imena igraca u listu
    let playersList = players.split(' ');

    // Selektujem svaki checkbox
    let roles = document.querySelectorAll('.role');

    // Deklarisem sve varijable ovde jer ne mogu u if-u
    var roleList;
    let roleName, role;
     
    // Pravim petlju da bih svaki checkbox mogao 
    // da selektujem zasebno
    for(i = 0; i < roles.length; i++){
        // Proveravam da li je checkbox checkovan
        if(roles[i].checked == true){
 
            // Ako je checkovan, izvlacim iz njega id
            roleName = roles[i].getAttribute('id');
 
            // Id stavljam u jednu varijablu
            role = role + ' ' + roleName;
 
            // Tu varijablu pretvaram u listu
            roleList = role.split(' ');
        }
    }
    
    // Nek se generator ponovi onoliko puta koliko ima imena u listi
    for(j = 0; j < playersList.length; j++){
        // Generisem random brojeve za players i roles
        p = Math.floor(Math.random() * 6);
        r = Math.floor(Math.random() * 7);

        // Dok je r jednak 0, generisi opet
        while(r == 0){
            r = Math.floor(Math.random() * 7);
        }

        // Pravim varijablje za nasumicna imena i rolove
        let chosenName, chosenRole;
        let generatedP, generatedR;

        // Proveravanje se ne obavlja u prvoj fazi petlje
        // Jer nema izabranih imena i uloga
        if(j > 0){
            // Proveravam da li se isti broj vec generisao 
            while(generatedP == p){
                p = Math.floor(Math.random() * 6);
            }
        }

        
        
        // Stavljam ime i ulogu u jednu varijablu
        chosenName = playersList[p];
        chosenRole = roleList[r];

        console.log(generatedP);
        console.log(chosenName);
        console.log(chosenRole);
        console.log('------------------')
        
        

        
    
    }
    
    


    
    

     
}




// function playerSummarize(){
    
//     // Prvo selektujem input
//     let input = document.querySelector('.players');

//     // Iz inputa uzimam imena igraca
//     let players = input.value;

//     // Stavljam imena igraca u listu
//     // let playersList = players.split(' ');
//     console.log(players);
//     // Listu igraca vracam iz funckije
//     return players;
// }   




// function roleSummarize(){
//     // Selektujem svaki checkbox
//     let roles = document.querySelectorAll('.role');

//     // Deklarisem sve varijable ovde jer ne mogu u if-u
//     var roleList;
//     let roleName, role;
    
//     // Pravim petlju da bih svaki checkbox mogao 
//     // da selektujem zasebno
//     for(i = 0; i < roles.length; i++){
//         // Proveravam da li je checkbox checkovan
//         if(roles[i].checked == true){

//             // Ako je checkovan, izvlacim iz njega id
//             roleName = roles[i].getAttribute('id');

//             // Id stavljam u jednu varijablu
//             role = role + ' ' + roleName;

//             // Tu varijablu pretvaram u listu
//             roleList = role.split(' ');
//         }
//     }
//     console.log(roleList);
//     // Listu rolova vracam iz funckije
//     return roleList;
// }




// function randomChoosing(players = playersList){
//     console.log(players);
//     // console.log(roles);
// }