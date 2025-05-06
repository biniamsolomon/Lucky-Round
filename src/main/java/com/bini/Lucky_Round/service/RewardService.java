package com.bini.Lucky_Round.service;


import com.bini.Lucky_Round.dto.RewardDTO;
import com.bini.Lucky_Round.dto.RewardRequestDTO;
import com.bini.Lucky_Round.dto.RewardTypeDTO;
import com.bini.Lucky_Round.dto.TransactionTypeDTO;
import com.bini.Lucky_Round.entity.Reward;
import com.bini.Lucky_Round.entity.RewardType;
import com.bini.Lucky_Round.entity.TransactionType;
import com.bini.Lucky_Round.repository.RewardRepository;
import com.bini.Lucky_Round.repository.RewardTypeRepository;
import com.bini.Lucky_Round.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RewardTypeRepository rewardTypeRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public RewardDTO createOrUpdateReward(RewardRequestDTO rewardRequestDTO) {
        Reward reward = new Reward();
        reward.setRewardName(rewardRequestDTO.getRewardName());
        reward.setRewardValue(rewardRequestDTO.getRewardValue());
        reward.setRewardDescription(rewardRequestDTO.getRewardDescription());

        // Find and set RewardType
        RewardType rewardType = rewardTypeRepository.findById(rewardRequestDTO.getRewardTypeId())
                .orElseThrow(() -> new RuntimeException("RewardType not found"));
        reward.setRewardType(rewardType);

        // Find and set TransactionType (if provided)
        if (rewardRequestDTO.getTransactionTypeId() != null) {
            TransactionType transactionType = transactionTypeRepository.findById(rewardRequestDTO.getTransactionTypeId())
                    .orElseThrow(() -> new RuntimeException("TransactionType not found"));
            reward.setTransactionType(transactionType);
        } else {
            reward.setTransactionType(null);
        }

        Reward savedReward = rewardRepository.save(reward);
        return convertToDTO(savedReward);
    }

    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }

    // Convert Reward Entity to RewardDTO
    private RewardDTO convertToDTO(Reward reward) {
        RewardTypeDTO rewardTypeDTO = new RewardTypeDTO(reward.getRewardType().getId(), reward.getRewardType().getTypeName());

        TransactionTypeDTO transactionTypeDTO = null;
        if (reward.getTransactionType() != null) {
            transactionTypeDTO = new TransactionTypeDTO(reward.getTransactionType().getId(), reward.getTransactionType().getTypeName());
        }

        return new RewardDTO(
                reward.getId(),
                reward.getRewardName(),
                reward.getRewardValue(),
                reward.getRewardDescription(),
                rewardTypeDTO,
                transactionTypeDTO
        );
    }
}
