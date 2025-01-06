package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Kullanici;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
}