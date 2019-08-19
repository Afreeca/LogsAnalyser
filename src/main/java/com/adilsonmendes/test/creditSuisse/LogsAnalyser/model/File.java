package com.adilsonmendes.test.creditSuisse.LogsAnalyser.model;

public class File {
  private Long id;
  private String name;
  private String type;
  private byte[] data;
  
  public File(String name, String type, byte[] data) {
    super();
    this.name = name;
    this.type = type;
    this.data = data;
  }
  
  public Long getId() {
    return id;
  }

  public void setName(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }
}
