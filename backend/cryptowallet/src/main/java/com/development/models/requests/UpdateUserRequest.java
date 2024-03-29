package com.development.models.requests;

import java.util.Objects;

public class UpdateUserRequest {
    private String token;
    private String email;

    private String name;

    private String avatar;

    private String avatarUrl;

    private String password;

    private String role;

    private boolean isAdmin;

    public UpdateUserRequest() {

    }

    public UpdateUserRequest(Builder builder) {
        this.token = builder.token;
        this.email = builder.email;
        this.name = builder.name;
        this.avatar = builder.avatar;
        this.avatarUrl = builder.avatarUrl;
        this.password = builder.password;
        this.role = builder.role;
        this.isAdmin = builder.isAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateUserRequest that = (UpdateUserRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private String email;

        private String name;

        private String avatar;

        private String avatarUrl;

        private String password;

        private String role;

        private boolean isAdmin;
        private Builder() {

        }

        public Builder withToken(String useToken) {
            this.token = useToken;
            return this;
        }

        public Builder withEmail(String useEmail) {
            this.email = useEmail;
            return this;
        }

        public Builder withName(String useName) {
            this.name = useName;
            return this;
        }

        public Builder withAvatar(String useAvatar) {
            this.avatar = useAvatar;
            return this;
        }

        public Builder withAvatarUrl(String useAvatarUrl) {
            this.avatarUrl = useAvatarUrl;
            return this;
        }

        public Builder withPassword(String usePassword) {
            this.password = usePassword;
            return this;
        }

        public Builder withRole(String useRole) {
            this.role = useRole;
            return this;
        }

        public Builder withIsAdmin(boolean useIsAdmin) {
            this.isAdmin = useIsAdmin;
            return this;
        }


        public UpdateUserRequest build() {
            return new UpdateUserRequest(this);
        }
    }
}
