package es.cheste;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id
    private int id;

    private String userName;
    private String userMessage;

    public User() {
        // Required empty constructor
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
