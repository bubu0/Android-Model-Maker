package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class VariantsJson { 
	@JsonProperty("nutritions")
	private ArrayList<NutritionsJson> nutritions; 
	@JsonProperty("simple_steps")
	private ArrayList<SimpleStepsJson> simpleSteps; 
	@JsonProperty("steps")
	private ArrayList<StepsJson> steps; 
	@JsonProperty("extra_data")
	private ExtraDataJson extraData; 
	@JsonProperty("nutritious")
	private String nutritious; 
	@JsonProperty("yield_value")
	private String yieldValue; 
	//URI to variants
	@JsonProperty("resource_uri")
	private String resourceUri; 
	//URI to units
	@JsonProperty("yield_unit")
	private String yieldUnit; 
	@JsonProperty("total_time")
	private long totalTime; 
	@JsonProperty("preparation_time")
	private long preparationTime; 
	@JsonProperty("id")
	private long idDb; 
	@JsonProperty("cooking_time")
	private long cookingTime; 
	@JsonProperty("rest_time")
	private long restTime; 

	public VariantsJson(ArrayList<NutritionsJson> nutritions, ArrayList<SimpleStepsJson> simpleSteps, ArrayList<StepsJson> steps, ExtraDataJson extraData, String nutritious, String yieldValue, String resourceUri, String yieldUnit, long totalTime, long preparationTime, long idDb, long cookingTime, long restTime) { 
		super();
		this.nutritions = nutritions;
		this.simpleSteps = simpleSteps;
		this.steps = steps;
		this.extraData = extraData;
		this.nutritious = nutritious;
		this.yieldValue = yieldValue;
		this.resourceUri = resourceUri;
		this.yieldUnit = yieldUnit;
		this.totalTime = totalTime;
		this.preparationTime = preparationTime;
		this.idDb = idDb;
		this.cookingTime = cookingTime;
		this.restTime = restTime;
	} 

	public VariantsJson() {
		super();
	} 
	public ArrayList<NutritionsJson> getNutritions() {
		return this.nutritions;
	}

	public void setNutritions(ArrayList<NutritionsJson> nutritions) {
		this.nutritions = nutritions;
	}
 
	public ArrayList<SimpleStepsJson> getSimpleSteps() {
		return this.simpleSteps;
	}

	public void setSimpleSteps(ArrayList<SimpleStepsJson> simpleSteps) {
		this.simpleSteps = simpleSteps;
	}
 
	public ArrayList<StepsJson> getSteps() {
		return this.steps;
	}

	public void setSteps(ArrayList<StepsJson> steps) {
		this.steps = steps;
	}
 
	public ExtraDataJson getExtraData() {
		return this.extraData;
	}

	public void setExtraData(ExtraDataJson extraData) {
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
