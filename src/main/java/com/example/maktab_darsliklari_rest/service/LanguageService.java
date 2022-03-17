package com.example.maktab_darsliklari_rest.service;

import com.example.maktab_darsliklari_rest.dto.ApiResponse;
import com.example.maktab_darsliklari_rest.entity.Language;
import com.example.maktab_darsliklari_rest.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public ApiResponse getAll() {
        List<Language> languages = languageRepository.findAll();
        return new ApiResponse("mana", true, languages);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            return new ApiResponse("mana", true, optionalLanguage.get());
        }
        return new ApiResponse("yo`q", false, null);
    }

    public ApiResponse save(Language dto) {
       if (!languageRepository.existsByName(dto.getName())) {
           Language language = new Language();
           language.setName(dto.getName());
           Language
                   saveLanguage = languageRepository.save(language);
           return new ApiResponse("qo`shildi", true, saveLanguage);
       }
       return new ApiResponse("bunday til oldin mavjud",false,null);
    }

    public ApiResponse edit(Integer id, Language dto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(optionalLanguage.isPresent()){
            if (!languageRepository.existsByName(dto.getName())) {
                Language language = optionalLanguage.get();
                language.setName(dto.getName());
                Language saveLanguage = languageRepository.save(language);
                return new ApiResponse("editLanguage", true, saveLanguage);
            }
            return new ApiResponse("bunday til oldin mavjut",false,null);
        }
        return new ApiResponse("bunday til topilmadi",false,null);
    }

    public ApiResponse delete(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()){
            languageRepository.deleteById(id);
            return new ApiResponse("O`chdi",true,optionalLanguage.get());
        }
      return   new ApiResponse("Not exsist",false,null);
    }
}
