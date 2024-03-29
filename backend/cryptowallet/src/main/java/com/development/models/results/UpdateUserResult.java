package com.development.models.results;

import java.util.Objects;

public class UpdateUserResult {
    private String email;

    private String name;

    private String avatar;

    private String avatarUrl;

    private String password;

    private String role;

    private boolean isAdmin;

    public UpdateUserResult() {

    }

    public UpdateUserResult(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.avatar = builder.avatar;
        this.avatarUrl = builder.avatarUrl;
        this.password = builder.password;
        this.role = builder.role;
        this.isAdmin = builder.isAdmin;
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
        UpdateUserResult that = (UpdateUserResult) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UpdateUserResult{" +
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
        private String email;

        private String name;

        private String avatar;

        private String avatarUrl;

        private String password;

        private String role;

        private boolean isAdmin;
        private Builder() {

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


        public UpdateUserResult build() {
            return new UpdateUserResult(this);
        }
    }
}
