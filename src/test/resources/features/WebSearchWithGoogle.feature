Feature: Web Search with Google
  Perform some simple web searches and verify the results

  #Scenario: Testing that you can enter a search into google and Google will return results
  Scenario: Simple Search
    Given a web browser is loaded
    When a Google search is performed for "Flooid test"
    Then results contain "Flooid test"

  #Scenario: List 10 top websites that contain the phone number of flooid coventry and validate any website has details other than flooid
  Scenario: Search for external websites containing Flooid Coventry phone number
    Given a web browser is loaded
     And a Google search is performed for "+44 (0)24 7669 4455"
    When the top 10 web pages are returned
    Then the external web pages contain "+44 (0)24 7669 4455"
