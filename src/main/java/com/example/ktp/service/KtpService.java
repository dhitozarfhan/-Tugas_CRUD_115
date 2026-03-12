package com.example.ktp.service;

import com.example.ktp.dto.KtpDto;

import java.util.List;

public interface KtpService {
    KtpDto createKtp(KtpDto ktpDto);

    KtpDto getKtpById(Integer id);

    List<KtpDto> getAllKtp();

    KtpDto updateKtp(Integer id, KtpDto updatedKtp);

    void deleteKtp(Integer id);
}
