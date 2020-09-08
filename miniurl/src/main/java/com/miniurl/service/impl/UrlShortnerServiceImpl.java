package com.miniurl.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miniurl.entity.UrlEntry;
import com.miniurl.model.CreateShortURLRequest;
import com.miniurl.repository.UrlEntryRepository;
import com.miniurl.service.UrlShortnerService;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {
	@Autowired
	UrlEntryRepository urlEntryRepository;
	String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rand = new Random();
	String key = getRandomString();

	public String createShortUrl(CreateShortURLRequest request) throws NoSuchAlgorithmException {
		String longUrl = request.getLongUrl();
		UrlEntry urlEntry = new UrlEntry();
		urlEntry.setLongUrl(longUrl);
		urlEntry.setId(encode(longUrl));
		urlEntryRepository.save(urlEntry);
		String baseUrl = getFullBaseUrl();
		return  baseUrl+ urlEntry.getId();
	}

	public String getLongUrl(String shortUrl) {
		Optional<UrlEntry> urlEntry = urlEntryRepository.findById(shortUrl);
		if(!urlEntry.isPresent())
			return "";
		UrlEntry entry = urlEntry.get(); 
		return entry.getLongUrl();
	}

	public String getFullBaseUrl() {
		return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";
	}

	public String getRandomString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(chars.charAt(rand.nextInt(62)));
		}
		return sb.toString();
	}

	public String encode(String longUrl) {
		while (urlEntryRepository.existsById(key)) {
			key = getRandomString();
		}
		return key;
	}
}
