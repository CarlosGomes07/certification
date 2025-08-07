package com.carlosdev.certification.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosdev.certification.modules.questions.entities.QuestionEntity;
import com.carlosdev.certification.modules.questions.repositories.QuestionRepository;
import com.carlosdev.certification.modules.students.dto.StudentCertificationAnswerDTO;
import com.carlosdev.certification.modules.students.entities.AnswersCertificationsEntity;
import com.carlosdev.certification.modules.students.entities.CertificationStudentEntity;
import com.carlosdev.certification.modules.students.entities.StudentEntity;
import com.carlosdev.certification.modules.students.repositories.CertificationStudentRepository;
import com.carlosdev.certification.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) {

        // Buscar as alternativas das pertguntas
        // - Correct ou Incorreta
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        dto.getQuestionAnswers()
                .stream().forEach(questionAnswer -> {
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
                            .findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        ;
                    } else {
                        questionAnswer.setCorrect(false);
                    }

                    var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                            .answerID(questionAnswer.getAlternativeID())
                            .questionID(questionAnswer.getQuestionID())
                            .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answersCertificationsEntity);
                });

        // Verificar se exite student pelo email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

        certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

        // Salvar as informações da cetifications

    }
}
