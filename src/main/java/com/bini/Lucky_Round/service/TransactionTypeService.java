package com.bini.Lucky_Round.service;


import com.bini.Lucky_Round.entity.TransactionType;
import com.bini.Lucky_Round.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> getAllTransactionTypes() {
        return transactionTypeRepository.findAll();
    }

    public Optional<TransactionType> getTransactionTypeById(Long id) {
        return transactionTypeRepository.findById(id);
    }

    public TransactionType createOrUpdateTransactionType(TransactionType transactionType) {
        return transactionTypeRepository.save(transactionType);
    }

    public void deleteTransactionType(Long id) {
        transactionTypeRepository.deleteById(id);
    }
}
