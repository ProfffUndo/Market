Alter table spring_session
    ALTER column principal_name type varchar(3000);

insert into category values (1,'обувь');
insert into category values (2,'Аксессуары');
insert into category values (3,'Электроника');
insert into category values (4,'Бытовая техника');
insert into category values (5,'Книги');
insert into category values (6,'Спорт');
insert into category values (7,'Игрушки');
insert into category values (8,'Сад и дача');
insert into category values (9,'Зоотовары');
insert into category values (10,'Канцтовары');

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values (
    1,'Зимние кроссовки с баскетбольным силуэтом. Прочный высокий верх дополнен теплой подкладкой из искуственного меха. Прорезиненный мысок и пятка защищают в непогоду. Легкая и мягкая подошва дарит комфорт в течение всего дня.',
    'https://images.wbstatic.net/c516x688/new/6090000/6090085-1.jpg','adidas HOOPS 2.0 MID',4549,1,13,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (2,'Выгляди круто в любую погоду. Женские кроссовки Reebok Royal Glide в стиле старой школы дольше хранят тепло, когда на улице холодно. Они мягкие внутри благодаря подкладке "шерпа". Для лучшего сцепления и прочности на подмётке есть рифлёный протектор. Высокий кроссовок фиксирует лодыжку для большего комфорта. Высокая модель для большей поддержки лодыжки. Ощущение уюта. Утепляющая подкладка "шерпа"; Съемная анатомическая стелька Ortholite из мягкого пеноматериала. Формованная промежуточная подошва из ЭВА обеспечивает амортизацию без утяжеления. Стойкая к истиранию рифленая резиновая подметка обеспечивает отличное сцепление.',
     'https://images.wbstatic.net/c516x688/new/14650000/14652578-1.jpg','REEBOK ROYAL GLIDE TRUGR7',3449,1,13,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (3,'Спортивный стиль мягких сандалий - слайдов LiteRide отлично рифмуется с вашим образом жизни! Утренняя зарядка, динамичное движение по городу или неторопливая прогулка после долгого рабочего дня - обувь LiteRide поддерживает ваш ритм. Необыкновенно мягкая стелька и невесомая подошва будут оберегать ваш комфорт, сколько бы времени вы ни проводили в движении. Для вашего комфорта при получении заказа, отмечаем, что на обуви обозначен американский размер. Другие международные размеры указаны на элементах упаковки.',
     'https://images.wbstatic.net/c516x688/new/10960000/10965114-1.jpg','Шлепанцы CROCS',2309,1,26,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (4,'Что-то очень странное происходит с наступлением зимы. Даже в теплых и уютных куртках, свитерах, шарфах, нам все равно холодно. Если не хотите превратиться в сосульку через несколько минут после того, как вышли из дома, купите зимние перчатки для мужчин. Они идеально подходят для холодного сезона.Сенсорные перчатки позволяют беспрепятственно пользоваться смартфоном. К тому же они стильно выглядят. Тактические перчатки отлично сочетаются с пальто и куртками.Черные теплые перчатки - это идеальный аксессуар для холодной погоды. Они модные, красивые и практичные. Наденьте их, и вы будете чувствовать себя комфортно даже в самый сильный мороз.Перчатки для телефона не нужно снимать, чтобы задать смартфону необходимые команды или ответить на звонок. В них вы можете водить автомобиль.Если в холодную погоду вам хочется выглядеть особенно ярко и чувствовать себя комфортно, обратите внимание на зимние модные мужские перчатки BEZET! Они обязательно должны быть в вашем гардеробе.',
     'https://images.wbstatic.net/c516x688/new/29410000/29415257-5.jpg','Перчатки мужские BEZET',1689,2,18,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (5,'Стильный ремень для женщин. Выполнен из высококачественной экокожи. По всему ремню расположены люверсы . Незаменимый аксессуар на каждый день.',
     'https://images.wbstatic.net/c516x688/new/14020000/14024250-2.jpg','Ремень с люверсами TopOnePrice',139,2,6,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (6,'Стильные солнцезащитные очки формы авиаторы выполнены из легкого прочного пластика. Шестислойные поляризационные линзы с антибликовым эффектом надежно защищают ваши глаза от солнечных лучей. В комплекте фирменный футляр. Шестислойные поляризационные линзы УФ - покрытие: UV 400 Регулируемые носовые упоры В комплекте идет фирменный футляр Световой фильтр 3 категории',
     'https://images.wbstatic.net/c516x688/new/21010000/21017560-4.jpg','Очки солнцезащитные Авиаторы Goodyear',1059,2,27,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (7,'Mi Smart Band 6 стирает границы! Невероятно широкий функционал Mi Smart Band 6 и внушительная диагональ экрана 1,56 дюйма позволяют гаджету шагнуть за черту привычного фитнес-трекера. Теперь отличия между новинкой и умными часами становятся настолько призрачными, что гаджет легко затмит даже гораздо более дорогие устройства.',
     'https://images.wbstatic.net/c516x688/new/26410000/26414401-1.jpg','Фитнес-браслет Mi Band 6',3271,3,18,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (8,'Струйное многофункциональное устройство HP DeskJet 2720 All in One Printer принтер/сканер/копир.',
     'https://images.wbstatic.net/c516x688/new/15820000/15824339-1.jpg','Цветное струйное МФУ DeskJet 2720',3819,3,9,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (9,'Умная колонка, которая управляется голосом. Внутри-голосовой помощник Алиса: она включает музыку, отвечает на вопросы и выполняет поручения. Также станция Лайт помогает управлять лампами, увлажнителями и другими устройствами, которые работают с Алисой.',
     'https://images.wbstatic.net/c516x688/new/33370000/33376524-1.jpg','Умная колонка Яндекс Станция Лайт ',3990,3,20,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (10,'Итальянская помпа с давлением 15 бар. Автоматический капучинатор. Сенсорная панель управления. Защита от перегрева. Съемный поддон для сбора капель. Съемный резервуар для воды (1.8 л) Трафареты для рисунков на молочной пене. Мерная ложка-уплотнитель в комплекте. Стальная платформа для подогрева чашек. Съемный фильтр. Мощность 1400 Вт. Корпус из нержавеющей стали. Система Coffee Non-Stop более 5 чашек кофе без перерыва. Панель управления с технологией Glass Touch. Съемная насадка для молока подходит для посудомоечных машин! Индикация заполнения поддона. Резервуар для молока объемом 0.5 л. Индикация включения с подсветкой. Система самоочистки. Для получения нежной пенки в рожковых кофеварках рекомендуется использовать охлажденное молоко жирностью не менее 2,5%.',
     'https://images.wbstatic.net/c516x688/new/6520000/6521709-1.jpg','Кофеварка PCM 1535E Adore Cappuccino',17999,4,14,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (11,'Пылесос-робот Xiaomi Mi Robot Vacuum Mop - функциональный бытовой прибор, с которым вы добьетесь идеальной чистоты в доме. Особенность устройства в поддержке влажной и сухой уборки, что позволит использовать его и для сбора мусора, и для мытья пола. Конструкция модели предполагает наличие пылесборника, объем которого равен 600 мл: периодически пылесборник необходимо опустошать. Контейнер также может быть использовать и при влажной уборке для добавления воды.',
     'https://images.wbstatic.net/c516x688/new/14440000/14444933-1.jpg','Робот-пылесос Mi Robot Vacuum-Mop Essential',11542,4,25,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (12,'Picooc Mini V2 - модель второго поколения умных весов Picooc. Повышена точность при определении состава тела. Добавлен новый измеряемый параметр - пульс. При каждом взвешивании модель позволяет отслеживать частоту сердечных сокращений и состав тела. Во втором поколении умных весов Picooc впервые используется метод многофазного биоимпеданса, что на 30% снижает погрешность измерения. При расчетах применяется специальный алгоритм, учитывающий возраст, пол и этнос.Весы Picooc Mini V2 измеряют более 15 параметров: массу, количество жира, мышц, воды в организме, скорость основного обмена веществ и др. Результаты сохраняются на смартфон при помощи Bluetooth Smart. Бесплатное русифицированное приложение анализирует данные и дает рекомендации для похудения, поддержания формы или набора мышечной массы.',
     'https://images.wbstatic.net/c516x688/new/18370000/18377374-1.jpg','Умные диагностические весы Picooc Mini White V2',2692,4,30,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (13,'Всемирное признание Айн Рэнд нетрудно объяснить: исключительный дар предвидения в самых разных областях - политике, бизнесе, экономике, общественных отношениях - в сочетании с художественной одаренностью принес ей славу большого писателя и проницательного мыслителя. Атлант расправил плечи, самое значимое произведение своей жизни, она писала 12 лет. В первой части читатели знакомятся с главными героями, гениальными предпринимателями, которым противостоят их антиподы - бездарные государственные чиновники. Повествование начинается с вопроса - Кто такой Джон Голт? - и на этот вопрос будут искать ответ герои романа и его читатели.',
     'https://images.wbstatic.net/c516x688/new/7610000/7614422-1.jpg','Атлант расправил плечи. В 3 книгах.',890,5,120,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (14,'Своеобразный антипод второй великой антиутопии XX века - "О дивный новый мир" Олдоса Хаксли. Что, в сущности, страшнее: доведенное до абсурда "общество потребления" - или доведенное до абсолюта "общество идеи"? По Оруэллу, нет и не может быть ничего ужаснее тотальной несвободы...',
     'https://images.wbstatic.net/c516x688/new/10730000/10733444-1.jpg','1984',213,5,80,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (15,'Самый известный роман бразильского писателя Пауло Коэльо, любимая книга миллионов людей во всем мире. В юности люди не боятся мечтать, все кажется им возможным. Но проходит время, и таинственная сила принимается им внушать, что их желания неосуществимы. "Добиться воплощения своей Судьбы - вот единственная подлинная обязанность человека...", - утверждает Пауло Коэльо. Этот, ставший культовым, роман-притча способен изменить жизнь своих читателей.',
     'https://images.wbstatic.net/c516x688/new/2160000/2167403-1.jpg','Пауло Коэльо - Алхимик',288,5,100,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (16,'Трюковой самокат Comrade 2021 предлагается нами в качестве первого самоката для начинающих райдеров ростом 145-160 см и весом до 100 кг, подходящий для катания в стилях Стрит и Парк. Особенность модели - усиленный Y-образный руль изготовленный из высокопрочного легкого алюминия 6061. Высота руля от нижней части зажима 62 см, от поверхности земли 82,5 см, ширина руля 58 см, диаметр 34,5 мм. Мягкие грипсы 17 см отлично гасят удары и вибрацию, пластиковые баренды обеспечивают их надежную фиксацию и защищают от травм. Не смотря, на начальный уровень, модель оснащена компрессией HIC. Размеры деки 51 см длина , 11,5 см ширина, футспейс 36 см.',
     'https://images.wbstatic.net/c516x688/new/26400000/26401290-5.jpg','Самокат трюковой Comrade 2021',6960,6,6,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (17,'Для определения размера коньков, рекомендуем воспользоваться следующими данными: размер 29-32 - раздвигается с 18 см до 20 см, размер 33-36 - раздвигается с 20 см до 22 см, размер 37-40 - раздвигается с 22,5 см до 25 см.Раздвижные ледовые коньки Arctic Boy, созданные специально для мальчиков от компании TechTeam. Полужесткий ботинок для большего комфорта. Верхняя часть ботинка в пластиковом каркасе для надежной фиксации голеностопа. Простая система фиксации облегчает надевание и регулировку коньков - верхняя микрометрическая клипса, Velcro - липучка для фиксации пятки, классическая система быстрой шнуровки. Растет нога, растут и коньки! Регулировка на 4 размера. Коньки легко надеваются и регулируются по размеру, с ними ребенок не расстанется долго, ведь особая система регулировки позволяет менять размер.',
     'https://images.wbstatic.net/c516x688/new/15270000/15278547-2.jpg','Коньки фигурные раздвижные Arctic Boy',3150,6,10,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (18,'Боксерские перчатки BoyBo Stain - это яркий дизайн для ярких спортсменов.Перчатки для бокса изготовлены из износостойкой эко кожи Flexy.Спортивные перчатки имеют вкладыш из пенополиуретана Crush Zone, благодаря использованию ударопоглощающих материалов увеличенной толщины, набивка обеспечивает наиболее гуманное гашение энергии удара в широком диапазоне нагрузок.Фиксация большого пальца минимизирует риск травм.Перфорация на ладони обеспечивает вентиляцию.Широкая манжета фиксируется на липучке для надежной фиксации запястного сустава.',
     'https://images.wbstatic.net/c516x688/new/36090000/36091876-3.jpg','Перчатки боксерские BoyBo Stain',1785,6,15,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (19,'Масштабная модель Honda Civic Type-R 1:32. У модели открываются двери капот и багажник.Модели магазина Гараж порадуют как детей, так и уже взрослых людей одержимых автомобилями.',
     'https://images.wbstatic.net/c516x688/new/26800000/26801113-1.jpg','Машинка Honda Civic Type-R в масштабе 1:32',1280,7,7,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (20,'Бластер BASR-L выполнен по образу оружия из популярной видеоигры Fortnite. Он снабжен скользящим затвором, обоймой, съемным прицелом и 12 стрелами Nerf Elite. Вставьте обойму со стрелами в бластер, передвиньте затвор и нажмите на спусковой крючок, чтобы сделать выстрел! Стрелы Nerf изготовлены из мягкого материала, прошли испытания и являются травмобезопасными. Воссоздайте эффектные моменты из любимой игры Фортнайт вместе с бластером BASR-L! Авторские права принадлежат Epic Games. Nerf и все связанные термины являются торговыми марками Hasbro. В комплекте бластер, ложе, прицел, обойма, 12 стрел, инструкция.',
     'https://images.wbstatic.net/c516x688/new/14020000/14023443-1.jpg','Бластер Нёрф Фортнайт BASR NERF FORTNITE E7522',2516,7,23,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (21,'Настоящим подарком для поклонников культовой киносаги "Звездные войны" станет конструктор LEGO Star Wars Episode IX 75257 "Сокол Тысячелетия". Продуманность внутреннего и внешнего вида собранной игровой модели восхищает и поражает. Внешний вид грузового судна отличает наличие вращающихся нижних и верхних турелей, 2 пушек на пружинном механизме, открывающейся кабины и опускающегося пандуса. Внутри - двухконтейнерный грузовой отсек, навигация, скрытый отсек и ремонтный блок. В качестве персонажей игрового набора выступают 7 минифигурок. Конструктор с фигурками, предназначенный для детей от 9 лет, порадует не только ребят, но и взрослых, в собранном виде он станет прекрасным коллекционным экспонатом.- В наборе имеется 7 минифигурок персонажей.- До мелочей продуманные и воссозданные из деталей конструктора интерьер и экстерьер грузового судна.- Игровой набор, способный в собранном виде стать коллекционным экспонатом.',
     'https://images.wbstatic.net/c516x688/new/9730000/9739322-1.jpg','Конструктор LEGO Star Wars Episode IX 75257 Сокол Тысячелетия',8099,7,12,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (22,'Шашлычные наборы для пикника, прекрасный подарок на любой праздник! Это не только оригинальный, красивый и качественный подарок но и главная его функция это польза. Вся наша продукция производится в России.Комплект включают в себя все необходимое для комфортного отдыха:-Профессиональные шампуры из нержавеющей стали(AISI430) тип лезвия-прямое, деревянной ручкой-бук.',
     'https://images.wbstatic.net/c516x688/new/18950000/18959364-5.jpg','Набор для пикника SHAMPUROV',6080,8,40,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (23,'Модель Керхер K 3 идеальна для периодического использования. Она поможет отмыть несильно загрязненные легковые автомобили, мотоциклы, велосипеды, садовую мебель, площадки вокруг дома и многое другое. Аппарат высокого давления "Керхер" К 3 оснащен системой Quick Connect, что позволяет подсоединять 6-метровый шланг высокого давления одним движением. Грязевая фреза поможет справиться со стойкими загрязнениями, а струйная трубка Vario Power с регулировкой давления пригодится при мойке мотоциклов, садовой мебели и др.',
     'https://images.wbstatic.net/c516x688/new/19630000/19636544-1.jpg','Минимойка Karcher K 3',8099,8,8,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (24,'Вечнозеленое древовидное растение фикус микрокарпа (Ficus microcarpa) является представителем семейства Тутовые.В комнатных условиях растение представляет собой деревце, средняя высота которого может достигать около полутора метров. Оно выделяется сравнительно толстыми воздушными корешками. Как правило, они не достигают поверхности субстрата и образуют необычные формы.',
     'https://images.wbstatic.net/c516x688/new/41350000/41351699-4.jpg','Фикус микрокарпа GRIN ME',1190,8,10,0);

insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (25,'Феликс Двойная вкуснятина для котят точно придётся по вкусу маленьким озорникам благодаря удивительному сочетанию двух текстур: невероятных хрустящих гранул и нежных мягких кусочков. Наш корм специально предназначен для самых маленьких озорников от 6 до 52 недель. С кормом Феликс Двойная вкуснятина Ваш котенок получит все необходимое, чтобы расти здоровым, счастливым и с озорством открывать мир вокруг. Ваш маленький озорник с удовольствием съест все до последнего кусочка.',
     'https://images.wbstatic.net/c516x688/new/12390000/12393256-1.jpg','Сухой корм Felix Двойная Вкуснятина для котят до года, с курочкой',211,9,200,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (26,'Приспособление для вычесывания кошек и собак. Данное приспособление имеет удобную ручку. Не причиняет боли или иного дискомфорта вашему питомцу, более того - многим очень нравится эта процедура. Подходит для кошек и собак полудлинношерстных и длинношерстных пород. Ширина лезвия - 6.5 см. Длина зубьев лезвия - 3 мм.',
     'https://images.wbstatic.net/c516x688/new/6330000/6330563-1.jpg','Щетка триммер для вычесывания животных MARKETHOT',353,9,40,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (27,'Электропастух Польской компании AGRI. Выполнен из радиодеталей произведённых в Германии. Перед отправкой каждый электропастух проверяется на осциллографе. Это позволяет прировнять процент брака практически к нулю.Электропастух предназначен для выгула домашних животных и птиц (коров, лошадей, собак, кроликов, грызунов, куриц, гусей, домашних птиц), а также хорошо справляется с ограждением территории от этих животных.Электропастух абсолютно безопасен для здоровья человека и животных. В момент контакта животного с электроизгородью происходит только кратковременное ощущение боли, вызванное потоком электрического импульса.',
     'https://images.wbstatic.net/c516x688/new/26990000/26991800-1.jpg','Электропастух AGRI 4000',14000,9,5,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (28,'Невероятно удобная и стильная точилка BRAUBERG. Дизайн: офисный. Серия: Jet. Материал корпуса: пластик. Материал механизма: металл. Роликовый нож: да. Количество отверстий: 1. Наличие контейнера для стружки: да. Диаметр затачиваемого карандаша: 8 мм. Высота: 95 мм. Ширина: 45 мм. Глубина: 90 мм. Основной цвет корпуса: бордовый. Дополнительный цвет корпуса: черный. Упаковка: прозрачная ПВХ-коробка.',
     'https://images.wbstatic.net/c516x688/new/10950000/10951477-3.jpg','Точилка механическая для карандашей с контейнером для стружки Brauberg',299,10,50,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (29,'Художественные чернографитные карандаши Малевичъ GrafArt предназначены для рисования и отвечают всем требованиям предъявляемым к профессиональной художественной продукции. Высококачественный грифель очищен от примесей за счет чего штриховка получается равномерной и четкой. Диаметр грифеля отличается в зависимости от степени мягкости карандаша. Высококачественная древесина обеспечивает легкое затачивание, корпус и грифель не крошится и остаются целыми даже при падении карандаша. В коллекции 8 степеней мягкости: 2Н, Н, НВ, В, 2В, 4В, 6В, 8В.',
     'https://images.wbstatic.net/c516x688/new/9260000/9262630-1.jpg','Карандаш простой GrafArt Малевичъ',284,10,100,0);
insert into product (id, description, image_path, name, price, category_id, amount, popularity) values
    (30,'Один из самых знаменитых символов Италии, действующий вулкан Везувий, находящийся близ Неаполя. Печальная известность к нему пришла благодаря тому, что при извержениях под лавой погиб античный римский город Помпеи, а в 1944 году, Сан-Себастьяно.Извержение выбросило облако камней, пепла и вулканических газов на высоту 33 км, и более 1000 человек погибли. Компания Ancora представляет ручку Vezuvio, посвященную этому одному из наиболее опасных и загадочных вулканов.Ограниченная серия из 88 перьевых ручек и 88 роллеров. Материал: акриловая смола, позолота, фрагмент лавы, кольца на корпусе - формовочное литье, перо из золота 18 К. Коллекции Ancora сочетают в себе техническое и эстетическое совершенство. Отказ от массовости в пользу ручного труда обеспечил эксклюзивные решения и тщательно продуманные детали для каждой модели.',
     'https://images.wbstatic.net/c516x688/new/47400000/47401416-1.avif','Ручка ANCORA 1919',264810,10,20,0);


/*
На случай если захотим вернуть свою реализацию пользователей

insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (0,'devil@hell.com','devil','Сатана','Великий','муж', '2021-11-22 17:51:16.479536','ru','devil',null,null);

insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (1,'kirill@gmail.com','kirill','Кирилл','Кузнецов','муж', '2021-11-22 17:51:16.479536','ru','kirill',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (2,'ivan@gmail.com','ivan','Иван','Софронов','муж', '2021-11-22 17:51:16.479536','ru','ivan',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (3,'vasilina@gmail.com','vasilina','Василина','Сапрыкина','жен', '2021-11-22 17:51:16.479536','ru','vasilina',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (4,'sasha@gmail.com','sasha','Александр','Коромыслов','муж', '2021-11-22 17:51:16.479536','ru','sasha',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (5,'ilya@gmail.com','ilya','Илья','Волочинский','муж', '2021-11-22 17:51:16.479536','ru','ilya',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (6,'petr@gmail.com','petr','Пётр','Первый','муж', '2021-11-22 17:51:16.479536','ru','petr',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (7,'aidar@gmail.com','aidar','Айдар','Из Кулатки','муж', '2021-11-22 17:51:16.479536','ru','aidar',null,null);
insert into usr (id, email,login, first_name,second_name, gender, last_visit, locale,  password, phone,  userpic)
values (8,'lena@gmail.com','lena','Лена','Из Уфы','жен', '2021-11-22 17:51:16.479536','ru','lena',null,null);

insert into user_role (user_id, roles) VALUES (0,'USER');
insert into user_role (user_id, roles) VALUES (1,'USER');
insert into user_role (user_id, roles) VALUES (2,'USER');
insert into user_role (user_id, roles) VALUES (3,'USER');
insert into user_role (user_id, roles) VALUES (4,'USER');
insert into user_role (user_id, roles) VALUES (5,'USER');
insert into user_role (user_id, roles) VALUES (6,'USER');
insert into user_role (user_id, roles) VALUES (7,'USER');
insert into user_role (user_id, roles) VALUES (8,'USER');
insert into user_role (user_id, roles) VALUES (1,'MANAGER');
insert into user_role (user_id, roles) VALUES (2,'MANAGER');
insert into user_role (user_id, roles) VALUES (3,'MANAGER');
insert into user_role (user_id, roles) VALUES (4,'MANAGER');
insert into user_role (user_id, roles) VALUES (5,'MANAGER');
insert into user_role (user_id, roles) VALUES (1,'ADMIN');
insert into user_role (user_id, roles) VALUES (2,'ADMIN');
insert into user_role (user_id, roles) VALUES (3,'ADMIN');
insert into user_role (user_id, roles) VALUES (4,'ADMIN');

insert into orders values (1,'заранее позвонить','Мурманская область,городской округ Александровск, Оленья Губа, д3',TIMESTAMP '2021-12-31 15:30:00','PAID',5);
insert into orders values (2,'','Ульяновская область,ПГТ Старая Кулатка, д31',TIMESTAMP '2022-01-04 12:00:00','SENT',7);
insert into orders values (3,'звонить в домофон','г.Москва, Каширское шоссе, д13 к1 кв 32',TIMESTAMP '2021-12-29 18:00:00','PAID',8);
insert into orders values (4,'','г.Улан-Удэ, ул.Чкалова, д49 кв 13',TIMESTAMP '2022-02-18 10:00:00','SENT',0);
insert into orders values (5,'звонить в колокол','г.Ярославль, ул.Ленина, д60В кв 73',TIMESTAMP '2021-12-24 17:30:00','PAID',4);
 */

--все заказы привязаны к юзерам, так что добавим одного для примера
insert into usr values (0,'example_devil@hell.com',null,TIMESTAMP '2021-11-22 17:51:16.479536','ru',null,'Сатана',null,null,null);

insert into orders(id, comment, delivery_address, order_date, payment_id, status, user_id) values
    (1,'заранее позвонить','Мурманская область,городской округ Александровск, Оленья Губа, д3',TIMESTAMP '2021-12-31 15:30:00','','PAID',0);
insert into orders(id, comment, delivery_address, order_date, payment_id, status, user_id) values
    (2,'','Ульяновская область,ПГТ Старая Кулатка, д31',TIMESTAMP '2022-01-04 12:00:00','','SENT',0);
insert into orders(id, comment, delivery_address, order_date, payment_id, status, user_id) values
    (3,'звонить в домофон','г.Москва, Каширское шоссе, д13 к1 кв 32',TIMESTAMP '2021-12-29 18:00:00','','PAID',0);
insert into orders(id, comment, delivery_address, order_date, payment_id, status, user_id) values
    (4,'','г.Улан-Удэ, ул.Чкалова, д49 кв 13',TIMESTAMP '2022-02-18 10:00:00','','SENT',0);
insert into orders(id, comment, delivery_address, order_date, payment_id, status, user_id) values
    (5,'звонить в колокол','г.Ярославль, ул.Ленина, д60В кв 73',TIMESTAMP '2021-12-24 17:30:00','','PAID',0);

insert into order_item (id, order_id, product_id, count) values (1,2,1,1);
insert into order_item (id, order_id, product_id, count) values (2,1,1,2);
insert into order_item (id, order_id, product_id, count) values (3,1,2,3);
insert into order_item (id, order_id, product_id, count) values (4,5,2,4);
insert into order_item (id, order_id, product_id, count) values (5,2,3,1);
insert into order_item (id, order_id, product_id, count) values (6,1,3,1);
insert into order_item (id, order_id, product_id, count) values (7,2,3,2);
insert into order_item (id, order_id, product_id, count) values (8,3,3,5);
insert into order_item (id, order_id, product_id, count) values (9,5,3,3);
insert into order_item (id, order_id, product_id, count) values (10,3,4,10);
insert into order_item (id, order_id, product_id, count) values (11,1,4,7);
insert into order_item (id, order_id, product_id, count) values (12,3,4,4);
insert into order_item (id, order_id, product_id, count) values (13,2,4,1);
insert into order_item (id, order_id, product_id, count) values (14,1,5,1);
insert into order_item (id, order_id, product_id, count) values (15,5,5,2);
insert into order_item (id, order_id, product_id, count) values (16,1,5,1);
insert into order_item (id, order_id, product_id, count) values (17,3,5,1);
insert into order_item (id, order_id, product_id, count) values (18,4,1,3);
insert into order_item (id, order_id, product_id, count) values (19,1,2,17);
insert into order_item (id, order_id, product_id, count) values (20,3,1,20);

drop sequence com_seq;
create sequence com_seq start 50 increment 1;

ALTER TABLE category ADD CONSTRAINT name UNIQUE (name);
--Я добавила аннотацию на уникальность названия категории, но она не применилась
--(мб для этого надо дропать бд, поэтому изменяю тут напрямую
ALTER TABLE usr ADD CONSTRAINT login UNIQUE (login);




