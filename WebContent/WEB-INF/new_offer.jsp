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
                <h4>Nouvelle Vente</h4>
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
            <form class="col-lg-10 offset-lg-1" action="<%=request.getContextPath()%>/NewOffer" method="post" enctype="multipart/form-data">
                <div class="form-group row">
                    <div class="col-lg-4">
                        Article:
                    </div>
                    <div class="col-lg-8">
                        <input type="text" id="article" name="article" class="form-control" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        Description:
                    </div>
                    <div class="col-lg-8">
                        <textarea id="description" name="description" rows="3" class="form-control" required></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        Catégorie
                    </div>
                    <div class="col-lg-8">
                        <select name="categorie" id="categorie">
                        	<option value="1">1</option>
                        	<option value="2">2</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        Photo
                    </div>
                    <div class="col-lg-8">
                        <input type="file" id="photo" name="photo" class="form-control" accept="image/*" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        Mise à prix:
                    </div>
                    <div class="col-lg-8">
                        <input type="number" id="prix" name="prix" class="form-control" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        Début de l'enchère:
                    </div>
                    <div class="col-lg-8">
                    	<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
                        <input type="date" id="debut" name="debut" class="form-control" value=<%= df.format(new java.util.Date()) %> required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-4">
                        fin de l'enchère:
                    </div>
                    <div class="col-lg-8">
                        <input type="date" id="fin" name="fin" class="form-control" required>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-lg-12">
                    	<fieldset class="border p-2">
                    		<legend class="w-auto">Retrait</legend>
	                        <div class="form-group row">
	                        	<% 	String rue = (String)request.getAttribute("rue"); %>
			                    <div class="col-lg-4">
			                        Rue:
			                    </div>
			                    <div class="col-lg-8">
			                        <input type="text" id="rue" name="rue" class="form-control" value="<%=rue%>" required>
			                    </div>
			            	</div>
			            	<div class="form-group row">
			            		<% 	String code_postal = (String)request.getAttribute("code_postal"); %>
			                    <div class="col-lg-4">
			                        Code postal:
			                    </div>
			                    <div class="col-lg-8">
			                        <input type="text" id="code_postal" name="code_postal" class="form-control" value="<%=code_postal%>" required>
			                    </div>
			            	</div>
			            	<div class="form-group row">
			            		<% 	String ville = (String)request.getAttribute("ville"); %>
			                    <div class="col-lg-4">
			                        ville:
			                    </div>
			                    <div class="col-lg-8">
			                        <input type="text" id="ville" name="ville" class="form-control" value="<%=ville%>" required>
			                    </div>
			            	</div>
	                    </fieldset>
	                </div>
                </div>
                <div class="form-group row">
                    <button class="col-lg-4 offset-lg-1 btn btn-primary" type="submit">Créer</button>
                    <button class="col-lg-4 offset-lg-2 btn btn-primary" type="reset">Annuler</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>