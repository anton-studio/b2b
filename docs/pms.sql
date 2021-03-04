drop table if exists pms_attr;

drop table if exists pms_attr_attrgroup_relation;

drop table if exists pms_attr_group;

drop table if exists pms_brand;

drop table if exists pms_category;

drop table if exists pms_sku_price_info;

drop table if exists pms_product_files

drop table if exists pms_category_brand_relation;

drop table if exists pms_comment_replay;

drop table if exists pms_product_attr_value;

drop table if exists pms_sku_images;

drop table if exists pms_sku_info;

drop table if exists pms_sku_sale_attr_value;

drop table if exists pms_spu_comment;

drop table if exists pms_spu_images;

drop table if exists pms_spu_info;

drop table if exists pms_spu_info_desc;

/*==============================================================*/
/* Table: pms_category                                          */
/*==============================================================*/
create table pms_category
(
    id               bigint not null auto_increment comment '分类id',
    name                 char(50) comment '分类名称',
    parent_cid           bigint comment '父分类id',
    cat_level            int comment '层级',
    show_status          tinyint comment '是否显示[0-不显示，1显示]',
    sort                 int comment '排序',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

/*==============================================================*/
/* Table: pms_spu_info                                          */
/*==============================================================*/
create table pms_spu_info
(
    id                   bigint not null auto_increment comment '商品id',
    spu_name             varchar(200) comment '商品名称',
    spu_description      varchar(1000) comment '商品描述',
    catalog_id           bigint comment '所属分类id',
    attr_group_id        bigint comment '',
    publish_status       tinyint comment '上架状态[0 - 下架，1 - 上架]',
    img_url              varchar(255) comment 'SPU 默认图片地址',
    price                varchar(255) comment '价格区间, 真实价格根据采购数量决定',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

/*==============================================================*/
/* Table: pms_sku_info                                          */
/*==============================================================*/
create table pms_sku_info
(
    id               bigint not null auto_increment comment 'skuId',
    spu_id               bigint comment 'spuId',
    sku_name             varchar(255) comment 'sku名称',
    sku_desc             varchar(2000) comment 'sku介绍描述',
    catalog_id           bigint comment '所属分类id-冗余设计',
    sku_default_img      varchar(255) comment '默认图片',
    sku_title            varchar(255) comment '标题',
    price                varchar(255) comment '价格区间, 真实价格根据采购数量决定',
    sale_count           bigint comment '销量',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

/*==============================================================*/
/* Table: pms_sku_price_ifo                                          */
/*==============================================================*/
create table pms_sku_price_info
(
    id             bigint not null auto_increment comment 'priceId',
    sku_id               bigint not null comment 'skuId',
    start_count          bigint comment '起始数量',
    end_count            bigint comment 'end count',
    price                bigint comment '真实价格',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

/*==============================================================*/
/* Table: pms_sku_files                                         */
/*==============================================================*/
create table pms_product_files
(
    id      bigint not null auto_increment comment 'skuFileId',
    file_id              bigint comment 'fileId',
    sku_id               bigint not null comment 'skuId',
    spu_id               bigint not null comment 'spuId',
    file_name            bigint comment 'file name',
    file_url             bigint comment 'file url',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

/*==============================================================*/
/* Table: pms_attr                                              */
/*==============================================================*/
create table pms_attr
(
    id              bigint not null auto_increment comment '属性id',
    attr_name            char(30) comment '属性名',
    search_type          tinyint comment '是否需要检索[0-不需要，1-需要]',
    icon                 varchar(255) comment '属性图标',
    value_select         char(255) comment '可选值列表[用逗号分隔] - 冗余设计',
    attr_type            tinyint comment '属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]',
    enable               bigint comment '启用状态[0 - 禁用，1 - 启用]',
    catalog_id           bigint comment '所属分类',
    attr_group_id        bigint comment '',
    show_desc            tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

alter table pms_attr comment '商品属性';

/*==============================================================*/
/* Table: pms_product_attr_value                                */
/*==============================================================*/
create table pms_product_attr_value
(
    id                   bigint not null auto_increment comment 'id',
    spu_id               bigint comment '商品id',
    attr_id              bigint comment '属性id',
    attr_name            varchar(200) comment '属性名',
    attr_value           varchar(200) comment '属性值',
    attr_sort            int comment '顺序',
    quick_show           tinyint comment '快速展示【是否展示在介绍上；0-否 1-是】',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);

alter table pms_product_attr_value comment 'spu属性值';

/*==============================================================*/
/* Table: pms_sku_sale_attr_value                               */
/*==============================================================*/
create table pms_sku_sale_attr_value
(
    id                   bigint not null auto_increment comment 'id',
    sku_id               bigint comment 'sku_id',
    attr_id              bigint comment 'attr_id',
    attr_name            varchar(200) comment '销售属性名',
    attr_value           varchar(200) comment '销售属性值',
    attr_sort            int comment '顺序',
    primary key (id)
);

alter table pms_sku_sale_attr_value comment 'sku销售属性&值';

/*==============================================================*/
/* Table: pms_attr_group                                        */
/*==============================================================*/
create table pms_attr_group
(
    id        bigint not null auto_increment comment '分组id',
    attr_group_name      char(20) comment '组名',
    sort                 int comment '排序',
    description          varchar(255) comment '描述',
    icon                 varchar(255) comment '组图标',
    create_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    update_time datetime(3)      NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    delete_time datetime(3)               DEFAULT NULL,
    primary key (id)
);