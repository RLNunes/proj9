-- *******************
-- Criação do esquema
-- *******************
CREATE SCHEMA IF NOT EXISTS app_hist;
-- ************************
-- Sequências
-- ************************

-- app_hist.capitania_pk_capitania_seq definition

-- DROP SEQUENCE app_hist.capitania_pk_capitania_seq;

CREATE SEQUENCE app_hist.capitania_pk_capitania_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.capitania_pk_capitania_seq1 definition

-- DROP SEQUENCE app_hist.capitania_pk_capitania_seq1;

CREATE SEQUENCE app_hist.capitania_pk_capitania_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.cargo_titulo_pk_cargo_titulo_seq definition

-- DROP SEQUENCE app_hist.cargo_titulo_pk_cargo_titulo_seq;

CREATE SEQUENCE app_hist.cargo_titulo_pk_cargo_titulo_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.comarca_pk_comarca_seq definition

-- DROP SEQUENCE app_hist.comarca_pk_comarca_seq;

CREATE SEQUENCE app_hist.comarca_pk_comarca_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.comarca_pk_seq definition

-- DROP SEQUENCE app_hist.comarca_pk_seq;

CREATE SEQUENCE app_hist.comarca_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.conselheiros_consulta_pk_conselheiro_seq definition

-- DROP SEQUENCE app_hist.conselheiros_consulta_pk_conselheiro_seq;

CREATE SEQUENCE app_hist.conselheiros_consulta_pk_conselheiro_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.conselheiros_consulta_pk_seq definition

-- DROP SEQUENCE app_hist.conselheiros_consulta_pk_seq;

CREATE SEQUENCE app_hist.conselheiros_consulta_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.consulta_pk_seq definition

-- DROP SEQUENCE app_hist.consulta_pk_seq;

CREATE SEQUENCE app_hist.consulta_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.destinatario_resposta_pk_destinatario_seq definition

-- DROP SEQUENCE app_hist.destinatario_resposta_pk_destinatario_seq;

CREATE SEQUENCE app_hist.destinatario_resposta_pk_destinatario_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.freguesia_pk_freguesia_seq definition

-- DROP SEQUENCE app_hist.freguesia_pk_freguesia_seq;

CREATE SEQUENCE app_hist.freguesia_pk_freguesia_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.freguesia_pk_seq definition

-- DROP SEQUENCE app_hist.freguesia_pk_seq;

CREATE SEQUENCE app_hist.freguesia_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.instado_resposta_pk_seq definition

-- DROP SEQUENCE app_hist.instado_resposta_pk_seq;

CREATE SEQUENCE app_hist.instado_resposta_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.mandado_pk_seq definition

-- DROP SEQUENCE app_hist.mandado_pk_seq;

CREATE SEQUENCE app_hist.mandado_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.marcador_economico_ocupacao_pk_economico_ocupacao_seq definition

-- DROP SEQUENCE app_hist.marcador_economico_ocupacao_pk_economico_ocupacao_seq;

CREATE SEQUENCE app_hist.marcador_economico_ocupacao_pk_economico_ocupacao_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.marcador_economico_ocupacao_seq definition

-- DROP SEQUENCE app_hist.marcador_economico_ocupacao_seq;

CREATE SEQUENCE app_hist.marcador_economico_ocupacao_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.marcador_juridicopk_seq definition

-- DROP SEQUENCE app_hist.marcador_juridicopk_seq;

CREATE SEQUENCE app_hist.marcador_juridicopk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.marcador_status_socio_juridico_p_pk_marcador_socio_juridico_seq definition

-- DROP SEQUENCE app_hist.marcador_status_socio_juridico_p_pk_marcador_socio_juridico_seq;

CREATE SEQUENCE app_hist.marcador_status_socio_juridico_p_pk_marcador_socio_juridico_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.oficio_agregador_pk_oficio_agregador_seq definition

-- DROP SEQUENCE app_hist.oficio_agregador_pk_oficio_agregador_seq;

CREATE SEQUENCE app_hist.oficio_agregador_pk_oficio_agregador_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.oficio_agregador_pk_seq definition

-- DROP SEQUENCE app_hist.oficio_agregador_pk_seq;

CREATE SEQUENCE app_hist.oficio_agregador_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.oficio_titulo_pk_oficio_titulo_seq definition

-- DROP SEQUENCE app_hist.oficio_titulo_pk_oficio_titulo_seq;

CREATE SEQUENCE app_hist.oficio_titulo_pk_oficio_titulo_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.palavra_chave_pk_palavra_chave_seq definition

-- DROP SEQUENCE app_hist.palavra_chave_pk_palavra_chave_seq;

CREATE SEQUENCE app_hist.palavra_chave_pk_palavra_chave_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.palavra_chave_pk_seq definition

-- DROP SEQUENCE app_hist.palavra_chave_pk_seq;

CREATE SEQUENCE app_hist.palavra_chave_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.pessoas_citadas_pk_pcitadas_seq definition

-- DROP SEQUENCE app_hist.pessoas_citadas_pk_pcitadas_seq;

CREATE SEQUENCE app_hist.pessoas_citadas_pk_pcitadas_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.provocacao_pk_seq definition

-- DROP SEQUENCE app_hist.provocacao_pk_seq;

CREATE SEQUENCE app_hist.provocacao_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.referencia_documental_pk_ref_documento_seq definition

-- DROP SEQUENCE app_hist.referencia_documental_pk_ref_documento_seq;

CREATE SEQUENCE app_hist.referencia_documental_pk_ref_documento_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.referencia_documental_pk_ref_documento_seq1 definition

-- DROP SEQUENCE app_hist.referencia_documental_pk_ref_documento_seq1;

CREATE SEQUENCE app_hist.referencia_documental_pk_ref_documento_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.registro_pk_registro_seq definition

-- DROP SEQUENCE app_hist.registro_pk_registro_seq;

CREATE SEQUENCE app_hist.registro_pk_registro_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.registro_pk_registro_seq1 definition

-- DROP SEQUENCE app_hist.registro_pk_registro_seq1;

CREATE SEQUENCE app_hist.registro_pk_registro_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.remetente_pk_seq definition

-- DROP SEQUENCE app_hist.remetente_pk_seq;

CREATE SEQUENCE app_hist.remetente_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.requerente_pk_requerente_seq definition

-- DROP SEQUENCE app_hist.requerente_pk_requerente_seq;

CREATE SEQUENCE app_hist.requerente_pk_requerente_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.resposta_pk_resposta_seq definition

-- DROP SEQUENCE app_hist.resposta_pk_resposta_seq;

CREATE SEQUENCE app_hist.resposta_pk_resposta_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.secretario_agregador_pk_seq definition

-- DROP SEQUENCE app_hist.secretario_agregador_pk_seq;

CREATE SEQUENCE app_hist.secretario_agregador_pk_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START 0
	CACHE 1
	NO CYCLE;


-- app_hist.secretario_concelheiro_pk_seq definition

-- DROP SEQUENCE app_hist.secretario_concelheiro_pk_seq;

CREATE SEQUENCE app_hist.secretario_concelheiro_pk_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 9223372036854775807
	START 0
	CACHE 1
	NO CYCLE;


-- app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq definition

-- DROP SEQUENCE app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq;

CREATE SEQUENCE app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq1 definition

-- DROP SEQUENCE app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq1;

CREATE SEQUENCE app_hist.secretario_conselho_resposta_pk_secretario_conselho_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.sutema_pk_subtema_seq definition

-- DROP SEQUENCE app_hist.sutema_pk_subtema_seq;

CREATE SEQUENCE app_hist.sutema_pk_subtema_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tema_agregador_pk_seq definition

-- DROP SEQUENCE app_hist.tema_agregador_pk_seq;

CREATE SEQUENCE app_hist.tema_agregador_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tema_pk_tema_seq definition

-- DROP SEQUENCE app_hist.tema_pk_tema_seq;

CREATE SEQUENCE app_hist.tema_pk_tema_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tema_pk_tema_seq1 definition

-- DROP SEQUENCE app_hist.tema_pk_tema_seq1;

CREATE SEQUENCE app_hist.tema_pk_tema_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.termo_pk_termo_seq definition

-- DROP SEQUENCE app_hist.termo_pk_termo_seq;

CREATE SEQUENCE app_hist.termo_pk_termo_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.termo_pk_termo_seq1 definition

-- DROP SEQUENCE app_hist.termo_pk_termo_seq1;

CREATE SEQUENCE app_hist.termo_pk_termo_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tipo_referencia_documental_pk_tipo_referencia_seq definition

-- DROP SEQUENCE app_hist.tipo_referencia_documental_pk_tipo_referencia_seq;

CREATE SEQUENCE app_hist.tipo_referencia_documental_pk_tipo_referencia_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tipo_referencia_pk_seq definition

-- DROP SEQUENCE app_hist.tipo_referencia_pk_seq;

CREATE SEQUENCE app_hist.tipo_referencia_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tipologia_diplomatica_pk_tipologia_seq definition

-- DROP SEQUENCE app_hist.tipologia_diplomatica_pk_tipologia_seq;

CREATE SEQUENCE app_hist.tipologia_diplomatica_pk_tipologia_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.tipologia_diplomatica_pk_tipologia_seq1 definition

-- DROP SEQUENCE app_hist.tipologia_diplomatica_pk_tipologia_seq1;

CREATE SEQUENCE app_hist.tipologia_diplomatica_pk_tipologia_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.ultramar_pk_seq definition

-- DROP SEQUENCE app_hist.ultramar_pk_seq;

CREATE SEQUENCE app_hist.ultramar_pk_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.utilizadores_user_id_seq definition

-- DROP SEQUENCE app_hist.utilizadores_user_id_seq;

CREATE SEQUENCE app_hist.utilizadores_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- app_hist.utilizadores_user_id_seq1 definition

-- DROP SEQUENCE app_hist.utilizadores_user_id_seq1;

CREATE SEQUENCE app_hist.utilizadores_user_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 32767
	START 1
	CACHE 1
	NO CYCLE;

-- ************************
-- Funções
-- ************************

-- DROP FUNCTION app_hist.resposta_ano();

CREATE OR REPLACE FUNCTION app_hist.resposta_ano()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
    BEGIN
        IF NEW.data_resposta IS NOT NULL THEN
           NEW.ano_resposta := extract(year from new.data_resposta);
         end if;
        RETURN NEW;
    END;
$function$
;

-- app_hist.capitania definition

-- Drop table

-- DROP TABLE app_hist.capitania;

CREATE TABLE app_hist.capitania (
	pk_capitania smallserial NOT NULL, -- Nº sequencial, sem significado
	nome_capitania varchar(40) NULL, -- Nome da capitania onde o documento foi emitido
	CONSTRAINT capitania_pkey PRIMARY KEY (pk_capitania)
);
COMMENT ON TABLE app_hist.capitania IS 'guarda os nomes das capitanias onde os documentos foram emitidos';

-- Column comments

COMMENT ON COLUMN app_hist.capitania.pk_capitania IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.capitania.nome_capitania IS 'Nome da capitania onde o documento foi emitido';


-- app_hist.conselheiros_consulta definition

-- Drop table

-- DROP TABLE app_hist.conselheiros_consulta;

CREATE TABLE app_hist.conselheiros_consulta (
	pk_conselheiro int2 NOT NULL DEFAULT nextval('app_hist.conselheiros_consulta_pk_seq'::regclass), -- Nº sequencial, sem significado
	nome varchar(80) NULL, -- Nome dos conselheiros
	CONSTRAINT pk_conselheiros_consulta PRIMARY KEY (pk_conselheiro)
);
COMMENT ON TABLE app_hist.conselheiros_consulta IS 'guarda o nome dos conselheiros do Conselho Ultramarino que subscreveram uma consulta e/ou resposta';

-- Column comments

COMMENT ON COLUMN app_hist.conselheiros_consulta.pk_conselheiro IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.conselheiros_consulta.nome IS 'Nome dos conselheiros';


-- app_hist.marcador_economico_ocupacao definition

-- Drop table

-- DROP TABLE app_hist.marcador_economico_ocupacao;

CREATE TABLE app_hist.marcador_economico_ocupacao (
	pk_economico_ocupacao int2 NOT NULL DEFAULT nextval('app_hist.marcador_economico_ocupacao_seq'::regclass), -- número sequencial sem significado
	designacao varchar(60) NULL, -- guarda informação sobre ocupação, proprietário, homem de negócios, etc.
	CONSTRAINT pk_marcador_economico_ocupacao PRIMARY KEY (pk_economico_ocupacao)
);
COMMENT ON TABLE app_hist.marcador_economico_ocupacao IS 'guarda informação sobreoOcupação, proprietário, homem de negócios, etc.';

-- Column comments

COMMENT ON COLUMN app_hist.marcador_economico_ocupacao.pk_economico_ocupacao IS 'número sequencial sem significado';
COMMENT ON COLUMN app_hist.marcador_economico_ocupacao.designacao IS 'guarda informação sobre ocupação, proprietário, homem de negócios, etc.';


-- app_hist.marcador_status_socio_juridico_pessoa definition

-- Drop table

-- DROP TABLE app_hist.marcador_status_socio_juridico_pessoa;

