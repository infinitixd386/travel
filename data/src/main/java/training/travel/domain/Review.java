package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Review {

    @JsonAlias({"reviewId", "id"})
    long id;
    private User user;
    private int rating;
    private String comment;
    private Attraction attraction;

    public Review(User user, int rating, String comment, Attraction attraction) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.attraction = attraction;
    }
}
