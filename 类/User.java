package com.application.q;

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements Serializable {
    int id;
    String name;
    String password;
    String ip;
    Image userIcon;
    boolean isOnline;
    int establishConnectionPort;
}
