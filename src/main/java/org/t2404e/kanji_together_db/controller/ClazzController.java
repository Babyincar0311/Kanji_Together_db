package org.t2404e.kanji_together_db.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.t2404e.kanji_together_db.dto.ErrorResponse;
import org.t2404e.kanji_together_db.entity.Clazz;
import org.t2404e.kanji_together_db.service.ClazzService;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String msg, String path) {
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), msg, path);
        return ResponseEntity.status(status).body(err);
    }

    // 1. Create
    @PostMapping
    public ResponseEntity<?> createClazz(@Valid @RequestBody Clazz clazz, BindingResult result, HttpServletRequest request) {
        // Kiểm tra lỗi Validation
        if (result.hasErrors()) {
            String msg = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return buildError(HttpStatus.BAD_REQUEST, "Validation failed: " + msg, request.getRequestURI());
        }

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clazzService.createClazz(clazz));
        } catch (Exception e) {
            return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
        }
    }

    // 2. Xem chi tiết
    @GetMapping("/{id}")
    public ResponseEntity<?> getClazz(@PathVariable Long id, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(clazzService.getClazzById(id));
        } catch (RuntimeException e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        }
    }

    // 3. List
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Boolean is_active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id,desc") String sort,
            HttpServletRequest request
    ) {
        try {
            String[] sortParams = sort.split(",");
            Sort.Direction dir = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortParams[0]));

            return ResponseEntity.ok(clazzService.getAllClazz(q, is_active, pageable));
        } catch (Exception e) {
            return buildError(HttpStatus.BAD_REQUEST, "Invalid sort parameter or query: " + e.getMessage(), request.getRequestURI());
        }
    }

    // 4. Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClazz(@PathVariable Long id, @RequestBody Clazz clazz, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(clazzService.updateClazz(id, clazz));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return buildError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
            }
            return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
        }
    }

    // 5. Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClazz(@PathVariable Long id, HttpServletRequest request) {
        try {
            clazzService.deleteClazz(id);
            return ResponseEntity.ok("Đã xóa Clazz thành công");
        } catch (RuntimeException e) {
            return buildError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
        }
    }
}