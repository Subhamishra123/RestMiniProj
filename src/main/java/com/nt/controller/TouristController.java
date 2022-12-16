package com.nt.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Tourist;
import com.nt.service.ITouristMgmtService;

@RestController
public class TouristController {
	
	@Autowired
	private ITouristMgmtService service;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist)
	{
		
			String message = service.registerTourist(tourist);
			return new ResponseEntity<String>(message,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> displayTourists()
	{
		
			List<Tourist> tourist = service.fetchAllTourist();
			return new ResponseEntity<List<Tourist>>(tourist,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> fetchTouristById(@PathVariable("id") Integer id) throws Exception
	{
		
			Tourist touristById = service.fetchTouristById(id);
			return new ResponseEntity<Tourist>(touristById,HttpStatus.ACCEPTED);
		
		
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modifyTourist(@RequestBody Tourist tourist) throws Exception
	{
		
			String message = service.updateTouristDetails(tourist);
			return new ResponseEntity<String>(message,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTourist(@PathVariable("id") Integer id)throws Exception
	{
		
			String msg = service.deleteTourist(id);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
	
	@PatchMapping("/modifyBudget/{id}/{hike}")
	public ResponseEntity<String> modifyTouristByBudget(@PathVariable("id") Integer id,
														@PathVariable("hike") Double hike)throws Exception
	{
		
			String msg = service.updateTouristBudgetById(id, hike);
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}
}
