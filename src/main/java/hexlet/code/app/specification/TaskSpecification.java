package hexlet.code.app.specification;

import hexlet.code.app.model.Task;
import hexlet.code.app.dto.TaskParamsDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TaskSpecification {

    public Specification<Task> build(TaskParamsDTO params) {
        return withTitleCont(params.getTitleCont())
                .and(withAssigneeId(params.getAssigneeId()))
                .and(withStatus(params.getStatus()))
                .and(withLabelId(params.getLabelId()));
    }

    private Specification<Task> withTitleCont(String titleCont) {
        return ((root, query, criteriaBuilder) -> titleCont == null
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + titleCont + "%"));
    }

    private Specification<Task> withAssigneeId(Long assigneeId) {
        return ((root, query, criteriaBuilder) -> assigneeId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("assignee").get("id"), assigneeId));
    }

    private Specification<Task> withStatus(String status) {
        return ((root, query, criteriaBuilder) -> status == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("taskStatus").get("slug"), status));
    }

    private Specification<Task> withLabelId(Long labelId) {
        return ((root, query, criteriaBuilder) -> labelId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.join("labels").get("id"), labelId));
    }
}
