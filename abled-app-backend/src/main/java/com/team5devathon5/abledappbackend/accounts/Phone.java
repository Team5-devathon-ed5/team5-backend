package com.team5devathon5.abledappbackend.accounts;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name ="phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="Phone_Code")
    @Pattern(regexp = "^\\+[1-9]\\d{0,2}",
            message = "Must be a valid international Phone code preceded by '+'.")
    private String phoneCode;

    @Column(name="phone_number")
    @Pattern(regexp = "^\\d{1,4}-\\d{1,4}-\\d{1,10}$",
            message = "Must be a valid phone number.")
    private String phoneNumber;

    @Column(name="share_phone")
    private Boolean sharePhone;

    @OneToOne (mappedBy = "phone")
    private Account account;

}
