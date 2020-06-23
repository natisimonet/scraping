package org.ada.restapi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.ada.restapi.model.ReporteSearch;
import org.ada.restapi.model.LinkSearch;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Component
public class MetodosRCsearch {

	public List<ReporteSearch> procesoSearchNaturallife(List<LinkSearch> listado) {
		List<ReporteSearch> n2 = new ArrayList<>();
		for (int i = 0; i < listado.size(); i++) {

			LinkSearch search = listado.get(i);
			String searchUtil = search.getLink();
			String url = searchUtil;
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.manage().window().maximize();
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			List<WebElement> productos = driver.findElements(By.className("product-name"));
			ReporteSearch n3 = new ReporteSearch();
			Metodos metodo = new Metodos();
			String titulo = productos.get(1).getAttribute("title");
			n3.setLoc01(titulo);
			String marca = metodo.obtenerMarca(titulo);
			n3.setMarca01(marca);
			try {
				String titulo1 = productos.get(2).getAttribute("title");
				n3.setLoc02(titulo1);
				String marca1 = metodo.obtenerMarca(titulo1);
				n3.setMarca02(marca1);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc02("NO");
			}
			try {
				String titulo2 = productos.get(3).getAttribute("title");
				n3.setLoc03(titulo2);
				String marca2 = metodo.obtenerMarca(titulo2);
				n3.setMarca03(marca2);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc03("NO");
			}
			try {
				String titulo3 = productos.get(4).getAttribute("title");
				n3.setLoc04(titulo3);
				String marca3 = metodo.obtenerMarca(titulo3);
				n3.setMarca04(marca3);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc04("NO");
			}
			try {
				String titulo4 = productos.get(5).getAttribute("title");
				n3.setLoc05(titulo4);
				String marca4 = metodo.obtenerMarca(titulo4);
				n3.setMarca05(marca4);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc05("NO");
			}
			try {
				String titulo5 = productos.get(6).getAttribute("title");
				n3.setLoc06(titulo5);
				String marca5 = metodo.obtenerMarca(titulo5);
				n3.setMarca06(marca5);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc06("NO");
			}
			try {
				String titulo6 = productos.get(7).getAttribute("title");
				n3.setLoc07(titulo6);
				String marca6 = metodo.obtenerMarca(titulo6);
				n3.setMarca07(marca6);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc07("NO");
			}
			try {
				String titulo7 = productos.get(8).getAttribute("title");
				n3.setLoc08(titulo7);
				String marca7 = metodo.obtenerMarca(titulo7);
				n3.setMarca08(marca7);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc08("NO");
			}
			try {
				String titulo8 = productos.get(9).getAttribute("title");
				n3.setLoc09(titulo8);
				String marca8 = metodo.obtenerMarca(titulo8);
				n3.setMarca09(marca8);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc09("NO");
			}
			try {
				String titulo9 = productos.get(10).getAttribute("title");
				n3.setLoc10(titulo9);
				String marca9 = metodo.obtenerMarca(titulo9);
				n3.setMarca10(marca9);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc10("NO");
			}

			Date fecha = new Date();
			n3.setFecha(fecha);
			n3.setLinkSearch(search);
			n2.add(n3);
			driver.close();
		}

		return n2;

	}

