package interfaces;

public class CountSortable {
    private Integer id;

    public CountSortable() {
    }

    public CountSortable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public CountSortable setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
