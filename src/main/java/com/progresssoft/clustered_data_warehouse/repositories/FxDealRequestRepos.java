package com.progresssoft.clustered_data_warehouse.repositories;

import com.progresssoft.clustered_data_warehouse.entity.FxDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRequestRepos extends JpaRepository<FxDeals, Long> {
}