	public List<ReporteSearch> procesoSearchPuppis(List<LinkSearch> listado) {
		List<ReporteSearch> n2 = new ArrayList<>();
		for (int i = 0; i < listado.size(); i++) {

			LinkSearch search = listado.get(i);
			String searchUtil = search.getLink();
			String url = searchUtil;
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.manage().window().maximize();
			driver.get(url);
			try {
				driver.findElement(By.xpath("//div[@class='close']")).click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}

			List<WebElement> productos = driver.findElements(By.className("productName"));
			ReporteSearch n3 = new ReporteSearch();
			Metodos metodo = new Metodos();
			String titulo = productos.get(0).getText();
			String marca = metodo.obtenerMarca(titulo);
			n3.setMarca01(marca);
			n3.setLoc01(titulo);
			try {
				String titulo1 = productos.get(1).getText();
				n3.setLoc02(titulo1);
				String marca1 = metodo.obtenerMarca(titulo1);
				n3.setMarca02(marca1);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc02("NO");
			}
			try {
				String titulo2 = productos.get(2).getText();
				n3.setLoc03(titulo2);
				String marca2 = metodo.obtenerMarca(titulo2);
				n3.setMarca03(marca2);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc03("NO");
			}
			try {
				String titulo3 = productos.get(3).getText();
				n3.setLoc04(titulo3);
				String marca3 = metodo.obtenerMarca(titulo3);
				n3.setMarca04(marca3);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc04("NO");
			}
			try {
				String titulo4 = productos.get(4).getText();
				n3.setLoc05(titulo4);
				String marca4 = metodo.obtenerMarca(titulo4);
				n3.setMarca05(marca4);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc05("NO");
			}
			try {
				String titulo5 = productos.get(5).getText();
				n3.setLoc06(titulo5);
				String marca5 = metodo.obtenerMarca(titulo5);
				n3.setMarca06(marca5);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc06("NO");
			}
			try {
				String titulo6 = productos.get(6).getText();
				n3.setLoc07(titulo6);
				String marca6 = metodo.obtenerMarca(titulo6);
				n3.setMarca07(marca6);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc07("NO");
			}
			try {
				String titulo7 = productos.get(7).getText();
				String marca7 = metodo.obtenerMarca(titulo7);
				n3.setMarca08(marca7);
				n3.setLoc08(titulo7);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc08("NO");
			}
			try {
				String titulo8 = productos.get(8).getText();
				String marca8 = metodo.obtenerMarca(titulo8);
				n3.setMarca09(marca8);
				n3.setLoc09(titulo8);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc09("NO");
			}
			try {
				String titulo9 = productos.get(9).getText();
				n3.setLoc10(titulo9);
				String marca9 = metodo.obtenerMarca(titulo9);
				n3.setMarca10(marca9);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc10("NO");
			}

			Date fecha = new Date();
			n3.setFecha(fecha);
			n3.setLinkSearch(search);
			n2.add(n3);
			driver.close();
		}

		return n2;

	}

	public List<ReporteSearch> procesoSearchSebastian(List<LinkSearch> listado) {
		List<ReporteSearch> n2 = new ArrayList<>();
		for (int i = 0; i < listado.size(); i++) {

			LinkSearch search = listado.get(i);
			String searchUtil = search.getLink();
			String url = searchUtil;
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			driver.manage().window().maximize();
			driver.get(url);

			List<WebElement> productos = driver.findElements(By.className("product-name"));
			ReporteSearch n3 = new ReporteSearch();
			Metodos metodo = new Metodos();
			try {
				String titulo = productos.get(0).getText();
				n3.setLoc01(titulo);
				String marca = metodo.obtenerMarca(titulo);
				n3.setMarca01(marca);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc01("NO");
			}
			try {
				String titulo1 = productos.get(1).getText();
				n3.setLoc02(titulo1);
				String marca1 = metodo.obtenerMarca(titulo1);
				n3.setMarca02(marca1);

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc02("NO");
			}
			try {
				String titulo2 = productos.get(2).getText();
				n3.setLoc03(titulo2);
				String marca2 = metodo.obtenerMarca(titulo2);
				n3.setMarca03(marca2);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc03("NO");
			}
			try {
				String titulo3 = productos.get(3).getText();
				n3.setLoc04(titulo3);
				String marca3 = metodo.obtenerMarca(titulo3);
				n3.setMarca04(marca3);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc04("NO");
			}
			try {
				String titulo4 = productos.get(4).getText();
				n3.setLoc05(titulo4);
				String marca4 = metodo.obtenerMarca(titulo4);
				n3.setMarca05(marca4);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc05("NO");
			}
			try {
				String titulo5 = productos.get(5).getText();
				n3.setLoc06(titulo5);
				String marca5 = metodo.obtenerMarca(titulo5);
				n3.setMarca05(marca5);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc06("NO");
			}
			try {
				String titulo6 = productos.get(6).getText();
				n3.setLoc07(titulo6);
				String marca6 = metodo.obtenerMarca(titulo6);
				n3.setMarca07(marca6);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc07("NO");
			}
			try {
				String titulo7 = productos.get(7).getText();
				n3.setLoc08(titulo7);
				String marca7 = metodo.obtenerMarca(titulo7);
				n3.setMarca08(marca7);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc08("NO");
			}
			try {
				String titulo8 = productos.get(8).getText();
				n3.setLoc09(titulo8);
				String marca8 = metodo.obtenerMarca(titulo8);
				n3.setMarca09(marca8);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc09("NO");
			}
			try {
				String titulo9 = productos.get(9).getText();
				n3.setLoc10(titulo9);
				String marca9 = metodo.obtenerMarca(titulo9);
				n3.setMarca10(marca9);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc10("NO");
			}

			Date fecha = new Date();
			n3.setFecha(fecha);
			n3.setLinkSearch(search);
			n2.add(n3);
			driver.close();
		}

		return n2;

	}

