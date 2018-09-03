/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/13 14:07:01                           */
/*==============================================================*/


drop table if exists tb_airLine_info;

drop table if exists tb_city_info;

drop table if exists tb_customer_info;

drop table if exists tb_customer_type;

drop table if exists tb_line_plane;

drop table if exists tb_login_log;

drop table if exists tb_operation_log;

drop table if exists tb_permi_roler;

drop table if exists tb_permission;

drop table if exists tb_plane_info;

drop table if exists tb_roler_info;

drop table if exists tb_roler_user;

drop table if exists tb_space_rank;

drop table if exists tb_ticket_book;

drop table if exists tb_user_info;

/*==============================================================*/
/* Table: tb_airLine_info                                       */
/*==============================================================*/
create table tb_airLine_info
(
   airLineId            int not null,
   spaceId              int,
   airLineNumber        varchar(50),
   deparCity            varchar(50),
   arrivalCity          varchar(50),
   airLineTime          varchar(50),
   outTime              varchar(50),
   inTime               varchar(50),
   tCabinPrice          varchar(50),
   bCabinPrice          varchar(50),
   fCabinPrice          varchar(50),
   sendState            varchar(50),
   remark               varchar(50),
   primary key (airLineId)
);

/*==============================================================*/
/* Table: tb_city_info                                          */
/*==============================================================*/
create table tb_city_info
(
   cityId               int not null,
   cityNumber           varchar(50),
   cityName             varchar(50),
   primary key (cityId)
);

/*==============================================================*/
/* Table: tb_customer_info                                      */
/*==============================================================*/
create table tb_customer_info
(
   custId               int not null,
   cusTypeId            int,
   custNumber           varchar(50),
   custName             varchar(50),
   custSex              varchar(50),
   custAge              varchar(50),
   custAdress           varchar(50),
   custEmail            varchar(50),
   custTel              varchar(50),
   custHirbeat          varchar(50),
   custIdCard           varchar(50),
   remark               varchar(50),
   primary key (custId)
);

/*==============================================================*/
/* Table: tb_customer_type                                      */
/*==============================================================*/
create table tb_customer_type
(
   cusTypeId            int not null,
   cusTypeNumber        varchar(50),
   cusTypeName          varchar(50),
   DiscPersent          varchar(50),
   remark               varchar(50),
   primary key (cusTypeId)
);

/*==============================================================*/
/* Table: tb_line_plane                                         */
/*==============================================================*/
create table tb_line_plane
(
   planeId              int not null,
   airLineId            int not null,
   primary key (planeId, airLineId)
);

/*==============================================================*/
/* Table: tb_login_log                                          */
/*==============================================================*/
create table tb_login_log
(
   loginId              int not null,
   loginName            varchar(50),
   loginIp              varchar(50),
   longinTime           varchar(50),
   logoutTime           varchar(50),
   remark               varchar(50),
   primary key (loginId)
);

/*==============================================================*/
/* Table: tb_operation_log                                      */
/*==============================================================*/
create table tb_operation_log
(
   operaId              int not null,
   operaUserName        varchar(50),
   operaKind            varchar(50),
   operaTime            varchar(50),
   remark               varchar(50),
   primary key (operaId)
);

/*==============================================================*/
/* Table: tb_permi_roler                                        */
/*==============================================================*/
create table tb_permi_roler
(
   permiId              int not null,
   rolerId              int not null,
   primary key (permiId, rolerId)
);

/*==============================================================*/
/* Table: tb_permission                                         */
/*==============================================================*/
create table tb_permission
(
   permiId              int not null,
   permiNumber          varchar(50),
   permiName            varchar(50),
   permiDesc            varchar(50),
   remark               varchar(50),
   primary key (permiId)
);

/*==============================================================*/
/* Table: tb_plane_info                                         */
/*==============================================================*/
create table tb_plane_info
(
   planeId              int not null,
   planeNumber          varchar(50),
   planeSince           varchar(50),
   buyTime              varchar(50),
   serviceTime          varchar(50),
   tSeats               varchar(50),
   bSeats               varchar(50),
   fSeats               varchar(50),
   remark               varchar(50),
   primary key (planeId)
);

/*==============================================================*/
/* Table: tb_roler_info                                         */
/*==============================================================*/
create table tb_roler_info
(
   rolerId              int not null,
   rolerNumber          varchar(50),
   rolerName            varchar(50),
   rolerDesc            varchar(50),
   remark               varchar(50),
   primary key (rolerId)
);

/*==============================================================*/
/* Table: tb_roler_user                                         */
/*==============================================================*/
create table tb_roler_user
(
   rolerId              int not null,
   userId               int not null,
   primary key (rolerId, userId)
);

/*==============================================================*/
/* Table: tb_space_rank                                         */
/*==============================================================*/
create table tb_space_rank
(
   spaceId              int not null,
   airLineId            int,
   spaceNumber          varchar(50),
   spaceName            varchar(50),
   isGift               varchar(50),
   isNewspaper          varchar(50),
   isDrink              varchar(50),
   isMovie              varchar(50),
   isChange             varchar(50),
   isReturnTicket       varchar(50),
   isDiscount           varchar(50),
   remark               varchar(50),
   primary key (spaceId)
);

/*==============================================================*/
/* Table: tb_ticket_book                                        */
/*==============================================================*/
create table tb_ticket_book
(
   bookId               int not null,
   custId               int,
   bookNumber           varchar(50),
   bookPrice            varchar(50),
   bookSum              varchar(50),
   bookTime             varchar(50),
   flightTime           varchar(50),
   outTime              varchar(50),
   inTime               varchar(50),
   remark               varchar(50),
   primary key (bookId)
);

/*==============================================================*/
/* Table: tb_user_info                                          */
/*==============================================================*/
create table tb_user_info
(
   userId               int not null,
   userNumber           varchar(50),
   userName             varchar(50),
   userPass				varchar(50),
   userSex              varchar(50),
   userAge              varchar(50),
   userEmail            varchar(50),
   userTel              varchar(50),
   remark               varchar(50),
   primary key (userId)
);

alter table tb_airLine_info add constraint FK_Relationship_5 foreign key (spaceId)
      references tb_space_rank (spaceId) on delete restrict on update restrict;

alter table tb_customer_info add constraint FK_Relationship_1 foreign key (cusTypeId)
      references tb_customer_type (cusTypeId) on delete restrict on update restrict;

alter table tb_line_plane add constraint FK_tb_line_plane foreign key (planeId)
      references tb_plane_info (planeId) on delete restrict on update restrict;

alter table tb_line_plane add constraint FK_tb_line_plane2 foreign key (airLineId)
      references tb_airLine_info (airLineId) on delete restrict on update restrict;

alter table tb_permi_roler add constraint FK_tb_permi_roler foreign key (permiId)
      references tb_permission (permiId) on delete restrict on update restrict;

alter table tb_permi_roler add constraint FK_tb_permi_roler2 foreign key (rolerId)
      references tb_roler_info (rolerId) on delete restrict on update restrict;

alter table tb_roler_user add constraint FK_tb_roler_user foreign key (rolerId)
      references tb_roler_info (rolerId) on delete restrict on update restrict;

alter table tb_roler_user add constraint FK_tb_roler_user2 foreign key (userId)
      references tb_user_info (userId) on delete restrict on update restrict;

alter table tb_space_rank add constraint FK_Relationship_4 foreign key (airLineId)
      references tb_airLine_info (airLineId) on delete restrict on update restrict;

alter table tb_ticket_book add constraint FK_Relationship_6 foreign key (custId)
      references tb_customer_info (custId) on delete restrict on update restrict;