CREATE TABLE app_hist.marcador_status_socio_juridico_pessoa (
	pk_marcador_socio_juridico int2 NOT NULL DEFAULT nextval('app_hist.marcador_juridicopk_seq'::regclass), -- número sequencial sem significado
	marcador_status_juridico varchar(20) NULL, -- Status civil (viúva, solteiro, casado, emancipado, etc)\ncondição jurídica (liberto, administrado, escravo, etc) cor (pardo, preto, etc)
	CONSTRAINT pk_marcador_juridico_requerente PRIMARY KEY (pk_marcador_socio_juridico)
);
COMMENT ON TABLE app_hist.marcador_status_socio_juridico_pessoa IS 'guarda a condição sócio-jurídica da pessoa';

-- Column comments

COMMENT ON COLUMN app_hist.marcador_status_socio_juridico_pessoa.pk_marcador_socio_juridico IS 'número sequencial sem significado';
COMMENT ON COLUMN app_hist.marcador_status_socio_juridico_pessoa.marcador_status_juridico IS 'Status civil (viúva, solteiro, casado, emancipado, etc)\ncondição jurídica (liberto, administrado, escravo, etc) cor (pardo, preto, etc)';


-- app_hist.oficio_agregador definition

-- Drop table

-- DROP TABLE app_hist.oficio_agregador;

CREATE TABLE app_hist.oficio_agregador (
	pk_oficio_agregador int2 NOT NULL DEFAULT nextval('app_hist.oficio_agregador_pk_seq'::regclass), -- Código de identificação para o ofício/título, nº sequencial sem significado
	designacao varchar(40) NULL, -- ofício ou título que qualifica a pessoa ou órgão
	CONSTRAINT pk_oficio_agregador PRIMARY KEY (pk_oficio_agregador)
);
COMMENT ON TABLE app_hist.oficio_agregador IS 'guarda a classificação mais geral que agrega os vários ofícios da tabela oficio_titulo';

-- Column comments

COMMENT ON COLUMN app_hist.oficio_agregador.pk_oficio_agregador IS 'Código de identificação para o ofício/título, nº sequencial sem significado';
COMMENT ON COLUMN app_hist.oficio_agregador.designacao IS 'ofício ou título que qualifica a pessoa ou órgão';


-- app_hist.palavra_chave definition

-- Drop table

-- DROP TABLE app_hist.palavra_chave;

CREATE TABLE app_hist.palavra_chave (
	pk_palavra_chave int2 NOT NULL DEFAULT nextval('app_hist.palavra_chave_pk_seq'::regclass), -- Nº sequencial, sem significado
	palavra_chave varchar(40) NULL, -- palavra chave que identifica o documento
	CONSTRAINT pk_palavra_chave PRIMARY KEY (pk_palavra_chave),
	CONSTRAINT unq_palavra_chave_palavra_chave UNIQUE (palavra_chave)
);
COMMENT ON TABLE app_hist.palavra_chave IS 'guarda palavras-chaves associadas ao teor dos documentos';

-- Column comments

COMMENT ON COLUMN app_hist.palavra_chave.pk_palavra_chave IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.palavra_chave.palavra_chave IS 'palavra chave que identifica o documento';


-- app_hist.registro definition

-- Drop table

-- DROP TABLE app_hist.registro;

CREATE TABLE app_hist.registro (
	pk_registro smallserial NOT NULL, -- Nº sequencial, sem significado
	descricao varchar NULL, -- Nomes dos livros, códices ou Pastas: valores possíveis:\nPartes, Capitanias, Avulsos, Provisões
	CONSTRAINT registro_pkey PRIMARY KEY (pk_registro)
);
COMMENT ON TABLE app_hist.registro IS 'guarda informações sobre os registros do documento';

-- Column comments

COMMENT ON COLUMN app_hist.registro.pk_registro IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.registro.descricao IS 'Nomes dos livros, códices ou Pastas: valores possíveis:\nPartes, Capitanias, Avulsos, Provisões';


-- app_hist.secretario_agregador definition

-- Drop table

-- DROP TABLE app_hist.secretario_agregador;

CREATE TABLE app_hist.secretario_agregador (
	pk_secretario_agregador int2 NOT NULL DEFAULT nextval('app_hist.secretario_agregador_pk_seq'::regclass),
	designacao varchar(40) NULL,
	CONSTRAINT secretario_agregador_pk PRIMARY KEY (pk_secretario_agregador)
);


-- app_hist.secretario_conselheiro definition

-- Drop table

-- DROP TABLE app_hist.secretario_conselheiro;

CREATE TABLE app_hist.secretario_conselheiro (
	pk_secretario_conselheiro int2 NOT NULL DEFAULT nextval('app_hist.secretario_concelheiro_pk_seq'::regclass),
	descricao varchar(40) NOT NULL,
	secretario_agregador int2 NULL,
	CONSTRAINT secretario_concelheiro_pk PRIMARY KEY (pk_secretario_conselheiro)
);


-- app_hist.secretario_conselho_resposta definition

-- Drop table

-- DROP TABLE app_hist.secretario_conselho_resposta;

CREATE TABLE app_hist.secretario_conselho_resposta (
	pk_secretario_conselho smallserial NOT NULL, -- Nº sequencial, sem significado
	nome varchar(80) NULL, -- nome do secretário ou do conselho
	quem_responde varchar(10) NULL, -- campo controlado: secretário ou conselho
	CONSTRAINT ch01_secretario_conselho CHECK (((quem_responde)::text = ANY (ARRAY[('secretário'::character varying)::text, ('conselho'::character varying)::text]))),
	CONSTRAINT secretario_conselho_resposta_pkey PRIMARY KEY (pk_secretario_conselho)
);
COMMENT ON TABLE app_hist.secretario_conselho_resposta IS 'guarda as ordens (em suas diferentes tipologias: provisões, decretos, alvarás, leis, etc.) que foram formadas na Secretaria de Estado a partir das petições apresentadas da América';

-- Column comments

COMMENT ON COLUMN app_hist.secretario_conselho_resposta.pk_secretario_conselho IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.secretario_conselho_resposta.nome IS 'nome do secretário ou do conselho';
COMMENT ON COLUMN app_hist.secretario_conselho_resposta.quem_responde IS 'campo controlado: secretário ou conselho';


-- app_hist.tema_agregador definition

-- Drop table

-- DROP TABLE app_hist.tema_agregador;

CREATE TABLE app_hist.tema_agregador (
	pk_tema_agregador int4 NOT NULL DEFAULT nextval('app_hist.tema_agregador_pk_seq'::regclass),
	designacao varchar(40) NOT NULL,
	CONSTRAINT tema_agregador_pk PRIMARY KEY (pk_tema_agregador)
);


-- app_hist.tipo_referencia_documental definition

-- Drop table

-- DROP TABLE app_hist.tipo_referencia_documental;

CREATE TABLE app_hist.tipo_referencia_documental (
	pk_tipo_referencia int2 NOT NULL DEFAULT nextval('app_hist.tipo_referencia_pk_seq'::regclass), -- número sequencial sem significado.
	tipo_referencia varchar(30) NULL, -- tipo de referência, pode ser códice ou avulso*\n* nos registos do filemaker aparece códice ou caixa (cx)
	CONSTRAINT pk_tipo_referencia_documental PRIMARY KEY (pk_tipo_referencia)
);
COMMENT ON TABLE app_hist.tipo_referencia_documental IS 'guarda o tipo da referência do documento original (por exemplo, códice ou caixa)';

-- Column comments

COMMENT ON COLUMN app_hist.tipo_referencia_documental.pk_tipo_referencia IS 'número sequencial sem significado.';
COMMENT ON COLUMN app_hist.tipo_referencia_documental.tipo_referencia IS 'tipo de referência, pode ser códice ou avulso*\n* nos registos do filemaker aparece códice ou caixa (cx)';


-- app_hist.tipologia_diplomatica definition

-- Drop table

-- DROP TABLE app_hist.tipologia_diplomatica;

CREATE TABLE app_hist.tipologia_diplomatica (
	pk_tipologia smallserial NOT NULL, -- Nº sequencial, sem significado
	nome varchar(60) NULL, -- Alvará, decreto, decisão,etc.
	CONSTRAINT tipologia_diplomatica_pkey PRIMARY KEY (pk_tipologia)
);
COMMENT ON TABLE app_hist.tipologia_diplomatica IS 'guarda informações sobre a tipologia diplomática do documento:  álvarás, decretos, etc';

-- Column comments

COMMENT ON COLUMN app_hist.tipologia_diplomatica.pk_tipologia IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.tipologia_diplomatica.nome IS 'Alvará, decreto, decisão,etc.';


-- app_hist.utilizadores definition

-- Drop table

-- DROP TABLE app_hist.utilizadores;

CREATE TABLE app_hist.utilizadores (
	user_id smallserial NOT NULL,
	username varchar NOT NULL,
	email varchar NULL,
	"password" varchar NOT NULL,
	"admin" bpchar(1) NOT NULL,
	nome varchar NULL,
	CONSTRAINT utilizadores_pk PRIMARY KEY (user_id)
);


-- app_hist.comarca definition

-- Drop table

-- DROP TABLE app_hist.comarca;

CREATE TABLE app_hist.comarca (
	pk_comarca int2 NOT NULL DEFAULT nextval('app_hist.comarca_pk_seq'::regclass), -- Nº sequencial, sem significado
	nome_comarca varchar(60) NULL, -- Identificador da região
	comarca_nome2 int2 NULL, -- guarda a relação entre a antiga e nova nomenclatura para a mesma comarca
	CONSTRAINT pk_comarca PRIMARY KEY (pk_comarca),
	CONSTRAINT fk01_comarca FOREIGN KEY (comarca_nome2) REFERENCES app_hist.comarca(pk_comarca)
);
COMMENT ON TABLE app_hist.comarca IS 'guarda os nomes das comarcas onde os documentos foram emitidos';

-- Column comments

COMMENT ON COLUMN app_hist.comarca.pk_comarca IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.comarca.nome_comarca IS 'Identificador da região';
COMMENT ON COLUMN app_hist.comarca.comarca_nome2 IS 'guarda a relação entre a antiga e nova nomenclatura para a mesma comarca';


-- app_hist.freguesia definition

-- Drop table

-- DROP TABLE app_hist.freguesia;

CREATE TABLE app_hist.freguesia (
	pk_freguesia int2 NOT NULL DEFAULT nextval('app_hist.freguesia_pk_seq'::regclass), -- Nº sequencial, sem significado
	nome_freguesia varchar(60) NULL, -- Nome da freguesia onde o documento foi emitido
	comarca int2 NULL,
	capitania int2 NULL,
	CONSTRAINT pk_freguesia PRIMARY KEY (pk_freguesia),
	CONSTRAINT freguesia_capitania_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT freguesia_comarca_fk FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca)
);
COMMENT ON TABLE app_hist.freguesia IS 'guarda os nomes das freguesias onde os documentos foram emitidos';

-- Column comments

COMMENT ON COLUMN app_hist.freguesia.pk_freguesia IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.freguesia.nome_freguesia IS 'Nome da freguesia onde o documento foi emitido';


-- app_hist.oficio_titulo definition

-- Drop table

-- DROP TABLE app_hist.oficio_titulo;

CREATE TABLE app_hist.oficio_titulo (
	pk_oficio_titulo int2 NOT NULL DEFAULT nextval('app_hist.cargo_titulo_pk_cargo_titulo_seq'::regclass), -- Código de identificação para o ofício/título, nº sequencial sem significado
	designacao varchar(50) NOT NULL, -- ofício ou título que qualifica a pessoa ou órgão
	oficio_agregador int2 NULL, -- código de identificação da tabela ofício agregador
	CONSTRAINT cargo_titulo_pkey PRIMARY KEY (pk_oficio_titulo),
	CONSTRAINT fk01_oficio_titulo_oficio_titulo FOREIGN KEY (oficio_agregador) REFERENCES app_hist.oficio_agregador(pk_oficio_agregador)
);
COMMENT ON TABLE app_hist.oficio_titulo IS 'guarda o ofício/título das autoridades que elaboraram a petição e/ou são destinatárias das ordens';

-- Column comments

COMMENT ON COLUMN app_hist.oficio_titulo.pk_oficio_titulo IS 'Código de identificação para o ofício/título, nº sequencial sem significado';
COMMENT ON COLUMN app_hist.oficio_titulo.designacao IS 'ofício ou título que qualifica a pessoa ou órgão';
COMMENT ON COLUMN app_hist.oficio_titulo.oficio_agregador IS 'código de identificação da tabela ofício agregador';


-- app_hist.referencia_documental definition

-- Drop table

-- DROP TABLE app_hist.referencia_documental;

CREATE TABLE app_hist.referencia_documental (
	pk_ref_documento smallserial NOT NULL, -- número sequencial, sem significado
	referencia varchar(250) NULL, -- código de identificação (referência) do documento original
	numero varchar(20) NULL, -- número do códice ou da caixa. Pode conter o período ano de início e fim
	complemento varchar(10) NULL, -- número da página ou do documento
	url varchar(150) NULL, -- endereço url para os documentos que estão online
	tipo_referencia int2 NULL, -- número correspondente ao tipo da referência contido na tabela tipo_referencia_documental
	CONSTRAINT pk_referencia_documental PRIMARY KEY (pk_ref_documento),
	CONSTRAINT fk_referencia_documental FOREIGN KEY (tipo_referencia) REFERENCES app_hist.tipo_referencia_documental(pk_tipo_referencia)
);
COMMENT ON TABLE app_hist.referencia_documental IS 'guarda a referência do documento original';

