ALTER TABLE paciente ADD COLUMN endereco_id BIGINT;

ALTER TABLE paciente
ADD CONSTRAINT fk_paciente_endereco
FOREIGN KEY (endereco_id)
REFERENCES endereco(id)
ON DELETE CASCADE;