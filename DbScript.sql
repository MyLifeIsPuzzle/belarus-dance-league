SET SEARCH_PATH TO dance_league;

create sequence dance_league.rating_id_seq;

create sequence dance_league.account_id_seq;

create sequence dance_league.club_id_seq;

create sequence dance_league.dance_class_id_seq;

create sequence dance_league.dance_group_id_seq;

create sequence dance_league.dancing_hall_id_seq;

create sequence dance_league.human_base_info_id_seq;

create sequence dance_league.request_id_seq;

create sequence dance_league.role_id_seq;

create table dance_league.dancer
(
  date_of_birth date   not null,
  age_category  varchar(32),
  league        varchar(32),
  phone_number  varchar(64),
  dancer_id     bigint not null,
  constraint dancer_base_info_id_pk
  primary key (dancer_id)
);

create unique index dancer_base_info_id_uindex
  on dance_league.dancer (dancer_id);

create table dance_league.rating
(
  id        bigserial   not null,
  dancer_id bigint      not null,
  value     integer     not null,
  style     varchar(32) not null,
  constraint rating_pkey
  primary key (id)
);

create unique index rating_dancer_id_style_uindex
  on dance_league.rating (dancer_id, style);

create table dance_league.club
(
  id     bigserial not null,
  active boolean,
  info   varchar(255),
  name   varchar(255),
  constraint club_pkey
  primary key (id),
  constraint uk_obh7q4yqh38kicj65tm0wd4t4
  unique (name)
);

create table dance_league.dance_group
(
  id      bigserial not null,
  active  boolean,
  name    varchar(255),
  club_id bigint,
  constraint dance_group_pkey
  primary key (id),
  constraint fk8v4402x5wcgr14w0bskwntkg1
  foreign key (club_id) references dance_league.club
);

create table dance_league.dancer_dance_group
(
  active         boolean,
  dance_group_id bigint not null,
  dancer_id      bigint not null,
  constraint dancer_dance_group_pkey
  primary key (dance_group_id, dancer_id),
  constraint fk4som1lhrsc9suedftbpio8dkb
  foreign key (dance_group_id) references dance_league.dance_group,
  constraint fkc0bwoalxrhh9nm7epw3obi77s
  foreign key (dancer_id) references dance_league.human_base_info (id)
);

create table dance_league.dancing_hall
(
  id     bigserial    not null,
  city   varchar(255) not null,
  hall   varchar(255) not null,
  street varchar(255) not null,
  active boolean      not null,
  name   varchar(255) not null,
  constraint dancing_hall_pkey
  primary key (id),
  constraint uk_pmds7bhjnpegebfk7ys1lfr9i
  unique (name)
);

create table dance_league.human_base_info
(
  id          bigserial    not null,
  active      boolean      not null,
  name        varchar(255) not null,
  second_name varchar(255) not null,
  constraint human_base_info_pkey
  primary key (id)
);

create table dance_league.request
(
  id            bigserial not null,
  date_of_birth date,
  active        boolean,
  name          varchar(255),
  phone_number  varchar(255),
  second_name   varchar(255),
  group_id      bigint,
  constraint request_pkey
  primary key (id),
  constraint fklb9p7dr09e0v2g2pcaxebpva9
  foreign key (group_id) references dance_league.dance_group
);

create table dance_league.role
(
  id   bigserial    not null,
  name varchar(255) not null,
  constraint role_pkey
  primary key (id),
  constraint uk_8sewwnpamngi6b1dwaa88askk
  unique (name)
);

create table dance_league.account
(
  id       bigserial not null,
  email    varchar(255),
  active   boolean,
  password varchar(255),
  role_id  bigint,
  coach_id bigint,
  constraint account_pkey
  primary key (id),
  constraint uk_q0uja26qgu1atulenwup9rxyr
  unique (email),
  constraint fkd4vb66o896tay3yy52oqxr9w0
  foreign key (role_id) references dance_league.role,
  constraint account_human_base_info_id_fk
  foreign key (coach_id) references dance_league.human_base_info
);

create table dance_league.coach
(
  info       varchar(255),
  coach_id   bigint not null,
  account_id bigint,
  constraint coach_pkey
  primary key (coach_id),
  constraint fkapihtc33ep9esmgq7poy27qec
  foreign key (coach_id) references dance_league.human_base_info,
  constraint fkneh7gfkfrubd0cyvw9wcwqlws
  foreign key (account_id) references dance_league.account
);

create table dance_league.club_coach
(
  active   boolean,
  club_id  bigint not null,
  coach_id bigint not null,
  constraint club_coach_pkey
  primary key (club_id, coach_id),
  constraint fkmf4exy4bjcxgmdi7l10dctwsq
  foreign key (club_id) references dance_league.club,
  constraint fkqhstvm5wgv2djw73g548qyrw
  foreign key (coach_id) references dance_league.coach
);

create table dance_league.dance_class
(
  id              bigserial not null,
  day_of_week     varchar(255),
  active          boolean,
  style           varchar(255),
  time            time,
  coach_id        bigint,
  dance_group_id  bigint    not null,
  dancing_hall_id bigint,
  constraint dance_class_pkey
  primary key (id),
  constraint fk9ohboks77ixtr1hra3132skgu
  foreign key (coach_id) references dance_league.coach,
  constraint fk9qq9uxjsi0fycwdsivi570yog
  foreign key (dance_group_id) references dance_league.dance_group,
  constraint fkn6ydfu1ca379w04mrfqb1t1ia
  foreign key (dancing_hall_id) references dance_league.dancing_hall
);

