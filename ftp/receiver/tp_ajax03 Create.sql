CREATE TABLE Commune (
  id         SERIAL NOT NULL, 
  nom        int4, 
  Districtid int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE District (
  id       SERIAL NOT NULL, 
  nom      int4, 
  Regionid int4 NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE Province (
  id  SERIAL NOT NULL, 
  nom int4, 
  PRIMARY KEY (id));
CREATE TABLE Region (
  id         SERIAL NOT NULL, 
  nom        int4, 
  Provinceid int4 NOT NULL, 
  chefLieu   varchar(50), 
  PRIMARY KEY (id));
ALTER TABLE Region ADD CONSTRAINT FKRegion987915 FOREIGN KEY (Provinceid) REFERENCES Province (id);
ALTER TABLE Commune ADD CONSTRAINT FKCommune771483 FOREIGN KEY (Districtid) REFERENCES District (id);
ALTER TABLE District ADD CONSTRAINT FKDistrict15487 FOREIGN KEY (Regionid) REFERENCES Region (id);

