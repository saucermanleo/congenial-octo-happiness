package org.zy.tinygame;

/**
 * @author : 生态环境-张阳
 * @date : 2020/8/28 0028 16:42
 */

public class User {
    private int userId;
    private String role;
    public MoveState moveState;
    public int blood;
    public String name;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
