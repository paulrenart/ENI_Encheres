<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ENI - Enchères</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous"></head>
    <style>
        .top-buffer { margin-top:10px; }
        #listeArticles {
		    padding:0;
		    list-style: none;
		}
		#listeArticles li{
		    display:block;
		    text-align: center;
		    text-decoration:none;
		    box-shadow: 0px 0px 4px 0px #c0c0c0;
		    padding-top:10px;
		    padding-bottom:5px;
		    margin-bottom:15px;
		    min-width: 180px;
		}
    </style>
    <%
	   Object userId = session.getAttribute("uid");
	   if(userId == null) {
	      response.sendRedirect(request.getContextPath());
	   }
	%>
<body>

<div class="container">
	<%@include file="includes/navbar.jsp" %>
	<%@include file="../includes/index_core.jsp" %>
</body>
</html>