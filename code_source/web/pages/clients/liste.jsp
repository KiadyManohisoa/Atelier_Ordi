<%@page import="src.models.clients.Client"%>

<%
    Client[] clts = null;
    if(request.getAttribute("clients")!=null) {
        clts = (Client[]) request.getAttribute("clients");
    }
%>

<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section client</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="accueil.html">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="insertionClient.html">Client</a></li>
            <li class="breadcrumb-item">Liste</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Liste de nos clients</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Nom</th>
                            <th scope="col">Prénom</th>
                            <th scope="col">Contact</th>
                            <th scope="col">Mail</th>
                          </tr>
                        </thead>
                        <tbody>
                        <% if(clts!=null) { 
                            for(int i=0;i<clts.length;i++) { %>
                            <tr>
                                <td><%=clts[i].getId()%></td>
                                <td><%=clts[i].getNom()%></td>
                                <td><%=clts[i].getPrenom()%></td>
                                <td><%=clts[i].getContact()%></td>
                                <td><%=clts[i].getAdresseMail()%></td>
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
