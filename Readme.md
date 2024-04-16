  ## Задание
#### 1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы), а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).

        dm@dm-VirtualBox:~$ cat > pets
        Собаки 
        Кошки
        Хомяки
        dm@dm-VirtualBox:~$ cat > pack_animals
        Лошади
        Верблюды
        Ослы
        dm@dm-VirtualBox:~$ cat pets pack_animals > all_pets
        dm@dm-VirtualBox:~$ cat all_pets
        Собаки
        Кошки
        Хомяки
        Лошади
        Верблюды
        Ослы
        dm@dm-VirtualBox:~$ mv all_pets people_friends

#### 2. Создать директорию, переместить файл туда.

        dm@dm-VirtualBox:~$ mkdir animals
        dm@dm-VirtualBox:~$ mv people_friends animals

#### 3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.

        dm@dm-VirtualBox:~$ sudo apt install mysql-server

####  4. Установить и удалить deb-пакет с помощью dpkg.

        dm@dm-VirtualBox:~$ wget http://archive.ubuntu.com/ubuntu/pool/main/t/tnftp/ftp_20230507-2_all.deb
        dm@dm-VirtualBox:~$ sudo dpkg -i ftp_20230507-2_all.deb
        dm@dm-VirtualBox:~$ sudo dpkg -r ftp

#### 5. Выложить историю команд в терминале ubuntu

История команд:

        250  cat > pets
        251  cat > pack_animals
        252  cat pets pack_animals > all_pets
        253  cat all_pets
        254  mv all_pets people_friends
        255  mkdir animals
        256  mv people_friends animals
        257  sudo nano /etc/apt/sources.list.d/mysql.list
        258  sudo apt update
        259  sudo apt install mysql-server
        260  wget http://archive.ubuntu.com/ubuntu/pool/main/t/tnftp/ftp_20230507-2_all.deb
        261  sudo dpkg -i ftp_20230507-2_all.deb
        262  sudo dpkg -r ftp
        263  history

#### 6. Нарисовать диаграмму, в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы).

![Диаграмма](Diagr.png)

#### 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”
```sql
CREATE DATABASE human_friends;
```
#### 8. Создать таблицы с иерархией из диаграммы в БД

```sql
USE human_friends;
CREATE TABLE `animals` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`type` ENUM('Домашние', 'Вьючные'),
	PRIMARY KEY (`id`)
);

CREATE TABLE `home_animals` (
	`id` INT(11) NULL,
	`name_animals` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `pack_animals` (
	`id` INT(11) NULL,
	`name_animals` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `собаки` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `home_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `кошки` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `home_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `хомяки` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `home_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `лошади` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `pack_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `верблюды` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `pack_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE `ослы` (
	`id` INT(11) NULL,
	`name` TEXT NULL,
	`date` DATE NULL,
	`commands` TEXT NULL,
	FOREIGN KEY (`id`) REFERENCES `pack_animals` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);


```
#### 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

```sql
INSERT INTO human_friends.animals (`type`) VALUES ('Домашние');
INSERT INTO human_friends.home_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Собака');
INSERT INTO human_friends.`собаки` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Мухтар', '2023-01-20', 'Фас');

INSERT INTO human_friends.animals (`type`) VALUES ('Домашние');
INSERT INTO human_friends.home_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Кошка');
INSERT INTO human_friends.`кошки` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Пушок', '2021-06-09', 'Кис-Кис');

INSERT INTO human_friends.animals (`type`) VALUES ('Домашние');
INSERT INTO human_friends.home_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Хомяк');
INSERT INTO human_friends.`хомяки` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Рыжик', '2023-11-21', '');

INSERT INTO human_friends.animals (`type`) VALUES ('Вьючные');
INSERT INTO human_friends.pack_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Лошадь');
INSERT INTO human_friends.`лошади` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Плотва', '2020-03-15', 'Шевелись');

INSERT INTO human_friends.animals (`type`) VALUES ('Вьючные');
INSERT INTO human_friends.pack_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Верблюд');
INSERT INTO human_friends.`верблюды` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Омар', '2018-04-08', 'Каш');

INSERT INTO human_friends.animals (`type`) VALUES ('Вьючные');
INSERT INTO human_friends.pack_animals (`id`, `name_animals`) VALUES (LAST_INSERT_ID(), 'Осел');
INSERT INTO human_friends.`ослы` (`id`, `name`, `date`, `commands`) VALUES (LAST_INSERT_ID(), 'Дженни', '2022-11-30', 'Иди');
```
#### 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.

Удалим всех верблюдов:

```sql
DELETE FROM `animals` WHERE id IN (SELECT id FROM `верблюды`);
```
Объединим таблицы лошади, и ослы в одну таблицу.
```sql
SELECT * FROM `лошади`
UNION
SELECT * FROM `ослы`
```
#### 11.Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице 

```sql
CREATE TEMPORARY TABLE `young_animals` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`animal_type` ENUM('собаки', 'кошки', 'хомяки', 'лошади', 'верблюды', 'ослы'),
	`name` TEXT NULL,
	`age_months` DECIMAL(5,2),
	PRIMARY KEY (`id`)
);

