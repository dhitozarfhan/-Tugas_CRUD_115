package com.example.ktp.util;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.Ktp;

public class KtpMapper {

    public static KtpDto mapToKtpDto(Ktp ktp) {
        return new KtpDto(
                ktp.getId(),
                ktp.getNomorKtp(),
                ktp.getNamaLengkap(),
                ktp.getAlamat(),
                ktp.getTanggalLahir(),
                ktp.getJenisKelamin());
    }

    public static Ktp mapToKtp(KtpDto ktpDto) {
        Ktp ktp = new Ktp();
        ktp.setId(ktpDto.getId());
        ktp.setNomorKtp(ktpDto.getNomorKtp());
        ktp.setNamaLengkap(ktpDto.getNamaLengkap());
        ktp.setAlamat(ktpDto.getAlamat());
        ktp.setTanggalLahir(ktpDto.getTanggalLahir());
        ktp.setJenisKelamin(ktpDto.getJenisKelamin());
        return ktp;
    }
}
