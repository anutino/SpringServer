package com.afokeeva.controller;

import com.afokeeva.repository.Animal;
import com.afokeeva.repository.User;
import com.afokeeva.table.AnimalData;
import com.afokeeva.table.AnimalType;
import com.afokeeva.table.UserData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
//@Controller //404 if use Controller  you need use @ResponseBody/ For  RestController ResponseBody dont need чето так не работает
public class MainController {

    private final ObjectMapper mapper;

    public MainController(ObjectMapper mapper) {
        this.mapper = mapper;
    }
//    @GetMapping("/controller")
//    public String foo() {
//        return "Response!";
//    }

    @GetMapping("/animal-types")
    public String getAnimalTypes() throws JsonProcessingException {
        ArrayList<AnimalType> animal = new Animal().getAnimalTypes();
        return mapper.writeValueAsString(animal);
    }

    @GetMapping("/animal-short-info-list")
    public String getAnimalShortInfoList() throws JsonProcessingException {
        ArrayList<AnimalData> animal = new Animal().getAnimalShortInfoList();
        //  return "getJsonAnimalsShortInfoList(animal)";
        //  return getJsonAnimalsShortInfoList(animal);
        return mapper.writeValueAsString(animal);
    }
//
//    @RequestMapping(value = "/animal-short-info-list1")
//    @ResponseBody
//    public String getAnimalShortInfoList1() throws JsonProcessingException {
//        ArrayList<AnimalData> animal = new Animal().getAnimalShortInfoList();
//        //  return "getJsonAnimalsShortInfoList(animal)";
//        //  return getJsonAnimalsShortInfoList(animal);
//        return mapper.writeValueAsString(animal);
//    }

    /*
   {"id":"1","description":"Как жилось собаке на улице, если побывав в Зоозащите по программе ОСВВ,
   когда ее выпускали назад после стерилизации, она не выбрала волю, а бежала за машиной ловца?",
   "mediaList":["animals/dog/bambi_1.jpg","animals/dog/bambi_2.jpg","animals/dog/bambi_3.jpg",
   "animals/dog/bambi_4.jpg"]}
    */
    //http://localhost:8081/animal-detailed-info?animal_id=1
    @GetMapping("/animal-detailed-info")
    @ResponseBody
    public String getAnimalDetailedInfo(@RequestParam("animal_id") String animalId) throws JsonProcessingException {
        AnimalData animal = new Animal().getAnimalDetailedInfo(animalId);
//        String array = "";
//        for (int i = 0; i < animal.getMediaList().size(); i++) {
//            if (i != animal.getMediaList().size() - 1) {
//                array += "\"" + animal.getMediaList().get(i) + "\",";
//            } else {
//                array += "\"" + animal.getMediaList().get(i) + "\"";
//            }
//        }
//        String jsonText = "{\"id\":\"" + animal.getId() + "\"," +
//                "\"description\":\"" + animal.getDescription() + "\"," +
//                "\"mediaList\":[" + array + "]}";
        // return jsonText;
        return mapper.writeValueAsString(animal);

    }

    /*
        [{"id":"1","age":"1.5","name":"Бэмби","type":"1","main_picture":"animals/dog/bambi_1.jpg"},
        {"id":"2","age":"1.5","name":"Гермиона","type":"1","main_picture":"animals/dog/germiona_1.jpg"},
        {"id":"4","age":"0.7","name":"Флёр","type":"1","main_picture":"animals/dog/fler_1.jpg"},
        {"id":"5","age":"0.5","name":"Клубничка","type":"2","main_picture":"animals/cat/klubnichka_1.jpg"},
        {"id":"6","age":"3.0","name":"Рузи","type":"2","main_picture":"animals/cat/ruzi_1.jpg"},
        {"id":"7","age":"0.7","name":"Зорька","type":"2","main_picture":"animals/cat/zorka_1.jpg"}]
         */
    @GetMapping("/animal-short-info-filtered-by-age")
    @ResponseBody
    public String getAnimalShortInfoListFilteredByAge(@RequestParam("from") String from, @RequestParam("to") String to) throws JsonProcessingException {
        ArrayList<AnimalData> animal = new Animal().getAnimalShortInfoListFilteredByAge(from, to);
        //  return getJsonAnimalsShortInfoList(animal);
        return mapper.writeValueAsString(animal);
    }


