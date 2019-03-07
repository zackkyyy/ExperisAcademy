package no.noroff.Task21.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "character_info")
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id", unique = true)
    private int id;

    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private String username;

    @Column(name = "char_name", unique = true, nullable = false)
    private String character_name;

    @Column(name = "level", nullable = false)
    private int level;

    @JoinColumn(name = "class_name", referencedColumnName = "class_name", nullable = false)
    private String class_name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", character_name='").append(character_name).append('\'');
        sb.append(", level=").append(level);
        sb.append(", class_name='").append(class_name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
