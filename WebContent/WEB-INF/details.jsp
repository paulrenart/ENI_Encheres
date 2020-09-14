<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-3"><h3><a href="<%=request.getContextPath()%>/Index">Eni Enchères</a></h3></div>
        </div>
        <br>
        <div class="row">
        Détail vente
        </div>
        <% 	String error = (String)request.getAttribute("error");
			if(error!=null)
			{
		%>
			<div class="row">
				<p style="color:red"><%=error%></p>
			</div>
		<%		
			}
		%>
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="row">
                    ${article.nom_article}
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Description:
                    </div>
                    <div class="col-lg-6">
                    	${article.description}
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Catégorie:
                    </div>
                    <div class="col-lg-6">
                    	${article.no_categorie}
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Meilleure offre:
                    </div>
                    <div class="col-lg-6">
                    	${article.prix_vente}
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Mise à prix:
                    </div>
                    <div class="col-lg-6">
                    	${article.prix_initial}
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Retrait:
                    </div>
                    <div class="col-lg-6">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                    	Vendeur:
                    </div>
                    <div class="col-lg-6">
                    	${article.no_utilisateur}
                    </div>
                </div>
                <div class="row">
	                <% 	String owner = (String)request.getAttribute("owner");
						if(owner == "false")
						{
					%>
						<div class="col-lg-6">
	                    	Ma proposition:
	                    </div>
	                    <div class="col-lg-6">
	                    	<form class="row" action="<%=request.getContextPath()%>/Details?no_article=${article.no_article}" method="post">
	                    		<input type="number" id="enchere" name="enchere" class="form-control" required>
					           	<button class="col-lg-4 offset-lg-4 btn btn-primary" type="submit">Enchérir</button>
					       </form>
	                    </div>
						 
					<%
						}
					%>
                    
                </div>
            </div>
        </div>
        <br>
        
       
    </div>
</body>
</html>