-- Admin kullanıcı ekleme
INSERT INTO customers (uid, name, email, password) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Admin User', 'admin@domain.com', '$2a$10$DCgXaPzFfrsQbe.PiBbQfue2CtDUpQzYQkGp5mdLf8CrpFcZQGm1m'); -- admin şifresi: 'adminpassword'

-- Müşteriler ekleme
INSERT INTO customers (uid, name, email, password) VALUES
                                                       ('22222222-2222-2222-2222-222222222222', 'Ahmet Yılmaz', 'ahmet.yilmaz@hotmail.com', '$2a$10$BtxqGJCIJ3XLpIDmTh0A7OofEsyj7kIzt1DPybBB.t9Ug1/nr.jDa'), -- şifre: 'password1'
                                                       ('33333333-3333-3333-3333-333333333333', 'Ayşe Demir', 'ayse.demir@hotmail.com', '$2a$10$sIcduL6Gv7Gyt6DrhbzAG.m1qfDLWDUiPyXjFHf5wsL3Es5isW/R2'), -- şifre: 'password2'
                                                       ('44444444-4444-4444-4444-444444444444', 'Mehmet Kara', 'mehmet.kara@hotmail.com', '$2a$10$L1AfIjXMIhVqBfInhy9kHuGqufiD1ykT1soNBZPVavppizjtrjzC2'); -- şifre: 'password3'

-- Her müşteri için TRY varlığı ekleme
INSERT INTO assets (uid, customer_uid, asset_name, size, usable_size) VALUES
                                                                          ('a1a1a1a1-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222', 'TRY', 10000, 8000),
                                                                          ('b2b2b2b2-2222-2222-2222-222222222222', '33333333-3333-3333-3333-333333333333', 'TRY', 15000, 10000),
                                                                          ('c3c3c3c3-3333-3333-3333-333333333333', '44444444-4444-4444-4444-444444444444', 'TRY', 20000, 15000);

-- Her müşteri için 10 adet order ekleme (asset_name: AAPL, GOOG, MSFT gibi hisse senetleri)
INSERT INTO orders (uid, customer_uid, asset_name, order_side, size, price, status, create_date) VALUES
                                                                                                     ('ord-1a2b3c4d-1111', '22222222-2222-2222-2222-222222222222', 'AAPL', 'BUY', 50, 150, 'PENDING', '2024-10-06 10:00:00'),
                                                                                                     ('ord-2a2b3c4d-1112', '22222222-2222-2222-2222-222222222222', 'GOOG', 'BUY', 20, 2500, 'MATCHED', '2024-10-06 11:00:00'),
                                                                                                     ('ord-3a2b3c4d-1113', '22222222-2222-2222-2222-222222222222', 'MSFT', 'SELL', 30, 300, 'PENDING', '2024-10-06 12:00:00'),
                                                                                                     ('ord-4a2b3c4d-1114', '22222222-2222-2222-2222-222222222222', 'AAPL', 'BUY', 40, 155, 'MATCHED', '2024-10-06 13:00:00'),
                                                                                                     ('ord-5a2b3c4d-1115', '22222222-2222-2222-2222-222222222222', 'GOOG', 'SELL', 10, 2550, 'CANCELED', '2024-10-06 14:00:00'),
                                                                                                     ('ord-6a2b3c4d-1116', '22222222-2222-2222-2222-222222222222', 'MSFT', 'BUY', 15, 305, 'PENDING', '2024-10-06 15:00:00'),
                                                                                                     ('ord-7a2b3c4d-1117', '22222222-2222-2222-2222-222222222222', 'AAPL', 'BUY', 35, 157, 'PENDING', '2024-10-06 16:00:00'),
                                                                                                     ('ord-8a2b3c4d-1118', '22222222-2222-2222-2222-222222222222', 'GOOG', 'SELL', 25, 2580, 'MATCHED', '2024-10-06 17:00:00'),
                                                                                                     ('ord-9a2b3c4d-1119', '22222222-2222-2222-2222-222222222222', 'MSFT', 'SELL', 20, 310, 'PENDING', '2024-10-06 18:00:00'),
                                                                                                     ('ord-0a2b3c4d-1120', '22222222-2222-2222-2222-222222222222', 'AAPL', 'BUY', 45, 160, 'CANCELED', '2024-10-06 19:00:00');