-- Column comments

COMMENT ON COLUMN app_hist.referencia_documental.pk_ref_documento IS 'número sequencial, sem significado';
COMMENT ON COLUMN app_hist.referencia_documental.referencia IS 'código de identificação (referência) do documento original';
COMMENT ON COLUMN app_hist.referencia_documental.numero IS 'número do códice ou da caixa. Pode conter o período ano de início e fim';
COMMENT ON COLUMN app_hist.referencia_documental.complemento IS 'número da página ou do documento';
COMMENT ON COLUMN app_hist.referencia_documental.url IS 'endereço url para os documentos que estão online';
COMMENT ON COLUMN app_hist.referencia_documental.tipo_referencia IS 'número correspondente ao tipo da referência contido na tabela tipo_referencia_documental';


-- app_hist.resposta definition

-- Drop table

-- DROP TABLE app_hist.resposta;

CREATE TABLE app_hist.resposta (
	pk_resposta serial4 NOT NULL, -- número sequencial, sem significado
	data_resposta date NULL, -- data completa: dia/mês/ano (quando houver)
	resumo text NULL, -- resumo da resposta
	nova_ordem_nao_cumprimento varchar(50) NULL, -- decisão
	tipologia_diplomatica int2 NULL, -- número que identifica na tabela tipologia_diplomática o alvará, decreto, etc.
	registro int2 NULL, -- número que identifica na tabela registro o nome do registro
	ano_resposta int2 NULL, -- ano da resposta, deve ser o mesmo ano se a data_resposta tiver sido preenchida
	referencia_documental int2 NULL, -- Identificador do documento, está na tabela referencia_documental
	impressos varchar(2000) NULL, -- guarda os nomes dos impressos relacionados com a resposta
	ano_post varchar(1) NULL,
	folio_pagina varchar(40) NULL,
	CONSTRAINT resposta_pkey PRIMARY KEY (pk_resposta),
	CONSTRAINT fk01_resposta FOREIGN KEY (tipologia_diplomatica) REFERENCES app_hist.tipologia_diplomatica(pk_tipologia) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_resposta FOREIGN KEY (registro) REFERENCES app_hist.registro(pk_registro) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk03_resposta FOREIGN KEY (referencia_documental) REFERENCES app_hist.referencia_documental(pk_ref_documento)
);
COMMENT ON TABLE app_hist.resposta IS 'guarda as ordens (em suas diferentes tipologias: provisões, decretos, alvarás, leis, etc.) que foram formadas no Conselho Ultramarino e/ou Secretaria de Estado a partir das petições apresentadas da América';

-- Column comments

COMMENT ON COLUMN app_hist.resposta.pk_resposta IS 'número sequencial, sem significado';
COMMENT ON COLUMN app_hist.resposta.data_resposta IS 'data completa: dia/mês/ano (quando houver)';
COMMENT ON COLUMN app_hist.resposta.resumo IS 'resumo da resposta';
COMMENT ON COLUMN app_hist.resposta.nova_ordem_nao_cumprimento IS 'decisão';
COMMENT ON COLUMN app_hist.resposta.tipologia_diplomatica IS 'número que identifica na tabela tipologia_diplomática o alvará, decreto, etc.';
COMMENT ON COLUMN app_hist.resposta.registro IS 'número que identifica na tabela registro o nome do registro';
COMMENT ON COLUMN app_hist.resposta.ano_resposta IS 'ano da resposta, deve ser o mesmo ano se a data_resposta tiver sido preenchida';
COMMENT ON COLUMN app_hist.resposta.referencia_documental IS 'Identificador do documento, está na tabela referencia_documental';
COMMENT ON COLUMN app_hist.resposta.impressos IS 'guarda os nomes dos impressos relacionados com a resposta';

-- Table Triggers

create trigger atualiza_ano_resposta before
insert
    or
update
    on
    app_hist.resposta for each row execute function app_hist.resposta_ano();


-- app_hist.tema definition

-- Drop table

-- DROP TABLE app_hist.tema;

CREATE TABLE app_hist.tema (
	pk_tema smallserial NOT NULL, -- Nº sequencial, sem significado
	tema varchar(50) NULL, -- Nomenclatura da classificação
	agregador int4 NULL,
	CONSTRAINT tema_pkey PRIMARY KEY (pk_tema),
	CONSTRAINT agregador_tema_fk FOREIGN KEY (agregador) REFERENCES app_hist.tema_agregador(pk_tema_agregador)
);
COMMENT ON TABLE app_hist.tema IS 'guarda a relação com o que é pedido ou solicitado';

-- Column comments

COMMENT ON COLUMN app_hist.tema.pk_tema IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.tema.tema IS 'Nomenclatura da classificação';


-- app_hist.termo definition

-- Drop table

-- DROP TABLE app_hist.termo;

CREATE TABLE app_hist.termo (
	pk_termo smallserial NOT NULL, -- Nº sequencial, sem significado
	nome_termo varchar(100) NULL, -- Nome da localidade (termo ou vila) de origem do documento
	capitania int2 NULL, -- Identificador da tabela  capitania
	termo_nome2 int2 NULL, -- guarda a relação entre a antiga e nova nomenclatura para o mesmo termo
	CONSTRAINT termo_pkey PRIMARY KEY (pk_termo),
	CONSTRAINT fk01_termo FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT fk02_termo FOREIGN KEY (termo_nome2) REFERENCES app_hist.termo(pk_termo)
);
COMMENT ON TABLE app_hist.termo IS 'guarda os nomes das vilas onde os documentos foram emitidos';

-- Column comments

COMMENT ON COLUMN app_hist.termo.pk_termo IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.termo.nome_termo IS 'Nome da localidade (termo ou vila) de origem do documento';
COMMENT ON COLUMN app_hist.termo.capitania IS 'Identificador da tabela  capitania';
COMMENT ON COLUMN app_hist.termo.termo_nome2 IS 'guarda a relação entre a antiga e nova nomenclatura para o mesmo termo';


-- app_hist.ultramar definition

-- Drop table

-- DROP TABLE app_hist.ultramar;

CREATE TABLE app_hist.ultramar (
	pk_ultramar int4 NOT NULL DEFAULT nextval('app_hist.ultramar_pk_seq'::regclass), -- Nº sequencial, sem significado
	resumo text NULL, -- resumo do documento
	registro int2 NULL, -- número que identifica na tabela registro o nome do registro
	"data" date NULL, -- Data completa - dia, mês, ano (quando houver)
	ano int2 NULL, -- Ano do documento
	referencia_documental int2 NULL, -- Identificador do documento, está na tabela referencia_documental
	autoridade varchar(80) NULL, -- nome da autoridade
	ano_post varchar(1) NULL,
	folio_pagina varchar(40) NULL,
	CONSTRAINT pk_ultramar PRIMARY KEY (pk_ultramar),
	CONSTRAINT fk01_ultramar_registro FOREIGN KEY (registro) REFERENCES app_hist.registro(pk_registro),
	CONSTRAINT fk02_ultramar FOREIGN KEY (referencia_documental) REFERENCES app_hist.referencia_documental(pk_ref_documento)
);
COMMENT ON TABLE app_hist.ultramar IS 'guarda informações e outros documentos elaborados no ultramar na tramitação de um caso';

-- Column comments

COMMENT ON COLUMN app_hist.ultramar.pk_ultramar IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.ultramar.resumo IS 'resumo do documento';
COMMENT ON COLUMN app_hist.ultramar.registro IS 'número que identifica na tabela registro o nome do registro';
COMMENT ON COLUMN app_hist.ultramar."data" IS 'Data completa - dia, mês, ano (quando houver)';
COMMENT ON COLUMN app_hist.ultramar.ano IS 'Ano do documento';
COMMENT ON COLUMN app_hist.ultramar.referencia_documental IS 'Identificador do documento, está na tabela referencia_documental';
COMMENT ON COLUMN app_hist.ultramar.autoridade IS 'nome da autoridade';


-- app_hist.consulta definition

-- Drop table

-- DROP TABLE app_hist.consulta;

CREATE TABLE app_hist.consulta (
	pk_consulta int4 NOT NULL DEFAULT nextval('app_hist.consulta_pk_seq'::regclass), -- Nº sequencial, sem significado
	data_parecer_regio date NULL, -- Data do parecer régio
	referencia_documental int2 NULL, -- Identificador do documento, está na tabela referencia_documental
	sumula text NULL, -- Resumo do resumo (feita no documento)
	data_consulta date NULL, -- Data completa da consulta - dia, mês, ano (quando houver)
	ano_consulta int2 NULL, -- Ano da consulta
	resumo text NULL, -- resumo da consulta
	registro int2 NULL, -- número que identifica na tabela registro o nome do registro
	parecer_regio text NULL, -- Opinião do rei sobre a consulta
	ano_post varchar(1) NULL,
	folio_pagina varchar(40) NULL,
	CONSTRAINT pk_consulta PRIMARY KEY (pk_consulta),
	CONSTRAINT fk01_consulta FOREIGN KEY (registro) REFERENCES app_hist.registro(pk_registro),
	CONSTRAINT fk02_consulta FOREIGN KEY (referencia_documental) REFERENCES app_hist.referencia_documental(pk_ref_documento)
);
COMMENT ON TABLE app_hist.consulta IS 'guarda as informações sobre as consultas (uma forma de deliberação) feitas no âmbito do Conselho Ultramarino';

-- Column comments

COMMENT ON COLUMN app_hist.consulta.pk_consulta IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.consulta.data_parecer_regio IS 'Data do parecer régio';
COMMENT ON COLUMN app_hist.consulta.referencia_documental IS 'Identificador do documento, está na tabela referencia_documental';
COMMENT ON COLUMN app_hist.consulta.sumula IS 'Resumo do resumo (feita no documento)';
COMMENT ON COLUMN app_hist.consulta.data_consulta IS 'Data completa da consulta - dia, mês, ano (quando houver)';
COMMENT ON COLUMN app_hist.consulta.ano_consulta IS 'Ano da consulta';
COMMENT ON COLUMN app_hist.consulta.resumo IS 'resumo da consulta';
COMMENT ON COLUMN app_hist.consulta.registro IS 'número que identifica na tabela registro o nome do registro';
COMMENT ON COLUMN app_hist.consulta.parecer_regio IS 'Opinião do rei sobre a consulta';


-- app_hist.mandado definition

-- Drop table

-- DROP TABLE app_hist.mandado;

CREATE TABLE app_hist.mandado (
	pk_mandado int4 NOT NULL DEFAULT nextval('app_hist.mandado_pk_seq'::regclass), -- Nº sequencial, sem significado
	"data" date NULL, -- Data completa - dia, mês, ano (quando houver)
	ano int2 NULL, -- Ano do documento (mandado)
	registro int2 NULL, -- número que identifica na tabela registro o nome do registro
	referencia_documental int2 NULL, -- Identificador do documento, está na tabela referencia_documental
	resumo text NULL, -- resumo do mandado
	mandado varchar(80) NULL, -- para quem foi enviado
	nome_quem_envia varchar(80) NULL, -- Secretário ou conselho
	ano_post varchar(1) NULL,
	folio_pagina varchar(40) NULL,
	CONSTRAINT pk_mandado PRIMARY KEY (pk_mandado),
	CONSTRAINT fk01_mandado FOREIGN KEY (registro) REFERENCES app_hist.registro(pk_registro),
	CONSTRAINT fk02_mandado FOREIGN KEY (referencia_documental) REFERENCES app_hist.referencia_documental(pk_ref_documento)
);
COMMENT ON TABLE app_hist.mandado IS 'guarda informações sobre a tramitação interna (em Lisboa) do caso, particularmente a correspondência interna e externa.';

-- Column comments

COMMENT ON COLUMN app_hist.mandado.pk_mandado IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.mandado."data" IS 'Data completa - dia, mês, ano (quando houver)';
COMMENT ON COLUMN app_hist.mandado.ano IS 'Ano do documento (mandado)';
COMMENT ON COLUMN app_hist.mandado.registro IS 'número que identifica na tabela registro o nome do registro';
COMMENT ON COLUMN app_hist.mandado.referencia_documental IS 'Identificador do documento, está na tabela referencia_documental';
COMMENT ON COLUMN app_hist.mandado.resumo IS 'resumo do mandado';
COMMENT ON COLUMN app_hist.mandado.mandado IS 'para quem foi enviado';
COMMENT ON COLUMN app_hist.mandado.nome_quem_envia IS 'Secretário ou conselho';


-- app_hist.pessoa definition

-- Drop table

-- DROP TABLE app_hist.pessoa;

