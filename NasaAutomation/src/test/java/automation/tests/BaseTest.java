package automation.tests;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    protected Logger logger;

    @Before
    public void setup(){
        logger = LoggerFactory.getLogger(getClass());
    }
}
