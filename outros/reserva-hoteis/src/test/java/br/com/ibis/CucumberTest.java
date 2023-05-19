package br.com.ibis;

import io.cucumber.core.options.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.*;

import java.io.File;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature")
@ConfigurationParameters({
        @ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "br.com.ibis.glue"),
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty"),
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "usage"),
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
})
public class CucumberTest {

    @Test
    void testarArquivosDeCenarios() {
        File features = new File("src/test/resources/feature");
        Assertions.assertTrue(features.exists());
    }
}
