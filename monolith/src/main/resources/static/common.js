// Common notification functions shared across all pages


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

