package seedu.classmanager.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.classmanager.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classmanager.logic.parser.ArgumentMultimap.areAdditionalPrefixesPresent;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.classmanager.logic.parser.CliSyntax.PREFIX_TUTORIAL_INDEX;

import seedu.classmanager.commons.core.index.Index;
import seedu.classmanager.logic.commands.MarkPresentCommand;
import seedu.classmanager.logic.parser.exceptions.ParseException;
import seedu.classmanager.model.student.ClassDetails;
import seedu.classmanager.model.student.StudentNumber;

/**
 * Parses input arguments and creates a new MarkPresentCommand object
 */
public class MarkPresentCommandParser implements Parser<MarkPresentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkPresentCommand
     * and returns a MarkPresentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkPresentCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_INDEX,
                PREFIX_STUDENT_NUMBER);

        if (!argMultimap.arePrefixesPresent(PREFIX_TUTORIAL_INDEX, PREFIX_STUDENT_NUMBER)
                || !argMultimap.getPreamble().isEmpty()
                || areAdditionalPrefixesPresent(args, PREFIX_TUTORIAL_INDEX, PREFIX_STUDENT_NUMBER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkPresentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL_INDEX, PREFIX_STUDENT_NUMBER);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TUTORIAL_INDEX).get());
        } catch (ParseException e) {
            throw new ParseException(ClassDetails.getMessageInvalidTutorialIndex());
        }

        StudentNumber studentNumber = ParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_STUDENT_NUMBER).get());

        return new MarkPresentCommand(index, studentNumber);
    }
}