CREATE TABLE app_hist.pessoa (
	pk_pessoa int4 NOT NULL DEFAULT nextval('app_hist.requerente_pk_requerente_seq'::regclass), -- Nº sequencial, sem significado
	nome varchar(80) NULL, -- nome das pessoa ou organização
	idade int2 NULL, -- idade do requerente quando mencionada
	marcador_economico_ocupacao int2 NULL, -- identificador para a tabela marcador_economico_ocupacao
	sexo varchar(3) NULL, -- o sexo do requerente pode ser F, M ou F/M
	filiacao varchar(80) NULL, -- nome do pai e/ou da mãe
	termo int2 NULL, -- nº de identificação do termo da pessoa
	freguesia int2 NULL, -- nº de identificação da freguesia da pessoa
	comarca int2 NULL, -- nº de identificação da comarca da pessoa
	capitania int2 NULL,
	CONSTRAINT cns_requerente_sexo CHECK (((sexo)::text = ANY (ARRAY[('F'::character varying)::text, ('M'::character varying)::text, ('F/M'::character varying)::text, (NULL::character varying)::text]))),
	CONSTRAINT requerente_pkey PRIMARY KEY (pk_pessoa),
	CONSTRAINT fk01_pessoa FOREIGN KEY (marcador_economico_ocupacao) REFERENCES app_hist.marcador_economico_ocupacao(pk_economico_ocupacao),
	CONSTRAINT fk02_pessoa FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo),
	CONSTRAINT fk03_pessoa FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT fk04_pessoa FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT fk05_pessoa FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania)
);
COMMENT ON TABLE app_hist.pessoa IS 'guarda o nome de todas as  pessoas ou órgãos que elaboraram a petição, que rementem documentos ou são citadas em algum documento';

-- Column comments

COMMENT ON COLUMN app_hist.pessoa.pk_pessoa IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.pessoa.nome IS 'nome das pessoa ou organização';
COMMENT ON COLUMN app_hist.pessoa.idade IS 'idade do requerente quando mencionada';
COMMENT ON COLUMN app_hist.pessoa.marcador_economico_ocupacao IS 'identificador para a tabela marcador_economico_ocupacao';
COMMENT ON COLUMN app_hist.pessoa.sexo IS 'o sexo do requerente pode ser F, M ou F/M';
COMMENT ON COLUMN app_hist.pessoa.filiacao IS 'nome do pai e/ou da mãe';
COMMENT ON COLUMN app_hist.pessoa.termo IS 'nº de identificação do termo da pessoa';
COMMENT ON COLUMN app_hist.pessoa.freguesia IS 'nº de identificação da freguesia da pessoa';
COMMENT ON COLUMN app_hist.pessoa.comarca IS 'nº de identificação da comarca da pessoa';


-- app_hist.provocacao definition

-- Drop table

-- DROP TABLE app_hist.provocacao;

CREATE TABLE app_hist.provocacao (
	pk_provocacao int4 NOT NULL DEFAULT nextval('app_hist.provocacao_pk_seq'::regclass), -- Nº sequencial, sem significado
	resumo text NULL, -- resumo da provocação
	referencia_documental int2 NULL, -- Código de identificação (referência) do documento original
	"data" date NULL, -- data completa - dia, mês, ano (quando houver)
	ano int2 NULL, -- Ano do documento, deve ser igual ao ano da data quando esta é preenchida
	registro int2 NULL, -- número que identifica na tabela registro o nome do registro
	destinatario_autoridade_tratamento varchar(40) NULL, -- Autoridade salutatio (rei ou secretário de estado)
	ano_post varchar(1) NULL,
	folio_pagina varchar(50) NULL,
	CONSTRAINT pk_provocacao PRIMARY KEY (pk_provocacao),
	CONSTRAINT fk01_provocacao_provocacao FOREIGN KEY (registro) REFERENCES app_hist.registro(pk_registro),
	CONSTRAINT fk02_provocacao_provocacao FOREIGN KEY (referencia_documental) REFERENCES app_hist.referencia_documental(pk_ref_documento)
);
COMMENT ON TABLE app_hist.provocacao IS 'guarda informações sobre os documentos que originam os trâmites institucionais analisados. Fundamentalmente, tratam-se de petições, cartas e representações enviadas da América para o poder régio sediado em Lisboa';

-- Column comments

COMMENT ON COLUMN app_hist.provocacao.pk_provocacao IS 'Nº sequencial, sem significado';
COMMENT ON COLUMN app_hist.provocacao.resumo IS 'resumo da provocação';
COMMENT ON COLUMN app_hist.provocacao.referencia_documental IS 'Código de identificação (referência) do documento original';
COMMENT ON COLUMN app_hist.provocacao."data" IS 'data completa - dia, mês, ano (quando houver)';
COMMENT ON COLUMN app_hist.provocacao.ano IS 'Ano do documento, deve ser igual ao ano da data quando esta é preenchida';
COMMENT ON COLUMN app_hist.provocacao.registro IS 'número que identifica na tabela registro o nome do registro';
COMMENT ON COLUMN app_hist.provocacao.destinatario_autoridade_tratamento IS 'Autoridade salutatio (rei ou secretário de estado)';


-- app_hist.relac_capitania_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_capitania_consulta;

CREATE TABLE app_hist.relac_capitania_consulta (
	capitania int2 NOT NULL,
	consulta int2 NOT NULL,
	CONSTRAINT relac_capitania_consulta_pk PRIMARY KEY (capitania, consulta),
	CONSTRAINT relac_capitania_consulta_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT relac_capitania_consulta_fk_1 FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);


-- app_hist.relac_capitania_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_capitania_mandado;

CREATE TABLE app_hist.relac_capitania_mandado (
	capitania int2 NOT NULL,
	mandado int2 NOT NULL,
	CONSTRAINT relac_capitania_mandado_pk PRIMARY KEY (capitania, mandado),
	CONSTRAINT relac_capitania_mandado_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT relac_capitania_mandado_fk_1 FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado)
);


-- app_hist.relac_capitania_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_capitania_provocacao;

CREATE TABLE app_hist.relac_capitania_provocacao (
	capitania int2 NOT NULL,
	provocacao int2 NOT NULL,
	CONSTRAINT relac_capitania_provocacao_pk PRIMARY KEY (capitania, provocacao),
	CONSTRAINT relac_capitania_provocacao_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT relac_capitania_provocacao_fk_1 FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao)
);


-- app_hist.relac_capitania_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_capitania_resposta;

CREATE TABLE app_hist.relac_capitania_resposta (
	capitania int2 NOT NULL,
	resposta int2 NOT NULL,
	CONSTRAINT relac_capitania_resposta_pk PRIMARY KEY (capitania, resposta),
	CONSTRAINT relac_capitania_resposta_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT relac_capitania_resposta_fk_1 FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);


-- app_hist.relac_capitania_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_capitania_ultramar;

CREATE TABLE app_hist.relac_capitania_ultramar (
	capitania int2 NOT NULL,
	ultramar int2 NOT NULL,
	CONSTRAINT relac_capitania_ultramar_pk PRIMARY KEY (capitania, ultramar),
	CONSTRAINT relac_capitania_ultramar_fk FOREIGN KEY (capitania) REFERENCES app_hist.capitania(pk_capitania),
	CONSTRAINT relac_capitania_ultramar_fk_1 FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);


-- app_hist.relac_comarca_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_comarca_consulta;

CREATE TABLE app_hist.relac_comarca_consulta (
	comarca int2 NOT NULL,
	consulta int2 NOT NULL,
	CONSTRAINT relac_comarca_consulta_pk PRIMARY KEY (comarca, consulta),
	CONSTRAINT relac_comarca_consulta_fk FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT relac_comarca_consulta_fk_1 FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca)
);


-- app_hist.relac_comarca_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_comarca_mandado;

CREATE TABLE app_hist.relac_comarca_mandado (
	mandado int2 NOT NULL,
	comarca int2 NOT NULL,
	CONSTRAINT relac_comarca_mandado_pk PRIMARY KEY (mandado, comarca),
	CONSTRAINT relac_comarca_mandado_fk FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT relac_comarca_mandado_fk_1 FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca)
);


-- app_hist.relac_comarca_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_comarca_provocacao;

CREATE TABLE app_hist.relac_comarca_provocacao (
	comarca int2 NOT NULL,
	provocacao int2 NOT NULL,
	CONSTRAINT relac_comarca_provocacao_pk PRIMARY KEY (comarca, provocacao),
	CONSTRAINT relac_comarca_provocacao_fk FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT relac_comarca_provocacao_fk_1 FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao)
);


-- app_hist.relac_comarca_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_comarca_resposta;

CREATE TABLE app_hist.relac_comarca_resposta (
	comarca int2 NOT NULL,
	resposta int2 NOT NULL,
	CONSTRAINT relac_comarca_resposta_pk PRIMARY KEY (comarca, resposta),
	CONSTRAINT relac_comarca_resposta_fk FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT relac_comarca_resposta_fk_1 FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);


-- app_hist.relac_comarca_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_comarca_ultramar;

CREATE TABLE app_hist.relac_comarca_ultramar (
	comarca int2 NOT NULL,
	ultramar int2 NOT NULL,
	CONSTRAINT relac_comarca_ultramar_pk PRIMARY KEY (comarca, ultramar),
	CONSTRAINT relac_comarca_ultramar_fk FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT relac_comarca_ultramar_fk_1 FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);


-- app_hist.relac_conselheiros_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_conselheiros_consulta;

CREATE TABLE app_hist.relac_conselheiros_consulta (
	conselheiro int2 NOT NULL, -- Identificador da tabela conselheiros_consulta
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	CONSTRAINT pk_relac_conselheiros_consulta PRIMARY KEY (conselheiro, consulta),
	CONSTRAINT fk01_relac_conselheiros_consulta FOREIGN KEY (conselheiro) REFERENCES app_hist.conselheiros_consulta(pk_conselheiro),
	CONSTRAINT fk02_relac_conselheiros_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_conselheiros_consulta IS 'guarda os relacionamentos entre a relação consulta e conselheiros_consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_conselheiros_consulta.conselheiro IS 'Identificador da tabela conselheiros_consulta';
COMMENT ON COLUMN app_hist.relac_conselheiros_consulta.consulta IS 'Identificador da tabela consulta';


-- app_hist.relac_consulta_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_consulta_consulta;

CREATE TABLE app_hist.relac_consulta_consulta (
	consulta_mais_antiga int4 NOT NULL, -- Identificador da tabela consulta - documento com data mais antiga
	consulta_mais_recente int4 NOT NULL, -- Identificador da tabela consulta - documento com data mais recente
	CONSTRAINT pk_relac_consulta_consulta PRIMARY KEY (consulta_mais_antiga, consulta_mais_recente),
	CONSTRAINT fk01_relac_consulta_consulta FOREIGN KEY (consulta_mais_antiga) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT fk02_relac_consulta_consulta FOREIGN KEY (consulta_mais_recente) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_consulta_consulta IS 'guarda os relacionamentos entre a relação provocacao e outras provocacoes';

-- Column comments

COMMENT ON COLUMN app_hist.relac_consulta_consulta.consulta_mais_antiga IS 'Identificador da tabela consulta - documento com data mais antiga';
COMMENT ON COLUMN app_hist.relac_consulta_consulta.consulta_mais_recente IS 'Identificador da tabela consulta - documento com data mais recente';


-- app_hist.relac_consulta_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_consulta_provocacao;

CREATE TABLE app_hist.relac_consulta_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	CONSTRAINT pk_relac_consulta_provocacao PRIMARY KEY (provocacao, consulta),
	CONSTRAINT fk01_relac_consulta_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_consulta_provocacao FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_consulta_provocacao IS 'guarda os relacionamentos entre a relação consulta e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_consulta_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_consulta_provocacao.consulta IS 'Identificador da tabela consulta';


-- app_hist.relac_consulta_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_consulta_resposta;

CREATE TABLE app_hist.relac_consulta_resposta (
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT pk_relac_consulta_resposta PRIMARY KEY (consulta, resposta),
	CONSTRAINT fk01_relac_consulta_resposta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT fk02_relac_consulta_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_consulta_resposta IS 'guarda os relacionamentos entre as tabelas  consulta e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_consulta_resposta.consulta IS 'Identificador da tabela consulta';
COMMENT ON COLUMN app_hist.relac_consulta_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_destinatario_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_destinatario_resposta;

CREATE TABLE app_hist.relac_destinatario_resposta (
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	destinatario int4 NOT NULL, -- Identificador da tabela destinatario_resposta
	oficio_titulo int2 NULL,
	CONSTRAINT relac_destinatario_resposta_pkey PRIMARY KEY (resposta, destinatario),
	CONSTRAINT fk01_relac_d_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk01_relac_destinatario_resposta FOREIGN KEY (destinatario) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_destinatario_resposta FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_destinatario_resposta IS 'guarda os relacionamentos entre a relação destinatario_resposta e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_destinatario_resposta.resposta IS 'Identificador da tabela resposta';
COMMENT ON COLUMN app_hist.relac_destinatario_resposta.destinatario IS 'Identificador da tabela destinatario_resposta';


-- app_hist.relac_freguesia_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_freguesia_consulta;

CREATE TABLE app_hist.relac_freguesia_consulta (
	freguesia int2 NOT NULL,
	consulta int2 NOT NULL,
	CONSTRAINT relac_freguesia_consulta_pk PRIMARY KEY (freguesia, consulta),
	CONSTRAINT relac_freguesia_consulta_fk FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT relac_freguesia_consulta_fk_1 FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);


-- app_hist.relac_freguesia_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_freguesia_mandado;

CREATE TABLE app_hist.relac_freguesia_mandado (
	freguesia int2 NOT NULL,
	mandado int2 NOT NULL,
	CONSTRAINT relac_freguesia_mandado_pk PRIMARY KEY (freguesia, mandado),
	CONSTRAINT relac_freguesia_mandado_fk FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT relac_freguesia_mandado_fk_1 FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado)
);


-- app_hist.relac_freguesia_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_freguesia_provocacao;

CREATE TABLE app_hist.relac_freguesia_provocacao (
	freguesia int2 NOT NULL,
	provocacao int2 NOT NULL,
	CONSTRAINT relac_freguesia_provocacao_pk PRIMARY KEY (freguesia, provocacao),
	CONSTRAINT relac_freguesia_provocacao_fk FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT relac_freguesia_provocacao_fk_1 FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao)
);


-- app_hist.relac_freguesia_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_freguesia_resposta;

CREATE TABLE app_hist.relac_freguesia_resposta (
	freguesia int2 NOT NULL,
	resposta int2 NOT NULL,
	CONSTRAINT relac_freguesia_resposta_pk PRIMARY KEY (freguesia, resposta),
	CONSTRAINT relac_freguesia_resposta_fk FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT relac_freguesia_resposta_fk_1 FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);


-- app_hist.relac_freguesia_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_freguesia_ultramar;

CREATE TABLE app_hist.relac_freguesia_ultramar (
	freguesia int2 NOT NULL,
	ultramar int2 NOT NULL,
	CONSTRAINT relac_freguesia_ultramar_pk PRIMARY KEY (freguesia, ultramar),
	CONSTRAINT relac_freguesia_ultramar_fk FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia),
	CONSTRAINT relac_freguesia_ultramar_fk_1 FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);


-- app_hist.relac_instado_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_instado_resposta;

CREATE TABLE app_hist.relac_instado_resposta (
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	instado int4 NOT NULL, -- Identificador da tabela instanciado_resposta
	oficio_titulo int2 NULL, -- identificador do ofício ou título da tabela oficio_titulo  para o instado
	CONSTRAINT pk_relac_instado_resposta PRIMARY KEY (resposta, instado),
	CONSTRAINT fk01_relac_instado_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta),
	CONSTRAINT fk03_relac_instado_resposta FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo),
	CONSTRAINT fk_relac_instado_resposta FOREIGN KEY (instado) REFERENCES app_hist.pessoa(pk_pessoa)
);
COMMENT ON TABLE app_hist.relac_instado_resposta IS 'guarda os relacionamentos entre a relação destinatario_resposta e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_instado_resposta.resposta IS 'Identificador da tabela resposta';
COMMENT ON COLUMN app_hist.relac_instado_resposta.instado IS 'Identificador da tabela instanciado_resposta';
COMMENT ON COLUMN app_hist.relac_instado_resposta.oficio_titulo IS 'identificador do ofício ou título da tabela oficio_titulo  para o instado';


