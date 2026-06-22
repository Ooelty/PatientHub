package com.example.heartdiseaseapp.dtos;
import lombok.Data;
@Data
public class PatientDto {
private Long id;
private int age;
private int gender;

 private int height;
    private double weight;

    private int ap_hi;
    private int ap_lo;
    private int cholesterol;// 1 normal // 2 above normal //3 very high
    private int gluc;// 1 normal // 2 above normal // 3 very high
    private int smoke; // 0 non // 1 oui 
    private int alco;// 0 non 1 oui
    private int active;// 0 non //1  oui 
    private int cardio;
}