package com.nt.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nt.entity.Tourist;

public interface ITouristRepo extends PagingAndSortingRepository<Tourist, Integer> {

}
