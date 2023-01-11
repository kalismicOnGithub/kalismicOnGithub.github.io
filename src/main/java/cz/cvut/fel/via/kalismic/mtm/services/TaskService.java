package cz.cvut.fel.via.kalismic.mtm.services;

import cz.cvut.fel.via.kalismic.mtm.entities.Task;
import cz.cvut.fel.via.kalismic.mtm.exeptions.ForbiddenException;
import cz.cvut.fel.via.kalismic.mtm.exeptions.NotFoundException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TaskService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private TaskStackService stackService;

    public List<Task> getAllTasks() {
        return em.createNamedQuery(Task.FIND_ALL, Task.class).getResultList();
    }

    public Task saveTask(Task task) {
        if (task.getId() == 0) {
            em.persist(task);
            em.flush();
            em.refresh(task);
        } else {
            em.merge(task);
        }
        return task;
    }

    public Task getTaskById(Long id) {
        Task result = em.find(Task.class, id);
        if (result != null) {
            return result;
        }
        throw new NotFoundException(Task.class.getName() + " with id " + id + " was not found!");
    }

    private void deleteTaskById(Long id) {
        Task task = em.find(Task.class, id);
        if (task != null) {
            em.remove(task);
        } else {
            throw new NotFoundException(Task.class.getName() + " with id " + id + " was not found!");
        }
    }

    public void deleteTask(Long stackId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        if (stackService.contains(stackId, taskId)) {
            stackService.removeTask(stackId, task);
            deleteTaskById(taskId);
        } else {
            throw new ForbiddenException(Task.class.getName() + " with id " + taskId + " cannot be deleted!");
        }
    }
}
