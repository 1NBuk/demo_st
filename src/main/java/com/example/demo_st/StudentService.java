package com.example.demo_st;
import java.util.List;
//Autowired-анноация,которая нужная для связи всмех зависимостей из всех классов
//то есть собираем проект вместе
import org.springframework.beans.factory.annotation.Autowired;
//Service-анноация для обнаружения всех зависимостей
//и класс service принадлежит springboot
//в этом классе вызов всех методов(bean компонентов)
import org.springframework.stereotype.Service;
@Service
public class StudentService {
    @Autowired
    private StudentRepository repo; // Автоматически создаем и внедряем интерфейс StudentRepository и переименовываем в его repo.
    // Вот так и выглядит внедрение зависимостей (dependency injection)
    //Этот метод возвращает список студентов, возможно, фильтруя их по заданному поисковому запросу. Если keyword не равен null, то используется метод search
    // репозитория для поиска студентов по ключевому слову. В противном случае используется метод findAll для получения всех студентов.
    // Сам список берем из нашей баз данных при помощи репозитория


    //поиск и фильтр в системе
    public List<Student> listAll(String keyword) {
        if (keyword != null) {
            //выводим все результаты поиска
            return repo.search(keyword); // Созданный метод в репозитории
        }
        //если ошибка
        return repo.findAll();// Встроенный в коллекцию метод (JPA)
    }
    //Этот метод сохраняет студента в базе данных, используя репозиторий
    public void save(Student student) {
        repo.save(student);
    }
    //Этот метод получает студента по его идентификатору.
    public Student get(Long id) {
        return repo.findById(id).get();
    }
    //Этот метод удаляет студента из базы данных по его идентификатору
    public void delete(Long id) {
        repo.deleteById(id);
    }
}