INSERT INTO orders (uid, customer_uid, asset_name, order_side, size, price, status, create_date) VALUES
                                                                                                     ('ord-1b2b3c4d-2221', '33333333-3333-3333-3333-333333333333', 'AAPL', 'BUY', 50, 150, 'PENDING', '2024-10-06 10:00:00'),
                                                                                                     ('ord-2b2b3c4d-2222', '33333333-3333-3333-3333-333333333333', 'GOOG', 'BUY', 20, 2500, 'MATCHED', '2024-10-06 11:00:00'),
                                                                                                     ('ord-3b2b3c4d-2223', '33333333-3333-3333-3333-333333333333', 'MSFT', 'SELL', 30, 300, 'PENDING', '2024-10-06 12:00:00'),
                                                                                                     ('ord-4b2b3c4d-2224', '33333333-3333-3333-3333-333333333333', 'AAPL', 'BUY', 40, 155, 'MATCHED', '2024-10-06 13:00:00'),
                                                                                                     ('ord-5b2b3c4d-2225', '33333333-3333-3333-3333-333333333333', 'GOOG', 'SELL', 10, 2550, 'CANCELED', '2024-10-06 14:00:00'),
                                                                                                     ('ord-6b2b3c4d-2226', '33333333-3333-3333-3333-333333333333', 'MSFT', 'BUY', 15, 305, 'PENDING', '2024-10-06 15:00:00'),
                                                                                                     ('ord-7b2b3c4d-2227', '33333333-3333-3333-3333-333333333333', 'AAPL', 'BUY', 35, 157, 'PENDING', '2024-10-06 16:00:00'),
                                                                                                     ('ord-8b2b3c4d-2228', '33333333-3333-3333-3333-333333333333', 'GOOG', 'SELL', 25, 2580, 'MATCHED', '2024-10-06 17:00:00'),
                                                                                                     ('ord-9b2b3c4d-2229', '33333333-3333-3333-3333-333333333333', 'MSFT', 'SELL', 20, 310, 'PENDING', '2024-10-06 18:00:00'),
                                                                                                     ('ord-0b2b3c4d-2230', '33333333-3333-3333-3333-333333333333', 'AAPL', 'BUY', 45, 160, 'CANCELED', '2024-10-06 19:00:00');

-- Müşteri 3 için de benzer 10 order eklemesi yapın
INSERT INTO orders (uid, customer_uid, asset_name, order_side, size, price, status, create_date) VALUES
                                                                                                     ('ord-1c2b3c4d-3331', '44444444-4444-4444-4444-444444444444', 'AAPL', 'BUY', 50, 150, 'PENDING', '2024-10-06 10:00:00'),
                                                                                                     ('ord-2c2b3c4d-3332', '44444444-4444-4444-4444-444444444444', 'GOOG', 'BUY', 20, 2500, 'MATCHED', '2024-10-06 11:00:00'),
                                                                                                     ('ord-3c2b3c4d-3333', '44444444-4444-4444-4444-444444444444', 'MSFT', 'SELL', 30, 300, 'PENDING', '2024-10-06 12:00:00'),
                                                                                                     ('ord-4c2b3c4d-3334', '44444444-4444-4444-4444-444444444444', 'AAPL', 'BUY', 40, 155, 'MATCHED', '2024-10-06 13:00:00'),
                                                                                                     ('ord-5c2b3c4d-3335', '44444444-4444-4444-4444-444444444444', 'GOOG', 'SELL', 10, 2550, 'CANCELED', '2024-10-06 14:00:00'),
                                                                                                     ('ord-6c2b3c4d-3336', '44444444-4444-4444-4444-444444444444', 'MSFT', 'BUY', 15, 305, 'PENDING', '2024-10-06 15:00:00'),
                                                                                                     ('ord-7c2b3c4d-3337', '44444444-4444-4444-4444-444444444444', 'AAPL', 'BUY', 35, 157, 'PENDING', '2024-10-06 16:00:00'),
                                                                                                     ('ord-8c2b3c4d-3338', '44444444-4444-4444-4444-444444444444', 'GOOG', 'SELL', 25, 2580, 'MATCHED', '2024-10-06 17:00:00'),
                                                                                                     ('ord-9c2b3c4d-3339', '44444444-4444-4444-4444-444444444444', 'MSFT', 'SELL', 20, 310, 'PENDING', '2024-10-06 18:00:00'),
                                                                                                     ('ord-0c2b3c4d-3340', '44444444-4444-4444-4444-444444444444', 'AAPL', 'BUY', 45, 160, 'CANCELED', '2024-10-06 19:00:00');
