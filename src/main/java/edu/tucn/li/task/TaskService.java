package edu.tucn.li.task;

import edu.tucn.li.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:radu.miro@aut.utcluj.ro">Radu Miron</a>
 */
@Service // creates one instance of this class
@Slf4j // adds the 'log(ger)' instance to this class
public class TaskService {

    @Autowired // injects the TaskRepository instance
    private TaskRepository taskRepository;
    @Autowired // injects the TaskMapper instance
    private TaskMapper taskMapper;

    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public TaskDTO create(TaskDTO taskDTO) {
        TaskDTO createdTaskDTO =
                taskMapper.taskToDto(taskRepository.save(taskMapper.dtoToTask(taskDTO)));
        log.info("Saved a new task " + createdTaskDTO.toString());

        return createdTaskDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true) // the method is executed in a transaction
    public TaskDTO get(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Task with id %s not found", id)));
        log.info("Read task with id " + task.getId());

        return taskMapper.taskToDto(task);
    }

    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public TaskDTO update(Integer id, TaskDTO taskDTO) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException(String.format("Task with id %s not found", taskDTO.getId()));
        }

        Task updatedTask = taskMapper.dtoToTask(taskDTO);
        updatedTask.setId(id);

        TaskDTO updatedTaskDTO =
                taskMapper.taskToDto(taskRepository.save(updatedTask));

        log.info("Updated task with id " + updatedTaskDTO.getId());

        return updatedTaskDTO;
    }

    @Transactional(rollbackFor = Exception.class) // the method is executed in a transaction
    public TaskDTO delete(Integer id) {
        Task deletedTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Task with id %s not found", id)));
        TaskDTO deletedTaskDTO = taskMapper.taskToDto(deletedTask);

        taskRepository.delete(deletedTask);
        log.info("Deleted task with id " + deletedTask.getId());

        return deletedTaskDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true) // the method is executed in a transaction
    public List<TaskDTO> find(boolean completed) {
        List<Task> tasks = taskRepository.getTasksByCompleted(completed);
        log.info("Read all tasks with completed = " + completed);

        return tasks.stream().map(taskMapper::taskToDto).collect(Collectors.toList());
    }
}
