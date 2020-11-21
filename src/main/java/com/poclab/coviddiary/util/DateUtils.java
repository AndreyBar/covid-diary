package com.poclab.coviddiary.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;

@UtilityClass
public class DateUtils {

  public static int calculateAge(LocalDate birthDate) {
    if (birthDate != null) {
      return Period.between(birthDate, LocalDate.now()).getYears();
    } else {
      return 0;
    }
  }

}
