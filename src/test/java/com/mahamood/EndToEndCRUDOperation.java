package com.mahamood;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.UpdatePlace;
import utils.TestDataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EndToEndCRUDOperation extends BaseTest {
    private String place_id;
    private String address = "29, side layout, cohen 09";
    private double lat = -38.383494;
    private double lng = 33.427362;
    private String newAddress = "Salt Lake";
    TestDataBuilder dataBuilder = new TestDataBuilder();

    @Test
    public void googleMapPost() {
        AddPlace addPlace = dataBuilder.addPlacePayload(address, lat, lng);

        Response response = given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(addPlace)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .extract().response();

        place_id = response.jsonPath().getString("place_id");
        System.out.println("Place created with ID: " + place_id);
    }

    @Test(dependsOnMethods = {"googleMapPost"})
    public void googleMapGet() {
        Response response = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when()
                .get("/maps/api/place/get/json")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        String actualAddress = response.jsonPath().getString("address");
        Assert.assertEquals(address, actualAddress, "Address mismatch after POST");
    }

    @Test(dependsOnMethods = {"googleMapGet"})
    public void googleMapPut() {
        UpdatePlace updatePlace = dataBuilder.updatePlacePayload(place_id, newAddress);

        given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(updatePlace)
                .when()
                .put("/maps/api/place/update/json")
                .then()
                .assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        Response updatedResponse = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when()
                .get("/maps/api/place/get/json")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        String updatedAddress = updatedResponse.jsonPath().getString("address");
        Assert.assertEquals(updatedAddress, newAddress, "Address not updated correctly");
    }

    @Test(dependsOnMethods = {"googleMapPut"})
    public void googleMapDelete() {
        String deleteBody = "{ \"place_id\":\"" + place_id + "\" }";

        given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(deleteBody)
                .when()
                .post("/maps/api/place/delete/json")
                .then()
                .assertThat().statusCode(200)
                .body("status", equalTo("OK"));

        System.out.println("Deleted place ID: " + place_id);
    }
}
