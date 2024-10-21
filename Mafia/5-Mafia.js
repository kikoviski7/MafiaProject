// Funkcija koja generise nasumican broj 
function getRandomNumber(min, max){
    // Prvi korak je da oduzmemo maksimalnu od minimalne vrednosti
    let step1 = max - min + 1;

    // Drugi korak je da razliku maksimalne i minimalne vrednosti pomnozimo sa nasumicnim brojem
    let step2 = Math.random() * step1;

    // Rezultat je da na zaokruzen nasumicni broj dodamo minimalnu vrednost
    let result = Math.floor(step2) + min;

    // Vracamo rezultat
    return result;
}

// Pravljenje liste brojeva
function createArrayOfNumbers(start, end){

    // Deklaracija liste
    let myArray = [];
    
    // Petlja ce poceti od pocetnog broja i zavrsice se na krajnjem
    for(let i = start; i<= end; i++){

        // U svakom ciklusu petlje ce se dodati broj
        myArray.push(i);
    }
    
    // Vracamo listu
    return myArray;
}





function Roll(){
    // Imena igraca stavljam u varijablu
    let input = document.querySelector('.players').value;

    // Stavljam imena igraca u listu
    let playersList = input.split(' ');

    //Varijabla za random broj
    let p;

    //Varijabla za iskoriscene brojeve
    let usedPlayersNumbers = [];
    
    for(let i = 0; i < playersList.length; i++){
        // Generise random broj od 0 do broja igraca
        p = Math.floor(Math.random() * playersList.length);

        // Ovo se ne obavlja u prvom ciklusu
        if(i == 0){
            // Ako iskorisceni brojevi sadrze trenutni broj, generisi novi
            while(usedPlayersNumbers.includes(p)){
                p = Math.floor(Math.random() * playersList.length);
            }
        }

        // Stavlja iskoriscen broj u varijalu
        usedPlayersNumbers.push(p);
    }

    console.log(usedPlayersNumbers);

}

