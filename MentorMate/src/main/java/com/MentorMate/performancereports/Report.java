package com.MentorMate.performancereports;

public class Report {
private int	topPerformersThreshold; 
private boolean useExprienceMultiplier;
private int	periodLimit;

public int getTopPerformersThreshold() {
	return topPerformersThreshold;
}
public void setTopPerformersThreshold(int topPerformersThreshold) {
	this.topPerformersThreshold = topPerformersThreshold;
}
public boolean isUseExprienceMultiplier() {
	return useExprienceMultiplier;
}
public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
	this.useExprienceMultiplier = useExprienceMultiplier;
}
public int getPeriodLimit() {
	return periodLimit;
}
public void setPeriodLimit(int periodLimit) {
	this.periodLimit = periodLimit;
}
@Override
public String toString() {
	return "Report [topPerformersThreshold=" + topPerformersThreshold + ", useExprienceMultiplier="
			+ useExprienceMultiplier + ", periodLimit=" + periodLimit + "]";
}


}
