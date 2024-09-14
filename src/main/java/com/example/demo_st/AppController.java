package com.example.demo_st;


import java.util.List;
//связываем все зависимости
import org.springframework.beans.factory.annotation.Autowired;
//привязываем наши параметры передачи sql запроса
import org.springframework.data.repository.query.Param;
//позволяет распозновать наш класс как управляющий
//тут будут адреса наших страниц, из какой таблицы и т.д.
import org.springframework.stereotype.Controller;
//интерфейс Model необохим для взаимодействия контроллера и конфигуратора
//и добавляния всех элементов в интерфейс
import org.springframework.ui.Model;
//аннотация для связывания параметра и метода/переменной, которая будет выводиться в веб интерфейсе
import org.springframework.web.bind.annotation.ModelAttribute;
//отвечает за получение параметра из адресной строки браузера
import org.springframework.web.bind.annotation.PathVariable;
//для создания адреса(url) в браузерной строке
//адреса контроллеров в целом и методов в частности
import org.springframework.web.bind.annotation.RequestMapping;
//метод, указывающий с помощью какого http запроса будем передавать данные(POST,GET...)
import org.springframework.web.bind.annotation.RequestMethod;
//метод, позволяющий указывать название html страницы, которую мы подвязываем к контроллеру
import org.springframework.web.servlet.ModelAndView;


@Controller // Весь наш класс будет контроллером
public class AppController { // Создаем класс с модификатором доступа Public, так как контроллер должен быит открытым полностью
    // из-за аннотации @Controller
    @Autowired
    private StudentService service;
    // Автоматически создаем и внедряем класс StudentService и переименовываем в его service. Вот так и выглядит
    // внедрение зависимостей (dependency injection)

    @RequestMapping("/") // слеш => Наша главная страница
    //1. Вызывается метод service.listAll(keyword), который, вероятно, возвращает список студентов, соответствующих заданному
    // поисковому запросу.
    //2. Полученный список добавляется в модель под именем listStudents.
//3. Значение keyword также добавляется в модель.
//4. Возвращается имя представления index, которое будет отображаться в ответ на запрос.
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
//Реализация поиска на главной странице по критериям
        List<Student> listStudents = service.listAll(keyword); // Наш список студентов. Элементы в список передаются из класса StudentService
        model.addAttribute("listStudents", listStudents); // Создаем модель и добавляем в нее атрибут. На главной странице будет
        // выводиться список студентов
        model.addAttribute("keyword", keyword); // Создаем модель и добавляем в нее атрибут. На главной странице будет выводиться
        // поиск студентов
        //index.html-туда будут возвращаться данные
        return "index"; // Выводится все то, что отображено в шаблоне index.html, модели будут туда также добавляться
    }
    //контроллер по добавлению студента
    @RequestMapping("/new")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }
    //метод сохранения студента
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.save(student);
        return "redirect:/";
    }

    //контроллер по редактированию струдентов по ключу id
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_student");
        Student student = service.get(id);  // Получаем студента по ID
        mav.addObject("student", student);  // Передаем объект студента в шаблон
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

}