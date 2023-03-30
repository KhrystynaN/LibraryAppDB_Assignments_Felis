Feature: As a librarian, I want to know borrowed books number
@db @UI @wip
  Scenario: verify the total amount of borrowed books
    Given the "librarian" on the home page -AT
    When the librarian gets borrowed books number -AT
    Then borrowed books number information must match with DB -AT
