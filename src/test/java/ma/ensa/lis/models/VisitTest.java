package ma.ensa.lis.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitTest {
    public  Visit v;
    TestLab t;
    @BeforeEach
    void setUp(){
        v=new Visit("g1234",new Date(2000-11-11),"testClass");
        t=new TestLab();
        v.addTest(t);
    }
    @Test
    void addTest() {
        assertEquals(1,v.getTests().size());
    }

    @Test
    void addPatient() {
    }

    @Test
    void toJson() {
    }

    @Test
    void setTests() {
    }

    @Test
    void setP() {
    }
}