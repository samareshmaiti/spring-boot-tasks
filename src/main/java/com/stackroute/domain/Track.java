package com.stackroute.domain;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Track {
    //Declare of the variables
    @Id
    private int id;
    private String name;
    private String comment;

}
