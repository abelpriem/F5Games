CREATE TABLE IF NOT EXISTS characters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    strength INT NOT NULL,
    health INT NOT NULL
);

INSERT INTO characters (name,strength,health) VALUES ('Aiden',15,100);
INSERT INTO characters (name,strength,health) VALUES ('Skeleton',5,100);
INSERT INTO characters (name,strength,health) VALUES ('Ghost',7,100);
INSERT INTO characters (name,strength,health) VALUES ('Vampire',10,100);
INSERT INTO characters (name,strength,health) VALUES ('Mortis',50,100);

