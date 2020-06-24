package org.ada.restapi.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ada.restapi.model.Categoria;
import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.LinkSearch;
import org.ada.restapi.model.Producto;
import org.ada.restapi.repository.CategoriaRepository;
import org.ada.restapi.repository.EtailerRepository;

import org.ada.restapi.repository.ProductoRepository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Si bien es un service debería ponerlo en el paquete Util
@Service
public class ExcelUtil {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	EtailerRepository etailerRepository;

//@Autowired
	// public ExcelService(MarcaRepository marcaRepository) {
	// this.marcaRepository = marcaRepository;

	// }

	// Log log
	public static final String PRODUCTO_BASE_PATH = "C:\\Users\\tati0\\OneDrive\\Escritorio\\RC\\BasesTodas2.xlsx";
	public static final String LINKS_SKU_BASE_PATH = "C:\\Users\\tati0\\OneDrive\\Escritorio\\RC\\LinkSku.xlsx";
	public static final String SEARCH_BASE_PATH = "C:\\Users\\tati0\\OneDrive\\Escritorio\\RC\\BaseSearch.xlsx";

	public List<Producto> productoDesdeExcel() throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<Producto> n2 = new ArrayList<>();

		Workbook workbook = WorkbookFactory.create(new File(PRODUCTO_BASE_PATH));
		Sheet sheet = workbook.getSheetAt(0);
		int rowStart = Math.min(15, sheet.getFirstRowNum());
		int rowEnd = sheet.getLastRowNum();

		for (int rowNum = rowStart + 1; rowNum < rowEnd + 1; rowNum++) {
			Producto n1 = new Producto();
			Row r = sheet.getRow(rowNum);
			int r2 = r.getRowNum();
			int lastColumn = r.getLastCellNum();
			for (int cn = 0; cn < lastColumn; cn++) {
				// asigno los datos a la base según la columna
				if (cn == 0) {
					Cell c = r.getCell(cn);
					String ean = c.getStringCellValue();
					n1.setEan(ean);

				} else if (cn == 1) {
					Cell c = r.getCell(cn);
					String marca = c.getStringCellValue();
					n1.setMarca(marca);

				} else if (cn == 2) {
					Cell c = r.getCell(cn);
					String compania = c.getStringCellValue();
					n1.setCompania(compania);

				} else if (cn == 3) {
					Cell c = r.getCell(cn);
					String titulo = c.getStringCellValue();
					n1.setTitulo(titulo);

				} else if (cn == 4) {
					Cell c = r.getCell(cn);
					double kg = c.getNumericCellValue();
					n1.setKg(kg);

				} else if (cn == 5) {
					Cell c = r.getCell(cn);
					double category = c.getNumericCellValue();
					int categoryInt = (int) category;
					List<Categoria> cat = categoriaRepository.findById(categoryInt);
					Categoria cat2 = cat.get(0);
					n1.setCategoria(cat2);

				} else if (cn == 6) {
					Cell c = r.getCell(cn);
					double precio = c.getNumericCellValue();
					n1.setPrecioSugerido(precio);
				}

			}
			n2.add(n1);
		}
		return n2;
	}

	public List<Link> linkSKUdesdeExcel() throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<Link> n2 = new ArrayList<>();

		Workbook workbook = WorkbookFactory.create(new File(LINKS_SKU_BASE_PATH));
		Sheet sheet = workbook.getSheetAt(0);
		int rowStart = Math.min(15, sheet.getFirstRowNum());
		int rowEnd = sheet.getLastRowNum();

		for (int rowNum = rowStart + 1; rowNum < rowEnd + 1; rowNum++) {
			Link n1 = new Link();
			Row r = sheet.getRow(rowNum);
			int r2 = r.getRowNum();
			int lastColumn = r.getLastCellNum();
			for (int cn = 0; cn < lastColumn; cn++) {
				// asigno los datos a la base según la columna
				if (cn == 0) {
					Cell c = r.getCell(cn);
					double id = c.getNumericCellValue();
					int idInt = (int) id;
					n1.setId(idInt);

				} else if (cn == 1) {
					Cell c = r.getCell(cn);
					String ean = c.getStringCellValue();
					Optional<Producto> prod = productoRepository.findById(ean);
					Producto prod2 = prod.get();
					n1.setProducto(prod2);

				} else if (cn == 2) {
					Cell c = r.getCell(cn);
					double kg = c.getNumericCellValue();
					String kg2 = kg + "";
					n1.setKg(kg2);

				} else if (cn == 3) {

					Cell c = r.getCell(cn);
					double etailer = c.getNumericCellValue();
					int etailerInt = (int) etailer;
					Optional<Etailer> e = etailerRepository.findById(etailerInt);
					Etailer e2 = e.get();
					n1.setEtailer(e2);

				} else if (cn == 4) {

					Cell c = r.getCell(cn);
					String link = c.getStringCellValue();

					n1.setLink(link);

				}

			}
			n2.add(n1);
		}
		return n2;
	}

	public List<LinkSearch> linkSearchdesdeExcel()
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<LinkSearch> n2 = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(new File(SEARCH_BASE_PATH));
		Sheet sheet = workbook.getSheetAt(0);
		int rowStart = Math.min(15, sheet.getFirstRowNum());
		int rowEnd = sheet.getLastRowNum();

		for (int rowNum = rowStart + 1; rowNum < rowEnd + 1; rowNum++) {
			LinkSearch n1 = new LinkSearch();
			Row r = sheet.getRow(rowNum);
			int r2 = r.getRowNum();
			int lastColumn = r.getLastCellNum();
			for (int cn = 0; cn < lastColumn; cn++) {
				// asigno los datos a la base según la columna
				if (cn == 0) {
					Cell c = r.getCell(cn);
					double id = c.getNumericCellValue();
					int idInt = (int) id;
					n1.setId(idInt);

				} else if (cn == 1) {
					Cell c = r.getCell(cn);
					String tipo = c.getStringCellValue();
					n1.setTipo(tipo);

				} else if (cn == 2) {
					Cell c = r.getCell(cn);
					double etailer = c.getNumericCellValue();
					int etailerInt = (int) etailer;
					Optional<Etailer> e = etailerRepository.findById(etailerInt);
					Etailer e2 = e.get();
					n1.setEtailer(e2);

				} else if (cn == 3) {

					Cell c = r.getCell(cn);
					String CatOrKW = c.getStringCellValue();
					n1.setCategoryOrKW(CatOrKW);

				} else if (cn == 4) {
					Cell c = r.getCell(cn);
					double category = c.getNumericCellValue();
					int categoryInt = (int) category;
					List<Categoria> cat = categoriaRepository.findById(categoryInt);
					Categoria cat2 = cat.get(0);
					n1.setCategoria(cat2);

				} else if (cn == 5) {
					Cell c = r.getCell(cn);
					double num = c.getNumericCellValue();
					int totalProd = (int) num;
					n1.setTotalProd(totalProd);

				} else if (cn == 6) {
					Cell c = r.getCell(cn);
					String link = c.getStringCellValue();

					n1.setLink(link);

				}

			}
			n2.add(n1);
		}
		return n2;
	}
}
