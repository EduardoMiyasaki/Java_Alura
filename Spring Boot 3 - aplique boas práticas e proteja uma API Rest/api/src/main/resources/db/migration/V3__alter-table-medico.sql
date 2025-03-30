ALTER TABLE medico ADD COLUMN endereco_id BIGINT;

ALTER TABLE medico
ADD CONSTRAINT fk_endereco_id
FOREIGN KEY (endereco_id)
REFERENCES endereco(id)
ON DELETE CASCADE;