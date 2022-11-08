package org.kook.spring.boot.exploration;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {}

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = {"/", "/welcome"})
    void shouldReturnWelcomeMessage(String uri) {
        String expectedContent = "Welcome you at Test Corp Name! Am I under testing?";
        mvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

}
