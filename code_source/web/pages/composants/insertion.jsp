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
        
                      <form class="row g-3" action="" method="post">

                        <div class="col-md-12">
                          <div class="form-floating">
                            <select name="" class="form-select" id="floatingSelect" aria-label="Types de composants">
                                <option value="">Choisissez un type de composant</option>
                                <option value="">Processeur</option>
                                <option value="">Processeur</option>
                                <option value="">Processeur</option>
                            </select>
                            <label for="floatingSelect">Composant</label>
                          </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating">
                              <select name="" class="form-select" id="floatingSelect" aria-label="Marques de composants">
                                  <option value="">Choisissez une marque de composant</option>
                                  <option value="">Dell</option>
                                  <option value="">Dell</option>
                                  <option value="">Dell</option>
                              </select>
                              <label for="floatingSelect">Marque</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating">
                              <input name="" type="text" class="form-control" id="" placeholder="Modèle">
                              <label for="floatingName">Nom du modèle</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="form-floating mb-3">
                                <textarea class="form-control" placeholder="Description" id="floatingTextarea" style="height: 100px;"></textarea>
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
