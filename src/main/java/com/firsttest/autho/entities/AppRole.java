package com.firsttest.autho.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppRole {
    @Id
    private String id;
    private String roleName;
}
