package com.fs.subscriptions.subscriptions.repository;

import com.fs.subscriptions.subscriptions.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profiles, Long> {
}
