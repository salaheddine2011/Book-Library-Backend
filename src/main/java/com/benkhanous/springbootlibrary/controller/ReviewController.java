package com.benkhanous.springbootlibrary.controller;

import com.benkhanous.springbootlibrary.requestmodels.ReviewRequest;
import com.benkhanous.springbootlibrary.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import utils.ExtractJWT;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token,@RequestParam Long bookId) throws Exception {
       String userEmail=ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
       if(userEmail == null){
           throw new Exception("User email is missing");
       }
       return reviewService.userReviewListed(userEmail,bookId);
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
