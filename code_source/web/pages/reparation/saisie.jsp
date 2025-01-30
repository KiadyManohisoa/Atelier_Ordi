<%@page import="src.models.clients.Client"%>
<%@page import="src.models.materiel.MarqueOrdi"%>
<%@page import="src.models.materiel.Composant"%>
<%@page import="src.models.materiel.TypeComposant"%>
<%@page import="src.models.materiel.Categorie"%>
<%@page import="src.models.processus.Technicien"%>
<%@page import="src.models.composants.ComposantParType"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
  Client [] clts = null;
  if(request.getAttribute("clts")!=null) {
    clts = (Client[]) request.getAttribute("clts");
  }

  MarqueOrdi [] marques = null;
  if(request.getAttribute("marques")!=null) {
    marques = (MarqueOrdi[]) request.getAttribute("marques");
  }

  Categorie [] cats = null;
  if(request.getAttribute("cats")!=null) {
    cats = (Categorie[]) request.getAttribute("cats");
  }

  Technicien [] techs = null;
  if(request.getAttribute("techs")!=null) {
    techs = (Technicien[]) request.getAttribute("techs");
  }

  ComposantParType [] cParTypes = null;
  if(request.getAttribute("cParTypes")!=null) {
    cParTypes = (ComposantParType[]) request.getAttribute("cParTypes");
  }

%>

