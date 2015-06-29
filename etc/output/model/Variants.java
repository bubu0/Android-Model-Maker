package be.qaz.jacksonorm.pojo; 

import java.sql.Date;
import java.util.ArrayList; 

public class Variants {
 
	private ArrayList<Nutritions> nutritions; 
	private ArrayList<SimpleSteps> simpleSteps; 
	private ArrayList<Steps> steps; 
	private ExtraData extraData; 
	private String nutritious; 
	private String yieldValue; 
	//URI to variants
	private String resourceUri; 
	//URI to units
	private String yieldUnit; 
	private long totalTime; 
	private long preparationTime; 
	private long idDb; 
	private long cookingTime; 
	private long restTime; 

	public Variants() {
		super();
	} 

	##################### 
	 GETTERS & SETTERS 
	#####################
 
	public ArrayList<Nutritions> getNutritions() {
		return this.nutritions;
	}

	public void setNutritions(ArrayList<Nutritions> nutritions) {
		this.nutritions = nutritions;
	}
 
	public ArrayList<SimpleSteps> getSimpleSteps() {
		return this.simpleSteps;
	}

	public void setSimpleSteps(ArrayList<SimpleSteps> simpleSteps) {
		this.simpleSteps = simpleSteps;
	}
 
	public ArrayList<Steps> getSteps() {
		return this.steps;
	}

	public void setSteps(ArrayList<Steps> steps) {
		this.steps = steps;
	}
 
	public ExtraData getExtraData() {
		return this.extraData;
	}

	public void setExtraData(ExtraData extraData) {
		this.extraData = extraData;
	}
 
	public String getNutritious() {
		return this.nutritious;
	}

	public void setNutritious(String nutritious) {
		this.nutritious = nutritious;
	}
 
	public String getYieldValue() {
		return this.yieldValue;
	}

	public void setYieldValue(String yieldValue) {
		this.yieldValue = yieldValue;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public String getYieldUnit() {
		return this.yieldUnit;
	}

	public void setYieldUnit(String yieldUnit) {
		this.yieldUnit = yieldUnit;
	}
 
	public long getTotalTime() {
		return this.totalTime;
	}

	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
 
	public long getPreparationTime() {
		return this.preparationTime;
	}

	public void setPreparationTime(long preparationTime) {
		this.preparationTime = preparationTime;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	public long getCookingTime() {
		return this.cookingTime;
	}

	public void setCookingTime(long cookingTime) {
		this.cookingTime = cookingTime;
	}
 
	public long getRestTime() {
		return this.restTime;
	}

	public void setRestTime(long restTime) {
		this.restTime = restTime;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Variants : "
		+ " nutritions = " + nutritions
		+ " simpleSteps = " + simpleSteps
		+ " steps = " + steps
		+ " extraData = " + extraData
		+ " nutritious = " + nutritious
		+ " yieldValue = " + yieldValue
		+ " resourceUri = " + resourceUri
		+ " yieldUnit = " + yieldUnit
		+ " totalTime = " + totalTime
		+ " preparationTime = " + preparationTime
		+ " idDb = " + idDb
		+ " cookingTime = " + cookingTime
		+ " restTime = " + restTime;
		return s;
	} 
} 
