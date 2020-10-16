Feature: Web Search with Google
  Perform some simple web searches and verify the results

  #Scenario: Testing that you can enter a search into google and Google will return results
  Scenario: Simple Search
    Given a web browser is loaded
    When a Google search is performed for "Flooid test"
    Then results contain "Flooid test"

  #Scenario: List 10 top websites that contain the phone number of flooid coventry and validate any website has details other than flooid
  Scenario: Search for websites containing Flooid Coventry phone number and validate contents
    Given a web browser is loaded
    When a Google search is performed for "024 7669 4455"
    Then the top 10 web pages all contain "024 7669 4455"
