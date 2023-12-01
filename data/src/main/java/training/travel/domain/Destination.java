package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class Destination {

    @JsonAlias({"destinationId", "id"})
    private long id;
    private String location;
    private List<Attraction> attractions;

    public Destination(long id, String location) {
        this.id = id;
        this.location = location;
    }

    public void addAttraction(Attraction attraction){
        attractions.add(attraction);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return id == that.id && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}
