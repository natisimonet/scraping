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
@RequestMapping("/api/save_or_update")
public class ControllerPost {

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

	Log log = LogFactory.getLog(ControllerPost.class);

// agrega un link de forma unitaria
	@PostMapping(path = "/Link")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> guardarLink(@RequestBody Link link) {
		log.info("Guardar o Actualizar Link" + link);
		int id = link.getId();
		List<Link> busqueda = linkRepository.findById(id);
		if (CollectionUtils.isEmpty(busqueda)) {
			linkRepository.save(link);
			return new ResponseEntity<>("El link " + id + " fue creado", HttpStatus.CREATED);
		} else {
			linkRepository.save(link);
			return new ResponseEntity<>("El link " + id + " fue actualizado", HttpStatus.OK);
		}
	}

	// agrega un producto de forma unitaria
	@PostMapping(path = "/Producto")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
		log.info("Guardar o Actualizar Producto" + producto);
		String ean = producto.getEan();
		Optional<Producto> busqueda = productoRepository.findById(ean);
		if (!Optional.empty().equals(busqueda)) {
			productoRepository.save(producto);
			return new ResponseEntity<>("El producto " + ean + " fue actualizado", HttpStatus.OK);
		} else {
			productoRepository.save(producto);
			return new ResponseEntity<>("El producto " + ean + " fue creado", HttpStatus.CREATED);
		}
	}

	// agregar un objeto Search de forma unitaria
	@PostMapping(path = "/LinkSearch")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> guardarLink(@RequestBody LinkSearch linkSearch) {
		log.info("Guardar o Actualizar Search " + linkSearch);
		int id = linkSearch.getId();
		Optional<LinkSearch> busqueda = linkSearchRepository.findById(id);
		if (!Optional.empty().equals(busqueda)) {
			linkSearchRepository.save(linkSearch);
			return new ResponseEntity<>("El linkSearch " + id + " fue actualizado", HttpStatus.OK);
		} else {
			linkSearchRepository.save(linkSearch);
			return new ResponseEntity<>("El producto " + id + " fue creado", HttpStatus.CREATED);
		}
	}

	// agrega un etailer de forma unitaria
	@PostMapping(path = "/Etailer")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> guardarEtailer(@RequestBody Etailer etailer) {
		log.info("Guardar" + etailer);
		String nombre = etailer.getNombre();
		List<Etailer> busqueda = etailerRepository.findByNombre(nombre);
		if (CollectionUtils.isEmpty(busqueda)) {
			etailerRepository.save(etailer);
			return new ResponseEntity<>("El etailer " + etailer.getNombre() + " fue creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("El etailer " + etailer.getNombre() + " ya existe", HttpStatus.BAD_REQUEST);
		}
	}

	// agrega una categoría de forma unitaria
	@PostMapping(path = "/Categoria")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> guardarCategoria(@RequestBody Categoria categoria) {
		log.info("Guardar Categoria " + categoria);

		String nombre = categoria.getNombre();
		List<Categoria> busqueda = categoriaRepository.findByNombre(nombre);
		if (CollectionUtils.isEmpty(busqueda)) {
			categoriaRepository.save(categoria);
			return new ResponseEntity<>("La categoria " + categoria.getNombre() + " fue creado", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("La categoria " + categoria.getNombre() + " ya existe", HttpStatus.BAD_REQUEST);
		}
		
	}

	
}
