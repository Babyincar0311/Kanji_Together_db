package org.t2404e.kanji_together_db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.t2404e.kanji_together_db.entity.Clazz;
import org.t2404e.kanji_together_db.repository.ClazzRepository;

@Service
public class ClazzService {

    @Autowired
    private ClazzRepository clazzRepository;

    public Clazz createClazz(Clazz clazz) {
        // Set default active = true nếu null
        if (clazz.getIs_active() == null) {
            clazz.setIs_active(true);
        }
        return clazzRepository.save(clazz);
    }

    public Clazz getClazzById(Long id) {
        return clazzRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clazz not found with id: " + id));
    }

    public Page<Clazz> getAllClazz(String search, Boolean isActive, Pageable pageable) {
        return clazzRepository.searchClazz(search, isActive, pageable);
    }

    public Clazz updateClazz(Long id, Clazz clazzDetails) {
        Clazz existingClazz = getClazzById(id);

        if (clazzDetails.getName() != null) existingClazz.setName(clazzDetails.getName());
        if (clazzDetails.getDescription() != null) existingClazz.setDescription(clazzDetails.getDescription());
        if (clazzDetails.getIs_active() != null) existingClazz.setIs_active(clazzDetails.getIs_active());

        return clazzRepository.save(existingClazz);
    }

    public void deleteClazz(Long id) {
        Clazz existingClazz = getClazzById(id);
        existingClazz.setIs_active(false); // Soft Delete
        clazzRepository.save(existingClazz);
    }
}