INSERT INTO `young_animals` (`animal_type`, `name`, `age_months`)
SELECT animal_type, name, TIMESTAMPDIFF(MONTH,`date`,CURDATE()) AS age_months FROM 
    (SELECT 'собаки' as animal_type, `name`, `date` FROM `собаки`
     UNION ALL SELECT 'кошки', `name`, `date` FROM `кошки`
     UNION ALL SELECT 'хомяки', `name`, `date` FROM `хомяки`
     UNION ALL SELECT 'лошади', `name`, `date` FROM `лошади`
     UNION ALL SELECT 'верблюды', `name`, `date` FROM `верблюды`
     UNION ALL SELECT 'ослы', `name`, `date` FROM `ослы`
    ) AS animals
WHERE `date` BETWEEN DATE_SUB(NOW(), INTERVAL 3 YEAR) AND DATE_SUB(NOW(), INTERVAL 1 YEAR);

SELECT * FROM young_animals;
```

#### 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

```sql
SELECT 
    a.id AS animal_id,
    a.type AS animal_type,
    ha.id AS home_animal_id,
    ha.name_animals AS home_animal_name,
    pa.id AS pack_animal_id,
    pa.name_animals AS pack_animal_name,
    d.id AS dog_id,
    d.name AS dog_name,
    d.date AS dog_date,
    d.commands AS dog_commands,
    c.id AS cat_id,
    c.name AS cat_name,
    c.date AS cat_date,
    c.commands AS cat_commands,
    h.id AS hamster_id,
    h.name AS hamster_name,
    h.date AS hamster_date,
    h.commands AS hamster_commands,
    ho.id AS horse_id,
    ho.name AS horse_name,
    ho.date AS horse_date,
    ho.commands AS horse_commands,
    v.id AS camel_id,
    v.name AS camel_name,
    v.date AS camel_date,
    v.commands AS camel_commands,
    o.id AS donkey_id,
    o.name AS donkey_name,
    o.date AS donkey_date,
    o.commands AS donkey_commands
FROM animals a
LEFT JOIN home_animals ha ON a.id = ha.id
LEFT JOIN pack_animals pa ON a.id = pa.id
LEFT JOIN собаки d ON ha.id = d.id
LEFT JOIN кошки c ON ha.id = c.id
LEFT JOIN хомяки h ON ha.id = h.id
LEFT JOIN лошади ho ON pa.id = ho.id
LEFT JOIN верблюды v ON pa.id = v.id
LEFT JOIN ослы o ON pa.id = o.id;
```

#### 13.Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
```java
public class Animal {
    private String name;
    private String dateOfBirth;

    public Animal(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

public class HomeAnimals extends Animal {
    private String command;

    public HomeAnimals(String name, String dateOfBirth, String command) {
        super(name, dateOfBirth);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}

public class PackAnimals extends Animal {
    public PackAnimals(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
}

public class Dog extends HomeAnimals {
    public Dog(String name, String dateOfBirth, String command) {
        super(name, dateOfBirth, command);
    }
}

public class Cat extends HomeAnimals {
    public Cat(String name, String dateOfBirth, String command) {
        super(name, dateOfBirth, command);
    }
}

public class Hamster extends HomeAnimals {
    public Hamster(String name, String dateOfBirth, String command) {
        super(name, dateOfBirth, command);
    }
}

public class Horse extends PackAnimals {
    public Horse(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
}

public class Camel extends PackAnimals {
    public Camel(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
}

public class Donkey extends PackAnimals {
    public Donkey(String name, String dateOfBirth) {
        super(name, dateOfBirth);
    }
}
```
---
---
#### 14. Написать программу, имитирующую работу реестра домашних животных. В программе должен быть реализован следующий функционал:
#### 14.1 Завести новое животное
#### 14.2 определять животное в правильный класс
#### 14.3 увидеть список команд, которое выполняет животное
#### 14.4 обучить животное новым командам
#### 14.5 Реализовать навигацию по меню
#### 15.Создайте класс Счетчик, у которого есть метод add(), увеличивающий значение внутренней int переменной на 1 при нажатие “Завести новое животное” Сделайте так, чтобы с объектом такого типа можно было работать в блоке try-with-resources. Нужно бросить исключение, если работа с объектом типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение считать в ресурсе try, если при заведения животного заполнены все поля.
