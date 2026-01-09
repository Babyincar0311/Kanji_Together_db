package org.t2404e.kanji_together_db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.t2404e.kanji_together_db.entity.Users;
import org.t2404e.kanji_together_db.repository.UsersRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    // 1. Lấy danh sách tất cả user
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // 2. Lấy user theo ID
    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createUser(Users user) {
        // --- VALIDATION ---
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new RuntimeException("Tên user không được để trống");
        }

        if (user.getEmail() != null && usersRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại: " + user.getEmail());
        }

        // --- SECURITY & DEFAULT VALUES ---
        user.setIs_verified(false);
        user.setIs_active(true);

        if (user.getHas_entrance_exam() == null) {
            user.setHas_entrance_exam(false);
        }

        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users userDetails) {
        Users currentUser = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));

        // Cập nhật tên
        if (userDetails.getName() != null && !userDetails.getName().isEmpty()) {
            currentUser.setName(userDetails.getName());
        }

        // Cập nhật trạng thái thi đầu vào
        if (userDetails.getHas_entrance_exam() != null) {
            currentUser.setHas_entrance_exam(userDetails.getHas_entrance_exam());
        }

        // Xử lý logic đổi Email
        if (userDetails.getEmail() != null
                && !userDetails.getEmail().isEmpty()
                && !userDetails.getEmail().equals(currentUser.getEmail())) {

            if (usersRepository.existsByEmail(userDetails.getEmail())) {
                throw new RuntimeException("Email mới đã tồn tại!");
            }
            currentUser.setEmail(userDetails.getEmail());
        }

        return usersRepository.save(currentUser);
    }
    // 5. Xóa
    public void softDeleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User để xóa"));

        user.setIs_active(false);
        usersRepository.save(user);
    }
}