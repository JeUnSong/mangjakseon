function acRegister() {
    let form = document.createElement('form');
    form.setAttribute('method', 'get');
    form.setAttribute('action', '/sign-up');
    document.body.appendChild(form);
    form.submit();
}
