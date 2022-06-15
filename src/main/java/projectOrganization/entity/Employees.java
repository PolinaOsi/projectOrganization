package projectOrganization.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "employees")
public class Employees {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_employee")
        private Integer id_employee;

        @Column(name = "name_employee", nullable = false)
        private String name_employee;

        @Column(name = "surname_employee", nullable = false)
        private String surname_employee;

        @Column(name = "patronymic_employee", nullable = false)
        private String patronymic_employee;

        @Column(name = "date_birth", nullable = false)
        private String date_birth;

        @Column(name = "id_association")
        private Integer id_association;

        @Column(name = "id_rank")
        private Integer id_rank;

        @Column(name = "id_army")
        private Integer id_army;

        @Column(name = "id_unit")
        private Integer id_unit;

        @Column(name = "id_department")
        private Integer id_department;

        @Column(name = "characteristic", nullable = false)
        private String characteristic;

        @ManyToOne
        private Ranks rank;

        @ManyToOne
        private Armies army;

        @ManyToOne
        private Departments department;

        @ManyToOne
        private Military_units military_unit;

        @ManyToOne
        private Associations association;

    public Employees(Integer id_employee, String name_employee, String surname_employee,
                     String patronymic_employee, String date_birth, Integer id_association,
                     Integer id_rank, Integer id_army, Integer id_unit, Integer id_department, String characteristic) {
        this.id_employee = id_employee;
        this.name_employee = name_employee;
        this.surname_employee = surname_employee;
        this.patronymic_employee = patronymic_employee;
        this.date_birth = date_birth;
        this.id_association = id_association;
        this.id_rank = id_rank;
        this.id_army = id_army;
        this.id_unit = id_unit;
        this.id_department = id_department;
        this.characteristic = characteristic;
        this.army = null;
        this.rank = null;
        this.department = null;
        this.military_unit = null;
        this.association = null;
    }

    public Employees() { }

    public Integer getId_employee() {
        return id_employee;
    }

    public void setId_employee(Integer id_employee) {
        this.id_employee = id_employee;
    }

    public String getName_employee() {
        return name_employee;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public String getSurname_employee() {
        return surname_employee;
    }

    public void setSurname_employee(String surname_employee) {
        this.surname_employee = surname_employee;
    }

    public String getPatronymic_employee() {
        return patronymic_employee;
    }

    public void setPatronymic_employee(String patronymic_employee) {
        this.patronymic_employee = patronymic_employee;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public Integer getId_association() {
        return id_association;
    }

    public void setId_association(Integer id_association) {
        this.id_association = id_association;
    }

    public Integer getId_rank() {
        return id_rank;
    }

    public void setId_rank(Integer id_rank) {
        this.id_rank = id_rank;
    }

    public Integer getId_army() {
        return id_army;
    }

    public void setId_army(Integer id_army) {
        this.id_army = id_army;
    }

    public Integer getId_unit() {
        return id_unit;
    }

    public void setId_unit(Integer id_unit) {
        this.id_unit = id_unit;
    }

    public Integer getId_department() {
        return id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public Armies getArmy() {
        return army;
    }

    public void setArmy(Armies army) {
        this.army = army;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Military_units getMilitary_unit() {
        return military_unit;
    }

    public void setMilitary_unit(Military_units military_unit) {
        this.military_unit = military_unit;
    }

    public Associations getAssociation() {
        return association;
    }

    public void setAssociation(Associations association) {
        this.association = association;
    }
}
