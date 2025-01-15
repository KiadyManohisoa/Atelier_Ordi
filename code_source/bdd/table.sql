CREATE TABLE Client(
   id VARCHAR(15)  DEFAULT ('CLT') || LPAD(nextval('s_Client')::TEXT, 6, '0'),
   nom VARCHAR(100)  NOT NULL,
   prenom VARCHAR(100) ,
   dateNaissance DATE NOT NULL,
   adresseMail VARCHAR(75) ,
   contact CHAR(10)  NOT NULL,
   adressePostale VARCHAR(75) ,
   PRIMARY KEY(id),
   UNIQUE(contact)
);

CREATE TABLE MarqueOrdi(
   id VARCHAR(15)  DEFAULT ('MRK') || LPAD(nextval('s_MarqueOrdi')::TEXT, 6, '0'),
   libelle VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(libelle)
);

CREATE TABLE TypeComposant(
   id VARCHAR(15)  DEFAULT ('TCMP') || LPAD(nextval('s_TypeComposant')::TEXT, 6, '0'),
   libelle VARCHAR(30)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(libelle)
);

CREATE TABLE Categorie(
   id VARCHAR(15)  DEFAULT ('CAT') || LPAD(nextval('s_Categorie')::TEXT, 6, '0'),
   libelle VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(libelle)
);

CREATE TABLE MarqueComposant(
   id VARCHAR(16)  DEFAULT ('MCMP') || LPAD(nextval('s_MarqueComposant')::TEXT, 6, '0'),
   libelle VARCHAR(40)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(libelle)
);

CREATE TABLE Composant(
   id VARCHAR(15)  DEFAULT ('CMP') || LPAD(nextval('s_Composant')::TEXT, 6, '0'),
   nomModele VARCHAR(50)  NOT NULL,
   description TEXT,
   idMarqueComposant VARCHAR(16)  NOT NULL,
   idTypeComposant VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nomModele),
   FOREIGN KEY(idMarqueComposant) REFERENCES MarqueComposant(id),
   FOREIGN KEY(idTypeComposant) REFERENCES TypeComposant(id)
);

CREATE TABLE SortieComposant(
   id VARCHAR(15)  DEFAULT ('SCMP') || LPAD(nextval('s_SortieComposant')::TEXT, 6, '0'),
   dateSortie DATE DEFAULT CURRENT_DATE,
   quantite INTEGER NOT NULL CHECK (quantite > 0),
   prixUnitaire NUMERIC(14,2)   NOT NULL CHECK (prixUnitaire > 0),
   idComposant VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idComposant) REFERENCES Composant(id)
);

CREATE TABLE ComposantsDuMois(
   id VARCHAR(16)  DEFAULT ('CMPM') || LPAD(nextval('s_ComposantsDuMois')::TEXT, 6, '0'),
   periode CHAR(7) NOT NULL,
   idComposant VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idComposant) REFERENCES Composant(id)
);

CREATE TABLE ReparationOrdi(
   id VARCHAR(15)  DEFAULT ('REP') || LPAD(nextval('s_ReparationOrdi')::TEXT, 6, '0'),
   nomModele VARCHAR(100)  NOT NULL,
   numeroSerie VARCHAR(20)  NOT NULL,
   anneeSortie INTEGER CHECK (anneeSortie >0),
   dateReception DATE NOT NULL DEFAULT CURRENT_DATE,
   avancement SMALLINT DEFAULT 0,
   idCategorie VARCHAR(15)  NOT NULL,
   idClient VARCHAR(15)  NOT NULL,
   idMarqueOrdinateur VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(id),
   FOREIGN KEY(idClient) REFERENCES Client(id),
   FOREIGN KEY(idMarqueOrdinateur) REFERENCES MarqueOrdi(id)
);

CREATE TABLE Diagnostic(
   id VARCHAR(15)  DEFAULT ('DGSTC') || LPAD(nextval('s_Diagnostic')::TEXT, 6, '0'),
   dateDiagnostic DATE NOT NULL DEFAULT CURRENT_DATE,
   id_reparationOrdi VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_reparationOrdi),
   FOREIGN KEY(id_reparationOrdi) REFERENCES ReparationOrdi(id)
);

CREATE TABLE DetailDiagnostic(
   id VARCHAR(15)  DEFAULT ('DTLD') || LPAD(nextval('s_DetailDiagnostic')::TEXT, 6, '0'),
   description VARCHAR(100)  NOT NULL,
   type CHAR(1)  NOT NULL,
   idDiagnostic VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idDiagnostic) REFERENCES Diagnostic(id)
);

CREATE TABLE Devis(
   id VARCHAR(15)  DEFAULT ('DVS') || LPAD(nextval('s_Devis')::TEXT, 6, '0'),
   dateDevis DATE NOT NULL DEFAULT CURRENT_DATE,
   fraisMateriel NUMERIC(14,2)   NOT NULL CHECK (fraisMateriel >=0),
   fraisLogiciel NUMERIC(14,2)   NOT NULL CHECK (fraisLogiciel >=0),
   fraisDiagnostic NUMERIC(14,2)   NOT NULL CHECK (fraisDiagnostic >=0),
   delaiEstime SMALLINT NOT NULL,
   dateRecuperation DATE,
   id_reparationOrdi VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_reparationOrdi),
   FOREIGN KEY(id_reparationOrdi) REFERENCES ReparationOrdi(id)
);

CREATE TABLE Facture(
   id VARCHAR(15)  DEFAULT ('FCT') || LPAD(nextval('s_Facture')::TEXT, 6, '0'),
   dateFacturation DATE NOT NULL DEFAULT CURRENT_DATE,
   d_coutTotal NUMERIC(14,2)   CHECK (d_coutTotal>0),
   id_reparationOrdi VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(id_reparationOrdi),
   FOREIGN KEY(id_reparationOrdi) REFERENCES ReparationOrdi(id)
);

CREATE TABLE EntreeComposant(
   id VARCHAR(15)  DEFAULT ('ECMP') || LPAD(nextval('s_EntreeComposant')::TEXT, 6, '0'),
   dateEntree DATE DEFAULT CURRENT_DATE,
   quantite INTEGER NOT NULL CHECK (quantite > 0),
   prixUnitaire NUMERIC(14,2)   NOT NULL CHECK (prixUnitaire > 0),
   d_reste SMALLINT NOT NULL CHECK (d_reste >=0),
   idComposant VARCHAR(15)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idComposant) REFERENCES Composant(id)
);

CREATE TABLE PanneOrdi(
   idReparationOrdi VARCHAR(15) ,
   idTypeComposant VARCHAR(15) ,
   description VARCHAR(50) ,
   PRIMARY KEY(idReparationOrdi, idTypeComposant),
   FOREIGN KEY(idReparationOrdi) REFERENCES ReparationOrdi(id),
   FOREIGN KEY(idTypeComposant) REFERENCES TypeComposant(id)
);

CREATE TABLE ActionComposant(
   idReparationOrdi VARCHAR(15) ,
   idTypeComposant VARCHAR(15) ,
   PRIMARY KEY(idReparationOrdi, idTypeComposant),
   FOREIGN KEY(idReparationOrdi) REFERENCES ReparationOrdi(id),
   FOREIGN KEY(idTypeComposant) REFERENCES TypeComposant(id)
);
