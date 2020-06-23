package org.ada.webScrapingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.junit.jupiter.api.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
// Disclaimer: el token está hardcodeado, se que lo correcto sería primero hacer un login y obtener el token, pero no se cómo hacerlo. 
public class TestControllerGet extends TestCase {
	@Test
	public void listarLink() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllLink")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}


	@Test
	public void listarProducto() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllProducto")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void listarEtailer() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllEtailer")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void listarCategoria() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllCategoria")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void listarReporte() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllReporte")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void listarLinkSearch() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllLinkSearch")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
	
	@Test
	public void listarReporteSearch() throws UnirestException {
	    HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/api/obtener_listas/AllReporteSearch")
	      .header("accept", "application/json")
	      .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mjg0MjA0NywiZXhwIjoxNTkyODc4MDQ3fQ.gFYUXT625_eosplZJAoB46S_qst4nHSv2rxfvgGhiBBwvSMsLM3bewG0g7nmNfzvzmko38h5Mgnnyf97J17gRw")
	    .asJson();
	    
	    assertNotNull(jsonResponse.getBody());
	    assertEquals(202, jsonResponse.getStatus());
	}
}
