package uz.pdp.restfullcodingsite.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Prob {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String question;

    private String solution;

    @Column(nullable = false, unique = true)
    private String code;

    private boolean completed;

    @ManyToOne
    private Theme theme;


}
