$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/BasicValidation.feature");
formatter.feature({
  "line": 1,
  "name": "Basic Validation",
  "description": "",
  "id": "basic-validation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 2883897,
  "status": "passed"
});
formatter.scenario({
  "line": 4,
  "name": "Google Title verification",
  "description": "",
  "id": "basic-validation;google-title-verification",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@googleTest"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "the user navigates to google",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the user searches for \"apple\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "verify \"flower\" is in the title of the page",
  "keyword": "Then "
});
formatter.match({
  "location": "BasicValidationSteps.the_user_navigates_to_google()"
});
formatter.result({
  "duration": 10713515265,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "apple",
      "offset": 23
    }
  ],
  "location": "BasicValidationSteps.the_user_searches_for(String)"
});
formatter.result({
  "duration": 4864215170,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "flower",
      "offset": 8
    }
  ],
  "location": "BasicValidationSteps.verify_is_in_the_title_of_the_page(String)"
});
formatter.result({
  "duration": 85228421,
  "error_message": "java.lang.AssertionError\n\tat org.junit.Assert.fail(Assert.java:86)\n\tat org.junit.Assert.assertTrue(Assert.java:41)\n\tat org.junit.Assert.assertTrue(Assert.java:52)\n\tat step_defs.BasicValidationSteps.verify_is_in_the_title_of_the_page(BasicValidationSteps.java:28)\n\tat ✽.Then verify \"flower\" is in the title of the page(features/BasicValidation.feature:7)\n",
  "status": "failed"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 2386756945,
  "status": "passed"
});
formatter.uri("features/SearchFunctionality.feature");
formatter.feature({
  "line": 1,
  "name": "Search Functionality",
  "description": "",
  "id": "search-functionality",
  "keyword": "Feature"
});
formatter.before({
  "duration": 376953,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "the user is on meetup homepage",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.scenario({
  "line": 6,
  "name": "Verify search results",
  "description": "",
  "id": "search-functionality;verify-search-results",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "the user executes the search for \"Foodie Rome\" in \"Los Angeles, CA\"",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "verify the resultset contains search criteria in the title",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 45887,
  "status": "passed"
});
});