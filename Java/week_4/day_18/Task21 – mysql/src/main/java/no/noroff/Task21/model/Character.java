package no.noroff.Task21.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "character_info")
public class Character implements Serializable {
    @Id
    @Column(name = "_id", unique = true)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "name", unique = true, nullable = false)
    private String character_name;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "className", nullable = false)
    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", character_name='").append(character_name).append('\'');
        sb.append(", level=").append(level);
        sb.append(", className='").append(className).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
