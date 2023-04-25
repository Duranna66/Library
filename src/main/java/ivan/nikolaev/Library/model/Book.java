package ivan.nikolaev.Library.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class Book {

    private int id;

    private int person_id;
    @NotNull(message = "название не должно быть пустым")
    private String title;

    @NotNull(message = "автор не должен быть пустым")
    private String author;
    @Min(value = 1500, message = "слишком много лет")
    private int year;
}
