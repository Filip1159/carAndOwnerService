insert into owner (id, name, surname, date_of_birth, is_premium_customer)
values (1, 'Jan', 'Kowalski', '1982-07-07', true),
       (2, 'Urszula', 'Lewandowska', '1993-08-27', true),
       (3, 'Agata', 'Kurczewska', '1978-10-24', false),
       (4, 'Jakub', 'Piotrkowski', '1988-07-19', false),
       (5, 'Aleksandra', 'Kubiak', '1968-12-13', true),
       (6, 'Piotr', 'Kozioł', '1984-09-20', false),
       (7, 'Grzegorz', 'Nowak', '2000-01-05', false),
       (8, 'Zofia', 'Mickiewicz', '1997-03-14', true),
       (9, 'Natalia', 'Borowik', '1973-10-08', false),
       (10, 'Robert', 'Lewandowski', '1985-05-23', false);

insert into car (id, brand, model, first_registration, year)
values (1, 'Opel', 'Astra', '2005-05-21', 2004),
       (2, 'Audi', 'A4', '2013-09-17', 2013),
       (3, 'Mercedes', 'E55', '1998-11-23', 1998),
       (4, 'BMW', 'x6', '2017-02-28', 2017),
       (5, 'Ford', 'Mondeo', '2010-02-15', 2009),
       (6, 'Fiat', 'Panda', '2007-01-26', 2006),
       (7, 'Lexus', 'NX300', '2019-10-07', 2019),
       (8, 'Mazda', '2', '2018-06-30', 2018),
       (9, 'Kia', 'Sportage', '2020-04-12', 2020),
       (10, 'Chevolet', 'Aveo', '2010-09-03', 2010);

insert into ownership (owner_id, car_id)
values (1, 4),
       (1, 9),
       (2, 10),
       (3, 9),
       (4, 5),
       (4, 3),
       (4, 1),
       (6, 2),
       (7, 7),
       (7, 6),
       (8, 1),
       (9, 10),
       (9, 5),
       (10, 1);

insert into inspection (id, comments, date, is_positive, mileage, car_id)
VALUES
    (1, 'Wszystko w porządku', '2020-01-01', TRUE, 250000, 1),
    (2, 'Małe wgniecenie na przedniej szybie', '2021-02-05', FALSE, 180000, 3),
    (3, 'Silnik wymaga naprawy', '2018-03-10', FALSE, 120000, 2),
    (4, 'Bardzo dobry stan techniczny', '2019-04-15', TRUE, 300000, 4),
    (5, 'Lewa przednia lampa jest uszkodzona', '2020-05-20', FALSE, 100000, 5),
    (6, 'Koła wymagają wyważenia', '2021-06-25', FALSE, 160000, 1),
    (7, 'Problemy z hamulcami', '2022-07-30', FALSE, 150000, 7),
    (8, 'Lekkie wycieki oleju', '2022-08-05', FALSE, 200000, 9),
    (9, 'Lekko zniszczone tylne tarcze hamulcowe', '2023-01-10', TRUE, 240000, 6),
    (10, 'Brak usterek', '2023-03-15', TRUE, 270000, 3),
    (11, 'Lewa przednia lampa wymaga wymiany', '2019-11-20', FALSE, 110000, 8),
    (12, 'Bardzo dobry stan pojazdu', '2022-12-25', TRUE, 90000, 2),
    (13, 'Problem z układem wydechowym', '2021-01-01', FALSE, 140000, 4),
    (14, 'Wszystko w porządku', '2017-02-05', TRUE, 80000, 5),
    (15, 'Zniszczone tylne zawieszenie', '2020-03-10', FALSE, 120000, 6),
    (16, 'Drobne rysy na karoserii', '2022-04-15', FALSE, 30000, 10),
    (17, 'Wymaga wymiany opon', '2023-05-20', FALSE, 100000, 9),
    (18, 'Dobry stan techniczny', '2022-06-25', TRUE, 60000, 7),
    (19, 'Wnętrze wymaga czyszczenia', '2021-07-30', TRUE, 150000, 8),
    (20, 'Lekkie wgniecenie na tylnej klapie', '2020-08-05', TRUE, 200000, 1);
