[МОДЕЛЬ][БРЕНД]
[КАЛИБР][МАССА][ДЛИНА_СТВОЛА][ПРИНЦИП_РАБОТЫ][ТИП_ЗАРЯДА][НОМЕР_ДРОБИ][МАССА ПУЛИ][СОВМЕСТИМОСТЬ][ВИД_ТЮНИНГА][ЦВЕТ][МАТЕРИАЛ][РЕЗЬБА][КРЕПЛЕНИЕ][УВЕЛИЧЕНИЕ][ЕМКОСТЬ][ДЛИНА]

ОХОТНИЧЬЕ_ОРУЖИЕ:
  [КАЛИБР][МАССА][ДЛИНА_СТВОЛА][ПРИНЦИП_РАБОТЫ]
  ГЛАДКОСТВОЛЬНОЕ - 1
  НАРЕЗНОЕ - 2
  ОГРАНИЧЕННОГО_ПОРАЖЕНИЯ - 3
  ОХОЛОЩЕННОЕ - 4

ПАТРОНЫ:
  [КАЛИБР][ТИП_ЗАРЯДА]
  ГЛАДКОСТВОЛЬНЫЕ - 5:
    [НОМЕР_ДРОБИ]
  НАРЕЗНЫЕ - 6:
    [МАССА ПУЛИ]
  ТРАВМАТИЧЕСКИЕ - 7:
    [МАССА ПУЛИ]
  ПРОЧИЕ - 8

ТЮНИНГ:
  [СОВМЕСТИМОСТЬ][ВИД]
    ПРИКЛАДЫ - 9:
      [ЦВЕТ][МАТЕРИАЛ]
    АКСЕССУАРЫ_ДЛЯ_ПРИКЛАДОВ - 10
    ДУЛЬНЫЕ_УСТРОЙСТВА - 11:
      [КАЛИБР][РЕЗЬБА]
    ГАЗОВАЯ_СИСТЕМА - 12
    ОРГАНЫ_УПРАВЛЕНИЯ - 13
    ЦЕВЬЯ - 14:
      [ЦВЕТ][МАТЕРИАЛ]
    АКСЕССУАРЫ_ДЛЯ_ЦЕВЬЯ - 15:
      [КРЕПЛЕНИЕ]
    УСМ - 16
    ПИСТОЛЕТНЫЕ_РУКОЯТКИ - 17:
      [ЦВЕТ]
    МАГАЗИНЫ - 18:
      [ЕМКОСТЬ][КАЛИБР][ЦВЕТ]
    ФОНАРИ_И_ЛЦУ - 19
    ТАКТИЧЕСКИЕ_РУКОЯТКИ - 20:
      [ЦВЕТ][КРЕПЛЕНИЕ]
    АНТАБКИ_И_РЕМНИ - 21:
      [КРЕПЛЕНИЕ]
    КРОШТЕЙНЫ_И_БАЗЫ - 22:
      [КРЕПЛЕНИЕ]
    ПРИЦЕЛЫ:
      [КРЕПЛЕНИЕ]
      ОПТИЧЕСКИЕ - 23:
        [УВЕЛИЧЕНИЕ]
      КОЛЛИМАТОРНЫЕ - 24
    СОШКИ - 25:
      [КРЕПЛЕНИЕ]
    ПРОЧЕЕ - 26

АКСЕССУАРЫ:
  СЕЙФЫ - 27:
    [ЕМКОСТЬ]
  ЧЕХЛЫ - 28:
    [ДЛИНА][МАТЕРИАЛ]
    ОРУЖЕЙНЫЙ_ЧЕХОЛ - 30
    ЧЕХОЛ_ДЛЯ_ПРИНАДЛЕЖНОСТЕЙ - 31
    ЯЩИК_ДЛЯ_ПАТРОНОВ - 32
    ПРОЧИЕ_ЧЕХЛЫ - 33
  СРЕДСТВА_ДЛЯ_ЧИСТКИ - 29
--
---- ----------------------------------------------------------------1 PRICE---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','offer','price','offer.price');;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ОХОТНИЧЬЕ_ОРУЖИЕ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ПАТРОНЫ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ТЮНИНГ','Цена', 'RANGE', 1,1);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('АКСЕССУАРЫ','Цена', 'RANGE', 1,1);;
---- ----------------------------------------------------------------2 BRAND---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','brand','short_name','brand.shortName');;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ОХОТНИЧЬЕ_ОРУЖИЕ','Цена', 'CHECKBOX', 2,2);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ПАТРОНЫ','Цена', 'CHECKBOX', 2,2);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ТЮНИНГ','Цена', 'CHECKBOX', 2,2);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('АКСЕССУАРЫ','Цена', 'CHECKBOX', 2,2);;
---- ----------------------------------------------------------------3 CALIBER---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','brand','short_name','brand.shortName');;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ОХОТНИЧЬЕ_ОРУЖИЕ','Цена', 'CHECKBOX', 3,3);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ПАТРОНЫ','Цена', 'CHECKBOX', 3,3);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ДУЛЬНЫЕ_УСТРОЙСТВА','Цена', 'CHECKBOX', 3,3);;
INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('ПАТРОНЫ','Цена', 'CHECKBOX', 3,3);;


---- ----------------------------------------------------------------4 BRAND---------------------------------------------------------
--/*INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','brand','short_name','brand.shortName');;
--INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES  ('Бренд', 'CHECKBOX', 4,4);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Бренд', 'CHECKBOX', 4,4);;
--
--
--
--
--
--
--


---- ----------------------------------------------------------------7 BARREL_LENGTH---------------------------------------------------------
--INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','product','barrel_length','barrelLength');;
--INSERT INTO filter_item (menu_item, name, type, key_path_id,rank) VALUES ('Gladkostvol', 'Длина ствола', 'CHECKBOX', 7,7);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Nareznoe', 'Длина ствола', 'CHECKBOX', 7,7);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Ooop', 'Длина ствола', 'CHECKBOX', 7,7);;
--INSERT INTO filter_item (menu_item, name, type, key_path_id, rank) VALUES ('Shp',  'Длина ствола', 'CHECKBOX', 7,7);;*/
