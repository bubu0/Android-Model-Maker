package be.qaz.jacksonorm.pojo.json; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection; 

import com.fasterxml.jackson.annotation.JsonProperty; 
public class MediasJson { 

	@JsonProperty("avatar")
	private boolean avatar; 

	@JsonProperty("created")
	private Date created; 

	@JsonProperty("created_by")
	private CreatedByJson createdBy; 

	@JsonProperty("extern_video_url")
	private String externVideoUrl; 

	@JsonProperty("id")
	private long idDb; 

	@JsonProperty("image")
	private String image; 

	@JsonProperty("lang")
	private String lang; 

	@JsonProperty("modified")
	private Date modified; 

	//URI to users
	@JsonProperty("modified_by")
	private String modifiedBy; 

	//URI to medias
	@JsonProperty("resource_uri")
	private String resourceUri; 

	@JsonProperty("steps")
	private StepsJson steps; 

	@JsonProperty("thumbs")
	private ThumbsJson thumbs; 

	@JsonProperty("title")
	private String title; 

	@JsonProperty("video")
	private String video; 

	@JsonProperty("video_url")
	private String videoUrl; 

	public MediasJson(boolean avatar, Date created, CreatedByJson createdBy, String externVideoUrl, long idDb, String image, String lang, Date modified, String modifiedBy, String resourceUri, StepsJson steps, ThumbsJson thumbs, String title, String video, String videoUrl) { 
		super();
		this.avatar = avatar;
		this.created = created;
		this.createdBy = createdBy;
		this.externVideoUrl = externVideoUrl;
		this.idDb = idDb;
		this.image = image;
		this.lang = lang;
		this.modified = modified;
		this.modifiedBy = modifiedBy;
		this.resourceUri = resourceUri;
		this.steps = steps;
		this.thumbs = thumbs;
		this.title = title;
		this.video = video;
		this.videoUrl = videoUrl;
	} 

	public MediasJson() {
		super();
	} 
	public boolean getAvatar() {
		return this.avatar;
	}

	public void setAvatar(boolean avatar) {
		this.avatar = avatar;
	}
 
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
	public CreatedByJson getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(CreatedByJson createdBy) {
		this.createdBy = createdBy;
	}
 
	public String getExternVideoUrl() {
		return this.externVideoUrl;
	}

	public void setExternVideoUrl(String externVideoUrl) {
		this.externVideoUrl = externVideoUrl;
	}
 
	public long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(long idDb) {
		this.idDb = idDb;
	}
 
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
 
	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
 
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
 
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
 
	public String getResourceUri() {
		return this.resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
 
	public StepsJson getSteps() {
		return this.steps;
	}

	public void setSteps(StepsJson steps) {
		this.steps = steps;
	}
 
	public ThumbsJson getThumbs() {
		return this.thumbs;
	}

	public void setThumbs(ThumbsJson thumbs) {
		this.thumbs = thumbs;
	}
 
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
 
	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
 
	@Override
	public String toString() {
		final String s = "Object : Medias : "
		+ " avatar = " + avatar
		+ " created = " + created
		+ " createdBy = " + createdBy
		+ " externVideoUrl = " + externVideoUrl
		+ " idDb = " + idDb
		+ " image = " + image
		+ " lang = " + lang
		+ " modified = " + modified
		+ " modifiedBy = " + modifiedBy
		+ " resourceUri = " + resourceUri
		+ " steps = " + steps
		+ " thumbs = " + thumbs
		+ " title = " + title
		+ " video = " + video
		+ " videoUrl = " + videoUrl;
		return s;
	} 
} 
