package ivan.nikolaev.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Client {
    private int id;
    private String FIO;
    private int yearOfBorn;

    public Client(int id, String FIO, int yearOfBorn) {
        this.FIO = FIO;
        this.yearOfBorn = yearOfBorn;
        this.id = id;
    }

    public Client() {
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYearOfBorn() {
        return yearOfBorn;
    }

    public void setYearOfBorn(int yearOfBorn) {
        this.yearOfBorn = yearOfBorn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
