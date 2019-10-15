package Test_BBC_Weather_API;
import org.testng.Assert;
import java.util.List;
import java.util.Map;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class GetWeatherLocation {
  Response resp = null;
  String actualTodaysWeatherStatus = null;
  String actualTomorrowsWeatherStatus = null;
  String actualDayAfterTomorrowsStatus = null;
  String actual2DaysAfterTomorrowsWeatherStatus = null;

   public static String baseApi=null;
   public static String londonWeatherAPI = null;

    public void getLocation() {
      resp = (Response) given().
                when().
                get(baseApi).
                then().extract().response();
    }

    public void statusCode() {
        if (resp.getStatusCode() == 200)
        {
            System.out.println("Meta Weather API is working fine"); }
        else {
            System.out.println("Meta Weather API is not working");
        }
    }

    public void getWeatherForcast() {

        resp = when().
        get(londonWeatherAPI);
      //  get("https://www.metaweather.com/api/location/"+woeid);
    }

    public void checkWeather(String days, String expectedWeather) {

        if ("Today's Weather".equals(days)) {

            List<Map<String, String>> consolidated_weather = resp.jsonPath().getList("consolidated_weather");
            actualTodaysWeatherStatus = consolidated_weather.get(0).get("weather_state_name");

            Assert.assertEquals(actualTodaysWeatherStatus, expectedWeather,
                    "Today's weather is not as expected");
            System.out.println(getMessage(actualTodaysWeatherStatus));

        } else if ("Tommorrow's Weather".equals(days)) {

            List<Map<String, String>> consolidated_weather = resp.jsonPath().getList("consolidated_weather");
            actualTomorrowsWeatherStatus = consolidated_weather.get(1).get("weather_state_name");

            Assert.assertEquals(actualTomorrowsWeatherStatus, expectedWeather,
                    "Tomorrow's weather is not as expected");

            System.out.println(getMessage(actualTomorrowsWeatherStatus));

        } else if ("Tomorrow+1 Weather".equals(days)) {

            List<Map<String, String>> consolidated_weather = resp.jsonPath().getList("consolidated_weather");
            actualDayAfterTomorrowsStatus = consolidated_weather.get(2).get("weather_state_name");

            Assert.assertEquals(actualDayAfterTomorrowsStatus, expectedWeather,
                    "DayAfterTomorrow's weather is not as expected");

            System.out.println(getMessage(actualDayAfterTomorrowsStatus));

        } else {
            List<Map<String, String>> consolidated_weather = resp.jsonPath().getList("consolidated_weather");
            actual2DaysAfterTomorrowsWeatherStatus = consolidated_weather.get(3).get("weather_state_name");

            Assert.assertEquals(actual2DaysAfterTomorrowsWeatherStatus, expectedWeather,
                    "DayAfterTomorrow +1 day weather is not as expected");

            System.out.println(getMessage(actual2DaysAfterTomorrowsWeatherStatus));
        }
    }

    private String getMessage(String weather){
        if("Light Rain".equals(weather)){
            return "Please wear raincoat";
        }else if ("Heavy Rain".equals(weather)) {
            return "Please carry an umbrella";
        }else{
            return "Don't leave your jumper at home";
        }
    }
}