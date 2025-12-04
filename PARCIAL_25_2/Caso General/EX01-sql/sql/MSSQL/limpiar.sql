USE transitsoft;
GO

DELETE FROM infraccion;
DELETE FROM captura;
DELETE FROM vehiculo_propietario;
DELETE FROM vehiculo;
DELETE FROM propietario;
DELETE FROM camara;
GO

DBCC CHECKIDENT ('infraccion', RESEED, 0);
DBCC CHECKIDENT ('captura', RESEED, 0);
DBCC CHECKIDENT ('vehiculo_propietario', RESEED, 0);
DBCC CHECKIDENT ('vehiculo', RESEED, 0);
DBCC CHECKIDENT ('propietario', RESEED, 0);
DBCC CHECKIDENT ('camara', RESEED, 0);
GO
