
package com.gauthier.lab.web.security.methodsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecurityLabService {

  public String callAdminMethod() {
    return adminMethod();
  }

  @PreAuthorize("hasRole('ADMIN')")
  public String adminMethod() {
    return "This is an admin method";
  }

}
