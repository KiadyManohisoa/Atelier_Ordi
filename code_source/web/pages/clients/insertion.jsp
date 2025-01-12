<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section client</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Client</a></li>
            <li class="breadcrumb-item">Insertion</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Nouveau client</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/clients" method="post">
                        <div class="col-md-12">
                          <div class="form-floating">
                            <input name="nomClt" type="text" class="form-control" id="nom" placeholder="Nom">
                            <label for="floatingName">Nom</label>
                          </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="prenomClt" type="text" class="form-control" id="prenom" placeholder="Prenom">
                              <label for="floatingName">Prenom</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="dtnClt" type="date" class="form-control" id="dtn" placeholder="Date de naissance">
                              <label for="floatingName">Date de naissance</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="mailClt" type="mail" class="form-control" id="mail" placeholder="Adresse mail">
                              <label for="floatingName">Adresse mail</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="contactClt" type="text" class="form-control" id="contact" placeholder="Contact">
                              <label for="floatingName">Contact</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="adrClt" type="text" class="form-control" id="adressePostale" placeholder="Adresse postale">
                              <label for="floatingName">Adresse postale</label>
                            </div>
                        </div>

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
