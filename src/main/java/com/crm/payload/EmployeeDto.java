package com.crm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min=3, message="Atleast 3 characters required")
    private String name;

    @Email
    private String emailId;

    @Size(min=10,max=10,message="Should be 10 digits")
    private String mobile;

    //    private Date date;
}