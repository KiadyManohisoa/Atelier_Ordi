<%@page import="src.models.materiel.MarqueComposant"%>
<%@page import="src.models.materiel.TypeComposant"%>
<%@page import="src.models.materiel.Composant"%>

<%

  TypeComposant [] tps = null;
  if(request.getAttribute("tps")!=null) {
    tps = (TypeComposant[]) request.getAttribute("tps");
  }

  MarqueComposant [] marques = null;
  if(request.getAttribute("marques")!=null) {
    marques = (MarqueComposant[]) request.getAttribute("marques");
  }

  Composant [] composants = null;
  if(request.getAttribute("composants")!=null) {
    composants = (Composant[]) request.getAttribute("composants");
  }


%>


<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section composants</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Composants</a></li>
            <li class="breadcrumb-item">Insertion</li>
          </ol>
        </nav>
      </div>


      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Nouveau composant</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/insertion" method="post">

                        <div class="col-md-12">
                          <div class="form-floating">
                            <select name="idTypeComposant" class="form-select" id="floatingSelect" aria-label="Types de composants">
                                <option value="">Choisissez un type de composant</option>
                                <% if(tps!=null) {
                                    for(int i=0;i<tps.length;i++) {
                                      %>
                                      <option value="<%=tps[i].getId()%>"> <%=tps[i].getLibelle()%> </option>
                                    <% }
                                } %> 
                            </select>
                            <label for="floatingSelect">Composant</label>
                          </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating">
                              <select name="idMarqueComposant" class="form-select" id="floatingSelect" aria-label="Marques de composants">
                                  <option value="">Choisissez une marque de composant</option>
                                  <% if(marques!=null) {
                                      for(int i=0;i<marques.length;i++) {
                                        %>
                                        <option value="<%=marques[i].getId()%>"> <%=marques[i].getLibelle()%> </option>
                                      <% }
                                  } %> 
                              </select>
                              <label for="floatingSelect">Marque</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="nomModele" type="text" class="form-control" id="" placeholder="Modèle">
                              <label for="floatingName">Nom du modèle</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="prixVente" type="text" class="form-control" id="" placeholder="Prix de vente">
                              <label for="floatingName">Prix de vente</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating mb-3">
                                <textarea name="description" class="form-control" placeholder="Description" id="floatingTextarea" style="height: 100px;"></textarea>
                                <label for="floatingTextarea">Description</label>
                            </div>
                        </div>

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Insérer</button>
                        </div>
                      </form><!-- End floating Labels Form -->
        
                    <h5 class="card-title">Liste des composants</h5>
        
                    <!-- Table with stripped rows -->
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Identifiant</th>
                          <th scope="col">Nom modèle</th>
                          <th scope="col">Type</th>
                          <th scope="col">Marque</th>
                          <th scope="col">Prix de vente</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>

                      <%if(composants!=null) { 
                          for(int i=0;i<composants.length;i++) { %>
                          <tr>
                              <td><%=composants[i].getId()%></td>
                              <td><%=composants[i].getNomModele()%></td>
                              <td><%=composants[i].getTypeComposant().getLibelle()%></td>
                              <td><%=composants[i].getMarqueComposant().getLibelle()%></td>
                              <td><%=composants[i].getPrixVente()%></td>
                              <td>
                                <a href="#" class="update-link" data-bs-toggle="modal" data-bs-target="#updateForm">
                                  <i class="bi bi-pencil-square"></i>
                                </a>
                              </td>
                          </tr>
                      <% } } %>

                      </tbody>
                    </table>

                    </div>
                  </div>
            </div>

          <div class="modal fade" id="updateForm" tabindex="-1" aria-labelledby="updateFormLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="formRetourLabel">Modification du composant</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/composant/mettreAjour" method="post">
                  <div class="modal-body">
                    <input type="hidden" name="idComposant" id="selectedId">

                    <div class="form-floating mb-3">
                      <input name="nouveauPrixVente" type="text" class="form-control" id="coutVente" placeholder="0.00">
                      <label for="coutVente">Nouveau prix de vente </label>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Valider</button>
                  </div>
                </form>
              </div>
            </div>
          </div>

        </div>
      </section>

      <script>
        document.addEventListener("DOMContentLoaded", function () {
            const updateLinks = document.querySelectorAll(".update-link"); 
            const hiddenInput = document.getElementById("selectedId"); 
    
            updateLinks.forEach(link => {
                link.addEventListener("click", function (event) {
                    event.preventDefault(); 
                    const row = this.closest("tr"); 
                    const id = row.querySelector("td:first-child").textContent.trim(); 
                    hiddenInput.value = id; 
                });
            });
        });
    </script>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
