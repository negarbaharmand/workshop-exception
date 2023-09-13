package se.lexicon.exceptions.workshop.data_access;

import se.lexicon.exceptions.workshop.domain.Gender;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exceptions.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

import java.util.List;
import java.util.Random;

public class NameService {


    private List<String> maleFirstNames;
    private List<String> femaleFirstNames;
    private List<String> lastNames;
    private static Random random = new Random();

    //should be no nulls
    public NameService(List<String> maleFirstNames, List<String> femaleFirstNames, List<String> lastNames) {

        this.maleFirstNames = maleFirstNames;
        this.femaleFirstNames = femaleFirstNames;
        this.lastNames = lastNames;
    }

    public Person getNewRandomPerson() {
        Gender gender = getRandomGender();
        Person person = null;
        switch (gender) {
            case MALE:
                person = new Person(getRandomMaleFirstName(), getRandomLastName(), gender);
                break;
            case FEMALE:
                person = new Person(getRandomFemaleFirstName(), getRandomLastName(), gender);
                break;
        }
        return person;
    }


    public String getRandomFemaleFirstName() {
        return femaleFirstNames.get(random.nextInt(femaleFirstNames.size()));
    }

    public String getRandomMaleFirstName() {
        return maleFirstNames.get(random.nextInt(maleFirstNames.size()));
    }

    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    public Gender getRandomGender() {
        return random.nextInt(100) > 50 ? Gender.FEMALE : Gender.MALE;
    }


    public void addFemaleFirstName(String name) throws DuplicateNameException {
        if (femaleFirstNames.contains(name)) {
            throw new DuplicateNameException("Female first name '" + name + "' already exists.");
        }
        femaleFirstNames.add(name);
        CSVReader_Writer.saveFemaleNames(femaleFirstNames);
    }

    public void addMaleFirstName(String name) throws DuplicateNameException {
        if (maleFirstNames.contains(name)) {
            throw new DuplicateNameException("Male first name '" + name + "' already exists.");
        }
        maleFirstNames.add(name);
        CSVReader_Writer.saveMaleNames(maleFirstNames);
    }

    public void addLastName(String lastName) throws DuplicateNameException {
        if (lastNames.contains(lastName)) {
            throw new DuplicateNameException("Last name '" + lastName + "' already exists.");
        }
        lastNames.add(lastName);
        CSVReader_Writer.saveLastNames(lastNames);
    }

}
