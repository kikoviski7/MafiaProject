function handleSubmit(){
    const name = document.getElementById('name').value;
    const surname = document.getElementById('surname').value;

    localStorage.setItem("NAME", name);
    localStorage.setItem("SURNAME", surname);

    return;
}