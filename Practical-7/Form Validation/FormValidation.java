import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
    String message() default "must not be blank";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();

    String message() default "exceeds maximum length";
}


class SignupForm {
    @NotBlank(message = "Username is required")
    @MaxLength(value = 20, message = "Username must be at most 20 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @MaxLength(value = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @MaxLength(value = 50, message = "Password must be at most 50 characters")
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

record ValidationError(String field, String message) {
}

final class FormValidator {
    private FormValidator() {
    }

    public static List<ValidationError> validate(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to validate must not be null");
        }

        List<ValidationError> errors = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Object rawValue;
            try {
                rawValue = field.get(object);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException(
                        "Could not read field: " + field.getName(), e);
            }

            String value = rawValue == null ? null : rawValue.toString();

            NotBlank notBlank = field.getAnnotation(NotBlank.class);
            if (notBlank != null && (value == null || value.trim().isEmpty())) {
                errors.add(new ValidationError(field.getName(), notBlank.message()));
            }

            MaxLength maxLength = field.getAnnotation(MaxLength.class);
            if (maxLength != null && value != null && value.length() > maxLength.value()) {
                errors.add(new ValidationError(field.getName(), maxLength.message()));
            }
        }

        return errors;
    }
}
public class FormValidation {
    public static void main(String[] args) {
        SignupForm form = new SignupForm(
                "",
                "a-very-long-email-address-that-exceeds-the-limit@example.com",
                "secret"
        );

        List<ValidationError> errors = FormValidator.validate(form);

        errors.forEach(error ->
                System.out.println(error.field() + ": " + error.message())
        );
    }
}
