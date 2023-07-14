package test.ngocpt.auth.entity;

import lombok.Getter;

public enum Permission {
    POST("post", 0), COMMENT("comment",1);
    @Getter
    private String label;

    @Getter
    private Integer value;

    Permission(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public static Permission getRole(Integer value){
        for (Permission role: Permission.values()){
            if (role.getValue().equals(value)){
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role");
    }
}
