document.getElementById("inputEkle").addEventListener("click", function () {
    var ad = document.getElementById("inputAd").value;
    var soyad = document.getElementById("inputSoyad").value;
    var tel_no = document.getElementById("inputTelefon").value;
    var dogum_tarihi = document.getElementById("inputTarih").value;
    var unvan = document.getElementById("inputUnvan").value;
    var maas = document.getElementById("inputMaas").value;

    var personel = {
        ad: ad,
        soyad: soyad,
        tel_no: tel_no,
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
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            alert("Kayıt Eklendi");
            document.getElementById("inputAd").value = "";
            document.getElementById("inputSoyad").value = "";
            document.getElementById("inputTelefon").value = "";
            document.getElementById("inputTarih").value = "";
            document.getElementById("inputUnvan").value = "";
            document.getElementById("inputMaas").value = "";
            window.location.href = "index.html";
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Hata oluştu. Lütfen tekrar deneyin.");
        });
});
