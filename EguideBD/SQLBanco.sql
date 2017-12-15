CREATE DATABASE `eguide` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `nomeAbnt` varchar(30) NOT NULL,
  PRIMARY KEY (`id_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `autor_livro` (
  `id_livro` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  PRIMARY KEY (`id_livro`,`id_autor`),
  KEY `FK_r89g87uquys0vxjy7eejnhc6r` (`id_autor`),
  CONSTRAINT `FK_cauqcmor9njaj8c6aejx8jqxc` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id_livro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_r89g87uquys0vxjy7eejnhc6r` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `avaliacao` (
  `id_avaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(45) DEFAULT NULL,
  `nota` double NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_livro` int(11) NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  KEY `fk_avaliacao_usuario1_idx` (`id_usuario`),
  KEY `fk_avaliacao_livro1_idx` (`id_livro`),
  CONSTRAINT `fk_avaliacao_livro1` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id_livro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_avaliacao_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `editora` (
  `id_editora` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id_editora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `estante` (
  `id_livro` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  PRIMARY KEY (`id_livro`,`id_usuario`),
  KEY `fk_estante_livro1_idx` (`id_livro`),
  KEY `fk_estante_usuario1_idx` (`id_usuario`),
  KEY `fk_estante_status1_idx` (`id_status`),
  CONSTRAINT `fk_estante_livro1` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id_livro`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_estante_status1` FOREIGN KEY (`id_status`) REFERENCES `statuslivro` (`id_status`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_estante_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genero` (
  `id_genero` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `idioma` (
  `id_idioma` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `sigla` varchar(10) NOT NULL,
  PRIMARY KEY (`id_idioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `livro` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `ano` int(11) NOT NULL,
  `descricao` text,
  `edicao` int(11) NOT NULL,
  `isbn10` int(11) NOT NULL,
  `isbn13` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `paginas` int(11) NOT NULL,
  `editora_id_editora` int(11) DEFAULT NULL,
  `idioma_id_idioma` int(11) DEFAULT NULL,
  `origem_id_origem` int(11) DEFAULT NULL,
  `subgenero_id_subgenero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_livro`),
  UNIQUE KEY `UK_8nx3b9u90aks6sn5uffw2g5qw` (`isbn10`),
  UNIQUE KEY `UK_9okpj7wevqhajuogepigx2bg` (`isbn13`),
  KEY `FK_1y6hwrpf6ky0bhdo9alqooxwh` (`idioma_id_idioma`),
  KEY `FK_ejvn7mw5pcbpyei35dhqaw6tu` (`editora_id_editora`),
  KEY `FK_gr5pbs1djlv7iqwns7igg6w10` (`subgenero_id_subgenero`),
  KEY `FK_pbxbjjuc6oxevf4g3ulysh9fl` (`origem_id_origem`),
  CONSTRAINT `FK_1y6hwrpf6ky0bhdo9alqooxwh` FOREIGN KEY (`idioma_id_idioma`) REFERENCES `idioma` (`id_idioma`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ejvn7mw5pcbpyei35dhqaw6tu` FOREIGN KEY (`editora_id_editora`) REFERENCES `editora` (`id_editora`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_gr5pbs1djlv7iqwns7igg6w10` FOREIGN KEY (`subgenero_id_subgenero`) REFERENCES `subgenero` (`id_subgenero`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_pbxbjjuc6oxevf4g3ulysh9fl` FOREIGN KEY (`origem_id_origem`) REFERENCES `origem` (`id_origem`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `nivel_usuario` (
  `id_usuario` int(11) NOT NULL,
  `id_nivel` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_nivel`),
  KEY `FK_a09lvq50wu47vwcks68n8fwh4` (`id_nivel`),
  CONSTRAINT `FK_8ig9vtotuciig3ixpcwhtu8im` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_a09lvq50wu47vwcks68n8fwh4` FOREIGN KEY (`id_nivel`) REFERENCES `nivelacesso` (`id_nivel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `nivelacesso` (
  `id_nivel` int(11) NOT NULL AUTO_INCREMENT,
  `nivel` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id_nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `origem` (
  `id_origem` int(11) NOT NULL AUTO_INCREMENT,
  `origem` varchar(25) NOT NULL,
  PRIMARY KEY (`id_origem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `statuslivro` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subgenero` (
  `id_subgenero` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `genero_id_genero` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_subgenero`),
  KEY `FK_jahv9mt1qfukvao5yl0mun98o` (`genero_id_genero`),
  CONSTRAINT `FK_jahv9mt1qfukvao5yl0mun98o` FOREIGN KEY (`genero_id_genero`) REFERENCES `genero` (`id_genero`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `emailSec` varchar(100) DEFAULT NULL,
  `nascimento` date NOT NULL,
  `nome` varchar(30) NOT NULL,
  `senha` varchar(40) NOT NULL,
  `sobrenome` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_4tdehxj7dh8ghfc68kbwbsbll` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `usuario` (`email`,`emailSec`,`nascimento`,`nome`,`senha`,`sobrenome`) VALUES ('daniel@gmail.com',NULL,'1950-02-22','Daniel','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','Silva');
INSERT INTO `usuario` (`email`,`emailSec`,`nascimento`,`nome`,`senha`,`sobrenome`) VALUES ('samuel@gmail.com',NULL,'2000-01-11','Samuel','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','Borges');

INSERT INTO `nivelacesso` (`nivel`,`descricao`) VALUES ('admin','Administrador');
INSERT INTO `nivelacesso` (`nivel`,`descricao`) VALUES ('user','Usuario');

INSERT INTO `nivel_usuario` (`id_usuario`,`id_nivel`) VALUES (1,1);
INSERT INTO `nivel_usuario` (`id_usuario`,`id_nivel`) VALUES (2,2);
INSERT INTO `nivel_usuario` (`id_usuario`,`id_nivel`) VALUES (1,2);