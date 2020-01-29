INSERT INTO skill(name) VALUES ('Java');
INSERT INTO skill(name) VALUES ('Php');
INSERT INTO skill(name) VALUES ('Python');
INSERT INTO skill(name) VALUES ('C++');


INSERT INTO account(content, status) VALUES ('tinka@gmail.com', 'ACTIVE');
INSERT INTO account(content, status) VALUES ('romik@gmail.com', 'ACTIVE');
INSERT INTO account(content, status) VALUES ('luka@gmail.com', 'ACTIVE');
INSERT INTO account(content, status) VALUES ('kat@gmail.com', 'ACTIVE');
INSERT INTO account(content, status) VALUES ('tom@gmail.com', 'NONACTIVE');

INSERT INTO developer(name, account_id) VALUES ('Tinka', '1');
INSERT INTO developer(name, account_id) VALUES ('Leon', '3');
INSERT INTO developer(name, account_id) VALUES ('Kate', '4');
INSERT INTO developer(name, account_id) VALUES ('Roman', '2');
INSERT INTO developer(name, account_id) VALUES ('Tom', '5');

INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('2', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '1');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('4', '4');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('5', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('5', '3');

SELECT * FROM skill;
SELECT * FROM account;
SELECT * FROM developer;
SELECT * FROM skills_developers;

SELECT dev.id, dev.name, account.id,account.content, account.status, skill.id, skill.name
FROM developer AS dev
       JOIN skills_developers AS temp ON dev.id=developer_id
       JOIN skill AS skill ON temp.skill_id=skill.id
       JOIN account AS account ON dev.account_id=account.id;

SET GLOBAL time_zone = '+00:00';

SELECT MAX(id) AS max FROM developer