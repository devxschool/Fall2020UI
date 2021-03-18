Feature: Search Functionality

  Background:
    Given the user is on meetup homepage

    Scenario: Verify search results
      When the user executes the search for "Foodie Rome" in "Los Angeles, CA"
      Then verify the resultset contains search criteria in the title



