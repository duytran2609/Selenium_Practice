package api;

import base.BaseAPI;
import io.restassured.response.Response;

public class MovieAPI extends BaseAPI {

    public Response getMoviesByType(String type) {
        return requestSpecification.queryParam("type", type)
                .when()
                .get("/movies");
    }
}
