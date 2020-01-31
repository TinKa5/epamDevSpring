DROP TABLE IF EXISTS skills_developers;
DROP TABLE IF EXISTS developer;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS skill;

CREATE TABLE skill(
  id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(30) NOT NULL
);

 CREATE TABLE account(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    content VARCHAR(250) NOT NULL ,
    status VARCHAR(30) NOT NULL
   );

  CREATE TABLE developer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    account_id INT REFERENCES account(id)
  );

CREATE TABLE skills_developers(
  developer_id INT NOT NULL REFERENCES  developer(id),
  skill_id INT NOT NULL REFERENCES skill(id)
)