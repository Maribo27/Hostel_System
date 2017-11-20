CREATE DATABASE IF NOT EXISTS hostel_system;
USE hostel_system;

CREATE USER IF NOT EXISTS 'epam_student'@'localhost' IDENTIFIED BY 'stud';
GRANT ALL ON hostel_system.* TO 'epam_student'@'localhost';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS login_data;
DROP TABLE IF EXISTS users;

CREATE TABLE login_data (
  username varchar(20) NOT NULL,
  password varchar(32) NOT NULL,
  email varchar(20) NOT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO login_data VALUES
  ('admin','21232f297a57a5a743894a0e4a801fc3','admin@admin.by'),
  ('ban_user','f290be7451fc10290212ee59f450a70b','banned@temp.by'),
  ('maribo','d5cbfe9ff07fef1ecc93861ce5dd4f3b','maribo@gmail.com'),
  ('user','5f4dcc3b5aa765d61d8327deb882cf99','user@temp.by');

CREATE TABLE users (
  username varchar(20) NOT NULL,
  name varchar(15) NOT NULL,
  discount smallint(6) NOT NULL,
  status enum('administrator','user','banned') DEFAULT NULL,
  PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users VALUES
  ('admin','Administrator',100,'administrator'),
  ('ban_user','User',0,'banned'),
  ('maribo','Maria',0,'user'),
  ('user','User',0,'user');