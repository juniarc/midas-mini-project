document.getElementById("signinForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const loginData = {
        username: username,
        password: password
    };

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json();

        if (response.ok) {
            document.getElementById("message").innerText = "Login successed!";
            localStorage.setItem("token", data.token);
            window.location.href = "../index.html";
        }
    } catch (error) {
        console.error("Error:", error);
        document.getElementById("message").innerText = "Login failed! please check again your username and password.";
    }
});