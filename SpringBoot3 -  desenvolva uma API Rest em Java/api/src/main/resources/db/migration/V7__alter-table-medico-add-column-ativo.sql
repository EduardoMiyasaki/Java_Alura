ALTER TABLE medico ADD COLUMN ativo BOOLEAN;

ALTER TABLE medico ALTER COLUMN ativo SET DEFAULT TRUE;

UPDATE medico SET ativo = true;