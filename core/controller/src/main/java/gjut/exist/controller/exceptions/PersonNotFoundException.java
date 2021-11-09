package gjut.exist.controller.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Could not find Person: " + id);
    }
}
