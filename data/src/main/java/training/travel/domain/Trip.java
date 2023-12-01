package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Trip {

    @JsonAlias({"tripId", "id"})
    private long id;
    private User user;
    private Destination destination;
    private List<Visit> visits;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public Trip(User user, Destination destination, List<Visit> visits, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.destination = destination;
        this.visits = visits;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
