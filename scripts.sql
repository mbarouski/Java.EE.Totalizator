-- Real data model is lost. This one is created to just start the application.

create table eventcategory (
category_id int unsigned auto_increment primary key,
category_name varchar(256));

create table event (
event_id int unsigned auto_increment primary key,
league_id int unsigned,
event_name text,
event_start_date date,
rate_types text,
live_translation_reference text,
event_status enum('POSTED', 'FINISHED'));
alter table event alter event_status set default 'POSTED';

create table league (
league_id int unsigned auto_increment primary key,
league_name varchar(256),
event_category_id int unsigned);

create table user (
user_id int unsigned auto_increment primary key,
login text,
pass_hash text,
email text,
balance decimal,
role text,
banned bool);
alter table user modify column role enum('USER','ADMINISTRATOR','MODERATOR') not null;
alter table user alter role set default 'USER';
alter table user alter balance set default 0;

create table rate(
rate_id int unsigned auto_increment primary key,
user_id int unsigned,
event_id int unsigned,
money decimal,
win_money decimal,
rate_type text,
date date,
event_member1_id int unsigned,
event_member2_id int unsigned,
event_member1_score int unsigned,
event_member2_score int unsigned
);

create table moneyoperation(
operation_id int unsigned auto_increment primary key,
user_id int unsigned,
operation_type text,
amount decimal,
card_number text,
validity_date text,
date date
);

create table eventmember(
member_id int unsigned auto_increment primary key,
member_name text,
league_id int unsigned
);

create table event_m2m_eventmember(
event_id int unsigned,
member_id int unsigned,
primary key(event_id, member_id)
);

create table eventresult(
event_id int unsigned primary key,
winner_id int unsigned,
loser_id int unsigned,
winner_score int unsigned,
loser_score int unsigned
);
