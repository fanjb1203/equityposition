DROP table if exists equityPosition;

create table equityPosition
(
    transactionID INTEGER auto_increment comment '主键',
    tradeID INTEGER  not null comment 'TradeID',
    version INTEGER  not null default 1,
    securityCode INTEGER not null,
    quantity INTEGER not null,
    action INTEGER not null,
    primary key (transactionID)
);