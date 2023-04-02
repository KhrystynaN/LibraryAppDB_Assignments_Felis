package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_StepDefs {

    String actualMostPopularGenre;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        //  System.out.println("Connection is already done in hooks class");
    }
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        String query = "select bc.name, count(*)\n" +
                "from book_borrow bb join books b on bb.book_id = b.id\n" +
                "join book_categories bc on bc.id = b.book_category_id\n" +
                "group by bc.name\n" +
                "order by count(*) desc";

        DB_Util.runQuery(query);
        actualMostPopularGenre = DB_Util.getFirstRowFirstColumn();
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedMostPopularGenre) {
        Assert.assertEquals(actualMostPopularGenre,expectedMostPopularGenre);
    }
}
