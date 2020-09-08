package com.miniurl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miniurl.entity.UrlEntry;

public interface UrlEntryRepository extends MongoRepository<UrlEntry, String>{

}
