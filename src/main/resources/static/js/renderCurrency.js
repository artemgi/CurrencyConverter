const sourceCurrencySelectElement = document.getElementById('sourceCurrency');
const targetCurrencySelectElement = document.getElementById('targetCurrency');
showCurrency(sourceCurrencySelectElement)
showCurrency(targetCurrencySelectElement)

function showCurrency(selectElement) {
    fetch('/currencies')
        .then(response => response.json())
        .then(data => {
            data.forEach(currency => {
                const option = document.createElement('option');
                option.text = `${currency.charCode} (${currency.name})`;
                option.value = currency.charCode;
                selectElement.add(option);
            });
        })
        .catch(error => console.error(error));
}