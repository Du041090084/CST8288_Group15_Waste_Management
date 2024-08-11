<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Food Waste Reduction Platform</h1>
        <nav>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
             <a href="LogoutServlet">Logout</a>
        </nav>
    </header>
    <main>
        <div class="form-container">
            <form action="./RegisterServlet" method="post" class="register-form">
                <h2>Registration</h2>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Confirm Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <div class="form-group">
                    <label for="userType">User Type:</label>
                    <select id="userType" name="userType" required>
                        <option value="CONSUMER">Consumer</option>
                        <option value="RETAILER">Retailer</option>
                        <option value="CHARITABLE_ORGANIZATION">Charitable Organization</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="contactInfo">Contact Info:</label>
                    <input type="text" id="contactInfo" name="contactInfo">
                </div>

                <div class="form-group">
                    <label for="location">Location:</label>
                    <input type="text" id="location" name="location">
                </div>

                <button type="submit" class="submit-btn">Register</button>
            </form>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Platform Name. All rights reserved.</p>
    </footer>
</body>
</html>
