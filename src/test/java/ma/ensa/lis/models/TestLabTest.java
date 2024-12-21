package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestLabTest {

    @Test
    void testToJson() {
        TestLab test = new TestLab("Test1", "Category1", "Description");
        String json = test.toJson();
        assertNotNull(json);
        assertTrue(json.contains("Test1"));
    }

    @Test
    void testEquals() {
        TestLab test1 = new TestLab("Test1", "Category1", "Description");
        TestLab test2 = new TestLab("Test1", "Category1", "Description");
        assertEquals(test1, test2);
    }

    @Test
    void testHashCode() {
        TestLab test = new TestLab("Test1", "Category1", "Description");
        assertNotNull(test.hashCode());
    }

    @Test
    void testCompareTest() {
        TestLab test1 = new TestLab("Test1", "Category1", "Description");
        TestLab test2 = new TestLab("Test1", "Category1", "Description");
        assertTrue(test1.compareTest(test2));
    }
}

