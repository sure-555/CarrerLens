document.getElementById("registerForm").addEventListener("submit", async function (e) {

    e.preventDefault();

    const fullName = document.getElementById("fullName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("/api/users/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({

                fullName,
                email,
                password

            })

        });

        if (response.ok) {

            alert("Registration Successful!");

            window.location.href = "/login";

        } else {

            alert("Registration Failed");

        }

    } catch (error) {

        console.error(error);

        alert("Server Error");

    }

});