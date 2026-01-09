package org.t2404e.kanji_together_db.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.t2404e.kanji_together_db.entity.Clazz;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Long> {

    @Query("SELECT c FROM Clazz c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:isActive IS NULL OR c.is_active = :isActive)")
    Page<Clazz> searchClazz(@Param("name") String name,
                            @Param("isActive") Boolean isActive,
                            Pageable pageable);
}