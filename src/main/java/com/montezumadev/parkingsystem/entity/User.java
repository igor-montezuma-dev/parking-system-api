package com.montezumadev.parkingsystem.entity;

import com.montezumadev.parkingsystem.utils.CPFGroup;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Basic
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "user_name", nullable = false, length = 45)
    private String name;

    @Basic
    @Size(max = 11)
    @Valid
    @CPF(groups = CPFGroup.class, message = "CPF inválido")
    @NotBlank(message = "CPF é obrigatório")
    @Column(name = "document", nullable = false, length = 11)
    private String document;



    @Basic
    @Size(max = 11)
    @NotBlank(message = "O telefone é obrigatório")
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;


    @Basic
    @Column(name = "loyalty_card", nullable = false)
    private boolean loyaltyCard;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;
}
