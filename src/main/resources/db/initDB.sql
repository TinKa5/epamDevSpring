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
    name VARCHAR(50) NOT NULL ,
    account_id INT ,
    FOREIGN KEY (account_id) references account (id) ON DELETE SET NULL
  );

CREATE TABLE skills_developers(
  developer_id INT NOT NULL ,
  skill_id INT NOT NULL ,
  FOREIGN KEY (developer_id) REFERENCES developer(id),
  FOREIGN KEY (skill_id) REFERENCES skill(id)
)