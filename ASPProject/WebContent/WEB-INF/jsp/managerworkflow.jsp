

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TMPS00047</title>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'/>
    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'/>

<link href="<c:url value="/resources/css/loginstylesheet.css" />" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/addemployeestyle.css" />" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="header-wrap">
	<div class="header">
		<div class="logo"><h1>CostLow Pick N Go</h1></div>
		<div class="menu">
        	<ul>
        		<li><a href="index.html" class="active">Home</a></li>
                <li><a href="about.html">About Us          </a></li>
                <li><a href="services.html">Services          </a></li>
                <li><a href="solutions.html">Solutions          </a></li>
                <li><a href="contact.html">Contact</a></li>
        	</ul>
        </div>
	</div>
</div><!---header-wrap--->

<div class="wrap" >


	<div class="leftcol">
		<div class="search">
			<div class="title">
            </div>
        </div>

		<div class="block">
        	<div class="panel">
            	<div class="title">
                </div>
                <div class="content">
                     	
                
                	<%-- <ul>
                    	<li>Name:<spring:input path="userName"/></li>
                        <li>Password:<spring:password path="password"/><br/>  </li>
                        <li><input type="submit" value="login"/>  </li>
                       
                	</ul> --%>
                	<ul>
<li><a href="${pageContext.request.contextPath}/managerController/viewOpenOrders">Click here to view Open Orders</a></li>
<li><a href="${pageContext.request.contextPath}/managerController/viewProcessingOrders">Click here to view Processing Orders</a></li>
<li><a href="${pageContext.request.contextPath}/managerController/viewClosedOrders">Click here to view Closed Orders</a></li>

<li><a href="${pageContext.request.contextPath}/managerController/viewRepresentativePerformance">Click here to view Representative Performance</a></li>




</ul>                
                	
                	 
                	
                	
                	
                </div>
        	</div>
		</div>
        
        
	</div><!---leftcol--->


<div class="rightcol">
	<div class="banner">
	
	<div class="login-card">
	<h1> Add Employee</h1>
	<h1>ORDER ID: ${orderId}</h1>
<spring:form action="${pageContext.request.contextPath}/managerController/processWorkFlow"  modelAttribute="WorkFlow" id="workFlow">  
<spring:hidden path="orderId" value="${orderId}"/><br/>  
<spring:hidden path="managerName" value="${managerName}"/><br/> 

<%-- Role: <spring:select path="role" items="${representativeList}" /><br/> --%>

Select Representative:<spring:select path="representativename">
   <spring:option value="0" label="--- Select ---"/>
   <spring:options items="${representativeList}" />
</spring:select>

<input type="submit" class="login login-submit" value="Process Order"/>  
</spring:form> 

    
                	
                	</div>
	
	</div>
	<div class="page">
		<div class="panel mar-bottom">
			<div class="title">
            	
            </div>
            
        </div>
        
        
        
	</div><!---page--->
</div><!---Rightcol--->
</div>
<div class="footer-wrap">
	<div class="footer">
		<div class="bolg">
            <div class="title">
            	<h1>From Our Bolg</h1>
            </div>
        	<div class="panel mar-right115">
            <div class="content">
            	<ul>
                	<li><img src="images/icon1.png" alt="icon" /></li>
                    <li>Jonathan Jah<br />Nov.2012</li>
            	</ul>
                <p>Nulla nisi urna, egestas non fringilla vitae, molestie vel leo. Proin hendrerit, turpis eu aliquet t in dapibus et, iaculis ac arc.</p>
                <p><a href="#">Read More + </a></p>
            </div>
           </div> 
           
           <div class="panel">
            <div class="content">
            	<ul>
                	<li><img src="images/icon1.png" alt="icon" /></li>
                    <li>Jonathan Jah<br />Nov.2012</li>
            	</ul>
                <p>We clearly help you figure out the number of pages and then include a fee of 1 hour of work per page at our hourly.</p>
                <p><a href="#">Read More + </a></p>
            </div>
           </div>
        </div>
        
        <div class="quickcontact">
        	<div class="title">
            	<h1>Quick Contact</h1>
            </div>
			<div class="panel">
            <div class="content">
            	<ul>
                    <li>Nulla ormare tins<br />sagtis idbibe dumtellus</li>
            	</ul>
                <p>Ultrices posuere cubilia Curae;
				Vestibulum eget libero a felis iaculis fringilla</p>
                <p><span>Tel: (000) 888 88888888</span></p>
            </div>
           </div>
        </div>
    </div>
</div><!---footer--->
<div class="copyright-wrap">
	<div class="copyright">
    	<div class="content">
        	<p>� 2012 All Rights Reserved  |  Privacy Policy   
            Designed by :<a href=" www.alltemplateneeds.com." class="active"> www.alltemplateneeds.com.</a>   
             Images From:<a href=" www.photorack.net"> www.photorack.net</a></p>
        </div>
    </div>
</div><!---copyright-wrap--->

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
</body>
</html>


