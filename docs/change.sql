alter table cms_client_info add column address text;

alter table pms_spu_info add column printable_attr_ids text;

alter table pms_sku_info add column size text;
alter table pms_sku_info add column weight bigint comment 'kg';
alter table pms_sku_info add column quantity_per_carton bigint;
alter table pms_sku_info add column net_weight_per_carton bigint;
alter table pms_sku_info add column gross_weight_per_carton bigint;
alter table pms_sku_info add column carton_measurement text;
alter table pms_sku_info add column carton_size text;

alter table cms_contract add column delivery_address text comment 'if this address is null, use client address for contract printing';
alter table cms_contract add column shipping_cost bigint comment 'usd';
alter table cms_contract add column transaction_fee bigint;
alter table cms_contract add column terms_of_sale text;
alter table cms_contract add column insurance_cost text;
alter table cms_contract add column additional_cost text;

alter table ims_contract_product add column price bigint comment 'contract price(real unit price)';
alter table ims_contract_product add column special_request text comment 'write your own';