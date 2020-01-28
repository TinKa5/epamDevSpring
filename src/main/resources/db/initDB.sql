DROP TABLE IF EXISTS skills_developers;
DROP TABLE IF EXISTS developers;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS skills;
CREATE TABLE skills(
  id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(30) NOT NULL
);

 CREATE TABLE account(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    content VARCHAR(250) NOT NULL ,
    status VARCHAR(30) NOT NULL
   );

  CREATE TABLE developers(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    account_id INT ,
    FOREIGN KEY (account_id) references account (id) ON DELETE SET NULL
  );

CREATE TABLE skills_developers(
  developer_id INT NOT NULL ,
  skill_id INT NOT NULL ,
  PRIMARY KEY (developer_id,skill_id),
  FOREIGN KEY (developer_id) REFERENCES developers(id) ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE
)