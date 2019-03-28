package com.spacex.gandalf.repository.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
public class UserPO {
    private Long id;
    private String name;
    private String avatar;

    private Date createdTime;
    private Date modifiedTime;
    private Date DeletedTime;
}
