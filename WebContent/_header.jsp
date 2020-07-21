<div style="background: #E0E0E0; height: 75px; padding: 5px;">
  <div style="float: left; margin-top: 0px">
     <h1>CAR RENTAL SYSTEM</h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
  	<!-- User store in session with attribute: loginedUser -->
    Welcome    
	<b><%if (session.getAttribute("email") != null){
			out.println(session.getAttribute("email"));
	}%>
	<a href="#">My Account</a>
	</b>
		
    <br/>
    <a href="login.jsp">Login</a> / <a href="LogoutServlet">Logout</a>
    <br/>
    <a href="register_member.jsp">Sign-up as a member</a>
    <!-- Search <input name="search">-->
  </div>
 
</div>