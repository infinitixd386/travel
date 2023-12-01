package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Visit {

    @JsonAlias({"visitId", "id"})
    private long id;
    private Attraction attraction;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate visitDate;

    public Visit(Attraction attraction, LocalDate visitDate) {
        this.attraction = attraction;
        this.visitDate = visitDate;
    }
}
