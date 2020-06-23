package org.ada.restapi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.Reporte;
import org.ada.restapi.repository.EtailerRepository;
import org.ada.restapi.repository.LinkRepository;
import org.ada.restapi.repository.ReporteRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class MetodosRC {

	@Autowired
	EtailerRepository etailerRepository;

	public List<Reporte> procesoNaturalLife(List<Link> listado) {
		List<Reporte> n2 = new ArrayList<>();

		for (int i = 0; i < listado.size(); i++) {
			try {
				Link link = listado.get(i);
				String linkUtil = link.getLink();
//Acá arranca el scrap // 
				String url = linkUtil;
				Document document = Jsoup.connect(url).get();
				Element stock = document.selectFirst("#add_to_cart");
				String stock2 = stock.text();
				if (stock2.equals("Añadir al carrito")) {

					Element price = document.selectFirst("#our_price_display");
					String priceText = price.text();
					Element titulo = document.selectFirst("h1");
					Element price2 = document.selectFirst("#old_price_display");
					String price2Text = price2.text();
					String tituloText = titulo.text();
//guardo cada scrap en un objeto Reporte
					Reporte n3 = new Reporte();
					Metodos metodo = new Metodos();
					double precioDouble = metodo.valorCorregido(priceText);
					double promoDouble = metodo.valorCorregido(price2Text);
					Date hoy = new Date();
					n3.setPrecio(precioDouble);
					n3.setTitulo(tituloText);
					n3.setFecha(hoy);
					n3.setPrecio2(promoDouble);
					n3.setLinkSku(link);
					n3.setStock("si");
					n2.add(n3);
				} else {
					Reporte n3 = new Reporte();
					Date hoy = new Date();
					Element titulo = document.selectFirst("h1");
					String tituloText = titulo.text();
					n3.setPrecio(0.0);
					n3.setTitulo(tituloText);
					n3.setFecha(hoy);
					n3.setPrecio2(0.0);
					n3.setLinkSku(link);
					n3.setStock("no");
					n2.add(n3);

				}
			} catch (IOException e) {
				e.printStackTrace();
				Reporte n3 = new Reporte();
				Date hoy = new Date();
				n3.setPrecio(0.0);
				n3.setTitulo("404");
				n3.setFecha(hoy);
				n3.setPrecio2(0.0);
				Link link = listado.get(i);
				n3.setLinkSku(link);
				n3.setStock("no");
				n2.add(n3);

			}
		}
		return n2;

	}

//funciona OK: OOS, precio, promo y título
	public List<Reporte> procesoSebastian2(List<Link> listado) {
		List<Reporte> n2 = new ArrayList<>();

		for (int i = 0; i < listado.size(); i++) {
			try {
				Link link = listado.get(i);
				String linkUtil = link.getLink();
				String url = linkUtil;
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String kgFormat = link.getKg();
				
				switch (kgFormat) {
				case "3.0":
					driver.findElement(By.className("skuespec_Peso_opcao_3kg")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio2(0.0);
							n3.setPrecio(precioDouble);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0.0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0.0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();

					break;
				case "12.0":
					driver.findElement(By.className("skuespec_Peso_opcao_12kg")).click();
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0.0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);

						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "7.5":
					try {
						driver.findElement(By.className("skuespec_Peso_opcao_7.5-kg")).click();
					} catch (NoSuchElementException e) {
						e.printStackTrace();
						driver.findElement(By.className("skuespec_Peso_opcao_7V5kg")).click();
					}
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);

						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "15.0":
					driver.findElement(By.className("skuespec_Peso_opcao_15kg")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);

						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "1.5":
					driver.findElement(By.className("skuespec_Peso_opcao_1,5-kg")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);

						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "2.0":
					driver.findElement(By.className("skuespec_Peso_opcao_2kg")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("fn")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);

						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						String title = driver.findElement(By.className("fn")).getText();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Reporte n3 = new Reporte();
				Link link = listado.get(i);
				Date hoy = new Date();
				n3.setPrecio(0);
				n3.setTitulo("404");
				n3.setFecha(hoy);
				n3.setPrecio2(0);
				n3.setLinkSku(link);
				n3.setStock("no");
				n2.add(n3);

			}
		}
		return n2;
	}

	// falta 404 /no vimos productos sin stock
	// ..............................................................................
	public List<Reporte> procesoCanRock(List<Link> listado) {
		List<Reporte> n2 = new ArrayList<>();
		for (int i = 0; i < listado.size(); i++) {
			try {
				Link link = listado.get(i);
				String linkUtil = link.getLink();
				String url = linkUtil;
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				try {

					driver.findElement(By.cssSelector(".fancybox-close")).click();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}

				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				String title = driver.findElement(By.xpath("//h1")).getText();
				WebDriverWait wait = new WebDriverWait(driver, 10);

				String pricePromo = driver.findElement(By.cssSelector("#buy_block #old_price_display")).getText();

				Reporte n3 = new Reporte();
				Date hoy = new Date();
				if (pricePromo.isEmpty()) {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String price = driver.findElement(By.id("our_price_display")).getText();
					n3.setFecha(hoy);
					Metodos metodo = new Metodos();
					double precioDouble = metodo.valorCorregidoCanRock(price);
					n3.setPrecio(precioDouble);
					n3.setTitulo(title);
					n3.setPrecio2(0);
					n3.setLinkSku(link);
					n3.setStock("si");
					n2.add(n3);
				} else {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String price = driver.findElement(By.id("our_price_display")).getText();
					Metodos metodo = new Metodos();
					double precioDouble = metodo.valorCorregidoCanRock(price);
					double promoDouble = metodo.valorCorregidoCanRock(pricePromo);
					n3.setPrecio(promoDouble);
					n3.setPrecio2(precioDouble);
					n3.setFecha(hoy);
					n3.setTitulo(title);
					n3.setStock("si");
					n3.setLinkSku(link);
					n2.add(n3);
				}
				driver.close();

			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Reporte n3 = new Reporte();
				Link link = listado.get(i);
				Date hoy = new Date();
				n3.setPrecio(0);
				n3.setTitulo("404");
				n3.setFecha(hoy);
				n3.setPrecio2(0);
				n3.setLinkSku(link);
				n3.setStock("no");
				n2.add(n3);

			}

		}

		return n2;
	}

	public List<Reporte> procesoPuppis(List<Link> listado) {
		List<Reporte> n2 = new ArrayList<>();

		for (int i = 0; i < listado.size(); i++) {
			try {
				Link link = listado.get(i);
				String linkUtil = link.getLink();
				String url = linkUtil;
				System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

				// Initiating your chromedriver
				WebDriver driver = new ChromeDriver();

				// Applied wait time
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// maximize window
				driver.manage().window().maximize();
				driver.get(url);

				try {
					driver.findElement(By.xpath("//div[@class='close']")).click();
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}

				String kgFormat = link.getKg();
				switch (kgFormat) {
				case "3.0":
					try {
						driver.findElement(By.xpath("//div[contains(text(),'3 Kg')]")).click();
					} catch (NoSuchElementException e) {
						e.printStackTrace();
						driver.findElement(By.xpath("//div[contains(text(),'3Kg')]")).click();
					}
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					// hay stock:
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);

						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);

						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
						// no hay stock:
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "1.5":
					driver.findElement(By.xpath("//div[contains(text(),'1.5 Kg')]")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "7.5":
					try {
						driver.findElement(By.xpath("//div[contains(text(),'7.5 Kg')]")).click();
					} catch (NoSuchElementException e) {
						e.printStackTrace();
						driver.findElement(By.xpath("//div[contains(text(),'7,5 Kg')]")).click();
					}
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "1.0":
					driver.findElement(By.xpath("//div[contains(text(),'1 Kg')]")).click();
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "15.0":

					driver.findElement(By.xpath("//div[contains(text(),'15 Kg')]")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "12.0":
					driver.findElement(By.xpath("//div[contains(text(),'12 Kg')]")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
					break;
				case "2.0":
					driver.findElement(By.xpath("//div[contains(text(),'2 Kg')]")).click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					price3 = driver.findElement(By.className("notifyme")).getAttribute("style");
					if (price3.equals("display: none;")) {
						Reporte n3 = new Reporte();
						String price = driver.findElement(By.className("skuBestPrice")).getText();
						String title = driver.findElement(By.className("productName")).getText();
						List<WebElement> price21 = driver.findElements(By.className("skuListPrice"));

						if (price21.isEmpty()) {
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							n3.setPrecio(precioDouble);
							n3.setPrecio2(0);
						} else {
							String price2 = driver.findElement(By.className("skuListPrice")).getText();
							Metodos metodo = new Metodos();
							double precioDouble = metodo.valorCorregido(price);
							double promoDouble = metodo.valorCorregido(price2);
							n3.setPrecio(promoDouble);
							n3.setPrecio2(precioDouble);
						}
						Date hoy = new Date();
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setLinkSku(link);
						n3.setStock("si");
						n2.add(n3);
					} else {
						String title = driver.findElement(By.className("productName")).getText();
						Reporte n3 = new Reporte();
						Date hoy = new Date();
						n3.setPrecio(0);
						n3.setTitulo(title);
						n3.setFecha(hoy);
						n3.setPrecio2(0);
						n3.setLinkSku(link);
						n3.setStock("no");
						n2.add(n3);

					}
					driver.close();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Reporte n3 = new Reporte();
				Link link = listado.get(i);
				Date hoy = new Date();
				n3.setPrecio(0);
				n3.setTitulo("404");
				n3.setFecha(hoy);
				n3.setPrecio2(0);
				n3.setLinkSku(link);
				n3.setStock("no");
				n2.add(n3);

			}

		}

		return n2;

	}
}