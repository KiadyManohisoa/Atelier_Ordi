<%@ include file="../templates/header.html"%>

<!--main-->
  <main id="main" class="main">

    <div class="pagetitle">
        <h1>Section composants</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Accueil</a></li>
            <li class="breadcrumb-item"> <a href="${pageContext.request.contextPath}/web/pages/clients/insertion.jsp">Composants</a></li>
            <li class="breadcrumb-item">Etat stock</li>
          </ol>
        </nav>
      </div>

      <section class="section">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                      <h5 class="card-title">Filtrage des composants</h5>
        
                      <form class="row g-3" action="${pageContext.request.contextPath}/composants/recommandes/recherche" method="post">
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

                        <div class="text-center">
                          <button type="submit" class="btn btn-primary">Filtrer</button>
                        </div>
                      </form><!-- End floating Labels Form -->
        
                    <h5 class="card-title">Résultats</h5>
        
                      <!-- Table with stripped rows -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Identifiant</th>
                            <th scope="col">Type</th>
                            <th scope="col">Marque</th>
                            <th scope="col">Modèle</th>
                            <th scope="col">Etat stock</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>CMM00001</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                            
                            <tr>
                                <td>CMM00002</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                            
                            <tr>
                                <td>CMM00003</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                            
                            <tr>
                                <td>CMM00004</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                            
                            <tr>
                                <td>CMM00005</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                            
                            <tr>
                                <td>CMM00006</td>
                                <td>Processeur</td>
                                <td>Intel</td>
                                <td>Core i9 10th Gen</td>
                                <td>25</td>
                                <td><a href="#" class="stock-link" data-bs-toggle="modal" data-bs-target="#formStock">Approvisionner ?</a></td>
                            </tr>

                        </tbody>
                        
                      </table>

                    </div>
                  </div>
            </div>

            <div class="modal fade" id="formStock" tabindex="-1" aria-labelledby="StockModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="sellModalLabel">Approvisionnement en stock</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <form class="row g-3">
                        <input type="hidden" id="selectedId" name="idComposant" value="">
                        <div class="col-md-12">
                            <div class="form-floating">
                                <input name="" type="date" class="form-control" id="" placeholder="Date">
                                <label for="floatingName">Date d'approvisionnement</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                                <input name="" type="number" class="form-control" id="" placeholder="Quantité">
                                <label for="floatingName">Quantité</label>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-floating">
                                <input name="" type="text" class="form-control" id="" placeholder="Prix unitaire">
                                <label for="floatingName">Prix unitaire</label>
                            </div>
                        </div>
                      </form>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                      <button type="submit" form="sellForm" class="btn btn-primary">Approvisionner</button>
                    </div>
                  </div>
                </div>
            </div>

        </div>
      </section>

      <script>
        document.addEventListener("DOMContentLoaded", function () {
            const stockLinks = document.querySelectorAll(".stock-link"); 
            const hiddenInput = document.getElementById("selectedId"); 
    
            stockLinks.forEach(link => {
                link.addEventListener("click", function (event) {
                    event.preventDefault(); 
                    const row = this.closest("tr"); 
                    const id = row.querySelector("td:first-child").textContent.trim(); 
                    hiddenInput.value = id; 
                });
            });
        });
    </script>

  </main>
<!--main-->

<%@ include file="../templates/footer.html"%>
