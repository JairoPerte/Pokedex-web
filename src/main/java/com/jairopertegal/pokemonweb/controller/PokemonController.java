package com.jairopertegal.pokemonweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jairopertegal.pokemonweb.model.Pokemon;
import com.jairopertegal.pokemonweb.model.PokemonRepository;

@Controller
public class PokemonController {

	@Autowired
	private PokemonRepository pokemonR;

	/**
	 * Página principal que muestra a todos los pokemones
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String pokedex(Model model) {
		// Cargo todos los productos
		List<Pokemon> pokedex = pokemonR.findAll();

		// Asocio al atributo productos la lista de productos
		// cargada de BD
		model.addAttribute("pokedex", pokedex);

		// Cargamos la lista
		return "pokedex";
	}

	/**
	 * Cuando el usuario introduce cualquier filtro/orden se le
	 * redireccionará a esta página, aquí se comprobará el orden
	 * para modificarlo y el filtro para mostrar solo los pokemones
	 * del filtro introducido
	 * 
	 * @param model
	 * @param orden orden solicitado por el usuario
	 * @param filtro filtro solicitado por el usuario
	 * @return
	 */
	@GetMapping("/pokedex")
	public String listaOrdenada(Model model, @RequestParam(name = "orden", required = false) String orden,
			@RequestParam(name = "filtro", required = false) String filtro) {

		// Creamos la lista a devolver momentaneamente la asignamos null
		// pero no va a existir ninguno de los casos en los que este valor
		// se quede así
		List<Pokemon> pokedex = null;

		//Si el orden es "az" es orden alfabético
		if (orden != null && orden.equals("az")) {

			// Si ha insertado filtro o el filtro se ha introducido
			if (filtro != null && !filtro.equals("none")) {
				pokedex = pokemonR.pokemonTipoAlfabeto(filtro);
			} else {
				// No hace falta crear otra query personalizada ya que con el sort 
				// lo ordena y el ASC es como se debería de ordenar en este caso es 
				// de la a a la z
				pokedex = pokemonR.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
			}
			model.addAttribute("pokedex", pokedex);
			//Sino es orden numérico por tanto no hacemos nada
		} else {

			// Si ha insertado filtro o el filtro se ha introducido
			if (filtro != null && !filtro.equals("none")) {
				pokedex = pokemonR.pokemonTipo(filtro);
			} else {
				pokedex = pokemonR.findAll();
			}
			model.addAttribute("pokedex", pokedex);
		}
		return "pokedex";
	}

	/**
	 * Muestra los datos del pokemon seleccionado
	 * 
	 * @param numPokedex numero de pokedex del pokemon (id)
	 * @param model
	 * @return
	 */
	@GetMapping("/pokedex/{numPokedex}")
	public String pokemon(@PathVariable Long numPokedex, Model model) {

		// Cargamos un producto por ejemplo con el id 2 (puede
		// existir o no para eso ess el Optional)
		Optional<Pokemon> datoBD = pokemonR.findById(numPokedex);

		// Si el findbyid nos ha devuelto una entidad de tipo
		// producto
		// Se ejecuta lo que hay dentro de la funcion
		// En particular añade el producto al modelo para pasarselo
		// A la plantilla html thymeleaf como atributo
		if (datoBD.isPresent()) {
			model.addAttribute("pokemon", datoBD.get());
			return "pokemon";
		}

		model.addAttribute("error", "No se ha encontrado el id");
		return "pokemonNotFound";
	}

