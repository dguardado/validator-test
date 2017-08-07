package com.dimas;

import com.dimas.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestApplication extends Application<TestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final TestConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(HelloResource.class);
    }

}
