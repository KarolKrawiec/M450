package ch.tbz.m450.testing.tools.controller;

import ch.tbz.m450.testing.tools.repository.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateStudent() throws Exception {
        Student student = new Student("Test Name", "test@email.com");
        
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
    }
}
