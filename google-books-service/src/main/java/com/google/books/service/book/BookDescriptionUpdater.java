package com.google.books.service.book;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * This component retrieves the description of a book from Google Books API.
 *
 * @author afernandez
 */
@Component
public class BookDescriptionUpdater {

    @Value("${google.api.endpoint}")
    private String googleBooksUri;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves a new and more complete description from a book given its ISBN.
     *
     * @param isbn ISBN from a book
     * @return The new description
     */
    public String updateDescriptionFromIsbn(String isbn) {
        final URI uri = UriComponentsBuilder.fromUriString(String.format(googleBooksUri, isbn))
                .queryParam("country", "NL")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> response = doInvoke(uri);

        return getBookDescriptionFromJsonString(response.getBody());
    }

    /**
     * Invokes the rest service with the given final URI fully constructed and the
     * needed headers.
     *
     * @param uri The full URI to invoke the request
     * @return The response entity expected
     */
    private ResponseEntity<String> doInvoke(final URI uri) {
        HttpHeaders httpHeaders = getHeaders();

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        return responseEntity;
    }

    /**
     * Returns the basic headers used in the request.
     *
     * @return The newly created headers
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return httpHeaders;
    }

    /**
     * Returns the book's description from a string containing the JSON response.
     *
     * @param jsonString A string containing all the JSON response
     * @return The book description extracted from the JSON string
     */
    private String getBookDescriptionFromJsonString(String jsonString) {
        JsonNode node;

        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readValue(jsonString, JsonNode.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return node.get("items")
                .get(0)
                .get("volumeInfo")
                .get("description")
                .asText();
    }
}