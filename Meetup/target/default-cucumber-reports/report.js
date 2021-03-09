$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/BasicValidation.feature");
formatter.feature({
  "line": 1,
  "name": "Basic Validation",
  "description": "",
  "id": "basic-validation",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Title verification",
  "description": "",
  "id": "basic-validation;title-verification",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "the user is on the meetup homepage",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "verify the title contains \"Meetup\"",
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
});