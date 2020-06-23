package org.ada.restapi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class Metodos {

	public double valorCorregido(String precioText) {
		try {
			if (precioText.length() >= 10) {
				String precioReplace = precioText.replace("$", "").replaceFirst("\\.", "").replaceFirst(",", ".")
						.trim();
				double d = Double.parseDouble(precioReplace);
				return d;
			} else {
				String precioReplace = precioText.replace("$", "").replaceFirst(",", ".").trim();
				double d1 = Double.parseDouble(precioReplace);
				return d1;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			double d2 = 0;
			return d2;
		}
		
	}
	
	public double valorCorregidoCanRock(String precioText) {
		try {
			if (precioText.length() >= 10) {
				String precioReplace = precioText.replace("$", "").replaceFirst(",", "")
						.trim();
				double d = Double.parseDouble(precioReplace);
				return d;
			} else {
				String precioReplace = precioText.replace("$", "").trim();
				double d1 = Double.parseDouble(precioReplace);
				return d1;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			double d2 = 0;
			return d2;
		}
		
	}

	public String obtenerMarca(String titulo) {
		String text = titulo;
		
		char[] caracteres = text.toLowerCase().toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);
		for (int i = 0; i < text.length()- 2; i++) 
	    // Es 'palabra'
	    if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
	      // Reemplazamos
	      caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
		String nuevo = new String(caracteres);

		List<String> tokens = new ArrayList<String>();
		tokens.add("Old Prince");
		tokens.add("Eukanuba");
		tokens.add("Pro Plan");
		tokens.add("Felix");
		tokens.add("Whiskas");
		tokens.add("Whole Earth");
		tokens.add("Excellent");
		tokens.add("Dog Chow");
		tokens.add("Unik");
		tokens.add("Core");
		tokens.add("Pedigree");
		tokens.add("Sieger");
		tokens.add("Iams");
		tokens.add("Cat Chow");
		tokens.add("Gati");
		tokens.add("Nutrique");
		tokens.add("Club Performance");
		tokens.add("VitalCan");
		tokens.add("VitalCat");
		tokens.add("Royal Canin");
		tokens.add("Top Nutrition");
		tokens.add("Ken- L");
		tokens.add("Vitalcan");
		tokens.add("Vitalcat");
		tokens.add("Proplan");
		tokens.add("Mon Ami");
		tokens.add("Criadores");
		tokens.add("Natural Meat");
		tokens.add("Infinity");
		tokens.add("Company");
		tokens.add("Exact");
		
		
		String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(nuevo);

		while (matcher.find()) {
			return matcher.group(1);
		} 
		return "no match";

	}
	
	
	
}
