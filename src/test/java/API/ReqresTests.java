package API;

import org.testng.annotations.Test;
import static API.Specifications.requestSpec;
import static API.Specifications.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class ReqresTests {

    @Test
    public void checkGeorgesEmail() {
        given()
                .spec(requestSpec)
                .when().get()
                .then()
                .spec(responseSpec)
                .body("data.find{it.first_name =='George' & it.last_name == 'Bluth'}.email", equalTo("george.bluth@reqres.in"));
    }

    @Test
    public void checkMichaelsEmail() {
        Specifications.getTotal();
        given()
                .spec(requestSpec)
                .params("per_page", Specifications.getTotal())
                .when().get()
                .then()
                .spec(responseSpec)
                .body("data.find{it.first_name == 'Michael' & it.last_name == 'Lawson'}.email", containsString("michael.lawson@reqres"));

    }
}
