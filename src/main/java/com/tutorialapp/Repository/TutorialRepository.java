package com.tutorialapp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.tutorialapp.Model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String>{
	Optional<Tutorial> findById(String id);
	List<Tutorial> findByTitle(String title);
	List<Tutorial> findAll();
	List<Tutorial> findByPublished(boolean published);
}
