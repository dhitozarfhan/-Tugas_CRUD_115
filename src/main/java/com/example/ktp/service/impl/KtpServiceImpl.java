package com.example.ktp.service.impl;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.Ktp;
import com.example.ktp.exception.DuplicateResourceException;
import com.example.ktp.exception.ResourceNotFoundException;
import com.example.ktp.repository.KtpRepository;
import com.example.ktp.service.KtpService;
import com.example.ktp.util.KtpMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KtpServiceImpl implements KtpService {

    private KtpRepository ktpRepository;

    @Override
    public KtpDto createKtp(KtpDto ktpDto) {
        if (ktpRepository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new DuplicateResourceException("Nomor KTP sudah terdaftar");
        }
        Ktp ktp = KtpMapper.mapToKtp(ktpDto);
        Ktp savedKtp = ktpRepository.save(ktp);
        return KtpMapper.mapToKtpDto(savedKtp);
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id : " + id));
        return KtpMapper.mapToKtpDto(ktp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktpList = ktpRepository.findAll();
        return ktpList.stream().map(KtpMapper::mapToKtpDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpDto updatedKtp) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id : " + id));

        if (ktpRepository.existsByNomorKtpAndIdNot(updatedKtp.getNomorKtp(), id)) {
            throw new DuplicateResourceException("Nomor KTP sudah digunakan oleh data lain");
        }

        ktp.setNomorKtp(updatedKtp.getNomorKtp());
        ktp.setNamaLengkap(updatedKtp.getNamaLengkap());
        ktp.setAlamat(updatedKtp.getAlamat());
        ktp.setTanggalLahir(updatedKtp.getTanggalLahir());
        ktp.setJenisKelamin(updatedKtp.getJenisKelamin());

        Ktp savedKtp = ktpRepository.save(ktp);
        return KtpMapper.mapToKtpDto(savedKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        ktpRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data KTP tidak ditemukan dengan id : " + id));
        ktpRepository.deleteById(id);
    }
}
