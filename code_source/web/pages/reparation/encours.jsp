<%@page import="src.models.processus.Reparation"%>
<%@page import="src.models.materiel.Ordinateur"%>
<%@page import="src.models.materiel.MarqueOrdi"%>
<%@page import="src.models.clients.Client"%>

<%

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
            <li class="breadcrumb-item"> <a href="insertionClient.html">Reparation</a></li>
            <li class="breadcrumb-item">En cours</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        
                      <h5 class="card-title">Liste des réparations en cours</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Ordinateur</th>
                            <th scope="col">Numéro de série</th>
                            <th scope="col">Propriétaire</th>
                            <th scope="col">Date de récéption</th>
                            <th></th>
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
                                        <td><a href="#" class="retour-link" data-bs-toggle="modal" data-bs-target="#formRetour">Retourner</a></td>
                                    </tr>
                        <% } } %>
                        </tbody>
                        
                      </table>
                      <!-- End Table with stripped rows -->
        
                    </div>
                  </div>
            </div>

          <div class="modal fade" id="formRetour" tabindex="-1" aria-labelledby="formRetourLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="formRetourLabel">Formulaire de facturation</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${pageContext.request.contextPath}/reparation/facturation" method="post">
                  <div class="modal-body">
                    <input type="hidden" name="idReparation" id="selectedId">

                    <div class="form-floating mb-3">
                      <input name="dateFacturation" id="dateFacturation" type="date" class="form-control">
                      <label for="dateFacturation">Date de facturation</label>
                    </div>

                    <div class="form-floating mb-3">
                      <input name="coutMainDoeuvre" type="text" class="form-control" id="coutMainDoeuvre" placeholder="0.00">
                      <label for="coutMainDoeuvre">Coût de la main d'œuvre </label>
                    </div>

                    <div class="form-floating mb-3">
                      <input name="pourcentageCommission" type="text" class="form-control" id="pourcentageCommission" placeholder="0.00">
                      <label for="pourcentageCommission">Pourcentage de commission (%)</label>
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
            const retourLinks = document.querySelectorAll(".retour-link"); 
            const hiddenInput = document.getElementById("selectedId"); 
    
            retourLinks.forEach(link => {
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
