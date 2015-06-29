package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class TermsJson { 

	@JsonProperty("recipes")
	private RecipesJson recipes; 

	//URI to recipes
	@JsonProperty("terms")
	private String terms; 

	public TermsJson(RecipesJson recipes, String terms) { 
		super();
		this.recipes = recipes;
		this.terms = terms;
	} 

	public TermsJson() {
		super();
	} 
	public RecipesJson getRecipes() {
		return this.recipes;
	}

	public void setRecipes(RecipesJson recipes) {
		this.recipes = recipes;
	}
 
	public String getTerms() {
		return this.terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Terms : "
		+ " recipes = " + recipes
		+ " terms = " + terms;
		return s;
	} 
} 
