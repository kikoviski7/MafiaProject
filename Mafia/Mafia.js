function roleSumerize() {                               //nece da appenduje div na div

    let sekcija = document.createElement('div');    // let sekcija = document.createElement('div');
    sekcija.classList = 'druga-strana';             // sekcija.classList = 'druga-strana';
    let body = document.querySelector('body');      // let poslednjaSekcija = document.querySelector('.poslednji-div');
    body.appendChild(sekcija);                      // poslednjaSekcija.appendChild(sekcija);
    var inputs = document.getElementsByTagName("input");
    inputs.checked
    for (var i = 0; i < inputs.length; i++) {
        let dugme = document.createElement("button");
        
        if(inputs[i].checked == true) {
            dugme.innerText = inputs[i].id;
            sekcija.appendChild(dugme); 
        }
                   
    }
    
}


function playerSumerize() {
    let igraci = document.querySelector('#igraci').value;
    //razdvaja stringove po razmacima i stavlja ga u igrac (igrac je pre bio const)
    let igrac = igraci.split(" ");

    console.log(igrac);
    //igrac.addEventListener('click',function get_random(list) {
       // return list[Math.floor((Math.random()*list.length))];
      //})
      //get_random(igrac);
    
}


  
  
  