-- app_hist.relac_mandado_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_mandado_consulta;

CREATE TABLE app_hist.relac_mandado_consulta (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	CONSTRAINT pk_relac_mandado_consulta PRIMARY KEY (mandado, consulta),
	CONSTRAINT fk01_relac_mandado_consulta FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk02_relac_mandado_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_mandado_consulta IS 'guarda os relacionamentos entre as tabelas  mandado e consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_mandado_consulta.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_mandado_consulta.consulta IS 'Identificador da tabela consulta';


-- app_hist.relac_mandado_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_mandado_mandado;

CREATE TABLE app_hist.relac_mandado_mandado (
	mandado_mais_antigo int4 NOT NULL,
	mandado_mais_recente int4 NOT NULL,
	CONSTRAINT pk_relac_mandado_mandado PRIMARY KEY (mandado_mais_antigo, mandado_mais_recente),
	CONSTRAINT fk01_relac_mandado_mandado FOREIGN KEY (mandado_mais_antigo) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk02_relac_mandado_mandado FOREIGN KEY (mandado_mais_recente) REFERENCES app_hist.mandado(pk_mandado)
);


-- app_hist.relac_mandado_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_mandado_provocacao;

CREATE TABLE app_hist.relac_mandado_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	CONSTRAINT pk_relac_mandado_provocacao PRIMARY KEY (provocacao, mandado),
	CONSTRAINT fk01_relac_mandado_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_mandado_provocacao FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado)
);
COMMENT ON TABLE app_hist.relac_mandado_provocacao IS 'guarda os relacionamentos entre a relação mandado e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_mandado_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_mandado_provocacao.mandado IS 'Identificador da tabela mandado';


-- app_hist.relac_mandado_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_mandado_resposta;

CREATE TABLE app_hist.relac_mandado_resposta (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT pk_relac_mandado_resposta PRIMARY KEY (mandado, resposta),
	CONSTRAINT fk01_relac_mandado_resposta FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk02_relac_mandado_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_mandado_resposta IS 'guarda os relacionamentos entre a relação mandado e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_mandado_resposta.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_mandado_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_mandado_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_mandado_ultramar;

CREATE TABLE app_hist.relac_mandado_ultramar (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	ultrarmar int4 NOT NULL, -- Identificador da tabela ultramar
	CONSTRAINT pk_relac_mandado_ultramar PRIMARY KEY (mandado, ultrarmar),
	CONSTRAINT fk01_relac_mandado_ultramar FOREIGN KEY (ultrarmar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_mandado_ultramar FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado)
);
COMMENT ON TABLE app_hist.relac_mandado_ultramar IS 'guarda os relacionamentos entre as tabelas mandado e ultramar';

-- Column comments

COMMENT ON COLUMN app_hist.relac_mandado_ultramar.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_mandado_ultramar.ultrarmar IS 'Identificador da tabela ultramar';


-- app_hist.relac_marcador_economico_ocupacao_pessoa definition

-- Drop table

-- DROP TABLE app_hist.relac_marcador_economico_ocupacao_pessoa;

CREATE TABLE app_hist.relac_marcador_economico_ocupacao_pessoa (
	marcador_economico_ocupacao int2 NOT NULL,
	pessoa int4 NOT NULL,
	ano int4 NULL,
	CONSTRAINT pk_relac_marcador_economico_ocupacao_pessoa PRIMARY KEY (marcador_economico_ocupacao, pessoa),
	CONSTRAINT fk01_pessoa FOREIGN KEY (marcador_economico_ocupacao) REFERENCES app_hist.marcador_economico_ocupacao(pk_economico_ocupacao),
	CONSTRAINT fk02_relac_marcador_economico_ocupacao_pessoa FOREIGN KEY (pessoa) REFERENCES app_hist.pessoa(pk_pessoa)
);


-- app_hist.relac_marcador_socio_juridico_pessoa definition

-- Drop table

-- DROP TABLE app_hist.relac_marcador_socio_juridico_pessoa;

CREATE TABLE app_hist.relac_marcador_socio_juridico_pessoa (
	marcador_socio_juridico int2 NOT NULL, -- código de identificação para a tabela marcador_juridico
	pessoa int4 NOT NULL, -- código de identificação ara a tabela pessoa
	ano int4 NULL,
	CONSTRAINT pk_relac_marcador_juridico_pessoa PRIMARY KEY (marcador_socio_juridico, pessoa),
	CONSTRAINT fk01_relac_marcador_juridico_pessoa FOREIGN KEY (pessoa) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk02_relac_marcador_juridico_pessoa FOREIGN KEY (marcador_socio_juridico) REFERENCES app_hist.marcador_status_socio_juridico_pessoa(pk_marcador_socio_juridico)
);
COMMENT ON TABLE app_hist.relac_marcador_socio_juridico_pessoa IS 'guarda o relacionamento entre a pessoa e os vários marcadores sócio- jurídicos que podem ter.';

-- Column comments

COMMENT ON COLUMN app_hist.relac_marcador_socio_juridico_pessoa.marcador_socio_juridico IS 'código de identificação para a tabela marcador_juridico';
COMMENT ON COLUMN app_hist.relac_marcador_socio_juridico_pessoa.pessoa IS 'código de identificação ara a tabela pessoa';


-- app_hist.relac_oficio_pessoa definition

-- Drop table

-- DROP TABLE app_hist.relac_oficio_pessoa;

CREATE TABLE app_hist.relac_oficio_pessoa (
	oficio_titulo int2 NOT NULL, -- Identificador da tabela oficio_titulo
	pessoa int4 NOT NULL, -- Identificador da tabela pessoa
	CONSTRAINT pk_relac_oficio_requerente PRIMARY KEY (oficio_titulo, pessoa),
	CONSTRAINT fk01_relac_oficio_requerente FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo),
	CONSTRAINT fk02_relac_oficio_requerente FOREIGN KEY (pessoa) REFERENCES app_hist.pessoa(pk_pessoa)
);
COMMENT ON TABLE app_hist.relac_oficio_pessoa IS 'guarda os relacionamentos entre a relação pessoa e oficio';

-- Column comments

COMMENT ON COLUMN app_hist.relac_oficio_pessoa.oficio_titulo IS 'Identificador da tabela oficio_titulo';
COMMENT ON COLUMN app_hist.relac_oficio_pessoa.pessoa IS 'Identificador da tabela pessoa';


-- app_hist.relac_pchave_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_pchave_consulta;

CREATE TABLE app_hist.relac_pchave_consulta (
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	palavra_chave int2 NOT NULL, -- Identificador da tabela palavra_chave
	CONSTRAINT pk_relac_pchave_consulta PRIMARY KEY (consulta, palavra_chave),
	CONSTRAINT fk01_relac_pchave_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT fk02_relac_pchave_consulta FOREIGN KEY (palavra_chave) REFERENCES app_hist.palavra_chave(pk_palavra_chave)
);
COMMENT ON TABLE app_hist.relac_pchave_consulta IS 'guarda os relacionamentos entre a relação palavra_chave e cosulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pchave_consulta.consulta IS 'Identificador da tabela consulta';
COMMENT ON COLUMN app_hist.relac_pchave_consulta.palavra_chave IS 'Identificador da tabela palavra_chave';


-- app_hist.relac_pchave_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_pchave_mandado;

CREATE TABLE app_hist.relac_pchave_mandado (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	palavra_chave int2 NOT NULL, -- Identificador da tabela palavra_chave
	CONSTRAINT pk_relac_pchave_mandado PRIMARY KEY (mandado, palavra_chave),
	CONSTRAINT fk01_relac_pchave_mandado FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk02_relac_pchave_mandado FOREIGN KEY (palavra_chave) REFERENCES app_hist.palavra_chave(pk_palavra_chave)
);
COMMENT ON TABLE app_hist.relac_pchave_mandado IS 'guarda os relacionamentos entre a relação palavra_chave e mandado';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pchave_mandado.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_pchave_mandado.palavra_chave IS 'Identificador da tabela palavra_chave';


-- app_hist.relac_pchave_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_pchave_provocacao;

CREATE TABLE app_hist.relac_pchave_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	palavra_chave int2 NOT NULL, -- Identificador da tabela palavra_chave
	CONSTRAINT pk_relac_pchave_provocacao PRIMARY KEY (provocacao, palavra_chave),
	CONSTRAINT fk01_relac_pchave_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_pchave_provocacao FOREIGN KEY (palavra_chave) REFERENCES app_hist.palavra_chave(pk_palavra_chave)
);
COMMENT ON TABLE app_hist.relac_pchave_provocacao IS 'guarda os relacionamentos entre a relação palavra_chave e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pchave_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_pchave_provocacao.palavra_chave IS 'Identificador da tabela palavra_chave';


-- app_hist.relac_pchave_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_pchave_resposta;

