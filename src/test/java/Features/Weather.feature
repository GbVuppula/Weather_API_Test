Feature: Verify stability of weather API
In order to test stability of Weather API
 As a member of the development team
  I want to send request and verify responses

  @Automation
  Scenario: Verify the stability of meta weather api
    Given I have meta weather api "https://www.metaweather.com/api/"
    When I send the request to meta weather base api
    Then I verify the status code is 200

  @Automation
  Scenario Outline: Get the weather forecast for next 3 days
   Given I have London weather weekly forecast api "https://www.metaweather.com/api/location/44418/"
   When I send the request to London weather forecast using woeid
   Then I check the weather forecast for <Day> is <ExpectedWeather>
     Examples:
      | Days               | ExpectedWeather |
      | Today's Weather    | Light Rain      |
      | Tomorrow's Weather | Light Rain      |
      | Tomorrow+1 Weather | Heavy Rain      |
      | Tomorrow+2 Weather | Light Rain      |

  @Manual
  Scenario: Verify stability of location query search API response and get woeid
    Given I have the request for location query search API
    When I send the request to location query search API
    Then the response status code should be 200
    And I should see <Title>, <Location_Type> And <Woeid>
      |Title  |Location_Type|Woeid|
      |London |City         |44418|

  @Manual
  Scenario: Verify weather forecast API response using woeid
   Given I have the request for weather forecast API with woeid
   When I send the request to weather forecast API with woeid
   Then the response status code should be 200
    And I should see the London weather forecast for next 5

  @Manual
  Scenario: Verify weather forecast API response without woeid
   Given I have request for weather forecast API without woied
   When I send the request for weather forecast without woeid
   Then I should see 404 status code
    And I should not see any weather report

  @Manual
  Scenario: Verify location query search API with incorrect location
    Given I have the request for location query search API
    When I send the request to location query search API
    Then the response status code should be 200
     And I should not see any response

  @Manual
  Scenario: Verify location query search API response without search/query
   Given I have request for location query search API without search/query
   When I send request to location query search API without search/query
   Then I should see status code as 404

  @Manual
  Scenario: Verify location query search API response without location
    Given I have request for location query search API without location
    When I send request to location query search API without location
    Then I should see status code as 404