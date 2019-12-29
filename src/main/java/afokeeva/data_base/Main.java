package afokeeva.data_base;

import afokeeva.Tables.Animal;
import afokeeva.dao.AnimalDao;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
    boolean res;
    /**
     * RestApi for Animals:
     * findAllAnimals?
     * findAllAnimalsByType?type=1 //find dogs or cats
     * findAllAnimalsByAge?from=0&to=15
     * findAnimalsFilterAgeType?type=1&from=0&to=15
     *
     * findAnimalById?id=1
     * /addAnimal
     * /updateAnimal
     * /deleteAnimal
     */
    //http://localhost:8080/findAnimalById?id=1

    @RequestMapping("/findAllAnimals")
    public @ResponseBody
    String findAllAnimals() {
        String s = new Gson().toJson(new AnimalDao().findAll());
        System.out.println("findAllAnimals " + s);
        return s;
    }
    //http://localhost:8080/findAllAnimalsByType?type=1
    @RequestMapping("/findAllAnimalsByType")
    public @ResponseBody
    String findAllAnimalsByType(@RequestParam("type") String type) {
        System.out.println("-------------------");
        String s = new Gson().toJson(new AnimalDao().findAllAnimalsByType(type));
        System.out.println("findAllAnimalsByType " + s);
        return s;
    }

    @RequestMapping("/findAllAnimalsByAge")
    public @ResponseBody
    String findAllAnimalsByAge(@RequestParam("from") String from,
                          @RequestParam("to") String to) {
        System.out.println("-------------------");
        String s = new Gson().toJson(new AnimalDao().findAllAnimalsByAge(from, to));
        System.out.println("findAllAnimalsByAge " + s);
        return s;
    }

     @RequestMapping("/findAnimalsFilterAgeType")
     public @ResponseBody
     String findAnimalsFilterAgeType(@RequestParam("type") String type,
                              @RequestParam("from") String from,
                              @RequestParam("to") String to) {
        System.out.println("-------------------");
        String s = new Gson().toJson(new AnimalDao().findAnimalsFilterAgeType(type, from, to));
        System.out.println("findAnimalsFilterAgeType " + s);
        return s;
      }

        //****************************************************************
        //**********************************************
    @RequestMapping("/findAnimalById")
    public @ResponseBody
      String findAnimalById(@RequestParam("id") String id) {
        System.out.println("-------------------");
        String s = new Gson().toJson(new AnimalDao().findEntityById(id));
        System.out.println("findAnimalById " + s);
        return s;
    }

    @RequestMapping("/findEntityImagesAndDescriptionById")
    public @ResponseBody
      String findEntityImagesAndDescriptionById(@RequestParam("id") String id) {
        System.out.println("-------------------");
        String s = new Gson().toJson(new AnimalDao().findEntityImagesAndDescriptionById(id));
        System.out.println("findEntityImagesAndDescriptionById " + s);
        return s;
    }

    @RequestMapping("/addAnimal")
    public @ResponseBody
    boolean addAnimal(@RequestParam("age") String age,
                  @RequestParam("name") String name,
                  @RequestParam("des") String des,
                  @RequestParam("id_type") String type){
        System.out.println(age + " " + name + " " + des);
        res = new AnimalDao().add(new Animal(age,name,des,type));
        System.out.println("addAnimal " );
        return res;
    }

    @RequestMapping("/updateAnimal")
    public @ResponseBody
    String updateAnimal(@RequestParam("age") String age,
                  @RequestParam("name") String name,
                  @RequestParam("des") String des,
                  @RequestParam("id_type") String type){
        String s = new Gson().toJson(new AnimalDao().update(new Animal(age,name,des,type)));
        System.out.println("updateAnimal " +s);
        return s;
    }

    @RequestMapping("/deleteAnimal")
    public @ResponseBody
        String deleteAnimal(@RequestParam("id") String id) {
        String s = new Gson().toJson(new AnimalDao().delete(id));
        System.out.println("deleteAnimal "+s);
        return s;
    }



}

