@test
Feature: Web Search with Google
  Perform some simple web searches and verify the results

  #Scenario: Testing that you can enter a search into google and Google will return results
  Scenario: Simple Search
    Given a web browser is on the Google page
    When a search is performed with text "Flooid test"
    Then results for "Flooid test" are shown

  #Scenario: List 10 top websites that contain the phone number of flooid coventry and validate any website has details other than flooid
#  Scenario: Search for external websites containing Flooid Coventry phone number
#    Given a Google search has been performed for "0247612471247"
#    When the top 10 web pages are returned
#    Then basic website information is returned

#  Scenario: Details of external websites found with a Search for Flooid Coventry phone number
#    Given a Google search has been performed for "0247612471247"
#    When the top 10 web pages are returned
#    Then details for each non-Flooid website are returned



      
      