package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerForm {
    private Long id;
    private String name;
    private int age;
    private String address;
    private MultipartFile img;
    private MultipartFile audio;

    public CustomerForm() {
    }

    public CustomerForm(Long id, String name, int age, String address, MultipartFile img, MultipartFile audio) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.img = img;
        this.audio = audio;
    }

    public MultipartFile getAudio() {
        return audio;
    }

    public void setAudio(MultipartFile audio) {
        this.audio = audio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
