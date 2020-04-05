package com.firsttest.autho.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utilisateur {
@Id
    private String id;
    private String Nom;
    private  String Prenom;
    private  String CIN;
    private String email;
    private String password;
    private ArrayList<String>liste_payeurs;

    public Utilisateur(String Nom,String Prenom) {
        this.Nom=Nom;
        this.Prenom=Prenom;
    }
}

