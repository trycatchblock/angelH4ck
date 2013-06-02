<html>
<head>
<title>Account page</title>
</head>
<body>
Hello welcome back ${firstname}!<br>
Here, you can view
Table of current queue

<#list deliveries as delivery>
<p>
fromLocation: ${delivery.fromLocation} </br>
ExpectedOnDate: ${delivery.ExpectedOnDate}             </br>
DeliveryStatus:${delivery.DeliveryStatus}     </br>
DeliveryDate:  ${delivery.DeliveryDate}    </br>
DeliveryLocation:  ${delivery.DeliveryLocation}
 </p>
</p>
</#list>
List of key addresses
 <#list addresses as address>
 <p>
 address_name: ${address.address_name} </br>
 address: ${address.address}    </br>
 <img src="${address.mapURL}">
  </p>
 </p>
 </#list>


How you will be notified:
<#list notifications as notification>
<p>
notification: ${notification} </br>
</p>
</p>
</#list>


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
</body>
</html>