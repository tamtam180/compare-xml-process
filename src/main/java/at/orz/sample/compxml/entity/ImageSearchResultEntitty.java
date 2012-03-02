/**
 * 
 */
package at.orz.sample.compxml.entity;

/**
 * @author tamtam
 *
 */
public class ImageSearchResultEntitty {
	
	protected String refererUrl;
	protected String clickUrl;
	protected String url;
	protected String title;
	protected String summary;
	protected int width;
	protected int height;
	protected String fileSize;
	protected String fileFormat;
	protected ImageSearchThumbnailEntity thumbnail;
	public String getRefererUrl() {
		return refererUrl;
	}
	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public ImageSearchThumbnailEntity getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(ImageSearchThumbnailEntity thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}
