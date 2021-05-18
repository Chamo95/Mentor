package com.MentorMate.performancereports;

public class Data implements Comparable<Data> {
	
private String name;
private Integer totalSales;
private Integer salesPeriod;
private double experienceMultiplier;
private double score;


public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getTotalSales() {
	return totalSales;
}

public void setTotalSales(Integer totalSales) {
	this.totalSales = totalSales;
}

public Integer getSalesPeriod() {
	return salesPeriod;
}

public void setSalesPeriod(Integer salesPeriod) {
	this.salesPeriod = salesPeriod;
}

public double getExperienceMultiplier() {
	return experienceMultiplier;
}

public void setExperienceMultiplier(double experienceMultiplier) {
	this.experienceMultiplier = experienceMultiplier;
}

@Override
public String toString() {
	return "Data [name=" + name + ", totalSales=" + totalSales + ", salesPeriod=" + salesPeriod
			+ ", experienceMultiplier=" + experienceMultiplier + "]";
}


public double getScore() {
	return score;
}

public void setScore(double score) {
	this.score = score;
}

@Override
public int compareTo(Data obj2) {
	if(this.getScore()>obj2.getScore()) 
		return 1;
	else
	return -1;
}

}
