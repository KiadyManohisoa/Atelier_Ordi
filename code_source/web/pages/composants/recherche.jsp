<%@page import="src.models.processus.Recommandations"%>
<%@page import="src.models.materiel.Composant"%>
<%@page import="src.models.materiel.ComposantDuMois"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>


<%
  List<ComposantDuMois> composants = null;
  if(request.getAttribute("cdm")!=null) {
    composants = (List<ComposantDuMois>) request.getAttribute("cdm");
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
            <li class="breadcrumb-item">Recherche</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Recheche mensuel des composants recommandés</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/recommandes/recherche" method="post">
                        <div class="col-md-12">
                          <div class="form-floating">
                            <input name="periode" type="month" class="form-control" id="nom" placeholder="Nom">
                            <label for="floatingName">Periode</label>
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
                            <th scope="col">Nom modèle</th>
                            <th scope="col">Type</th>
                            <th scope="col">Marque</th>
                            <th scope="col">Description</th>
                          </tr>
                        </thead>
                        <tbody>

                        <%if(composants!=null) { 
                            for(int i=0;i<composants.size();i++) { %>
                            <tr>
                                <td><%=composants.get(i).getId()%></td>
                                <td><%=composants.get(i).getNomModele()%></td>
                                <td><%=composants.get(i).getTypeComposant().getLibelle()%></td>
                                <td><%=composants.get(i).getMarqueComposant().getLibelle()%></td>
                                <td><%=composants.get(i).getDescription()%></td>
                            </tr>
                        <% } } %>

                        </tbody>
                        
                      </table>

                    </div>
                  </div>
            </div>
        </div>
      </section>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
