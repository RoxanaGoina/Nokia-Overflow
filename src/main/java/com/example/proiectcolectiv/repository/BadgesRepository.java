package com.example.proiectcolectiv.repository;

import com.example.proiectcolectiv.model.Badges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgesRepository  extends JpaRepository<Badges, String> {
    @Query(value = "select distinct *  from badges where userID = :uid",nativeQuery = true)
    public List<Badges> getUserBadges(@Param("uid") String id);
    @Modifying
    @Query(value="insert into badges (bID,idBadges,userID,badgeType) values (:bID,:idBadges,:userID,:badgeType)",nativeQuery = true)
    public void addBadge(@Param("bID")Integer bID,@Param("idBadges") String idBadges,@Param("userID") String userID,@Param("badgeType") String badgeType);
    @Query(value="select max(bID) from badges",nativeQuery = true)
    public Integer getMaxBadge();
}
