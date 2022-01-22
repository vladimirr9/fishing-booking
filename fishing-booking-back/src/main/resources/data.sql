insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (1, 'Novi Sad', 'Serbia', 0, 0, 'Kralja Petra 1');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (2, 'Novi Sad', 'Serbia', 45.2889, 19.7245, 'Kralja Petra 2');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (3, 'Novi Sad', 'Serbia', 45.6889, 19.5245, 'Kralja Petra 4');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (4, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 11');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (5, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 12');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (6, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 13');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (7, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 15');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (8, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 16');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (9, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 22');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (10, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 33');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (11, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 63');
insert into ADDRESS (ID, CITY, COUNTRY, LATITUDE, LONGITUDE, STREET_AND_NUMBER)
values (12, 'Novi Sad', 'Serbia', 45.2889, 19.3245, 'Cara Lazara 113');

--USERS--
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN, ADDRESS_ID)
VALUES ('ADMIN', 1, 'admin', 'admin', 'admin', 'pass', '21323', 1, FALSE, 1);
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN, ADDRESS_ID)
VALUES ('BOAT_OWNER', 2, 'jovanjovanic@gmail.com', 'Jovan', 'Jovanic', 'pass', '+43365464', 2, null, 2);
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN, ADDRESS_ID)
VALUES ('FISHING_INSTRUCTOR', 3, 'peraperic4200@gmail.com', 'Pera', 'Peric', 'pass', '+4343365464', 3, null, 3);
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, IS_ENABLED, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN,
                   ADDRESS_ID)
VALUES ('CLIENT', 4, 'nikola1@gmail.com', 'Pera', true, 'Peric', 'pass', '+4343365464', 0, null, 4);
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, IS_ENABLED, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN,
                   ADDRESS_ID)
VALUES ('CLIENT', 5, 'nikola2@gmail.com', 'Pera', false, 'Peric', 'pass', '+4343365464', 0, null, 5);
INSERT INTO users (D_TYPE, ID, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, ROLE, FIRST_LOGIN, ADDRESS_ID)
VALUES ('HOME_OWNER', 6, 'home@owner.com', 'Zdravko', 'Cholich', 'pass', '+3811369666', 4, null, 6);


--HOLIDAY HOMES

INSERT INTO holiday_home(ID, ADDITIONAL_INFO, BEDS_PER_ROOM, DESCRIPTION, NAME, PRICE_PER_DAY, ROOMS_PER_HOME,
                         RULES_OF_CONDUCT, AVERAGE_MARK, ADDRESS_ID, OWNER_ID)
VALUES (1, 'addInfo1', 3, 'Dobra.', 'Jamure', 1000, 10, 'Nema drogiranja.', 0, 7, 6);
INSERT INTO holiday_home(ID, ADDITIONAL_INFO, BEDS_PER_ROOM, DESCRIPTION, NAME, PRICE_PER_DAY, ROOMS_PER_HOME,
                         RULES_OF_CONDUCT, AVERAGE_MARK, ADDRESS_ID, OWNER_ID)
VALUES (2, 'addInfo2', 4, 'Dobra jako.', 'Paxow sallash', 2000, 20, 'Nema kockanja.', 0, 8, 6);

--BOATS

INSERT INTO boat(ID, ADDITIONAL_INFO, CABIN, CANCELLATION_FEE_PERCENTAGE, CAPACITY, DESCRIPTION, ENGINE_NUMBER,
                 ENGINE_POWER, FISHFINDER, FISHING_EQUIPMENT, GPS, LENGTH, MAX_SPEED, NAME, PRICE_PER_DAY, RADAR,
                 RULES_OF_CONDUCT, TYPE, VHF, AVERAGE_MARK, ADDRESS_ID, OWNER_ID)
VALUES (1, 'addInfo3', true, 0, 5, 'Dobar.', 1, 2, true, 'Ima svega.', false, 12, 100, 'Slice of life', 10, false,
        'Nema skakanja u vodu.', 'Gliser', false, 0, 9, 2);
INSERT INTO boat(ID, ADDITIONAL_INFO, CABIN, CANCELLATION_FEE_PERCENTAGE, CAPACITY, DESCRIPTION, ENGINE_NUMBER,
                 ENGINE_POWER, FISHFINDER, FISHING_EQUIPMENT, GPS, LENGTH, MAX_SPEED, NAME, PRICE_PER_DAY, RADAR,
                 RULES_OF_CONDUCT, TYPE, VHF, AVERAGE_MARK, ADDRESS_ID, OWNER_ID)
VALUES (2, 'addInfo4', false, 10, 5, 'Dobar veoma.', 10, 20, false, 'Nema ni volan.', true, 120, 1000, 'Puchina', 100,
        true, 'Nema spavanja u brodu.', 'Kruzer', true, 0, 10, 2);

--FISHING ADVENTURES
INSERT INTO fishing_adventure(id, available_equipment, biography, cancellation_fee, description, hourly_price,
                              max_people, name, rules_of_conduct, average_mark, address_id, instructor_id)
