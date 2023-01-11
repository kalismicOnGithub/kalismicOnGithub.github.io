package cz.cvut.fel.via.kalismic.mtm.entities;

import cz.cvut.fel.via.kalismic.mtm.enums.Importance;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = Task.FIND_ALL, query = "SELECT a FROM Task a")
})
public class Task implements Serializable {

    public static final String FIND_ALL = "Task.findAll";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime time;

    private Boolean done;

    private Importance importance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "cz.cvut.fel.via.kalismic.mtm.entities.Task[ id=" + id + " ]";
    }

}
