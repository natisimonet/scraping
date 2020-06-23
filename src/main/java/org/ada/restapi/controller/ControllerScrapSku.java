package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.Reporte;
import org.ada.restapi.repository.EtailerRepository;
import org.ada.restapi.repository.LinkRepository;
import org.ada.restapi.repository.ReporteRepository;
import org.ada.restapi.util.MetodosRC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Esta clase inicia los procesos automáticos de scrap en los etailers y los guarda en la tabla Reporte.

@RestController
@RequestMapping("/api/scrapSku")
public class ControllerScrapSku {

	@Autowired
	private ReporteRepository reporteRepository;
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private EtailerRepository etailerRepository;
	@Autowired
	private MetodosRC metodorc;

	Log log = LogFactory.getLog(ControllerScrapSku.class);

	// lanza el scrap según el etailer pasado por parámetro, guarda los resultados en la tabla.
	@PostMapping(path = "/sku/{id}")
	public ResponseEntity skuById(@PathVariable int id) throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(id);

		if (!Optional.empty().equals(etailer)) {
			Etailer e = etailer.get();
			String name = e.getNombre();
			switch (name) {
			case "Puppis":
				List<Link> listado = linkRepository.findByEtailer(e);
				List<Reporte> n = metodorc.procesoPuppis(listado);
				reporteRepository.saveAll(n);

				return new ResponseEntity<>("Scrap SKUs de: " + name + " finalizado", HttpStatus.CREATED);

			case "Sebastian":
				List<Link> listado1 = linkRepository.findByEtailer(e);
				List<Reporte> n1 = metodorc.procesoSebastian2(listado1);
				reporteRepository.saveAll(n1);
				return new ResponseEntity<>("Scrap SKUs de: " + name + " finalizado", HttpStatus.CREATED);

			case "NaturalLife":
				List<Link> listado2 = linkRepository.findByEtailer(e);
				List<Reporte> n2 = metodorc.procesoNaturalLife(listado2);
				reporteRepository.saveAll(n2);

				return new ResponseEntity<>("Scrap SKUs de: " + name + " finalizado", HttpStatus.CREATED);

			case "CanRock":
				List<Link> listado3 = linkRepository.findByEtailer(e);
				List<Reporte> n3 = metodorc.procesoCanRock(listado3);
				reporteRepository.saveAll(n3);
				return new ResponseEntity<>("Scrap SKUs de: " + name + " finalizado", HttpStatus.CREATED);

			}
			return new ResponseEntity<>("No hay proceso para realizar con ese etailer, comuníquese con el ADMIN", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("El id de etailer no existe", HttpStatus.BAD_REQUEST);

		}

	}
	
// Desde acá se dejaron los métodos para lanzar el scrap directo de cada etailer.
	
	@PostMapping(path = "/naturalLife")
	public ResponseEntity<Object> naturalLife() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(3);
		Etailer e = etailer.get();
		List<Link> listado = linkRepository.findByEtailer(e);
		List<Reporte> n = metodorc.procesoNaturalLife(listado);
		reporteRepository.saveAll(n);
		return new ResponseEntity<>("Scrap productos Natural Life Finalizado", HttpStatus.CREATED);
	}


	@PostMapping(path = "/sebastian")
	public ResponseEntity sebastian() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(2);
		Etailer e = etailer.get();
		List<Link> listado = linkRepository.findByEtailer(e);
		List<Reporte> n = metodorc.procesoSebastian2(listado);
		reporteRepository.saveAll(n);
		return new ResponseEntity<>("Scrap productos Sebastian Finalizado", HttpStatus.CREATED);
	}

	@PostMapping(path = "/canRock")
	public ResponseEntity canRock() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(4);
		Etailer e = etailer.get();
		List<Link> listado = linkRepository.findByEtailer(e);
		List<Reporte> n = metodorc.procesoCanRock(listado);
		reporteRepository.saveAll(n);
		return new ResponseEntity<>("Scrap productos CanRock Finalizado", HttpStatus.CREATED);
	}

	
	@PostMapping(path = "/puppis")
	public ResponseEntity puppis() throws IOException {
		Optional<Etailer> etailer = etailerRepository.findById(1);
		Etailer e = etailer.get();
		List<Link> listado = linkRepository.findByEtailer(e);
		List<Reporte> n = metodorc.procesoPuppis(listado);
		reporteRepository.saveAll(n);
		return new ResponseEntity<>("Scrap productos Puppis Finalizado", HttpStatus.CREATED);
	}
	

}
