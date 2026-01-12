package ch.tbz.recipe.planner.service;

import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeEntityMapper mapper;
    private final RecipeRepository repository;

    public RecipeService(RecipeEntityMapper mapper, RecipeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<Recipe> getRecipes() {
        List<RecipeEntity> recipeEntities = repository.findAll();
        return recipeEntities.stream()
                .map(mapper::entityToDomain)  // Verwende die Methode 'entityToDomain'
                .toList();
    }

    public Recipe getRecipeById(UUID recipeId) {
        return repository.findById(recipeId)
                .map(mapper::entityToDomain)  // Verwende Optional.map(), um null zu vermeiden
                .orElse(null);
    }

    public Recipe addRecipe(Recipe recipe) {
        RecipeEntity createdRecipe = repository.save(mapper.domainToEntity(recipe));  // Verwende 'domainToEntity'
        return mapper.entityToDomain(createdRecipe);
    }
}
