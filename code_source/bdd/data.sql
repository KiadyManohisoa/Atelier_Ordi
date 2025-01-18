INSERT INTO client (nom, prenom, datenaissance, adressemail, contact, adressepostale) VALUES
('Dupont', 'Jean', '1985-04-15', 'jean.dupont@example.com', '0612345678', '12 Rue des Lilas Paris'),
('Durand', 'Claire', '1992-09-22', 'claire.durand@example.com', '0623456789', '45 Boulevard Haussmann'),
('Leroy', 'Paul', '1978-01-30', 'paul.leroy@example.org', '0634567890', '88 Avenue de la Republique'),
('Morel', 'Sophie', '1980-07-12', 'sophie.morel@example.net', '0645678901', '5 Place de la Bastille'),
('Bernard', 'Alice', '1995-05-25', 'alice.bernard@example.fr', '0656789012', '23 Chemin des Ecoliers');

INSERT INTO MarqueOrdi (libelle) VALUES
('HP'),
('Dell'),
('Lenovo'),
('Apple'),
('Asus');

INSERT INTO TypeComposant (libelle) VALUES
('Processeur'),
('Carte mere'),
('Mémoire RAM'),
('Disque dur'),
('Ecran');

INSERT INTO Categorie (libelle) VALUES 
('Portable'),
('Gamers');

INSERT INTO ReparationOrdi (nomModele, numeroSerie, anneeSortie, dateReception, idClient, idMarqueOrdinateur, idCategorie) VALUES
('EliteBook 840', 'SN123456', 2019, '2025-01-01', 'CLT000001', 'MRK000001', 'CAT000001'),
('Inspiron 15', 'SN234567', 2021, '2025-01-02', 'CLT000002', 'MRK000002', 'CAT000002'),
('ThinkPad X1', 'SN345678', 2020, '2025-01-03', 'CLT000003', 'MRK000003', 'CAT000002'),
('MacBook Pro', 'SN456789', 2018, '2025-01-04', 'CLT000004', 'MRK000004', 'CAT000001'),
('VivoBook S15', 'SN567890', 2022, '2025-01-05', 'CLT000005', 'MRK000005', 'CAT000002');

INSERT INTO ReparationOrdi (nommodele, numeroserie, anneesortie, datereception, avancement, idcategorie, idclient, idmarqueordinateur)
VALUES 
('Pavilion 15', 'SN12345HP001', 2022, '2025-01-15', 0, 'CAT000001', 'CLT000001', 'MRK000001'),
('XPS 13', 'SN67890DL001', 2021, '2025-01-15', 1, 'CAT000002', 'CLT000002', 'MRK000002'),
('ThinkPad X2', 'SN11223LN001', 2020, '2025-01-15', 2, 'CAT000001', 'CLT000003', 'MRK000003');

INSERT INTO ReparationOrdi (nommodele, numeroserie, anneesortie, datereception, avancement, idcategorie, idclient, idmarqueordinateur)
VALUES 
('MacBook Air', 'SN44556AP001', 2023, '2025-01-16', 0, 'CAT000001', 'CLT000004', 'MRK000004'),
('ROG Zephyrus', 'SN77889AS001', 2022, '2025-01-16', 1, 'CAT000002', 'CLT000005', 'MRK000005'),
('Envy x360', 'SN99001HP002', 2021, '2025-01-16', 2, 'CAT000001', 'CLT000001', 'MRK000001');


INSERT INTO PanneOrdi (idReparationOrdi, idTypeComposant, description) VALUES
('REP000001', 'TCMP000001', 'Processeur surchauffe'),
('REP000001', 'TCMP000004', 'Disque dur defaillant'),
('REP000002', 'TCMP000002', 'Carte mere non fonctionnelle'),
('REP000003', 'TCMP000005', 'Ecran fissure'),
('REP000003', 'TCMP000003', 'RAM defectueuse'),
('REP000004', 'TCMP000001', 'Processeur lent'),
('REP000005', 'TCMP000004', 'Disque dur endommage'),
('REP000005', 'TCMP000002', 'Probleme avec la carte mere');

INSERT INTO ActionComposant (idReparationOrdi, idTypeComposant) VALUES 
('REP000002', 'TCMP000002');

INSERT INTO MarqueComposant ("libelle") VALUES 
('Intel'),
('AMD'),
('NVIDIA'),
('Corsair'),
('Kingston');

INSERT INTO Composant (nommodele, description, idmarquecomposant, idtypecomposant) VALUES
('Core i9-13900K', 'Processeur haut de gamme Intel', 'MCMP000001', 'TCMP000001'),
('Ryzen 7 5800X', 'Processeur performant AMD', 'MCMP000002', 'TCMP000001'),
('GeForce RTX 3080', 'Carte graphique NVIDIA', 'MCMP000003', 'TCMP000002'),
('Vengeance LPX 16GB', 'RAM DDR4 Corsair, 3200MHz', 'MCMP000004', 'TCMP000003'),
('KC3000 1TB', 'SSD rapide Kingston, NVMe', 'MCMP000005', 'TCMP000004');
