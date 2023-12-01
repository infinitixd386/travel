package training.travel.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    @JsonAlias({"userId", "id"})
    long id;
    private String fullName;
    private Credentials credentials;
    private Role role;

    public User(String fullName, Credentials credentials, Role role) {
        this.fullName = fullName;
        this.credentials = credentials;
        this.role = role;
    }

    public boolean isAdmin() {
        return this.role.equals(Role.ADMIN);
    }

}
