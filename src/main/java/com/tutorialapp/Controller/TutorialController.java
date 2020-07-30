package com.tutorialapp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialapp.Model.Tutorial;
import com.tutorialapp.Repository.TutorialRepository;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	TutorialRepository tutorialRepository;
	
	@GetMapping("/tutorials")
	  public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {
			List<Tutorial> tutorialList=tutorialRepository.findAll();
			//tutorialRepository.findAll().forEach(tutorialList::add);
			System.out.println(tutorialList.toString());
			if(tutorialList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Tutorial>>(tutorialList,HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	  }

	  @GetMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
		  try {
			  Optional<Tutorial> tutorial=tutorialRepository.findById(id);
			  if(tutorial.isPresent()) {
				  return new ResponseEntity<Tutorial>(tutorial.get(),HttpStatus.OK);
			  }
			  else {
				  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			  }
		  }catch(Exception e) {
			  return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		  }
	    
	  }
	  @PostMapping("/tutorials")
	  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
	      
		  try {
			  Tutorial newtutorial=tutorialRepository.save(new Tutorial(tutorial.getTitle(),tutorial.getDescription(),false));
		  	  return new ResponseEntity<Tutorial>(newtutorial,HttpStatus.CREATED);
		  }catch(Exception e)
		  {
			  return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
		  }
	  }
	  @PutMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
	       Optional<Tutorial> tutorialdata=tutorialRepository.findById(id);
	       if(tutorialdata.isPresent()) {
	    	   Tutorial _tutorial=tutorialdata.get();
	    	   _tutorial.setDescription(tutorial.getDescription());
	    	   _tutorial.setTitle(tutorial.getTitle());
	    	   _tutorial.setPublished(tutorial.isPublished());
	    	   return new ResponseEntity<Tutorial>(tutorialRepository.save(_tutorial),HttpStatus.OK);
	       }
	       else {
	    	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
	  }

	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
		  try {
			  	  tutorialRepository.deleteById(id);
				  return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		  }catch(Exception e) {
			  System.out.println(e);
			  return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		  }
	    
	  }
	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  try {
			    tutorialRepository.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			  }
	  }

	  @GetMapping("/tutorials/published")
	  public ResponseEntity<List<Tutorial>> findByPublished() {
		  try {
			  List<Tutorial> tutorialList=tutorialRepository.findByPublished(true);
			  if(tutorialList.size()>0) {
				  return new ResponseEntity<List<Tutorial>>(tutorialList,HttpStatus.OK);
			  }
			  else {
				  return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			  }
		  }catch(Exception e) {
			  return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		  }
	    
	  }
	
}
