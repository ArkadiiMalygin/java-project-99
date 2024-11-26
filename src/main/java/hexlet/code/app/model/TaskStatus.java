package hexlet.code.app.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
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
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "task_statuses")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class TaskStatus {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @ToString.Include
    @NotBlank
    @Size(min = 1)
    private String name;

    @Column(unique = true, name = "slug")
    @ToString.Include
    @NotBlank
    @Size(min = 1)
    @EqualsAndHashCode.Include
    private String slug;

    @OneToMany(mappedBy = "taskStatus")
    private List<Task> tasks = new ArrayList<>();

    @CreatedDate
    private Instant createdAt;

    public void addTask(Task task) {
        tasks.add(task);
        task.setTaskStatus(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setTaskStatus((TaskStatus) null);
    }
}
