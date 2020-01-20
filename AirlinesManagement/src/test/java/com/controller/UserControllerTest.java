package com.controller;


import com.airlinesmanagement.AirlinesManagement;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AirlinesManagement.class)
public class UserControllerTest {
private MockMvc mockMvc;

@Autowired
private WebApplicationContext webApplicationContext;

@Before
    public void setup(){
this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
}

@Test
@Ignore
public void getAllUsersTest() throws Exception{
mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
}

@Test
public void getUserbyId() throws Exception{
mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/user/getUser?userid=1")
.accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("userid").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("firstname").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("lastname").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("contactnumber").exists())
        .andDo(print());

}


}
