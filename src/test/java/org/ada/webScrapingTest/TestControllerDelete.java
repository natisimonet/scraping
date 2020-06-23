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
public class TestControllerDelete extends TestCase {
	@Test
	public void eliminarEtailer() throws UnirestException {
		HttpResponse<String> jsonResponse = Unirest.delete("http://localhost:8080/api/delete/Etailer/19")
				.header("accept", "application/json")
				.header("Authorization",
						"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5MjkzMDc0NiwiZXhwIjoxNTkyOTY2NzQ2fQ.KvM-5ZuKOMiCSo3VuU3pMbsVSx92OQ-MNDWOYFYRQm3KRVMNH_QbqY1nkFnpeHbxHLI89l_DqV7i8NuEwaNiDA")
				.header("Content-Type", "application/json")
				.header("User-Agent", "PostmanRuntime/7.25.0")
				.asString();
		
		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

}
