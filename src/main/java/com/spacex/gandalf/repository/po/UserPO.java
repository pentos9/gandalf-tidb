package com.spacex.gandalf.repository.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
public class UserPO {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private String name;
    private String avatar;
    private Integer deleted;

    private Date createdTime;
    private Date modifiedTime;
    private Date DeletedTime;
}
