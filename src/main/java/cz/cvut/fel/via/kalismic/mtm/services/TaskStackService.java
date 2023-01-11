package cz.cvut.fel.via.kalismic.mtm.services;

import cz.cvut.fel.via.kalismic.mtm.entities.Task;
import cz.cvut.fel.via.kalismic.mtm.entities.TaskStack;
import cz.cvut.fel.via.kalismic.mtm.exeptions.NotFoundException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TaskStackService {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private TaskService taskService;

    public List<TaskStack> getAllTaskStacks() {
        return em.createNamedQuery(TaskStack.FIND_ALL, TaskStack.class).getResultList();
    }

    public TaskStack saveTaskStack(TaskStack stack) {
        if (stack.getId() == 0) {
            em.persist(stack);
            em.flush();
            em.refresh(stack);
        } else {
            em.merge(stack);
        }
        return stack;
    }

    public TaskStack getTaskStackById(Long id) throws Exception {
        TaskStack result = em.find(TaskStack.class, id);
        if (result != null) {
            return result;
        }
        throw new NotFoundException(TaskStack.class.getName() + " with id " + id + " was not found!");
    }

    public void addTask(Long stackId, Task task) {
        TaskStack stack = em.find(TaskStack.class, stackId);
        stack.addTask(task);
        saveTaskStack(stack);
    }

    public void removeTask(Long stackId, Task task) {
        TaskStack stack = em.find(TaskStack.class, stackId);
        stack.removeTask(task);
    }

    public List<Task> getAllTasksByStackId(Long stackId) throws Exception {
        TaskStack stack = getTaskStackById(stackId);
        return stack.getStack();
    }

    public Boolean contains(Long stackId, Long taskId) throws Exception {
        TaskStack stack = getTaskStackById(stackId);
        Task task = taskService.getTaskById(taskId);
        if (stack.getStack().contains(task)) {
            return true;
        }
        return false;
    }

}
