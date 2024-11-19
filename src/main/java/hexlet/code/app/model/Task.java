package hexlet.code.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Task {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;

    @ToString.Include
    @NotBlank
    @Size(min = 1)
    private String name;

    private Long index;

    @ToString.Include
    @Column(columnDefinition = "TEXT")
    private String description;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_status_id")
    private TaskStatus taskStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToMany
    @JoinTable(name = "task_labels",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Label> labels = new ArrayList<>();

    @CreatedDate
    private LocalDate createdAt;

    public void setLabels(List<Label> labelsToAdd) {
//        labelsToAdd.forEach(l -> l.addTask(this));
        labels.addAll(labelsToAdd);
    }

    public void removeLabels(List<Label> labelsToRemove) {
//        labelsToRemove.forEach(l -> l.removeTask(this));
        labels.removeAll(labelsToRemove);
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    public void removeLabel(Label label) {
        labels.remove(label);
    }
}
