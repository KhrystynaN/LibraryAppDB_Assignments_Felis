
  @us10_MS
  Feature: As a data consumer, I want UI and DB book categories are match

    @db @UI
    Scenario: verify book categories with DB
      Given the "librarian" on the home page_MS
      When the user navigates to "Books" page_MS
      And the user clicks book categories_MS
      Then verify book categories must match book_categories table from db_MS