package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

    protected RequestSpecification requestSpecification;

    public BaseAPI() {
        RestAssured.baseURI = "https://movie-project-back-end.vercel.app/api";
        requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON);

    }

}
