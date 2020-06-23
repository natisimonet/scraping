package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.collections4.CollectionUtils;

/* Este controller se encarga de agregar o actualizar los objetos de las bases de datos: Link, LinkSearch, Producto,
Categoría, Etailer, reporte y reporte Search.
*/

@RestController
@RequestMapping("/api/only_update")
public class ControllerPut {

	@Autowired
	private ReporteRepository reporteRepository;
	
	@Autowired
	private ReporteSearchRepository reporteSearchRepository;

	@Autowired
	private EtailerRepository etailerRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	Log log = LogFactory.getLog(ControllerPut.class);


	@PutMapping(path = "/Etailer")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> editarEtailer(@RequestBody Etailer etailer) {
		log.info("actualizar Etailer");
		int id = etailer.getId();
		Optional<Etailer> busqueda = etailerRepository.findById(id);
		if (!Optional.empty().equals(busqueda)) {
			etailerRepository.save(etailer);
			return new ResponseEntity<>("El etailer " + id + " fue actualizado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El etailer " + id + " no existe", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(path = "/Categoria")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> editarCategoria(@RequestBody Categoria categoria) {
		log.info("actualizar categoria");
		int id = categoria.getId();
		List<Categoria> busqueda = categoriaRepository.findById(id);
		if (!CollectionUtils.isEmpty(busqueda)) {
			categoriaRepository.save(categoria);
			return new ResponseEntity<>("La categoria " + id + " fue actualizado", HttpStatus.OK);
		} else {		
			return new ResponseEntity<>("La categoria " + id + " no existe", HttpStatus.BAD_REQUEST);
		}
	}

//probarlos
	@PutMapping(path = "/Reporte")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Reporte> editarReporte(@RequestBody Reporte reporte) {
		log.info("actualizar Reporte");
		reporteRepository.save(reporte);
		return new ResponseEntity<>(reporte, HttpStatus.OK);
	}

//probarlos
	@PutMapping(path = "/ReporteSearch")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ReporteSearch> editarReporteSearch(@RequestBody ReporteSearch reporte) {
		log.info("actualizar Reporte Search");
		reporteSearchRepository.save(reporte);
		return new ResponseEntity<>(reporte, HttpStatus.OK);
	}

}
