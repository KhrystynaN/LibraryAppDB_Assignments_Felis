
Feature: As a data consumer, I want to know genre of books are being borrowed the most
@B28G18-166 @db
  Scenario: verify the the common book genre that’s being borrowed
    Given Establish the database connection
    When I execute query to find most popular book genre
    Then verify "Fantasy" is the most popular book genre.