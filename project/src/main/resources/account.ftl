
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Account | CatchBox</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="http://198.199.87.209/style.css">
	<link rel="stylesheet" href="http://198.199.87.209/assets/css/pure-min.css">
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
</head>

<body>
	<header>
		<div class="container section row">
			<div class="col five">
				<a href="#">
					<h1>CatchBox</h1>
				</a>
			</div>

			<nav class="col seven">
				<ul class="nav">
					<a href="#"><li>How It Works</li></a>
					<a href="#"><li>FAQ</li></a>
					<a href="#"><li>Account</li></a>
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

	<section id="account" class="row section">
		<div class="container">
			<div class="col ten">
				<div class="welcome">
					<h2>Hey Sam!</h2>
					<p>Welcome back. On this page, you can view your expected deliveries, access past delivery history, and modify your key addresses. Need a refresher on package interception by CatchBox? <a href="#">We've got you covered.</a>
				</div>

				<div class="queue">
					<h2>Delivery Queue</h2>

					<div class="cardTop">
						<h3>Contact Lens Refill</h3>
						<div class="fromLocation"><span>Merchant:</span> FourEyes Inc</div>
					</div>
					<div class="cardBottom">
					<div class="clear"></div>
						<div class="statusBar transit">
							<div class="deliveryStatus">
								<span class="DeliveryStatus">In Transit</span> to <span class="DeliveryLocation">Work</span>. Expected on <time>May 31st.</time>
							</div>
						</div>
					</div>

					<div class="cardTop">
						<h3>Shoes - LL Bean</h3>
						<div class="fromLocation"><span>Merchant:</span> Amazon LLC</div>
					</div>
					<img src="http://maps.google.com/maps/api/staticmap?center=+40.758178710938,-73.808700561523&zoom=13&size=900x300&sensor=false" />
					<div class="cardBottom">
					<div class="clear"></div>
						<div class="statusBar">
							<div class="deliveryStatus">
								<span class="DeliveryStatus">Delivered</span> to <span class="DeliveryLocation">Home</span> on <time>May 24th.</time>
							</div>
						</div>
					</div>

					<a href="#">View past deliveries</a>
				</div>


			</div>
		</div>
	</section>


<section id="keyAddress">
	<div class="row section container">
		<h2>Key Addresses</h2>
	</div>

    <div class="row section container">
     <#list addresses as address>
		<div class="col four" >
			<h4>Home</h4>
			<span><a href="#">Edit</a> | <a href="#">Delete</a></span>
			<div class="clear"></div>
			<address>
			${address.address_name} <br />
			${address.address}
			</address>
		</div>
     </#list>

</section>

<!--
live map of delivery guy
input delivery tracking info

</br>
</br>
<a href="/contact">contact</a>
</br>
<a href="/faq">faq</a>
</br>
<a href="/settings">settings</a>
</br>
<a id="" href="#">Sync mailbox with context.io</a>
-->

<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <script>
  $(function() {
    //$( "#dialog" ).dialog();
  });
  </script>


<div id="dialog" title="Basic dialog">
    <!-- <iframe src="http://198.199.87.209/context.io.php" width="500" height="500" style="display:none;"></iframe> -->
</div>
</body>
</html>