package matser2.istic.mmmback.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Role {

    private String roleName;


    public Role() {}

    public Role(String roleName, String department) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}