<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section saisie</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="accueil.html">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="insertionClient.html">Reparation</a></li>
            <li class="breadcrumb-item">Saisie</li>
          </ol>
        </nav>
    </div>

    <section class="section">
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Saisie de l'ordinateur à réparer</h5>
    
              <!-- Formulaire unique -->
              <form class="row g-3" action="${pageContext.request.contextPath}/reparation/saisie" method="post">
                <div class="row">
                  <!-- Section 1 -->
                  <div class="col-md-6 section1">
                    <h6 class="fw-semibold text-muted">1) Information client et machine :</h6>
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <input name="dateReception" type="date" class="form-control" id="dateReception" placeholder="Date de réception">
                        <label for="dateReception">Date de réception</label>
                      </div>
                    </div>
    
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <select name="idClient" class="form-select" id="floatingSelectClient" aria-label="Choisissez le client">
                          <option value="">Choisissez le client propriétaire</option>
                          <% if(clts!=null) { for(int i=0;i<clts.length;i++) { %>
                          <option value="<%=clts[i].getId()%>"><%=clts[i].getNom()%> <%=clts[i].getPrenom()%></option>
                          <% } } %>
                        </select>
                        <label for="floatingSelectClient">Propriétaire</label>
                      </div>
                    </div>
    
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <select name="idMarque" class="form-select" id="floatingSelectMarque" aria-label="Choisissez la marque">
                          <option value="">Choisissez la marque de l'ordinateur</option>
                          <% if(marques!=null) { for(int i=0;i<marques.length;i++) { %>
                          <option value="<%=marques[i].getId()%>"><%=marques[i].getLibelle()%></option>
                          <% } } %>
                        </select>
                        <label for="floatingSelectMarque">Marque</label>
                      </div>
                    </div>

                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <select name="idCategorie" class="form-select" id="floatingSelectCat" aria-label="Choisissez une catégorie">
                          <option value="">Choisissez la catégorie de l'ordinateur</option>
                          <% if(cats!=null) { for(int i=0;i<cats.length;i++) { %>
                          <option value="<%=cats[i].getId()%>"><%=cats[i].getLibelle()%></option>
                          <% } } %>
                        </select>
                        <label for="floatingSelectMarque">Catégorie</label>
                      </div>
                    </div>
    
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <input name="nomModele" type="text" class="form-control" id="modele" placeholder="Modèle">
                        <label for="modele">Modèle</label>
                      </div>
                    </div>
    
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <input name="numeroSerie" type="text" class="form-control" id="numeroSerie" placeholder="Numéro de série">
                        <label for="numeroSerie">Numéro de série</label>
                      </div>
                    </div>
    
                    <div class="col-md-12">
                      <div class="form-floating mb-3">
                        <input name="anneeSortie" type="number" class="form-control" id="anneeSortie" placeholder="Année de sortie">
                        <label for="anneeSortie">Année de sortie</label>
                      </div>
                    </div>
                  </div>
    
                  <!-- Section 2 -->
                  <div class="col-md-6 section2 border-start">
                    <h6 class="fw-semibold text-muted">2) Panne des composants</h6>
                    <div id="dynamicFields">
                      <div class="row align-items-center mb-3">
                        <div class="col-md-6">
                          <select name="typeComposant[]" class="form-select">
                            <option value="" selected>Type de composant</option>
                          </select>
                        </div>
                        <div class="col-md-6">
                          <input name="descriptionPanne[]" type="text" class="form-control" placeholder="Description de la panne">
                        </div>
                      </div>
                    </div>
                    <button type="button" id="addField" class="btn btn-link">+ Autres</button>
                  </div>
                  
                </div>

                <hr class="my-4">
    
                <!-- Section 3 -->
              <div class="col-md-6 section3">
                  <h6 class="fw-semibold text-muted">3) Composants à remplacer</h6>
                  <div class="col-md-12">
                      <%
                      if (cParTypes != null) {
                          for (int i = 0; i < cParTypes.length; i++) { 
                              TypeComposant typeComposant = cParTypes[i].getTypeComposant();
                              List<Composant> composants = cParTypes[i].getComposants();
                      %>
                              <div class="d-flex align-items-center gap-3 mb-2">
                                  <div class="form-check">
                                      <input 
                                          name="composantsRemplaces[]" 
                                          class="form-check-input composant-checkbox" 
                                          type="checkbox" 
                                          value="<%= typeComposant.getId() %>" 
                                          id="checkbox-<%= typeComposant.getId() %>"
                                          data-type-id="<%= typeComposant.getId() %>"
                                      >
                                      <label class="form-check-label" for="checkbox-<%= typeComposant.getId() %>">
                                          <%= typeComposant.getLibelle() %>
                                      </label>
                                  </div>
                                  <div id="select-container-<%= typeComposant.getId() %>" class="composant-select-container flex-grow-1" style="display: none;">
                                      <select 
                                          name="composantSelectionne[]" 
                                          class="form-select composant-select" 
                                          id="select-<%= typeComposant.getId() %>"
                                      >
                                          <option value="">Choisir un composant</option>
                                          <%
                                          for (Composant composant : composants) {
                                          %>
                                              <option value="<%= composant.getId() %>">
                                                  <%= composant.getMarqueComposant().getLibelle() %> - <%= composant.getNomModele() %>
                                              </option>
                                          <%
                                          }
                                          %>
                                      </select>
                                  </div>
                              </div>
                      <%
                          }
                      }
                      %>
                  </div>
              </div>


                <div class="col-md-6 section4 border-start">
                  <h6 class="fw-semibold text-muted">4) Technicien responsable :</h6>
                  <div class="col-md-12">
                    <div class="form-floating mb-3">
                      <select name="idTechnicien" class="form-select" id="floatingSelectClient" aria-label="Choisissez le technicien">
                        <option value="">Choisissez le technicien responsable</option>
                        <% if(techs!=null) { for(int i=0;i<techs.length;i++) { %>
                        <option value="<%=techs[i].getId()%>"><%=techs[i].getNom()%> <%=techs[i].getPrenom()%></option>
                        <% } } %>
                      </select>
                      <label for="floatingSelectClient">Technicien</label>
                    </div>
                  </div>
                </div>
    
                <!-- Bouton Valider en bas -->
                <div class="text-center mt-4">
                  <button type="submit" class="btn btn-primary">Valider</button>
                </div>
    
              </form>
    
            </div>
          </div>
        </div>
      </div>
    </section>
    
      
    <script>
      const composants = [
        <% if (cParTypes != null) {
          for (int i = 0; i < cParTypes.length; i++) { %>
            { id: "<%= cParTypes[i].getTypeComposant().getId() %>", libelle: "<%= cParTypes[i].getTypeComposant().getLibelle() %>" },
        <% } } %>
      ];
    </script>

    <script src="${pageContext.request.contextPath}/web/assets/js/utils/saisieReparation.js"></script>
    
      

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
