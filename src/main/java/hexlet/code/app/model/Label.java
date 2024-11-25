package hexlet.code.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.Instant;


import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "labels")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Label {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @ToString.Include
    @NotBlank
    @Size(min = 3)
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String name;

//    @ManyToMany(cascade = CascadeType.ALL)
////    @JoinTable(name = "task_labels",
////            joinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"),
////            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
//    private List<Task> tasks = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

//    public void addTask(Task task) {
//        tasks.add(task);
//    }
//
//    public void removeTask(Task task) {
//        tasks.remove(task);
//    }
//
//    public void setTasks(List<Task> tasksToAdd) {
//        tasksToAdd.forEach(t -> t.addLabel(this));
//        tasks.addAll(tasksToAdd);
//    }
//
//    public void removeTasks(List<Task> tasksToRemove) {
//        tasksToRemove.forEach(t -> t.removeLabel(this));
//        tasks.removeAll(tasksToRemove);
//    }
}
