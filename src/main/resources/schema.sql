CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(200) NOT NULL,
    status varchar(50)
);

INSERT INTO tasks (name, description, status) VALUES ('store', 'milk, bread, cola', 'in process');