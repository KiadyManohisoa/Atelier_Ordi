<%@page import="src.models.materiel.MarqueComposant"%>
<%@page import="src.models.materiel.TypeComposant"%>

<%

  TypeComposant [] tps = null;
  if(request.getAttribute("tps")!=null) {
    tps = (TypeComposant[]) request.getAttribute("tps");
  }

  MarqueComposant [] marques = null;
  if(request.getAttribute("marques")!=null) {
    marques = (MarqueComposant[]) request.getAttribute("marques");
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
                            <div class="form-floating mb-3">
                                <textarea name="description" class="form-control" placeholder="Description" id="floatingTextarea" style="height: 100px;"></textarea>
                                <label for="floatingTextarea">Description</label>
                            </div>
                        </div>

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Insérer</button>
                        </div>
                      </form><!-- End floating Labels Form -->
        
                    </div>
                  </div>
            </div>
        </div>
      </section>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
