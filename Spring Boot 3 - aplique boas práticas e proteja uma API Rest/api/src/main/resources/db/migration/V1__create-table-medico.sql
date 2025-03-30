CREATE TABLE medico(

    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome          VARCHAR(45)  NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    crm           VARCHAR(6)   NOT NULL UNIQUE,
    especialidade VARCHAR(45)  NOT NULL

);


