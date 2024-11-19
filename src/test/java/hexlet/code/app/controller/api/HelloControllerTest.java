package hexlet.code.app.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

     @Test
     public void testIndex() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get("/welcome"))
                 .andExpect(MockMvcResultMatchers.status().isOk());
     }

}