CREATE TABLE app_hist.relac_pchave_resposta (
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	palavra_chave int2 NOT NULL, -- Identificador da tabela palavra_chave
	CONSTRAINT pk_relac_pchave_resposta PRIMARY KEY (resposta, palavra_chave),
	CONSTRAINT fk01_relac_pchave_resposta FOREIGN KEY (palavra_chave) REFERENCES app_hist.palavra_chave(pk_palavra_chave),
	CONSTRAINT fk02_relac_pchave_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_pchave_resposta IS 'guarda os relacionamentos entre a relação palavra_chave e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pchave_resposta.resposta IS 'Identificador da tabela resposta';
COMMENT ON COLUMN app_hist.relac_pchave_resposta.palavra_chave IS 'Identificador da tabela palavra_chave';


-- app_hist.relac_pchave_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_pchave_ultramar;

CREATE TABLE app_hist.relac_pchave_ultramar (
	ultramar int4 NOT NULL, -- Identificador da tabela multramar
	palavra_chave int2 NOT NULL, -- Identificador da tabela palavra_chave
	CONSTRAINT pk_relac_pchave_ultramar PRIMARY KEY (ultramar, palavra_chave),
	CONSTRAINT fk01_relac_pchave_ultramar FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_pchave_ultramar FOREIGN KEY (palavra_chave) REFERENCES app_hist.palavra_chave(pk_palavra_chave)
);
COMMENT ON TABLE app_hist.relac_pchave_ultramar IS 'guarda os relacionamentos entre a relação palavra_chave e ultramar';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pchave_ultramar.ultramar IS 'Identificador da tabela multramar';
COMMENT ON COLUMN app_hist.relac_pchave_ultramar.palavra_chave IS 'Identificador da tabela palavra_chave';


-- app_hist.relac_pcitadas_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_pcitadas_consulta;

CREATE TABLE app_hist.relac_pcitadas_consulta (
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	p_citadas int4 NOT NULL, -- Identificador da tabela pessoas_citadas
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_pcitadas_consulta PRIMARY KEY (consulta, p_citadas),
	CONSTRAINT fk01_relac_pcitadas_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT fk02_relac_pcitadas_consulta FOREIGN KEY (p_citadas) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_pcitadas_consulta FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_pcitadas_consulta IS 'guarda os relacionamentos entre a relação pessoas_citadas e consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pcitadas_consulta.consulta IS 'Identificador da tabela consulta';
COMMENT ON COLUMN app_hist.relac_pcitadas_consulta.p_citadas IS 'Identificador da tabela pessoas_citadas';
COMMENT ON COLUMN app_hist.relac_pcitadas_consulta.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_pcitadas_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_pcitadas_mandado;

CREATE TABLE app_hist.relac_pcitadas_mandado (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	p_citadas int4 NOT NULL, -- Identificador da tabela pessoas_citadas
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_pcitadas_mandado PRIMARY KEY (mandado, p_citadas),
	CONSTRAINT fk01_relac_pcitadas_mandado FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk03_relac_pcitadas_mandado FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_pcitadas_mandado IS 'guarda os relacionamentos entre a relação pessoas_citadas e mandado';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pcitadas_mandado.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_pcitadas_mandado.p_citadas IS 'Identificador da tabela pessoas_citadas';
COMMENT ON COLUMN app_hist.relac_pcitadas_mandado.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_pcitadas_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_pcitadas_provocacao;

CREATE TABLE app_hist.relac_pcitadas_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	p_citadas int4 NOT NULL, -- Identificador da tabela pessoas_citadas
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_pcitadas_provocacao PRIMARY KEY (provocacao, p_citadas),
	CONSTRAINT fk01_relac_pcitadas_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_pcitadas_provocacao FOREIGN KEY (p_citadas) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_pcitadas_provocacao FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_pcitadas_provocacao IS 'guarda os relacionamentos entre a relação provocacao e pessoas_citadas';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pcitadas_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_pcitadas_provocacao.p_citadas IS 'Identificador da tabela pessoas_citadas';
COMMENT ON COLUMN app_hist.relac_pcitadas_provocacao.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_pcitadas_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_pcitadas_ultramar;

CREATE TABLE app_hist.relac_pcitadas_ultramar (
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	p_citadas int4 NOT NULL, -- Identificador da tabela pessoas_citadas
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_pcitadas_ultramar PRIMARY KEY (ultramar, p_citadas),
	CONSTRAINT fk01_relac_pcitadas_ultramar FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_pcitadas_provocacao FOREIGN KEY (p_citadas) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_pcitadas_ultramar FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_pcitadas_ultramar IS 'guarda os relacionamentos entre a relação pessoas_citadas e ultramar';

-- Column comments

COMMENT ON COLUMN app_hist.relac_pcitadas_ultramar.ultramar IS 'Identificador da tabela ultramar';
COMMENT ON COLUMN app_hist.relac_pcitadas_ultramar.p_citadas IS 'Identificador da tabela pessoas_citadas';
COMMENT ON COLUMN app_hist.relac_pcitadas_ultramar.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_provocacao_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_provocacao_provocacao;

CREATE TABLE app_hist.relac_provocacao_provocacao (
	provocacao_mais_antiga int4 NOT NULL, -- Identificador da tabela provocacao - documento com data mais antiga
	provocacao_mais_recente int4 NOT NULL, -- Identificador da tabela provocacao - documento com data mais recente
	CONSTRAINT pk_relac_provocacao_provocacao PRIMARY KEY (provocacao_mais_antiga, provocacao_mais_recente),
	CONSTRAINT fk01_relac_provocacao_provocacao FOREIGN KEY (provocacao_mais_antiga) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_provocacao_provocacao FOREIGN KEY (provocacao_mais_recente) REFERENCES app_hist.provocacao(pk_provocacao)
);
COMMENT ON TABLE app_hist.relac_provocacao_provocacao IS 'guarda os relacionamentos entre a relação provocacao e outras provocacoes';

-- Column comments

COMMENT ON COLUMN app_hist.relac_provocacao_provocacao.provocacao_mais_antiga IS 'Identificador da tabela provocacao - documento com data mais antiga';
COMMENT ON COLUMN app_hist.relac_provocacao_provocacao.provocacao_mais_recente IS 'Identificador da tabela provocacao - documento com data mais recente';


-- app_hist.relac_provocacao_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_provocacao_resposta;

CREATE TABLE app_hist.relac_provocacao_resposta (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT pk_relac_provocacao_resposta PRIMARY KEY (provocacao, resposta),
	CONSTRAINT fk01_relac_provocacao_resposta FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_provocacao_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_provocacao_resposta IS 'guarda os relacionamentos entre a relação provocacao e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_provocacao_resposta.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_provocacao_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_remetente_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_remetente_provocacao;

CREATE TABLE app_hist.relac_remetente_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	remetente int4 NOT NULL, -- Identificador da tabela remetente
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_remetente_provocacao PRIMARY KEY (provocacao, remetente),
	CONSTRAINT fk02_relac_remetente_provocacao_0 FOREIGN KEY (remetente) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_remetente_provocacao_0 FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo),
	CONSTRAINT fk_relac_remetente_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao)
);
COMMENT ON TABLE app_hist.relac_remetente_provocacao IS 'guarda os relacionamentos entre a relação remetente e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_remetente_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_remetente_provocacao.remetente IS 'Identificador da tabela remetente';
COMMENT ON COLUMN app_hist.relac_remetente_provocacao.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_requerente_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_requerente_consulta;

CREATE TABLE app_hist.relac_requerente_consulta (
	requerente int4 NOT NULL, -- Identificador da tabela requerente
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_requerente_consulta PRIMARY KEY (requerente, consulta),
	CONSTRAINT fk01_relac_requerente_consulta FOREIGN KEY (requerente) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk02_relac_requerente_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT fk03_relac_requerente_consulta FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_requerente_consulta IS 'guarda os relacionamentos entre as tabelas requerente e consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_requerente_consulta.requerente IS 'Identificador da tabela requerente';
COMMENT ON COLUMN app_hist.relac_requerente_consulta.consulta IS 'Identificador da tabela consulta';
COMMENT ON COLUMN app_hist.relac_requerente_consulta.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_requerente_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_requerente_mandado;

CREATE TABLE app_hist.relac_requerente_mandado (
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	requerente int4 NOT NULL, -- Identificador da tabela requerente
	oficio_titulo int2 NULL,
	CONSTRAINT pk_relac_requerente_mandado PRIMARY KEY (mandado, requerente),
	CONSTRAINT fk01_relac_requerente_mandado FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT fk02_relac_requerente_mandado FOREIGN KEY (requerente) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_requerente_mandado FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_requerente_mandado IS 'guarda os relacionamentos entre a relação requerente e mandado';

-- Column comments

COMMENT ON COLUMN app_hist.relac_requerente_mandado.mandado IS 'Identificador da tabela mandado';
COMMENT ON COLUMN app_hist.relac_requerente_mandado.requerente IS 'Identificador da tabela requerente';


-- app_hist.relac_requerente_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_requerente_provocacao;

CREATE TABLE app_hist.relac_requerente_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	requerente int4 NOT NULL, -- Identificador da tabela requerente
	oficio_titulo int2 NULL, -- oficio ou título da pessoa citada, quando for conhecido
	CONSTRAINT pk_relac_requerente_provocacao PRIMARY KEY (provocacao, requerente),
	CONSTRAINT fk01_relac_requerente_provocacao FOREIGN KEY (requerente) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk02_relac_requerente_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk03_relac_requerente_provocacao FOREIGN KEY (oficio_titulo) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_requerente_provocacao IS 'guarda os relacionamentos entre a relação requerente e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_requerente_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_requerente_provocacao.requerente IS 'Identificador da tabela requerente';
COMMENT ON COLUMN app_hist.relac_requerente_provocacao.oficio_titulo IS 'oficio ou título da pessoa citada, quando for conhecido';


-- app_hist.relac_requerente_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_requerente_resposta;

CREATE TABLE app_hist.relac_requerente_resposta (
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	requerente int4 NOT NULL, -- Identificador da tabela requerente
	termo int2 NULL, -- Identificador da tabela termo
	comarca int2 NULL, -- comarca do requerente da resposta
	oficio_titular int2 NULL,
	freguesia int2 NULL,
	CONSTRAINT relac_requerente_resposta_pkey PRIMARY KEY (resposta, requerente),
	CONSTRAINT fk01_relac_r_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_relac_r_resposta FOREIGN KEY (requerente) REFERENCES app_hist.pessoa(pk_pessoa) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk03_relac_r_resposta FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk04_relac_requerente_resposta FOREIGN KEY (oficio_titular) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo),
	CONSTRAINT fk05_relac_requerente_resposta FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT fk06_relac_requerente_resposta FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia)
);
COMMENT ON TABLE app_hist.relac_requerente_resposta IS 'guarda os relacionamentos entre a relação requerente e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_requerente_resposta.resposta IS 'Identificador da tabela resposta';
COMMENT ON COLUMN app_hist.relac_requerente_resposta.requerente IS 'Identificador da tabela requerente';
COMMENT ON COLUMN app_hist.relac_requerente_resposta.termo IS 'Identificador da tabela termo';
COMMENT ON COLUMN app_hist.relac_requerente_resposta.comarca IS 'comarca do requerente da resposta';


-- app_hist.relac_requerente_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_requerente_ultramar;

CREATE TABLE app_hist.relac_requerente_ultramar (
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	requerente int4 NOT NULL, -- Identificador da tabela requerente
	oficio_titular int2 NULL,
	CONSTRAINT pk_relac_requerente_ultramar PRIMARY KEY (ultramar, requerente),
	CONSTRAINT fk01_relac_requerente_ultramar FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_requerente_ultramar FOREIGN KEY (requerente) REFERENCES app_hist.pessoa(pk_pessoa),
	CONSTRAINT fk03_relac_requerente_ultramar FOREIGN KEY (oficio_titular) REFERENCES app_hist.oficio_titulo(pk_oficio_titulo)
);
COMMENT ON TABLE app_hist.relac_requerente_ultramar IS 'guarda os relacionamentos entre a relação requerente e ultramar';

-- Column comments

COMMENT ON COLUMN app_hist.relac_requerente_ultramar.ultramar IS 'Identificador da tabela ultramar';
COMMENT ON COLUMN app_hist.relac_requerente_ultramar.requerente IS 'Identificador da tabela requerente';


-- app_hist.relac_resposta_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_resposta_resposta;

CREATE TABLE app_hist.relac_resposta_resposta (
	resposta_mais_antiga int4 NOT NULL, -- Identificador da tabela resposta - documento com data mais antiga
	resposta_mais_recente int4 NOT NULL, -- Identificador da tabela resposta - documento com data mais recente
	CONSTRAINT pk_relac_resposta_resposta PRIMARY KEY (resposta_mais_antiga, resposta_mais_recente),
	CONSTRAINT fk01_relac_resposta_resposta FOREIGN KEY (resposta_mais_antiga) REFERENCES app_hist.resposta(pk_resposta),
	CONSTRAINT fk02_relac_resposta_resposta FOREIGN KEY (resposta_mais_recente) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_resposta_resposta IS 'guarda os relacionamentos entre a relação provocacao e outras provocacoes';

-- Column comments

COMMENT ON COLUMN app_hist.relac_resposta_resposta.resposta_mais_antiga IS 'Identificador da tabela resposta - documento com data mais antiga';
COMMENT ON COLUMN app_hist.relac_resposta_resposta.resposta_mais_recente IS 'Identificador da tabela resposta - documento com data mais recente';


-- app_hist.relac_secretario_conselho_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_secretario_conselho_consulta;

CREATE TABLE app_hist.relac_secretario_conselho_consulta (
	secretario_conselho int2 NOT NULL,
	consulta int4 NOT NULL,
	CONSTRAINT relac_secretario_conselho_consulta_pkey PRIMARY KEY (secretario_conselho, consulta),
	CONSTRAINT fk01_relac_s_c_consulta FOREIGN KEY (secretario_conselho) REFERENCES app_hist.secretario_conselheiro(pk_secretario_conselheiro) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_relac_s_c_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta) ON DELETE CASCADE ON UPDATE CASCADE
);


-- app_hist.relac_secretario_conselho_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_secretario_conselho_mandado;

CREATE TABLE app_hist.relac_secretario_conselho_mandado (
	secretario_conselho int2 NOT NULL,
	mandado int4 NOT NULL,
	CONSTRAINT relac_secretario_conselho_mandado_pkey PRIMARY KEY (secretario_conselho, mandado),
	CONSTRAINT fk01_relac_s_c_mandado FOREIGN KEY (secretario_conselho) REFERENCES app_hist.secretario_conselheiro(pk_secretario_conselheiro) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_relac_s_c_mandado FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado) ON DELETE CASCADE ON UPDATE CASCADE
);


-- app_hist.relac_secretario_conselho_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_secretario_conselho_provocacao;

CREATE TABLE app_hist.relac_secretario_conselho_provocacao (
	secretario_conselho int2 NOT NULL,
	provocacao int4 NOT NULL,
	CONSTRAINT relac_secretario_conselho_provocacao_pkey PRIMARY KEY (secretario_conselho, provocacao),
	CONSTRAINT fk01_relac_s_c_provocacao FOREIGN KEY (secretario_conselho) REFERENCES app_hist.secretario_conselheiro(pk_secretario_conselheiro) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_relac_s_c_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao) ON DELETE CASCADE ON UPDATE CASCADE
);


