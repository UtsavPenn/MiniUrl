package com.miniurl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miniurl.model.CreateShortURLRequest;
import com.miniurl.model.CreateShortUrlResponse;
import com.miniurl.service.UrlShortnerService;

@RestController
public class UrlShortnerController {
	@Autowired
	private UrlShortnerService urlShortnerService;
	
	@PostMapping("/generate-url")
	public ResponseEntity<CreateShortUrlResponse> generateUrl(@RequestBody CreateShortURLRequest request ) throws NoSuchAlgorithmException {
		CreateShortUrlResponse response = new CreateShortUrlResponse();
		String shortUrl = urlShortnerService.createShortUrl(request);
		response.setShortUrl(shortUrl);
		return new ResponseEntity<CreateShortUrlResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "{shortUrl}", method = RequestMethod.GET)
	public void method(@PathVariable String shortUrl,HttpServletResponse httpServletResponse) throws IOException {
		String longUrl = urlShortnerService.getLongUrl(shortUrl);
		if(longUrl.isEmpty())
			httpServletResponse.sendError(200, "Unable to find url to redirect to");
	    httpServletResponse.setHeader("Location", longUrl);
	    httpServletResponse.setStatus(302);
	}
}
