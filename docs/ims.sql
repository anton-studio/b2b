# different invoice management system
create table ims_contract
(
    id                   bigint not null auto_increment comment 'id',
    contract_time datetime(3),
    delivery_time datetime(3),
    client_id bigint,
    total_amount bigint,
    actual_delivery_fee bigint,
    other_fee bigint,
    payment_method text,
    notes text,
    payment_status text,
    raw_cost bigint,
    review_status text,
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) comment 'if 30 days not update, put to public sea, update means 1, change basic info, 2, create client follow log',
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

create table ims_contract_product
(
    id                   bigint not null auto_increment comment 'id',
    contract_id bigint,
    spu_id bigint,
    sku_id bigint,
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) comment 'if 30 days not update, put to public sea, update means 1, change basic info, 2, create client follow log',
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);