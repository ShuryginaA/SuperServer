package com.carservice.CarService.repositories;

import com.carservice.CarService.data.CallBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallbackRepository extends JpaRepository<CallBack, Long> {
}
