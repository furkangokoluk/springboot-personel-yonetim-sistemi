package com.ltp.pys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.pys.entity.personel;
import com.ltp.pys.repository.personelRepository;


@Service
public class personService 
{
    private final personelRepository personelRepository;

    public personService(personelRepository personelRepository)
    {
        this.personelRepository = personelRepository;
    }

    public List<personel> gettAllPersonel() {
        return personelRepository.findAll();
    }

    public personel saveOnePersonel(personel newPersonel) {
        return personelRepository.save(newPersonel);
    }

    public personel getOnePersonel(Long personelId) {
        return personelRepository.findById(personelId).orElse(null);
    }

    public void deleteById(Long personelId) {
        personelRepository.deleteById(personelId);
    }

    public personel updateOnePersonel(Long personelId, personel newPersonel) {
        Optional<personel> optionalPersonel = personelRepository.findById(personelId);
        
        if (optionalPersonel.isPresent()) {
            personel foundPersonel = optionalPersonel.get();
            foundPersonel.setAd(newPersonel.getAd());
            foundPersonel.setSoyad(newPersonel.getSoyad());
            foundPersonel.setTel_no(newPersonel.getTel_no());
            foundPersonel.setDogum_tarihi(newPersonel.getDogum_tarihi());
            foundPersonel.setUnvan(newPersonel.getUnvan());
            foundPersonel.setMaas(newPersonel.getMaas());
            personelRepository.save(foundPersonel);
            return foundPersonel;
        } else {
            return null;
        }
    }
}
