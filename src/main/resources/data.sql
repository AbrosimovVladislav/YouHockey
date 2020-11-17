CREATE OR REPLACE FUNCTION insertNewRatingOnNewShop() RETURNS TRIGGER AS $shopTrigger$
    DECLARE
        i BIGINT;
    BEGIN
        IF NEW.rating_id IS NULL THEN
            INSERT INTO rating (value) VALUES (0) RETURNING rating_id into i;
            NEW.rating_id := i;
        END IF;
        RETURN NEW;
    END;
$shopTrigger$ LANGUAGE plpgsql;;

DROP TRIGGER IF EXISTS shopTrigger ON shop;;
CREATE TRIGGER shopTrigger BEFORE INSERT ON shop
FOR EACH ROW
EXECUTE FUNCTION insertNewRatingOnNewShop();;

CREATE OR REPLACE FUNCTION insertNewRatingOnNewProduct() RETURNS TRIGGER AS $productTrigger$
    DECLARE
        i BIGINT;
    BEGIN
        IF NEW.rating_id IS NULL THEN
            INSERT INTO rating (value) VALUES (0) RETURNING rating_id into i;
            NEW.rating_id := i;
        END IF;
        RETURN NEW;
    END;
$productTrigger$ LANGUAGE plpgsql;;

DROP TRIGGER IF EXISTS productTrigger ON product;;
CREATE TRIGGER productTrigger BEFORE INSERT ON product
FOR EACH ROW
EXECUTE FUNCTION insertNewRatingOnNewProduct();;

-- BRAND INSERTING ************************************************************************************************** --
INSERT INTO brand (full_name, short_name) VALUES ('BAUER Hockey, LLC','BAUER') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('Canada Cycle & Motor Co. Ltd.','CCM') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('Warrior hockey, Ltd.','WARRIOR') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('Fischer Sports GMBH','FISCHER') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('Brians Custom Sports','BRIANS') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('WALL','WALL') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('ITECH','ITECH') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('MH','MH') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('AR','AR') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SL','SL') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('VAUGHN','VAUGHN') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('EASTON','EASTON') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('REEBOK','REEBOK') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('MISSION','MISSION') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('EAGLE','EAGLE') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('TRUE','TRUE') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('ЗАРЯД','ЗАРЯД') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('HOWIES','HOWIES') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('UNDER ARMOUR','UNDER ARMOUR') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('ISOSTAR','ISOSTAR') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SHERWOOD','SHERWOOD') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('MAD GUY','MAD GUY') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('FINNSHARP','FINNSHARP') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('TOPO','TOPO') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('WRITEYBOARD','WRITEYBOARD') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('AEGIS','AEGIS') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('BLUESPORTS','BLUESPORTS') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SPORTSTAPE','SPORTSTAPE') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('RENFREW','RENFREW') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('POWERFLEX','POWERFLEX') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SALMING','SALMING') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('VEGUM','VEGUM') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('TSP','TSP') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('DRIBBLING PRO','DRIBBLING PRO') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('GREEN BISCUIT','GREEN BISCUIT') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('TEXSTYLE','TEXSTYLE') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('PROSHARP','PROSHARP') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SKATE MATE','SKATE MATE') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('BLADEMASTER','BLADEMASTER') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('SSM','SSM') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('EYELETS','EYELETS') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('STEP','STEP') ON CONFLICT (short_name) DO NOTHING;;
INSERT INTO brand (full_name, short_name) VALUES ('HEJDUK','HEJDUK') ON CONFLICT (short_name) DO NOTHING;;
-- ***************************************************************************************************************** --

