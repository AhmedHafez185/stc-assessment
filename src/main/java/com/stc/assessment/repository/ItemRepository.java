package com.stc.assessment.repository;

import com.stc.assessment.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT COUNT(p) > 0\n" +
            "FROM Permission p , Item i, PermissionGroup g\n" +
            "WHERE g.id = i.group.id\n" +
            "  AND p.permissionLevel = :level\n" +
            "  AND i.id = :spaceId\n" +
            "  AND g.id = p.group.id ")
    public boolean isHasEditAccess(Long spaceId, String level);
}
