$(function () {
    hentBilletter();
})


function lagreBillett() {
    let feil = false;
    const filmer = $("#filmer");
    const antall = $("#antall");
    const fornavn = $("#fornavn");
    const etternavn = $("#etternavn");
    const nummer = $("#nummer");
    const epost = $("#epost");


    const kunde={
        filmer: filmer.val(),
        antall: antall.val(),
        fornavn: fornavn.val(),
        etternavn: etternavn.val(),
        nummer: nummer.val(),
        epost: epost.val()
    }
    if (filmer.val() == ""){
        document.getElementById("error-filmer").innerHTML = "Må velge en film";
        feil= true;
    }
    else{
        document.getElementById("error-filmer").innerHTML ="";
    }
    if (antall.val() == ""){
        document.getElementById("error-antall").innerHTML = "Må skrive noe i Antall";
        feil= true;
    }
    else{
        document.getElementById("error-antall").innerHTML ="";
    }

    if(fornavn.val() == ""){
        document.getElementById("error-fornavn").innerHTML = "Må skrive noe i Fornavn";
        feil= true;
    }
    else{
        document.getElementById("error-fornavn").innerHTML ="";
    }

    if(etternavn.val() == ""){
        document.getElementById("error-etternavn").innerHTML = "Må skrive noe i Etternavn";
        feil= true;
    }
    else{
        document.getElementById("error-etternavn").innerHTML ="";
    }
    if(nummer.val() == ""){
        document.getElementById("error-nummer").innerHTML = "Må skrive noe i Telefonnr";
        feil= true;
    }
    else{
        document.getElementById("error-nummer").innerHTML ="";
    }
    if(epost.val() == ""){
        document.getElementById("error-epost").innerHTML = "Må skrive noe i E-post";
        feil= true;
    }
    else{
        document.getElementById("error-epost").innerHTML ="";
    }

    if (feil){
        return;
    }
    $.post("/lagre", kunde, function (){
        hentBilletter();
    });

    $("#filmer").val("");
    $("#antall").val("");
    $("#fornavn").val("");
    $("#etternavn").val("");
    $("#nummer").val("");
    $("#epost").val("");
    console.log(kunde)
}

function hentBilletter() {
    $.get("/hentBillett", function (kunde){
        lagreBilletter(kunde);
    })
        .fail(function (status){
        if(status.status=="404")
            $("#feil").html("Må logge inn for å bestille")
    });
}


function lagreBilletter(billetter){
    let ut = "<table><tr>" +
        "<th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>E-post</th>"+
        "</tr>";
    for (let b of billetter){
        ut+="<tr>";
        ut+="<td>"+b.filmer+"</td><td>"+b.antall+"</td><td>"+b.fornavn+"</td><td>"+b.etternavn+"</td><td>"+b.nummer+"</td><td>"+b.epost+"</td>";
        ut+="</tr>"
    }
    document.getElementById("alleBilletter").innerHTML=ut;
}

function clearArray(){

    var r = confirm("Dette vil fjerne alle billettene dine. Er du sikker på at du vil det?")
    if (r == true){
        $.get("/clearArray", function (){
            lagreBilletter();
        });
        document.getElementById("alleBilletter").innerHTML="";
    }
    else {
    }
}