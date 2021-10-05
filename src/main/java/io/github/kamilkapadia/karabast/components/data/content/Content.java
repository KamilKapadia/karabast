package io.github.kamilkapadia.karabast.components.data.content;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.components.data.contentresult.ContentResult;

@Entity
@Table(name = "content")
public class Content {
	/*
	id LONG PRIMARY KEY auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL DEFAULT 'file.bin',
	mime_type VARCHAR(256) NOT NULL DEFAULT 'application/octet-stream',
	content_length LONG NOT NULL,
	crc32 LONG NOT NULL, 
	adler32 LONG NOT NULL, 
	md5 VARCHAR(32) NOT NULL, 
	SHA512 VARCHAR(128) NOT NULL);
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "mime_type")
	private String mimeType;
	
	@Column(name = "content_length")
	private long contentLength;
	
	@Column(name = "crc32")
	private long crc32;
	
	@Column(name = "adler32")
	private long adler32;
	
	@Column(name = "md5")
	private String md5;
	
	@Column(name = "sha512")
	private String sha512;

	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="content", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<ContentResult> contentResult = new ArrayList<ContentResult>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public long getCrc32() {
		return crc32;
	}

	public void setCrc32(long crc32) {
		this.crc32 = crc32;
	}

	public long getAdler32() {
		return adler32;
	}

	public void setAdler32(long adler32) {
		this.adler32 = adler32;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSha512() {
		return sha512;
	}

	public void setSha512(String sha512) {
		this.sha512 = sha512;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", name=" + name + ", mimeType=" + mimeType + ", contentLength=" + contentLength
				+ ", crc32=" + crc32 + ", adler32=" + adler32 + ", md5=" + md5 + ", sha512=" + sha512 + "]";
	}
}
