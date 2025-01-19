CREATE OR REPLACE VIEW v_Ordi 
    AS SELECT r.id as idReparation, m.libelle as marque, r.nomModele, r.numeroSerie, r.dateReception, r.avancement, r.idCategorie, c.nom, c.prenom, c.id as idClient 
FROM ReparationOrdi r 
    INNER JOIN MarqueOrdi m 
ON r.idMarqueOrdinateur=m.id
    INNER JOIN Client c 
ON r.idClient=c.id;

CREATE OR REPLACE VIEW v_panneOrdi 
    AS SELECT o.*, t.id as idTypeComposant, t.libelle, p.description
FROM PanneOrdi p 
    INNER JOIN v_Ordi o 
ON p.idReparationOrdi=o.idReparation 
    INNER JOIN TypeComposant t 
ON p.idTypeComposant=t.id;

CREATE OR REPLACE VIEW v_actionOrdi 
    AS SELECT o.*, t.id as idTypeComposant, t.libelle FROM ActionComposant a 
INNER JOIN v_Ordi o 
    ON a.idReparationOrdi = o.idReparation 
INNER JOIN TypeComposant t 
    ON a.idTypeComposant=t.id;

CREATE OR REPLACE VIEW v_stockComposant 
    AS SELECT idComposant, sum(d_reste) as stockComposant 
FROM entreeComposant 
    GROUP BY idComposant;

CREATE OR REPLACE VIEW v_Composant 
    AS SELECT c.*, m.libelle as marqueComposant, t.libelle as typeComposant, COALESCE(s.stockComposant,0) as stockComposant FROM Composant c 
INNER JOIN MarqueComposant m 
    ON c.idMarqueComposant=m.id 
INNER JOIN TypeComposant t 
    ON c.idTypeComposant=t.id
LEFT JOIN v_stockComposant s 
    ON c.id=s.idComposant;

CREATE OR REPLACE VIEW v_ComposantsDuMois 
    AS SELECT c.*, cdm.id idCdm, cdm.periode FROM ComposantsDuMois cdm 
INNER JOIN v_Composant c 
    ON cdm.idComposant=c.id;