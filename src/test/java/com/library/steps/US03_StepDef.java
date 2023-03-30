package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03_StepDef {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    List<String> actualBookCategories = new ArrayList<>();
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String userType) {
       loginPage.login(userType);
    }
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {
        bookPage.navigateModule(module);
    }
    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualBookCategories = BrowserUtil.getAllSelectOptions(bookPage.categoryDropdown);
        actualBookCategories.remove(0);
        System.out.println("actualBookCategories = " + actualBookCategories);
    }
    @Then("user should be able to see following categories")
    public void user_should_be_able_to_see_following_categories(List<String> bookCategoriesFromRequirement) {
        Assert.assertEquals(actualBookCategories, bookCategoriesFromRequirement);


    }

        @Then("verify book categories must match book categories table from db")
        public void verify_book_categories_must_match_book_categories_table_from_db() {
            String query = "select name from book_categories";
            DB_Util.runQuery(query);

            List<String> expectedBookCategoriesDB = DB_Util.getColumnDataAsList("name");

            Assert.assertEquals(actualBookCategories, expectedBookCategoriesDB);
        }




}
