<%@page import="src.models.processus.CommissionTechnicien"%>
<%@page import="src.models.processus.Technicien"%>
<%@page import="src.models.processus.Genre"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
  String periode = new String();
  if(request.getAttribute("periode")!=null) {
    periode = (String) request.getAttribute("periode");
  }

  List<CommissionTechnicien> ct = null;
  if(request.getAttribute("ct")!=null) {
    ct = (List<CommissionTechnicien>)request.getAttribute("ct");
  }

  double [] coms = new double [2];
  coms[0] = 0;
  coms[1] = 0;
  int isReady = 0;

  if(request.getAttribute("coms")!=null) {
    coms = (double[]) request.getAttribute("coms");
    isReady=1;
  }
%>

<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section technicien</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Technicien</a></li>
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Commission</a></li>
            <li class="breadcrumb-item">Periode</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Recheche des commissions/technicien sur une periode</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/technicien/commissions" method="post">
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
        
                    <h5 class="card-title">Résultats du <%=periode%></h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Id technicien</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Prénom</th>
                            <th scope="col">Valeur commmision</th>
                            <th scope="col">Genre</th>
                          </tr>
                        </thead>
                        <tbody>

                        <%if(ct!=null) { 
                            for(int i=0;i<ct.size();i++) { %>
                            <tr>
                                <td><%=ct.get(i).getTechnicien().getId()%></td>
                                <td><%=ct.get(i).getTechnicien().getNom()%></td>
                                <td><%=ct.get(i).getTechnicien().getPrenom()%></td>
                                <td><%=ct.get(i).getValeurCommission()%></td>
                                <td><%=ct.get(i).getTechnicien().getGenre().getLibelle()%></td>
                            </tr>
                        <% } } %>

                        </tbody>
                        
                      </table>

                      <%
                        if(isReady==1) {
                          %>
                      <h5 class="card-title">Commissions par genre</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Homme</th>
                            <th scope="col">Femme</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td> <%=coms[0]%></td>
                            <td> <%=coms[1]%> </td>
                          </tr>

                        </tbody>
                        
                      </table>
                        <% }                      
                      %>

                    </div>
                  </div>
            </div>
        </div>
      </section>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
