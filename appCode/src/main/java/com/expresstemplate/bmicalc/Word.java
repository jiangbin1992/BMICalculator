package com.expresstemplate.bmicalc;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Word implements Serializable {
	
	String id;
	String bmi;

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBmi() {
		return bmi;
	}

	public void setBmi(String bmi) {
		this.bmi = bmi;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	String height;
	String weight;


	
}
