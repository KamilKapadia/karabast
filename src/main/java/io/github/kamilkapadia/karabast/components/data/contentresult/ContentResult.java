package io.github.kamilkapadia.karabast.components.data.contentresult;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.github.kamilkapadia.karabast.components.data.content.Content;
import io.github.kamilkapadia.karabast.components.data.result.Result;


@Entity
@Table(name = "content_result")
public class ContentResult {
	/*
	 id LONG PRIMARY KEY auto_increment NOT NULL,
	 result_id LONG NOT NULL, 
	 content_id LONG NOT NULL);
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="result_id")
    private Result result;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="content_id")
	private Content content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ContentResult [id=" + id + ", result=" + result + ", content=" + content + "]";
	}
}

