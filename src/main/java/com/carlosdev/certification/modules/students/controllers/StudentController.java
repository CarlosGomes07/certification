package com.carlosdev.certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosdev.certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.carlosdev.certification.modules.students.dto.VerifyHasCertificationDTO;
import com.carlosdev.certification.modules.students.entities.CertificationStudentEntity;
import com.carlosdev.certification.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.carlosdev.certification.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    //Preciso usar o meu USECASE
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) {
        //Email
        //Technology
        var result = this.verifyIfHasCertificationUseCase.excute(verifyHasCertificationDTO);
        if (result) {
            return "Usario ja fez a prova";   
        }

        return "Usuario pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public CertificationStudentEntity certificationAnswer (@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO)  {
        return studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
    }
}
