package edu.tucn.li.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
@Entity // maps class to DB table, object to table row, attribute to column
@Data // lombok annotation, creates equals(), hashCode() and toString at compile time
public class Task {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Integer id;
    private String description;
    private boolean completed;
}
