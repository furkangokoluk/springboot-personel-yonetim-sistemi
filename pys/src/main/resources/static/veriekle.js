document.getElementById("inputEkle").addEventListener("click", function() {
    var ad = document.getElementById("inputAd").value;
    var soyad = document.getElementById("inputSoyad").value;
    var tel_no = document.getElementById("inputTelefon").value;
    var dogum_tarihi = document.getElementById("inputTarih").value;
    var unvan = document.getElementById("inputUnvan").value;
    var maas = document.getElementById("inputMaas").value;

    if (!ad || !soyad || !tel_no || !dogum_tarihi || !unvan || !maas) {
        alert("Lütfen tüm alanları doldurun.");
        return;
    }

    var personel = {
        ad: ad,
        soyad: soyad,
        tel_no : tel_no,
        dogum_tarihi: dogum_tarihi,
        unvan: unvan,
        maas: maas
    };

    fetch('http://localhost:8080/personeller', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(personel)
    })
    .then(response => response.json())
    .then(data => {
        alert("Kayıt Eklendi");
        ad = document.getElementById("inputAd").value="";
        soyad = document.getElementById("inputSoyad").value="";
        tel_no = document.getElementById("inputTelefon").value="";
        dogum_tarihi = document.getElementById("inputTarih").value="";
        unvan = document.getElementById("inputUnvan").value="";
        maas = document.getElementById("inputMaas").value="";
        window.location.href="index.html";
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});
