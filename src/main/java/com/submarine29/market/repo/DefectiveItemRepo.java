package com.submarine29.market.repo;

import com.submarine29.market.domain.DefectiveItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectiveItemRepo extends JpaRepository<DefectiveItem,Long> {
}
