package helper;

import model.IEntity;


import org.junit.Test;

import static org.junit.Assert.*;


public class FactoryTest {
    
    @Test
    public void getModel() {
        IEntity model = new Factory().getModel(true);
        assertEquals("IndexCard",model.getClass().getSimpleName());
    }
}
