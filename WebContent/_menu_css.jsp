<style>
@charset "UTF-8";
.third-level-menu
{
    position: absolute;
    top: 0;
    right: -150px;
    width: 150px;
    list-style: none;
    padding: 0;
    margin: 0;
    display: none;
}

.third-level-menu > li
{
    height: 30px;
    background: #999999;
}
.third-level-menu > li:hover { background: #CCCCCC; }

.second-level-menu
{
    position: absolute;
    top: 30px;
    left: 0;
    width: 250px;
    list-style: none;
    padding: 0;
    margin: 0;
    display: none;
}

.second-level-menu > li
{
    position: relative;
    height: 30px;
    background: #999999;
}
.second-level-menu > li:hover { background: #CCCCCC; }

.top-level-menu
{
    list-style: none;
    padding: 0;
    margin: 0;
}

.top-level-menu > li
{
    position: relative;
    float: left;
    height: 30px;
    width: auto;
    padding-right: 20px;
    background: #999999;
}
.top-level-menu > li:hover { background: #CCCCCC; }

.top-level-menu li:hover > ul
{
    /* On hover, display the next level's menu */
    display: inline;
}


/* Menu Link Styles */

.top-level-menu a /* Apply to all links inside the multi-level menu */
{
    font: bold 14px Arial, Helvetica, sans-serif;
    color: #FFFFFF;
    text-decoration: none;
    padding: 0 0 0 10px;

    /* Make the link cover the entire list item-container */
    display: block;
    line-height: 30px;
}
.top-level-menu a:hover { color: #000000; }
</style>

<div style="padding: 3px;padding-left: 0px;padding-bottom: 50px;">
	<ul class="top-level-menu">
		<li><a href="index.jsp">Home</a></li>
		<li><a href="DisplayRentalServlet">Rental Management</a></li>
	    <li>
	        <a href="#">Reservation Management</a>
	        <ul class="second-level-menu">
	            <li><a href="reservation.jsp">New  Reservation</a></li>
	            <li><a href="DisplayReservationServlet">Display Reservation</a></li>
	        </ul>
	    </li>
	    			    <li>
	        <a href="#">Customer Management</a>
	        <ul class="second-level-menu">
	            <li><a href="register_member.jsp">Register New Member</a></li>
	            <li><a href="DisplayMembersServlet">Display Member</a></li>
	        </ul>
	    </li>
	    <li>
	        <a href="#">Inventory Management</a>
	        <ul class="second-level-menu">
	            <li><a href="#">Register Car Model</a></li>
	            <li><a href="DisplayCarModelServlet">Display Car Model</a></li>
	            <li><a href="#">Register Car Item</a></li>
	            <li><a href="DisplayCarItemServlet">Display Car Item</a></li>
	        </ul>
	    </li>
	    <li>
	        <a href="#">Inquiry Management</a>
	        <ul class="second-level-menu">
	            <li><a href="#">Generate Report of Members</a></li>
	            <li><a href="#">Generate Report of Inventory</a></li>
	            <li><a href="#">Generate Report of Reservation</a></li>
	            <li><a href="#">Generate Report of Rental</a></li>
	        </ul>
	    </li>
	</ul>
</div>
