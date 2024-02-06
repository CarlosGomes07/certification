package com.carlosdev.certification.modules.students.useCases;

import org.springframework.stereotype.Service;

import com.carlosdev.certification.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {
    
    public boolean excute( VerifyHasCertificationDTO dto ) {
        if(dto.getEmail().equals("carlosrick@gmail.com") && dto. getTechnology().equals("Java")){
            return true;
        }
        return false;
    }
}
