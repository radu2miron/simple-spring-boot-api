package edu.tucn.li.task;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<Task> getTasksByCompleted(boolean completed);
}
