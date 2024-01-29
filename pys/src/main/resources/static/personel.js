document.addEventListener("DOMContentLoaded", function () {
    fetchData();
});

function fetchData() {
    fetch("http://localhost:8080/personeller")
        .then(response => response.json())
        .then(data => populateTable(data))
        .catch(error => console.error("Error fetching data: ", error));
}

function populateTable(personelList) {
    const tableBody = document.getElementById("table-body");

    personelList.forEach(personel => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${personel.id}</td>
            <td>${personel.ad}</td>
            <td>${personel.soyad}</td>
            <td>${personel.tel_no}</td>
            <td>${personel.dogum_tarihi}</td>
            <td>${personel.unvan}</td>
            <td>${personel.maas}</td>
            <td>
                <button onclick="openUpdateForm('${personel.id}')">Güncelle</button>
                <button onclick="deletePersonel('${personel.id}')">Sil</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function deletePersonel(personelId) {
    fetch(`http://localhost:8080/personeller/${personelId}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error deleting personel. Status: ${response.status}`);
        }
        return response.text();
    })
    .then(data => {
        if (data.trim() === "") {
            alert("Kayıt Silindi");
            window.location.href = "index.html";
        }
    })
    .catch(error => console.error("Error deleting personel: ", error));
}

function openUpdateForm(personelId) {
    fetch(`http://localhost:8080/personeller/${personelId}`)
        .then(response => response.json())
        .then(personel => {

            const inputAd = document.getElementById('inputAd');
            const inputSoyad = document.getElementById('inputSoyad');
            const inputTelefon = document.getElementById('inputTelefon');
            const inputTarih = document.getElementById('inputTarih');
            const inputUnvan = document.getElementById('inputUnvan');
            const inputMaas = document.getElementById('inputMaas');


            inputAd.value = personel.ad;
            inputSoyad.value = personel.soyad;
            inputTelefon.value = personel.tel_no;
            inputTarih.value = personel.dogum_tarihi;
            inputUnvan.value = personel.unvan;
            inputMaas.value = personel.maas;

            const updateButton = document.getElementById('inputEkle');

            updateButton.removeEventListener('click', updateButtonClickHandler);

            updateButton.addEventListener('click', updateButtonClickHandler);

            function updateButtonClickHandler() {

                const updatedPersonel = {
                    ad: inputAd.value,
                    soyad: inputSoyad.value,
                    tel_no: inputTelefon.value,
                    dogum_tarihi: inputTarih.value,
                    unvan: inputUnvan.value,
                    maas: inputMaas.value
                };

                fetch(`http://localhost:8080/personeller/${personelId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedPersonel)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Error updating personel. Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.error) {
                        alert(`Validation Error: ${data.error}`);
                    } else {
                        alert('Güncelleme Başarılı:', data);
                        window.location.href = 'index.html';
                    }
                })
                .catch(error => {
                    console.error('Hata:', error);
                    alert("Güncelleme Başarısız! Tekrar Deneyiziniz..");
                    });
            }
        })
        .catch(error => console.error('Error fetching personel details: ', error));
}


