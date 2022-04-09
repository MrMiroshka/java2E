package ru.miroshka.hw4;

import javafx.scene.image.Image;

public class Message {
    private final String nickName;
    private final String message;
    private final Image image;
    private final String time;
    private final boolean isOutgoing;
    private final boolean isImage;

    private Message(Builder builder) {
        this.nickName = builder.nickName;
        this.message = builder.text;
        this.image = builder.image;
        this.time = builder.time;
        this.isOutgoing = builder.isOutgoing;
        this.isImage = builder.isImage;
    }

    public String getMessage() {
        return message;
    }

    public String getNickName() {
        return nickName;
    }

    public Image getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public boolean isOutgoing() {
        return isOutgoing;
    }

    public boolean isImage() {
        return isImage;
    }

    public static class Builder {
        private String nickName;
        private String text;
        private Image image;
        private String time;
        private boolean isOutgoing;
        private boolean isImage;

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Builder isOutgoing(boolean isOutgoing) {
            this.isOutgoing = isOutgoing;
            return this;
        }

        public Builder isImage(boolean isImage) {
            this.isImage = isImage;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

}