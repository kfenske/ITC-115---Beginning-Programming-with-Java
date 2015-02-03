package com.kfenske.Assignment3;

public class Calculate {
	
	private double bats;
	private double hits;
	private double walks;
	private double outs;

	public double calculateBatAvg() 
	{
		double average = getHits() / getBats();
		
		return average;
	}
	
	public double calculateOnBaseAvg()
	{
		double onBase = (getHits() + getWalks()) / getBats();
		
		return onBase;
	}

	public double getBats() {
		return bats;
	}

	public void setBats(double bats) {
		this.bats = bats;
	}

	public double getHits() {
		return hits;
	}

	public void setHits(double hits) {
		this.hits = hits;
	}

	public double getWalks() {
		return walks;
	}

	public void setWalks(double walks) {
		this.walks = walks;
	}

	public double getOuts() {
		return outs;
	}

	public void setOuts(double outs) {
		this.outs = outs;
	}
}