-- TYPE INSERTING ************************************************************************************************** --
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (1,
        'КОНЬКИ',
        'КОНЬКИ_ХОККЕЙНЫЕ',
        'Коньки')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (2,
        'КОНЬКИ',
        'КОНЬКИ_РОЛИКОВЫЕ',
        'Коньки роликовые')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (3,
        'ЗАЩИТА_ИГРОКА',
        'НАГРУДНИК_ИГРОКА',
        'Нагрудник')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (4,
        'ЗАЩИТА_ИГРОКА',
        'ЩИТКИ_ИГРОКА',
        'Щитки')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (5,
        'ЗАЩИТА_ИГРОКА',
        'ШЛЕМ_ИГРОКА',
        'Шлем')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (6,
        'ЗАЩИТА_ИГРОКА',
        'ЗАЩИТА_ПАХА_ИГРОКА',
        'Защита паха')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (7,
        'ЗАЩИТА_ИГРОКА',
        'НАЛОКОТНИКИ_ИГРОКА',
        'Налокотники')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (8,
        'ЗАЩИТА_ИГРОКА',
        'ТРУСЫ_ИГРОКА',
        'Трусы')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (9,
        'ЗАЩИТА_ИГРОКА',
        'ВИЗОРЫ_И_МАСКИ_ИГРОКА',
        'Визор/Маска')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (10,
        'ЗАЩИТА_ИГРОКА',
        'ЗАЩИТА_ШЕИ_ИГРОКА',
        'Защита шеи')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (11,
        'ЗАЩИТА_ИГРОКА',
        'ПЕРЧАТКИ_ИГРОКА',
        'Перчатки')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (12,
        'ЗАЩИТА_ИГРОКА',
        'ЗАЩИТА_ЗАПЯСТЬЯ_ИГРОКА',
        'Защита запястья')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (13,
        'КЛЮШКИ',
        'КЛЮШКА_ИГРОКА',
        'Клюшка')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (14,
        'КЛЮШКИ',
        'КЛЮШКА_ВРАТАРЯ',
        'Вратарская клюшка')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (15,
        'КЛЮШКИ',
        'ТРУБЫ',
        'Труба')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (16,
        'КЛЮШКИ',
        'КРЮКИ',
        'Крюк')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (17,
        'ОДЕЖДА',
        'ГАМАШИ',
        'Гамаши')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (18,
        'ОДЕЖДА',
        'ТРЕНИРОВОЧНЫЕ_МАЙКИ',
        'Хоккейная майка')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (19,
        'ОДЕЖДА',
        'НАТЕЛЬНОЕ_БЕЛЬЕ',
        'Нательное белье')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (20,
        'ВРАТАРСКАЯ_ФОРМА',
        'НАГРУДНИК_ВРАТАРЯ',
        'Нагрудник вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (21,
        'ВРАТАРСКАЯ_ФОРМА',
        'ТРУСЫ_ВРАТАРЯ',
        'Трусы вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (22,
        'ВРАТАРСКАЯ_ФОРМА',
        'КОНЬКИ_ВРАТАРЯ',
        'Коньки вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (23,
        'ВРАТАРСКАЯ_ФОРМА',
        'ЩИТКИ_ВРАТАРЯ',
        'Щитки вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (24,
        'ВРАТАРСКАЯ_ФОРМА',
        'ШЛЕМ_ВРАТАРЯ',
        'Шлем вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (25,
        'ВРАТАРСКАЯ_ФОРМА',
        'БЛИН_И_ЛОВУШКА_ВРАТАРЯ',
        'Блин/ловушка вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (26,
        'ВРАТАРСКАЯ_ФОРМА',
        'АКСЕССУАРЫ_ВРАТАРЯ',
        'ЗАЩИТА_ШЕИ_ВРАТАРЯ',
        'Защита шеи вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (27,
        'ВРАТАРСКАЯ_ФОРМА',
        'АКСЕССУАРЫ_ВРАТАРЯ',
        'ЗАЩИТА_ГОРЛА_ВРАТАРЯ',
        'Защита горла вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (28,
        'ВРАТАРСКАЯ_ФОРМА',
        'АКСЕССУАРЫ_ВРАТАРЯ',
        'ЗАЩИТА_ПАХА_ВРАТАРЯ',
        'Защита паха вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (29,
        'ВРАТАРСКАЯ_ФОРМА',
        'АКСЕССУАРЫ_ВРАТАРЯ',
        'ЗАЩИТА_КОЛЕНЕЙ_ВРАТАРЯ',
        'Защита коленей вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (30,
        'ВРАТАРСКАЯ_ФОРМА',
        'АКСЕССУАРЫ_ВРАТАРЯ',
        'ПОЯС_ВРАТАРЯ',
        'Пояс вратаря')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (31,
        'СУМКИ',
        'СУМКИ_ХОККЕЙНЫЕ',
        'Сумка хоккейная')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (32,
        'СУМКИ',
        'СУМКИ_ДЛЯ_ПРИНАДЛЕЖНОСТЕЙ',
        'Сумка для принадлежностей')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (33,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_КОНЬКОВ',
        'ЧЕХЛЫ',
        'Чехлы')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (34,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_КОНЬКОВ',
        'ЛЕЗВИЯ',
        'Лезвия')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (35,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_КОНЬКОВ',
        'СТАКАНЫ',
        'Стаканы')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (36,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_КОНЬКОВ',
        'ШНУРКИ',
        'Шнурки')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (37,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_ШЛЕМА',
        'ЗАПЧАСТИ',
        'Запчасти для шлема')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (38,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_ШЛЕМА',
        'АНТИФОГ',
        'Антифог')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (39,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_ШЛЕМА',
        'ПОДШЛЕМНИКИ',
        'Подшлемник')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (40,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_КЛЮШЕК',
        'ЛЕНТА',
        'Лента')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (41,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_ИГРОКА',
        'БУТЫЛКИ',
        'Бутылка')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (42,
        'АКСЕССУАРЫ',
        'АКСЕССУАРЫ_ДЛЯ_ИГРОКА',
        'ПОЯСА_И_ПОДТЯЖКИ',
        'Пояс/Подтяжки')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (43,
        'АКСЕССУАРЫ',
        'ШАЙБЫ_И_МЯЧИ',
        'ШАЙБЫ',
        'Шайба')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, lower, show_name)
