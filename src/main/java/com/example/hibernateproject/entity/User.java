package com.example.hibernateproject.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile"})
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @AttributeOverride(name = "birthDate", column = @Column (name = "birth_date"))
    private PersonalInfo personalInfo;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

}
