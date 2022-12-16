package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFoundException;
import com.nt.repo.ITouristRepo;
@Service
public class TouristServiceImpl implements ITouristMgmtService {

	@Autowired
	private ITouristRepo repo;
	
	@Override
	public String registerTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		
		return "Tourist is registered having id "+repo.save(tourist).getTouristId();
	}
	
	@Override
	public List<Tourist> fetchAllTourist() {
		List<Tourist> tourists = (List<Tourist>)repo.findAll();
		tourists.sort((t1,t2)->t1.getName().compareTo(t2.getName()));
		return tourists;
	}
	@Override
	public Tourist fetchTouristById(Integer id) throws TouristNotFoundException {
		// TODO Auto-generated method stub
		return	repo.findById(id).orElseThrow(()->new TouristNotFoundException(id+" Tourist not found"));
		
	}
	
	@Override
	public String updateTouristDetails(Tourist tourist) throws TouristNotFoundException {
		// TODO Auto-generated method stub
		
		
			Optional<Tourist> optional = repo.findById(tourist.getTouristId());
			if(optional.isPresent()) {
				return repo.save(tourist).getTouristId()+" updated succesfully";
			}
			else {
				throw new TouristNotFoundException(tourist.getTouristId()+" Tourist is not present");
			}
			
		
	}
	@Override
	public String deleteTourist(Integer id) throws TouristNotFoundException {
		// TODO Auto-generated method stub
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent()) {
			repo.delete(optional.get());
			return id+" tourist deleted";
		}
		throw new TouristNotFoundException(id+" tourist id not found");
	}
	
	@Override
	public String updateTouristBudgetById(Integer id, Double hike) throws TouristNotFoundException {
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent()) {
			Tourist tourist = optional.get();
			tourist.setBudget(tourist.getBudget()+tourist.getBudget()*hike/100);
			repo.save(tourist);
			return "tourist "+id+" updated succesfully";
		}
		throw new TouristNotFoundException(id+" tourist id not found");
	}

}
