ALTER TABLE paciente ADD COLUMN ativo tinyint;

UPDATE paciente SET ativo = 1;