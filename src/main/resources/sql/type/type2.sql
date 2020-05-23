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
