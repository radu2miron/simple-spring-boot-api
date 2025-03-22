package edu.tucn.li.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
@Data // lombok annotation, creates equals(), hashCode() and toString at compile time
public class TaskDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY) // don't show id in swagger's request body
    private Integer id;
    @NotEmpty // not empty validation
    private String description;
    @NotEmpty // not empty validation
    @Schema(defaultValue = "false") // set default value to 'false' in swagger's request body
    private boolean completed;
}