    // [{"name":"Клубничка","id":5,"age":0.5,"mainPicture":"animals/cat/klubnichka_1.jpg"},
    // {"name":"Рузи","id":6,"age":3.0,"mainPicture":"animals/cat/ruzi_1.jpg"},
    // {"name":"Зорька","id":7,"age":0.7,"mainPicture":"animals/cat/zorka_1.jpg"}]
    @GetMapping("/animal-short-info-filtered-by-type")
    @ResponseBody
    public String getAnimalShortInfoListFilteredByType(@RequestParam("type") String type) throws JsonProcessingException {
        ArrayList<AnimalData> animal = new Animal().getAnimalShortInfoListFilteredByType(type);
        System.out.println(animal);
//        return getJsonAnimalsShortInfoList(animal);
        return mapper.writeValueAsString(animal);

    }

    /*
   [{"name":"Клубничка","id":5,"type":2,"age":0.5,"mainPicture":"animals/cat/klubnichka_1.jpg"},
   {"name":"Зорька","id":7,"type":2,"age":0.7,"mainPicture":"animals/cat/zorka_1.jpg"}]
     */
    @GetMapping("/animal-short-info-list-filtered-by-age-type")
    @ResponseBody
    public String getAnimalShortInfoListFilteredByAgeAndType(@RequestParam("from") String from,
                                                             @RequestParam("to") String to,
                                                             @RequestParam("type") String type) throws JsonProcessingException {
        ArrayList<AnimalData> animal = new Animal().getAnimalShortInfoListFilteredByAgeAndType(from, to, type);
//        return getJsonAnimalsShortInfoList(animal);
        return mapper.writeValueAsString(animal);

    }

    @GetMapping("/create-user")
    @ResponseBody
    public boolean createUser(@RequestParam("name") String name,
                              @RequestParam("login") String login,
                              @RequestParam("password") String password) {
        return (new User().createUser(new UserData(name, login, password)));
    }

    @GetMapping("/delete-user")
    @ResponseBody
    public boolean deleteUser(@RequestParam("user_id") String userId) {
        return (new User().deleteUser(userId));
    }

    @GetMapping("/sign-in")
    @ResponseBody
    public boolean signInUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        return (new User().signInUser(login, password));
    }


    @GetMapping("/add-favorite-animal")
    @ResponseBody
    public boolean addFavoriteAnimal(@RequestParam("user_id") String userId, @RequestParam("animal_id") String animalId) {
        return (new Animal().addFavoriteAnimal(userId, animalId));
    }

    @GetMapping("/delete-favorite-animal")
    @ResponseBody
    public boolean deleteFavoriteAnimal(@RequestParam("user_id") String userId, @RequestParam("animal_id") String animalId) {
        return (new Animal().deleteFavoriteAnimal(userId, animalId));
    }

    /*
    [{"id":"1","age":"1.5","name":"Бэмби","type":"1","main_picture":"animals/dog/bambi_1.jpg"},
    {"id":"2","age":"1.5","name":"Гермиона","type":"1","main_picture":"animals/dog/germiona_1.jpg"},
    {"id":"3","age":"4.0","name":"Денвер","type":"1","main_picture":"animals/dog/denver_1.jpg"},
    {"id":"4","age":"0.7","name":"Флёр","type":"1","main_picture":"animals/dog/fler_1.jpg"}]
     */
    @GetMapping("/favorite-animal-list")
    @ResponseBody
    public String getFavoriteAnimalList(@RequestParam("user_id") String userId) throws JsonProcessingException {
        //  return getJsonAnimalsShortInfoList(new Animal().getFavoriteAnimalsList(userId));
        ArrayList<AnimalData> animal = new Animal().getFavoriteAnimalList(userId);
        return mapper.writeValueAsString(animal);

    }

//    private String getJsonAnimalShortInfoList(ArrayList<AnimalData> animal) {
//        String jsonText = "[";
//        String comma = ",";
//        for (int i = 0; i < animal.size(); i++) {
//            jsonText += "{\"id\":\"" + animal.get(i).getId() + "\","
//                    + "\"age\":\"" + animal.get(i).getAge() + "\","
//                    + "\"name\":\"" + animal.get(i).getName() + "\","
//                    + "\"type\":\"" + animal.get(i).getType() + "\","
//                    + "\"main_picture\":\"" + animal.get(i).getMainPicture() + "\"}";
//
//            if (i != animal.size() - 1) {
//                jsonText += comma;
//            }
//        }
//        jsonText += "]";
//        return jsonText;
//    }

//    @GetMapping("/animals-short-info-list-by-user") //TODO get favorite list
//    @ResponseBody
//    public String getAnimalsShortInfoList() throws JsonProcessingException {
//        ArrayList<AnimalData> animal = new Animal().getAnimalsShortInfoList();
//        //  return "getJsonAnimalsShortInfoList(animal)";
//        //  return getJsonAnimalsShortInfoList(animal);
//        return mapper.writeValueAsString(animal);
//    }

}

