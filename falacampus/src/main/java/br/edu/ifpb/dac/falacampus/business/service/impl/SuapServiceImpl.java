package br.edu.ifpb.dac.falacampus.business.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.json.JsonMapper.Builder;

import br.edu.ifpb.dac.falacampus.business.service.ConverterService;
import br.edu.ifpb.dac.falacampus.business.service.SuapService;

public class SuapServiceImpl implements SuapService {
	private ConverterService converterService;

	public String login(String registration, String password) {
		Map body = Map.of(USERNAME_JSON_FIELD, registration, PASSWORD_JSON_FIELD, password);
		String json = converterService.mapToJson(body);
		// mapTOJson(body);
		try {
			HttpRequest url = generatePostUrl(OBTAIN_TOKEN_URL, null, json);
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			e3.printStackTrace();

		}
		return null;
	}

	@Override
	public String findEmployee(String token, String username) {
		String url = String.format("%s?search=%s", EMPLOYEES_URL, username);
		return find(token, url);
	}

	@Override
	public String findEmployee(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findStudent(String token, String username) {
		String url = String.format("%s?search=%s", STUDENS_URL, username);
		return find(token, url);
	}

	@Override
	public String findStudent(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findUser(String token, String username) {
		String result = findEmployee(token, username);
		if (result == null) {
			result = findStudent(token, username);
		}
		return result;
	}

	private HttpRequest generatePostUrl(String url, Map<String, String> headers, String body)
			throws URISyntaxException {
		// Builder builder = (Builder) HttpRequest.newBuilder().uri(new URI(url));

		java.net.http.HttpRequest.Builder builder = HttpRequest.newBuilder().uri(new URI(body));

		if (DEFAULT_HEADERS != null) {
			for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
				((java.net.http.HttpRequest.Builder) builder).setHeader(header.getKey(), header.getValue());
			}
		}
		if (headers != null) {
			for (Map.Entry<String, String> header : headers.entrySet()) {
				((java.net.http.HttpRequest.Builder) builder).setHeader(header.getKey(), header.getValue());
			}

		}
		HttpRequest request = ((java.net.http.HttpRequest.Builder) builder).POST(BodyPublishers.ofString(body)).build();
		return request;
		
	}

	private String sendRequest(HttpRequest httpRequest) throws IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		String response="";
		try {
			response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();

		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}
		return response;
	}

	private HttpRequest generateGetUrl(String url, Map<String, String> headers) throws URISyntaxException {
		Builder builder = (Builder) HttpRequest.newBuilder().uri(new URI(url));
		for (Map.Entry<String, String> header : DEFAULT_HEADERS.entrySet()) {
			((java.net.http.HttpRequest.Builder) builder).setHeader(header.getKey(), header.getValue());
		}
		for (Map.Entry<String, String> header : headers.entrySet()) {
			((java.net.http.HttpRequest.Builder) builder).setHeader(header.getKey(), header.getValue());
		}
		HttpRequest request = ((java.net.http.HttpRequest.Builder) builder).GET().build();
		return request;
	}

	private String find(String token, String findUrl) {
		try {
			HttpRequest url = generateGetUrl(findUrl,
					Map.of(TOKEN_HEADER_NAME, String.format(TOKEN_HEADER_VALUE, token)));
			return sendRequest(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();

		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		return null;
	}

	@Override
	public String login(Long registration, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findUser(String token, Long registration) {
		// TODO Auto-generated method stub
		return null;
	}
}
