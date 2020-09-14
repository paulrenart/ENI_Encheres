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
            <div class="col-lg-3 offset-lg-6">S'inscrire / se connecter</div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <h4>Mon Profil</h4>
            </div>
        </div>
        <div class="row">
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
            <form class="col-lg-10 offset-lg-1" action="<%=request.getContextPath()%>/EditProfile" method="post">
                <div class="form-group row">
                    <div class="col-lg-2">
                        Pseudo:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="login" name="login" class="form-control">
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        Nom:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="nom" name="nom" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-2">
                        Prénom:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="prenom" name="prenom" class="form-control">
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        Email:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="email" name="email" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-2">
                        Téléphone:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="telephone" name="telephone" class="form-control">
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        Rue:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="rue" name="rue" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-2">
                        Code postal:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="code_postal" name="code_postal" class="form-control">
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        Ville:
                    </div>
                
                    <div class="col-lg-3">
                        <input type="text" id="ville" name="ville" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-2">
                        Mot de passe actuel:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="mot_de_passe" name="mot_de_passe" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-2">
                        Nouveau Mot de passe:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="mot_de_passe" name="mot_de_passe" class="form-control">
                    </div>
                    <div class="col-lg-2 offset-lg-1">
                        Confirmation:
                    </div>
                    <div class="col-lg-3">
                        <input type="text" id="confirmation" name="confirmation" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
	                <% 	String credit = (String)request.getAttribute("credit");
						if(credit!=null)
						{
					%>
						<div class="col-lg-2">
                        Crédit:
	                    </div>
	                    <div class="col-lg-3">
	                        <%=credit%>
	                    </div>
					<%		
						}
					%>
				</div>
                <div class="form-group row">
                    <button class="col-lg-4 offset-lg-1 btn btn-primary" type="submit">Enregister</button>
                    <button class="col-lg-4 offset-lg-2 btn btn-primary" type="submit" name="delete" value="delete">Supprimer mon compte</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>