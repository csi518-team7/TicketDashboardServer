package us.team7pro.login.Model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Ticket {
    @Id @GeneratedValue
    private Long id;
    private @NonNull String name;
}