-- app_hist.relac_secretario_conselho_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_secretario_conselho_resposta;

CREATE TABLE app_hist.relac_secretario_conselho_resposta (
	secretario_conselho int2 NOT NULL, -- Identificador da tabela secretario_conselho_resposta
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT relac_secretario_conselho_resposta_pkey PRIMARY KEY (secretario_conselho, resposta),
	CONSTRAINT fk02_relac_s_c_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT relac_secretario_conselho_resposta_fk FOREIGN KEY (secretario_conselho) REFERENCES app_hist.secretario_conselheiro(pk_secretario_conselheiro)
);
COMMENT ON TABLE app_hist.relac_secretario_conselho_resposta IS 'guarda os relacionamentos entre a relação secretario_resposta e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_secretario_conselho_resposta.secretario_conselho IS 'Identificador da tabela secretario_conselho_resposta';
COMMENT ON COLUMN app_hist.relac_secretario_conselho_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_secretario_conselho_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_secretario_conselho_ultramar;

CREATE TABLE app_hist.relac_secretario_conselho_ultramar (
	secretario_conselho int2 NOT NULL,
	ultramar int4 NOT NULL,
	CONSTRAINT relac_secretario_conselho_ultramar_pkey PRIMARY KEY (secretario_conselho, ultramar),
	CONSTRAINT fk01_relac_s_c_ultramar FOREIGN KEY (secretario_conselho) REFERENCES app_hist.secretario_conselheiro(pk_secretario_conselheiro) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk02_relac_s_c_ultramar FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar) ON DELETE CASCADE ON UPDATE CASCADE
);


-- app_hist.relac_tema_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_tema_consulta;

CREATE TABLE app_hist.relac_tema_consulta (
	tema int2 NOT NULL, -- Identificador da tabela tema
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	CONSTRAINT pk_relac_tema_consulta PRIMARY KEY (tema, consulta),
	CONSTRAINT fk01_relac_tema_consulta FOREIGN KEY (tema) REFERENCES app_hist.tema(pk_tema),
	CONSTRAINT fk02_relac_tema_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_tema_consulta IS 'guarda os relacionamentos entre as tabelas  tema e consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_tema_consulta.tema IS 'Identificador da tabela tema';
COMMENT ON COLUMN app_hist.relac_tema_consulta.consulta IS 'Identificador da tabela consulta';


-- app_hist.relac_tema_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_tema_mandado;

CREATE TABLE app_hist.relac_tema_mandado (
	tema int2 NOT NULL, -- Identificador da tabela tema
	mandado int4 NOT NULL, -- Identificador da tabela mandado
	CONSTRAINT pk_relac_tema_mandado PRIMARY KEY (tema, mandado),
	CONSTRAINT fk01_relac_tema_mandado FOREIGN KEY (tema) REFERENCES app_hist.tema(pk_tema),
	CONSTRAINT fk02_relac_tema_mandado FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado)
);
COMMENT ON TABLE app_hist.relac_tema_mandado IS 'guarda os relacionamentos entre a relação tema e mandado';

-- Column comments

COMMENT ON COLUMN app_hist.relac_tema_mandado.tema IS 'Identificador da tabela tema';
COMMENT ON COLUMN app_hist.relac_tema_mandado.mandado IS 'Identificador da tabela mandado';


-- app_hist.relac_tema_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_tema_provocacao;

CREATE TABLE app_hist.relac_tema_provocacao (
	tema int2 NOT NULL, -- Identificador da tabela tema
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	CONSTRAINT pk_relac_tema_provocacao PRIMARY KEY (tema, provocacao),
	CONSTRAINT fk01_relac_tema_provocacao_tema FOREIGN KEY (tema) REFERENCES app_hist.tema(pk_tema),
	CONSTRAINT fk02_relac_tema_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao)
);
COMMENT ON TABLE app_hist.relac_tema_provocacao IS 'guarda os relacionamentos entre a relação tema e provocação.';

-- Column comments

COMMENT ON COLUMN app_hist.relac_tema_provocacao.tema IS 'Identificador da tabela tema';
COMMENT ON COLUMN app_hist.relac_tema_provocacao.provocacao IS 'Identificador da tabela provocacao';


-- app_hist.relac_tema_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_tema_resposta;

CREATE TABLE app_hist.relac_tema_resposta (
	tema int2 NOT NULL, -- Identificador da tabela tema
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT pk_relac_tema_resposta PRIMARY KEY (tema, resposta),
	CONSTRAINT fk01_relac_tema_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta),
	CONSTRAINT fk02_relac_tema_resposta FOREIGN KEY (tema) REFERENCES app_hist.tema(pk_tema)
);
COMMENT ON TABLE app_hist.relac_tema_resposta IS 'guarda os relacionamentos entre a relação sub_tema e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_tema_resposta.tema IS 'Identificador da tabela tema';
COMMENT ON COLUMN app_hist.relac_tema_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_tema_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_tema_ultramar;

CREATE TABLE app_hist.relac_tema_ultramar (
	tema int2 NOT NULL, -- Identificador da tabela tema
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	CONSTRAINT pk_relac_tema_ultramar PRIMARY KEY (tema, ultramar),
	CONSTRAINT fk01_relac_tema_ultramar FOREIGN KEY (tema) REFERENCES app_hist.tema(pk_tema),
	CONSTRAINT fk02_relac_tema_ultramar FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);
COMMENT ON TABLE app_hist.relac_tema_ultramar IS 'guarda os relacionamentos entre a relação tema e ultramar';

-- Column comments

COMMENT ON COLUMN app_hist.relac_tema_ultramar.tema IS 'Identificador da tabela tema';
COMMENT ON COLUMN app_hist.relac_tema_ultramar.ultramar IS 'Identificador da tabela ultramar';


-- app_hist.relac_termo_comarca definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_comarca;

CREATE TABLE app_hist.relac_termo_comarca (
	termo int2 NOT NULL, -- Identificador da tabela termo
	comarca int2 NOT NULL, -- Identificador da tabela comarca
	CONSTRAINT pk_relac_termo_comarca PRIMARY KEY (termo, comarca),
	CONSTRAINT fk01_relac_termo_comarca FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo),
	CONSTRAINT fk02_relac_termo_comarca FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca)
);
COMMENT ON TABLE app_hist.relac_termo_comarca IS 'guarda os relacionamentos entre a relação termo_vila e comarca';

-- Column comments

COMMENT ON COLUMN app_hist.relac_termo_comarca.termo IS 'Identificador da tabela termo';
COMMENT ON COLUMN app_hist.relac_termo_comarca.comarca IS 'Identificador da tabela comarca';


-- app_hist.relac_termo_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_consulta;

CREATE TABLE app_hist.relac_termo_consulta (
	termo int2 NOT NULL,
	consulta int2 NOT NULL,
	CONSTRAINT relac_termo_consulta_pk PRIMARY KEY (termo, consulta),
	CONSTRAINT relac_termo_consulta_fk FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta),
	CONSTRAINT relac_termo_consulta_fk_1 FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo)
);


-- app_hist.relac_termo_freguesia definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_freguesia;

CREATE TABLE app_hist.relac_termo_freguesia (
	termo int2 NOT NULL, -- Identificador da tabela termo
	freguesia int2 NOT NULL, -- Identificador da tabela freguesia
	CONSTRAINT pk_relac_termo_freguesia PRIMARY KEY (termo, freguesia),
	CONSTRAINT fk01_relac_termo_freguesia FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo),
	CONSTRAINT fk02_relac_termo_freguesia FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia)
);
COMMENT ON TABLE app_hist.relac_termo_freguesia IS 'guarda os relacionamentos entre a relação termo_vila e freguesia';

-- Column comments

COMMENT ON COLUMN app_hist.relac_termo_freguesia.termo IS 'Identificador da tabela termo';
COMMENT ON COLUMN app_hist.relac_termo_freguesia.freguesia IS 'Identificador da tabela freguesia';


-- app_hist.relac_termo_mandado definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_mandado;

CREATE TABLE app_hist.relac_termo_mandado (
	termo int2 NOT NULL,
	mandado int2 NOT NULL,
	CONSTRAINT relac_termo_mandado_pk PRIMARY KEY (termo, mandado),
	CONSTRAINT relac_termo_mandado_fk FOREIGN KEY (mandado) REFERENCES app_hist.mandado(pk_mandado),
	CONSTRAINT relac_termo_mandado_fk_1 FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo)
);


-- app_hist.relac_termo_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_provocacao;

CREATE TABLE app_hist.relac_termo_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	termo int2 NOT NULL, -- Identificador da tabela termo
	comarca int2 NULL,
	freguesia int2 NULL,
	CONSTRAINT pk_relac_termo_provocacao PRIMARY KEY (provocacao, termo),
	CONSTRAINT fk01_relac_termo_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_termo_provocacao FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo),
	CONSTRAINT fk03_relac_termo_provocacao FOREIGN KEY (comarca) REFERENCES app_hist.comarca(pk_comarca),
	CONSTRAINT fk04_relac_termo_provocacao FOREIGN KEY (freguesia) REFERENCES app_hist.freguesia(pk_freguesia)
);
COMMENT ON TABLE app_hist.relac_termo_provocacao IS 'guarda os relacionamentos entre a relação termo e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_termo_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_termo_provocacao.termo IS 'Identificador da tabela termo';


-- app_hist.relac_termo_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_resposta;

CREATE TABLE app_hist.relac_termo_resposta (
	termo int2 NOT NULL,
	resposta int2 NOT NULL,
	CONSTRAINT relac_termo_resposta_pk PRIMARY KEY (termo, resposta),
	CONSTRAINT relac_termo_resposta_fk FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta),
	CONSTRAINT relac_termo_resposta_fk_1 FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo)
);


-- app_hist.relac_termo_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_termo_ultramar;

CREATE TABLE app_hist.relac_termo_ultramar (
	termo int2 NOT NULL,
	ultramar int2 NOT NULL,
	CONSTRAINT relac_termo_ultramar_pk PRIMARY KEY (termo, ultramar),
	CONSTRAINT relac_termo_ultramar_fk FOREIGN KEY (termo) REFERENCES app_hist.termo(pk_termo),
	CONSTRAINT relac_termo_ultramar_fk_1 FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);


-- app_hist.relac_ultramar_consulta definition

-- Drop table

-- DROP TABLE app_hist.relac_ultramar_consulta;

CREATE TABLE app_hist.relac_ultramar_consulta (
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	consulta int4 NOT NULL, -- Identificador da tabela consulta
	CONSTRAINT pk_relac_ultramar_consulta PRIMARY KEY (ultramar, consulta),
	CONSTRAINT fk01_relac_ultramar_consulta FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_ultramar_consulta FOREIGN KEY (consulta) REFERENCES app_hist.consulta(pk_consulta)
);
COMMENT ON TABLE app_hist.relac_ultramar_consulta IS 'guarda os relacionamentos entre as tabelas ultramar  e consulta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_ultramar_consulta.ultramar IS 'Identificador da tabela ultramar';
COMMENT ON COLUMN app_hist.relac_ultramar_consulta.consulta IS 'Identificador da tabela consulta';


-- app_hist.relac_ultramar_provocacao definition

-- Drop table

-- DROP TABLE app_hist.relac_ultramar_provocacao;

CREATE TABLE app_hist.relac_ultramar_provocacao (
	provocacao int4 NOT NULL, -- Identificador da tabela provocacao
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	CONSTRAINT pk_relac_ultramar_provocacao PRIMARY KEY (provocacao, ultramar),
	CONSTRAINT fk01_relac_ultramar_provocacao FOREIGN KEY (provocacao) REFERENCES app_hist.provocacao(pk_provocacao),
	CONSTRAINT fk02_relac_ultramar_provocacao FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar)
);
COMMENT ON TABLE app_hist.relac_ultramar_provocacao IS 'guarda os relacionamentos entre a relação ultramar e provocacao';

-- Column comments

COMMENT ON COLUMN app_hist.relac_ultramar_provocacao.provocacao IS 'Identificador da tabela provocacao';
COMMENT ON COLUMN app_hist.relac_ultramar_provocacao.ultramar IS 'Identificador da tabela ultramar';


-- app_hist.relac_ultramar_resposta definition

-- Drop table

-- DROP TABLE app_hist.relac_ultramar_resposta;

