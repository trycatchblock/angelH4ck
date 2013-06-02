<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="http://198.199.87.209/style.css">
	<link rel="stylesheet" href="http://198.199.87.209/assets/css/pure-min.css">
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<title>Register</title>
</head>

<body>
	<header>
		<div class="container section row">
			<div class="col five">
				<a href="#">
					<img src="http://198.199.87.209/catchbox_logo.png" />
					<h1>CatchBox</h1>
				</a>
			</div>

			<nav class="col seven">
				<ul class="nav">
					<a href="#"><li>How It Works</li></a>
					<a href="#"><li>FAQ</li></a>
					<a href="#" class="login"><li>Login</li></a>
				</ul>

				<div class="loginBox hide">
					<form class="pure-form">
						<fieldset>
							<input type="email" placeholder="Email" required />
							<input type="password" placeholder="Password" required />
							<input type="submit" class="pure-button" value="Submit" />
						</fieldset>	
					</form>
				</div>
			</nav>
		</div>
	</header>

<div class="clear"></div>

<div class="container section row">
<h2>Register</h2>
<form action="/register_2" method="POST" class="pure-form pure-form-stacked">
  <fieldset>
    <label for="first_name">First Name: </label><input type="text" name="first_name">
    <label for="last_name">Last Name: </label><input type="text" name="last_name">
    <label for="user_name">User Name: </label><input type="text" name="user_name">
    <label for="pass_word">Password: </label><input type="password" name="pass_word"> 
    <label for="email_address">Email Address: </label><input type="text" name="email_address">
    <label for="plan">Select Plan: </label><input type="text" name="plan">
    <!--<label for="billing_method">Billing method: </label><input type="text" name="billing_method">-->
    <br/>
    <script src="http://198.199.87.209/assets/js/paypal-button.js?merchant=4PHXPJ4QRARZE"
	data-button="buynow"
	data-name="My product"
	data-amount="1.00"></script>

    <!--<label for="billing_address">Billing Address: </label><input type="text" name="billing_address">-->
  </fieldset>
<input type="submit" value="Submit">
</form>
</div>

</br>
</body>
</html>