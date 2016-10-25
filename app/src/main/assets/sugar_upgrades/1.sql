-- Criação da tabela horaComplementar
CREATE TABLE 'HORA_COMPLEMENTAR' (
    'id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    'nome' TEXT,
    'descricao' TEXT,
    'data_evento' TEXT,
    'quantidade_horas' INTEGER,
    'comprovante' BLOB
);