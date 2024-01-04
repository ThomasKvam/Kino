
function loggInn(){
    const bruker = {
        brukernavn : $("#brukernavn").val(),
        passord : $("#passord").val()
    }
    const url = "/login";
    $.get( url, bruker, function(innLogget) {
        if(innLogget){
            window.location.href = 'index.html';
        }
        else{
            $("#feil").html("Feil brukernavn eller passord");
        }
    })
        .fail(function() {
                $("#feil").html("Serverfeil- prøv igjen senere");
            }
        );
}

function registrer(){
    const bruker = {
        brukernavn: $("#brukernavn").val(),
        passord: $("#passord").val()
    };
    const url = "/registrer";
    $.post(url, bruker, function (){
        window.location.href="index.html";
    })
}

function loggUt(){
    const url ="/loggUt";
    $.get(url, function (){
        window.location.href="login.html";
    })
}