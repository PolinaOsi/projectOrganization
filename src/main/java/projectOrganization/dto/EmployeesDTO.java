package projectOrganization.dto;
import lombok.Data;

@Data
public class EmployeesDTO {
    private Integer id_employee;
    private String name_employee;
    private String surname_employee;
    private String patronymic_employee;
    private String date_birth;
    private Integer id_association;
    private Integer id_rank;
    private Integer id_army;
    private Integer id_unit;
    private Integer id_department;
    private String characteristic;
}
