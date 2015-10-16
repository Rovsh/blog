package com.damienfremont.blog;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/person")
public class PersonJAXRS {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Person get() {
    return data;
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @ValidateOnExecution
  public void post(@Valid Person data) {
    this.data = data;
  }
  
  // MODEL
  
  static class Person implements Serializable {
    private static final long serialVersionUID = 9167120287441116359L;
    @NotNull
    public String firstName;
    @NotNull
    public String lastName;
    @NotNull
    @Past
    public Calendar birthDate;
    public Boolean active;
  }
  
  // MOCK

  static Person data;
  static {
    data = new Person();
    data.firstName = "Albert";
    data.lastName = "Einstein";
    data.birthDate = null;
    data.active = Boolean.FALSE;
  }

}