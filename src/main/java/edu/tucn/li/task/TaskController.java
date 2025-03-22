package edu.tucn.li.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
@RestController // creates an instance of the current class
@RequestMapping("/tasks") // maps the requests starting with '/tasks' to this controller
public class TaskController {

    @Autowired // creates the injection of TaskService instance
    private TaskService taskService;

    @PostMapping // maps the '/tasks' POST requests to this method
    @Operation(summary = "Save a new task") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public TaskDTO create(@RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @GetMapping // maps the '/tasks' GET requests to this method
    @Operation(summary = "Get a task by id") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public TaskDTO get(@RequestParam Integer id) {
        return taskService.get(id);
    }

    @PutMapping // maps the '/tasks' PUT requests to this method
    @Operation(summary = "Update a task") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public TaskDTO update(@RequestParam Integer id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }

    @DeleteMapping // maps the '/tasks' GET requests to this method
    @Operation(summary = "Delete a task by id") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public TaskDTO delete(@RequestParam Integer id) {
        return taskService.delete(id);
    }

    @GetMapping("/find") // maps the '/tasks' GET requests to this method
    @Operation(summary = "Find tasks by status") // swagger description
    @SecurityRequirement(name = "Bearer Authentication") // swagger auth
    public List<TaskDTO> find(@RequestParam boolean completed) {
        return taskService.find(completed);
    }
}
