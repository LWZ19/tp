package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Comment;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;

/**
 * Adds a comment to a student.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the comment of the person identified "
            + "by the student number used.\n"
            + "Existing comment will be overwritten by the input.\n"
            + "Parameters: " + PREFIX_STUDENT_NUMBER + "[StudentNumber] (must be an existing student number) "
            + PREFIX_COMMENT + "[Comment]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STUDENT_NUMBER + "A1234567M" + " "
            + PREFIX_COMMENT + " Struggling with tutorials.";

    public static final String MESSAGE_COMMENT_SUCCESS = "Comment added successfully.";


    private final StudentNumber studentNumber;
    private final Comment comment;


    /**
     * Creates an CommentCommand to add the specified {@code Comment} to a student.
     */
    public CommentCommand(StudentNumber studentNumber, Comment comment) {
        requireAllNonNull(studentNumber, comment);

        this.studentNumber = studentNumber;
        this.comment = comment;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudent(new Student(studentNumber))) {
            throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENT_NUMBER);
        }

        Student student = model.getStudent(studentNumber);

        Student editedStudent = new Student(
                student.getName(), student.getPhone(), student.getEmail(),
                student.getStudentNumber(), student.getClassDetails(), student.getTags(), comment);

        model.setStudent(student, editedStudent);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_COMMENT_SUCCESS, Messages.format(student)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        CommentCommand e = (CommentCommand) other;
        return studentNumber.equals(e.studentNumber)
                && comment.equals(e.comment);
    }
}
