package apiTests;


import helpers.RandomGenerator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Category;
import models.PetResponse;
import models.PostPetRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class petStore {
    @Test
    public void getPetById(){
        Response response =
                given()
                    .baseUri("https://petstore.swagger.io/v2/")
                    .basePath("pet/100")
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .extract()
                    .response();


        JsonPath jsonResponse = response.jsonPath();
        PetResponse pet = jsonResponse.getObject("$", PetResponse.class);

        // var list = jsonResponse.getObject("$", PetResponse[].class);
        Category category = jsonResponse.getObject("category", Category.class);

        Assert.assertEquals(pet.getName(),"CaptainJack");
        String catName = pet.getCategory().getName();
        Assert.assertEquals(catName,"birds");

        Assert.assertEquals(category.getName(),"birds");
    }

    @Test
    public void postPet(){
        PostPetRequest petRequest = RandomGenerator.petRequestGenerator();
        Response response =
                given()
                        .baseUri("https://petstore.swagger.io/v2/")
                        .basePath("pet")
                        .header("Content-Type","application/json")
                        .body(petRequest)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        JsonPath jsonPath = response.jsonPath();
        PetResponse petResponse = jsonPath.getObject("$",PetResponse.class);
        Assert.assertEquals(petRequest.getName(),petResponse.getName());

    }
}