VALUES (44,
        'АКСЕССУАРЫ',
        'ШАЙБЫ_И_МЯЧИ',
        'МЯЧИ',
        'Мяч')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (45,
        'АКСЕССУАРЫ',
        'ВОРОТА',
        'Ворота')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;
INSERT INTO type (type_id, upper, medium, show_name)
VALUES (46,
        'РАЗНОЕ',
        'ДОСКИ_ТАКТИЧЕСКИЕ',
        'Доска тактическая')
ON CONFLICT (type_id) DO UPDATE
    SET upper     = excluded.upper,
        medium    = excluded.medium,
        show_name = excluded.show_name;;


INSERT INTO shop (name, website) VALUES ('HOCKEYBEZGRANIC', 'https://hockeybezgranic.ru') ON CONFLICT (website) DO NOTHING;;
INSERT INTO shop (name, website) VALUES ('SPORTDEPO', 'https://www.sportdepo.ru/') ON CONFLICT (website) DO NOTHING;;
INSERT INTO shop (name, website) VALUES ('HOCK5', 'https://hock5.ru/') ON CONFLICT (website) DO NOTHING;;
INSERT INTO shop (name, website) VALUES ('ALLHOCKEY', 'https://allhockey24.ru') ON CONFLICT (website) DO NOTHING;;
INSERT INTO shop (name, website) VALUES ('FORMASPB', 'https://forma-spb.ru') ON CONFLICT (website) DO NOTHING;;
INSERT INTO shop (name, website) VALUES ('KLUSHKI', 'https://klushki.com//') ON CONFLICT (website) DO NOTHING;;
--this shop was added to db by hands, check why it is that
-- INSERT INTO shop (name, website) VALUES ('NORDHOCKEY', 'https://www.nordhockey.ru') ON CONFLICT (website) DO NOTHING;;

/*-- ----------------------------------------------------------------1 PRICE---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','offer','price','offer.price')ON CONFLICT (value) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (1,'КОНЬКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (2,'ЗАЩИТА_ИГРОКА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (3,'КЛЮШКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (4,'ОДЕЖДА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (5,'ВРАТАРСКАЯ_ФОРМА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (6,'СУМКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (7,'АКСЕССУАРЫ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (8,'РАЗНОЕ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
*/
/*-- ----------------------------------------------------------------2 BRAND---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','brand','short_name','brand.shortName')ON CONFLICT (value) DO NOTHING;;

*/
