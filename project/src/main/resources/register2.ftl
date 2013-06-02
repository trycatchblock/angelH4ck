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
					<a href="/account"><li>Account</li></a>
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
    Congratulations ${first_name}! You've signed up for Catchbox. </br>
    Your custom ID# is ${_id}</br>     </br>
    Use it when you ship your mail to    </br>
    123 Main Street                   </br>
    Brooklyn, NY 11204              </br></br>

    Please fill out your profile below:

    <form action="/signed_in" method="POST" class="pure-form pure-form-stacked">
        <fieldset>
            <input type="hidden" name="user_name" value="${user_name}">
            <label for="key_address_name">key address 1 name </label><input type="text" name="key_address_name">
            <label for="key_address">key address 1 </label><input type="text" name="key_address">    </br>
            <label for="key_address2_name">key address2 name<input type="text" name="key_address2_name">
            <label for="key_address2">key address2 <input type="text" name="key_address2">     </br>
            <label for="phone_num">Phone number for SMS notification<input type="text" name="phone_num">
        </fieldset>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>