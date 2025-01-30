function generateOptions(selectElement) {
    composants.forEach(composant => {
      const option = document.createElement('option');
      option.value = composant.id;
      option.textContent = composant.libelle;
      selectElement.appendChild(option);
    });
  }
  
  document.addEventListener('DOMContentLoaded', function () {
    //gestion des pannes
    const initialSelect = document.querySelector('#dynamicFields select');
    if (initialSelect) generateOptions(initialSelect);
    
    // Gestion des remplacements
    const checkboxes = document.querySelectorAll('.composant-checkbox');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            const typeId = this.getAttribute('data-type-id');
            const selectContainer = document.getElementById(`select-container-${typeId}`);
            const select = selectContainer.querySelector('.composant-select');

            if (this.checked) {
                selectContainer.style.display = 'block';
            } else {
                selectContainer.style.display = 'none';
                select.selectedIndex = 0;
                this.value = typeId; // Réinitialise la valeur du checkbox
            }
        });

        // Ajoute un écouteur pour mettre à jour la valeur du checkbox quand un composant est sélectionné
        const typeId = checkbox.getAttribute('data-type-id');
        const select = document.getElementById(`select-${typeId}`);

        select.addEventListener('change', function () {
            if (checkbox.checked) {
                const composantId = this.value;
                if (composantId) {
                    checkbox.value = `${typeId}-${composantId}`;
                } else {
                    checkbox.value = typeId; // Si aucun composant sélectionné, garde l'ID du type
                }
            }
        });
    });

  });
  
  document.getElementById('addField').addEventListener('click', function () {
    const dynamicFields = document.getElementById('dynamicFields');
    const newField = document.createElement('div');
    newField.className = 'row align-items-center mb-3';
    newField.innerHTML = `
      <div class="col-md-6">
        <select name="typeComposant[]" class="form-select">
          <option value="" selected>Type de composant</option>
        </select>
      </div>
      <div class="col-md-6">
        <input name="descriptionPanne[]" type="text" class="form-control" placeholder="Description de la panne">
      </div>`;
    dynamicFields.appendChild(newField);
  
    const newSelect = newField.querySelector('select');
    generateOptions(newSelect);
  });
  