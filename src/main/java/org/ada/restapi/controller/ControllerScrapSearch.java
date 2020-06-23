package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;

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
import org.ada.restapi.util.Metodos;
import org.ada.restapi.util.MetodosRC;
import org.ada.restapi.util.MetodosRCsearch;
import org.ada.restapi.repository.LinkSearchRepository;

import org.ada.restapi.repository.ProductoRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Esta clase inicia los procesos automáticos de scrap search en los etailers y guardo los resultados en la tabla ReporteSearch.

@RestController
@RequestMapping("/api/scrap")
public class ControllerScrapSearch {

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
	private MetodosRCsearch metodorcS;
	@Autowired
	private MetodosRC metodorc;

	Log log = LogFactory.getLog(ControllerScrapSearch.class);


	// lanza el scrap según el etailer pasado por parámetro, guarda los resultados en la tabla.	
	@PostMapping(path = "/search/{id}")
	public ResponseEntity<Object> searchById(@PathVariable int id) throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(id);

		if (!Optional.empty().equals(etailer)) {
			Etailer e = etailer.get();
			String name = e.getNombre();
			switch (name) {
			case "Puppis":
				List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
				List<ReporteSearch> n = metodorcS.procesoSearchPuppis(listado);
				reporteSearchRepository.saveAll(n);

				return new ResponseEntity<>("Search: " + name + " finalizado", HttpStatus.CREATED);

			case "Sebastian":
				List<LinkSearch> listado1 = linkSearchRepository.findByEtailer(e);
				List<ReporteSearch> n1 = metodorcS.procesoSearchSebastian(listado1);
				reporteSearchRepository.saveAll(n1);
				return new ResponseEntity<>("Search: " + name + " finalizado", HttpStatus.CREATED);

			case "NaturalLife":
				List<LinkSearch> listado2 = linkSearchRepository.findByEtailer(e);
				List<ReporteSearch> n2 = metodorcS.procesoSearchNaturallife(listado2);
				reporteSearchRepository.saveAll(n2);
				return new ResponseEntity<>("Search: " + name + " finalizado", HttpStatus.CREATED);

			case "CanRock":
				List<LinkSearch> listado3 = linkSearchRepository.findByEtailer(e);
				List<ReporteSearch> n3 = metodorcS.procesoSearchCanRock(listado3);
				reporteSearchRepository.saveAll(n3);
				return new ResponseEntity<>("Search: " + name + " finalizado", HttpStatus.CREATED);

			}
			return new ResponseEntity<>("No hay proceso para realizar con ese etailer, comuníquese con el ADMIN", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("El num de etailer no existe", HttpStatus.BAD_REQUEST);

		}

	}
	
	//Desde acá se dejaron todos los procesos de scrap separados por etailer.

	@PostMapping(path = "/searchNaturalLife")
	public ResponseEntity<Object> searchNaturalLife() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(3);
		Etailer e = etailer.get();
		List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
		List<ReporteSearch> n = metodorcS.procesoSearchNaturallife(listado);
		reporteSearchRepository.saveAll(n);
		return new ResponseEntity<>("Search Natural Life Finalizado", HttpStatus.CREATED);
	}

	

	@PostMapping(path = "/searchPuppis")
	public ResponseEntity<Object> searchPuppis() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(1);
		Etailer e = etailer.get();
		List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
		List<ReporteSearch> n = metodorcS.procesoSearchPuppis(listado);
		reporteSearchRepository.saveAll(n);
		return new ResponseEntity<>("Search Puppis Finalizado", HttpStatus.CREATED);
	}

	@PostMapping(path = "/searchSebastian")
	public ResponseEntity<Object> searchSebastian() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(2);
		Etailer e = etailer.get();
		List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
		List<ReporteSearch> n = metodorcS.procesoSearchSebastian(listado);
		reporteSearchRepository.saveAll(n);
		return new ResponseEntity<>("Search Sebastian Finalizado", HttpStatus.CREATED);
	}

	@PostMapping(path = "/searchCanRock")
	public ResponseEntity<Object> searchCanRock() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(4);
		Etailer e = etailer.get();
		List<LinkSearch> listado = linkSearchRepository.findByEtailer(e);
		List<ReporteSearch> n = metodorcS.procesoSearchCanRock(listado);
		reporteSearchRepository.saveAll(n);
		return new ResponseEntity<>("Search CanRock Finalizado", HttpStatus.CREATED);
	}


}
