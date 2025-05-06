package com.bini.Lucky_Round.repository;

import com.bini.Lucky_Round.entity.SpinTheWheelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpinTheWheelRequestRepo extends JpaRepository<SpinTheWheelRequest, Long> {
}
