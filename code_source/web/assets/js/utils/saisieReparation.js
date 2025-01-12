function generateOptions(selectElement) {
    composants.forEach(composant => {
      const option = document.createElement('option');
      option.value = composant.id;
      option.textContent = composant.libelle;
      selectElement.appendChild(option);
    });
  }
  
  document.addEventListener('DOMContentLoaded', function () {
    const initialSelect = document.querySelector('#dynamicFields select');
    if (initialSelect) generateOptions(initialSelect);
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
  