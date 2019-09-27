-- noinspection SpellCheckingInspectionForFile

create table if not exists BANDS
(
    ID                       bigint primary key auto_increment,
    UID                      varchar_ignorecase(256) unique      not null,
    ALIAS                    varchar_ignorecase(256) default ''  not null,
    TITLE                    varchar(256)                        not null,
    SOURCE                   clob                    default ''  not null,
    CONTENT                  clob                    default ''  not null,
    LETTER                   char(1)                 default '#' not null,
    GENRE                    varchar(256)            default '',
    IMAGE_TYPE               varchar(256),
    IMAGE_CONTENT_LENGTH     bigint,
    IMAGE_WIDTH              integer,
    IMAGE_HEIGHT             integer,
    IMAGE_PATH               varchar(256),
    THUMBNAIL_TYPE           varchar(256),
    THUMBNAIL_CONTENT_LENGTH varchar(256),
    THUMBNAIL_WIDTH          integer,
    THUMBNAIL_HEIGHT         integer,
    THUMBNAIL_PATH           varchar(256)
);

create table if not exists ALBUMS
(
    ID                       bigint primary key auto_increment,
    UID                      varchar_ignorecase(256) unique     not null,
    ALIAS                    varchar_ignorecase(256) default '' not null,
    TITLE                    varchar(256)                       not null,
    SOURCE                   clob                    default '' not null,
    CONTENT                  clob                    default '' not null,
    YEAR                     integer                            not null,
    IMAGE_TYPE               varchar(256),
    IMAGE_CONTENT_LENGTH     bigint,
    IMAGE_WIDTH              integer,
    IMAGE_HEIGHT             integer,
    IMAGE_PATH               varchar(256),
    THUMBNAIL_TYPE           varchar(256),
    THUMBNAIL_CONTENT_LENGTH varchar(256),
    THUMBNAIL_WIDTH          integer,
    THUMBNAIL_HEIGHT         integer,
    THUMBNAIL_PATH           varchar(256),
    BAND_ID                  bigint
);

create table if not exists TRACKS
(
    ID                   bigint primary key auto_increment,
    UID                  varchar_ignorecase(256) unique     not null,
    ALIAS                varchar_ignorecase(256) default '' not null,
    TITLE                varchar(256)                       not null,
    SOURCE               clob                    default '' not null,
    CONTENT              clob                    default '' not null,
    TRACK_NUMBER         integer                            not null,
    AUDIO_TYPE           varchar(256),
    AUDIO_CONTENT_LENGTH varchar(256),
    AUDIO_LENGTH         varchar(256),
    AUDIO_BITRATE        integer,
    AUDIO_PATH           varchar(256),
    VIDEO_TYPE           varchar(256),
    VIDEO_CONTENT_LENGTH bigint,
    VIDEO_LENGTH         varchar(256),
    VIDEO_WIDTH          integer,
    VIDEO_HEIGHT         integer,
    VIDEO_PATH           varchar(256),
    ALBUM_ID             bigint
);

create table if not exists USERS
(
    USERNAME varchar_ignorecase(256) not null primary key,
    PASSWORD varchar_ignorecase(256) not null,
    ENABLED  boolean                 not null
);

create table if not exists AUTHORITIES
(
    USERNAME  varchar_ignorecase(256) not null,
    AUTHORITY varchar_ignorecase(256) not null,
    constraint FK_AUTHORITIES_USERS foreign key (USERNAME) references USERS (USERNAME)
);
create unique index if not exists IX_AUTH_USERNAME on AUTHORITIES (USERNAME, AUTHORITY);

create table if not exists PERSISTENT_LOGINS
(
    USERNAME  varchar_ignorecase(256) not null,
    SERIES    varchar_ignorecase(256) primary key,
    TOKEN     varchar_ignorecase(256) not null,
    LAST_USED timestamp               not null
);

create table if not exists SPRING_SESSION
(
    PRIMARY_ID            char(36) not null,
    SESSION_ID            char(36) not null,
    CREATION_TIME         bigint   not null,
    LAST_ACCESS_TIME      bigint   not null,
    MAX_INACTIVE_INTERVAL int      not null,
    EXPIRY_TIME           bigint   not null,
    PRINCIPAL_NAME        varchar(100),
    constraint SPRING_SESSION_PK primary key (PRIMARY_ID)
);

create unique index if not exists SPRING_SESSION_IX1 on SPRING_SESSION (SESSION_ID);
create index if not exists SPRING_SESSION_IX2 on SPRING_SESSION (EXPIRY_TIME);
create index if not exists SPRING_SESSION_IX3 on SPRING_SESSION (PRINCIPAL_NAME);

create table if not exists SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID char(36)      not null,
    ATTRIBUTE_NAME     varchar(200)  not null,
    ATTRIBUTE_BYTES    longvarbinary not null,
    constraint SPRING_SESSION_ATTRIBUTES_PK primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    constraint SPRING_SESSION_ATTRIBUTES_FK foreign key (SESSION_PRIMARY_ID) references SPRING_SESSION (PRIMARY_ID) on delete cascade
);
