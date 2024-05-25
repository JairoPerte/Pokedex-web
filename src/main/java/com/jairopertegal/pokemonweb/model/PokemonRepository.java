package com.jairopertegal.pokemonweb.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	@Query("SELECT p FROM Pokemon p WHERE p.tipo1 = :tipo OR p.tipo2 = :tipo")
	List<Pokemon> pokemonTipo(@Param("tipo") String tipo);

	@Query("SELECT p FROM Pokemon p WHERE p.tipo1 = :tipo OR p.tipo2 = :tipo order by p.nombre ASC")
	List<Pokemon> pokemonTipoAlfabeto(@Param("tipo") String tipo);
}
