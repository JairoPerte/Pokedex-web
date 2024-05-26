-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema pokemon
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pokemon
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pokemonweb` DEFAULT CHARACTER SET utf8 ;
USE `pokemonweb`;

-- -----------------------------------------------------
-- Table `pokemon`.`pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokemonweb`.`pokemon` (
  `numPokedex` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` TINYTEXT NOT NULL,
  `tipo1` VARCHAR(45) NOT NULL,
  `tipo2` VARCHAR(45) NULL,
  `habilidad` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`numPokedex`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nombre_UNIQUE` ON `pokemonweb`.`pokemon` (`nombre` ASC);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `pokemon` (nombre,descripcion,tipo1,tipo2,habilidad) VALUES
	("Bulbasur","Una rara semilla fue plantada en su espalda al nacer. La planta brota y crece con este Pokémon.","planta","veneno","Espesura"),
    ("Ivysaur","Cuando el bulbo de su espalda crece, parece no poder ponerse de pie sobre sus patas traseras","planta","veneno","Espesura"),
    ("Venusaur","La planta florece cuando absorbe energía solar. Ésta le obliga a ponerse en busca de la luz solar.","planta","veneno","Espesura"),
    ("Charmander","Prefiere los sitios calientes. Dicen que cuando llueve sale vapor de la punta de su cola.","fuego",null,"Mar llamas"),
    ("Charmaleon","Cuando balancea su ardiente cola, eleva la temperatura a niveles muy altos.","fuego",null,"Mar llamas"),
    ("Charizard","Escupe fuego tan caliente que funde las rocas. Causa incendios forestales sin querer.","fuego","volador","Mar llamas"),
    ("Squirtle","Tras nacer, su espalda se hincha y endurece como una concha. Echa potente espuma por la boca.","agua",null,"Torrente"),
    ("Wartortle","Se oculta en el agua para cazar a sus presas. Al nadar rápidamente, mueve sus orejas para nivelarse.","agua",null,"Torrente"),
    ("Blastoise","Un brutal Pokémon con reactores de agua en su caparazón. Éstos son usados para rápidos placajes.","agua",null,"Torrente");
