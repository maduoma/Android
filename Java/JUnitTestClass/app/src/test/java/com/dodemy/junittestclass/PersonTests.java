package com.dodemy.junittestclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class PersonTests {
    @Test
    public void test1() throws Exception {
        Person p1 = new Person("Buffy", 26);
        Person p2 = new Person("Buffy", 26);
        assertSame(p1, p2);
    }
    @Test
    public void test2() throws Exception {
        Person p1 = new Person("Biff", 27);
        Person p2 = new Person("Biff", 27);
        assertEquals(p1, p2);
    }
}
