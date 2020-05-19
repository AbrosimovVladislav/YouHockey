КОНЬКИ
ЗАЩИТА_ИГРОКА
КЛЮШКИ
ОДЕЖДА
ВРАТАРСКАЯ_ФОРМА
СУМКИ
АКСЕССУАРЫ
РАЗНОЕ

-- ----------------------------------------------------------------1 PRICE---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','offer','price','offer.price');;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('КОНЬКИ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ЗАЩИТА_ИГРОКА','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('КЛЮШКИ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ОДЕЖДА','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ВРАТАРСКАЯ_ФОРМА','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('СУМКИ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('АКСЕССУАРЫ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('РАЗНОЕ','Цена', 'RANGE', 1,1);;
-- ----------------------------------------------------------------4 BRAND---------------------------------------------------------
/*INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','brand','short_name','brand.shortName');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES  ('Бренд', 'CHECKBOX', 4,4);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;







-- ----------------------------------------------------------------5 CONDITION---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','condition','condition');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Состояние', 'CHECKBOX', 5,5);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Состояние', 'CHECKBOX', 5,5);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop',     'Состояние', 'CHECKBOX', 5,5);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',      'Состояние', 'CHECKBOX', 5,5);;
-- ----------------------------------------------------------------6 CAPACITY---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','capacity','capacity');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Емкость магазина', 'CHECKBOX', 6,6);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Емкость магазина', 'CHECKBOX',  6,6);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Емкость магазина', 'CHECKBOX',      6,6);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Емкость магазина', 'CHECKBOX',      6,6);;
-- ----------------------------------------------------------------7 BARREL_LENGTH---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','barrel_length','barrelLength');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Длина ствола', 'CHECKBOX', 7,7);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Длина ствола', 'CHECKBOX', 7,7);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Длина ствола', 'CHECKBOX', 7,7);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Длина ствола', 'CHECKBOX', 7,7);;
-- ----------------------------------------------------------------8 TOTAL_LENGTH---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','total_length','totalLength');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Общая длина', 'CHECKBOX', 8,8);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Общая длина', 'CHECKBOX',   8,8);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Общая длина', 'CHECKBOX',       8,8);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp', 'Общая длина', 'CHECKBOX',        8,8);;
-- ----------------------------------------------------------------9 BARREL_ORIENTATION---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','barrel_orientation','barrelOrientation');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Расположение стволов', 'CHECKBOX', 9,9);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Расположение стволов', 'CHECKBOX',  9,9);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Расположение стволов', 'CHECKBOX',      9,9);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Расположение стволов', 'CHECKBOX',      9,9);;
-- ----------------------------------------------------------------10 WEIGHT---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','weight','weight');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Вес', 'CHECKBOX', 10,10);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Вес', 'CHECKBOX',   10,10);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Вес', 'CHECKBOX',       10,10);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Вес', 'CHECKBOX',       10,10);;
-- ----------------------------------------------------------------11 COUNTRY---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','country','country');;
INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Страна происхождения', 'CHECKBOX', 11,11);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Страна происхождения', 'CHECKBOX',  11,11);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Страна происхождения', 'CHECKBOX',      11,11);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Страна происхождения', 'CHECKBOX',      11,11);;*/
