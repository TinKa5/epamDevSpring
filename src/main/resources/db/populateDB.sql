INSERT INTO skill(id,name) VALUES (1,'Java');
INSERT INTO skill(id,name) VALUES (2,'Php');
INSERT INTO skill(id,name) VALUES (3,'Python');
INSERT INTO skill(id,name) VALUES (4,'C++');

INSERT INTO account(id,content, status) VALUES (1,'tinka@gmail.com', 'ACTIVE');
INSERT INTO account(id, content, status) VALUES (2,'romik@gmail.com', 'ACTIVE');
INSERT INTO account(id,content, status) VALUES (3,'luka@gmail.com', 'ACTIVE');
INSERT INTO account(id,content, status) VALUES (4,'kat@gmail.com', 'ACTIVE');
INSERT INTO account(id,content, status) VALUES (5,'tom@gmail.com', 'NONACTIVE');

INSERT INTO developer(id,name, account_id) VALUES (1,'Tinka', '1');
INSERT INTO developer(id,name, account_id) VALUES (2,'Leon', '3');
INSERT INTO developer(id,name, account_id) VALUES (3,'Kate', '4');
INSERT INTO developer(id,name, account_id) VALUES (4,'Roman', '2');
INSERT INTO developer(id,name, account_id) VALUES (5,'Tom', '5');

INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('2', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '1');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('4', '4');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('5', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('5', '3');

SELECT dev.id, dev.name, ac.id,ac.content, ac.status, sk.id, sk.name
FROM developer dev left JOIN skills_developers temp ON dev.id=temp.developer_id
                   left JOIN skill sk ON temp.skill_id=sk.id
                                left JOIN account ac ON dev.account_id=ac.id