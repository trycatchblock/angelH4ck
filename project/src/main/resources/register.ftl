<html>
<head>
<title>Register</title>
</head>
<body>
Sign up
<form action="/register_2" method="POST">
First Name: <input type="text" name="first_name">     </br>
Last Name: <input type="text" name="last_name">                    </br>
User Name: <input type="text" name="user_name">     </br>
Password: <input type="text" name="pass_word">                    </br>
Email Address: <input type="text" name="email_address">     </br>
Select Plan: <input type="text" name="plan">
Billing method: <input type="text" name="billing_method">     </br>

<script src="paypal-button.js?merchant=4PHXPJ4QRARZE"
    data-button="buynow"
    data-name="My product"
    data-amount="1.00"></script>

Billing Address: <input type="text" name="billing_address">
<input type="submit" value="Submit">
</form>
</br>
</body>
</html>