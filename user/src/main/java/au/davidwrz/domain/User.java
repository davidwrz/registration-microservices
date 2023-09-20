package au.davidwrz.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
