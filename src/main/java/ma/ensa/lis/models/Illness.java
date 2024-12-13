package ma.ensa.lis.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.management.ConstructorParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Illness {
    private String Id;
    private String nom;
    private String description;
    private List<TestLab> commonTests;
    private List<String> symptoms;

    public Illness(String Id, String nom, String description){
        this.Id= Id;
        this.nom= nom;
        this.description= description;
        this.symptoms= new ArrayList<>();
        this.commonTests= new ArrayList<>();
    }

    public void addSymptom(String symptom){
        if(!symptoms.contains(symptom)){
            symptoms.add(symptom);
        }
    }

    public void addCommonTest(TestLab test){
        if(!commonTests.contains(test)){
            commonTests.add(test);
        }
    }

    @Override
    public String toString() {
        return "Sickness{" +
                "sicknessID='" + Id + '\'' +
                ", name='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", symptoms=" + symptoms +
                ", commonTests=" + commonTests +
                '}';
    }

    // Méthode equals pour comparer les objets Sickness
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Illness illness = (Illness) o;
        return Objects.equals(Id, illness.Id) &&
                Objects.equals(nom, illness.nom) &&
                Objects.equals(description, illness.description) &&
                Objects.equals(commonTests, illness.commonTests) &&
                Objects.equals(symptoms, illness.symptoms);
    }

    // Méthode hashCode pour une utilisation dans des collections comme HashMap
    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, description, commonTests, symptoms);
    }
}