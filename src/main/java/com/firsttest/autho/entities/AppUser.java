package com.firsttest.autho.entities;


import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AppUser {
    @Id
    @BsonProperty("id")
    private String id;
    @Column (unique = true)
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean actived;
    @ManyToMany (fetch = FetchType.EAGER)
    private Collection<AppRole> roles=new ArrayList<>();

}
