package com.dimas;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorTest.class);;

    @ClassRule
    public static final DropwizardAppRule<TestConfiguration> RULE =
            new DropwizardAppRule<>(TestApplication.class,"config.yml");

    private static Client client;

    @BeforeClass
    public static void setUp() {
        client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
    }

    @Test
    public void valid() {
        Response response = client.target(
                String.format("http://localhost:%d/hello", RULE.getLocalPort()))
                .request()
                .post(Entity.json(
                        ImmutableMap.of(
                                "id", "4642df498cf98746",
                                "otherId", "97cdb866-9b30-4bab-821c-46de8323bba6")));

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void badJson() {
        Response response = client.target(
                String.format("http://localhost:%d/hello", RULE.getLocalPort()))
                .request()
                .post(Entity.json("doop"));

        assertThat(response.getStatus()).isEqualTo(400);
    }

    @Test
    public void invalidEntity() {
        Response response = client.target(
                String.format("http://localhost:%d/hello", RULE.getLocalPort()))
                .request()
                .post(Entity.json(ImmutableMap.of()));

        assertThat(response.getStatus()).isEqualTo(422);
        logger.info(response.readEntity(String.class));
    }


    @Test
    public void invalidFields() {
        Response response = client.target(
                String.format("http://localhost:%d/hello", RULE.getLocalPort()))
                .request()
                .post(Entity.json(ImmutableMap.of("id", "wxyz", "otherId", "krs1")));

        logger.info(response.readEntity(String.class));
        assertThat(response.getStatus()).isEqualTo(422);
    }
}
