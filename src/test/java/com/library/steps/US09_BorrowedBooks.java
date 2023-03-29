package com.library.steps;

import com.library.pages.BasePage;
import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US09_BorrowedBooks extends BasePage{



  DashBoardPage dashBoardPage = new DashBoardPage();

  LoginPage loginPage = new LoginPage();

  String actualBorrowedBookNumbers;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
    loginPage.login(user);
        BrowserUtil.waitFor(4);
    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query = "select count(*) from book_borrow\n" +
                "where is_returned = 0";

        DB_Util.runQuery(query);

        String expectedBorrowedBookNumbers= DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBookNumbers = " + expectedBorrowedBookNumbers);

        Assert.assertEquals(actualBorrowedBookNumbers,expectedBorrowedBookNumbers);




    }
}
