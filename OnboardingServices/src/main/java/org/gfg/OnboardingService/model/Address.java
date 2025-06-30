package org.gfg.OnboardingService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String streetName;
    private String city;
    private String district;
    private int pincode;

//    @OneToOne(mappedBy = "user_id")
//    private int userId;

}
