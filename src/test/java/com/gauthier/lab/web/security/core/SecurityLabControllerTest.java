package com.gauthier.lab.web.security.core;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityLabControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void adminEnpoint_whenAnonymous_returns401() throws Exception {
    mockMvc.perform(get("/api/security-lab/admin"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void adminEnpoint_whenUser_returns403() throws Exception {
    mockMvc.perform(get("/api/security-lab/admin")
        .with(user("admin").roles("USER")))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  void adminEnpoint_whenAdmin_returns200() throws Exception {
    mockMvc.perform(get("/api/security-lab/admin")
        .with(user("admin").roles("ADMIN")))
        .andExpect(status().isOk());
  }

}
