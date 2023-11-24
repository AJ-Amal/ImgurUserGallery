package com.image.photos.ImgurApiService.api;

public class ImgurApiResponse {

    private boolean success;

    private Data data;

    private String error;

    // Constructors, getters, and setters

    public ImgurApiResponse() {
        // Default constructor
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Inner class representing the 'data' field in the Imgur API response
    public static class Data {

        private String link;

        // Constructors, getters, and setters

        public Data() {
            // Default constructor
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

}
