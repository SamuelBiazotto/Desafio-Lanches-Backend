package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Sandwiches;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SandwichesEndPointTests {

    @Autowired
    private SandwichesEndPoint sandwichesEndPoint;

    @Test
    public void testeGettAllSandwichesGraterThanZero() throws IllegalAccessException {
        List<Sandwiches> allSandwiches = sandwichesEndPoint.getAll();
        MatcherAssert.assertThat(allSandwiches.size(), Matchers.greaterThan(0));
    }

    @Test
    public void testeGettAllSandwichesNotNull() throws IllegalAccessException {
        List<Sandwiches> allSandwiches = sandwichesEndPoint.getAll();
        MatcherAssert.assertThat(allSandwiches, Matchers.notNullValue());
    }
}
