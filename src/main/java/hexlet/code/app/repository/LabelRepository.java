package hexlet.code.app.repository;

import hexlet.code.app.model.Label;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>, JpaSpecificationExecutor<Label> {
    Optional<Label> findByName(String name);
}