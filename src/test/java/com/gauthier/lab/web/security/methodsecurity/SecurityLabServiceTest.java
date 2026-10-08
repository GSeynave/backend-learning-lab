package com.gauthier.lab.web.security.methodsecurity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class SecurityLabServiceTest {

  @Autowired
  SecurityLabService securityLabService;

  @Test
  @WithMockUser(roles = "ADMIN")
  void adminOnly_whenAdmin_isAllowed() {
    securityLabService.adminMethod();
  }

  @Test
  @WithMockUser(roles = "USER")
  void adminOnly_whenUser_isDenied() {
    assertThatThrownBy(
        () -> securityLabService.adminMethod())
        .isInstanceOf(AccessDeniedException.class);
  }
}
