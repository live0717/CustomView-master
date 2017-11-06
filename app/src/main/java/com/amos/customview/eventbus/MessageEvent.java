package com.amos.customview.eventbus;

/**
 * @author : Amos
 * @Date : 2017-11-06
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
