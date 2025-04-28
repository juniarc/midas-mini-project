package dev.codejar.model.entity;


import dev.codejar.model.entity.enums.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenType tokenType;

    @Column(nullable = false, unique = true, length = 500)
    private String accessToken;

    @Column(nullable = false, unique = true, length = 500)
    private String refreshToken;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private LocalDateTime refreshExpiresAt;

    @Column(name = "expired", nullable = false)
    private boolean expired = false;

    // Untuk menandai token tidak boleh dipakai lagi
    @Column(nullable = false)
    private boolean revoked = false;

    @Column(nullable = false)
    private Boolean isValid = true;

    // Set expiration time otomatis saat token dibuat
    @PrePersist
    public void setExpirationTime() {
        if (expiresAt == null) {
            expiresAt = tokenType == TokenType.ACCESS
                    ? createdAt.plusMinutes(30)  // Access Token valid 30 menit
                    : createdAt.plusDays(7);     // Refresh Token valid 7 hari
        }
        if (refreshExpiresAt == null) {
            refreshExpiresAt = createdAt.plusDays(7); // Refresh Token valid 7 hari
        }
    }

    // ✅ Pastikan token valid hanya jika belum expired & belum revoked
    public boolean isValid() {
        return !revoked && !expired && expiresAt.isAfter(LocalDateTime.now());
    }



}
