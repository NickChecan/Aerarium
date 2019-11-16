/*
 * Initial Setup - Standard Schema
 * Initial DML Script creation for Master Data tables
 */

-- DML script for standard values of the banks list
INSERT INTO bank (ba_name) VALUES ('Banco do Brasil');
INSERT INTO bank (ba_name) VALUES ('Bradesco');
INSERT INTO bank (ba_name) VALUES ('Caixa');
INSERT INTO bank (ba_name) VALUES ('Itaú');
INSERT INTO bank (ba_name) VALUES ('Santander');
INSERT INTO bank (ba_name) VALUES ('Outros');

-- DML script for standard values of the cost types list
INSERT INTO cost_type (ct_name) VALUES ('Investimentos');
INSERT INTO cost_type (ct_name) VALUES ('Fixos');
INSERT INTO cost_type (ct_name) VALUES ('Variáveis');
INSERT INTO cost_type (ct_name) VALUES ('Extras');

-- DML script for standard values of the specifications list
INSERT INTO specification (sp_name) VALUES ('ACI');
INSERT INTO specification (sp_name) VALUES ('Água');
INSERT INTO specification (sp_name) VALUES ('Aluguel');
INSERT INTO specification (sp_name) VALUES ('Cartão de Crédito');
INSERT INTO specification (sp_name) VALUES ('Celular');
INSERT INTO specification (sp_name) VALUES ('Consultoria');
INSERT INTO specification (sp_name) VALUES ('Contabilidade');
INSERT INTO specification (sp_name) VALUES ('Convênio Odontológico');
INSERT INTO specification (sp_name) VALUES ('Consórcio');
INSERT INTO specification (sp_name) VALUES ('DAS Parcelamento');
INSERT INTO specification (sp_name) VALUES ('Despesas Administrativas');
INSERT INTO specification (sp_name) VALUES ('Despesas Gerais');
INSERT INTO specification (sp_name) VALUES ('Empréstimos - Caixa');
INSERT INTO specification (sp_name) VALUES ('Empréstimos - Itaú');
INSERT INTO specification (sp_name) VALUES ('Empréstimos - HSBC');
INSERT INTO specification (sp_name) VALUES ('Financiamento de Veículos');
INSERT INTO specification (sp_name) VALUES ('Férias');
INSERT INTO specification (sp_name) VALUES ('FGTS');
INSERT INTO specification (sp_name) VALUES ('Folha de Pagamento');
INSERT INTO specification (sp_name) VALUES ('Fundo Fixo');
INSERT INTO specification (sp_name) VALUES ('GPS');
INSERT INTO specification (sp_name) VALUES ('Informática');
INSERT INTO specification (sp_name) VALUES ('Internet');
INSERT INTO specification (sp_name) VALUES ('IPVA');
INSERT INTO specification (sp_name) VALUES ('IPTU');
INSERT INTO specification (sp_name) VALUES ('ISS');
INSERT INTO specification (sp_name) VALUES ('Limpeza / Manutenção');
INSERT INTO specification (sp_name) VALUES ('Luz');
INSERT INTO specification (sp_name) VALUES ('Marketing / Publicidade');
INSERT INTO specification (sp_name) VALUES ('Material de Escritório');
INSERT INTO specification (sp_name) VALUES ('NEXTEL');
INSERT INTO specification (sp_name) VALUES ('Plano de Saúde');
INSERT INTO specification (sp_name) VALUES ('Plano Funerário');
INSERT INTO specification (sp_name) VALUES ('Recrutamento');
INSERT INTO specification (sp_name) VALUES ('Reforma');
INSERT INTO specification (sp_name) VALUES ('Segurança');
INSERT INTO specification (sp_name) VALUES ('Seguro');
INSERT INTO specification (sp_name) VALUES ('Seguro Obrigatório - Veículo');
INSERT INTO specification (sp_name) VALUES ('Seguro de Vida');
INSERT INTO specification (sp_name) VALUES ('Sindicato');
INSERT INTO specification (sp_name) VALUES ('Taxa de Licenciamento');
INSERT INTO specification (sp_name) VALUES ('Taxa de Licença');
INSERT INTO specification (sp_name) VALUES ('Telefone');
INSERT INTO specification (sp_name) VALUES ('Vale Transporte');

-- DML script for standard values of the payment types list
INSERT INTO payment_type (pt_name) VALUES ('Boleto');
INSERT INTO payment_type (pt_name) VALUES ('Carnê');
INSERT INTO payment_type (pt_name) VALUES ('Cartão de Crédito');
INSERT INTO payment_type (pt_name) VALUES ('Cartão de Débito');
INSERT INTO payment_type (pt_name) VALUES ('Cheque');
INSERT INTO payment_type (pt_name) VALUES ('Depósito');
INSERT INTO payment_type (pt_name) VALUES ('Dinheiro');
INSERT INTO payment_type (pt_name) VALUES ('Transferência');

-- DML script for standard values of the installments list
INSERT INTO installment (in_name) VALUES ('A prazo');
INSERT INTO installment (in_name) VALUES ('À vista');
INSERT INTO installment (in_name) VALUES ('30 dias');
INSERT INTO installment (in_name) VALUES ('60 dias');
INSERT INTO installment (in_name) VALUES ('90 dias');
INSERT INTO installment (in_name) VALUES ('1 parcela');
INSERT INTO installment (in_name) VALUES ('2 parcela');
INSERT INTO installment (in_name) VALUES ('3 parcela');
INSERT INTO installment (in_name) VALUES ('4 parcela');
INSERT INTO installment (in_name) VALUES ('5 parcela');
INSERT INTO installment (in_name) VALUES ('6 parcela');
INSERT INTO installment (in_name) VALUES ('7 parcela');
INSERT INTO installment (in_name) VALUES ('8 parcela');
INSERT INTO installment (in_name) VALUES ('9 parcela');
INSERT INTO installment (in_name) VALUES ('10 parcela');
INSERT INTO installment (in_name) VALUES ('11 parcela');
INSERT INTO installment (in_name) VALUES ('12 parcela');

-- DML script for standard values of the bill statuses list
INSERT INTO bill_status (bs_name) VALUES ('A vencer');
INSERT INTO bill_status (bs_name) VALUES ('Antecipado');
INSERT INTO bill_status (bs_name) VALUES ('Liquidado');
INSERT INTO bill_status (bs_name) VALUES ('Vencido');

-- DML script for the main company service provider
--INSERT INTO company (co_name) VALUES ('Aerarium');

-- DML script to set standard roles for the application users
INSERT INTO application_role (ro_name) VALUES ('ROLE_ADMIN');
INSERT INTO application_role (ro_name) VALUES ('ROLE_USER');