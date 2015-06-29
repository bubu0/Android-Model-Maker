package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class ThumbsJson { 

	@JsonProperty("1024x704")
	private String n1024x704; 

	@JsonProperty("103x89")
	private String n103x89; 

	@JsonProperty("140x140")
	private String n140x140; 

	@JsonProperty("153x133")
	private String n153x133; 

	@JsonProperty("164x156")
	private String n164x156; 

	@JsonProperty("184x162")
	private String n184x162; 

	@JsonProperty("192x176")
	private String n192x176; 

	@JsonProperty("202x180")
	private String n202x180; 

	@JsonProperty("240x240")
	private String n240x240; 

	@JsonProperty("256x256")
	private String n256x256; 

	@JsonProperty("262x180")
	private String n262x180; 

	@JsonProperty("262x202")
	private String n262x202; 

	@JsonProperty("324x324")
	private String n324x324; 

	@JsonProperty("352x264")
	private String n352x264; 

	@JsonProperty("426x342")
	private String n426x342; 

	@JsonProperty("548x408")
	private String n548x408; 

	@JsonProperty("640x1070")
	private String n640x1070; 

	@JsonProperty("640x790")
	private String n640x790; 

	@JsonProperty("650x492")
	private String n650x492; 

	@JsonProperty("672x512")
	private String n672x512; 

	@JsonProperty("68x68")
	private String n68x68; 

	@JsonProperty("72x44")
	private String n72x44; 

	@JsonProperty("80x80")
	private String n80x80; 

	@JsonProperty("820x820")
	private String n820x820; 

	@JsonProperty("icon")
	private String icon; 

	@JsonProperty("large")
	private String large; 

	@JsonProperty("medias")
	private MediasJson medias; 

	@JsonProperty("medium")
	private String medium; 

	@JsonProperty("mini")
	private String mini; 

	@JsonProperty("original")
	private String original; 

	@JsonProperty("small")
	private String small; 

	@JsonProperty("thumb")
	private String thumb; 

	public ThumbsJson(String n1024x704, String n103x89, String n140x140, String n153x133, String n164x156, String n184x162, String n192x176, String n202x180, String n240x240, String n256x256, String n262x180, String n262x202, String n324x324, String n352x264, String n426x342, String n548x408, String n640x1070, String n640x790, String n650x492, String n672x512, String n68x68, String n72x44, String n80x80, String n820x820, String icon, String large, MediasJson medias, String medium, String mini, String original, String small, String thumb) { 
		super();
		this.n1024x704 = n1024x704;
		this.n103x89 = n103x89;
		this.n140x140 = n140x140;
		this.n153x133 = n153x133;
		this.n164x156 = n164x156;
		this.n184x162 = n184x162;
		this.n192x176 = n192x176;
		this.n202x180 = n202x180;
		this.n240x240 = n240x240;
		this.n256x256 = n256x256;
		this.n262x180 = n262x180;
		this.n262x202 = n262x202;
		this.n324x324 = n324x324;
		this.n352x264 = n352x264;
		this.n426x342 = n426x342;
		this.n548x408 = n548x408;
		this.n640x1070 = n640x1070;
		this.n640x790 = n640x790;
		this.n650x492 = n650x492;
		this.n672x512 = n672x512;
		this.n68x68 = n68x68;
		this.n72x44 = n72x44;
		this.n80x80 = n80x80;
		this.n820x820 = n820x820;
		this.icon = icon;
		this.large = large;
		this.medias = medias;
		this.medium = medium;
		this.mini = mini;
		this.original = original;
		this.small = small;
		this.thumb = thumb;
	} 

	public ThumbsJson() {
		super();
	} 
	public String getN1024x704() {
		return this.n1024x704;
	}

	public void setN1024x704(String n1024x704) {
		this.n1024x704 = n1024x704;
	}
 
	public String getN103x89() {
		return this.n103x89;
	}

	public void setN103x89(String n103x89) {
		this.n103x89 = n103x89;
	}
 
	public String getN140x140() {
		return this.n140x140;
	}

	public void setN140x140(String n140x140) {
		this.n140x140 = n140x140;
	}
 
	public String getN153x133() {
		return this.n153x133;
	}

	public void setN153x133(String n153x133) {
		this.n153x133 = n153x133;
	}
 
	public String getN164x156() {
		return this.n164x156;
	}

	public void setN164x156(String n164x156) {
		this.n164x156 = n164x156;
	}
 
	public String getN184x162() {
		return this.n184x162;
	}

	public void setN184x162(String n184x162) {
		this.n184x162 = n184x162;
	}
 
	public String getN192x176() {
		return this.n192x176;
	}

	public void setN192x176(String n192x176) {
		this.n192x176 = n192x176;
	}
 
	public String getN202x180() {
		return this.n202x180;
	}

	public void setN202x180(String n202x180) {
		this.n202x180 = n202x180;
	}
 
	public String getN240x240() {
		return this.n240x240;
	}

	public void setN240x240(String n240x240) {
		this.n240x240 = n240x240;
	}
 
	public String getN256x256() {
		return this.n256x256;
	}

	public void setN256x256(String n256x256) {
		this.n256x256 = n256x256;
	}
 
	public String getN262x180() {
		return this.n262x180;
	}

	public void setN262x180(String n262x180) {
		this.n262x180 = n262x180;
	}
 
	public String getN262x202() {
		return this.n262x202;
	}

	public void setN262x202(String n262x202) {
		this.n262x202 = n262x202;
	}
 
	public String getN324x324() {
		return this.n324x324;
	}

	public void setN324x324(String n324x324) {
		this.n324x324 = n324x324;
	}
 
	public String getN352x264() {
		return this.n352x264;
	}

	public void setN352x264(String n352x264) {
		this.n352x264 = n352x264;
	}
 
	public String getN426x342() {
		return this.n426x342;
	}

	public void setN426x342(String n426x342) {
		this.n426x342 = n426x342;
	}
 
	public String getN548x408() {
		return this.n548x408;
	}

	public void setN548x408(String n548x408) {
		this.n548x408 = n548x408;
	}
 
	public String getN640x1070() {
		return this.n640x1070;
	}

	public void setN640x1070(String n640x1070) {
		this.n640x1070 = n640x1070;
	}
 
	public String getN640x790() {
		return this.n640x790;
	}

	public void setN640x790(String n640x790) {
		this.n640x790 = n640x790;
	}
 
	public String getN650x492() {
		return this.n650x492;
	}

	public void setN650x492(String n650x492) {
		this.n650x492 = n650x492;
	}
 
	public String getN672x512() {
		return this.n672x512;
	}

	public void setN672x512(String n672x512) {
		this.n672x512 = n672x512;
	}
 
	public String getN68x68() {
		return this.n68x68;
	}

	public void setN68x68(String n68x68) {
		this.n68x68 = n68x68;
	}
 
	public String getN72x44() {
		return this.n72x44;
	}

	public void setN72x44(String n72x44) {
		this.n72x44 = n72x44;
	}
 
	public String getN80x80() {
		return this.n80x80;
	}

	public void setN80x80(String n80x80) {
		this.n80x80 = n80x80;
	}
 
	public String getN820x820() {
		return this.n820x820;
	}

	public void setN820x820(String n820x820) {
		this.n820x820 = n820x820;
	}
 
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
 
	public String getLarge() {
		return this.large;
	}

	public void setLarge(String large) {
		this.large = large;
	}
 
	public MediasJson getMedias() {
		return this.medias;
	}

	public void setMedias(MediasJson medias) {
		this.medias = medias;
	}
 
	public String getMedium() {
		return this.medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}
 
	public String getMini() {
		return this.mini;
	}

	public void setMini(String mini) {
		this.mini = mini;
	}
 
	public String getOriginal() {
		return this.original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}
 
	public String getSmall() {
		return this.small;
	}

	public void setSmall(String small) {
		this.small = small;
	}
 
	public String getThumb() {
		return this.thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Thumbs : "
		+ " n1024x704 = " + n1024x704
		+ " n103x89 = " + n103x89
		+ " n140x140 = " + n140x140
		+ " n153x133 = " + n153x133
		+ " n164x156 = " + n164x156
		+ " n184x162 = " + n184x162
		+ " n192x176 = " + n192x176
		+ " n202x180 = " + n202x180
		+ " n240x240 = " + n240x240
		+ " n256x256 = " + n256x256
		+ " n262x180 = " + n262x180
		+ " n262x202 = " + n262x202
		+ " n324x324 = " + n324x324
		+ " n352x264 = " + n352x264
		+ " n426x342 = " + n426x342
		+ " n548x408 = " + n548x408
		+ " n640x1070 = " + n640x1070
		+ " n640x790 = " + n640x790
		+ " n650x492 = " + n650x492
		+ " n672x512 = " + n672x512
		+ " n68x68 = " + n68x68
		+ " n72x44 = " + n72x44
		+ " n80x80 = " + n80x80
		+ " n820x820 = " + n820x820
		+ " icon = " + icon
		+ " large = " + large
		+ " medias = " + medias
		+ " medium = " + medium
		+ " mini = " + mini
		+ " original = " + original
		+ " small = " + small
		+ " thumb = " + thumb;
		return s;
	} 
} 
