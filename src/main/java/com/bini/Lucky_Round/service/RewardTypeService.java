package com.bini.Lucky_Round.service;


import com.bini.Lucky_Round.entity.RewardType;
import com.bini.Lucky_Round.repository.RewardTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RewardTypeService {

    @Autowired
    private RewardTypeRepository rewardTypeRepository;

    public List<RewardType> getAllRewardTypes() {
        return rewardTypeRepository.findAll();
    }

    public Optional<RewardType> getRewardTypeById(Long id) {
        return rewardTypeRepository.findById(id);
    }

    public RewardType createOrUpdateRewardType(RewardType rewardType) {
        return rewardTypeRepository.save(rewardType);
    }

    public void deleteRewardType(Long id) {
        rewardTypeRepository.deleteById(id);
    }
}
