package com.project.android.app.kys.business;

public class University {
	private Integer mUniversityId;
	private String mUniversityName;
	private String mUniversityInitials;
	private String mUniversityCity;
	private String mUniversityState;
	private String mUniversityCountry;
	private String mUniversitySummary;

	public Integer getUniversityId() {
		return mUniversityId;
	}

	public void setUniversityId(Integer universityId) {
		this.mUniversityId = universityId;
	}

	public String getUniversityName() {
		return mUniversityName;
	}

	public void setUniversityCity(String universityCity) {
		this.mUniversityCity = universityCity;
	}

	public String getUniversityCity() {
		return mUniversityCity;
	}

	public void setUniversityState(String universitySate) {
		this.mUniversityState = universitySate;
	}

	public String getUniversityState() {
		return mUniversityState;
	}

	public void setUniversityCountry(String universityCountry) {
		this.mUniversityCountry = universityCountry;
	}

	public String getUniversityCountry() {
		return mUniversityCountry;
	}

	public void setUniversityName(String universityName) {
		this.mUniversityName = universityName;
	}

	public String getUniversityInitials() {
		return mUniversityInitials;
	}

	public void setUniversityInitials(String universityInitials) {
		this.mUniversityInitials = universityInitials;
	}

	public String getUniversitySummary() {
		return mUniversitySummary;
	}

	public void setUniversitySummary(String universitySummary) {
		this.mUniversitySummary = universitySummary;
	}

}
