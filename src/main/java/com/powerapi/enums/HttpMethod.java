package com.powerapi.enums;

public enum HttpMethod {
    GET("GET"), POST("POST");

    private final String text;

    /**
     * @param text
     */
    HttpMethod(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
