INSERT INTO skill(id,name) VALUES (1,'Java');
INSERT INTO skill(id,name) VALUES (2,'Php');
INSERT INTO skill(id,name) VALUES (3,'Python');
INSERT INTO skill(id,name) VALUES (4,'HTML');


INSERT INTO account(id,content, status) VALUES (1,'tinka@gmail.com', 'ACTIVE');
INSERT INTO account(id, content, status) VALUES (2,'romik@gmail.com', 'ACTIVE');
INSERT INTO account(id,content, status) VALUES (3,'luka@gmail.com', 'ACTIVE');

INSERT INTO developer(id,name, account_id) VALUES (1,'Tinka', '1');
INSERT INTO developer(id,name, account_id) VALUES (2,'Leon', '3');
INSERT INTO developer(id,name, account_id) VALUES (3,'Kate', '2');


INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('1', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('2', '2');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '3');
INSERT INTO skills_developers(developer_id, skill_id) VALUES ('3', '1');

