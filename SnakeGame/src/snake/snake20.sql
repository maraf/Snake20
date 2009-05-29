CREATE DATABASE snake20;

CREATE TABLE `highscores` (
		`id` INT NOT NULL AUTO_INCREMENT, 
		`name` TINYTEXT NOT NULL, 
		`level` INT NOT NULL, 
		`maze` INT NOT NULL, 
		`hs` INT NOT NULL, 
		PRIMARY KEY ( `id` ));
		
CREATE TABLE `mazes` (
		`id` INT NOT NULL AUTO_INCREMENT, 
		`name` TINYTEXT NOT NULL, 
		`path` TINYTEXT NOT NULL, 
		PRIMARY KEY ( `id` ));
		
INSERT INTO `mazes` (name, path) VALUES (\"No Maze\", \"nomaze.maze\");
INSERT INTO `mazes` (name, path) VALUES (\"Box\", \"box.maze\");
INSERT INTO `mazes` (name, path) VALUES (\"Circle\", \"circle.maze\");