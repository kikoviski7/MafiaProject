function handleNames(){

    // Sva imena stavljam u varijablu
    let names = document.getElementById('names').value;
    console.log(names);

    // Razdvajam imena tako sto detektujem razmak i stavljam ih u listu
    let players = names.split(' ');
    console.log(players);

    localStorage.setItem('PLAYERS', players);
}