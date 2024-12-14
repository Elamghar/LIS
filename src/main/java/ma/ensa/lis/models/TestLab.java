package ma.ensa.lis.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestLab {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String category;
    @Expose
    private Date testDate;
    @Expose
    private Date expectedCompletionDate;
    @Expose
    private TestStatus status;
    //@JsonManagedReference @Expose
    //private List<Symptome> symptomes = new ArrayList<>();
    @Expose
    private String result;
    @Expose
    private Float price;
    // Constructor
    public TestLab(String id, String name, Date testDate, Float price, String category, Date expectedCompletionDate) {
        this.id = id;
        this.name = name;
        this.testDate = testDate;
        this.price = price;
        this.category = category;
        this.status = TestStatus.PENDING;  // Default status
        this.expectedCompletionDate = expectedCompletionDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestLab test = (TestLab) o;
        return Objects.equals(getName(), test.getName()) && Objects.equals(getCategory(), test.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", testDate=" + testDate +
                ", expectedCompletionDate=" + expectedCompletionDate +
                ", status=" + status +
                ", symptomes=" + //symptomes +
                ", result='" + result + '\'' +
                ", price=" + price +
                '}';
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }

    /**
     * Starts the test, setting the status to "In Progress".
     */
    public void startTest() {
        if(this.status == TestStatus.PENDING) {
            this.status = TestStatus.IN_PROGRESS;
        }
        else {
            System.out.println("Cannot start test: current status is " + this.status);
        }
    }

    /**
     * Completes the test, setting the status to "Completed".
     */
    public void completeTest() {
        if(this.status == TestStatus.IN_PROGRESS) {
            this.status = TestStatus.COMPLETED;
        }
        else {
            System.out.println("Cannot complete test: current status is " + this.status);
        }
    }

    /**
     * Calculate Remaining Days Until Expected Completion
     */
    public long daysUntilCompletion() {
        if (this.expectedCompletionDate == null) {
            return -1;
        }
        long diff = this.expectedCompletionDate.getTime() - new Date().getTime();
        return diff / (1000 * 60 * 60 * 24); // Convert milliseconds to days
    }


    public void notifyCompletion() {
        if (this.status == TestStatus.COMPLETED) {
            System.out.println("Test " + name + " is completed. Notification sent.");
        } else {
            System.out.println("Test " + name + " is not yet completed.");
        }
    }

    /**
     * Compares this test with another test to check if they are similar
     * based on certain criteria (e.g., same testName and category).
     */
    public boolean compareTest(TestLab otherTest) {
        return this.equals(otherTest);
    }

    /**
     * Adding a symptome to symptomes
     */
    /*
    public void addSymptome(Symptome symptome) {
        this.symptomes.add(symptome);
    }
     */


    public void getTestDetails() {
        System.out.println(this); // Using toString
    }


}

