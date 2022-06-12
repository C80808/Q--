package com.application.q;

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String name;
    public String password;
    public String ip;
    public Image userIcon;
    public boolean isOnline;
    public int establishConnectionPort;

    public User(int id, String name, String password, String ip, Image userIcon, boolean isOnline, int establishConnectionPort) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.ip = ip;
        this.userIcon = userIcon;
        this.isOnline = isOnline;
        this.establishConnectionPort = establishConnectionPort;
    }
}
