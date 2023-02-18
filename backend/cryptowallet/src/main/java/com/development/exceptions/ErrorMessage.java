package com.development.exceptions;

public class ErrorMessage {
    private int status;
    private String error;
    private String contentType;

    public ErrorMessage() {

    }
    public ErrorMessage(Builder builder) {
        this.status = builder.status;
        this.error = builder.error;
        this.contentType = builder.contentType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        contentType = contentType;
    }

    @Override
    public String toString() {
        return "(" +
                "\"status\":" + status +
                ",\"error\":\"" + error +
                "\",\"Content-type\":\"" + contentType + "\"}}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int status;
        private String error;
        private String contentType;
        private Builder() {

        }

        public Builder withStatus(int useStatus) {
            this.status = useStatus;
            return this;
        }

        public Builder withError(String useError) {
            this.error = useError;
            return this;
        }

        public Builder withContentType(String useContentType) {
            this.contentType = useContentType;
            return this;
        }


        public ErrorMessage build() {
            return new ErrorMessage(this);
        }
    }


}
