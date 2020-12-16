drop table users;

create table users(
	user_id serial primary key,
	"name" text,
	user_name text not null,
	"password" text not null,
	"type" text,
	phone_number bigint
);

drop table account;

create table account(
	account_number serial primary key,
	account_name text,
	ammount bigint,
	active_account boolean,
	user_id int references users ("user_id") 
	
);

drop table transactions;

create table transactions(
	transaction_id serial primary key,
	transaction_comment text
);

drop table money_transfer;

create table money_transfer(
	transfer_id serial primary key,
	receiver_account_number int,
	ammount bigint,
	sender_account_number int references account ("account_number")
);

insert into users ("name", user_name, "password", "type", phone_number)
	values('Mohammad Hamza', 'mhamza', 'password', 'employee', 4031113215);




