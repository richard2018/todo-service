package com.example.todo;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
public class ToDoServiceTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    ToDoService toDoService;

    @After
    public void tearDown(){
        toDoRepository.deleteAll();
    }

    @Test
    public void test1() {
        System.out.println(toDoService);
        System.out.println(toDoRepository);
    }
    @Test
    public void getAllToDos(){
        ToDo todoSample = new ToDo("Todo Sample 1",true);
        toDoRepository.save(todoSample);
        ToDoService toDoService = new ToDoService(toDoRepository);

        List<ToDo> toDoList = toDoService.findAll();
        ToDo lastToDo = toDoList.get(toDoList.size()-1);

        assertEquals(todoSample.getText(), lastToDo.getText());
        assertEquals(todoSample.isCompleted(), lastToDo.isCompleted());
        assertEquals(todoSample.getId(), lastToDo.getId());
    }

    @Test
    public void saveAToDo() {
        ToDoService toDoService = new ToDoService(toDoRepository);
        ToDo todoSample = new ToDo("Todo Sample 1",true);

        toDoService.save(todoSample);

        assertEquals(1, toDoRepository.count());
    }
}