import java.io.Serializable;

public class Hall implements Serializable {
    private int number;
    private TypeHall type;

    public Hall(int number) {
        this.number = number;
        switch (number) {
            case 1:
                this.type = TypeHall.GOOD;
                break;
            case 2:
                this.type = TypeHall.SUPERLUX;
                break;
            case 3:
                this.type = TypeHall.GOOD;
                break;
            case 4:
                this.type = TypeHall.GOOD;
                break;
            case 5:
                this.type = TypeHall.LUX;
                break;
            case 6:
                this.type = TypeHall.GOOD;
                break;
            case 7:
                this.type = TypeHall.SUPERLUX;
                break;
            case 8:
                this.type = TypeHall.GOOD;
                break;
            case 9:
                this.type = TypeHall.LUX;
                break;
            case 10:
                this.type = TypeHall.LUX;
                break;
            default:
                this.type = TypeHall.GOOD;
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeHall getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}
