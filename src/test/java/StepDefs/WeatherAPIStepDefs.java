package StepDefs;
import Test_BBC_Weather_API.GetWeatherLocation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class WeatherAPIStepDefs extends  GetWeatherLocation{

    @Given("^I have meta weather api \"([^\"]*)\"$")
    public void iHaveMetaWeatherApi(String url) {baseApi=url;}

    @Given("^I have London weather weekly forecast api \"([^\"]*)\"$")
    public void iHaveLondonWeatherApi(String url){londonWeatherAPI=url;}

    @When("^I send the request to meta weather base api$")
    public void iSendTheRequestToMetaWeatherBaseApi() { getLocation();}

    @When("^I send the request to London weather forecast using woeid$")
    public void iSendTheRequestToLondonWeatherForecastUsingWoeid() {getWeatherForcast();}

    @Then("^I verify the status code is 200$")
    public void iVerifyTheStatusCodeIs200() { statusCode();}

    @Then("^I check the weather forecast for (.*) is (.*)$")
    public void iCheckTheWeatherForecastForDaysIsExpectedWeather(String days, String expectedWeather) {checkWeather(days,expectedWeather); }
}