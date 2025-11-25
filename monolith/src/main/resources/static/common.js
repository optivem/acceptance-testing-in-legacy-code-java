// Common notification functions shared across all pages

function showSuccess(message, elementId = 'orderResult') {
    const resultDiv = document.getElementById(elementId);
    resultDiv.textContent = message;
    resultDiv.classList.remove('error');
    resultDiv.classList.add('success', 'show');
}

function showError(message, elementId = 'orderResult') {
    const resultDiv = document.getElementById(elementId);
    resultDiv.textContent = message;
    resultDiv.classList.remove('success');
    resultDiv.classList.add('error', 'show');
}

function clearMessage(elementId = 'orderResult') {
    const resultDiv = document.getElementById(elementId);
    resultDiv.textContent = '';
    resultDiv.classList.remove('show', 'success', 'error');
}

function showNotification(message, isError = false, containerElementId = 'notifications') {
    const notificationsDiv = document.getElementById(containerElementId);

    notificationsDiv.innerHTML = '';

    const notif = document.createElement('div');
    notif.setAttribute('role', 'alert');
    notif.className = `notification ${isError ? 'error' : 'success'}`;
    notif.textContent = message;

    notificationsDiv.appendChild(notif);

    if (!isError) {
        setTimeout(() => {
            if (notif.parentNode) {
                notif.remove();
            }
        }, 5000);
    }
}

