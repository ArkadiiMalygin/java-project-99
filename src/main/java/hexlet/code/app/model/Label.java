package hexlet.code.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "labels")
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_labels",
            joinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();

    @CreatedDate
    private LocalDate createdAt;

    public void addTask(Task task) {
        tasks.add(task);
        task.getLabels().add(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getLabels().remove(this);
    }
}
