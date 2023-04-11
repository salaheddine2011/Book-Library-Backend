package com.benkhanous.springbootlibrary.controller;

import com.benkhanous.springbootlibrary.requestmodels.ReviewRequest;
import com.benkhanous.springbootlibrary.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value="Authorization")String token,@RequestBody ReviewRequest reviewRequest)throws Exception{
     String userEmail= ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
     if(userEmail==null){
         throw new Exception("user Email is missing");
     }
     reviewService.postReview(userEmail,reviewRequest);
    }

}
