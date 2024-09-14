package com.example.demo_st;
//в интерфейсе аннотация с sql запросом
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//аантоция отвечает за sql запросы
import org.springframework.data.jpa.repository.Query;
//p-параметр,нам вавжно взаимодействовать с классом Студент
//1-наполнитель значения. слово, которое введем в переменной будем отображаться вместо него
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT p FROM Student p WHERE CONCAT(p.first, ' ', p.last, ' ', p.num, ' ', p.av) LIKE %?1%")
    List<Student> search(String keyword);
} // сформированный список, используемый во всем исходном коде. Все переменные в запрос берутся класса Student

