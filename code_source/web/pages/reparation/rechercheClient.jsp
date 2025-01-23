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
            <li class="breadcrumb-item">Recherche</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                    
                    <h5 class="card-title">Recherche par date (clients)</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/reparation/recherche/client" method="post">
                        <div class="col-md-12">
                            <div class="form-floating mb-3">
                            <input name="dateRecherche" type="date" class="form-control" id="date" placeholder="date">
                            <label for="floatingSelect">Date de recherche</label>
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
                            <th scope="col">Id client</th>
                            <th scope="col">Id reparation</th>
                            <th scope="col">Client</th>
                            <th scope="col">Ordinateur</th>
                            <th scope="col">Date de récéption</th>
                          </tr>
                        </thead>
                        <tbody>

                        <% if(reparations!=null) {

                            for(int i=0;i<reparations.length;i++) {
                                %>
                                    <tr>
                                        <td><%=reparations[i].getClient().getId()%></td>
                                        <td><%=reparations[i].getId()%></td>
                                        <td><%=reparations[i].getClient().getNom()%> <%=reparations[i].getClient().getPrenom()%></td>
                                        <td><%=reparations[i].getOrdinateur().getMarque().getLibelle()%> <%=reparations[i].getOrdinateur().getModel()%></td>
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
