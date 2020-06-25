package com.sistemaGestion;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GestionRecursosRestApplication.class)
@CucumberContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class SpringIntegrationTest {

}
