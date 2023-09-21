package com.example.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import com.example.Taco.controller.registrationController;

@WebMvcTest(registrationController.class)
public class registrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testregistrationPage() throws Exception{
        mockMvc.perform(post("/register")).andExpect(status().isOk()).andExpect(view().name("registration"));
    }
}
