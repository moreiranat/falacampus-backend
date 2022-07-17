package br.edu.ifpb.dac.falacampus.business.service;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.edu.ifpb.dac.falacampus.model.entity.SystemUser;

public class ConverterService {
	
	
	public String mapToJson(Map<String,String>map) {
		Gson gson = new Gson();
		String json =gson.toJson(map);
		return json;
	}
	
	public String jsonToToken(String json) {
		JsonElement jsonElement = JsonParser.parseString(json);
		String token = jsonElement.getAsJsonObject().get("token").getAsString();
		return token;
	}
	
	public SystemUser jsonToUser(String json) {
		JsonElement jsonElement = JsonParser.parseString(json);
		JsonObject results = jsonElement.
		getAsJsonObject()
		.get("results")
		.getAsJsonArray()
		.get(0).getAsJsonObject();
		String name = results.get("nome").getAsString();
		String username = results.get("matricula").getAsString();
		SystemUser user = new SystemUser();
		user.setName(name);
		user.setUserName(username);
		
		return user;
	}

}
