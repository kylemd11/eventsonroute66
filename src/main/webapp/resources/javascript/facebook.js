function displayUser(user) {
  window.opener.document.getElementById('facebookForm:usernameFacebook').value = user.username;
  window.opener.document.getElementById('facebookForm:facebooksubmit').click();
  window.close();  
}
     
function facebook_login() {
   var appID = 474286489251115;

   if (window.location.hash.length == 0) {
      var path = 'https://www.facebook.com/dialog/oauth?';
      var queryParams = ['client_id=' + appID,'redirect_uri=' + window.location,'response_type=token'];
      var query = queryParams.join('&');
      var url = path + query;
      window.open(url);
   } 
   else {
       var accessToken = window.location.hash.substring(1);
       var path = "https://graph.facebook.com/me?";
       var queryParams = [accessToken, 'callback=displayUser'];
       var query = queryParams.join('&');
       var url = path + query;

   // use jsonp to call the graph
       var script = document.createElement('script');
       script.src = url;
       document.body.appendChild(script);        
  }
}

function facebook_phase2() {
	if (window.location.hash.length != 0) {
	       var accessToken = window.location.hash.substring(1);
	       var path = "https://graph.facebook.com/me?";
	       var queryParams = [accessToken, 'callback=displayUser'];
	       var query = queryParams.join('&');
	       var url = path + query;
	
	   // use jsonp to call the graph
	       var script = document.createElement('script');
	       script.src = url;
	       document.body.appendChild(script);
	    }  
}