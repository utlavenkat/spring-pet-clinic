package venkat.org.springframework.petclinic.controllers;

import org.junit.Assert;
import org.junit.Test;

public class IndexControllerTest {

    @Test
    public void index() {
        IndexController indexController = new IndexController();
        String viewName = indexController.index();
        Assert.assertEquals("View Name not matched", "index", viewName);
    }
}