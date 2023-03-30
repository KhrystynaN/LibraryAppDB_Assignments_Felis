package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class us02_AT_Step_Definitions extends BasePage {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    String actualBorrowBookNum;

    @Given("the {string} on the home page -AT")
    public void the_on_the_home_page_at(String string) {
        loginPage.login(string);


    }
    @When("the librarian gets borrowed books number -AT")
    public void the_librarian_gets_borrowed_books_number_at() {

        actualBorrowBookNum= dashBoardPage.borrowedBooksNumber.getText();



    }
    @Then("borrowed books number information must match with DB -AT")
    public void borrowed_books_number_information_must_match_with_db_at() {


        DB_Util.runQuery("select count(*)from book_borrow where is_returned=0");


        String expectedBrrowBookNumowBookNum = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(actualBorrowBookNum,expectedBrrowBookNumowBookNum);


    }
}
