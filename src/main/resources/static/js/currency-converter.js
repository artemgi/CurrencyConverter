function convert() {
    const sourceCurrency = document.getElementById('sourceCurrency');
    const targetCurrency = document.getElementById('targetCurrency');
    const sourceCurrencyAmount = document.getElementById('sourceCurrencyAmount');
    const targetCurrencyAmount = document.getElementById('targetCurrencyAmount');

    const data = {
        sourceCurrencyCode: sourceCurrency.value,
        targetCurrencyCode: targetCurrency.value,
        sourceAmount: sourceCurrencyAmount.value
    };

    fetch("/converter", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            let targetAmount = data.toFixed(2);
            console.log(targetAmount);
            targetCurrencyAmount.textContent = targetAmount;
            const newRow = getNewRow(sourceCurrency.value, targetCurrency.value, sourceCurrencyAmount.value, targetAmount);
            document.getElementById('operations').appendChild(newRow);
        })
        .catch(error => {
            console.error(error);
        });
}

function getNewRow(sourceCurrencyCode, targetCurrencyCode, sourceAmount, targetAmount) {
    const newRow = document.createElement("tr");

    const cell1 = createTableCell(sourceCurrencyCode);
    const cell2 = createTableCell(targetCurrencyCode);
    const cell3 = createTableCell(sourceAmount);
    const cell4 = createTableCell(targetAmount);
    const cell5 = createTableCell(getCurrentDate());

    // Добавить каждую ячейку в строку
    newRow.appendChild(cell1);
    newRow.appendChild(cell2);
    newRow.appendChild(cell3);
    newRow.appendChild(cell4);
    newRow.appendChild(cell5);

    return newRow;
}

function createTableCell(text) {
    const cell = document.createElement("td");
    cell.textContent = text;
    return cell;
}

function getCurrentDate() {
    const currentDate = new Date();
    return getCorrectDateFormat(currentDate);
}

function getCorrectDateFormat(date) {
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}.${month}.${year}`;
}