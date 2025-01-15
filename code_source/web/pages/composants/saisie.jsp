<%@page import="src.models.materiel.Composant"%>

<%
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
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Recommandations</a></li>
            <li class="breadcrumb-item">Saisie</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Saisie de composants recommandés</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/recommandes" method="post">
                        <div class="col-md-12">
                          <div class="form-floating">
                            <input name="periode" type="month" class="form-control" id="nom" placeholder="Nom">
                            <label for="floatingName">Periode</label>
                          </div>
                        </div>

                        <% if(composants!=null) { 
                            for(int i=0;i<composants.length;i++) { %>

                            <div class="col-md-12">
                                <div class="form-check">
                                    <input name="composantsRemplaces[]" value="<%=composants[i].getId()%>" class="form-check-input" type="checkbox" id="cpuCheck">
                                    <label class="form-check-label" for="cpuCheck"> <%=composants[i].getNomModele()%> </label>
                                </div>
                            </div>


                        <% } } %>

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Valider</button>
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
