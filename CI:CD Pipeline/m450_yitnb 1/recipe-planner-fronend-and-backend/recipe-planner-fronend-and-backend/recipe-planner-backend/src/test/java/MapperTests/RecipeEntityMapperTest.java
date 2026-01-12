package MapperTests;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapperImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

public class RecipeEntityMapperTest {

    private final RecipeEntityMapper recipeEntityMapper = new RecipeEntityMapperImpl();

    @Test
    public void testMapRecipeToEntity() {
        Recipe recipe = new Recipe(UUID.randomUUID(), "Pasta", "Delicious pasta recipe", "imageUrl", Collections.emptyList());
        RecipeEntity recipeEntity = recipeEntityMapper.domainToEntity(recipe);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipeEntity.getName()).isEqualTo(recipe.getName());
        softly.assertThat(recipeEntity.getDescription()).isEqualTo(recipe.getDescription());
        softly.assertThat(recipeEntity.getImageUrl()).isEqualTo(recipe.getImageUrl());
        softly.assertAll();
    }

    @Test
    public void testMapEntityToRecipe() {
        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(), "Pasta", "Delicious pasta recipe", "imageUrl", Collections.emptyList());
        Recipe recipe = recipeEntityMapper.entityToDomain(recipeEntity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(recipe.getName()).isEqualTo(recipeEntity.getName());
        softly.assertThat(recipe.getDescription()).isEqualTo(recipeEntity.getDescription());
        softly.assertThat(recipe.getImageUrl()).isEqualTo(recipeEntity.getImageUrl());
        softly.assertAll();
    }
}
