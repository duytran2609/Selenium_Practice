package api;

import base.BaseAPI;
import io.restassured.response.Response;

public class MovieAPI extends BaseAPI {

    public Response getMoviesByType(String type) {
        return requestSpecification.queryParam("type", type)
                .when()
                .get("/movies");
    }

    public Response getMoviesBySearch(String keyword) {
        return requestSpecification.queryParam("search", keyword)
                .when()
                .get("/movies");
    }

    public Response getMoviesByPage(String page) {
        return requestSpecification.queryParam("page", page)
                .when()
                .get("/movies");
    }
}
