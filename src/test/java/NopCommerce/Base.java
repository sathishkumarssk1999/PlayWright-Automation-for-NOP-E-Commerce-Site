package NopCommerce;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class Base {

    @BeforeSuite
    public void setup() throws IOException {

        // Clear old results
        FileUtils.deleteDirectory(new File("test-output"));

        // Create Test-Output directory to store results
        FileUtils.forceMkdir(new File("test-output/screenshots"));

        // Copy test data file to write test results
        FileUtils.copyFile(new File("src/main/resources/TestData.xls"), new File("test-output/TestOutput.xls"));

    }

}
