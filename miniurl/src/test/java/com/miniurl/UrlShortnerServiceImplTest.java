package com.miniurl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miniurl.model.CreateShortURLRequest;
import com.miniurl.repository.UrlEntryRepository;
import com.miniurl.service.UrlShortnerService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UrlShortnerServiceImplTest {
	@Autowired
	private UrlShortnerService urlShortnerService;
	
	@Autowired
	private UrlEntryRepository urlEntryRepository;
	
	@Test
	public void whenShortUrlGenerated_thenLongUrlShouldbeFound() throws NoSuchAlgorithmException {
		String longUrl = "https:://google.com";
		CreateShortURLRequest req = new CreateShortURLRequest();
		req.setLongUrl(longUrl);
		String baseUrl = urlShortnerService.getFullBaseUrl();
		String shortUrl = urlShortnerService.createShortUrl(req).replace(baseUrl,"");
		String longUrlReceived = urlShortnerService.getLongUrl(shortUrl);
		assertEquals(longUrl, longUrlReceived);
	}
	
	@Test
	public void whenShortUrlNotGenerated_thenEmptyRedirectUrlShouldbeFound() throws NoSuchAlgorithmException {
		String longUrlReceived = urlShortnerService.getLongUrl("random");
		assertEquals("", longUrlReceived);
	}
}
