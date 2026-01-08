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

    // 1. lấy danh sách tất cả user
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // 2. lay user theo ID
    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    // 3. Tạo mới user
    public Users createUser(Users user) {

        //  Kiểm tra tên không được rỗng
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new RuntimeException("Tên user không được để trống");
        }

        //  kiểm tra Email không được trùng
        if (user.getEmail() != null && usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại: " + user.getEmail());
        }
        //  set defaund value
        if (user.getHas_entrance_exam() == null) {
            user.setHas_entrance_exam(false);
        }

        if (user.getIs_active() == null) {
            user.setIs_active(true);
        }

        if (user.getIs_verified() == null) {
            user.setIs_verified(false);
        }

        return usersRepository.save(user);
    }

    // 4. Cập nhật user
    public Users updateUser(Long id, Users userDetails) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + id));

        // Cập nhật thông tin
        user.setName(userDetails.getName());
        user.setHas_entrance_exam(userDetails.getHas_entrance_exam());

        // sửa email
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            if (usersRepository.existsByEmail(userDetails.getEmail())) {
                throw new RuntimeException("Email mới đã tồn tại!");
            }
            user.setEmail(userDetails.getEmail());
        }

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