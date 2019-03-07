package no.noroff.Task21.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "class_info")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", unique = true)
    private int id;

    @Column(name = "class_name", nullable = false, unique = true)
    private String class_name;

    @Column(name = "ability", nullable = false)
    private String ability;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Class{");
        sb.append("id=").append(id);
        sb.append(", class_name='").append(class_name).append('\'');
        sb.append(", ability='").append(ability).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
