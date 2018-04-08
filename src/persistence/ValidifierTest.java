package persistence;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidifierTest {
    private Validifier validifier;
    @Before
    public void setUp() throws Exception {
        this.validifier = new Validifier();
    }

    @Test
    public void checkName() {
        boolean res = this.validifier.checkName("Person",1,2);
        assertFalse(res);
    }

    @Test
    public void checkNumber() {
        boolean res = this.validifier.checkNumber("1423");
        assertTrue(res);
    }
}