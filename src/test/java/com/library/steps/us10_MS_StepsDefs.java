package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class us10_MS_StepsDefs extends BasePage {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();


    @Given("the {string} on the home page_MS")
    public void the_on_the_home_page_ms(String librarian) {
        loginPage.login(librarian);

    }
    @When("the user navigates to {string} page_MS")
    public void the_user_navigates_to_page_ms(String module) {
     bookPage.navigateModule(module);

    }
    @When("the user clicks book categories_MS")
    public void the_user_clicks_book_categories_ms() {
    bookPage.mainCategoryElement.click();

    }
    @Then("verify book categories must match book_categories table from db_MS")
    public void verify_book_categories_must_match_book_categories_table_from_db_ms() {
        List<String> actualBookCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCategories.remove(0);//to remove the column name

        DB_Util.runQuery("select name from book_categories");
        List<String> expectedBookCategories = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedBookCategories,actualBookCategories);

    }


}
