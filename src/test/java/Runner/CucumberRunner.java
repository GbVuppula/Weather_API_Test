package Runner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "StepDefs",
        dryRun = false,
        strict = false,
        monochrome = true,
        tags 	= {"@Automation"},
        plugin = {
                "pretty",
                "html:target/cucumber",
        }
)
public class CucumberRunner
{
    @org.junit.BeforeClass
    public static void beforeClass() {
        System.out.println("*************************************************");
    }

    @org.junit.AfterClass
    public static void afterClass() {
        System.out.println("*************************************************");
    }
}

