package com.firsttest.autho.entities;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CentreList {
    @Id
    private String id;
    private String nom;
    private String ip;
    private String serveur;

}
