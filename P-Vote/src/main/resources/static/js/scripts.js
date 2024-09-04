function showAspirants(role) {
    // Hide all aspirant sections
    document.querySelectorAll('.aspirants').forEach(function(element) {
        element.classList.remove('show');
    });

    // Show the selected role's aspirants
    document.getElementById(role).classList.add('show');
}

// voter.html home page js
function showContent(tabId) {
    const contents = document.querySelectorAll('.tab-content');
    contents.forEach(content => {
        content.style.display = 'none';
    });

    document.getElementById(tabId).style.display = 'block';
}


//sidebar
document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.tab-button');
    const contentDiv = document.getElementById('content');

    buttons.forEach(button => {
        button.addEventListener('click', function () {
            const page = this.getAttribute('data-page');

            fetch(`/pages/${page}.html`)
                .then(response => response.text())
                .then(data => {
                    contentDiv.innerHTML = data;
                })
                .catch(error => console.error('Error loading page:', error));
        });
    });

    // Load default content
    document.querySelector('.tab-button').click();
});

//sidebar effects