CREATE TABLE app_hist.relac_ultramar_resposta (
	ultramar int4 NOT NULL, -- Identificador da tabela ultramar
	resposta int4 NOT NULL, -- Identificador da tabela resposta
	CONSTRAINT pk_relac_ultramar_resposta PRIMARY KEY (ultramar, resposta),
	CONSTRAINT fk01_relac_ultramar_resposta FOREIGN KEY (ultramar) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_ultramar_resposta FOREIGN KEY (resposta) REFERENCES app_hist.resposta(pk_resposta)
);
COMMENT ON TABLE app_hist.relac_ultramar_resposta IS 'guarda os relacionamentos entre a relação ultramar e resposta';

-- Column comments

COMMENT ON COLUMN app_hist.relac_ultramar_resposta.ultramar IS 'Identificador da tabela ultramar';
COMMENT ON COLUMN app_hist.relac_ultramar_resposta.resposta IS 'Identificador da tabela resposta';


-- app_hist.relac_ultramar_ultramar definition

-- Drop table

-- DROP TABLE app_hist.relac_ultramar_ultramar;

CREATE TABLE app_hist.relac_ultramar_ultramar (
	ultramar_mais_antigo int4 NOT NULL,
	ultramar_mais_recente int4 NOT NULL,
	CONSTRAINT pk_relac_ultramar_ultramar PRIMARY KEY (ultramar_mais_antigo, ultramar_mais_recente),
	CONSTRAINT fk01_relac_ultramar_ultramar FOREIGN KEY (ultramar_mais_antigo) REFERENCES app_hist.ultramar(pk_ultramar),
	CONSTRAINT fk02_relac_ultramar_ultramar FOREIGN KEY (ultramar_mais_recente) REFERENCES app_hist.ultramar(pk_ultramar)
);

-- ************************
-- Criacao dos índices
-- ************************


CREATE UNIQUE INDEX IF NOT EXISTS pk_ultramar ON app_hist.ultramar USING btree (pk_ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS secretario_agregador_pk ON app_hist.secretario_agregador USING btree (pk_secretario_agregador);

CREATE UNIQUE INDEX IF NOT EXISTS secretario_concelheiro_pk ON app_hist.secretario_conselheiro USING btree (pk_secretario_conselheiro);

CREATE UNIQUE INDEX IF NOT EXISTS registro_pkey ON app_hist.registro USING btree (pk_registro);

CREATE UNIQUE INDEX IF NOT EXISTS relac_capitania_consulta_pk ON app_hist.relac_capitania_consulta USING btree (capitania, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_requerente_ultramar ON app_hist.relac_requerente_ultramar USING btree (ultramar, requerente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_mandado_provocacao ON app_hist.relac_mandado_provocacao USING btree (provocacao, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_tema_consulta ON app_hist.relac_tema_consulta USING btree (tema, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_referencia_documental ON app_hist.referencia_documental USING btree (pk_ref_documento);

CREATE UNIQUE INDEX IF NOT EXISTS utilizadores_pk ON app_hist.utilizadores USING btree (user_id);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pcitadas_mandado ON app_hist.relac_pcitadas_mandado USING btree (mandado, p_citadas);

CREATE UNIQUE INDEX IF NOT EXISTS relac_secretario_conselho_resposta_pkey ON app_hist.relac_secretario_conselho_resposta USING btree (secretario_conselho, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS cargo_titulo_pkey ON app_hist.oficio_titulo USING btree (pk_oficio_titulo);

CREATE UNIQUE INDEX IF NOT EXISTS tema_agregador_pk ON app_hist.tema_agregador USING btree (pk_tema_agregador);

CREATE UNIQUE INDEX IF NOT EXISTS relac_destinatario_resposta_pkey ON app_hist.relac_destinatario_resposta USING btree (resposta, destinatario);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_requerente_provocacao ON app_hist.relac_requerente_provocacao USING btree (provocacao, requerente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_consulta_provocacao ON app_hist.relac_consulta_provocacao USING btree (provocacao, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pchave_resposta ON app_hist.relac_pchave_resposta USING btree (resposta, palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pchave_provocacao ON app_hist.relac_pchave_provocacao USING btree (provocacao, palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_tema_resposta ON app_hist.relac_tema_resposta USING btree (tema, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_termo_consulta_pk ON app_hist.relac_termo_consulta USING btree (termo, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_tipo_referencia_documental ON app_hist.tipo_referencia_documental USING btree (pk_tipo_referencia);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_instado_resposta ON app_hist.relac_instado_resposta USING btree (resposta, instado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_consulta_consulta ON app_hist.relac_consulta_consulta USING btree (consulta_mais_antiga, consulta_mais_recente);

CREATE UNIQUE INDEX IF NOT EXISTS relac_comarca_mandado_pk ON app_hist.relac_comarca_mandado USING btree (mandado, comarca);

CREATE UNIQUE INDEX IF NOT EXISTS relac_freguesia_consulta_pk ON app_hist.relac_freguesia_consulta USING btree (freguesia, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_requerente_resposta_pkey ON app_hist.relac_requerente_resposta USING btree (resposta, requerente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_mandado_mandado ON app_hist.relac_mandado_mandado USING btree (mandado_mais_antigo, mandado_mais_recente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_oficio_requerente ON app_hist.relac_oficio_pessoa USING btree (oficio_titulo, pessoa);

CREATE UNIQUE INDEX IF NOT EXISTS pk_comarca ON app_hist.comarca USING btree (pk_comarca);

CREATE UNIQUE INDEX IF NOT EXISTS resposta_pkey ON app_hist.resposta USING btree (pk_resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_tema_mandado ON app_hist.relac_tema_mandado USING btree (tema, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS relac_capitania_ultramar_pk ON app_hist.relac_capitania_ultramar USING btree (capitania, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS relac_secretario_conselho_provocacao_pkey ON app_hist.relac_secretario_conselho_provocacao USING btree (secretario_conselho, provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_marcador_juridico_pessoa ON app_hist.relac_marcador_socio_juridico_pessoa USING btree (marcador_socio_juridico, pessoa);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_ultramar_resposta ON app_hist.relac_ultramar_resposta USING btree (ultramar, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_ultramar_consulta ON app_hist.relac_ultramar_consulta USING btree (ultramar, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pchave_consulta ON app_hist.relac_pchave_consulta USING btree (consulta, palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS pk_marcador_economico_ocupacao ON app_hist.marcador_economico_ocupacao USING btree (pk_economico_ocupacao);

CREATE UNIQUE INDEX IF NOT EXISTS relac_comarca_resposta_pk ON app_hist.relac_comarca_resposta USING btree (comarca, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_mandado ON app_hist.mandado USING btree (pk_mandado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_termo_freguesia ON app_hist.relac_termo_freguesia USING btree (termo, freguesia);

CREATE UNIQUE INDEX IF NOT EXISTS relac_secretario_conselho_ultramar_pkey ON app_hist.relac_secretario_conselho_ultramar USING btree (secretario_conselho, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pcitadas_provocacao ON app_hist.relac_pcitadas_provocacao USING btree (provocacao, p_citadas);

CREATE UNIQUE INDEX IF NOT EXISTS capitania_pkey ON app_hist.capitania USING btree (pk_capitania);

CREATE UNIQUE INDEX IF NOT EXISTS relac_comarca_consulta_pk ON app_hist.relac_comarca_consulta USING btree (comarca, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_termo_resposta_pk ON app_hist.relac_termo_resposta USING btree (termo, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_mandado_ultramar ON app_hist.relac_mandado_ultramar USING btree (mandado, ultrarmar);

CREATE UNIQUE INDEX IF NOT EXISTS tema_pkey ON app_hist.tema USING btree (pk_tema);

CREATE UNIQUE INDEX IF NOT EXISTS pk_marcador_juridico_requerente ON app_hist.marcador_status_socio_juridico_pessoa USING btree (pk_marcador_socio_juridico);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pchave_ultramar ON app_hist.relac_pchave_ultramar USING btree (ultramar, palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_provocacao_provocacao ON app_hist.relac_provocacao_provocacao USING btree (provocacao_mais_antiga, provocacao_mais_recente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_ultramar_ultramar ON app_hist.relac_ultramar_ultramar USING btree (ultramar_mais_antigo, ultramar_mais_recente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_tema_provocacao ON app_hist.relac_tema_provocacao USING btree (tema, provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS relac_secretario_conselho_mandado_pkey ON app_hist.relac_secretario_conselho_mandado USING btree (secretario_conselho, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_provocacao ON app_hist.provocacao USING btree (pk_provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_ultramar_provocacao ON app_hist.relac_ultramar_provocacao USING btree (provocacao, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_termo_provocacao ON app_hist.relac_termo_provocacao USING btree (provocacao, termo);

CREATE UNIQUE INDEX IF NOT EXISTS relac_comarca_ultramar_pk ON app_hist.relac_comarca_ultramar USING btree (comarca, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_mandado_resposta ON app_hist.relac_mandado_resposta USING btree (mandado, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pcitadas_consulta ON app_hist.relac_pcitadas_consulta USING btree (consulta, p_citadas);

CREATE UNIQUE INDEX IF NOT EXISTS relac_termo_mandado_pk ON app_hist.relac_termo_mandado USING btree (termo, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_freguesia ON app_hist.freguesia USING btree (pk_freguesia);

CREATE UNIQUE INDEX IF NOT EXISTS pk_conselheiros_consulta ON app_hist.conselheiros_consulta USING btree (pk_conselheiro);

CREATE UNIQUE INDEX IF NOT EXISTS relac_freguesia_provocacao_pk ON app_hist.relac_freguesia_provocacao USING btree (freguesia, provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS relac_secretario_conselho_consulta_pkey ON app_hist.relac_secretario_conselho_consulta USING btree (secretario_conselho, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_capitania_provocacao_pk ON app_hist.relac_capitania_provocacao USING btree (capitania, provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pcitadas_ultramar ON app_hist.relac_pcitadas_ultramar USING btree (ultramar, p_citadas);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_tema_ultramar ON app_hist.relac_tema_ultramar USING btree (tema, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_requerente_mandado ON app_hist.relac_requerente_mandado USING btree (mandado, requerente);

CREATE UNIQUE INDEX IF NOT EXISTS pk_oficio_agregador ON app_hist.oficio_agregador USING btree (pk_oficio_agregador);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_mandado_consulta ON app_hist.relac_mandado_consulta USING btree (mandado, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_capitania_mandado_pk ON app_hist.relac_capitania_mandado USING btree (capitania, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS tipologia_diplomatica_pkey ON app_hist.tipologia_diplomatica USING btree (pk_tipologia);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_conselheiros_consulta ON app_hist.relac_conselheiros_consulta USING btree (conselheiro, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_remetente_provocacao ON app_hist.relac_remetente_provocacao USING btree (provocacao, remetente);

CREATE UNIQUE INDEX IF NOT EXISTS relac_freguesia_ultramar_pk ON app_hist.relac_freguesia_ultramar USING btree (freguesia, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS secretario_conselho_resposta_pkey ON app_hist.secretario_conselho_resposta USING btree (pk_secretario_conselho);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_marcador_economico_ocupacao_pessoa ON app_hist.relac_marcador_economico_ocupacao_pessoa USING btree (marcador_economico_ocupacao, pessoa);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_termo_comarca ON app_hist.relac_termo_comarca USING btree (termo, comarca);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_requerente_consulta ON app_hist.relac_requerente_consulta USING btree (requerente, consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_freguesia_mandado_pk ON app_hist.relac_freguesia_mandado USING btree (freguesia, mandado);

CREATE UNIQUE INDEX IF NOT EXISTS pk_consulta ON app_hist.consulta USING btree (pk_consulta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_comarca_provocacao_pk ON app_hist.relac_comarca_provocacao USING btree (comarca, provocacao);

CREATE UNIQUE INDEX IF NOT EXISTS relac_termo_ultramar_pk ON app_hist.relac_termo_ultramar USING btree (termo, ultramar);

CREATE UNIQUE INDEX IF NOT EXISTS requerente_pkey ON app_hist.pessoa USING btree (pk_pessoa);

CREATE UNIQUE INDEX IF NOT EXISTS pk_palavra_chave ON app_hist.palavra_chave USING btree (pk_palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS unq_palavra_chave_palavra_chave ON app_hist.palavra_chave USING btree (palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_provocacao_resposta ON app_hist.relac_provocacao_resposta USING btree (provocacao, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_resposta_resposta ON app_hist.relac_resposta_resposta USING btree (resposta_mais_antiga, resposta_mais_recente);

CREATE UNIQUE INDEX IF NOT EXISTS termo_pkey ON app_hist.termo USING btree (pk_termo);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_consulta_resposta ON app_hist.relac_consulta_resposta USING btree (consulta, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS relac_freguesia_resposta_pk ON app_hist.relac_freguesia_resposta USING btree (freguesia, resposta);

CREATE UNIQUE INDEX IF NOT EXISTS pk_relac_pchave_mandado ON app_hist.relac_pchave_mandado USING btree (mandado, palavra_chave);

CREATE UNIQUE INDEX IF NOT EXISTS relac_capitania_resposta_pk ON app_hist.relac_capitania_resposta USING btree (capitania, resposta);



