package cz.cvut.fel.via.kalismic.mtm.entities;

import cz.cvut.fel.via.kalismic.mtm.enums.StackType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taskstack")
@NamedQueries({
    @NamedQuery(name = TaskStack.FIND_ALL, query = "SELECT a FROM TaskStack a")
})
public class TaskStack implements Serializable {

    public static final String FIND_ALL = "TaskStack.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> stack;

    private StackType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Task> getStack() {
        return stack;
    }

    public void setStack(List<Task> stack) {
        this.stack = stack;
    }

    public StackType getType() {
        return type;
    }

    public void setType(StackType type) {
        this.type = type;
    }

    public void addTask(Task task) {
        stack.add(task);
    }

    public void removeTask(Task task) {
        stack.remove(task);
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.via.kalismic.mtm.entities.TaskStack[ id=" + id + " ]";
    }

}
