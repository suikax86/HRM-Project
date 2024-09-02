package com.example.rewardspointsservice.service;

import com.example.rewardspointsservice.model.RewardPointsProfile;
import com.example.rewardspointsservice.model.dtos.RewardPointsProfileDto;
import com.example.rewardspointsservice.repository.RewardPointsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardPointsService {
    private final RewardPointsRepository rewardPointsRepository;


    public RewardPointsService(RewardPointsRepository rewardPointsRepository) {
        this.rewardPointsRepository = rewardPointsRepository;
    }

    public RewardPointsProfileDto getProfileDto(Long employeeId) {
        RewardPointsProfile profile = rewardPointsRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        RewardPointsProfileDto profileDto = new RewardPointsProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setEmployeeId(profile.getEmployeeId());
        profileDto.setTotalPoints(profile.getTotalPoints());
        profileDto.setPointsHistory(profile.getPointsHistory());
        return profileDto;
    }

    public List<RewardPointsProfileDto> getAllProfilesDto() {
        List<RewardPointsProfile> profiles = rewardPointsRepository.findAll();
        List<RewardPointsProfileDto> profileDtos = new ArrayList<>();
        for (RewardPointsProfile profile : profiles) {
            RewardPointsProfileDto profileDto = new RewardPointsProfileDto();
            profileDto.setId(profile.getId());
            profileDto.setEmployeeId(profile.getEmployeeId());
            profileDto.setTotalPoints(profile.getTotalPoints());
            profileDto.setPointsHistory(profile.getPointsHistory());
            profileDtos.add(profileDto);
        }
        return profileDtos;
    }

    public String createRewardPointsProfile(Long employeeId) {
            RewardPointsProfile profile = new RewardPointsProfile();
            profile.setEmployeeId(employeeId);
            rewardPointsRepository.save(profile);
            return "Profile created successfully for employee ID: " + employeeId;
    }

    public void deleteRewardPointsProfile(Long employeeId) {
        rewardPointsRepository.deleteByEmployeeId(employeeId);
    }


}