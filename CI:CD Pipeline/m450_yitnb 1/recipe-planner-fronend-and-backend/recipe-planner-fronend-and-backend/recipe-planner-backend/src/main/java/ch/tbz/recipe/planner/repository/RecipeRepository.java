package ch.tbz.recipe.planner.repository;


import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findById(UUID id);
    List<RecipeEntity> findAll();

}
