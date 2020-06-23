package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;

import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.Reporte;
import org.ada.restapi.model.ReporteSearch;
import org.ada.restapi.model.LinkSearch;

import org.ada.restapi.model.Producto;
import org.ada.restapi.repository.EtailerRepository;
import org.ada.restapi.repository.LinkRepository;
import org.ada.restapi.repository.ReporteRepository;
import org.ada.restapi.repository.ReporteSearchRepository;

import org.ada.restapi.repository.LinkSearchRepository;

import org.ada.restapi.repository.ProductoRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

// Este controller se encarga de borrar los objetos de las bases de datos: Link, LinkSearch, Producto, Reporte, Reporte Search.
@RestController
@RequestMapping("/api/delete")
public class ControllerDelete {

	@Autowired
	private ReporteRepository reporteRepository;
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private ReporteSearchRepository reporteSearchRepository;
	@Autowired
	private LinkSearchRepository linkSearchRepository;

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private EtailerRepository etailerRepository;

	Log log = LogFactory.getLog(ControllerScrapSku.class);

	@DeleteMapping(path = "/Producto/{ean}")
	@Operation(summary = "Borrar Producto", description = "Borra un producto según el EAN")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteProducto(@PathVariable String ean) {
		log.info("metodo: deleteProducto " + ean);
		Optional<Producto> prod = productoRepository.findById(ean);
		if (!Optional.empty().equals(prod)) {
			try {
			productoRepository.deleteById(ean);
			} catch (Exception e) {
				return new ResponseEntity<>("No se puede eliminar el producto porque está siendo utilizado en otra tabla", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Producto " + ean + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El ean a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/Link/{id}")
	@Operation(summary = "Borrar LINK", description = "Borra un link según el id")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteLink(@PathVariable Integer id) {
		log.info("metodo: borrar Link por el id: " + id);
		Optional<Link> link = linkRepository.findById(id);
		if (!Optional.empty().equals(link)) {
			try {
			linkRepository.deleteById(id);
			} catch (Exception e) {
				return new ResponseEntity<>("No se puede eliminar el link porque está siendo usado en otra base", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Link " + id + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id de link a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/LinkSearch/{id}")
	@Operation(summary = "Borrar Link Search", description = "Borra un linkSearch según el id")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteLinkSearch(@PathVariable Integer id) {
		log.info("metodo: borrar LinkSearch x el id: " + id);
		Optional<LinkSearch> linkSearch = linkSearchRepository.findById(id);
		if (!Optional.empty().equals(linkSearch)) {
			try {
			linkSearchRepository.deleteById(id);
			} catch (Exception e) {
				return new ResponseEntity<>("No se puede eliminar el linkSearch porque está siendo usado en otra tabla", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("LinkSearch " + id + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id de linkSearch a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/ReporteSearch/{id}")
	@Operation(summary = "Borrar ReporteSearch", description = "Borra un ReporteSearch según el id")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteReporteSearch(@PathVariable Integer id) {
		log.info("metodo: borrar ReporteSearch x el id: " + id);
		Optional<ReporteSearch> reporteSearch = reporteSearchRepository.findById(id);
		if (!Optional.empty().equals(reporteSearch)) {

			reporteSearchRepository.deleteById(id);
			return new ResponseEntity<>("ReporteSearch " + id + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id de ReporteSearch a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/Reporte/{id}")
	@Operation(summary = "Borrar Reporte", description = "Borra un Reporte según el id")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteReporteh(@PathVariable Integer id) {
		log.info("metodo: borrar Reportex el id: " + id);
		Optional<Reporte> reporte = reporteRepository.findById(id);
		Reporte reporteBorrar = reporte.get();
		if (reporteBorrar != null) {
			reporteRepository.deleteById(id);
			return new ResponseEntity<>("Reporte " + id + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id de Reporte a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}

	@DeleteMapping(path = "/Etailer/{id}")
	@Operation(summary = "Borrar Etailer", description = "Borra un etailer según el id")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteEtailer(@PathVariable Integer id) {
		log.info("metodo: borrar Etailer por el id: " + id);
		Optional<Etailer> etailer = etailerRepository.findById(id);
		if (!Optional.empty().equals(etailer)) {
			try {
				etailerRepository.deleteById(id);
			} catch (Exception e) {
				return new ResponseEntity<>("No se puede eliminar el etailer porque está siendo usado en otra tabla", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Etailer " + id + " borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id de etailer a borrar no existe", HttpStatus.BAD_REQUEST);

		}

	}
}
