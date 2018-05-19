package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Do wywalenia; ważne tylko ze względu na sposób traktowania daty
 */

//@Entity
@Data
//@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserOld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userid;
    String username;

    @Type(type = "date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    Date birthdate;
    Boolean active;

    @PrePersist
    void defaultInit() {
        if (active==null) active=true;
    }

}
