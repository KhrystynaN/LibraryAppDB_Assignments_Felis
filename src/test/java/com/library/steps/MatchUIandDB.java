package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchUIandDB {

LoginPage loginPage;
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
       loginPage=new LoginPage();
        loginPage.login(user);
        BrowserUtil.waitFor(3);
    }


    BookPage bookPage;
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage = new BookPage();
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(1);
    }

    String bookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String name) {
        bookName = name;
        bookPage = new BookPage();
        bookPage.search.sendKeys(bookName);
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        bookPage = new BookPage();
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();
        BrowserUtil.waitFor(3);
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

            // bookName: global variable that can bu used in the query
            bookPage = new BookPage();
            String UI_book_Name = bookPage.bookName.getAttribute("value");
            String UI_author_Name = bookPage.author.getAttribute("value");
            Select select = new Select(bookPage.categoryDropdown);
            String UI_book_Category = select.getFirstSelectedOption().getText();
            List<String> bookInfoFromUI = new ArrayList<>(Arrays.asList(UI_book_Name,UI_author_Name,UI_book_Category));
            // Database Info
            String query = "select b.name as bookName, author, bc.name as bookCategoryName from books b inner join\n" +
                    "    book_categories bc on b.book_category_id = bc.id\n" +
                    "where b.name = '"+bookName+"'";

            DB_Util.runQuery(query);  // executes query and stores data into result-set object

            // Map<String,String> bookInfoMap = DB_Util.getRowMap(1);

            // System.out.println("bookInfoMap = " + bookInfoMap);

            List<String> bookInfoListFromDB = DB_Util.getRowDataAsList(1);
            System.out.println("bookInfoListFromDB = " + bookInfoListFromDB);

            String DB_book_Name = bookInfoListFromDB.get(0);
            String DB_author_Name = bookInfoListFromDB.get(1);
            String DB_category_Name = bookInfoListFromDB.get(2);

            Assert.assertEquals(DB_book_Name,UI_book_Name); // verify one by one
            Assert.assertEquals(bookInfoListFromDB,bookInfoFromUI); // verify as a List

        }

    }
