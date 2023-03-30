package com.library.steps;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US06_stepDefinitions {


  LoginPage loginPage=new LoginPage();
  DashBoardPage dashBoardPage=new DashBoardPage();

  BookPage bookPage=new BookPage();
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
      Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
      loginPage.login(user);

    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String model) {
      dashBoardPage.navigateModule(model);


    }
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

      bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String BookName) {

      bookPage.bookName.sendKeys(BookName);
      BrowserUtil.waitFor(3);

    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String ISBN ) {
     bookPage.isbn.sendKeys(ISBN);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String Year ) {
      bookPage.year.sendKeys(Year);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {

      bookPage.author.sendKeys(author);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
      BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,category);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {

      BrowserUtil.waitFor(3);
       bookPage.saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String string) {
      Assert.assertTrue( bookPage.toastMessage.isDisplayed());

    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

  String query=("select id,name,author from books where name = '"+expectedBookName+"'");

      DB_Util.runQuery(query);

  Map<String,String>rowMap = DB_Util.getRowMap(1);


      String actualBookName= rowMap.get("name");
      System.out.println("actualBookName = " + actualBookName);

      Assert.assertEquals(expectedBookName,actualBookName);
    }
}
