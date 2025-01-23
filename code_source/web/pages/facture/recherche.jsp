<%@page import="src.models.materiel.TypeComposant"%>
<%@page import="src.models.materiel.Categorie"%> 
<%@page import="src.models.processus.Reparation"%>

<%
    TypeComposant [] tps = null;
    if(request.getAttribute("tps")!=null) {
        tps = (TypeComposant[]) request.getAttribute("tps");
    }

    Categorie[] cats = null;
    if(request.getAttribute("cats")!=null) {
        cats = (Categorie[]) request.getAttribute("cats");
    }

    Reparation[] reparations = null;
    if(request.getAttribute("reparations")!=null) {
        reparations = (Reparation[]) request.getAttribute("reparations");
    }
%>

<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section recherche</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="accueil.html">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="insertionClient.html">Facture</a></li>
            <li class="breadcrumb-item">Recherche</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                    
                    <h5 class="card-title">Recherche multicritère sur les retours</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/facture/recherche" method="post">
                        <div class="col-md-12">
                            <div class="form-floating mb-3">
                                <select name="idTypeComposant" class="form-select" id="floatingSelect" aria-label="Types de composants">
                                    <option value="">Choisissez un type de composant</option>
                                    <%
                                        if(tps!=null) {
                                            for(int i=0;i<tps.length;i++) { %>
                                                <option value="<%=tps[i].getId()%>"> <%=tps[i].getLibelle()%></option>
                                            <% }
                                        }
                                    %>
                                </select>
                                <label for="floatingSelect">Composant remplacé</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating mb-3">
                                <select name="idCategorie" class="form-select" id="floatingSelect" aria-label="Catégorie d'ordinateurs">
                                    <option value="">Choisissez une catégorie</option>
                                    <%
                                        if(cats!=null) {
                                            for(int i=0;i<cats.length;i++) { %>
                                                <option value="<%=cats[i].getId()%>"> <%=cats[i].getLibelle()%></option>
                                            <% }
                                        }
                                    %>
                                </select>
                                <label for="floatingSelect">Catégorie de l'ordinateur</label>
                            </div>
                        </div>


                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Valider</button>
                        </div>
                      </form><!-- End floating Labels Form -->

                      <h5 class="card-title">Résultats</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Ordinateur</th>
                            <th scope="col">Numéro de série</th>
                            <th scope="col">Propriétaire</th>
                            <th scope="col">Date de récéption</th>
                          </tr>
                        </thead>
                        <tbody>

                        <% if(reparations!=null) {

                            for(int i=0;i<reparations.length;i++) {
                                %>
                                    <tr>
                                        <td><%=reparations[i].getId()%></td>
                                        <td><%=reparations[i].getOrdinateur().getMarque().getLibelle()%> <%=reparations[i].getOrdinateur().getModel()%></td>
                                        <td><%=reparations[i].getOrdinateur().getNumeroSerie()%></td>
                                        <td><%=reparations[i].getClient().getNom()%> <%=reparations[i].getClient().getPrenom()%></td>
                                        <td><%=reparations[i].getDateReception()%></td>
                                          
                                    </tr>
                        <% } } %> 
                        </tbody>
                        
                      </table>
                      <!-- End Table with stripped rows -->
        
                    </div>
                  </div>
            </div>
        </div>
      </section>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
