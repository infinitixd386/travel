package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class Attraction {

    @JsonAlias({"attractionId", "id"})
    private long id;
    private Destination destination;
    private String name;
    private String description;
    private Category category;

    public Attraction(Destination destination, String name, String description, Category category) {
        this.destination = destination;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category);
    }
}
