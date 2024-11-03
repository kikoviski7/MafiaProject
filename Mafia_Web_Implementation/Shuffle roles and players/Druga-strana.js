window.addEventListener('load', () => {
    
    // Iz storage uzeti listu imena
    let names = localStorage.getItem('PLAYERS');

    // Razdvajam imena tako sto detektujem zarez i stavljam ih u listu
    let players = names.split(',');

})

function handleRoles(){
    // Selektujem svaki checkbox
    let roles = document.querySelectorAll('.role');

    // Deklarisem sve varijable ovde jer ne mogu u if-u
    let roleList ,roleName, role;
    
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
    // Uklanja undefined iz liste
    roleList.shift();

    // Stavlja listu rolova u storage
    localStorage.setItem('ROLES',roleList);
    
    
    

}