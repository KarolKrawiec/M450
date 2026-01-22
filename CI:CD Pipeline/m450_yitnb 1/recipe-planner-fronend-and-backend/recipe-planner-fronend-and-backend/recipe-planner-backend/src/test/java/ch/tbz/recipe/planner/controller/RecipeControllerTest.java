package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.RecipePlannerApplication;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RecipePlannerApplication.class)
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetRecipes() throws Exception {
        when(recipeService.getRecipes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetRecipeById() throws Exception {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe(id, "Pasta", "Delicious pasta recipe", "imageUrl", Collections.emptyList());
        when(recipeService.getRecipeById(id)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/recipe/{recipeId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(recipe.getName()));
    }

    @Test
    public void testAddRecipe() throws Exception {
        Recipe newRecipe = new Recipe(UUID.randomUUID(), "Pasta", "Delicious pasta recipe", "imageUrl",
                Collections.emptyList());
        when(recipeService.addRecipe(newRecipe)).thenReturn(newRecipe);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newRecipe)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newRecipe.getName()));
    }
}
