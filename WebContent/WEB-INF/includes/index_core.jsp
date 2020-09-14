<div class="row">
    <p class="col-lg-12 text-center">Liste des enchères</p>
</div>
<div class="row ">
    <div class="col-lg-12">
    <!--
        <div class="row">
            <div class="col-lg-12">Filtres:</div>
        </div>
        <div class="row top-buffer">
            <div class="search-container col-lg-12">
                <form action="/action.jsp">
                  <input type="text" placeholder="Rechercher" name="search">
                  <button class="btn" type="submit"><i class="fa fa-search"></i></button>
                </form>
              </div>
        </div>
        
        <div class="row top-buffer">
            <div class="col-lg-4">Catégorie:</div>
            <div class="dropdown col-lg-8">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Toutes
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                <c:forEach var="no_categorie" items="${categories}">
					   <c:forEach var="libelle" items="${no_categorie.value}">
					        <a class="dropdown-item" href="#">${libelle}</a>
					   </c:forEach>
					</c:forEach>
                </div>
            </div>
        </div>
        <br>
          -->
        <div class="row">
             <c:choose>
	    		<c:when test="${listArticles.size()>0}">
		        	<c:forEach var="c" items="${listArticles}">
		        		<c:set var="no_categorieAsString">${c.no_categorie}</c:set>
			            <div class="col-lg-4">
				            <div class="row">
				            	<div class="col-lg-6">
				            		<c:choose>
									    <c:when test="${c.image_path==null}">
									        <img src="/datas/no_image.png" class="img-fluid">
									    </c:when>    
									    <c:otherwise>
									        <img src="${c.image_path}" class="img-fluid">
									    </c:otherwise>
									</c:choose>
				            	</div>
				            	<div class="col-lg-6">
					            <u>${c.nom_article}</u><br>
					            Prix: ${c.prix_vente}<br>
					            Fin de l'enchère: ${c.date_fin_encheres}<br>
					            <c:set var="no_user">${c.no_utilisateur}</c:set>
					            Vendeur: ${users[no_user]}<br>
					            <a href="<%=request.getContextPath()%>/Details?no_article=${c.no_article}" style="display:block;">Détails</a>
					            </div>
				            </div>
				        </div>
			      	</c:forEach>
		        </c:when>
		        <c:otherwise>
		        	<p>Pas de liste actuellement.<p>
		        </c:otherwise>
	        </c:choose>
	    </div>
    </div>
</div>