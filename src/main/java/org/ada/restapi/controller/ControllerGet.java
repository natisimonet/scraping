package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

import org.ada.restapi.model.Categoria;

import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.Reporte;
import org.ada.restapi.model.ReporteSearch;
import org.ada.restapi.model.LinkSearch;

import org.ada.restapi.model.Producto;
import org.ada.restapi.repository.CategoriaRepository;

import org.ada.restapi.repository.EtailerRepository;
import org.ada.restapi.repository.LinkRepository;
import org.ada.restapi.repository.ReporteRepository;
import org.ada.restapi.repository.ReporteSearchRepository;

import org.ada.restapi.repository.LinkSearchRepository;

import org.ada.restapi.repository.ProductoRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// Este controller se encarga de buscar y mostrar todas las tablas. 
@RestController
@RequestMapping("/api/obtener_listas")
public class ControllerGet {

	@Autowired
	private ReporteRepository reporteRepository;
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private ReporteSearchRepository reporteSearchRepository;
	@Autowired
	private LinkSearchRepository linkSearchRepository;
	@Autowired
	private EtailerRepository etailerRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProductoRepository productoRepository;

	Log log = LogFactory.getLog(ControllerGet.class);

	// devuelve el listado completo de la tabla Link
	@GetMapping(path = "/AllLink")
	@Operation(summary = "Listado Link", description = "Obtiene la lista completa de la Tabla Link")
	public ResponseEntity<List<Link>> getAllLink2() {
		log.info("Método Mostrar base Link");
		List<Link> listado = linkRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/LinkById/{id}")
	@Operation(summary = "Listado Link x Id", description = "Busca en la tabla link según el Id establecido")
	public ResponseEntity<Object> getLinkById(@PathVariable Integer id) {
		log.info("Busca y Muestra un Link por el id");
		Optional<Link> listado = linkRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/LinkByEtailer/{id}")
	@Operation(summary = "Listado Link x eTailer", description = "Busca en la tabla Link, filtrando por Etailer")
	public ResponseEntity<Object> getLinkByEtailer(@PathVariable Integer id) {
		log.info("Busca y muestra un listado de LinkSearch filtrado por etailer");
		Optional<Etailer> etailer = etailerRepository.findById(id);
		if (!Optional.empty().equals(etailer)) {
			Etailer e = etailer.get();
			List<Link> listado = linkRepository.findByEtailer(e);
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El id de etailer no existe o no hay resultados para ese id",
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/AllLinkSearch")
	@Operation(summary = "Listado Link Search", description = "Obtiene la lista completa de la Tabla Link")
	public ResponseEntity<List<LinkSearch>> getAllLinkSearch() {
		log.info("Método Mostrar base Link Search");
		List<LinkSearch> listado = linkSearchRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/LinkSearchById/{id}")
	public ResponseEntity<Object> getLinkSearchById(@PathVariable Integer id) {
		log.info("Busca y muestra un LinkSearch por el id");
		Optional<LinkSearch> listado = linkSearchRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/LinkSearchByEtailer/{id}")
	public ResponseEntity<Object> getLinkSearchByEtailer(@PathVariable Integer id) {
		log.info("Busca y muestra un listado de LinkSearch filtrado por etailer");
		Optional<Etailer> etailer = etailerRepository.findById(id);

		if (!Optional.empty().equals(etailer)) {
			Etailer e = etailer.get();

			List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de etailer no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/AllProducto")
	@Operation(summary = "Listado Producto", description = "Obtiene la lista completa de la Tabla Producto")
	public ResponseEntity<List<Producto>> getAllProducto() {
		log.info("Método Mostrar base Producto");
		List<Producto> listado = productoRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/ProductoById/{ean}")
	public ResponseEntity<Object> getProductoById(@PathVariable String ean) {
		log.info("Busca y muestra un producto por el ean");
		Optional<Producto> listado = productoRepository.findById(ean);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El ean ingresado no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/AllCategoria")
	@Operation(summary = "Listado Categoria", description = "Obtiene la lista completa de la Tabla Categoria")
	public ResponseEntity<List<Categoria>> getAllCategoria() {
		log.info("Mostrar tabla Categoria");
		List<Categoria> listado = categoriaRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/CategoriaById/{id}")
	public ResponseEntity<Object> getCategoriaById(@PathVariable Integer id) {
		log.info("Busca y muestra una categoría por el id");
		Optional<Categoria> listado = categoriaRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/AllEtailer")
	@Operation(summary = "Listado Etailer", description = "Obtiene la lista completa de la Tabla Etailer")
	public ResponseEntity<List<Etailer>> getAllEtailer() {
		log.info("Método Mostrar base Etailer");
		List<Etailer> listado = etailerRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/EtailerById/{id}")
	public ResponseEntity<Object> getEtailerById(@PathVariable Integer id) {
		log.info("Busca y Muestra un Etailer por el id");
		Optional<Etailer> listado = etailerRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/AllReporte")
	@Operation(summary = "Listado Reporte", description = "Obtiene la lista completa de la Tabla Reporte")
	public ResponseEntity<List<Reporte>> getAllReporte() {
		log.info("Método Mostrar base Reporte");
		List<Reporte> listado = reporteRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/ReporteById/{id}")
	public ResponseEntity<Object> getReporteById(@PathVariable Integer id) {
		log.info("Busca y Muestra un Reporte por el id");
		Optional<Reporte> listado = reporteRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = "/ReporteByPrecioMayor/{precio}")
	public ResponseEntity<Object> getReporteByPrecioMayor(@PathVariable double precio) {
		log.info("Busca y Muestra un Reporte por el id");
		List<Reporte> listado = reporteRepository.findByPrecioGreaterThanEqual(precio);
		if (CollectionUtils.isEmpty(listado)) {
			return new ResponseEntity<>("No hay resultados para ese parámetro", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping(path = "/AllReporteSearch")
	@Operation(summary = "Listado Search", description = "Obtiene la lista completa de la Tabla Search")
	public ResponseEntity<List<ReporteSearch>> getAllReporteSearch() {
		log.info("Método Mostrar base Reporte");
		List<ReporteSearch> listado = reporteSearchRepository.findAll();
		return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);

	}

	@GetMapping(path = "/ReporteSearchById/{id}")
	public ResponseEntity<Object> getReporteSearchById(@PathVariable Integer id) {
		log.info("Busca y Muestra un ReporteSearch por el id");
		Optional<ReporteSearch> listado = reporteSearchRepository.findById(id);
		if (!Optional.empty().equals(listado)) {
			return new ResponseEntity<>(listado, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("El num de id no existe", HttpStatus.BAD_REQUEST);
		}
	}
}
