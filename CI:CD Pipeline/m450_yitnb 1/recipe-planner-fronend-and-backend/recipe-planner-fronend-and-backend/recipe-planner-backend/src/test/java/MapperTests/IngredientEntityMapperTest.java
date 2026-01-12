package MapperTests;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.mapper.IngredientEntityMapper;
import ch.tbz.recipe.planner.mapper.IngredientEntityMapperImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class IngredientEntityMapperTest {

    private final IngredientEntityMapper ingredientEntityMapper = new IngredientEntityMapperImpl();

    @Test
    public void testMapIngredientToEntity() {
        Ingredient ingredient = new Ingredient(UUID.randomUUID(), "Tomato", "Fresh", null, 2);
        IngredientEntity ingredientEntity = ingredientEntityMapper.domainToEntity(ingredient);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredientEntity.getName()).isEqualTo(ingredient.getName());
        softly.assertThat(ingredientEntity.getComment()).isEqualTo(ingredient.getComment());
        softly.assertThat(ingredientEntity.getAmount()).isEqualTo(ingredient.getAmount());
        softly.assertAll();
    }

    @Test
    public void testMapEntityToIngredient() {
        IngredientEntity ingredientEntity = new IngredientEntity(UUID.randomUUID(), "Tomato", "Fresh", null, 2);
        Ingredient ingredient = ingredientEntityMapper.entityToDomain(ingredientEntity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ingredient.getName()).isEqualTo(ingredientEntity.getName());
        softly.assertThat(ingredient.getComment()).isEqualTo(ingredientEntity.getComment());
        softly.assertThat(ingredient.getAmount()).isEqualTo(ingredientEntity.getAmount());
        softly.assertAll();
    }
}
