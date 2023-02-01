package io.excellent.project.crypto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

//@QuarkusTest
public class CryptoResourceTest {

    @Test
    @Disabled
    public void testHelloEndpoint() {
        given()
          .when().get("/crypto")
          .then()
             .statusCode(200);
    }

}