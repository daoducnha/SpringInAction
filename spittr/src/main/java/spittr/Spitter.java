package spittr;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import javax.validation.constraints.*;

public class Spitter {

  private Long id;
  @NotNull
  @Size(min = 2, max = 30)
  private String firstName;
  @NotNull
  @Size(min = 2, max = 30)
  private String lastName;
  @NotNull
  @Size(min = 5, max = 30)
  private String username;
  @NotNull
  @Size(min = 5, max = 25)
  private String password;
  private String email;

  public Spitter() {
  }

  public Spitter(String firstName, String lastName, String username, String password,
      String email) {
    this(null, firstName, lastName, username, password, email);
  }

  public Spitter(Long id, String firstName, String lastName, String username,
      String password, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object that) {
    return EqualsBuilder
        .reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder
        .reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
  }
}