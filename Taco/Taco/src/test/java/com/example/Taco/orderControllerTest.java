package com.example.Taco;

// import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.example.Taco.controller.orderController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(orderController.class) 
public class orderControllerTest {

 @Autowired
 private MockMvc mockMvc; 
 
 @Test
 public void testOrderFormPage() throws Exception {
 mockMvc.perform(post("/orders")).andExpect(status().isOk()).andExpect(view().name("orderForm"));
 }
}