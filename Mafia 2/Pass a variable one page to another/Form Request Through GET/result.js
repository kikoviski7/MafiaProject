window.addEventListener('load', () => {
    const params = (new URL(document.location)).searchParams;
    const name = params.get('name');
    const surname = params.get('surname');

    document.getElementById('result-name').innerHTML = name;
    document.getElementById('result-surname').innerHTML = surname;
})