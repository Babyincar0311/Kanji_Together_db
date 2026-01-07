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

    // 3. Tạo mới user
    public Users createUser(Users user) {
        // Kiểm tra Email không được trùng
        if (user.email != null && usersRepository.findByEmail(user.email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại: " + user.email);
        }

        // Thiết lập giá trị mặc định khi tạo mới
        user.is_active = true;
        user.is_verified = false;

        return usersRepository.save(user);
    }

    // 4. Cập nhật user
    public Users updateUser(Long id, Users userDetails) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));

        // Cập nhật thông tin
        user.setName(userDetails.getName());
        user.setHas_entrance_exam(userDetails.getHas_entrance_exam());
        return usersRepository.save(user);
    }

    // 5. Xóa
    public void softDeleteUser(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User để xóa"));
        user.setIs_active(false);
        usersRepository.save(user);
    }
}