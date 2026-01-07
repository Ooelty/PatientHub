package com.example.HeartdiseaseApp.entities;






import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor

@NoArgsConstructor//importantHibernate a besoin d'un constructeur sans paramètres pour créer les objets depuis la base de données
@Table(name="patients_data")
public class Patients {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private int age;// en jours
private int gender;//1 femme 2 homme
private int height;
private double weight;

private int ap_hi;
private int ap_lo;
private int cholesterol;// 1 normal // 2 above normal //3 very high
private int gluc;// 1 normal // 2 above normal // 3 very high
private int smoke; // 0 non // 1 oui 
private int alco;// 0 non 1 oui
private int active;// 0 non //1  oui 
private int cardio;// 0 non malade // 1 malade
}
