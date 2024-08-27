package com.job_app_microservices.reviewService.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job_app_microservices.reviewService.review.Review;
import com.job_app_microservices.reviewService.review.ReviewRepository;
import com.job_app_microservices.reviewService.review.ReviewService;


@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewRepository reviewRepo;

	public ReviewServiceImpl(ReviewRepository reviewRepo) {
		super();
		this.reviewRepo = reviewRepo;
	}

	@Override
	public List<Review> findAll(Long companyId) {
		return reviewRepo.findByCompanyId(companyId);
		
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		// TODO Auto-generated method stub
		
		if (companyId != null) {
			review.setCompanyId(companyId);
			reviewRepo.save(review);
			return true;
		}
		return false;	
		
	}

	@Override
	public Review getReviewById(Long reviewId) {
		Review temp = reviewRepo.findById(reviewId).orElse(null);
		return temp;
	}

	@Override
	public boolean updateById(Long reviewId,Review review) {
		Review temp = reviewRepo.findById(reviewId).orElse(null);
		if (temp!=null) {
			temp.setDescription(review.getDescription());
			temp.setRating(review.getRating());
			temp.setTitle(review.getTitle());
			temp.setCompanyId(review.getCompanyId());
			reviewRepo.save(temp);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteById(Long reviewId) {
		try{
			reviewRepo.deleteById(reviewId); 
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

}
