package ma.ensa.lis.models;

import lombok.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestLab {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String category;
    @Expose
    private String description;
    @Expose
    private BooleanProperty selected = new SimpleBooleanProperty(false);

    public TestLab(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public TestLab(String s, String name, String category, String description, Object o) {
    }

    // Properties for TableView binding
    public BooleanProperty getSelectedProperty() {
        return selected;
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

    public String toJson() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }

    public boolean compareTest(TestLab otherTest) {
        return this.equals(otherTest);
    }

    public void getTestDetails() {
        System.out.println(this); // Using toString
    }


    public ObservableValue<String> nomProperty() {
        return null;
}

    public ObservableValue<String> categorieProperty() {
        return null;
    }
}

