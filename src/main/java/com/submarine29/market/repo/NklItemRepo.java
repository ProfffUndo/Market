package com.submarine29.market.repo;

import com.submarine29.market.domain.NklItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NklItemRepo extends JpaRepository<NklItem,Long> {
}
