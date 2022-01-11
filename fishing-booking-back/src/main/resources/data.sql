--ADDRESS--
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (1, 'Novi Sad', 'Serbia', 0, 0, 'Kralja Petra 1');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (2, 'Novi Sad', 'Serbia', 45.2889, 19.7245, 'Kralja Petra 2');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (3, 'Novi Sad', 'Serbia', 45.6889, 19.5245, 'Kralja Petra 4');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (4, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 13');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (5, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 13');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER) values (6, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 13');

--USERS--
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('ADMIN',1,'admin','admin','admin','pass','21323',1,0,1);
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('BOAT_OWNER',2,'jovanjovanic@gmail.com','Jovan','Jovanić','pass','+43365464',2,null,2);
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('FISHING_INSTRUCTOR',3,'peraperic4200@gmail.com','Pera','Perić','pass','+4343365464',3,null,3);
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,IS_ENABLED,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('CLIENT',4,'nikola1@gmail.com','Pera',true,'Perić','pass','+4343365464',0,null,4);
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,IS_ENABLED,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('CLIENT',5,'nikola2@gmail.com','Pera',false,'Perić','pass','+4343365464',0,null,5);
INSERT INTO users (D_TYPE,ID,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,PHONE_NUMBER,ROLE,FIRST_LOGIN,ADDRESS_ID) VALUES ('HOME_OWNER',6,'home@owner.com','Zdravko','Cholich','pass','+3811369666',4,null,6);





--FISHING ADVENTURES
INSERT INTO fishing_adventure(id,available_equipment,biography,cancellation_fee,description,hourly_price,max_people,name,rules_of_conduct,address_id,instructor_id)
VALUES (1,'Everything you will need will be provided to you', 'I''m a seasoned veteran when it comes to angling', 15, 'Tons of fun for the whole family', 450, 5, 'Amazon Fishing', 'You must stay with me, the instructor, the entire duration of the trip', 1,3);

INSERT INTO fishing_adventure(id,available_equipment,biography,cancellation_fee,description,hourly_price,max_people,name,rules_of_conduct,address_id,instructor_id)
VALUES (2,'Everything you will need will be provided to you', 'I''m a seasoned veteran when it comes to angling', 15, 'Tons of fun for the whole family', 450, 5, 'Canyon Rock', 'You must stay with me, the instructor, the entire duration of the trip', 3,3);

--RESERVATIONS
INSERT INTO ADVENTURE_RESERVATION (id,duration_in_hours,mark,start_date,price,approved,adventure_id)
VALUES(1,2,3.4,'2012-09-17 18:47:52.69',200,1,1)
