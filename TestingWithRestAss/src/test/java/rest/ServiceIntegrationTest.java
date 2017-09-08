/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author MartinLodahl
 */
public class ServiceIntegrationTest {

    public ServiceIntegrationTest() {
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/Test1";
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void serverIsRunning() {
        given().
                when().get().
                then().
                statusCode(200);
    }

    @Test
    public void addOperation() {
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/add/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(4), "operation", equalTo("2 + 2"));
    }

    @Test
    public void addOperationv2() {
        given().
                when().get("/api/calculator/add/2/2").
                then().
                statusCode(200).
                body("result", equalTo(4), "operation", equalTo("2 + 2"));
    }

    @Test
    public void subOperation() {
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/sub/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(0), "operation", equalTo("2 - 2"));
    }

    @Test
    public void mulOperation() {
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/mul/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(4), "operation", equalTo("2 * 2"));
    }

    @Test
    public void divOperation() {
        given().pathParam("n1", 2).pathParam("n2", 2).
                when().get("/api/calculator/div/{n1}/{n2}").
                then().
                statusCode(200).
                body("result", equalTo(1), "operation", equalTo("2 / 2"));
    }

    @Test
    public void addOperationWrongArguments() {
        given().pathParam("n1", 2).pathParam("n2", 2.2).
                when().get("/api/calculator/add/{n1}/{n2}").
                then().
                statusCode(400).
                body("code", equalTo(400));
    }

    @Test
    public void wrongRoute() {
        given().pathParam("n1", 2).pathParam("n2", 2.2).
                when().get("/api/calculator/havefun/{n1}/{n2}").
                then().
                statusCode(404).
                body("code", equalTo(404));
    }

    @Test
    public void divByZero() {
        given().pathParam("n1", 2).pathParam("n2",0).
                when().get("/api/calculator/div/{n1}/{n2}").
                then().
                statusCode(500).
                body("code", equalTo(500));
    }

}
