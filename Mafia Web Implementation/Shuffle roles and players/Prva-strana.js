function handleNames(){

    // Sva imena stavljam u varijablu
    let names = document.getElementById('names').value;

    // Razdvajam imena tako sto detektujem razmak i stavljam ih u listu
    let players = names.split(' ');

    localStorage.setItem('PLAYERS', players);
}