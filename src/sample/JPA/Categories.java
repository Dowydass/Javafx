package sample.JPA;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class  Categories {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementation")
    @GenericGenerator(name = "incrementation", strategy = "increment")
    private int id;
    @Column(name = "name", length = 512)
    private String name;
    @Column(name = "lft")
    private int lft;
    @Column(name = "rght")
    private int rght;
    @Column(name = "category_parameter_id")
    private int category_parameter_id;
    @Transient
    private long countParents;

    public Categories(int id, String name, int lft, int rght, int category_parameter_id) {
        this.id = id;
        this.name = name;
        this.lft = lft;
        this.rght = rght;
        this.category_parameter_id = category_parameter_id;
    }

    public Categories(int id, String name, long countParents, int category_parameter_id) {
        this.id = id;
        this.name = name;
        this.countParents = countParents;
        this.category_parameter_id = category_parameter_id;
    }

    public Categories(String name, int lft, int rght, int category_parameter_id) {
        this.name = name;
        this.lft = lft;
        this.rght = rght;
        this.category_parameter_id = category_parameter_id;
    }

    public Categories(int id, String name, int lft, int rght, long countParents, int category_parameter_id) {
        this.id = id;
        this.name = name;
        this.lft = lft;
        this.rght = rght;
        this.countParents = countParents;
        this.category_parameter_id = category_parameter_id;
    }

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getlft() {
        return lft;
    }

    public void setlft(int lft) {
        this.lft = lft;
    }

    public int getrght() {
        return rght;
    }

    public void setrght(int rght) {
        this.rght = rght;
    }

    public long getCountParents() {
        return countParents;
    }

    public void setCountParents(long countParents) {
        this.countParents = countParents;
    }

    public int getCategory_parameter_id() {
        return category_parameter_id;
    }

    public void setCategory_parameter_id(int category_parameter_id) {
        this.category_parameter_id = category_parameter_id;
    }

    @Override
    public String toString() {
        return name;
    }
}
