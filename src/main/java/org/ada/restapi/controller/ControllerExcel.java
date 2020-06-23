package org.ada.restapi.controller;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.ada.restapi.model.Link;

import org.ada.restapi.model.LinkSearch;

import org.ada.restapi.model.Producto;

import org.ada.restapi.repository.LinkRepository;

import org.ada.restapi.service.ExcelUtil;

import org.ada.restapi.repository.LinkSearchRepository;

import org.ada.restapi.repository.ProductoRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//Esta clase guarda en las bases objetos leídos desde un excel. Únicamente para Producto, Link y Link Search.

@RestController
@RequestMapping("/api/excel")
public class ControllerExcel {

	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private LinkRepository linkRepository;
	@Autowired
	private LinkSearchRepository linkSearchRepository;

	@Autowired
	private ExcelUtil excelService;
	
	Log log = LogFactory.getLog(ControllerExcel.class);

	@PostMapping(path = "/addExcelProd")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> guardarProducto() throws EncryptedDocumentException, IOException, InvalidFormatException {
		log.info("Leer excel de Productos");
		List<Producto> n2 = excelService.productoDesdeExcel();
		productoRepository.saveAll(n2);
		return new ResponseEntity<>(n2, HttpStatus.CREATED);
	}

	@PostMapping(path = "/addExcelLink")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> guardarLink() throws EncryptedDocumentException, IOException, InvalidFormatException {
		log.info("Leer excel de Link");
		List<Link> n2 = excelService.linkSKUdesdeExcel();
		linkRepository.saveAll(n2);
		return new ResponseEntity<>(n2, HttpStatus.CREATED);
	}

	@PostMapping(path = "/addExcelSearch")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> guardarSearch() throws EncryptedDocumentException, IOException, InvalidFormatException {
		log.info("Leer excel de Search");
		List<LinkSearch> n2 = excelService.linkSearchdesdeExcel();
		linkSearchRepository.saveAll(n2);
		return new ResponseEntity<>(n2, HttpStatus.CREATED);
	}
}
