package com.spacex.gandalf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String name;
    private String avatar;

    private Date createdTime;
    private Date modifiedTime;
    private Date DeletedTime;
}
