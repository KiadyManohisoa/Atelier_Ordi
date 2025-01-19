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

  Composant [] cps = null;
  if(request.getAttribute("cps")!=null) {
    cps = (Composant[]) request.getAttribute("cps");
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
            <li class="breadcrumb-item">Etat stock</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Filtrage des composants</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/etatstock" method="post">
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

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Filtrer</button>
                        </div>
                      </form><!-- End floating Labels Form -->
        
                    <h5 class="card-title">Résultats</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Type</th>
                            <th scope="col">Marque</th>
                            <th scope="col">Modèle</th>
                            <th scope="col">Etat stock</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <%
                              if(cps!=null) {
                                for(int i=0;i<cps.length;i++) {
                                  %>
                                  <tr>
                                    <td><%=cps[i].getId()%></td>
                                    <td><%=cps[i].getTypeComposant().getLibelle()%></td>
                                    <td><%=cps[i].getMarqueComposant().getLibelle()%></td>
                                    <td><%=cps[i].getNomModele()%></td>
                                    <td><%=cps[i].getStockComposant().getReste()%></td>
                                    <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                                  </tr>
                                <% }
                              }
                            %>

                        </tbody>
                        
                      </table>

                    </div>
                  </div>
            </div>

            <div class="modal fade" id="formStock" tabindex="-1" aria-labelledby="StockModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <h5 class="modal-title" id="sellModalLabel">Approvisionnement en stock</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form id="formStockApvr" action="${pageContext.request.contextPath}/composants/stock/approvisionner" method="post" class="row g-3">
                          <div class="modal-body">
                              <input type="hidden" id="selectedId" name="idComposant" value="">
                              <div class="col-md-12 mb-3">
                                  <div class="form-floating">
                                      <input name="dateAprv" type="date" class="form-control" id="" placeholder="Date">
                                      <label for="floatingName">Date d'approvisionnement</label>
                                  </div>
                              </div>
                              <div class="col-md-12 mb-3">
                                  <div class="form-floating">
                                      <input name="qtt" type="number" class="form-control" id="" placeholder="Quantité">
                                      <label for="floatingName">Quantité</label>
                                  </div>
                              </div>
                              <div class="col-md-12 mb-3">
                                  <div class="form-floating">
                                      <input name="pu" type="text" class="form-control" id="" placeholder="Prix unitaire">
                                      <label for="floatingName">Prix unitaire</label>
                                  </div>
                              </div>
                          </div>
                          <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                              <button type="submit" form="formStockApvr" class="btn btn-primary">Approvisionner</button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
          
        </div>
      </section>

      <script>
        document.addEventListener("DOMContentLoaded", function () {
            const stockLinks = document.querySelectorAll(".stock-link"); 
            const hiddenInput = document.getElementById("selectedId"); 
    
            stockLinks.forEach(link => {
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
