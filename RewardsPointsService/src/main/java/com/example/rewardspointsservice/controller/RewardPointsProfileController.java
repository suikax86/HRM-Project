package com.example.rewardspointsservice.controller;

import com.example.rewardspointsservice.model.dtos.RewardPointsProfileDto;
import com.example.rewardspointsservice.service.RewardPointsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-points-profile")
public class RewardPointsProfileController {
    private final RewardPointsService rewardPointsService;

    public RewardPointsProfileController(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    @GetMapping("/{employeeId}")
    public RewardPointsProfileDto getRewardPoints(@PathVariable Long employeeId) {
        return rewardPointsService.getProfileDto(employeeId);
    }

    @GetMapping("/all")
    public List<RewardPointsProfileDto> getAllRewardPoints() {
       return rewardPointsService.getAllProfilesDto();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRewardPointsProfile(@RequestBody Long employeeId) {
        String response = rewardPointsService.createRewardPointsProfile(employeeId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
