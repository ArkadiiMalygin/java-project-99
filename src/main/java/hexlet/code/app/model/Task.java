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
@Table(name = "tasks")
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

    @ToString.Include
    @NotNull
    @ManyToOne
    private TaskStatus taskStatus;

    @ToString.Include
    @ManyToOne
    private User assignee;

    @ManyToMany(mappedBy = "tasks")
    private List<Label> labels = new ArrayList<>();

    @CreatedDate
    private LocalDate createdAt;

    public void addLabel(Label label) {
        labels.add(label);
        label.getTasks().add(this);
    }

    public void removeLabel(Label label) {
        labels.remove(label);
        label.getTasks().remove(this);
    }
}