	public List<ReporteSearch> procesoSearchCanRock(List<LinkSearch> listado) {
		List<ReporteSearch> n2 = new ArrayList<>();
		for (int i = 0; i < listado.size(); i++) {

			LinkSearch search = listado.get(i);
			String searchUtil = search.getLink();
			String url = searchUtil;
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			driver.manage().window().maximize();
			driver.get(url);
			try {

				driver.findElement(By.cssSelector(".fancybox-close")).click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			Metodos metodo = new Metodos();
			List<WebElement> productos = driver.findElements(By.className("product-name"));
			ReporteSearch n3 = new ReporteSearch();
			try {
				String titulo = productos.get(1).getText();
				n3.setLoc01(titulo);
				String marca = metodo.obtenerMarca(titulo);
				n3.setMarca01(marca);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc01("NO");
			}
			try {
				String titulo1 = productos.get(2).getText();
				n3.setLoc02(titulo1);
				String marca1 = metodo.obtenerMarca(titulo1);
				n3.setMarca02(marca1);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc02("NO");
			}
			try {
				String titulo2 = productos.get(3).getText();
				n3.setLoc03(titulo2);
				String marca2 = metodo.obtenerMarca(titulo2);
				n3.setMarca03(marca2);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc03("NO");
			}
			try {
				String titulo3 = productos.get(4).getText();
				n3.setLoc04(titulo3);
				String marca3 = metodo.obtenerMarca(titulo3);
				n3.setMarca04(marca3);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc04("NO");
			}
			try {
				String titulo4 = productos.get(5).getText();
				n3.setLoc05(titulo4);
				String marca4 = metodo.obtenerMarca(titulo4);
				n3.setMarca05(marca4);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc05("NO");
			}
			try {
				String titulo5 = productos.get(6).getText();
				n3.setLoc06(titulo5);
				String marca5 = metodo.obtenerMarca(titulo5);
				n3.setMarca06(marca5);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc06("NO");
			}
			try {
				String titulo6 = productos.get(7).getText();
				n3.setLoc07(titulo6);
				String marca6 = metodo.obtenerMarca(titulo6);
				n3.setMarca07(marca6);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc07("NO");
			}
			try {
				String titulo7 = productos.get(8).getText();
				n3.setLoc08(titulo7);
				String marca7 = metodo.obtenerMarca(titulo7);
				n3.setMarca08(marca7);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc08("NO");
			}
			try {
				String titulo8 = productos.get(9).getText();
				n3.setLoc09(titulo8);
				String marca8 = metodo.obtenerMarca(titulo8);
				n3.setMarca09(marca8);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc09("NO");
			}
			try {
				String titulo9 = productos.get(10).getText();
				n3.setLoc10(titulo9);
				String marca9 = metodo.obtenerMarca(titulo9);
				n3.setMarca10(marca9);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				n3.setLoc10("NO");
			}

			Date fecha = new Date();
			n3.setFecha(fecha);
			n3.setLinkSearch(search);
			n2.add(n3);
			driver.close();
		}

		return n2;

	}
}
