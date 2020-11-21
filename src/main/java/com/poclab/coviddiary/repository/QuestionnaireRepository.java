package com.poclab.coviddiary.repository;

import com.poclab.coviddiary.entity.Patient;
import com.poclab.coviddiary.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

  @Query("SELECT q FROM Questionnaire q JOIN q.token t JOIN t.patient p WHERE p = :patient")
  List<Questionnaire> findAllByPatient(Patient patient);

  @Query("select q "
      + "from Questionnaire q "
      + "join q.token t "
      + "join t.patient p "
      + "where p = :patient "
      + "and q.submittedOn in (select max(q1.submittedOn) from Questionnaire q1 "
      + "                       join q1.token t1 "
      + "                       join t1.patient p1 "
      + "                       where p1 = :patient)")
  Optional<Questionnaire> findLatestByPatient(Patient patient);

}
