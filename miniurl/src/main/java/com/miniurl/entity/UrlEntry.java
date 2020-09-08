package com.miniurl.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("url_entry")
public class UrlEntry {
	@Id
	private String id;
	String longUrl;
}