	/**
	 * Formulario para introducir un nuevo pokemon a la pokedex
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/pokedex/nuevo")
	public String nuevoPokemon(Model model) {
		// Creamos un objeto de tipo Entidad Producto
		Pokemon pokemon = new Pokemon();
		// Se lo pasamos al modelo para que llegue al formulario
		model.addAttribute("pokemon", pokemon);
		// template crearProducto
		return "crearPokemon";
	}

	/**
	 * Crea el pokemon con el formulario introducido
	 * 
	 * @param pokemon 			 pokemon a insertar a la BD
	 * @param redirectAttributes es un valor que envia al 
	 * 		  					 redireccionarse a una página
	 * @return
	 */
	@PostMapping("/pokedex/crear")
	public String crearPokemon(@ModelAttribute Pokemon pokemon, RedirectAttributes redirectAttributes) {
		if (pokemon.getNombre().isEmpty() || pokemon.getDescripcion().isEmpty() || pokemon.getHabilidad().isEmpty()) {
			// Esto es para luego mostrarle en un div personalizado como un menajito de error
			// No he podido encontrar como guardar los campos escritos anteriormente :(
			redirectAttributes.addFlashAttribute("mensajeError", "Por favor, completa todos los campos.");
			return "redirect:/pokedex/nuevo";
		}

		if (pokemon.getTipo2().isEmpty()) {
			pokemon.setTipo2(null);
		}
		pokemonR.save(pokemon);
		return "redirect:/";
	}

	/**
	 * Elimina el pokemon con el id introducido
	 * 
	 * @param numPokedex numero de pokedex del pokemon (id)
	 * @param model
	 * @return
	 */
	@GetMapping("pokedex/eliminar/{numPokedex}")
	public String eliminarPokemon(@PathVariable Long numPokedex, Model model) {

		// Nos Cargamos un producto por ejemplo con el id
		if (!pokemonR.findById(numPokedex).isPresent()) {
			model.addAttribute("error", "No se ha encontrado el id");
			return "pokemonNotFound";
		}
		pokemonR.deleteById(numPokedex);

		return "redirect:/";
	}

	/**
	 * Formulario para editar al pokemon de dicho id
	 * 
	 * @param numPokedex numero de pokedex del pokemon (id)
	 * @param model
	 * @return
	 */
	@GetMapping("/pokedex/editar/{numPokedex}")
	public String editarPokemon(@PathVariable Long numPokedex, Model model) {
		// Cargamos un producto por ejemplo con el id
		Optional<Pokemon> datoBD = pokemonR.findById(numPokedex);

		// Si el findbyid nos ha devuelto una entidad de tipo
		// producto
		// Se ejecuta lo que hay dentro de la funcion
		// En particular añade el producto al modelo para pasarselo
		// A la plantilla html thymeleaf como atributo
		if (datoBD.isPresent()) {
			model.addAttribute("pokemon", datoBD.get());
			return "editarPokemon";
		}

		model.addAttribute("error", "No se ha encontrado el id");
		return "pokemonNotFound";
	}

	/**
	 * Modifica el pokemon de dicho numero de Pokedex insertando
	 * un pokemon nuevo creado por el usuario y asignandole su id
	 * 
	 * @param numPokedex 		 numero de pokedex del pokemon (id)
	 * @param pokemon
	 * @param redirectAttributes es un valor que envia al 
	 * 		  					 redireccionarse a una página
	 * @return
	 */
	@PostMapping("/pokedex/modificar/{numPokedex}")
	public String modificarPokemon(@PathVariable Long numPokedex, @ModelAttribute Pokemon pokemon,
			RedirectAttributes redirectAttributes) {
		if (pokemon.getNombre().isEmpty() || pokemon.getDescripcion().isEmpty() || pokemon.getHabilidad().isEmpty()) {
			// Esto es para luego mostrarle en un div personalizado como un menajito de error
			// No he podido encontrar como guardar los campos escritos anteriormente :(
			redirectAttributes.addFlashAttribute("mensajeError", "Por favor, completa todos los campos.");
			return "redirect:/pokedex/editar/" + numPokedex;
		}

		if (pokemon.getTipo2().isEmpty()) {
			pokemon.setTipo2(null);
		}

		// Le asiganmos el id para saber que producto modificamos
		pokemon.setNumPokedex(numPokedex);

		// Modificamos el producto
		pokemonR.save(pokemon);
		// Redirecionamos al listado
		return "redirect:/";
	}

}