VALUES (1, 'Everything you will need will be provided to you', 'I''m a seasoned veteran when it comes to angling', 15,
        'Tons of fun for the whole family', 450, 5, 'Amazon Fishing',
        'You must stay with me, the instructor, the entire duration of the trip', 0, 11, 3);

INSERT INTO fishing_adventure(id, available_equipment, biography, cancellation_fee, description, hourly_price,
                              max_people, name, rules_of_conduct, average_mark, address_id, instructor_id)
VALUES (2, 'Everything you will need will be provided to you', 'I''m a seasoned veteran when it comes to angling', 15,
        'Tons of fun for the whole family', 450, 5, 'Canyon Rock',
        'You must stay with me, the instructor, the entire duration of the trip', 0, 12, 3);

--RESERVATIONS
INSERT INTO ADVENTURE_RESERVATION (id, start_date, end_date, price, approved, client_id, adventure_id, SERVICE_FEE)
VALUES (1, '2022-01-17 18:47:52.69', '2022-01-17 20:52:52.69', 200, TRUE, 4, 1, 0.15);
INSERT INTO ADVENTURE_RESERVATION (id, start_date, end_date, price, approved, client_id, adventure_id, SERVICE_FEE)
VALUES (2, '2022-01-21 18:47:52.69', '2022-01-21 20:52:52.69', 200, FALSE, 4, 1, 0.15);

INSERT INTO HOLIDAY_HOME_RESERVATION (id, start_date, end_date, price, approved, client_id, holidayhome_id, SERVICE_FEE)
VALUES (3, '2022-01-17 18:47:52.69', '2022-01-18 20:52:52.69', 200, TRUE, 4, 1, 0.15);
INSERT INTO HOLIDAY_HOME_RESERVATION (id, start_date, end_date, price, approved, client_id, holidayhome_id, SERVICE_FEE)
VALUES (4, '2022-01-21 18:47:52.69', '2022-01-23 20:52:52.69', 200, FALSE, 4, 1, 0.15);

INSERT INTO BOAT_RESERVATION (id, start_date, end_date, price, approved, client_id, boat_id, SERVICE_FEE)
VALUES (5, '2022-01-17 18:47:52.69', '2022-01-18 20:52:52.69', 200, TRUE, 4, 1, 0.15);
INSERT INTO BOAT_RESERVATION (id, start_date, end_date, price, approved, client_id, boat_id, SERVICE_FEE)
VALUES (6, '2022-01-21 18:47:52.69', '2022-01-23 20:52:52.69', 200, FALSE, 4, 1, 0.15);

ALTER SEQUENCE MY_SEQ RESTART WITH 7;


INSERT INTO REVIEW(ID, APPROVED, COMMENT, MARK, RESERVATION_ID)
VALUES (1, FALSE, 'good', 4, 1);

INSERT INTO COMPLAINT(ID, CONTENT, RESERVATION_ID)
VALUES (1, 'Wasnt too good', 1);


--PERIODS
INSERT INTO AVAILABLE_PERIOD(id, from_time, to_time)
VALUES (1, '2022-02-05 18:47:52.69', '2022-02-08 20:52:52.69');
INSERT INTO AVAILABLE_PERIOD(id, from_time, to_time)
VALUES (2, '2022-02-05 18:47:52.69', '2022-02-08 20:52:52.69');
INSERT INTO AVAILABLE_PERIOD(id, from_time, to_time)
VALUES (3, '2022-02-05 18:47:52.69', '2022-02-08 20:52:52.69');

--INSTRUCTOR AVAILABLE PERIOD
INSERT INTO INSTRUCTOR_AVAILABLE_PERIODS (instructor_id, available_periods_id)
VALUES (3, 1);

--BOAT AVAILABLE PERIOD
INSERT INTO BOAT_AVAILABLE_PERIODS(boat_id,available_periods_id)
values(1,2);

--HOME AVAILABLE PERIOD
INSERT INTO HOME_AVAILABLE_PERIODS(holiday_home_id,available_periods_id)
values(1,3);


-- BOAT PROMOTIONS
INSERT INTO BOAT_PROMOTION(id, from_time, price, to_time, valid_until, boat_id)
values (1, '2022-02-09 18:47:52.69', 2000, '2022-02-13 18:47:52.69', '2022-01-31 18:47:52.69', 1);

-- HOLIDAY HOME PROMOTIONS
INSERT INTO HOLIDAY_HOME_PROMOTION(id, from_time, price, to_time, valid_until, holiday_home_id)
values (2, '2022-02-09 18:47:52.69', 2000, '2022-02-13 18:47:52.69', '2022-01-31 18:47:52.69', 1);

-- ADVENTURE FISHING_PROMOTION
INSERT INTO FISHING_PROMOTION (id, from_time, price, to_time, valid_until, people_number, fishing_id)
values (3, '2022-02-09 18:47:52.69', 2000, '2022-02-13 18:47:52.69', '2022-01-31 18:47:52.69', 2, 1);

ALTER SEQUENCE PROMOTION_SEQ_GEN RESTART WITH 4;

