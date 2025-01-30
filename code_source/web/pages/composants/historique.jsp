<%@page import="src.models.materiel.Composant"%>
<%@page import="src.models.materiel.TypeComposant"%>
<%@page import="src.models.materiel.MarqueComposant"%>
<%@page import="src.models.composants.HistoriqueComposant"%>

<%
    Composant[] cps = null;
    if(request.getAttribute("cps")!=null) {
        cps = (Composant[]) request.getAttribute("cps");
    }

    HistoriqueComposant [] histos = null;
    if(request.getAttribute("histos")!=null) {
        histos = (HistoriqueComposant[]) request.getAttribute("histos");
    }

    Composant thecmp = null;
    if(request.getAttribute("thecmp")!=null) {
      thecmp = (Composant) request.getAttribute("thecmp");
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
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Historique</a></li>
            <li class="breadcrumb-item">Prix de vente</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Historique des prix de ventes pour un composant</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/historique/vente" method="post">
                        <div class="col-md-12">
                          <div class="form-floating">
                                <select name="idComposant" class="form-select" id="floatingSelect" aria-label="Types de composants">
                                    <option value="">Choisissez un composant</option>
                                    <%
                                        if(cps!=null) {
                                            for(int i=0;i<cps.length;i++) { %>
                                                <option value="<%=cps[i].getId()%>"> <%=cps[i].getTypeComposant().getLibelle()%> - <%=cps[i].getMarqueComposant().getLibelle()%> (<%=cps[i].getNomModele()%>) </option>
                                            <% }
                                        }
                                    %>
                                </select>
                                <label for="floatingSelect">Composant</label>
                          </div>
                        </div>

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Valider</button>
                        </div>
                      </form><!-- End floating Labels Form -->
        
                    <h5 class="card-title">Résultats <% if(thecmp!=null) { %> pour <%=thecmp.getTypeComposant().getLibelle()%> - <%=thecmp.getMarqueComposant().getLibelle()%> (<%=thecmp.getNomModele()%>) <% } %> </h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Date mouvement</th>
                            <th scope="col">Prix vente</th>
                          </tr>
                        </thead>
                        <tbody>

                        <%if(histos!=null) { 
                            for(int i=0;i<histos.length;i++) { %>
                            <tr>
                                <td><%=histos[i].getId()%></td>
                                <td><%=histos[i].getDateMvt()%></td>
                                <td><%=histos[i].getPrixVente()%></td>
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
