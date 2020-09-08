package com.miniurl.service;

import java.security.NoSuchAlgorithmException;

import com.miniurl.model.CreateShortURLRequest;

public interface UrlShortnerService {
	public String createShortUrl(CreateShortURLRequest request) throws NoSuchAlgorithmException;
	public String getLongUrl(String shortUrl);
	public String getFullBaseUrl();
}
